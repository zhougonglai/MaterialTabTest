package com.yifudai.zhougonglai.materialtabtest.adt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.po.Invest;

import java.util.List;

/**
 * Created by zhougonglai on 16/4/11.
 */
public class InvestAdapter extends RecyclerView.Adapter<InvestAdapter.MyinvestHolder>{
    private List<Invest> invests;
    private LayoutInflater inflater;

    public class MyinvestHolder extends RecyclerView.ViewHolder{
        public TextView title,money,date,rate;
        public ImageView type;
        public NumberProgressBar progressBar;

        public MyinvestHolder(View itemView) {
            super(itemView);
            setupTitle(itemView);
            setupProgress(itemView);
            setupInfo(itemView);
        }

        public void setupTitle(View view){
            title = (TextView) view.findViewById(R.id.title);
            type = (ImageView) view.findViewById(R.id.thumbnail);
        }

        public void setupProgress(View view){
            progressBar = (NumberProgressBar) view.findViewById(R.id.progress);
        }

        public void setupInfo(View view){
            money = (TextView) view.findViewById(R.id.money);
            date = (TextView) view.findViewById(R.id.date);
            rate = (TextView) view.findViewById(R.id.rate);
        }
    }

    public InvestAdapter(List<Invest> invests,Context context){
        this.invests = invests;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyinvestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.swipe_layout,parent,false);
        return new MyinvestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyinvestHolder holder, int position) {
        Invest invest = invests.get(position);

        holder.title.setText(invest.getTitle());
        switch (invest.getType()){
            case 1:
                holder.type.setBackgroundResource(R.drawable.mark1);
                break;
            case 2:
                holder.type.setBackgroundResource(R.drawable.mark2);
                break;
            case 3:
                holder.type.setBackgroundResource(R.drawable.mark3);
                break;
            case 4:
                holder.type.setBackgroundResource(R.drawable.mark4);
                break;
            case 5:
                holder.type.setBackgroundResource(R.drawable.mark5);
                break;
            case 6:
                holder.type.setBackgroundResource(R.drawable.mark6);
                break;
            default:
                holder.type.setBackgroundResource(R.drawable.mark1);
                break;
        }
        holder.progressBar.setProgress(invest.getIn_money()*100/invest.getMoney());
        holder.money.setText(invest.getMoney()+"元");
        holder.date.setText(invest.getMonth().toString()+"个月");
        holder.rate.setText(invest.getRate().toString()+"年化收益");
    }


    @Override
    public int getItemCount() {
        return invests.size();
    }
}
