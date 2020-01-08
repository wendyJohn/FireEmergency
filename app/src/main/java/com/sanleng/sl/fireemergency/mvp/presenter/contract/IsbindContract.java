package com.sanleng.sl.fireemergency.mvp.presenter.contract;


public interface IsbindContract {
    void Success(String qrcodeCode,String equipmentName,String equipmentposition,String equipmentids);
    void Failed();
    void Timeout();
}
