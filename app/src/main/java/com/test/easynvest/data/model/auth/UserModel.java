package com.test.easynvest.data.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class UserModel implements Serializable {


    @Expose
    @SerializedName("Type")
    private int type;

    @Expose
    @SerializedName("DeviceOS")
    private String deviceOS;

    @Expose
    @SerializedName("DeviceOSText")
    private String deviceOSText;

    @Expose
    @SerializedName("DeviceToken")
    private String deviceToken;

    @Expose
    @SerializedName("FullName")
    private String fullName;

    @Expose
    @SerializedName("Email")
    private String email;

    @Expose
    @SerializedName("Phone")
    private String phone;

    @Expose
    @SerializedName("Password")
    private String password;

    @Expose
    @SerializedName("FacebookID")
    private String facebookId;

    @Expose
    @SerializedName("GoogleID")
    private String googleId;

    @Expose
    @SerializedName("NesletterOptIn")
    private boolean newsLetterOptIn;

    @Expose
    @SerializedName("Gender")
    private String gender;

    @Expose
    @SerializedName("GenderText")
    private String genderText;

    @Expose
    @SerializedName("City")
    private String city;

    @Expose
    @SerializedName("ProvinceShortName")
    private String UF;

    @Expose
    @SerializedName("BirthDate")
    private String birthdate;

    @Expose
    @SerializedName("MaritalStatus")
    private int maritalStatus;

    @Expose
    @SerializedName("MaritalStatusText")
    private String maritalStatusText;

    @Expose
    @SerializedName("NesletterOptInTips")
    private boolean newsLetterOptInTips;

    @Expose
    @SerializedName("NesletterOptInPromotions")
    private boolean newsLetterOptInPromotions;

    @Expose
    @SerializedName("NesletterOptInEvents")
    private boolean newsLetterOptInEvents;

    @Expose
    @SerializedName("NesletterOptInActions")
    private boolean newsLetterOptInActions;

    @Expose
    @SerializedName("RequiresPhoneVerification")
    private boolean requiresPhoneVerification;

    @Expose
    @SerializedName("SMSCode")
    private String smsCode;

    @Expose
    @SerializedName("NewPhone")
    private String newPhone;

    public UserModel() {}

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public void setDeviceToken(String token) {
        this.deviceToken = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public boolean isNewsLetterOptIn() {
        return newsLetterOptIn;
    }

    public void setNewsLetterOptIn(boolean newsLetterOptIn) {
        this.newsLetterOptIn = newsLetterOptIn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderText() {
        return genderText;
    }

    public void setGenderText(String genderText) {
        this.genderText = genderText;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getBirthdate() {

        if(birthdate != null){
            String[] splitDate = birthdate.split("-");
            if(splitDate.length == 3){
                return splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];
            }
        }
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusText() {
        return maritalStatusText;
    }

    public void setMaritalStatusText(String maritalStatusText) {
        this.maritalStatusText = maritalStatusText;
    }

    public boolean isNewsLetterOptInTips() {
        return newsLetterOptInTips;
    }

    public void setNewsLetterOptInTips(boolean newsLetterOptInTips) {
        this.newsLetterOptInTips = newsLetterOptInTips;
    }

    public boolean isNewsLetterOptInPromotions() {
        return newsLetterOptInPromotions;
    }

    public void setNewsLetterOptInPromotions(boolean newsLetterOptInPromotions) {
        this.newsLetterOptInPromotions = newsLetterOptInPromotions;
    }

    public boolean isNewsLetterOptInEvents() {
        return newsLetterOptInEvents;
    }

    public void setNewsLetterOptInEvents(boolean newsLetterOptInEvents) {
        this.newsLetterOptInEvents = newsLetterOptInEvents;
    }

    public boolean isNewsLetterOptInActions() {
        return newsLetterOptInActions;
    }

    public void setNewsLetterOptInActions(boolean newsLetterOptInActions) {
        this.newsLetterOptInActions = newsLetterOptInActions;
    }

    public boolean isRequiresPhoneVerification() {
        return requiresPhoneVerification;
    }

    public void setRequiresPhoneVerification(boolean requiresPhoneVerification) {
        this.requiresPhoneVerification = requiresPhoneVerification;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String cellphone) {
        cellphone = cellphone.replace("(", "");
        cellphone = cellphone.replace(")", "");
        cellphone = cellphone.replace("-", "");
        cellphone = cellphone.replace(" ", "");
        this.phone = cellphone;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public boolean equals(Object o) {
        if(o.getClass().equals(this.getClass())) {
            UserModel object = (UserModel) o;
            if(object.phone.equals(this.phone)) return true;
        }
        return false;
    }

    public String getDeviceOSText() {
        return deviceOSText;
    }
}
