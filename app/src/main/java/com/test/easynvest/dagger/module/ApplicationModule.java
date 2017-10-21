package com.test.easynvest.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.test.easynvest.dagger.annotations.ApplicationContext;
import com.test.easynvest.data.SharedPrefsHelper;
import com.test.easynvest.data.manager.UserManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;
    private final UserManager mUserManager;
    private final SharedPrefsHelper mSharedPreferences;

    public ApplicationModule(Application app) {
        mApplication = app;
        mSharedPreferences = new SharedPrefsHelper(provideSharedPrefs());
        mUserManager = new UserManager(mApplication,mSharedPreferences);
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    public UserManager provideUserManager() {
        return mUserManager;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("ecovias-prefs", Context.MODE_PRIVATE);
    }

}