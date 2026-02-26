package com.mohnad.tareqstore;

import java.util.ArrayList;
import java.util.List;

public class StoreRepository {

    // =========================
    // Categories
    // =========================
    public static ArrayList<Category> getCategories() {
        ArrayList<Category> list = new ArrayList<>();

        list.add(new Category("c1", "Perfumes", R.drawable.a1));
        list.add(new Category("c2", "Gift Sets", R.drawable.a2));
        list.add(new Category("c3", "Body Care", R.drawable.a3));
        list.add(new Category("c4", "Accessories", R.drawable.a4));

        return list;
    }

    // =========================
    // Products by Category Id
    // =========================
    public static ArrayList<Product> getProducts(String categoryId) {
        ArrayList<Product> list = new ArrayList<>();

        if ("c1".equals(categoryId)) {
            list.add(new Product("p1", "Rose Mist", 19.99, R.drawable.p1));
            list.add(new Product("p2", "Vanilla Touch", 24.50, R.drawable.p2));
            list.add(new Product("p3", "Ocean Breeze", 18.75, R.drawable.p3));
            list.add(new Product("p4", "Amber Night", 29.00, R.drawable.p4));
        } else if ("c2".equals(categoryId)) {
            list.add(new Product("p5", "Gift Box A", 34.99, R.drawable.p5));
            list.add(new Product("p6", "Gift Box B", 27.50, R.drawable.p6));
        } else if ("c3".equals(categoryId)) {
            list.add(new Product("p7", "Body Lotion", 11.99, R.drawable.p7));
            list.add(new Product("p8", "Hand Cream", 7.99, R.drawable.p8));
        } else if ("c4".equals(categoryId)) {
            list.add(new Product("p9", "Mini Bag", 14.50, R.drawable.p9));
            list.add(new Product("p10", "Accessory Kit", 9.99, R.drawable.p10));
        }

        return list;
    }

    // Optional: search helper (لو بدك)
    public static ArrayList<Product> search(List<Product> base, String q) {
        ArrayList<Product> out = new ArrayList<>();
        String x = q == null ? "" : q.toLowerCase().trim();
        if (x.isEmpty()) {
            out.addAll(base);
            return out;
        }
        for (Product p : base) {
            if (p.name.toLowerCase().contains(x)) out.add(p);
        }
        return out;
    }
}
