package com.mohnad.tareqstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {

    public interface OnCategoryClick { void onClick(Category category); }

    private final ArrayList<Category> list;
    private final OnCategoryClick listener;

    public CategoryAdapter(ArrayList<Category> list, OnCategoryClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Category c = list.get(position);

        h.tvTitle.setText(c.name);
        h.tvSub.setText("Tap to explore");
        h.imgCat.setImageResource(c.imageRes);

        h.card.setOnClickListener(v -> {
            v.animate().scaleX(0.98f).scaleY(0.98f).setDuration(80)
                    .withEndAction(() -> v.animate().scaleX(1f).scaleY(1f).setDuration(80)
                            .withEndAction(() -> listener.onClick(c))).start();
        });
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView tvTitle, tvSub;
        ImageView imgCat;

        VH(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSub = itemView.findViewById(R.id.tvSub);
            imgCat = itemView.findViewById(R.id.imgCat);
        }
    }
}
