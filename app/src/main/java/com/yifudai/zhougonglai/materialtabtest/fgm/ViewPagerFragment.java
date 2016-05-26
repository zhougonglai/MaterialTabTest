package com.yifudai.zhougonglai.materialtabtest.fgm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yifudai.zhougonglai.materialtabtest.R;

/**
 * Created by zhougonglai on 16/4/7.
 */
public class ViewPagerFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.swipe_layout,container,false);
    }
}
