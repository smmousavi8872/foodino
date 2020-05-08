package com.developer.smmousavi.foodino.ui.fragments.signinsignup;


import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseDaggerFragment {

    public static final String TAG = "SignUpFragmentTag";

    @BindView(R.id.edtSignUpUserEmail)
    AppCompatEditText mEdtUserEmail;
    @BindView(R.id.edtSignUpUserPassword)
    AppCompatEditText mEdtUserPassword;
    @BindView(R.id.chkReceiveMagazine)
    AppCompatCheckBox mChkRecieveMagazine;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, v);
        mEdtUserPassword.setTransformationMethod(new PasswordTransformationMethod());
        return v;
    }

}
