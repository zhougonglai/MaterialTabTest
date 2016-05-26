package com.yifudai.zhougonglai.materialtabtest.fgm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.adt.InvestAdapter;
import com.yifudai.zhougonglai.materialtabtest.de.DividerItemDecoration;
import com.yifudai.zhougonglai.materialtabtest.po.Invest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhougonglai on 16/4/6.
 */
public class TwoFragment extends Fragment{
    private int[] image_resource = {R.drawable.sample_0,R.drawable.sample_1,R.drawable.sample_2,R.drawable.banner};
    private ViewFlipper flipper;
    private RecyclerView recyclerView;
    private List<Invest> invests = new ArrayList<>();
    private Invest invest;
    private InvestAdapter mAdapter;

    public TwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareInvestData();
    }

    private ImageView getImageView(int resId){
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(resId);
        return image;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_layout,container,false);
        mAdapter = new InvestAdapter(invests,getContext());

        setupRecycler(view);
        return view;
    }

    public void setupRecycler(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Invest invest = invests.get(position);
                Toast.makeText(getContext(),invest.getTitle() + "已经被选中!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(mAdapter);
    }

    public void prepareInvestData(){
        // title,type,周期,已投,利率
        invest = new Invest("借款标1",1,100000,12,1000,12.5);
        invests.add(invest);

        invest = new Invest("借款标2",2,100000,6,1000,10.22);
        invests.add(invest);

        invest = new Invest("借款标3",3,120000,1,7000,11.3);
        invests.add(invest);

        invest = new Invest("借款标4",4,150000,1,7000,11.3);
        invests.add(invest);

        invest = new Invest("借款标5",5,1000000,3,310000,12.1);
        invests.add(invest);
    }

    public interface ClickListener {
        void onClick(View view,int position);
        void onLongClick(View view,int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private GestureDetector gestureDetector;
        private TwoFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context,final RecyclerView recyclerView,final TwoFragment.ClickListener clickListener) {
//            this.gestureDetector = gestureDetector;
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child != null && clickListener != null){
                        clickListener.onLongClick(child,recyclerView.getChildLayoutPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildLayoutPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
