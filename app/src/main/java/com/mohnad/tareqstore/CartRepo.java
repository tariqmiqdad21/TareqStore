package com.mohnad.tareqstore;

import java.util.ArrayList;

public class CartRepo {
    private static final ArrayList<Product> cart = new ArrayList<>();

    public static ArrayList<Product> getCart() {
        return cart;
    }

    public static void add(Product p) {
        for (Product item : cart) {
            if (item.id.equals(p.id)) {
                item.qty++;
                return;
            }
        }
        cart.add(p);
    }

    public static void inc(String id) {
        for (Product item : cart) {
            if (item.id.equals(id)) {
                item.qty++;
                return;
            }
        }
    }

    public static void dec(String id) {
        for (int i = 0; i < cart.size(); i++) {
            Product item = cart.get(i);
            if (item.id.equals(id)) {
                item.qty--;
                if (item.qty <= 0) cart.remove(i);
                return;
            }
        }
    }

    public static double total() {
        double t = 0;
        for (Product item : cart) t += item.price * item.qty;
        return t;
    }

    public static int count() {
        int c = 0;
        for (Product item : cart) c += item.qty;
        return c;
    }

    public static void clear() {
        cart.clear();
    }
}
