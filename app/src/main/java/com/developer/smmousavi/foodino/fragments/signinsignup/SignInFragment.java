package com.developer.smmousavi.foodino.fragments.signinsignup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.activities.signinsignup.SignInSignUpActivity;
import com.developer.smmousavi.foodino.fragments.base.BaseDaggerFragment;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends BaseDaggerFragment {

    public static final String TAG = "SignInFragmentTag";

    @BindView(R.id.edtSignInUserEmail)
    TextInputEditText mEdtUserEmail;
    @BindView(R.id.edtSignInUserpassword)
    TextInputEditText mEdtUserPasswrod;
    @BindView(R.id.txtForgotPassword)
    AppCompatTextView mTxtForgotPassword;
    @BindView(R.id.chkShowPassword)
    AppCompatCheckBox mChkShowPassword;
    @BindView(R.id.txtSignUpInDigishop)
    AppCompatTextView mTxtSignUp;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.txtSignUpInDigishop)
    public void setOnSignUpListenter(View v) {
        SignInSignUpActivity parentActivity = (SignInSignUpActivity) getActivity();
        parentActivity.repalceFramgen(SignUpFragment.newInstance());
    }

    @OnClick(R.id.chkShowPassword)
    public void setOnShowPasswordListener(View v) {
    }
}
