package com.ajla.campusswap.views;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.ajla.campusswap.adapters.ItemAdapter;
import com.ajla.campusswap.viewmodels.ItemViewModel;
import com.ajla.campusswap.R;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getItemsLiveData().observe(this, items -> {
            if (items != null) {
                adapter = new ItemAdapter(items);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}