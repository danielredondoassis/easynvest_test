package com.test.easynvest.dagger.component;

import com.test.easynvest.dagger.annotations.PerActivity;
import com.test.easynvest.dagger.module.AuthModule;
import com.test.easynvest.view.FundActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AuthModule.class)
public interface AuthComponent {

    void inject(FundActivity activity);

}