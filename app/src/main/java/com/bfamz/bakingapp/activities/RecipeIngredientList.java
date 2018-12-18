package com.bfamz.bakingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bfamz.bakingapp.R;
import com.bfamz.bakingapp.adapters.RecipeIngredientsAdapter;
import com.bfamz.bakingapp.models.RecipeIngredients;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientList extends AppCompatActivity {

    public static final String RECIPE_INGREDIENT = "recipe_ingredients";

    @BindView(R.id.recipe_ingredient_list_rv)
    RecyclerView mRecipeIngredientsRecyclerView;

    private ArrayList<RecipeIngredients> mRecipeIngredients;
    private RecipeIngredientsAdapter mRecipeIngredientsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredient_list);

        ButterKnife.bind(this);

        mRecipeIngredients = getIntent().getParcelableArrayListExtra(RECIPE_INGREDIENT);

        mRecipeIngredientsAdapter = new RecipeIngredientsAdapter(mRecipeIngredients, this);
        mLayoutManager = new LinearLayoutManager(this);

        mRecipeIngredientsRecyclerView.setLayoutManager(mLayoutManager);
        mRecipeIngredientsRecyclerView.setHasFixedSize(true);
        mRecipeIngredientsRecyclerView.setAdapter(mRecipeIngredientsAdapter);
    }
}
