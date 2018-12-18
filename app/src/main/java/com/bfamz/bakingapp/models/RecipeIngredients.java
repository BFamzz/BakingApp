package com.bfamz.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeIngredients implements Parcelable {

    private double quantity;
    private String measure;
    private String ingredient;

    public RecipeIngredients(double quantity, String measure, String ingredient) {
        super();
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    protected RecipeIngredients(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<RecipeIngredients> CREATOR = new Creator<RecipeIngredients>() {
        @Override
        public RecipeIngredients createFromParcel(Parcel in) {
            return new RecipeIngredients(in);
        }

        @Override
        public RecipeIngredients[] newArray(int size) {
            return new RecipeIngredients[size];
        }
    };

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }
}
