package com.developer.smmousavi.foodino.activities.singlefragment;

import android.os.Bundle;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.activities.base.BaseDaggerCompatActivity;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;

import androidx.fragment.app.Fragment;


public abstract class SingleFragmentActivity extends BaseDaggerCompatActivity implements SingleFragmentFactory {

    private BaseDaggerFragment mGuestFragment;

    public Fragment getGuestFragment() {
        return mGuestFragment;
    }

    public void setGuestFragment(BaseDaggerFragment guestFragment) {
        mGuestFragment = guestFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        /*
         * Factory Method Design Pattern
         * Functionality: Client
         */
        insertFragment();
    }

    private void insertFragment() {
        mGuestFragment = (BaseDaggerFragment) mFm.findFragmentById(R.id.flSingleFragmentContainer);

        if (mGuestFragment == null) {
            mGuestFragment = createFragment();
            String tag = getTag();
            mFm.beginTransaction()
                .add(R.id.flSingleFragmentContainer, mGuestFragment, tag)
                .commit();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
