package com.bfamz.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bfamz.bakingapp.R;
import com.bfamz.bakingapp.models.RecipeIngredients;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<RecipeIngredientsAdapter.ViewHolder> {

    private List<RecipeIngredients> mRecipeIngredients;
    private Context mContext;

    public RecipeIngredientsAdapter(List<RecipeIngredients> mRecipeIngredients, Context mContext) {
        this.mRecipeIngredients = mRecipeIngredients;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecipeIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_ingredient_item, viewGroup, false);
        return new RecipeIngredientsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientsAdapter.ViewHolder viewHolder, int position) {
        RecipeIngredients recipeIngredients = mRecipeIngredients.get(position);

        viewHolder.ingredients.setText(recipeIngredients.getIngredient());
        viewHolder.quantity.setText(recipeIngredientAndMeasure(recipeIngredients));
    }

    @Override
    public int getItemCount() {
        if (mRecipeIngredients != null) {
            return mRecipeIngredients.size();
        } else
            return 0;
    }

    private String recipeIngredientAndMeasure(RecipeIngredients recipeIngredients) {
        double recipeIngredientQuantity = recipeIngredients.getQuantity();
        String recipeIngredientMeasure = recipeIngredients.getMeasure();

        return mContext.getString(R.string.recipe_ingredient_quantity) + " " +
                String.valueOf(recipeIngredientQuantity) + " " + recipeIngredientMeasure;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredients)
        TextView ingredients;

        @BindView(R.id.quantity)
        TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
