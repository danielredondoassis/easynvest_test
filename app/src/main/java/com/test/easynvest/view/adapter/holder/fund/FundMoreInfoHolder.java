package com.test.easynvest.view.adapter.holder.fund;

import android.view.View;
import android.widget.ImageView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.data.model.fund.MoreInfoModel;
import com.test.easynvest.data.model.fund.PeriodInfoModel;
import com.test.easynvest.data.model.fund.RowModel;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import butterknife.BindView;

/**
 * Created by DT on 10/25/17.
 */

public class FundMoreInfoHolder extends BaseHolder {

    @BindView(R.id.textMoreInfoTitle)
    FontTextView mTextMoreInfoTitle;
    @BindView(R.id.textFundLabel)
    FontTextView mTextFundLabel;
    @BindView(R.id.textCDLabel)
    FontTextView mTextCDLabel;
    @BindView(R.id.textFundInfoMonthLabel)
    FontTextView mTextFundInfoMonthLabel;
    @BindView(R.id.textFundMonthValue)
    FontTextView mTextFundMonthValue;
    @BindView(R.id.textCDMonthValue)
    FontTextView mTextCDMonthValue;
    @BindView(R.id.textFundInfoYearLabel)
    FontTextView mTextFundInfoYearLabel;
    @BindView(R.id.textFundYearValue)
    FontTextView mTextFundYearValue;
    @BindView(R.id.textCDYearValue)
    FontTextView mTextCDYearValue;
    @BindView(R.id.textFundInfo12MonthLabel)
    FontTextView mTextFundInfo12MonthLabel;
    @BindView(R.id.textFund12MonthValue)
    FontTextView mTextFund12MonthValue;
    @BindView(R.id.textCD12MonthValue)
    FontTextView mTextCD12MonthValue;

    public FundMoreInfoHolder(View itemView) {
        super(itemView);
        mTextCD12MonthValue = itemView.findViewById(R.id.textCD12MonthValue);
        mTextFund12MonthValue = itemView.findViewById(R.id.textFund12MonthValue);
        mTextCDYearValue = itemView.findViewById(R.id.textCDYearValue);
        mTextFundYearValue = itemView.findViewById(R.id.textFundYearValue);
        mTextCDMonthValue = itemView.findViewById(R.id.textFundMonthValue);
        mTextFundMonthValue = itemView.findViewById(R.id.textCD12MonthValue);
    }

    public void setup(MoreInfoModel moreInfoModel){

        PeriodInfoModel yearModel = moreInfoModel.getYear();
        PeriodInfoModel monthModel = moreInfoModel.getMonth();
        PeriodInfoModel twelveMonthsModel = moreInfoModel.getTwelveMonths();

        if(twelveMonthsModel != null){
            mTextFund12MonthValue.setText(twelveMonthsModel.getFundValue() + " %");
            mTextCD12MonthValue.setText(twelveMonthsModel.getCDIValue() + " %");
        }

        if(yearModel != null){
            mTextFundYearValue.setText(yearModel.getFundValue() + " %");
            mTextCDYearValue.setText(yearModel.getCDIValue() + " %");
        }

        if(yearModel != null){
            mTextCDMonthValue.setText(monthModel.getFundValue() + " %");
            mTextFundMonthValue.setText(monthModel.getCDIValue() + " %");
        }
    }
}
