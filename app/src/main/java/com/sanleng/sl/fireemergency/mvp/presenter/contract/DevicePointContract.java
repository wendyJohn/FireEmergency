package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.Dpoint;

import java.util.List;

public interface DevicePointContract {

    void Success(List<Dpoint.DataBean.ListBean> list, int size);
    void Failed();
    void Timeout();
}
