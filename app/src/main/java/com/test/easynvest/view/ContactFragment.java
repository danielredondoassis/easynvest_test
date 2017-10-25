package com.test.easynvest.view;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.easynvest.R;
import com.test.easynvest.util.FileUtil;
import com.test.easynvest.util.PictureUtils;
import com.test.easynvest.util.Validator;
import com.test.easynvest.util.typeface.FontButton;
import com.test.easynvest.util.typeface.FontEditText;
import com.test.easynvest.util.typeface.FontInputTextLayout;
import com.test.easynvest.util.typeface.FontTextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class ContactFragment extends PictureFragment {


    @BindView(R.id.editNameText)
    FontEditText editText;
    @BindView(R.id.editInput)
    FontInputTextLayout editInput;
    @BindView(R.id.btnNameClear)
    ImageView btnNameClear;
    @BindView(R.id.editEmailText)
    FontEditText editEmailText;
    @BindView(R.id.editEmailInput)
    FontInputTextLayout editEmailInput;
    @BindView(R.id.btnEmailClear)
    ImageView btnEmailClear;
    @BindView(R.id.editPhoneText)
    FontEditText editPhoneText;
    @BindView(R.id.editPhonetInput)
    FontInputTextLayout editPhonetInput;
    @BindView(R.id.btnPhoneClear)
    ImageView btnPhoneClear;
    @BindView(R.id.attachImage)
    ImageView attachImage;
    @BindView(R.id.checkBoxRegisterEmail)
    AppCompatCheckBox checkBoxRegisterEmail;
    @BindView(R.id.textCheckboxOption)
    FontTextView textCheckboxOption;
    @BindView(R.id.checkBoxLayout)
    LinearLayout checkBoxLayout;
    @BindView(R.id.btnSendMessage)
    FontButton mBtnSendMessage;
    @BindView(R.id.btnSendAnotherMessage)
    FontTextView mBtnSendAnotherMessage;
    @BindView(R.id.messageSentLayout)
    RelativeLayout mMessageSentLayout;

    private Unbinder unbinder;
    private AppCompatActivity context;
    public static final int PICK_IMAGE = 1;
    public static final int PICK_CAMERA = 2;
    private String mImagePath;
    private Uri mFileUri;

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.context = (AppCompatActivity) getActivity();

        editPhoneText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_CLASS_PHONE);
        editPhoneText.isPhone(true);
        editPhoneText.setMask("(99) 99999-9999");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        addTextWatcherListeners();
    }

    @Override
    public void onPause() {
        super.onPause();
        removeTextWatcherListener();
    }

    private void addTextWatcherListeners() {
        editText.addTextChangedListener(clearNameWatcher);
        editEmailText.addTextChangedListener(clearEmailWatcher);
        editPhoneText.addTextChangedListener(clearPhoneWatcher);
    }

    private void removeTextWatcherListener() {
        editText.removeTextChangedListener(clearNameWatcher);
        editEmailText.removeTextChangedListener(clearEmailWatcher);
        editPhoneText.removeTextChangedListener(clearPhoneWatcher);
    }

    TextWatcher clearNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnNameClear.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher clearPhoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnPhoneClear.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher clearEmailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            btnEmailClear.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @OnClick(R.id.btnSendAnotherMessage)
    protected void resetScreen() {
        if(mMessageSentLayout.getAlpha() == 1f) {
            editText.setText("");
            editEmailText.setText("");
            editPhoneText.setText("");
            checkBoxRegisterEmail.setChecked(false);
            attachImage.setImageResource(R.drawable.ic_attach_bitmap);
            mMessageSentLayout.animate().alpha(0f).setDuration(200).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mMessageSentLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }


    @OnClick(R.id.attachImage)
    protected void attachImage() {
        checkPermissions();
    }

    @OnClick(R.id.btnEmailClear)
    protected void clearEmailField() {
        editEmailText.setText("");
        resetFieldError(editEmailInput);
    }

    @OnClick(R.id.btnPhoneClear)
    protected void clearPhoneField() {
        editPhoneText.setText("");
        resetFieldError(editPhonetInput);
    }

    @OnClick(R.id.btnNameClear)
    protected void clearNameField() {
        editText.setText("");
        resetFieldError(editInput);
    }

    @OnClick(R.id.btnSendMessage)
    protected void validateStuff() {
        String email = editEmailText.getText().toString();
        String phone = editPhoneText.getText().toString();
        String name = editText.getText().toString();

        if ((email.equals("") || !email.equals("") && !Validator.validateEmail(email))) {
            setFieldError(editEmailInput, context.getResources().getString(R.string.error_invalid_email));
        } else if (phone.equals("") || !phone.equals("") && !Validator.validatePhone(phone)) {
            setFieldError(editPhonetInput, context.getResources().getString(R.string.error_invalid_email));
        } else if (name.equals("")) {
            setFieldError(editInput, context.getResources().getString(R.string.error_invalid_email));
        } else {
            sendStuff(email, phone, name);
        }

    }

    private void sendStuff(String email, String phone, String name) {

        mMessageSentLayout.setVisibility(View.VISIBLE);
        mMessageSentLayout.animate().alpha(1f).setDuration(200).start();
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String originPath = FileUtil.getImagePathFromGalleryUri(getActivity(), uri);
                if (originPath != null) {
                    File picture = new File(originPath);
                    PictureUtils.cropFile(picture);

                    mImagePath = picture.getPath();

                    ImageLoader.getInstance().displayImage("file://" + mImagePath, attachImage);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.initialize_file_fail_message), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.initialize_file_fail_message), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PICK_CAMERA && resultCode == RESULT_OK) {
            if (mFileUri != null) {
                File origin = new File(mFileUri.getPath());
                PictureUtils.cropFile(origin);

                if (mFileUri.getPath() != null) {
                    File picture = new File(mFileUri.getPath());

                    mImagePath = picture.getPath();
                    ImageLoader.getInstance().displayImage("file://" + mImagePath, attachImage);
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.initialize_file_fail_message), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.initialize_file_fail_message), Toast.LENGTH_SHORT).show();

            }
        }
    }
}
