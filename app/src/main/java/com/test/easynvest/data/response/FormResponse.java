package com.test.easynvest.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.easynvest.data.model.form.CellModel;

/**
 * Created by DT on 10/21/17.
 */

public class FormResponse {

    @Expose
    @SerializedName("cells")
    private CellModel[] cells;

    public CellModel[] getCells() {
        return cells;
    }

}
