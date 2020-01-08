package com.sanleng.sl.fireemergency.mvp.presenter.contract;

import java.util.List;

public interface WaterSystemContract {

    void WaterSystemSuccess(List<com.sanleng.sl.fireemergency.mvp.bean.WaterSystem.PageBean.ListBean> list, int size);
    void WaterSystemNumberSuccess(int hyrant, int eqt, int water);
    void WaterSystemFailed();
    void Timeout();
}
