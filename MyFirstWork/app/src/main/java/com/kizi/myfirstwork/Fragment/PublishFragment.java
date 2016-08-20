package com.kizi.myfirstwork.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kizi.myfirstwork.Base.BaseFragment;
import com.kizi.myfirstwork.R;

public class PublishFragment extends BaseFragment {


    private ImageView iv_picture;
    private ImageView iv_tright;
    private ImageView iv_video;

    public PublishFragment() {
    }

    public static PublishFragment newInstance() {
        PublishFragment fragment = new PublishFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_publish, container, false);
        iv_picture=(ImageView)view.findViewById(R.id.iv_fragment_picture);
        iv_tright=(ImageView)view.findViewById(R.id.iv_fragment_tright);
        iv_video=(ImageView)view.findViewById(R.id.iv_fragment_video);
        return view;
    }

}
