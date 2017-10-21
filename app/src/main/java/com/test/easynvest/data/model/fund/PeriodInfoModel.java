package com.test.easynvest.data.model.fund;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class PeriodInfoModel implements Serializable {


    @Expose
    @SerializedName("fund")
    private double fund;

    @Expose
    @SerializedName("CDI")
    private double cdi;


    public double getFundValue() {
        return fund;
    }

    public double getCDIValue() {
        return cdi;
    }
}
