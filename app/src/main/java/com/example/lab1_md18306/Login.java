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

public class Login extends AppCompatActivity {
    private FirebaseAuth mAth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtemail = findViewById(R.id.edtUserdn);
        EditText edtpass = findViewById(R.id.edtPassdn);
        Button btndn = findViewById(R.id.btnDN);
        Button btnReset = findViewById(R.id.btnResetPass);
        Button btndk = findViewById(R.id.btnDKK);
        mAth = FirebaseAuth.getInstance();

        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString();
                String pass = edtpass.getText().toString();
                mAth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAth.getCurrentUser();
                            Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MhChinh.class);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "signInUserWithEmail: failure", task.getException());
                            Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ResetPWemail.class);
                startActivity(intent);

            }
        });
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}