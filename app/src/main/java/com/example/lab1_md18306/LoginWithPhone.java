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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginWithPhone extends AppCompatActivity {
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerifacationId;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login_with_phone);
        EditText edtPhone = findViewById(R.id.edtPhone);
        EditText edtOtp = findViewById(R.id.edtOTP);

        Button btnGetOtp = findViewById(R.id.btngetOTP);
        Button btnDN = findViewById(R.id.btnDNOTP);
        btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện lấy OTP
                String phoneNumber = edtPhone.getText().toString();
                // Gửi mã xác minh đến số điện thoại
                // ...
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+84"+phoneNumber)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(LoginWithPhone.this)
                                .setCallbacks(mCallbacks)
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);


            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Xử lý khi xác minh số điện thoại hoàn tất tự động
                Toast.makeText(LoginWithPhone.this, "Đã gửi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Xử lý khi xác minh số điện thoại thất bại
                String errorMes = e.getMessage();
                Toast.makeText(LoginWithPhone.this, errorMes, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                // Lưu trữ verificationId để sử dụng trong quá trình đăng nhập
                mVerifacationId = verificationId;
            }
        };

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện đăng nhập
                String userOTP = edtOtp.getText().toString();
                // Hoàn tất quá trình đăng nhập bằng số điện thoại
                // ...
                verifyOTP(userOTP);
            }
        });

    }
    private void verifyOTP(String userOTP) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerifacationId, userOTP);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

                mAuth.signInWithCredential(credential).addOnCompleteListener(LoginWithPhone.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginWithPhone.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(LoginWithPhone.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}