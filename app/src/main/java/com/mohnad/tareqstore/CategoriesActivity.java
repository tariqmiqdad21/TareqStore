package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

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

        RecyclerView rv = findViewById(R.id.rvCategories);
        rv.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<Category> categories = StoreRepository.getCategories();

        CategoryAdapter adapter = new CategoryAdapter(categories, c -> {
            Intent i = new Intent(CategoriesActivity.this, ProductsActivity.class);
            i.putExtra("category_id", c.id);
            i.putExtra("category_name", c.name);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        rv.setAdapter(adapter);

    }
}
