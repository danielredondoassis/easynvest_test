package com.test.easynvest.data.model.fund;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class GraphModel implements Serializable {

    @Expose
    @SerializedName("CDI")
    private double[] cdiInfo;

    @Expose
    @SerializedName("fund")
    private double[] fundInfo;

    @Expose
    @SerializedName("x")
    private String[] graphPeriod;

    public String[] getGraphPeriod () {
        return graphPeriod;
    }

    public double[] getFundInfo() {
        return fundInfo;
    }

    public double[] getCdiInfo() {
        return cdiInfo;
    }

}