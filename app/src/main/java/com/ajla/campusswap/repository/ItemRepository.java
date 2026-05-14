package com.ajla.campusswap.repository;

import android.app.DownloadManager;

import androidx.lifecycle.MutableLiveData;
import com.ajla.campusswap.models.Item;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
public class ItemRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public MutableLiveData<List<Item>>getItems() {
        MutableLiveData<List<Item>>itemsLiveData = new MutableLiveData<>();
        db.collection("items").addSnapshotListener((value, error) -> {
            if (error != null) {
                itemsLiveData.postValue(null);
                return;
            }
            List<Item>itemList = new ArrayList<>();
            if (value != null) {
                for (QueryDocumentSnapshot doc : value) {
                    Item item = doc.toObject(Item.class);
                    itemList.add(item);
                }
            }
            itemsLiveData.postValue(itemList);
        });
        return itemsLiveData;
    }

    public void addItem(Item item) {
        db.collection("items").add(item);
    }
}
