package com.developer.smmousavi.foodino.activities.base.di;

import com.developer.smmousavi.foodino.activities.aboutus.AboutUsActivity;
import com.developer.smmousavi.foodino.activities.aboutus.di.AboutUsActivityModule;
import com.developer.smmousavi.foodino.activities.base.BaseDaggerCompatActivity;
import com.developer.smmousavi.foodino.activities.categorylist.CategoryListActivity;
import com.developer.smmousavi.foodino.activities.categorylist.di.CategoryListActivityModule;
import com.developer.smmousavi.foodino.activities.drawer.BaseDrawerActivity;
import com.developer.smmousavi.foodino.activities.drawer.di.BaseDrawerActivityModule;
import com.developer.smmousavi.foodino.activities.home.HomeDrawerActivity;
import com.developer.smmousavi.foodino.activities.home.di.HomeDrawerActivityModule;
import com.developer.smmousavi.foodino.activities.prodcutdetail.ProductDetailDrawerActivity;
import com.developer.smmousavi.foodino.activities.prodcutdetail.di.ProductDetailDrawerActivityModule;
import com.developer.smmousavi.foodino.activities.finilizeshop.FinalizeShopActivity;
import com.developer.smmousavi.foodino.activities.finilizeshop.di.FinalizeShopActivityModule;
import com.developer.smmousavi.foodino.activities.map.MapActivity;
import com.developer.smmousavi.foodino.activities.map.di.MapActivityModule;
import com.developer.smmousavi.foodino.activities.settings.SettingsActivity;
import com.developer.smmousavi.foodino.activities.settings.di.SettingsActivityModule;
import com.developer.smmousavi.foodino.activities.shoppingbasket.ShoppingBasketActivity;
import com.developer.smmousavi.foodino.activities.shoppingbasket.di.ShoppingBasketActivityModule;
import com.developer.smmousavi.foodino.activities.signinsignup.SignInSignUpActivity;
import com.developer.smmousavi.foodino.activities.signinsignup.di.SignInSignUpActivityModule;
import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.activities.singlefragment.di.SingleFragmentActivityModule;
import com.developer.smmousavi.foodino.activities.specialoffers.SpecialOffersActivity;
import com.developer.smmousavi.foodino.activities.specialoffers.di.SpecialOffersActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {BaseDaggerAppCompatActivityModule.class})
    abstract BaseDaggerCompatActivity contributeBaseDaggerAppCompatActivity();

    @ContributesAndroidInjector(modules = {AboutUsActivityModule.class})
    abstract AboutUsActivity contributeAboutUsActivity();

    @ContributesAndroidInjector(modules = {CategoryListActivityModule.class})
    abstract CategoryListActivity contriuteCategoryListActivity();

    @ContributesAndroidInjector(modules = {MapActivityModule.class})
    abstract MapActivity contributeFrequentQuestionsActivity();

    @ContributesAndroidInjector(modules = {BaseDrawerActivityModule.class})
    abstract BaseDrawerActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {HomeDrawerActivityModule.class})
    abstract HomeDrawerActivity contributeHomeDrawerActivity();

    @ContributesAndroidInjector(modules = {ProductDetailDrawerActivityModule.class})
    abstract ProductDetailDrawerActivity contributeProductDetailDrawerActivity();

    @ContributesAndroidInjector(modules = {SettingsActivityModule.class})
    abstract SettingsActivity contributeSettingsActivity();

    @ContributesAndroidInjector(modules = {ShoppingBasketActivityModule.class})
    abstract ShoppingBasketActivity contributeShoppingBasketActivity();

    @ContributesAndroidInjector(modules = {SingleFragmentActivityModule.class})
    abstract SingleFragmentActivity contributeSingleFragmentActivity();

    @ContributesAndroidInjector(modules = {SpecialOffersActivityModule.class})
    abstract SpecialOffersActivity contributeSpecialActivity();

    @ContributesAndroidInjector(modules = {FinalizeShopActivityModule.class})
    abstract FinalizeShopActivity contributeFinalizeShopActivity();

    @ContributesAndroidInjector(modules = {SignInSignUpActivityModule.class})
    abstract SignInSignUpActivity contributeSginInActivity();

}