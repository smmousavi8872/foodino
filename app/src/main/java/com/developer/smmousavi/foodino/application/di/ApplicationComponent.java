package com.developer.smmousavi.foodino.application.di;

import android.app.Application;

import com.developer.smmousavi.foodino.ui.activities.base.di.ActivityBuildersModule;
import com.developer.smmousavi.foodino.application.BaseApplication;
import com.developer.smmousavi.foodino.factory.viewmodel.di.ViewModelFactoryModule;
import com.developer.smmousavi.foodino.ui.fragments.base.di.FragmentBuildersModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
    AndroidSupportInjectionModule.class,
    BaseApplicationModule.class,
    ActivityBuildersModule.class,
    FragmentBuildersModule.class,
    ViewModelFactoryModule.class
})

public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
