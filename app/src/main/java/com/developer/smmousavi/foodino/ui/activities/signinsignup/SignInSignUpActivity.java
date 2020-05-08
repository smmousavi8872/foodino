package com.developer.smmousavi.foodino.ui.activities.signinsignup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.activities.base.BaseDaggerCompatActivity;
import com.developer.smmousavi.foodino.ui.activities.shoppingbasket.ShoppingBasketActivity;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;
import com.developer.smmousavi.foodino.ui.fragments.signinsignup.SignInFragment;
import com.developer.smmousavi.foodino.ui.fragments.signinsignup.SignUpFragment;
import com.developer.smmousavi.foodino.util.Animations;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInSignUpActivity extends BaseDaggerCompatActivity {

    @BindView(R.id.txtToolbarTitle)
    AppCompatTextView mTxtToolbarTitle;
    @BindView(R.id.imgToolbarShoppingBasket)
    AppCompatImageView mImgToolbarShoppingBasket;
    @BindView(R.id.imgToolbarEmptyShoppingBasket)
    AppCompatImageView mImgEmptyShoppingBasket;
    @BindView(R.id.imgToolbarCloseShoppingBasket)
    AppCompatImageView mImgCloseActivity;

    private BaseDaggerFragment mAttachedFragment;

    public static Intent newIntent(Context orgin) {
        Intent intent = new Intent(orgin, SignInSignUpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);
        ButterKnife.bind(this);
        insertFragment(SignInFragment.newInstance());
        initToolabr();
    }

    private void initToolabr() {
        if (mAttachedFragment instanceof SignInFragment) {
            mTxtToolbarTitle.setText(getResources().getString(R.string.toolbar_signin));
        } else if (mAttachedFragment instanceof SignUpFragment) {
            mTxtToolbarTitle.setText(getResources().getString(R.string.toolbar_signup));
            Animations.setAnimation(Animations.FADE_IN, mTxtToolbarTitle);
        }
        mImgEmptyShoppingBasket.setVisibility(View.GONE);
    }

    public void insertFragment(BaseDaggerFragment fragment) {
        mAttachedFragment = fragment;
        getSupportFragmentManager().beginTransaction()
            .add(R.id.flSignFragmentContianer, fragment)
            .commit();
    }

    public void repalceFramgen(BaseDaggerFragment replacmentFragment) {
        mAttachedFragment = replacmentFragment;
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(R.anim.hint_in, R.anim.fade_out)
            .replace(R.id.flSignFragmentContianer, replacmentFragment, replacmentFragment.getTag())
            .commit();

        initToolabr();
    }

    @OnClick(R.id.imgToolbarCloseShoppingBasket)
    public void setOnCloseImageLitener(View v) {
        finish();
    }

    @OnClick(R.id.imgToolbarShoppingBasket)
    public void setOnShoppingBasketListener(View v) {
        startActivity(ShoppingBasketActivity.newIntent(this));
    }
}
