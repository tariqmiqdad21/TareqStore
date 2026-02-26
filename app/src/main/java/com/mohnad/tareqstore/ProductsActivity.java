package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private final ArrayList<Product> all = new ArrayList<>();
    private final ArrayList<Product> filtered = new ArrayList<>();
    private ProductAdapter adapter;
    private RecyclerView rvProducts;
    private TextView tvEmptyProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(v -> finish());
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_cart) {
                startActivity(new Intent(this, CartActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;
            }
            return false;
        });

        String categoryId = getIntent().getStringExtra("category_id");
        String categoryName = getIntent().getStringExtra("category_name");
        if (categoryName == null) categoryName = "Products";
        topAppBar.setTitle(categoryName);

        rvProducts = findViewById(R.id.rvProducts);
        tvEmptyProducts = findViewById(R.id.tvEmptyProducts);

        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        // Load products from repository
        all.clear();
        List<Product> repoList = StoreRepository.getProducts(categoryId);
        if (repoList != null) all.addAll(repoList);

        filtered.clear();
        filtered.addAll(all);

        adapter = new ProductAdapter(filtered, product -> {
            Intent i = new Intent(ProductsActivity.this, DetailsActivity.class);
            i.putExtra("product", product);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        rvProducts.setAdapter(adapter);

        // Initial state
        updateEmptyState();

        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String q) {
        filtered.clear();
        String x = q == null ? "" : q.toLowerCase().trim();

        if (x.isEmpty()) {
            filtered.addAll(all);
        } else {
            for (Product p : all) {
                if (p.name != null && p.name.toLowerCase().contains(x)) {
                    filtered.add(p);
                }
            }
        }

        adapter.notifyDataSetChanged();
        updateEmptyState();
    }

    private void updateEmptyState() {
        boolean isEmpty = filtered.isEmpty();
        tvEmptyProducts.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        rvProducts.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
