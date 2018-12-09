package com.example.pascual.eva3_15_clima_listas.Clima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pascual.eva3_15_clima_listas.R;

public class DetallesDeClima extends AppCompatActivity {

    Intent inDatos;
    ImageView imgvwClima;
    TextView txtCiduad, txtTemperatura, txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_de_clima);

        imgvwClima = findViewById(R.id.imgVwClima_detallesdeclima);
        txtCiduad = findViewById(R.id.txtVwCiudad_detallesdeclima);
        txtTemperatura = findViewById(R.id.txtVwTemperatura_detallesdeclima);
        txtDescripcion = findViewById(R.id.txtVwDescripcion_detallesdeclima);

        inDatos = getIntent();

        imgvwClima.setImageResource(inDatos.getIntExtra("IMAGEN", R.drawable.ic_launcher_foreground));
        txtCiduad.setText(inDatos.getStringExtra("CIUDAD"));
        //txtTemperatura.setText(inDatos.getStringExtra("TEMP") + "°");
        txtTemperatura.setText(inDatos.getIntExtra("TEMP", 0) + "°C" );
        txtDescripcion.setText(inDatos.getStringExtra("DESC"));

    }
}
