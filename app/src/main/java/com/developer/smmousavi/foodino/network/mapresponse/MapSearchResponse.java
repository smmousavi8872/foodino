package com.developer.smmousavi.foodino.network.mapresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapSearchResponse {
    @SerializedName("odata.count")
    @Expose
    private int mCount;

    @SerializedName("value")
    @Expose
    private List<Value> mValueList;


    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public List<Value> getValueList() {
        return mValueList;
    }

    public void setValueList(List<Value> valueList) {
        mValueList = valueList;
    }
}
