package com.developer.smmousavi.foodino.activities.drawer;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

public interface SetOnContentFragmentInsert {

    @IdRes
    int getFragmentId();

    Fragment getFragmentObject();

    String getFragmentTag();
}
