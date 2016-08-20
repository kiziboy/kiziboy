package com.kizi.myfirstwork.Base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.kizi.myfirstwork.Http.HttpClient;

/**
 * Created by ASUS on 2016/8/11.
 */
public class BaseFragment extends Fragment {
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        HttpClient.cancelRequest(this);
    }
}
