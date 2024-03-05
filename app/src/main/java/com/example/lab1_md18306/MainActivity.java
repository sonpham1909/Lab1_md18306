package com.example.lab1_md18306;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtemail = findViewById(R.id.edtUserdk);
        EditText edtpass = findViewById(R.id.edtPassdk);
        Button btndk = findViewById(R.id.btnDK);
        Button btndn = findViewById(R.id.btnDNN);
        mAuth = FirebaseAuth.getInstance();

//        String email = "sonpvph33179@gmail.com";
//        String pass = "Sonpv@12345";
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString();
                String pass = edtpass.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, Login.class);
                                    startActivity(intent);
                                } else {
                                    Log.w(TAG, "createUserWithEmail: failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);

            }
        });


    }
}