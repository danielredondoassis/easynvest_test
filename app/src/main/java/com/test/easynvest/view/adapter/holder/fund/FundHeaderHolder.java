package com.test.easynvest.view.adapter.holder.fund;

import android.view.View;
import android.widget.ImageView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.data.model.fund.RowModel;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import butterknife.BindView;

/**
 * Created by DT on 10/25/17.
 */

public class FundHeaderHolder extends BaseHolder {

    FontTextView mTextTitle;
    FontTextView mTextFundName;
    FontTextView mTextWhatIs;
    FontTextView mTextDefinition;

    public FundHeaderHolder(View itemView) {
        super(itemView);

        mTextTitle = itemView.findViewById(R.id.textTitle);
        mTextFundName = itemView.findViewById(R.id.textFundName);
        mTextWhatIs = itemView.findViewById(R.id.textWhatIs);
        mTextDefinition = itemView.findViewById(R.id.textDefinition);
    }

    public void setup(FundModel fundModel){

        mTextDefinition.setText(fundModel.getDefinition());
        mTextWhatIs.setText(fundModel.getWhatIs());
        mTextFundName.setText(fundModel.getFundName());
        mTextTitle.setText(fundModel.getTitle());

    }



}
