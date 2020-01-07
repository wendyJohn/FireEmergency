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
import com.sanleng.sl.fireemergency.mvp.bean.PatrolRecordBean;

import java.util.List;

/**
 * 巡查任务记录数据适配器
 *
 * @author QiaoShi
 *
 */
@SuppressLint("ResourceAsColor")
public class PatrolRecordAdapter extends BaseAdapter {

	private List< PatrolRecordBean.DataBean.ListBean> mList;
	private Context mContext;

	/**
	 * bindData用来传递数据给适配器。
	 *
	 * @list
	 */
	public void bindData(Context mContext, List< PatrolRecordBean.DataBean.ListBean> mList) {
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

	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.patrolrecod_item, null);

			holder = new Holder();
			holder.device = convertView.findViewById(R.id.device);
			holder.name =  convertView.findViewById(R.id.name);
			holder.time =  convertView.findViewById(R.id.time);
			holder.p_state = convertView.findViewById(R.id.p_state);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.device.setText(mList.get(position).getEquipment_name());
		holder.name.setText(mList.get(position).getCreate_user());
		holder.time.setText(mList.get(position).getInspection_time());

		String State = mList.get(position).getEquipment_status();
		if (State.equals("0")) {
			holder.p_state.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.p_normal));
		}
		if (State.equals("2")) {
			holder.p_state.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.p_lose));
		}
		if (State.equals("1")) {
			holder.p_state.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.p_fault));
		}

		return convertView;
	}

	class Holder {
		TextView device;
		TextView name;
		TextView time;
		ImageView p_state;

	}
}
