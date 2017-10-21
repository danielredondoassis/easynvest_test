package com.test.easynvest.view;

import android.app.Application;
import android.os.Bundle;

import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.dagger.component.AuthComponent;
import com.test.easynvest.dagger.component.DaggerAuthComponent;
import com.test.easynvest.dagger.module.AuthModule;
import com.test.easynvest.data.SharedPrefsHelper;

import javax.inject.Inject;

import rx.Subscription;


public class AuthActivity extends BaseActivity {

    @Inject
    protected SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    Application application;

    private Subscription mSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
    }

    public void inject(){
        getActivityComponent().inject(this);
    }

    private AuthComponent activityComponent;

    public AuthComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerAuthComponent.builder()
                    .authModule(new AuthModule(this))
                    .applicationComponent(EasynvestApplication.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

}
