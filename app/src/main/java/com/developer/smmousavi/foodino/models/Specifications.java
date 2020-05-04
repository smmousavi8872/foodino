package com.developer.smmousavi.foodino.models;

import com.google.auto.value.AutoValue;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.annotations.Nullable;

@AutoValue
public abstract class Specifications {

    public abstract LinkedHashMap<String, String> generalSpecificationsList();

    @Nullable
    public abstract LinkedHashMap<String, Boolean> booleanSpecificationsList();

    @Nullable
    public abstract List<String> otherExplainationList();

    @Nullable
    public abstract List<String> commodityList();

    public static Specifications create(LinkedHashMap<String, String> generalSpecificationsList,
                                        LinkedHashMap<String, Boolean> booleanSpecificationsList,
                                        List<String> otherExplainationList,
                                        List<String> commodityList) {

        return new AutoValue_Specifications(generalSpecificationsList, booleanSpecificationsList, otherExplainationList, commodityList);

    }
}
