package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.RealDataBean;

import java.util.List;

public interface RealTimeData {

    void RealTimeDataSuccess(List<RealDataBean> list, int size);
    void RealTimeDataFailed();
    void Timeout();
}
