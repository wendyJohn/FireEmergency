package com.sanleng.sl.fireemergency.mvp.presenter.contract;


import com.sanleng.sl.fireemergency.mvp.bean.Gas;

import java.util.List;

public interface GasContract {

    void GasSuccess(List<Gas.PageBean.ListBean> list, int size);


    void GasFailed();
}
