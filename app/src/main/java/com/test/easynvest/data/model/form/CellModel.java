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
    private int message;

    @Expose
    @SerializedName("typefield")
    private int typefield;

    @Expose
    @SerializedName("hidden")
    private boolean hidden;

    @Expose
    @SerializedName("topSpacing")
    private double topSpacing;

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

    public void setType(int type) {
        this.type = type;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getTypefield() {
        return typefield;
    }

    public void setTypefield(int typefield) {
        this.typefield = typefield;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public double getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(double topSpacing) {
        this.topSpacing = topSpacing;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
