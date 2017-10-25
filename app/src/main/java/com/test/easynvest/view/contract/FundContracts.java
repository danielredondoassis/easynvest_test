package com.test.easynvest.view.contract;

import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;
import com.test.easynvest.data.model.auth.UserModel;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.data.model.fund.FundModel;

import java.util.ArrayList;

/**
 * Created by DT on 10/23/17.
 */

public interface FundContracts {
    interface View {
        void showError(String message);
        void showNoFundScreen();
        void setupAdapter(FundModel fundInfo);
    }

    interface Presenter {
        void onDestroy();
        void onFundButtonPressed(UserModel model);
        void onContactButtonPressed();
    }

    interface Interactor {
        void unregister();
        void loadFundInfo();
        void sendContactMessage();
    }

    interface InteractorOutput {
        void onLoadSuccess(FundModel fundInfo);
        void onLoadError();
        void onSendMessageSuccess();
        void onSendMessageError(String message);
    }

    interface Router {
        void unregister();
        void showFundFragment();
        void showContactFragment();

    }
}
