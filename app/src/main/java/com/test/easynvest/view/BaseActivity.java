package com.test.easynvest.view;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.R;

import java.lang.reflect.Field;


public class BaseActivity<T> extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    public static final int PICK_CAMERA = 2;

    private ProgressDialog mProgressDialog;
    private ProgressDialog mLoadingDialog;

    public void tintStatusBar(Window window, int color) {
        if (window == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, color));
        }
    }

    public void tintActionBar(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if(getSupportActionBar() != null)  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((EasynvestApplication)this.getApplication()).activityStarted();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ((EasynvestApplication)this.getApplication()).activityStopped();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void changeActivity(BaseActivity activity, Class<T> clazz){
        startActivity(new Intent(activity, clazz));
    };

    public void changeActivityWithFinish(BaseActivity activity, Class<T> clazz){
        startActivity(new Intent(activity, clazz));
        activity.finish();
    };

    public void changeActivityWithIntent(Intent intent){
        startActivity(intent);
    };


    protected void setupSupportActionBar(Toolbar toolbar, boolean backOnOff) {

        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setDisplayHomeAsUpEnabled(backOnOff);
            getSupportActionBar().setDisplayShowHomeEnabled(backOnOff);
            if(backOnOff) getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
        tintStatusBar(getWindow(), R.color.black);
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = ProgressDialog.show(this, getString(R.string.loading_title), getString(R.string.loading_message), true);
            mLoadingDialog.setCancelable(false);
        } else {
            mLoadingDialog.show();
        }
    }

    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    protected void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    protected void hideInputMethod(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void showBasicAlert(String title, String message) {
        final AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create();

        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialog, which) -> alert.dismiss());
        alert.show();
    }

    public void showBasicAlertWithFinish(String title, String message) {
        final AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .create();

        alert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {
            alert.dismiss();
            finish();
        });
        alert.show();
    }

    public void changeProgressBarColor(ProgressBar progressBar, int color) {
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(color),
                PorterDuff.Mode.SRC_ATOP);
    }

    public void animateViewAlpha(final View v, float alpha, int duration){
        if(alpha > 0){
            v.setVisibility(View.VISIBLE);
            v.animate().alpha(alpha).setDuration(duration).start();
        } else {
            v.animate().alpha(alpha).setDuration(duration).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {}

                @Override
                public void onAnimationEnd(Animator animation) {
                    v.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationRepeat(Animator animation) {}
            }).start();

        }
    }

    public void changeWithStackFragment(Fragment fragment, FrameLayout fragmentHolder){
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(fragmentHolder.getId(), fragment);
        fragmentTransaction.addToBackStack(null);
        try {
            fragmentTransaction.commit();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    public void changeFragment(Fragment fragment, FrameLayout fragmentHolder){
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(fragmentHolder.getId(), fragment);

        try {
            fragmentTransaction.commit();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }
    }
}
