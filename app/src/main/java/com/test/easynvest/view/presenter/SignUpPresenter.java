package com.test.easynvest.view.presenter;

import android.support.v7.app.AppCompatActivity;

import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.interactor.SignUpInteractor;
import com.test.easynvest.view.router.SignUpRouter;

import java.util.ArrayList;

/**
 * Created by DT on 10/24/17.
 */

public class SignUpPresenter implements SignUpContracts.Presenter, SignUpContracts.InteractorOutput {

    private SignUpRouter router;
    private SignUpContracts.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContracts.View view){
        this.view = view;
        this.router = new SignUpRouter((AppCompatActivity) view);
        this.interactor = new SignUpInteractor(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        if(interactor != null) {
            interactor.unregister();
            interactor = null;
        }

        if(router != null) {
            router.unregister();
            router = null;
        }
    }

    public SignUpInteractor getInteractor() {
        return interactor;
    }

    @Override
    public void onRegisterButtonPressed(UserModel user) {
        if(interactor != null) interactor.registerUser(user);
    }

    @Override
    public void onLoadSuccess(ArrayList<CellModel> form) {
        if(view != null) view.setupAdapter(form);
    }

    @Override
    public void onLoadError() {
        if(view != null) view.showNoSignUpScreen();
    }

    @Override
    public void onRegisterSuccess(UserModel user) {
        if(router != null) router.presentFundScreen(user);
    }

    @Override
    public void onRegisterError(String message) {
        if(view != null) view.showError(message);

    }
}
