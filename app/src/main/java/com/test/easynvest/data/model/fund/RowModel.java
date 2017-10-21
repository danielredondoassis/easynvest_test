package com.test.easynvest.data.model.fund;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DT on 6/1/16.
 */
public class RowModel implements Serializable {


    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("data")
    private String data;


    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
