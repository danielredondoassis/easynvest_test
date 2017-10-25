package com.test.easynvest.view.presenter;

import android.support.v7.app.AppCompatActivity;

import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.view.contract.FundContracts;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.interactor.FundInteractor;
import com.test.easynvest.view.interactor.SignUpInteractor;
import com.test.easynvest.view.router.FundRouter;
import com.test.easynvest.view.router.SignUpRouter;

import java.util.ArrayList;

/**
 * Created by DT on 10/24/17.
 */

public class FundPresenter implements FundContracts.Presenter, FundContracts.InteractorOutput {

    private FundRouter router;
    private FundContracts.View view;
    private FundInteractor interactor;

    public FundPresenter(FundContracts.View view){
        this.view = view;
        this.router = new FundRouter((AppCompatActivity) view);
        this.interactor = new FundInteractor(this);
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

    @Override
    public void onFundButtonPressed(UserModel model) {

    }

    @Override
    public void onContactButtonPressed() {

    }

    public FundInteractor getInteractor() {
        return interactor;
    }

    @Override
    public void onLoadSuccess(FundModel fundInfo) {
        if(view != null) view.setupAdapter(fundInfo);
    }

    @Override
    public void onLoadError() {
        if(view != null) view.showNoFundScreen();
    }

    @Override
    public void onSendMessageSuccess() {

    }

    @Override
    public void onSendMessageError(String message) {

    }
}
