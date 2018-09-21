package com.sean.myquery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sean.myquery.R;
import com.sean.myquery.bean.ShiLunhouBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/*****************************************
 * @description:市排名
 * @author:lixiaohui
 * @date: 2017/9/19
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class ShiLunhouAdapter extends ArrayAdapter<ShiLunhouBean> {
//    轮候排位
//            备案回执号
//    申请人姓名
//            身份证号
//    入深户时间
//            社保累计缴费时间
//    排位依据时间
//            弃选情况
//    备注
    private LayoutInflater mInflater;

    public ShiLunhouAdapter(@NonNull Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView= mInflater.inflate(R.layout.listitem_shi_lunhou, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();
        ShiLunhouBean item = getItem(position);
        holder.mT1.setText(item.getPAIX()+"");
        holder.mT2.setText(item.getXINGM());
        holder.mT3.setText(item.getRUHSJ());
        holder.mT4.setText(item.getSFZH());
        holder.mT5.setText(item.getSHOULHZH());
        holder.mT6.setText(item.getREMARK());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.t1)
        TextView mT1;
        @BindView(R.id.t2)
        TextView mT2;
        @BindView(R.id.t3)
        TextView mT3;
        @BindView(R.id.t4)
        TextView mT4;
        @BindView(R.id.t5)
        TextView mT5;
        @BindView(R.id.t6)
        TextView mT6;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
