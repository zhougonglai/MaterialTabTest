package com.yifudai.zhougonglai.materialtabtest.fgm;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.rey.material.widget.ProgressView;
import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.adt.CustomSwipeAdapter;
import com.yifudai.zhougonglai.materialtabtest.adt.InvestAdapter;
import com.yifudai.zhougonglai.materialtabtest.po.Invest;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;


/**
 * Created by zhougonglai on 16/4/6.
 */
public class MMFragment extends Fragment{
    private int[] image_resource = {R.drawable.sample_0,R.drawable.sample_1,R.drawable.sample_2,R.drawable.banner};

    private List<Invest> invests = new ArrayList<>();

    private RecyclerView recyclerView;

    private InvestAdapter investAdapter;

    private Invest invest;

    private NumberProgressBar progressBar;

    public MMFragment() {
        prepareInvestData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public ImageView getImage(int img_res){
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(img_res);
        return image;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        setupFlipper(view);
        setupTitle(view);
        setupProgress(view);
        setupInfo(view);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        investAdapter = new InvestAdapter(invests,getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(investAdapter);
        return view;
    }

    public void setupInfo(View view){
        TextView money = (TextView) view.findViewById(R.id.money);
        money.setText(invest.getMoney().toString());

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(invest.getMonth().toString()+"个月");

        TextView rate = (TextView) view.findViewById(R.id.rate);
        rate.setText(invest.getRate().toString()+"%年化收益");
    }

    public void setupProgress(View view){
        progressBar = (NumberProgressBar) view.findViewById(R.id.progress);
        progressBar.setProgress(invest.getIn_money()*100/invest.getMoney());
    }

    public void setupTitle(View view){
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(invest.getTitle());

        ImageView image = (ImageView) view.findViewById(R.id.thumbnail);
        switch (invest.getType()){
            case 1:
                image.setBackgroundResource(R.drawable.mark1);
                break;
            case 2:
                image.setBackgroundResource(R.drawable.mark2);
                break;
            case 3:
                image.setBackgroundResource(R.drawable.mark3);
                break;
            case 4:
                image.setBackgroundResource(R.drawable.mark4);
                break;
            case 5:
                image.setBackgroundResource(R.drawable.mark5);
                break;
            case 6:
                image.setBackgroundResource(R.drawable.mark6);
                break;
            default:
                image.setBackgroundResource(R.drawable.mark1);
                break;
        }
    }

    public void setupFlipper(View view){
        ViewFlipper flipper = (ViewFlipper) view.findViewById(R.id.flipper);
        for ( int i : image_resource){
            flipper.addView(getImage(i));
        }
        flipper.setAutoStart(true);
        flipper.setInAnimation(getContext(),R.anim.right_enter);
        flipper.setOutAnimation(getContext(),R.anim.left_out);
        flipper.setFlipInterval(3000);
        if(flipper.isAutoStart() && !flipper.isFlipping()){
            flipper.startFlipping();
        }
    }

    public void prepareInvestData(){
         invest = new Invest("借款标",1,100000,12,13000,12.5);
         invests.add(invest);
    }
}
