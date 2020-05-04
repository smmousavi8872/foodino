package com.developer.smmousavi.foodino.appannotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.AboutUsFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.HomeFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.CategoryListFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.FinalizeShopFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.ChatBottomSheetFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.MainFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.ProductDetailFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.SettingsFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.ShoppingBasketFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.SignUpSignInFragment;
import static com.developer.smmousavi.foodino.appannotation.BaseFragmentNames.SpecialOffersFragment;

@Retention(RetentionPolicy.SOURCE)
@StringDef({
    HomeFragment,
    AboutUsFragment,
    CategoryListFragment,
    FinalizeShopFragment,
    ChatBottomSheetFragment,
    MainFragment,
    ProductDetailFragment,
    SettingsFragment,
    ShoppingBasketFragment,
    SignUpSignInFragment,
    SpecialOffersFragment
})

public @interface BaseFragmentNames {
    String HomeFragment = "HomeDrawerFragment";
    String AboutUsFragment = "AboutUsFragment";
    String CategoryListFragment = "CategoryListFragment";
    String FinalizeShopFragment = "FinalizeShopFragment";
    String ChatBottomSheetFragment = "ChatBottomSheetFragment";
    String MainFragment = "DrawerFragment";
    String ProductDetailFragment = "ProductDetailDrawerFragment";
    String SettingsFragment = "SettingsFragment";
    String ShoppingBasketFragment = "ShoppingBasketFragment";
    String SignUpSignInFragment = "SignUpSignInFragment";
    String SpecialOffersFragment = "SpecialOffersFragment";

}

