package com.mohnad.tareqstore;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    public String id;
    public String name;
    public double price;
    public int imageRes;
    public int qty;

    public Product(String id, String name, double price, int imageRes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
        this.qty = 1;
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readDouble();
        imageRes = in.readInt();
        qty = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override public Product createFromParcel(Parcel in) { return new Product(in); }
        @Override public Product[] newArray(int size) { return new Product[size]; }
    };

    @Override public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(imageRes);
        dest.writeInt(qty);
    }
}
