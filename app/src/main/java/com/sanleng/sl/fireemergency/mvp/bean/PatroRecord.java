package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class PatroRecord {


    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":42,"nextPage":2,"list":[{"inspection_time":"2018-04-18 13:19:39","districtnum":"","inspectionpoint_code":"30f7a3ebfc2111e7bcbb80fa5b35167a","equipment_status":"0","inspection_imgs":"/RootFile/FirePatrol/20180418/1524028768168.png,","inspection_info":"aaaa","qrcode_code":"20170323051856631_10003","patrolunitname":"","item_num":"","bind_taskid":"","inspectionpoint_name":"防火门","ids":"04851f41a1514d1b93f8f0ad0d73fd4a","unit_code":"00e31db02e024a05bfe8f91bf79d2be7","create_user":"hailiang"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public static class DataBean {
        /**
         * total : 42
         * nextPage : 2
         * list : [{"inspection_time":"2018-04-18 13:19:39","districtnum":"","inspectionpoint_code":"30f7a3ebfc2111e7bcbb80fa5b35167a","equipment_status":"0","inspection_imgs":"/RootFile/FirePatrol/20180418/1524028768168.png,","inspection_info":"aaaa","qrcode_code":"20170323051856631_10003","patrolunitname":"","item_num":"","bind_taskid":"","inspectionpoint_name":"防火门","ids":"04851f41a1514d1b93f8f0ad0d73fd4a","unit_code":"00e31db02e024a05bfe8f91bf79d2be7","create_user":"hailiang"}]
         */

        private int total;
        private int nextPage;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * inspection_time : 2018-04-18 13:19:39
             * districtnum :
             * inspectionpoint_code : 30f7a3ebfc2111e7bcbb80fa5b35167a
             * equipment_status : 0
             * inspection_imgs : /RootFile/FirePatrol/20180418/1524028768168.png,
             * inspection_info : aaaa
             * qrcode_code : 20170323051856631_10003
             * patrolunitname :
             * item_num :
             * bind_taskid :
             * inspectionpoint_name : 防火门
             * ids : 04851f41a1514d1b93f8f0ad0d73fd4a
             * unit_code : 00e31db02e024a05bfe8f91bf79d2be7
             * create_user : hailiang
             */

            private String inspection_time;
            private String districtnum;
            private String inspectionpoint_code;
            private String equipment_status;
            private String inspection_imgs;
            private String inspection_info;
            private String qrcode_code;
            private String patrolunitname;
            private String item_num;
            private String bind_taskid;
            private String inspectionpoint_name;
            private String ids;
            private String unit_code;
            private String create_user;

            public String getInspection_time() {
                return inspection_time;
            }

            public void setInspection_time(String inspection_time) {
                this.inspection_time = inspection_time;
            }

            public String getDistrictnum() {
                return districtnum;
            }

            public void setDistrictnum(String districtnum) {
                this.districtnum = districtnum;
            }

            public String getInspectionpoint_code() {
                return inspectionpoint_code;
            }

            public void setInspectionpoint_code(String inspectionpoint_code) {
                this.inspectionpoint_code = inspectionpoint_code;
            }

            public String getEquipment_status() {
                return equipment_status;
            }

            public void setEquipment_status(String equipment_status) {
                this.equipment_status = equipment_status;
            }

            public String getInspection_imgs() {
                return inspection_imgs;
            }

            public void setInspection_imgs(String inspection_imgs) {
                this.inspection_imgs = inspection_imgs;
            }

            public String getInspection_info() {
                return inspection_info;
            }

            public void setInspection_info(String inspection_info) {
                this.inspection_info = inspection_info;
            }

            public String getQrcode_code() {
                return qrcode_code;
            }

            public void setQrcode_code(String qrcode_code) {
                this.qrcode_code = qrcode_code;
            }

            public String getPatrolunitname() {
                return patrolunitname;
            }

            public void setPatrolunitname(String patrolunitname) {
                this.patrolunitname = patrolunitname;
            }

            public String getItem_num() {
                return item_num;
            }

            public void setItem_num(String item_num) {
                this.item_num = item_num;
            }

            public String getBind_taskid() {
                return bind_taskid;
            }

            public void setBind_taskid(String bind_taskid) {
                this.bind_taskid = bind_taskid;
            }

            public String getInspectionpoint_name() {
                return inspectionpoint_name;
            }

            public void setInspectionpoint_name(String inspectionpoint_name) {
                this.inspectionpoint_name = inspectionpoint_name;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getUnit_code() {
                return unit_code;
            }

            public void setUnit_code(String unit_code) {
                this.unit_code = unit_code;
            }

            public String getCreate_user() {
                return create_user;
            }

            public void setCreate_user(String create_user) {
                this.create_user = create_user;
            }
        }
    }
}
