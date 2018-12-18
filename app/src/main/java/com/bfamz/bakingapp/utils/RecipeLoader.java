package com.bfamz.bakingapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.bfamz.bakingapp.models.Recipe;

import java.net.URL;
import java.util.List;

public class RecipeLoader extends AsyncTaskLoader<List<Recipe>> {

    // Recipe Url
    private final URL mRecipeUrl;

    // List of recipes
    private List<Recipe> mRecipes;

    public RecipeLoader(@NonNull Context context, URL recipeUrl) {
        super(context);
        this.mRecipeUrl = recipeUrl;
    }
    @Nullable
    @Override
    public List<Recipe> loadInBackground() {

        if (mRecipeUrl == null) {
            return null;
        }

        String jsonResponse = null;

        jsonResponse = NetworkUtils.httpRequest(mRecipeUrl);

        return JsonUtils.parseJson(jsonResponse);
    }

    @Override
    protected void onStartLoading() {
        if (mRecipes != null) {
            deliverResult(mRecipes);
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(@Nullable List<Recipe> data) {
        mRecipes = data;
        super.deliverResult(data);
    }
}
