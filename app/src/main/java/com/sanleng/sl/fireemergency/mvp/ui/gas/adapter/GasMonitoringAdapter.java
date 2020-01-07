package com.sanleng.sl.fireemergency.mvp.ui.gas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.bean.Gas;

import java.util.List;

/**
 * 燃气监测数据适配器
 *
 * @author QiaoShi
 */
public class GasMonitoringAdapter extends BaseAdapter {
    private List<Gas.PageBean.ListBean> mList;
    private Context mContext;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<Gas.PageBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gasmonitoring_item, null);
            holder = new Holder();
            holder.name = convertView.findViewById(R.id.name);
            holder.value = convertView.findViewById(R.id.value);

            holder.image_devicetype = convertView.findViewById(R.id.image_devicetype);
            holder.img_state = convertView.findViewById(R.id.img_state);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        String device_address=mList.get(position).getPosition();
        String current_state=mList.get(position).getCurrent_value();
        holder.image_devicetype.setBackground(mContext.getResources().getDrawable(R.mipmap.naturalgas));
        holder.name.setText("地址: " + device_address);
        holder.value.setText("当前报警值: " + current_state + " ppm");

//        String state = mList.get(position).;
//        if (state.equals("异常")) {
//            holder.img_state.setBackground(mContext.getResources().getDrawable(R.drawable.abnormal));
//        }
//        if (state.equals("正常")) {
//            holder.img_state.setBackground(mContext.getResources().getDrawable(R.drawable.normal));
//        }

        return convertView;
    }

    class Holder {
        TextView name;
        TextView value;
        ImageView image_devicetype;
        ImageView img_state;

    }
}
