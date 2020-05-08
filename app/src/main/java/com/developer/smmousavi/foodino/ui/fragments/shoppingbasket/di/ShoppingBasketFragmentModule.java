package com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.di;

import android.app.Application;

import com.developer.smmousavi.foodino.adapters.shoppingbasket.ShoppingBasketRvAdapter;
import com.developer.smmousavi.foodino.adapters.shoppingbasket.ShoppingBasketSuggestionsRvAdapter;
import com.developer.smmousavi.foodino.helper.RecyclerViewHelper;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.repositories.ShoppingBasketRepository;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingBasketFragmentModule {

    @Provides
    public List<Product> provideProductList(Application app) {
        return ShoppingBasketRepository.getInstance(app).getShoppingBasketList().getValue();
    }

    @Provides
    public ShoppingBasketRvAdapter provideShoppingBasketRvAdapter() {
        return new ShoppingBasketRvAdapter();
    }

    @Provides
    public ShoppingBasketSuggestionsRvAdapter provideProductSuggestionsRvAdapter() {
        return new ShoppingBasketSuggestionsRvAdapter();
    }

    @Provides
    public RecyclerViewHelper provideRecyclerViewHelper() {
        return new RecyclerViewHelper();
    }
}
