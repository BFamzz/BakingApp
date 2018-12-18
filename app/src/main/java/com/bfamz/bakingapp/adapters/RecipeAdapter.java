package com.bfamz.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bfamz.bakingapp.R;
import com.bfamz.bakingapp.activities.RecipeDetails;
import com.bfamz.bakingapp.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context mContext;
    private List<Recipe> mRecipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.mContext = context;
        this.mRecipeList = recipeList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_item, parent, false);
        return new RecipeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Recipe recipe = mRecipeList.get(position);
        viewHolder.recipeName.setText(recipe.getRecipeName());
        Picasso.get()
                .load(recipe.getRecipeImage().trim())
                .into(viewHolder.recipeImageView);

        viewHolder.itemView.setOnClickListener(view -> {
            Intent recipeDetails = new Intent(mContext, RecipeDetails.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(RecipeDetails.EXTRA_RECIPE, recipe);
            recipeDetails.putExtras(bundle);
            mContext.startActivity(recipeDetails);

        });

    }

    @Override
    public int getItemCount() {
        if (mRecipeList != null) {
            return mRecipeList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipe_poster)
        ImageView recipeImageView;

        @BindView(R.id.recipe_name)
        TextView recipeName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
