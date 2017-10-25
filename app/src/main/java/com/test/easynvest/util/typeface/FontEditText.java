package com.test.easynvest.util.typeface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.test.easynvest.R;

import timber.log.Timber;


public class FontEditText extends MaskedEditText {

    private static final String TAG = FontTextView.class.getSimpleName();

    public FontEditText(Context context) {
        super(context);
    }

    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public FontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.FontEditText);
        int code = a.getInt(R.styleable.FontEditText_customFont, 0);
        setCustomFont(ctx, code);

        String mask = a.getString(R.styleable.CustomInputText_edit_mask);
        boolean isPhone = a.getBoolean(R.styleable.CustomInputText_edit_is_phone, false);

        this.isPhone(isPhone);
        if (mask != null && !mask.isEmpty()) this.setMask(mask);

        a.recycle();
    }

    public boolean setCustomFont(Context ctx, int code) {
        Typeface tf = null;
        try {

            Typefaces.Font font = EasynvestFont.fontByCode(code);
            if (font != null) {
                tf = Typefaces.get(ctx.getAssets(), font);
            }
        } catch (Exception e) {
            Timber.e("Could not get typeface: %s",e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }

}
