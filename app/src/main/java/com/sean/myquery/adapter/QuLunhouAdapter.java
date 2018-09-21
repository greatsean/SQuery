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
import com.sean.myquery.bean.QuLunhouBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/*****************************************
 * @description:区排名
 * @author:lixiaohui
 * @date: 2017/9/19
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class QuLunhouAdapter extends ArrayAdapter<QuLunhouBean> {

//    轮候册类型
//            户籍区排序号
//    轮候排序号
//            备案回执编号
//    姓名
//            人员类型
//    身份证号码

    private LayoutInflater mInflater;

    public QuLunhouAdapter(@NonNull Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_qu_lunhou, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        QuLunhouBean item = getItem(position);
        holder.mT2.setText(item.getAREA_PAIX() + "");
        holder.mT3.setText(item.getPAIX() + "");
        holder.mT4.setText(item.getSHOULHZH());
        holder.mT5.setText(item.getXINGM());
        holder.mT6.setText(item.getPERSON_TYPE());
        holder.mT7.setText(item.getSFZH());

        return convertView;
    }

    static class ViewHolder {
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
        @BindView(R.id.t7)
        TextView mT7;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
