package com.developer.smmousavi.foodino.ui.fragments.alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.developer.smmousavi.foodino.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertDialogFragment extends DialogFragment {

    public static final String NEW_INSTANCE_TITE = "newInstanceTitle";
    public static final String NEW_INSTANCE_MESSAGE = "newInstnaceMessage";
    public static final String NEW_INSTANCE_POSITIVE_TEXT = "newInstancePositiveText";
    public static final String NEW_INSTANCE_NEGETIVE_TEXT = "newInstanceNegetiveText";

    @BindView(R.id.txtDilaogTitle)
    AppCompatTextView mTxtDialogTitle;
    @BindView(R.id.txtDilaogMessage)
    AppCompatTextView mTxtDialogMessage;
    @BindView(R.id.txtPositiveButton)
    AppCompatTextView mBtnPositive;
    @BindView(R.id.txtNegetiveButton)
    AppCompatTextView mBtnNegetive;

    private View view;
    private OnDialogButtonClickListener mButtonClickListener;

    public void setButtonClickListener(OnDialogButtonClickListener buttonClickListener) {
        mButtonClickListener = buttonClickListener;
    }

    public static AlertDialogFragment newInstance(String title,
                                                  String alertMessage,
                                                  String positiveButtonText,
                                                  String negetiveButtonText) {
        Bundle args = new Bundle();
        args.putString(NEW_INSTANCE_TITE, title);
        args.putString(NEW_INSTANCE_MESSAGE, alertMessage);
        args.putString(NEW_INSTANCE_POSITIVE_TEXT, positiveButtonText);
        args.putString(NEW_INSTANCE_NEGETIVE_TEXT, negetiveButtonText);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // dependenices are all here
        // should be taken to the module
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_alert_dialog, null, false);
        ButterKnife.bind(this , view);
        setViews();

        return new AlertDialog.Builder(getActivity())
            .setView(view)
            .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hideDefualtBackground();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setViews() {
        Bundle args = getArguments();
        String title = args.getString(NEW_INSTANCE_TITE);
        String message = args.getString(NEW_INSTANCE_MESSAGE);
        String positiveBtnText = args.getString(NEW_INSTANCE_POSITIVE_TEXT);
        String negetiveBtnText = args.getString(NEW_INSTANCE_NEGETIVE_TEXT);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/iran_sans_bold.ttf");

        mTxtDialogTitle.setText(title);
        mTxtDialogMessage.setText(message);
        mBtnPositive.setText(positiveBtnText);
        mBtnNegetive.setText(negetiveBtnText);
        mTxtDialogTitle.setTypeface(tf);

        mBtnPositive.setOnClickListener(v -> {
            if (mButtonClickListener != null)
                mButtonClickListener.onPositiveButtonClick(v);
        });

        mBtnNegetive.setOnClickListener(v -> {
            if (mButtonClickListener != null)
                mButtonClickListener.onNegativeButtonClick(v);
        });
    }

    private void hideDefualtBackground() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
    }
}
