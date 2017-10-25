package com.test.easynvest.view.adapter.holder.signup;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.easynvest.R;
import com.test.easynvest.data.event.GetFieldInfoEvent;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.util.UnitConverter;
import com.test.easynvest.util.typeface.FontButton;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by DT on 10/22/17.
 */

public class ButtonHolder extends BaseHolder {

    private FontButton mBtnSend;
    private SignUpContracts.Interactor mInteractor;

    public ButtonHolder(View itemView) {
        super(itemView);

        mBtnSend = itemView.findViewById(R.id.btnSend);
    }

    public void setup(Context context, SignUpContracts.Interactor interactor, CellModel cellModel) {
        this.mInteractor = interactor;
        addTopSpacing(context,cellModel.getTopSpacing());
        mBtnSend.setText(cellModel.getMessage());

        mBtnSend.setOnClickListener(v -> {
            EventBus.getDefault().post(new GetFieldInfoEvent());
        });
    }

    private void addTopSpacing(Context context, int spacing) {
        int topSpacing = UnitConverter.dpToPx2(spacing, context);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBtnSend.getLayoutParams();
        params.topMargin = topSpacing;
        mBtnSend.setLayoutParams(params);
    }
}
