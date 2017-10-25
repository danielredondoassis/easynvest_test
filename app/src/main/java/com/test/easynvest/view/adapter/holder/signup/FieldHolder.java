package com.test.easynvest.view.adapter.holder.signup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test.easynvest.R;
import com.test.easynvest.data.event.GetFieldInfoEvent;
import com.test.easynvest.data.event.ShowFieldErrorEvent;
import com.test.easynvest.data.event.ShowFieldSuccessEvent;
import com.test.easynvest.data.model.Type;
import com.test.easynvest.data.model.TypeField;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.util.UnitConverter;
import com.test.easynvest.util.typeface.FontEditText;
import com.test.easynvest.util.typeface.FontInputTextLayout;
import com.test.easynvest.view.contract.SignUpContracts;
import com.test.easynvest.view.adapter.holder.BaseHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by DT on 10/22/17.
 */

public class FieldHolder extends BaseHolder implements BaseHolder.HolderListeners {

    private FontEditText mEditText;
    private FontInputTextLayout mEditInput;
    private ImageView mBtnClear;
    private SignUpContracts.Interactor mInteractor;
    private Type mType;
    private TypeField mTypeField;
    private Context context;

    public FieldHolder(View itemView) {
        super(itemView);

        mEditInput = itemView.findViewById(R.id.editInput);
        mEditText = itemView.findViewById(R.id.editText);
        mBtnClear = itemView.findViewById(R.id.btnClear);
    }

    public void setup(Context context, SignUpContracts.Interactor interactor, CellModel cellModel) {

        this.mInteractor = interactor;
        this.context = context;

        addTopSpacing(context,cellModel.getTopSpacing());
        addObserver();

        mType = Type.getEnumForId(cellModel.getType());
        mTypeField = getTypeFieldForCell(cellModel.getTypefield());

        if(mTypeField != null){
            if(mTypeField == TypeField.TEXT){
                mEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_CLASS_TEXT);
            }
        }

        mEditInput.setHint(cellModel.getMessage());

        mBtnClear.setOnClickListener(v -> {
            mEditText.setText("");
            resetFieldError(mEditInput);
            resetFieldColor(context,mEditText);
        });

        mEditText.addTextChangedListener(clearBtnWatcher);
    }

    TextWatcher clearBtnWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            resetFieldColor(context,mEditText);
            mBtnClear.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void addTopSpacing(Context context, int spacing) {
        int topSpacing = UnitConverter.dpToPx2(spacing, context);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mEditInput.getLayoutParams();
        params.topMargin = topSpacing;
        mEditInput.setLayoutParams(params);
    }

    @Override
    public void removeObserver() {
        if(EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this);
        mEditText.removeTextChangedListener(clearBtnWatcher);
    }

    @Override
    public void addObserver() {
        if(!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onMessageEvent(ShowFieldErrorEvent event) {
        String errorMessage = event.getErrorMessage(mType,mTypeField);
        if(errorMessage != null) setFieldError(mEditInput, errorMessage);
    }

    @Subscribe
    public void onMessageEvent(ShowFieldSuccessEvent event) {
        if(event.getType() == mType && event.getTypeField() == mTypeField) {
            resetFieldError(mEditInput);
            Drawable wrappedDrawable = DrawableCompat.wrap(mEditText.getBackground());
            DrawableCompat.setTint(wrappedDrawable.mutate(), context.getResources().getColor(R.color.field_validationgreen));
            mEditText.setBackgroundDrawable(wrappedDrawable);
        }
    }

    @Subscribe
    public void onMessageEvent(GetFieldInfoEvent event) {
        mInteractor.registerUserInfo(mType,mTypeField,mEditText.getText().toString());
    }


}
