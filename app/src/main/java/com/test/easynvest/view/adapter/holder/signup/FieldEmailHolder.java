package com.test.easynvest.view.adapter.holder.signup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import com.test.easynvest.data.event.HideSignUpFieldEvent;
import com.test.easynvest.data.event.ShowFieldErrorEvent;
import com.test.easynvest.data.event.ShowFieldSuccessEvent;
import com.test.easynvest.data.event.ShowSignUpFieldEvent;
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

public class FieldEmailHolder extends BaseHolder implements BaseHolder.HolderListeners {

    private RelativeLayout mTransitionLayout;
    private RelativeLayout mFieldTextLayout;
    private FontEditText mEditText;
    private FontInputTextLayout mEditInput;
    private ImageView mBtnClear;
    private CellModel mCellModel;
    private int mLayoutHeight;
    private SignUpContracts.Interactor mInteractor;
    private TypeField mTypeField;
    private Type mType;
    private Context context;


    public FieldEmailHolder(View itemView) {
        super(itemView);

        mEditInput = itemView.findViewById(R.id.editEmailInput);
        mEditText = itemView.findViewById(R.id.editEmailText);
        mBtnClear = itemView.findViewById(R.id.btnClear);
        mFieldTextLayout = itemView.findViewById(R.id.fieldTextLayout);
        mTransitionLayout = itemView.findViewById(R.id.transitionLayout);
    }

    public void setup(Context context, SignUpContracts.Interactor interactor, CellModel cellModel) {

        this.context = context;
        this.mInteractor = interactor;

        mCellModel = cellModel;

        addTopSpacing(context,mCellModel.getTopSpacing());
        hideFieldLayout(true);
        addObserver();

        mType = Type.getEnumForId(cellModel.getType());
        mTypeField = getTypeFieldForCell(cellModel.getTypefield());

        if(mTypeField != null){
            if(mTypeField == TypeField.EMAIL){
                mEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
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

    @Subscribe
    public void onMessageEvent(HideSignUpFieldEvent event) {
        hideFieldLayout(false);
    }

    @Subscribe
    public void onMessageEvent(ShowSignUpFieldEvent event) {
        showFieldLayout();
    }

    public void showFieldLayout(){
            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTransitionLayout, "alpha", 1f);
            ObjectAnimator objectAlphaOutAnimator = ObjectAnimator.ofFloat(mTransitionLayout, "alpha", 0f);

            ValueAnimator layoutAnimation = new ValueAnimator().ofObject(new IntEvaluator(), 0, mLayoutHeight);

            layoutAnimation.addUpdateListener(animator -> {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mEditInput.getLayoutParams();
                params.height = (int) animator.getAnimatedValue();
                mEditInput.setLayoutParams(params);
                mEditInput.requestLayout();
            });

            objectAnimator.setDuration(0);
            objectAlphaOutAnimator.setDuration(125);
            layoutAnimation.setDuration(200);

            animatorSet.playSequentially(objectAnimator,layoutAnimation,objectAlphaOutAnimator);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}
            });
            animatorSet.start();
    }

    public void hideFieldLayout(boolean noDuration){
        mBtnClear.setVisibility(View.GONE);
        AnimatorSet animatorSet = new AnimatorSet();

        mEditInput.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mLayoutHeight = mEditInput.getMeasuredHeight();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTransitionLayout, "alpha", 1f);
        ValueAnimator layoutAnimation = new ValueAnimator().ofObject(new IntEvaluator(), mLayoutHeight, 0);

        layoutAnimation.addUpdateListener(animator -> {
            if(mEditInput != null) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mEditInput.getLayoutParams();
                params.height = (int) animator.getAnimatedValue();
                mEditInput.setLayoutParams(params);
                mEditInput.requestLayout();
            }
        });

        objectAnimator.setDuration(noDuration ? 0 : 125);
        layoutAnimation.setDuration(noDuration ? 0 : 200);

        animatorSet.playSequentially(objectAnimator,layoutAnimation);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                mEditText.setText("");
            resetFieldError(mEditInput);}

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        animatorSet.start();
    };

    @Override
    public void removeObserver() {
        if(EventBus.getDefault().isRegistered(this)) EventBus.getDefault().unregister(this);
        mEditText.removeTextChangedListener(clearBtnWatcher);
    }

    @Override
    public void addObserver() {
        if(!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }
}
