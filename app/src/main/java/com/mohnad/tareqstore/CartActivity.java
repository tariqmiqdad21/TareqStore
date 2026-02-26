package com.mohnad.tareqstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class CartActivity extends AppCompatActivity {

    private TextView tvEmpty, tvTotal;
    private RecyclerView rvCart;
    private MaterialButton btnCheckout;

    private CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(v -> finish());

        tvEmpty = findViewById(R.id.tvEmpty);
        tvTotal = findViewById(R.id.tvTotal);
        rvCart = findViewById(R.id.rvCart);
        btnCheckout = findViewById(R.id.btnCheckout);

        rvCart.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CartItemAdapter(CartRepo.getCart(), new CartItemAdapter.OnQtyChange() {
            @Override
            public void onPlus(Product p) {
                CartRepo.inc(p.id);
                refresh();
            }

            @Override
            public void onMinus(Product p) {
                CartRepo.dec(p.id);
                refresh();
            }
        });

        rvCart.setAdapter(adapter);

        btnCheckout.setOnClickListener(v -> {
            if (CartRepo.getCart().isEmpty()) {
                startActivity(new Intent(this, CategoriesActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return;
            }

            // Go to Checkout screen and send total
            Intent i = new Intent(CartActivity.this, CheckoutActivity.class);
            i.putExtra("total", CartRepo.total());
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
}
    private void refresh () {
        boolean empty = CartRepo.getCart().isEmpty();

        tvEmpty.setVisibility(empty ? View.VISIBLE : View.GONE);
        rvCart.setVisibility(empty ? View.GONE : View.VISIBLE);
        tvTotal.setVisibility(empty ? View.GONE : View.VISIBLE);

        if (empty) {
            btnCheckout.setText("Browse Products");
        } else {
            btnCheckout.setText("Checkout");
            tvTotal.setText("Total: $ " + String.format("%.2f", CartRepo.total()));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

}