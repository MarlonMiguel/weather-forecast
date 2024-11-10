package com.example.trabalho;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        // Fecha a activity ao clicar em qualquer parte da tela
        findViewById(R.id.sobre_container).setOnClickListener(v -> finish());
    }
}