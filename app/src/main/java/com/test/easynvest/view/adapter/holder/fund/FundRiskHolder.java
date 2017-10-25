package com.test.easynvest.view.adapter.holder.fund;

import android.view.View;
import android.widget.ImageView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.FundModel;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import butterknife.BindView;

/**
 * Created by DT on 10/25/17.
 */

public class FundRiskHolder extends BaseHolder {

    ImageView mImageArrowRisk1;
    ImageView mImageArrowRisk2;
    ImageView mImageArrowRisk3;
    ImageView mImageArrowRisk4;
    ImageView mImageArrowRisk5;

    public FundRiskHolder(View itemView) {
        super(itemView);

        mImageArrowRisk1 = itemView.findViewById(R.id.imageArrowRisk1);
        mImageArrowRisk2 = itemView.findViewById(R.id.imageArrowRisk2);
        mImageArrowRisk3 = itemView.findViewById(R.id.imageArrowRisk3);
        mImageArrowRisk4 = itemView.findViewById(R.id.imageArrowRisk4);
        mImageArrowRisk5 = itemView.findViewById(R.id.imageArrowRisk5);

    }

    public void setup(int risk){

        mImageArrowRisk1.setVisibility(View.INVISIBLE);
        mImageArrowRisk2.setVisibility(View.INVISIBLE);
        mImageArrowRisk3.setVisibility(View.INVISIBLE);
        mImageArrowRisk4.setVisibility(View.INVISIBLE);
        mImageArrowRisk5.setVisibility(View.INVISIBLE);

        switch (risk){
            case 1:
                mImageArrowRisk1.setVisibility(View.VISIBLE);
                break;
            case 2:
                mImageArrowRisk2.setVisibility(View.VISIBLE);
                break;
            case 3:
                mImageArrowRisk3.setVisibility(View.VISIBLE);
                break;
            case 4:
                mImageArrowRisk4.setVisibility(View.VISIBLE);
                break;
            case 5:
                mImageArrowRisk5.setVisibility(View.VISIBLE);
                break;
        }

    }

}
