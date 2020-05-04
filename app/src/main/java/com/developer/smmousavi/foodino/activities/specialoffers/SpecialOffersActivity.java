package com.developer.smmousavi.foodino.activities.specialoffers;

import android.content.Context;
import android.content.Intent;

import com.developer.smmousavi.foodino.activities.singlefragment.SingleFragmentActivity;
import com.developer.smmousavi.foodino.fragments.specialoffers.SpecialOffersFragment;

import androidx.fragment.app.Fragment;

public class SpecialOffersActivity extends SingleFragmentActivity {


    public static final String NEW_INTENT_OFFER_TYPE =
        "com.developer.smmousavi.digishop.activity.specialoffers.SpecialOffersActivity.NewIntentOfferType";

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
    public Fragment createFragment() {
        OfferType type = (OfferType) getIntent().getSerializableExtra(NEW_INTENT_OFFER_TYPE);
        return SpecialOffersFragment.newInstance(type);
    }

    @Override
    public String getTag() {
        return SpecialOffersFragment.TAG;
    }

}
