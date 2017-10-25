package com.test.easynvest.view.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.view.FundActivity;
import com.test.easynvest.view.contract.FundContracts;
import com.test.easynvest.view.contract.SignUpContracts;

/**
 * Created by DT on 10/24/17.
 */

public class FundRouter implements FundContracts.Router {

    private AppCompatActivity activity;

    public FundRouter(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void unregister() {
        activity = null;
    }

    @Override
    public void showFundFragment() {

    }

    @Override
    public void showContactFragment() {

    }

}
