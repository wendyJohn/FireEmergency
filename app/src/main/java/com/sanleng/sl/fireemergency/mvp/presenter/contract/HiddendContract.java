package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.ARectBean;
import com.sanleng.sl.fireemergency.mvp.bean.RectificationBean;

import java.util.List;

public interface HiddendContract {

    void Success(List<RectificationBean.DataBean.ListBean> list, int size, String scope);
    void Failed();

}
