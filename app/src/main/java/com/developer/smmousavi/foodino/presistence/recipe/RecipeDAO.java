package com.developer.smmousavi.foodino.presistence.recipe;

import com.developer.smmousavi.foodino.models.Recipe;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RecipeDAO {

    @Insert(onConflict = IGNORE)
    long[] insertRecipes(Recipe... recipes);
    // in case of success: {id1, id2, id3, id4}
    // in case of failure: {-1, id2, -1, -1}

    @Insert(onConflict = REPLACE)
    void insertRecipe(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);

    @Query("UPDATE recipes SET title = :title, publisher = :publisher, image_url = :imageUrl, social_rank = :social_rank "
        + "WHERE recipe_id = :recipeId ")
    void updateRecipe(String recipeId, String title, String publisher, String imageUrl, float social_rank);

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :query || '%' OR ingredients LIKE '%' || :query || '%' " +
        "ORDER BY social_rank DESC LIMIT (:pageNumber * 30)")
    LiveData<List<Recipe>> searchRecipes(String query, int pageNumber);

    @Query("SELECT * FROM recipes WHERE recipe_id = :recipe_id")
    LiveData<Recipe> getRecipe(String recipe_id);

}
