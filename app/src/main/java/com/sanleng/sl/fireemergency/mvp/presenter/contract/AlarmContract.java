package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.Alarm;

import java.util.List;

public interface AlarmContract {
    void FireAlarmSuccess(List<Alarm> list, int size);
    void FireAlarmFailed();
    void Timeout();
}
