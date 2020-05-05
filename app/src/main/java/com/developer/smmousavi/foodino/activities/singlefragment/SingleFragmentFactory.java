package com.developer.smmousavi.foodino.activities.singlefragment;

import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;

public interface SingleFragmentFactory {

   /*
    * Factory Method Design Pattern
    * Functionality: Factory methods which return products
    */

   BaseDaggerFragment createFragment();

   String getTag();
}
