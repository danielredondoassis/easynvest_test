package com.test.easynvest.view.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.view.FundActivity;
import com.test.easynvest.view.contract.SignUpContracts;

/**
 * Created by DT on 10/24/17.
 */

public class SignUpRouter implements SignUpContracts.Router {

    private AppCompatActivity activity;

    public SignUpRouter(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void unregister() {
        activity = null;
    }

    @Override
    public void presentFundScreen(UserModel user) {

        Intent intent = new Intent(activity, FundActivity.class);
        //intent.putExtra(Constants.IntentExtras.USER, user)
        if(activity != null) activity.startActivity(intent);
    }
}
