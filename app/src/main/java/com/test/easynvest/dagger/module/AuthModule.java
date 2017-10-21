package com.test.easynvest.dagger.module;

import android.content.Context;

import com.test.easynvest.dagger.annotations.ActivityContext;
import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.view.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {

    private UserModel mUser;
    private BaseActivity mActivity;

    public AuthModule(BaseActivity activity) {
        mActivity = activity;
        mUser = new UserModel();
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    BaseActivity provideActivity() {
        return mActivity;
    }

    @Provides
    UserModel provideUserModel() {
        return mUser;
    }
}