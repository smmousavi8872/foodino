package com.developer.smmousavi.foodino.models;

import com.developer.smmousavi.foodino.presistence.recipe.RecipeConverter;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "recipes")
public class Recipe {


    @SerializedName("recipe_id")
    @ColumnInfo(name = "recipe_id")
    @PrimaryKey
    @NonNull
    private String mRecipeId;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    private String mPublisher;

    @TypeConverters(RecipeConverter.class)
    @ColumnInfo(name = "ingredients")
    @SerializedName("ingredients")
    private String[] mIngredients;

    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    private String mImageUrl;

    @ColumnInfo(name = "social_rank")
    @SerializedName("social_rank")
    private float mSocialRank;

    @ColumnInfo(name = "time_stamp")
    private int mTimestamp;

    public Recipe(@NonNull String recipeId, String title, String publisher, String[] ingredients, String imageUrl, float socialRank, int timestamp) {
        mRecipeId = recipeId;
        mTitle = title;
        mPublisher = publisher;
        mIngredients = ingredients;
        mImageUrl = imageUrl;
        mSocialRank = socialRank;
        mTimestamp = timestamp;
    }

    public Recipe() {
    }

    public String getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(String recipeId) {
        mRecipeId = recipeId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String[] getIngredients() {
        return mIngredients;
    }

    public void setIngredients(String[] ingredients) {
        mIngredients = ingredients;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public float getSocialRank() {
        return mSocialRank;
    }

    public void setSocialRank(float socialRank) {
        mSocialRank = socialRank;
    }

    public int getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(int timestamp) {
        mTimestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Recipe{" +
            "mRecipeId='" + mRecipeId + '\'' +
            ", mTitle='" + mTitle + '\'' +
            ", mPublisher='" + mPublisher + '\'' +
            ", mIngredients=" + Arrays.toString(mIngredients) +
            ", mImageUrl='" + mImageUrl + '\'' +
            ", mSocialRank=" + mSocialRank +
            ", mTimestamp=" + mTimestamp +
            '}';
    }
}