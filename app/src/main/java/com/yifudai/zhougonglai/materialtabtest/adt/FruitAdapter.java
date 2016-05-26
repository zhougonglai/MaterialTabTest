package com.yifudai.zhougonglai.materialtabtest.adt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.po.Fruit;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zhougonglai on 16/4/19.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> implements AdapterView.OnClickListener{
    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();

            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            ImageView icon = (ImageView) view.findViewById(R.id.icon_right);
            icon.setBackgroundResource(R.drawable.ic_right);

            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);

            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);

            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    @Override
    public void onClick(View v) {
//        v.setOnClickListener();
    }

    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}
