package com.bfamz.bakingapp.utils;

import com.bfamz.bakingapp.models.Recipe;
import com.bfamz.bakingapp.models.RecipeIngredients;
import com.bfamz.bakingapp.models.RecipeSteps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static Recipe mRecipe = null;
    private static List<Recipe> mRecipes = new ArrayList<>();

    public JsonUtils() {

    }

    public static List<Recipe> parseJson(String queryUrl) {

        List<RecipeIngredients> mRecipeIngredients;
        List<RecipeSteps> mRecipeSteps;

        try {
            JSONArray jsonArray = new JSONArray(queryUrl);

            for (int i = 0; i<jsonArray.length(); i++) {

                mRecipeIngredients = new ArrayList<>();
                mRecipeSteps = new ArrayList<>();
                JSONObject recipeObject = jsonArray.optJSONObject(i);
                int recipeId = recipeObject.optInt("id");
                String recipeName = recipeObject.optString("name");

                JSONArray recipeIngredientArray = recipeObject.optJSONArray("ingredients");
                for (int j = 0; j<recipeIngredientArray.length(); j++) {
                    JSONObject recipeIngredient = recipeIngredientArray.optJSONObject(j);
                    double quantity = recipeIngredient.optDouble("quantity");
                    String measure = recipeIngredient.optString("measure");
                    String ingredient = recipeIngredient.optString("ingredient");
                    RecipeIngredients recipeIngredients = new RecipeIngredients(quantity, measure, ingredient);
                    mRecipeIngredients.add(recipeIngredients);
                }

                JSONArray recipeStepArray = recipeObject.optJSONArray("steps");
                for (int k = 0; k<recipeStepArray.length(); k++) {
                    JSONObject recipeStep = recipeStepArray.optJSONObject(k);
                    int id = recipeStep.optInt("id");
                    String shortDescription = recipeStep.optString("shortDescription");
                    String description = recipeStep.optString("description");
                    String videoUrl = recipeStep.optString("videoURL");
                    RecipeSteps recipeSteps = new RecipeSteps(id, shortDescription, description, videoUrl);
                    mRecipeSteps.add(recipeSteps);
                }

                String recipeImage = null;

                switch (recipeName) {
                    case "Nutella Pie":
                        recipeImage = "https://hips.hearstapps.com/del.h-cdn.co/assets/16/32/1470773544-delish-nutella-cool-whip-pie-1.jpg";
                        break;
                    case "Brownies":
                        recipeImage = "https://images-gmi-pmc.edge-generalmills.com/c95a0455-70d0-4667-bc17-acfaf2894210.jpg";
                        break;
                    case "Yellow Cake":
                        recipeImage = "https://assets.epicurious.com/photos/57c5b45184c001120f616523/master/pass/moist-yellow-cake.jpg";
                        break;
                    case "Cheesecake":
                        recipeImage = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/104649.jpg";
                        break;
                }

                int servings = recipeObject.getInt("servings");
                mRecipe = new Recipe(recipeId, recipeName, mRecipeIngredients, mRecipeSteps, servings, recipeImage);
                mRecipes.add(mRecipe);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mRecipes;
    }
}
