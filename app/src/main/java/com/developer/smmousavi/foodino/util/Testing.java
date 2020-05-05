package com.developer.smmousavi.foodino.util;

import android.util.Log;

import com.developer.smmousavi.foodino.models.Recipe;

import java.util.List;

public class Testing {

    public static void printRecipes(List<Recipe> recipes, String tag) {
        for (Recipe recipe : recipes)
            Log.i(tag, recipe.getTitle());
    }

}
