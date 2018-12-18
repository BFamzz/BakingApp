package com.bfamz.bakingapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bfamz.bakingapp.R;
import com.bfamz.bakingapp.adapters.RecipeAdapter;
import com.bfamz.bakingapp.models.Recipe;
import com.bfamz.bakingapp.utils.NetworkUtils;
import com.bfamz.bakingapp.utils.RecipeLoader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Recipe>> {

    @BindView(R.id.recipe_recyclerView)
    RecyclerView recipeRecyclerView;

    private RecipeAdapter recipeAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Recipe> mRecipeList;

    private URL mRecipeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecipeList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(this, mRecipeList);

        linearLayoutManager = new LinearLayoutManager(this);

        recipeRecyclerView.setLayoutManager(linearLayoutManager);
        recipeRecyclerView.setHasFixedSize(true);
//        recipeRecyclerView.setAdapter(recipeAdapter);
        LoaderManager.getInstance(this).initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, @Nullable Bundle bundle) {
        mRecipeUrl = NetworkUtils.recipeUrl();
        return new RecipeLoader(this, mRecipeUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Recipe>> loader, List<Recipe> recipes) {
        if (recipes != null && !recipes.isEmpty()) {
            recipeAdapter = new RecipeAdapter(this, recipes);
            recipeAdapter.notifyDataSetChanged();
            recipeRecyclerView.setAdapter(recipeAdapter);
        } else {
            Log.i("recipes", "Recipe is null or empty");

        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Recipe>> loader) {

    }

}
