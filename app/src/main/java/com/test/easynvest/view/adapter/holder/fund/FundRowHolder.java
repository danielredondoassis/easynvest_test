package com.test.easynvest.view.adapter.holder.fund;

import android.view.View;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.data.model.fund.RowModel;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import butterknife.BindView;

/**
 * Created by DT on 10/25/17.
 */

public class FundRowHolder extends BaseHolder {

    FontTextView mTextFundInfoLabel;
    FontTextView mTextCDValue;

    public FundRowHolder(View itemView) {
        super(itemView);
        mTextFundInfoLabel = itemView.findViewById(R.id.textFundInfoLabel);
        mTextCDValue = itemView.findViewById(R.id.textCDValue);
    }

    public void setup(RowModel rowModel){
        mTextFundInfoLabel.setText(rowModel.getName());
        mTextCDValue.setText(rowModel.getData());
    }

}
