package com.developer.smmousavi.foodino.activities.specialoffers;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.fragments.specialoffers.SpecialOffersFragment;

public class SpecialOffersActivity extends SingleFragmentActivity {


    public static final String NEW_INTENT_OFFER_TYPE =
        "com.developer.smmousavi.foodino.activity.specialoffers.SpecialOffersActivity.NewIntentOfferType";

    public enum OfferType {
        SPECIAL_OFFER,
        MOST_SOLD,
        MOST_SEEN,
        NEWEST
    }

    public static Intent newIntent(Context context, OfferType offerType) {
        Intent intent = new Intent(context, SpecialOffersActivity.class);
        intent.putExtra(NEW_INTENT_OFFER_TYPE, offerType);
        return intent;
    }

    @Override
    public BaseDaggerFragment createFragment() {
        OfferType type = (OfferType) getIntent().getSerializableExtra(NEW_INTENT_OFFER_TYPE);
        return SpecialOffersFragment.newInstance(type);
    }

    @Override
    public String getTag() {
        return SpecialOffersFragment.TAG;
    }

}
