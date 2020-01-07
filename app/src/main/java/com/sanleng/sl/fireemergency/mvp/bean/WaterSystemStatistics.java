package com.sanleng.sl.fireemergency.mvp.bean;

public class WaterSystemStatistics {

    /**
     * msg : 首页统计查询成功
     * code : 0
     * data : {"hyrant":2,"eqt":0,"water":0}
     * state : ok
     */

    private String msg;
    private String code;
    private DataBean data;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * hyrant : 2
         * eqt : 0
         * water : 0
         */

        private int hyrant;
        private int eqt;
        private int water;

        public int getHyrant() {
            return hyrant;
        }

        public void setHyrant(int hyrant) {
            this.hyrant = hyrant;
        }

        public int getEqt() {
            return eqt;
        }

        public void setEqt(int eqt) {
            this.eqt = eqt;
        }

        public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
        }
    }
}
