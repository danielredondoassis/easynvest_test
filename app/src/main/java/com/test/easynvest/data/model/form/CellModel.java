package com.test.easynvest.data.model.form;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DT on 10/21/17.
 */

public class CellModel {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("type")
    private int type;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("typefield")
    private String typefield;

    @Expose
    @SerializedName("hidden")
    private boolean hidden;

    @Expose
    @SerializedName("topSpacing")
    private int topSpacing;

    @Expose
    @SerializedName("show")
    private String show;

    @Expose
    @SerializedName("required")
    private boolean required;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getTypefield() {
        return typefield;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getTopSpacing() {
        return topSpacing;
    }

    public String getShow() {
        return show;
    }

    public boolean isRequired() {
        return required;
    }

}
