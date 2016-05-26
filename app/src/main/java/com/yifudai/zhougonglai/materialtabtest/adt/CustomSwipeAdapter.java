package com.yifudai.zhougonglai.materialtabtest.adt;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yifudai.zhougonglai.materialtabtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhougonglai on 16/4/6.
 */
public class CustomSwipeAdapter extends PagerAdapter{
    private int[] image_resource = {R.drawable.sample_0,R.drawable.sample_1,R.drawable.sample_2,R.drawable.banner};
    private Context ctx;
    private LayoutInflater layoutInflater;
    private List<ImageView> imageViews = new ArrayList<ImageView>();

    public CustomSwipeAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }

    public void removeView(){

    }


    public void addImageView(){

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        layoutInflater = (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
//        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
//        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
//        imageView.setImageResource(image_resource[position]);
//        container.addView(imageView);
//        return imageView;
//        container.addView(imageViews.get(position));
        return null;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //(LinearLayout)
        return view== object;
    }
}
