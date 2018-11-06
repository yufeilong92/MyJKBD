package com.example.myjkbd.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myjkbd.R;

import java.util.ArrayList;


public class ReadFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private String mParam2;
    private TextView mTvContent;

    public ReadFragment() {
    }

    public static ReadFragment newInstance(String mData) {
        ReadFragment fragment = new ReadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        mTvContent.setText(mParam1);
    }

    private void initView(View view) {
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
    }
}
