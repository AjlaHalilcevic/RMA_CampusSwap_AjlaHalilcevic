package com.ajla.campusswap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ajla.campusswap.R;
import com.ajla.campusswap.models.Item;
import com.ajla.campusswap.viewmodels.ItemViewModel;

import java.util.List;
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item>itemList;
    public ItemAdapter(List<Item>itemList) {
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());
        com.bumptech.glide.Glide.with(holder.itemView.getContext()).load(item.getImageUrl()).placeholder(android.R.drawable.ic_menu_gallery)
                        .error(android.R.drawable.stat_notify_error).into(holder.image);
    }
    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ImageView image;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            price = itemView.findViewById(R.id.itemPrice);
            image = itemView.findViewById(R.id.itemImage);
        }
    }
    public void updateList(java.util.List<com.ajla.campusswap.models.Item>newList) {
        this.itemList = newList;
        notifyDataSetChanged();
    }
}
