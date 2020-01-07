package com.sanleng.sl.fireemergency.mvp.ui.hiddendanger.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;

import java.util.List;

/**
 * 待整改数据适配器
 *
 * @author QiaoShi
 */
@SuppressLint("ResourceAsColor")
public class SRectificationAdapter extends BaseAdapter {

    private List<RectificationBean.DataBean.ListBean> mList;
    private Context mContext;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<RectificationBean.DataBean.ListBean> mList) {
        this.mList = mList;
        this.mContext = mContext;

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

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.stayrrectification_item, null);
            holder = new Holder();
            holder.labelnumber = convertView.findViewById(R.id.labelnumber);
            holder.devicename = convertView.findViewById(R.id.devicename);
            holder.deviceposition = convertView.findViewById(R.id.deviceposition);
            holder.term = convertView.findViewById(R.id.term);
            holder.reorganizer = convertView.findViewById(R.id.reorganizer);
            holder.describe = convertView.findViewById(R.id.describe);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.labelnumber.setText(mList.get(position).getQrcode());
        holder.devicename.setText(mList.get(position).getFpEquipmentName());
        holder.deviceposition.setText(mList.get(position).getOwnedBuildingName());
        holder.term.setText(mList.get(position).getProcessingPeriod());
        holder.reorganizer.setText(mList.get(position).getCreate_user());
        holder.describe.setText(mList.get(position).getDescription());

        return convertView;
    }

    class Holder {
        TextView labelnumber;
        TextView devicename;
        TextView deviceposition;
        TextView term;
        TextView reorganizer;
        TextView describe;
    }
}
