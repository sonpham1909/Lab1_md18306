package com.example.lab1_md18306;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MyAdapter extends ArrayAdapter<QueryDocumentSnapshot> {
    private Context context;
    private int resource;
    private List<QueryDocumentSnapshot> documents;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<QueryDocumentSnapshot> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.documents = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(resource, parent, false);
        }

        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemCountry = convertView.findViewById(R.id.item_country);
        TextView itemPopulation = convertView.findViewById(R.id.item_population);

        QueryDocumentSnapshot document = documents.get(position);
        String name = document.getString("name");
        String country = document.getString("country");
        long population = document.getLong("population");

        itemName.setText("Name: "+name);
        itemCountry.setText("Country: "+country);
        itemPopulation.setText("Population: "+population);

        // Gán dữ liệu khác cho các thành phần khác trong mục ListView nếu cần

        return convertView;
    }
}