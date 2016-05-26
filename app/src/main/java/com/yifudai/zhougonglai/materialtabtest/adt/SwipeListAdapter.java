package com.yifudai.zhougonglai.materialtabtest.adt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yifudai.zhougonglai.materialtabtest.po.Fruit;

import java.util.List;

/**
 * Created by zhougonglai on 16/5/12.
 */
public class SwipeListAdapter extends BaseAdapter{
    private List<Fruit> fruits;
    private LayoutInflater inflater;
    private String[] bgColors;

    public SwipeListAdapter() {
    }

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return fruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
