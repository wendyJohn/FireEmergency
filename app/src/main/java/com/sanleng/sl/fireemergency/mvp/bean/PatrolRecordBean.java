package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class PatrolRecordBean {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":2,"nextPage":-1,"list":[{"inspection_time":"2020-01-03 13:42:32","districtnum":"","inspectionpoint_code":"30202616fc2111e7bcbb80fa5b35167a","equipment_name":"灭火器","equipment_status":"0","inspection_imgs":"jssl.oss-cn-beijing.aliyuncs.com/a0d866eec7264c57b79c27f8d7f6be88.JPEG","inspection_info":"","qrcode_code":"20170323051850367_10071","patrolunitname":"南京交通职业学院","item_num":"","ids":"2e5d5369686032fcb452fa12b9370050","unit_code":"0c7f1aba9d15449ab65bf46b51ccdb87","create_user":"南交院"},{"inspection_time":"2020-01-03 13:37:43","districtnum":"","inspectionpoint_code":"30202616fc2111e7bcbb80fa5b35167a","equipment_name":"灭火器","equipment_status":"0","inspection_imgs":"jssl.oss-cn-beijing.aliyuncs.com/9416238dfbc64ab1b9f3cb0c579a0d40.JPEG","inspection_info":"","qrcode_code":"20170323051850367_10071","patrolunitname":"南京交通职业学院","item_num":"","ids":"c1e8f63a4deb38a0a1aedb3536b004a6","unit_code":"0c7f1aba9d15449ab65bf46b51ccdb87","create_user":"南交院"}]}
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
         * total : 2
         * nextPage : -1
         * list : [{"inspection_time":"2020-01-03 13:42:32","districtnum":"","inspectionpoint_code":"30202616fc2111e7bcbb80fa5b35167a","equipment_name":"灭火器","equipment_status":"0","inspection_imgs":"jssl.oss-cn-beijing.aliyuncs.com/a0d866eec7264c57b79c27f8d7f6be88.JPEG","inspection_info":"","qrcode_code":"20170323051850367_10071","patrolunitname":"南京交通职业学院","item_num":"","ids":"2e5d5369686032fcb452fa12b9370050","unit_code":"0c7f1aba9d15449ab65bf46b51ccdb87","create_user":"南交院"},{"inspection_time":"2020-01-03 13:37:43","districtnum":"","inspectionpoint_code":"30202616fc2111e7bcbb80fa5b35167a","equipment_name":"灭火器","equipment_status":"0","inspection_imgs":"jssl.oss-cn-beijing.aliyuncs.com/9416238dfbc64ab1b9f3cb0c579a0d40.JPEG","inspection_info":"","qrcode_code":"20170323051850367_10071","patrolunitname":"南京交通职业学院","item_num":"","ids":"c1e8f63a4deb38a0a1aedb3536b004a6","unit_code":"0c7f1aba9d15449ab65bf46b51ccdb87","create_user":"南交院"}]
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
             * inspection_time : 2020-01-03 13:42:32
             * districtnum :
             * inspectionpoint_code : 30202616fc2111e7bcbb80fa5b35167a
             * equipment_name : 灭火器
             * equipment_status : 0
             * inspection_imgs : jssl.oss-cn-beijing.aliyuncs.com/a0d866eec7264c57b79c27f8d7f6be88.JPEG
             * inspection_info :
             * qrcode_code : 20170323051850367_10071
             * patrolunitname : 南京交通职业学院
             * item_num :
             * ids : 2e5d5369686032fcb452fa12b9370050
             * unit_code : 0c7f1aba9d15449ab65bf46b51ccdb87
             * create_user : 南交院
             */

            private String inspection_time;
            private String districtnum;
            private String inspectionpoint_code;
            private String equipment_name;
            private String equipment_status;
            private String inspection_imgs;
            private String inspection_info;
            private String qrcode_code;
            private String patrolunitname;
            private String item_num;
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

            public String getEquipment_name() {
                return equipment_name;
            }

            public void setEquipment_name(String equipment_name) {
                this.equipment_name = equipment_name;
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
