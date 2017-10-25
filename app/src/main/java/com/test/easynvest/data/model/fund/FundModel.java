package com.test.easynvest.data.model.fund;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.easynvest.data.model.auth.UserModel;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class FundModel implements Serializable {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("fundName")
    private String fundName;

    @Expose
    @SerializedName("whatIs")
    private String whatIs;

    @Expose
    @SerializedName("definition")
    private String definition;

    @Expose
    @SerializedName("graph")
    private GraphModel graphInfo;


    @Expose
    @SerializedName("riskTitle")
    private String riskTitle;

    @Expose
    @SerializedName("risk")
    private int risk;

    @Expose
    @SerializedName("infoTitle")
    private String infoTitle;

    @Expose
    @SerializedName("moreInfo")
    private MoreInfoModel moreInfo;

    @Expose
    @SerializedName("info")
    private RowModel[] info;

    @Expose
    @SerializedName("downInfo")
    private RowModel[] downInfo;

    public String getTitle() {
        return title;
    }

    public String getFundName() {
        return fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public GraphModel getGraphInfo() {
        return graphInfo;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public int getRisk() {
        return risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public MoreInfoModel getMoreInfo() {
        return moreInfo;
    }

    public RowModel[] getInfo() {
        return info;
    }

    public RowModel[] getDownInfo() {
        return downInfo;
    }
}
