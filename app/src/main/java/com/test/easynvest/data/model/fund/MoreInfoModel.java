package com.test.easynvest.data.model.fund;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class MoreInfoModel implements Serializable {


    @Expose
    @SerializedName("month")
    private PeriodInfoModel month;


    @Expose
    @SerializedName("year")
    private PeriodInfoModel year;


    @Expose
    @SerializedName("12months")
    private PeriodInfoModel twelveMonths;

    public PeriodInfoModel getMonth() {
        return month;
    }

    public PeriodInfoModel getYear() {
        return year;
    }

    public PeriodInfoModel getTwelveMonths() {
        return twelveMonths;
    }
}
