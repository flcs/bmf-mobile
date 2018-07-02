package br.com.bmf.bmfmobile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.NumberFormat;

import br.com.bmf.bmfmobile.databinding.ActivityInvestimentoBinding;
import br.com.bmf.bmfmobile.persistence.Preferences;
import br.com.bmf.bmfmobile.widget.MoneyTextWatcher;

public class InvestimentoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityInvestimentoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_investimento);

        binding.valorEt.addTextChangedListener(new MoneyTextWatcher(binding.valorEt));

        String formatted = NumberFormat.getCurrencyInstance().format(Preferences.VALOR.getFloat(this));
        binding.valorEt.setText(formatted);

        binding.confirmarBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Analisando", Toast.LENGTH_LONG).show();

        String cleanString = binding.valorEt.getText().toString().replaceAll("[R$,.]", "");
        BigDecimal parsed = new BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
        Preferences.VALOR.putFloat(this, parsed.floatValue());

        Intent intent = new Intent(this, AnaliseActivity.class);

        startActivity(intent);
    }
}
