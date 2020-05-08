package com.developer.smmousavi.foodino.ui.fragments.base.di;


import com.developer.smmousavi.foodino.ui.fragments.aboutus.AboutUsFragment;
import com.developer.smmousavi.foodino.ui.fragments.aboutus.di.AboutUsFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.CategoryListFragment;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.CategoryListTabLayoutFragment;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.di.CategoryListFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.di.CategoryListFragmentViewModelModule;
import com.developer.smmousavi.foodino.ui.fragments.categorylist.di.CategoryListTabLayoutFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.chatbottomsheet.ChatBottomSheetFargment;
import com.developer.smmousavi.foodino.ui.fragments.finalizeshop.FinalizeShopFragment;
import com.developer.smmousavi.foodino.ui.fragments.finalizeshop.di.FinalizeShopFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.finalizeshop.di.FinalizeShopFragmentViewModelModule;
import com.developer.smmousavi.foodino.ui.fragments.home.HomeDrawerFragment;
import com.developer.smmousavi.foodino.ui.fragments.home.di.HomeDrawerFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.home.di.HomeFragmentViewModelModule;
import com.developer.smmousavi.foodino.ui.fragments.map.MapFragment;
import com.developer.smmousavi.foodino.ui.fragments.map.di.MapFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.map.di.MapFragmentViewModelModule;
import com.developer.smmousavi.foodino.ui.fragments.mapsearch.MapSearchFragment;
import com.developer.smmousavi.foodino.ui.fragments.mapsearch.di.MapSearchFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.mapsearch.di.MapSearchFragmentVMModule;
import com.developer.smmousavi.foodino.ui.fragments.productdetail.ProductDetailFragment;
import com.developer.smmousavi.foodino.ui.fragments.productdetail.di.ProductDetailDrawerFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.productdetail.di.ProductDetailFragmentVMModule;
import com.developer.smmousavi.foodino.ui.fragments.settings.SettingsFragment;
import com.developer.smmousavi.foodino.ui.fragments.settings.di.SettingsFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.ShoppingBasketFragment;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.di.ShoppingBasketFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.shoppingbasket.di.ShoppingBasketFragmentVMModule;
import com.developer.smmousavi.foodino.ui.fragments.signinsignup.SignInFragment;
import com.developer.smmousavi.foodino.ui.fragments.signinsignup.SignUpFragment;
import com.developer.smmousavi.foodino.ui.fragments.signinsignup.di.SignInFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.signinsignup.di.SignUpFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.specialoffers.SpecialOffersFragment;
import com.developer.smmousavi.foodino.ui.fragments.specialoffers.di.SpecialOfferFragmentModule;
import com.developer.smmousavi.foodino.ui.fragments.specialoffers.di.SpecialOffersFragmentVMModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = {BaseDaggerFragmentModule.class})
    abstract BaseDaggerFragment contributeBaseDaggerFragment();

    @ContributesAndroidInjector(modules = {ShoppingBasketFragmentModule.class, ShoppingBasketFragmentVMModule.class})
    abstract ShoppingBasketFragment contributeShoppingBasketFragment();

    @ContributesAndroidInjector(modules = {CategoryListFragmentModule.class, CategoryListFragmentViewModelModule.class})
    abstract CategoryListFragment contributeCategoryListFragment();

    @ContributesAndroidInjector(modules = {CategoryListTabLayoutFragmentModule.class})
    abstract CategoryListTabLayoutFragment contributeCategoryListTabContainer();

    @ContributesAndroidInjector(modules = {AboutUsFragmentModule.class})
    abstract AboutUsFragment contributesAboutUsFragment();

    @ContributesAndroidInjector(modules = {MapFragmentModule.class, MapFragmentViewModelModule.class})
    abstract MapFragment contributeMapFragment();

    @ContributesAndroidInjector(modules = {MapSearchFragmentModule.class, MapSearchFragmentVMModule.class})
    abstract MapSearchFragment contributeSearchMapFragment();

    @ContributesAndroidInjector(modules = {HomeDrawerFragmentModule.class, HomeFragmentViewModelModule.class})
    abstract HomeDrawerFragment contributeHomeFragment();

    @ContributesAndroidInjector(modules = {SettingsFragmentModule.class})
    abstract SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector(modules = {SpecialOfferFragmentModule.class, SpecialOffersFragmentVMModule.class})
    abstract SpecialOffersFragment contributeSpecialOffersFragment();

    @ContributesAndroidInjector(modules = {FinalizeShopFragmentModule.class, FinalizeShopFragmentViewModelModule.class})
    abstract FinalizeShopFragment contributeFinalizeShoppingFragment();

    @ContributesAndroidInjector(modules = {SignInFragmentModule.class})
    abstract SignInFragment contributeSignInFragment();

    @ContributesAndroidInjector(modules = {SignUpFragmentModule.class})
    abstract SignUpFragment contributeSignUpFragment();

    @ContributesAndroidInjector(modules = {ProductDetailDrawerFragmentModule.class, ProductDetailFragmentVMModule.class})
    abstract ProductDetailFragment contributeProductDetailFragment();

    @ContributesAndroidInjector()
    abstract ChatBottomSheetFargment contributeChatBottomSheetFragment();
}
