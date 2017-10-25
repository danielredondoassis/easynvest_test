package com.test.easynvest.view.adapter.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.easynvest.R;
import com.test.easynvest.data.event.HideSignUpFieldEvent;
import com.test.easynvest.data.event.ShowSignUpFieldEvent;
import com.test.easynvest.data.model.TypeField;
import com.test.easynvest.util.typeface.FontEditText;
import com.test.easynvest.util.typeface.FontInputTextLayout;
import com.test.easynvest.view.contract.SignUpContracts;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by DT on 10/22/17.
 */

public class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public void setupCheckListener(CheckBox checkBox, SignUpContracts.Interactor interactor) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                EventBus.getDefault().post(new ShowSignUpFieldEvent());
                interactor.validateUserPhone(true);
            } else {
                EventBus.getDefault().post(new HideSignUpFieldEvent());
                interactor.validateUserPhone(false);

            }
        });
    }

    public void setFieldError(FontInputTextLayout textInputLayout, String errorMessage) {
        textInputLayout.setError(errorMessage);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.refreshDrawableState();
    }

    public void resetFieldError(FontInputTextLayout textInputLayout) {
        textInputLayout.setErrorEnabled(false);
        textInputLayout.refreshDrawableState();
    }

    public void resetFieldColor(Context context, FontEditText editText){
        Drawable wrappedDrawable = DrawableCompat.wrap(editText.getBackground());
        DrawableCompat.setTint(wrappedDrawable.mutate(), context.getResources().getColor(R.color.form_textgray));
        editText.setBackgroundDrawable(wrappedDrawable);
    }

    public void displayImage(String imageUrl, ImageView imageView) {
        ImageLoader.getInstance().displayImage(imageUrl, imageView);
    }

    public interface HolderListeners {
        void removeObserver();
        void addObserver();
    }

    public TypeField getTypeFieldForCell(String typeField) {

        TypeField type = null;
        try {
            type = TypeField.getEnumForId(Integer.parseInt(typeField));
        } catch (NumberFormatException e){
            try {
                type = TypeField.getEnumForName(typeField);
            } catch (Exception e2){
                e2.printStackTrace();
            }
            e.printStackTrace();
        }

        return type;
    }

}
