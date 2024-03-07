package com.example.lab1_md18306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.Map;

public class AddCities extends AppCompatActivity {
    EditText edtName,edtCountry,edtPopulation;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cities);
        db = FirebaseFirestore.getInstance(); // Thêm dòng này để khởi tạo đối tượng FirebaseFirestore
        edtName= findViewById(R.id.edtName);
        edtCountry= findViewById(R.id.edtCountry);
        edtPopulation= findViewById(R.id.edtPopulation);
        Button btnsave = findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToFirestore();
            }
        });

    }
    private void addDataToFirestore() {
        String name = edtName.getText().toString();
        String address =edtCountry.getText().toString();
        long population = Long.parseLong(edtPopulation.getText().toString());

        // Kiểm tra các trường nhập liệu

        // Tạo một đối tượng Map để đại diện cho dữ liệu mới
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("country", address);
        data.put("population", population);


        // Thêm dữ liệu vào Firestore
        db.collection("cities")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Xử lý thành công
                        // Ví dụ: hiển thị thông báo thành công hoặc làm sạch form
                        Toast.makeText(AddCities.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddCities.this, ListItem.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý lỗi
                        // Ví dụ: hiển thị thông báo lỗi
                    }
                });
    }
}