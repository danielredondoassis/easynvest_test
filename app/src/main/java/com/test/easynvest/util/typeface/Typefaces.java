package com.test.easynvest.util.typeface;

/**
 * Created by Rogerio Shimizu on 9/10/13.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

public class Typefaces {

    private static final String TAG = Typefaces.class.getSimpleName();

    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public interface Font {
        String getName();
        int getCode();
    }

    public interface SimpleFont {
        String getName();
    }

    public static Typeface get(AssetManager assets, Font font) {
        synchronized (cache) {
            if (!cache.containsKey(font.getName())) {
                try {
                    Typeface t = Typeface.createFromAsset(assets, "fonts/" + font.getName());
                    cache.put(font.getName(), t);
                } catch (Exception e) {
                    Log.e(TAG, "Typeface error '" + font.getName()
                            + "' : " + e.getMessage());
                    return null;
                }
            }
            return cache.get(font.getName());
        }
    }

    public static void setTextFont(AssetManager assets, ArrayList<TextView> views, Font font){
        Typeface tf =  get(assets, font);
        if(tf != null) {
        for(int i = 0; i < views.size(); i++){
            if(views.get(i)==null) return;
            views.get(i).setTypeface(tf);
        }
        }
    }

    public static void setTextFont(AssetManager assets, TextInputLayout text, Font font) {
        if(text==null) return;
        Typeface tf =  get(assets, font);
        if(tf != null) {
            text.setTypeface(tf);
        }
    }

    public static void setTextFont(AssetManager assets, TextView text, Font font) {
        if(text==null) return;
        Typeface tf =  get(assets, font);
        if(tf != null) {
            text.setTypeface(tf);
        }
    }

    public static void setButtonTextFont(AssetManager assets, Button button, Font font) {
        if(button==null) return;
        Typeface tf =  get(assets, font);
        if(tf != null) {
            button.setTypeface(tf);
        }
    }



    public static class TypefaceSpan extends MetricAffectingSpan {
        /** An <code>LruCache</code> for previously loaded typefaces. */

        private Typeface mTypeface;

        public TypefaceSpan(Context context, Typefaces.Font typefaceName) {
            mTypeface = Typefaces.get(context.getAssets(), typefaceName);
        }

        @Override
        public void updateMeasureState(TextPaint p) {
            p.setTypeface(mTypeface);

            // Note: This flag is required for proper typeface rendering
            p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }

        @Override
        public void updateDrawState(TextPaint tp) {
            tp.setTypeface(mTypeface);

            // Note: This flag is required for proper typeface rendering
            tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
    }

}