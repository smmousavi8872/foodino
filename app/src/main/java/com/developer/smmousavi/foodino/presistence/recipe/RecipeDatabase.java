package com.developer.smmousavi.foodino.presistence.recipe;

import android.content.Context;

import com.developer.smmousavi.foodino.models.Recipe;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Recipe.class}, version = 1)
@TypeConverters({RecipeConverter.class})
public abstract class RecipeDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "recipes_db";

    public abstract RecipeDAO getRecipeDao();

    private static RecipeDatabase sInstance;

    public static RecipeDatabase getInstance(final Context context) {
        if (sInstance == null)
            sInstance = Room.databaseBuilder(
                context.getApplicationContext(),
                RecipeDatabase.class,
                DATABASE_NAME).build();
        return sInstance;
    }

}
