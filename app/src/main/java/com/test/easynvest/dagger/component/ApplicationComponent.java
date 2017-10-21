package com.test.easynvest.dagger.component;

import android.app.Application;
import android.content.Context;

import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.dagger.annotations.ApplicationContext;
import com.test.easynvest.dagger.module.ApplicationModule;
import com.test.easynvest.data.SharedPrefsHelper;
import com.test.easynvest.data.manager.UserManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(EasynvestApplication estudaJuntoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    SharedPrefsHelper getPreferenceHelper();

    UserManager getUserManager();

}