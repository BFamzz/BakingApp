package com.bfamz.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bfamz.bakingapp.R;
import com.bfamz.bakingapp.adapters.RecipeDetailsAdapter;
import com.bfamz.bakingapp.models.Recipe;
import com.bfamz.bakingapp.models.RecipeIngredients;
import com.bfamz.bakingapp.models.RecipeSteps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetails extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "recipe";

    @BindView(R.id.recipe_details_name)
    TextView recipeDetailsName;

    @BindView(R.id.recipe_details_steps)
    TextView recipeDetailsSteps;

    @BindView(R.id.recipe_details_ingredient)
    TextView recipeDetailsIngredients;

    @BindView(R.id.recipe_details_recyclerView)
    RecyclerView recipeDetailsRecyclerView;

    private Recipe mRecipe;

    private List<RecipeSteps> mRecipeSteps;
    private List<RecipeIngredients> mRecipeIngredients;

    private RecipeDetailsAdapter recipeDetailsAdapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        mRecipe = bundle.getParcelable(EXTRA_RECIPE);

//        mRecipeSteps = new ArrayList<>();

        assert mRecipe != null;
        recipeDetailsName.setText(mRecipe.getRecipeName());

        mRecipeSteps = mRecipe.getRecipeSteps();
        mRecipeIngredients = mRecipe.getRecipeIngredients();



        recipeDetailsIngredients.setText(getString(R.string.recipe_ingredients));

        recipeDetailsIngredients.setOnClickListener(view -> sendRecipeIngredients());
        recipeDetailsSteps.setText(getString(R.string.recipe_steps));

        recipeDetailsAdapter = new RecipeDetailsAdapter(mRecipeSteps, this);
        linearLayoutManager = new LinearLayoutManager(this);

        recipeDetailsRecyclerView.setLayoutManager(linearLayoutManager);
        recipeDetailsRecyclerView.setHasFixedSize(true);
        recipeDetailsRecyclerView.setAdapter(recipeDetailsAdapter);
    }

    private void sendRecipeIngredients() {
        Intent ingredientIntent = new Intent(this, RecipeIngredientList.class);
        ingredientIntent.putParcelableArrayListExtra(RecipeIngredientList.RECIPE_INGREDIENT,
                (ArrayList<? extends Parcelable>) mRecipeIngredients);
        startActivity(ingredientIntent);
    }
}
