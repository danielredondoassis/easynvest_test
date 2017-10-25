package com.test.easynvest.view.adapter.holder.fund;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.fund.RowModel;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DT on 10/25/17.
 */

public class FundDownInfoHolder extends BaseHolder {

    FontTextView mTextFundInfoLabel;
    ImageView mImageDownload;
    FontTextView mTextDownload;

    public FundDownInfoHolder(View itemView) {
        super(itemView);

        mTextDownload = itemView.findViewById(R.id.textDownload);
        mImageDownload = itemView.findViewById(R.id.imageDownload);
        mTextFundInfoLabel = itemView.findViewById(R.id.textFundInfoLabel);
    }

    public void setup(RowModel rowModel){
        mTextFundInfoLabel.setText(rowModel.getName());
    }
}
