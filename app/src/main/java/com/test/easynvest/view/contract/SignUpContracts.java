package com.test.easynvest.view.contract;

import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;
import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.data.model.form.CellModel;

import java.util.ArrayList;

/**
 * Created by DT on 10/23/17.
 */

public interface SignUpContracts {
    interface View {
        void showError(String message);
        void showNoSignUpScreen();
        void setupAdapter(ArrayList<CellModel> form);
    }

    interface Presenter {
        void onDestroy();
        void onRegisterButtonPressed(UserModel model);
        Interactor getInteractor();
    }

    interface Interactor {
        void unregister();
        void loadSignUpForm();
        void registerUser(UserModel user);
        void registerUserInfo(Type type, TypeField typeField, String info);

        void validateUserPhone(boolean validate);
    }

    interface InteractorOutput {
        void onLoadSuccess(ArrayList<CellModel> form);
        void onLoadError();
        void onRegisterSuccess(UserModel model);
        void onRegisterError(String message);
    }

    interface Router {
        void unregister();
        void presentFundScreen(UserModel user);
    }
}
