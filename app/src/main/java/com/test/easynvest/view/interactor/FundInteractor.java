package com.test.easynvest.view.interactor;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.R;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.data.response.FormResponse;
import com.test.easynvest.data.response.FundResponse;
import com.test.easynvest.view.contract.FundContracts;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DT on 10/24/17.
 */

public class FundInteractor implements FundContracts.Interactor {

    private FundContracts.InteractorOutput output;

    public FundInteractor(FundContracts.InteractorOutput output){
        this.output = output;
        loadFundInfo();
    }

    public void unregister() {
        output = null;
    }

    @Override
    public void sendContactMessage() {

    }

    @Override
    public void loadFundInfo() {
        TaskGetFundInfo task = new TaskGetFundInfo();
        task.execute();
    }

    public class TaskGetFundInfo extends AsyncTask<String, Void, FundResponse> {

        @Override
        protected FundResponse doInBackground(String... arg0) {
            try {

                Context context = EasynvestApplication.getStaticContext();
                InputStream is = context.getAssets().open(context.getResources().getString(R.string.fund_json));

                Gson gson = new GsonBuilder().create();
                Reader reader = new InputStreamReader(is);

                FundResponse fundInfo = gson.fromJson(reader,FundResponse.class);

                return fundInfo;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(FundResponse result) {
                if(result.getFundModel() != null) {
                    output.onLoadSuccess(result.getFundModel());
                } else {
                    output.onLoadError();
                }
        }
    }
}

