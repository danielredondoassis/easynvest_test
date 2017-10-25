package com.test.easynvest.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.easynvest.data.model.form.CellModel;
import com.test.easynvest.data.model.fund.FundModel;

/**
 * Created by DT on 10/21/17.
 */

public class FundResponse {

    @Expose
    @SerializedName("screen")
    private FundModel screen;

    public FundModel getFundModel() {
        return screen;
    }

}
