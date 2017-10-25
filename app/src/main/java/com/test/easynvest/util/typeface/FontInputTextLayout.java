package com.test.easynvest.util.typeface;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.test.easynvest.R;

import java.lang.reflect.Field;

import timber.log.Timber;

/**
 * Created by DT on 10/3/17.
 */

public class FontInputTextLayout extends TextInputLayout {

        public FontInputTextLayout(Context context) {
            super(context);
        }

        public FontInputTextLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            setCustomFont(context,attrs);
        }

    public FontInputTextLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.FontInputTextLayout);
        int code = a.getInt(R.styleable.FontInputTextLayout_customFont, 0);
        setCustomFont(ctx, code);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, int code) {

        Typeface typeface = null;
        try {

            Typefaces.Font font = EasynvestFont.fontByCode(code);
            if (font != null) {
                typeface = Typefaces.get(ctx.getAssets(), font);
            }

            EditText editText = getEditText();
            if (editText != null) {
                editText.setTypeface(typeface);
            }

            // Retrieve the CollapsingTextHelper Field
            final Field cthf = TextInputLayout.class.getDeclaredField("mCollapsingTextHelper");
            cthf.setAccessible(true);

            // Retrieve an instance of CollapsingTextHelper and its TextPaint
            final Object cth = cthf.get(this);
            final Field tpf = cth.getClass().getDeclaredField("mTextPaint");
            tpf.setAccessible(true);

            // Apply your Typeface to the CollapsingTextHelper TextPaint
            ((TextPaint) tpf.get(cth)).setTypeface(typeface);

        } catch (Exception e) {
            Timber.e("Could not get typeface: %s", e.getMessage());
            return false;
        }

        return true;
    }
}
