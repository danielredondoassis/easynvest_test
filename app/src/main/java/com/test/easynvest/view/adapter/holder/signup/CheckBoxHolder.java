package com.test.easynvest.view.adapter.holder.signup;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.LinearLayout;

import com.test.easynvest.R;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.util.UnitConverter;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.adapter.holder.BaseHolder;

/**
 * Created by DT on 10/22/17.
 */

public class CheckBoxHolder extends BaseHolder {

    private FontTextView mTextCheckboxOption;
    private AppCompatCheckBox mCheckBoxRegisterEmail;
    private LinearLayout mCheckBoxLayout;

    public CheckBoxHolder(View itemView) {
        super(itemView);

        mTextCheckboxOption = itemView.findViewById(R.id.textCheckboxOption);
        mCheckBoxRegisterEmail = itemView.findViewById(R.id.checkBoxRegisterEmail);
        mCheckBoxLayout = itemView.findViewById(R.id.checkBoxLayout);
    }

    public void setup(Context context, SignUpContracts.Interactor interactor, CellModel cellModel) {

        addTopSpacing(context,cellModel.getTopSpacing());
        mTextCheckboxOption.setText(cellModel.getMessage());
        setupCheckListener(mCheckBoxRegisterEmail,interactor);
    }

    private void addTopSpacing(Context context, int spacing) {
        int topSpacing = UnitConverter.dpToPx2(spacing, context);
        mCheckBoxLayout.setPadding(0,topSpacing,0,0);
    }
}
