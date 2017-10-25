package com.test.easynvest.view.adapter.holder.signup;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.easynvest.R;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.util.UnitConverter;
import com.test.easynvest.util.typeface.FontTextView;
import com.test.easynvest.view.adapter.holder.BaseHolder;

/**
 * Created by DT on 10/22/17.
 */

public class TextHolder extends BaseHolder {

    private FontTextView mTextHeader;

    public TextHolder(View itemView) {
        super(itemView);

       mTextHeader = itemView.findViewById(R.id.textHeader);
    }

    public void setup(Context context, CellModel cellModel) {
        addTopSpacing(context, cellModel.getTopSpacing());
        mTextHeader.setText(cellModel.getMessage());
    }

    private void addTopSpacing(Context context, int spacing) {
        int topSpacing = UnitConverter.dpToPx2(spacing, context);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTextHeader.getLayoutParams();
        params.topMargin = topSpacing;
        mTextHeader.setLayoutParams(params);
    }
}
