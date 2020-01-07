package com.sanleng.sl.fireemergency.mvp.ui.electricalfire.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;

import java.util.List;

/**
 * 电气火灾数据适配器
 *
 * @author QiaoShi
 */
public class RealDataItemAdapter extends BaseAdapter {
    private Context context;
    List<ReadTimeItems.PageBean.ListBean> list;

    public RealDataItemAdapter(Context context, List<ReadTimeItems.PageBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.realdata_item, parent, false);
            holder.name = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String detector_name = list.get(position).getDetector_name();
        if (detector_name.equals("temperature_detector")) {
//            if (Double.valueOf(list.get(position).getCurrent_value().toString()).intValue() >= Double.valueOf(list.get(position).getLower_limit().toString()).intValue()) {
//                holder.name.setTextColor(context.getResources().getColor(R.color.red));
//                holder.name.setText(list.get(position).getDetector_port() + " | " + "温度: " + list.get(position).getCurrent_value() + " ℃" + "  限值:" + list.get(position).getLower_limit() + "～" + list.get(position).getUpper_limit() + " ℃");
//            } else {
                holder.name.setTextColor(context.getResources().getColor(R.color.white));
                holder.name.setText(list.get(position).getDetector_port() + " | " + "温度: " + list.get(position).getCurrent_value() + " ℃" );//+ "  限值:" + list.get(position).getLower_limit() + "～" + list.get(position).getUpper_limit() + " ℃"
//            }
        }

        if (detector_name.equals("electricity_detector")) {
            holder.name.setText(list.get(position).getDetector_port() + " | " + "电流: " + list.get(position).getCurrent_value() + " A" );//+ "  限值:" + list.get(position).getLower_limit() + "～" + list.get(position).getUpper_limit() + " A"
        }
        if (detector_name.equals("residualcurrent_detector")) {
//            if (Double.valueOf(list.get(position).getCurrent_value().toString()).intValue() >= Double.valueOf(list.get(position).getLower_limit().toString()).intValue()) {
//                holder.name.setTextColor(context.getResources().getColor(R.color.red));
//                holder.name.setText(list.get(position).getDetector_port() + " | " + "剩余电流: " + list.get(position).getCurrent_value() + " mA" + "\n" + "限值：" + list.get(position).getLower_limit() + "～" + list.get(position).getUpper_limit() + " mA");
//            } else {
                holder.name.setTextColor(context.getResources().getColor(R.color.white));
                holder.name.setText(list.get(position).getDetector_port() + " | " + "剩余电流: " + list.get(position).getCurrent_value() + " mA" );//+ "\n" + "限值：" + list.get(position).getLower_limit() + "～" + list.get(position).getUpper_limit() + " mA"
//            }
        }
        if (detector_name.equals("voltage_detector")) {
            holder.name.setText(list.get(position).getDetector_port() + " | " + "电压: " + list.get(position).getCurrent_value() + " V" );//+ "  限值:" + list.get(position).getLower_limit() + "～" + list.get(position).getUpper_limit() + " V"
        }
        return convertView;
    }

    class ViewHolder {
        TextView name;
    }
}
