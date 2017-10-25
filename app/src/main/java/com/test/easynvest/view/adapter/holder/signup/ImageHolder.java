package com.test.easynvest.view.adapter.holder.signup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.test.easynvest.R;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.util.UnitConverter;
import com.test.easynvest.view.adapter.holder.BaseHolder;

/**
 * Created by DT on 10/22/17.
 */

public class ImageHolder extends BaseHolder {

    private ImageView mImageKitten;

    public ImageHolder(View itemView) {
        super(itemView);

        mImageKitten = itemView.findViewById(R.id.imageKitten);
    }

    public void setup(Context context, CellModel cellModel) {
        addTopSpacing(context, cellModel.getTopSpacing());
        displayImage(cellModel.getMessage(), mImageKitten);
    }

    private void addTopSpacing(Context context, int spacing) {
        int topSpacing = UnitConverter.dpToPx2(spacing, context);
        mImageKitten.setPadding(0,topSpacing,0,0);
    }
}
