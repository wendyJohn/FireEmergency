package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.ARectBean;

import java.util.List;

public interface AiddendContract {

    void Success(List<ARectBean.DataBean.ListBean> list, int size, String scope);
    void Failed();

}
