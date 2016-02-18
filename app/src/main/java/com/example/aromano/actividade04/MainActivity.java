package com.example.aromano.actividade04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_refeicao = (EditText) findViewById(R.id.et_refeicao);
        final EditText et_bebidas = (EditText) findViewById(R.id.et_bebidas);
        final EditText et_taxaiva = (EditText) findViewById(R.id.et_taxaiva);
        final EditText et_numpessoas = (EditText) findViewById(R.id.et_numpessoas);
        final EditText et_total = (EditText) findViewById(R.id.et_total);
        final EditText et_totalporpessoa = (EditText) findViewById(R.id.et_totalporpessoa);
        final Button btn_calcular = (Button) findViewById(R.id.btn_calcular);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float refeicao = Float.parseFloat(et_refeicao.getText().toString());
                float bebidas = Float.parseFloat(et_bebidas.getText().toString());
                float total;
                float totalporpessoa;
                float taxaiva;
                float numpessoas;

                try {
                    taxaiva = (Float.parseFloat(et_taxaiva.getText().toString()) / 100) + 1;
                } catch (Exception ex) {
                    taxaiva = 1.23f;
                }

                try {
                    numpessoas = Float.parseFloat(et_numpessoas.getText().toString());
                } catch(Exception ex) {
                    numpessoas = 1;
                }

                total = calcularTotal(refeicao, bebidas, taxaiva);
                totalporpessoa = calcularTotalPorPessoa(refeicao, bebidas, taxaiva, numpessoas);

                et_total.setText(String.valueOf(total));
                et_totalporpessoa.setText(String.valueOf(totalporpessoa));
            }
        });
    }

    private float calcularTotal(float refeicao, float bebidas, float taxaiva) {
        float total = (refeicao + bebidas) * taxaiva;

        return total;
    }

    private float calcularTotalPorPessoa(float refeicao, float bebidas, float taxaiva, float numpessoas) {
        float total = (refeicao + bebidas) * taxaiva;
        total /= numpessoas;

        return total;
    }
}
