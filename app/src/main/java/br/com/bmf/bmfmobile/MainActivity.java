package br.com.bmf.bmfmobile;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import br.com.bmf.bmfmobile.databinding.ActivityMainBinding;
import br.com.bmf.bmfmobile.persistence.Ativo;
import br.com.bmf.bmfmobile.persistence.Preferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.conservadorBtn.setOnClickListener(this);
        binding.arrojadoBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    MY_PERMISSIONS_REQUEST_INTERNET);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.conservadorBtn) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Aviso");
            alertDialogBuilder.setMessage("Procure outras formas de investimento!");
            alertDialogBuilder.setNegativeButton("Ok", (dialog, which) -> dialog.dismiss());

            alertDialogBuilder.create().show();

            return;
        }

        if (v.getId() == R.id.arrojadoBtn) {
            Thread thread = new Thread(() -> {
                try {
                    List<Ativo> ativos = AtivosFromURLUtil.get();

                    Gson gson = new Gson();
                    Preferences.JSON_ATIVOS.putString(MainActivity.this, gson.toJson(ativos.toArray()));

                    Intent intent = new Intent(MainActivity.this, InvestimentoActivity.class);

                    startActivity(intent);
                } catch (IOException e) {

                    Intent intent = new Intent(MainActivity.this, InvestimentoActivity.class);

                    startActivity(intent);
                }
            });

            thread.start();
        }
    }
}
