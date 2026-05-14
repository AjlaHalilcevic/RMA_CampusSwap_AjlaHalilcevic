package com.ajla.campusswap.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.ajla.campusswap.R;
import com.ajla.campusswap.models.Item;
import com.ajla.campusswap.viewmodels.ItemViewModel;
import com.google.firebase.auth.FirebaseAuth;
import java.util.UUID;
public class AddItemActivity extends AppCompatActivity {
    private EditText editTitle, editPrice, editDescription;
    private Button btnSave;
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        editTitle = findViewById(R.id.editTitle);
        editPrice = findViewById(R.id.editPrice);
        editDescription = findViewById(R.id.editDescription);
        btnSave = findViewById(R.id.btnSave);
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString().trim();
            String price = editPrice.getText().toString().trim();
            String desc = editDescription.getText().toString().trim();
            String userId = FirebaseAuth.getInstance().getUid();
            if (userId == null) {
                Toast.makeText(this, "Error: You must be logged in!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!title.isEmpty() && !price.isEmpty()) {
                String itemId = UUID.randomUUID().toString();
                com.ajla.campusswap.models.Item newItem = new com.ajla.campusswap.models.Item(itemId, title, desc, price, "default", userId);

                itemViewModel.addItem(newItem);
                Toast.makeText(this, "Item posted!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
