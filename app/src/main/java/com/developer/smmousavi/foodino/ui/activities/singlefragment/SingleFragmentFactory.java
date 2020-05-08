package com.developer.smmousavi.foodino.ui.activities.singlefragment;

import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;

public interface SingleFragmentFactory {

   /*
    * Factory Method Design Pattern
    * Functionality: Factory methods which return products
    */

   BaseDaggerFragment createFragment();

   String getTag();
}
