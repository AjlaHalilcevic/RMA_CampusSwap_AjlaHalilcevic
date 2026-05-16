package com.ajla.campusswap.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ajla.campusswap.adapters.ItemAdapter;
import com.ajla.campusswap.viewmodels.ItemViewModel;
import com.ajla.campusswap.models.Item;
import com.ajla.campusswap.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private androidx.appcompat.widget.SearchView searchView;
    private List<Item> fullItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                CharSequence title = item.getTitle();
                if (title != null) {
                    String itemTitle = title.toString().toLowerCase();
                    if (itemTitle.equals("home")) {
                        return true;
                    } else if (itemTitle.equals("profile")) {
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getItemsLiveData().observe(this, items -> {
            if (items != null) {
                fullItemList = items;
                adapter = new ItemAdapter(items);
                recyclerView.setAdapter(adapter);
                if (items.isEmpty()) {
                    android.widget.Toast.makeText(this, "No items found in database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void filter(String text) {
        List<Item>filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(fullItemList);
        } else {
            for (Item item : fullItemList) {
                if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        if (adapter != null) {
            adapter.updateList(filteredList);
        }
    }
}