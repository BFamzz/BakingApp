package com.bfamz.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bfamz.bakingapp.R;
import com.bfamz.bakingapp.models.RecipeSteps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.ViewHolder> {
    private List<RecipeSteps> mRecipeSteps;
    private Context mContext;

    public RecipeDetailsAdapter(List<RecipeSteps> mRecipeSteps, Context mContext) {
        this.mRecipeSteps = mRecipeSteps;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecipeDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_step_item, parent, false);
        return new RecipeDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailsAdapter.ViewHolder viewHolder, int i) {
        RecipeSteps recipeSteps = mRecipeSteps.get(i);
        viewHolder.steps.setText(recipeSteps.getShortDescription());
    }

    @Override
    public int getItemCount() {

        if (mRecipeSteps != null) {
            return mRecipeSteps.size();
        } else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.steps)
        TextView steps;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
