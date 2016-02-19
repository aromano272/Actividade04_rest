package com.example.aromano.actividade04;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_refeicao = (EditText) findViewById(R.id.et_refeicao);
        final EditText et_bebidas = (EditText) findViewById(R.id.et_bebidas);
//        final EditText et_taxaiva = (EditText) findViewById(R.id.et_taxaiva);
        final RadioButton rb_iva13 = (RadioButton) findViewById(R.id.rb_iva13);
        final RadioButton rb_iva23 = (RadioButton) findViewById(R.id.rb_iva23);
        final EditText et_numpessoas = (EditText) findViewById(R.id.et_numpessoas);
        final TextView et_total = (TextView) findViewById(R.id.tv_total);
        final TextView et_totalporpessoa = (TextView) findViewById(R.id.tv_totalporpessoa);
        final Button btn_calcular = (Button) findViewById(R.id.btn_calcular);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float refeicao = 0;
                float bebidas = 0;
                float total;
                float totalporpessoa;
                float taxaiva;
                float numpessoas;

                try {
                    refeicao = Float.parseFloat(et_refeicao.getText().toString());
                    bebidas = Float.parseFloat(et_bebidas.getText().toString());
                } catch(Exception ex) {
                    Toast.makeText(MainActivity.this, "Erro de insercao de dados", Toast.LENGTH_LONG).show();
                }

                /*
                try {
                    taxaiva = (Float.parseFloat(et_taxaiva.getText().toString()) / 100);
                } catch (Exception ex) {
                    taxaiva = 0.23f;
                }*/

                // verificar se o radiobutton de iva 23% esta selecionado
                taxaiva = (rb_iva23.isChecked() ? 0.23f : 0.13f);

                try {
                    numpessoas = Float.parseFloat(et_numpessoas.getText().toString());
                } catch(Exception ex) {
                    numpessoas = 1;
                }

                total = calcularTotal(refeicao, bebidas, taxaiva);
                totalporpessoa = calcularTotalPorPessoa(refeicao, bebidas, taxaiva, numpessoas);

                // fazemos um template para posteriormente poderemos formatar numeros chamando euroTemplate.format(numero)
                DecimalFormat euroTemplate = new DecimalFormat("#.## EUR");
                euroTemplate.setMinimumFractionDigits(2);

                et_total.setText(euroTemplate.format((double) total));
                et_totalporpessoa.setText(euroTemplate.format((double)totalporpessoa));

//                et_total.setText(String.valueOf(total));
//                et_totalporpessoa.setText(String.valueOf(totalporpessoa));

                // dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("Resultado");
                dialog.setNeutralButton("Total " + String.format("%.2f", total) + " €\nPor Pessoa "
                        + String.format("%.2f", totalporpessoa) + " €", null);
                dialog.show();
            }
        });
    }

    private float calcularTotal(float refeicao, float bebidas, float taxaiva) {
        float total = (refeicao + bebidas) * (taxaiva + 1);

        return total;
    }

    private float calcularTotalPorPessoa(float refeicao, float bebidas, float taxaiva, float numpessoas) {
        float total = (refeicao + bebidas) * (taxaiva + 1);
        total /= numpessoas;

        return total;
    }
}
