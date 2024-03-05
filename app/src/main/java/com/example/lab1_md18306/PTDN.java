package com.example.lab1_md18306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PTDN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptdn);
        Button btnEmail = findViewById(R.id.btnEmail);
        Button btnPhone = findViewById(R.id.btnPhone);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PTDN.this, Login.class);
                startActivity(intent);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PTDN.this, LoginWithPhone.class);
                startActivity(intent);
            }
        });
    }
}