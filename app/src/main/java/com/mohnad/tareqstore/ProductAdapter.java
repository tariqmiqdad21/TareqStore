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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    public interface OnProductClick { void onDetails(Product product); }

    private final ArrayList<Product> list;
    private final OnProductClick listener;

    public ProductAdapter(ArrayList<Product> list, OnProductClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Product p = list.get(position);
        h.tvName.setText(p.name);
        h.tvPrice.setText("$ " + String.format("%.2f", p.price));
        h.img.setImageResource(p.imageRes);

        h.btnDetails.setOnClickListener(v -> listener.onDetails(p));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvPrice;
        MaterialButton btnDetails;

        VH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}
