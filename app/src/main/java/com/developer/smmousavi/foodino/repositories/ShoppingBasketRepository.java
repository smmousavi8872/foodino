package com.developer.smmousavi.foodino.repositories;

import android.content.Context;
import android.util.Log;

import com.developer.smmousavi.foodino.constants.Constants;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.models.Specifications;
import com.developer.smmousavi.foodino.network.AppExecutors;
import com.developer.smmousavi.foodino.network.factory.RecipeRestApiFactory;
import com.developer.smmousavi.foodino.network.reciperesponses.ApiResponse;
import com.developer.smmousavi.foodino.network.reciperesponses.RecipeSearchResponse;
import com.developer.smmousavi.foodino.network.util.NetworkBoundResource;
import com.developer.smmousavi.foodino.network.util.Resource;
import com.developer.smmousavi.foodino.presistence.recipe.RecipeDAO;
import com.developer.smmousavi.foodino.presistence.recipe.RecipeDatabase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ShoppingBasketRepository {

    private static final String TAG = "TAG";
    private static ShoppingBasketRepository sInstance;

    private RecipeDAO mRecipeDAO;


    public static ShoppingBasketRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ShoppingBasketRepository(context);
            return sInstance;
        }
        return sInstance;
    }

    private ShoppingBasketRepository(Context context) {
        mRecipeDAO = RecipeDatabase.getInstance(context).getRecipeDao();
    }

    public LiveData<Resource<List<Recipe>>> getSuggestedRecipes(final String query, final int pageNumber) {
        return new NetworkBoundResource<List<Recipe>, RecipeSearchResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull RecipeSearchResponse item) {
                if (item.getRecipes() != null) {
                    //  recipe list will be null if the api key is expired
                    Recipe[] recipes = new Recipe[item.getRecipes().size()];
                    int index = 0;
                    for (long rowId : mRecipeDAO.insertRecipes(item.getRecipes().toArray(recipes))) {
                        if (rowId == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                            // if the recipe already exists... I don't want to set the ingredients or timestamp b/c
                            // they will be erased
                            mRecipeDAO.updateRecipe(
                                recipes[index].getRecipeId(),
                                recipes[index].getTitle(),
                                recipes[index].getPublisher(),
                                recipes[index].getImageUrl(),
                                recipes[index].getSocialRank()
                            );
                        }
                        index++;
                    }
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Recipe> data) {
                // set the interval of request.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Recipe>> loadFromDb() {
                return mRecipeDAO.searchRecipes(query, pageNumber);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<RecipeSearchResponse>> createCall() {
                return RecipeRestApiFactory.create().getSuggestionRecipes(Constants.MAP_API_KEY, query, String.valueOf(pageNumber));
            }
        }.getAsLiveData();

    }

    /**
     * @HardCoded should receive from server
     * TODO: MutableLiveData should change to Observable<>
     */
    public MutableLiveData<List<Product>> getShoppingBasketList() {
        LinkedHashMap<String, String> generalSpecificationsList = new LinkedHashMap<>();
        List<Category> parentCategoryList = new ArrayList<>();
        List<Product> relatedProductList = new ArrayList<>();
        List<String> imageUrlList = new ArrayList<>();
        List<Product> shoppingBasketProductList = new ArrayList<>();
        Specifications specifications = Specifications.create(generalSpecificationsList, null, null, null);
        MutableLiveData<List<Product>> productMld = new MutableLiveData<>();
        String avatarUrl = "https://dkstatics-public.digikala.com/digikala-products/4297027.jpg?x-oss-process=image/resize,m_lfit,h_350,w_350/quality,q_60";

        /*imageUrlList.add("https://dkstatics-public.digikala.com/digikala-adservice-banners/1000005511.jpg");
        imageUrlList.add("https://dkstatics-public-2.digikala.com/digikala-adservice-banners/1000004605.jpg");
        imageUrlList.add("https://dkstatics-public-2.digikala.com/digikala-adservice-banners/1000005458.jpg");
        imageUrlList.add("https://dkstatics-public-2.digikala.com/digikala-adservice-banners/1000004605.jpg");*/

        relatedProductList.add(Product.create("0", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("1", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("2", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("3", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("4", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("5", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("6", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "فودینو", "فودینو", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));

        shoppingBasketProductList.add(Product.create("0", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, 5.0, 0, true, true, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("1", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, null, 0, true, false, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("2", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, 5.0, 0, true, true, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("3", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, null, 0, true, false, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("4", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, 18.0, 0, true, true, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("5", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, null, 0, true, false, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("6", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, 45.0, 0, true, true, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("7", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, null, 0, true, false, relatedProductList,
            0, null));
        shoppingBasketProductList.add(Product.create("8", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, 12.5, 0, true, true, relatedProductList,
            0, null));

        shoppingBasketProductList.add(Product.create("9", "Teno Tissue Pager 250pcs", "دستمال کاغذی نایلونی تنو 250 برگ",
            avatarUrl, "نرم و لیطف با کیفیت مرغوب", "گارانتی اصالت و سلامت فیزیکی کالا", "فودینو", 5,
            specifications, parentCategoryList, imageUrlList, 10000, 25.0, 0, true, true, relatedProductList,
            0, null));
        productMld.setValue(shoppingBasketProductList);
        return productMld;
    }
}
