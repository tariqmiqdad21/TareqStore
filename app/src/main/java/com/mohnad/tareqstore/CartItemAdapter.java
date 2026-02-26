package com.mohnad.tareqstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.VH> {

    public interface OnQtyChange {
        void onPlus(Product p);
        void onMinus(Product p);
    }

    private final ArrayList<Product> list;
    private final OnQtyChange listener;

    public CartItemAdapter(ArrayList<Product> list, OnQtyChange listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Product p = list.get(position);

        h.img.setImageResource(p.imageRes);
        h.tvName.setText(p.name);
        h.tvPrice.setText("$ " + String.format("%.2f", p.price));
        h.tvQty.setText(String.valueOf(p.qty));

        h.btnPlus.setOnClickListener(v -> listener.onPlus(p));
        h.btnMinus.setOnClickListener(v -> listener.onMinus(p));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvPrice, tvQty;
        MaterialButton btnPlus, btnMinus;

        VH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
        }
    }
}
