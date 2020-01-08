package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItems;

import java.util.List;

public interface RealItemsContract {
    void RealTimeDataFailed();
    void RealItemSuccess(List<ReadTimeItems.PageBean.ListBean> list_temperature, List<ReadTimeItems.PageBean.ListBean> list_current, List<ReadTimeItems.PageBean.ListBean> list_residualcurrent, List<ReadTimeItems.PageBean.ListBean> list_voltage, String buildids, String floorids);
    void Timeout();
}
