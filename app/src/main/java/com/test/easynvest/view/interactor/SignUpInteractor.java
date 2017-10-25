package com.test.easynvest.view.interactor;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.R;
import com.test.easynvest.data.event.ShowFieldErrorEvent;
import com.test.easynvest.data.event.ShowFieldSuccessEvent;
import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;
import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.data.response.FormResponse;
import com.test.easynvest.util.Validator;
import com.test.easynvest.view.contract.SignUpContracts;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DT on 10/24/17.
 */

public class SignUpInteractor implements SignUpContracts.Interactor {

    private Context context;
    private UserModel mUserModel;
    private SignUpContracts.InteractorOutput output;

    public SignUpInteractor(SignUpContracts.InteractorOutput output){
        this.output = output;
        this.mUserModel = new UserModel();
        this.context = EasynvestApplication.getStaticContext();
        loadSignUpForm();
    }

    public void unregister() {
        output = null;
    }

    public void loadSignUpForm() {
        TaskGetSignUpForm task = new TaskGetSignUpForm();
        task.execute();
    }

    @Override
    public void registerUserInfo(Type type, TypeField typeField, String info) {

        switch (typeField) {
            case EMAIL:
                if ((info.equals("") || !info.equals("") && !Validator.validateEmail(info))  && mUserModel.validateEmail()) {
                    mUserModel.setEmail(null);
                    EventBus.getDefault().post(new ShowFieldErrorEvent(type, typeField, context.getResources().getString(R.string.error_invalid_email)));
                } else {
                    mUserModel.setEmail(info);
                    EventBus.getDefault().post(new ShowFieldSuccessEvent(type, typeField));
                }
                break;
            case PHONE:
                if (info.equals("") || !info.equals("") && !Validator.validatePhone(info)) {
                    mUserModel.setPhone(null);
                    EventBus.getDefault().post(new ShowFieldErrorEvent(type, typeField, context.getResources().getString(R.string.error_invalid_phone)));
                } else {
                    mUserModel.setPhone(info);
                    EventBus.getDefault().post(new ShowFieldSuccessEvent(type, typeField));
                }
                break;
            case TEXT:
                if (info.equals("")){
                    mUserModel.setName(null);
                    EventBus.getDefault().post(new ShowFieldErrorEvent(type, typeField, context.getResources().getString(R.string.error_name_needed)));
                } else {
                    mUserModel.setName(info);
                    EventBus.getDefault().post(new ShowFieldSuccessEvent(type, typeField));
                }
                break;
        }

        if(mUserModel.isValid()) registerUser(mUserModel);
    }

    @Override
    public void validateUserPhone(boolean validate) {
        mUserModel.setValidateEmail(validate);
    }

    @Override
    public void registerUser(UserModel user) {
        output.onRegisterSuccess(user);
    }


    public class TaskGetSignUpForm extends AsyncTask<String, Void, FormResponse> {

        @Override
        protected FormResponse doInBackground(String... arg0) {
            try {

                Context context = EasynvestApplication.getStaticContext();
                InputStream is = context.getAssets().open(context.getResources().getString(R.string.signup_json));

                Gson gson = new GsonBuilder().create();
                Reader reader = new InputStreamReader(is);

                FormResponse formCells = gson.fromJson(reader,FormResponse.class);

                return formCells;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(FormResponse result) {
            if(result != null) {
                CellModel[] cells = result.getCells();
                if(cells!= null && cells.length > 0) {
                    ArrayList<CellModel> form = new ArrayList<>(Arrays.asList(cells));
                    output.onLoadSuccess(form);
                } else {
                    output.onLoadError();
                }
            }
        }
    }
}

