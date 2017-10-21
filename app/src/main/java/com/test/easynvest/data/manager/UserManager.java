package com.test.easynvest.data.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.test.easynvest.EasynvestApplication;
import com.test.easynvest.dagger.annotations.ApplicationContext;
import com.test.easynvest.data.SharedPrefsHelper;
import com.test.easynvest.data.model.auth.UserModel;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserManager implements Serializable {

    private static final String USER_KEY = "user_key";
    private static final String USER_EMAIL_KEY = "user_email_key";
    private SharedPrefsHelper mSharedPrefsHelper;
    private Context mContext;

    private UserModel mUserModel;

    @Inject
    public UserManager(@ApplicationContext Context context, SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveUser(UserModel user) {
        //TODO: save user for real
        if(user != null) {
            this.mUserModel = user;
            Gson gson = new Gson();
            String sUser = gson.toJson(user);
            mSharedPrefsHelper.put(USER_KEY, sUser);
        }
    }

    public void removeUser() {
        mSharedPrefsHelper.put(USER_KEY, null);
        mUserModel = null;
    }

    public void saveUserEmail(String email) {
        mSharedPrefsHelper.put(USER_EMAIL_KEY, email);
    }

    public String getUserEmail(){
        return mSharedPrefsHelper.get(USER_EMAIL_KEY,null);
    }

    public UserModel getAuthInfo() {
        if(mUserModel == null) {
            //use preferences for now
            Context ctx = EasynvestApplication.getStaticContext();
            String sUser = mSharedPrefsHelper.get(USER_KEY,null);
            if(sUser!=null) {
                Gson gson = new Gson();
                try {
                    mUserModel = gson.fromJson(sUser, UserModel.class);
                } catch(Exception e) {
                    mSharedPrefsHelper.put(USER_KEY, null);
                }
            }
        }
        return mUserModel;
    }

    public boolean isUserLogged() {
        if(getAuthInfo() != null) {
            return true;
        }
        return false;
    }

    public void logout() {
        if(mUserModel == null) getAuthInfo();
        mUserModel = null;
        saveUserEmail(null);
        saveUserPassword(getUserEmail(),null);
        mSharedPrefsHelper.put(USER_KEY, null);
    }

    public void saveUserPassword(String email, String password) {
        saveUserEmail(email);
        mSharedPrefsHelper.put(email, password);
    }

    public String getUserPassword(){
        return mSharedPrefsHelper.get(getUserEmail(),null);
    }
}
