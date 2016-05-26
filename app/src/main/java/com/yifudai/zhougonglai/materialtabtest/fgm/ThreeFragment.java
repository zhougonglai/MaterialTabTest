package com.yifudai.zhougonglai.materialtabtest.fgm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rey.material.app.Dialog;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;
import com.rey.material.widget.TextView;
import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.service.Login;

/**
 * Created by zhougonglai on 16/4/6.
 */
public class ThreeFragment extends Fragment {
    public Login login;
    public ThreeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = new Login(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.three_layout,container,false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.onCreate();
            }
        });

        return view;
    }
}
