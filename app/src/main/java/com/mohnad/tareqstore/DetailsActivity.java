package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

        Product p = getIntent().getParcelableExtra("product");
        if (p == null) finish();

        ImageView img = findViewById(R.id.imgProduct);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvDesc = findViewById(R.id.tvDesc);
        MaterialButton btnAdd = findViewById(R.id.btnAdd);

        img.setImageResource(p.imageRes);
        tvName.setText(p.name);
        tvPrice.setText("$ " + String.format("%.2f", p.price));
        tvDesc.setText("Premium quality • Long lasting • Perfect for daily use.\nTrusted by customers and carefully selected.");

        btnAdd.setOnClickListener(v -> {
            CartRepo.add(new Product(p.id, p.name, p.price, p.imageRes));
            Snackbar.make(findViewById(R.id.root), "Added to cart", Snackbar.LENGTH_LONG)
                    .setAction("View", vv -> {
                        startActivity(new Intent(DetailsActivity.this, CartActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }).show();
        });
    }
}
