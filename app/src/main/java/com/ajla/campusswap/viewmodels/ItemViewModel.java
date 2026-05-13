package com.ajla.campusswap.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.ajla.campusswap.models.Item;
import com.ajla.campusswap.repository.ItemRepository;
import java.util.List;
public class ItemViewModel extends ViewModel {
    private ItemRepository repository;
    private LiveData<List<Item>>itemsLiveData;

    public ItemViewModel() {
        repository = new ItemRepository();
        itemsLiveData = repository.getItems();
    }
    public  LiveData<List<Item>>getItemsLiveData() { return itemsLiveData; }

}