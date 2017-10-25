package com.test.easynvest.util.typeface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.test.easynvest.R;

import timber.log.Timber;

public class FontButton extends Button {

    private static final String TAG = FontButton.class.getSimpleName();

    public FontButton(Context context) {
        super(context);
    }

    public FontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public FontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.FontButton);
        int code = a.getInt(R.styleable.FontButton_customFont, 0);
        setCustomFont(ctx, code);
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
            Timber.e("Could not get typeface: %s", e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }
}
