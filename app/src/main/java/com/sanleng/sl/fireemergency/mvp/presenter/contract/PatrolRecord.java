package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.PatrolRecordBean;

import java.util.List;

public interface PatrolRecord {
    void PatrolRecordSuccess(List< PatrolRecordBean.DataBean.ListBean> list, int size);
    void PatrolRecordFailed();
    void Timeout();
}
