package com.test.easynvest.data.model.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class UserModel implements Serializable {


    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("Email")
    private String email;

    @Expose
    @SerializedName("Phone")
    private String phone;

    public void setValidateEmail(boolean validateEmail) {
        this.validateEmail = validateEmail;
    }

    private boolean validateEmail = false;

    public UserModel() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserEmail(String userEmail) {
        this.email = userEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String cellphone) {
        if(cellphone != null) {
            cellphone = cellphone.replace("(", "");
            cellphone = cellphone.replace(")", "");
            cellphone = cellphone.replace("-", "");
            cellphone = cellphone.replace(" ", "");
            this.phone = cellphone;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        int count = 0;

        if(name != null) count++;
        if(phone != null) count++;
        if(email != null && !email.equals("") && validateEmail) count ++;

        return validateEmail ? count == 3 ? true : false : count == 2 ? true : false;
    }

    public boolean validateEmail() {
        return validateEmail;
    }
}
