package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.ReadTimeItemData;
import com.sanleng.sl.fireemergency.mvp.bean.RealDataBean;

import java.util.List;

public interface RealTimeData {

    void RealTimeDataSuccess(List<RealDataBean> list, int size);
    void RealTimeDatasSuccess(List<RealDataBean> list, int size);
    void RealDataItemSuccess(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage, String buildids, String floorids, String electricalDetectorInfos);

    void RealTimeDataFailed();

}
