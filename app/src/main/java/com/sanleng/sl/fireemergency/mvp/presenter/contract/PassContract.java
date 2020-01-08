package com.sanleng.sl.fireemergency.mvp.presenter.contract;


public interface PassContract {

    void Success(String msg);
    void Failed();
    void Timeout();
}
