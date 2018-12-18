package com.bfamz.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private int id;
    private String recipeName;
    private List<RecipeIngredients> recipeIngredients;
    private List<RecipeSteps> recipeSteps;
    private int servings;
    private String recipeImage;

    public Recipe(int id, String recipeName, List<RecipeIngredients> recipeIngredients,
                  List<RecipeSteps> recipeSteps, int servings, String recipeImage) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeSteps = recipeSteps;
        this.servings = servings;
        this.recipeImage = recipeImage;
    }

    private Recipe() {
        recipeIngredients = new ArrayList<>();
        recipeSteps = new ArrayList<>();
    }

    protected Recipe(Parcel in) {
        this();
        id = in.readInt();
        recipeName = in.readString();
        in.readTypedList(recipeIngredients, RecipeIngredients.CREATOR);
        in.readTypedList(recipeSteps, RecipeSteps.CREATOR);
        servings = in.readInt();
        recipeImage = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public List<RecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    public List<RecipeSteps> getRecipeSteps() {
        return recipeSteps;
    }

    public int getServings() {
        return servings;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(recipeName);
        parcel.writeTypedList(recipeIngredients);
        parcel.writeTypedList(recipeSteps);
        parcel.writeInt(servings);
        parcel.writeString(recipeImage);
    }
}
