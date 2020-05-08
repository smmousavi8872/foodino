package com.developer.smmousavi.foodino.ui.activities.base.di;

import com.developer.smmousavi.foodino.ui.activities.aboutus.AboutUsActivity;
import com.developer.smmousavi.foodino.ui.activities.aboutus.di.AboutUsActivityModule;
import com.developer.smmousavi.foodino.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmousavi.foodino.ui.activities.categorylist.CategoryListActivity;
import com.developer.smmousavi.foodino.ui.activities.categorylist.di.CategoryListActivityModule;
import com.developer.smmousavi.foodino.ui.activities.drawer.BaseDrawerActivity;
import com.developer.smmousavi.foodino.ui.activities.drawer.di.BaseDrawerActivityModule;
import com.developer.smmousavi.foodino.ui.activities.home.HomeDrawerActivity;
import com.developer.smmousavi.foodino.ui.activities.home.di.HomeDrawerActivityModule;
import com.developer.smmousavi.foodino.ui.activities.prodcutdetail.ProductDetailDrawerActivity;
import com.developer.smmousavi.foodino.ui.activities.prodcutdetail.di.ProductDetailDrawerActivityModule;
import com.developer.smmousavi.foodino.ui.activities.finilizeshop.FinalizeShopActivity;
import com.developer.smmousavi.foodino.ui.activities.finilizeshop.di.FinalizeShopActivityModule;
import com.developer.smmousavi.foodino.ui.activities.map.MapActivity;
import com.developer.smmousavi.foodino.ui.activities.map.di.MapActivityModule;
import com.developer.smmousavi.foodino.ui.activities.settings.SettingsActivity;
import com.developer.smmousavi.foodino.ui.activities.settings.di.SettingsActivityModule;
import com.developer.smmousavi.foodino.ui.activities.shoppingbasket.ShoppingBasketActivity;
import com.developer.smmousavi.foodino.ui.activities.shoppingbasket.di.ShoppingBasketActivityModule;
import com.developer.smmousavi.foodino.ui.activities.signinsignup.SignInSignUpActivity;
import com.developer.smmousavi.foodino.ui.activities.signinsignup.di.SignInSignUpActivityModule;
import com.developer.smmousavi.foodino.ui.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.ui.activities.singlefragment.di.SingleFragmentActivityModule;
import com.developer.smmousavi.foodino.ui.activities.specialoffers.SpecialOffersActivity;
import com.developer.smmousavi.foodino.ui.activities.specialoffers.di.SpecialOffersActivityModule;

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