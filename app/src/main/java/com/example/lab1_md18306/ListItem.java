package com.example.lab1_md18306;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
public class ListItem extends AppCompatActivity {
    private FirebaseFirestore db;
    private ListView listView;
    private MyAdapter adapter;
    private List<QueryDocumentSnapshot> documents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        Button btnadd = findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListItem.this, AddCities.class);
                startActivity(intent);
            }
        });


        db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.list_view);

        documents = new ArrayList<>();
        adapter = new MyAdapter(this, R.layout.listcities, documents);
        listView.setAdapter(adapter);

        loadDataFromFirestore();
    }

    private void loadDataFromFirestore() {
        db.collection("cities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            documents.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                documents.add(document);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            // Xử lý lỗi tại đây
                        }
                    }
                });
    }
}