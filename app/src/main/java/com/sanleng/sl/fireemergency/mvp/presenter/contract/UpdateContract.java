package com.sanleng.sl.fireemergency.mvp.presenter.contract;

public interface UpdateContract {
    void UpdateSuccess(String version, String path, String appDescribe);
    void UpdateFailed();
}
