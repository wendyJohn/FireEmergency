package com.sanleng.sl.fireemergency.mvp.ui.patrol.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanleng.sl.fireemergency.R;
import com.sanleng.sl.fireemergency.mvp.bean.Dpoint;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;

import java.util.List;

/**
 * 点位数据适配器
 *
 * @author QiaoShi
 */
@SuppressLint("ResourceAsColor")
public class DevicePointAdapter extends BaseAdapter {

    private List<Dpoint.DataBean.ListBean> mList;
    private Context mContext;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<Dpoint.DataBean.ListBean> mList) {
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

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.devicepoint_item, null);
            holder = new Holder();
            holder.equipmentName = convertView.findViewById(R.id.equipmentName);
            holder.position = convertView.findViewById(R.id.position);
            holder.state = convertView.findViewById(R.id.state);
            holder.point_bag = convertView.findViewById(R.id.point_bag);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.equipmentName.setText("点位名称: " + mList.get(position).getEquipmentName());
        holder.position.setText("点位位置: " + mList.get(position).getOwnedBuildingVal() + "-" + mList.get(position).getFloorNumberVal());
        holder.state.setText("点位状态: " + mList.get(position).getActivateStatus());

        if (mList.get(position).getActivateStatus().equals("激活")) {
            holder.point_bag.setBackground(mContext.getResources().getDrawable(R.mipmap.nfc_icon));
            holder.equipmentName.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.position.setTextColor(mContext.getResources().getColor(R.color.black));
            holder.state.setTextColor(mContext.getResources().getColor(R.color.black));
        }

        if (mList.get(position).getActivateStatus().equals("未激活")) {
            holder.point_bag.setBackground(mContext.getResources().getDrawable(R.mipmap.nfcs_icon));
            holder.equipmentName.setTextColor(mContext.getResources().getColor(R.color.gray));
            holder.position.setTextColor(mContext.getResources().getColor(R.color.gray));
            holder.state.setTextColor(mContext.getResources().getColor(R.color.gray));
        }
        return convertView;
    }

    class Holder {
        ImageView point_bag;
        TextView equipmentName;
        TextView position;
        TextView state;
    }
}
