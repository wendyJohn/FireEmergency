package com.sanleng.sl.fireemergency.mvp.ui.alarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.bean.Alarm;

import java.util.List;

/**
 * 火警通知数据适配器
 *
 * @author QiaoShi
 */
public class AlarmsAdapter extends BaseAdapter {

    private List<Alarm> mList;
    private Context mContext;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<Alarm> mList) {
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

    @SuppressWarnings("deprecation")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.alarms_item, null);
            holder = new Holder();
            holder.name = convertView.findViewById(R.id.name);
            holder.describe = convertView.findViewById(R.id.describe);
            holder.time = convertView.findViewById(R.id.time);
            holder.yxicon = convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.describe.setText(mList.get(position).getAddress());
        holder.time.setText(mList.get(position).getType());
        String alarm_type = mList.get(position).getFormat();
        if (alarm_type.equals("1")) {
            holder.name.setText(mList.get(position).getName() + "发生火警");
            holder.yxicon.setBackground(mContext.getResources().getDrawable(R.drawable.alarm));
        }
        if (alarm_type.equals("0")) {
            holder.name.setText(mList.get(position).getName() + "火警消除");
            holder.yxicon.setBackground(mContext.getResources().getDrawable(R.drawable.alarms));
        }
        return convertView;
    }

    class Holder {
        TextView name;
        TextView describe;
        TextView time;
        ImageView yxicon;
    }
}
