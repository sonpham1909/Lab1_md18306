package com.example.lab1_md18306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPWemail extends AppCompatActivity {
    private FirebaseAuth mAth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwemail);
        EditText edtEmail = findViewById(R.id.edtemailReset);
        Button btnReset = findViewById(R.id.btnRe);
        mAth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
//                mAth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(ResetPWemail.this, "Vui lòng kiểm tra hộp thư để cập nhật lại mật khẩu", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(ResetPWemail.this, "Lỗi", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                });
                mAth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ResetPWemail.this, "Vui lòng kiểm tra hộp thư để cập nhật lại mật khẩu", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ResetPWemail.this, "Lỗi", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

    }
}