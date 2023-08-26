package com.example.conversormoneda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import java.text.DecimalFormat;

import com.example.conversormoneda.MainActivityViewModel;
import com.example.conversormoneda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mv;
    private ActivityMainBinding binding;
    private RadioButton rbDolar;
    private RadioButton rbEuro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = new ViewModelProvider(this).get(MainActivityViewModel.class);

        rbDolar = binding.rbDolar;
        rbEuro = binding.rbEuro;

        // Set listeners for radio buttons
        rbDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDolarConversion();
            }
        });

        rbEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEuroConversion();
            }
        });

        binding.btConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dolar = Integer.parseInt(binding.etDolar.getText().toString());
                int euro = Integer.parseInt(binding.etEuro.getText().toString());
                mv.convertir(dolar, euro);
            }
        });

        mv.getValorDolar().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String d) {
                if (rbDolar.isChecked()) {
                    displayDolarResult(d);
                }
            }
        });

        mv.getValorEuro().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String e) {
                if (rbEuro.isChecked()) {
                    displayEuroResult(e);
                }
            }
        });

        // Initialize the view with Dolar conversion selected
        enableDolarConversion();
    }

    private void enableDolarConversion() {
        rbDolar.setChecked(true);
        rbEuro.setChecked(false);
        binding.etDolar.setEnabled(true);
        binding.etEuro.setEnabled(false);
        binding.etEuro.setText("0");
        binding.tvResultado.setText("EUR€ 0.00");
    }

    private void enableEuroConversion() {
        rbDolar.setChecked(false);
        rbEuro.setChecked(true);
        binding.etDolar.setEnabled(false);
        binding.etDolar.setText("0");
        binding.etEuro.setEnabled(true);
        binding.tvResultado.setText("USD$ 0.00");
    }

    private void displayDolarResult(String result) {
        binding.tvResultado.setText("EUR€ " + result);
    }

    private void displayEuroResult(String result) {
        binding.tvResultado.setText("USD$ " + result);
    }
}
