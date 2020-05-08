package com.developer.smmousavi.foodino.ui.fragments.chatbottomsheet;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.smmousavi.foodino.R;
import com.developer.smmousavi.foodino.ui.fragments.base.BaseDaggerFragment;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatBottomSheetFargment extends BaseDaggerFragment {

    public static final String TAG = "ChatBottomSheetFragmentTag";

    @BindView(R.id.imgCloseChat)
    AppCompatImageView mImgCloseChat;

    private OnChatCloseClickListener mOnChatCloseClickListener;

    public void setOnChatCloseClickListener(OnChatCloseClickListener onChatCloseClickListener) {
        mOnChatCloseClickListener = onChatCloseClickListener;
    }

    public static ChatBottomSheetFargment newInstance() {

        Bundle args = new Bundle();

        ChatBottomSheetFargment fragment = new ChatBottomSheetFargment();
        fragment.setArguments(args);
        return fragment;
    }


    public ChatBottomSheetFargment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat_bottom_sheet, container, false);
        return v;
    }

    @OnClick(R.id.imgCloseChat)
    void setChatClocseListener() {
        mOnChatCloseClickListener.onCloseClicked();
    }

}
