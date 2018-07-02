package br.com.bmf.bmfmobile;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.bmf.bmfmobile.databinding.ActivityAnaliseBinding;
import br.com.bmf.bmfmobile.persistence.Ativo;
import br.com.bmf.bmfmobile.persistence.Preferences;

public class AnaliseActivity extends AppCompatActivity {

    private ActivityAnaliseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_analise);

        Gson gson = new Gson();

        List<Ativo> ativos = Arrays.asList(gson.fromJson(Preferences.JSON_ATIVOS.getString(this), Ativo[].class));
//        List<Ativo> ativos = new ArrayList<>();
//        ativos.add(new Ativo("VALE ON NM", 53.83F));
//        ativos.add(new Ativo("PETROBRAS PN N2", 15.83F));
//        ativos.add(new Ativo("ITAUUNIBANCOPN ED N1", 41.12F));
//        ativos.add(new Ativo("BRASIL ON NM", 26.29F));
//        ativos.add(new Ativo("B3 ON NM", 20.19F));

        Float valor = Preferences.VALOR.getFloat(this);

        binding.resultadoTl.removeAllViews();
        binding.resultadoTl.addView(LayoutInflater.from(this).inflate(R.layout.tablerow_header, null));

        for (Ativo ativo : ativos) {
            View view = LayoutInflater.from(this).inflate(R.layout.tablerow_row, null);
            binding.resultadoTl.addView(view);

            TextView codigoTv = view.findViewById(R.id.codigoTv);
            TextView valorTv = view.findViewById(R.id.valorTv);
            TextView quantidadeTv = view.findViewById(R.id.quantidadeTv);

            codigoTv.setText(ativo.getCodigo());

            String formatted = NumberFormat.getCurrencyInstance().format(ativo.getValor());

            valorTv.setText(formatted);

            if (valor > 0) {
                int qtd = (int)(valor / ativo.getValor());

                quantidadeTv.setText(String.valueOf(qtd));
            }
        }

        String formatted = NumberFormat.getCurrencyInstance().format(valor);

        binding.valorTv.setText(formatted);

    }
}
