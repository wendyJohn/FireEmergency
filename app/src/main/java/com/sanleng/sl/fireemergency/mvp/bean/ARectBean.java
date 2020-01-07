package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class ARectBean {


    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":3,"nextPage":-1,"list":[{"ownedBuildingName":"培训东楼","equipment_status":"故障","picture_url":"jssl.oss-cn-beijing.aliyuncs.com/e48baf737a604fdb973609fbafb6038e.JPEG","positionAddr":"1234","responsible_person":"QQ","qrcode":"805D4DF20BF704","description":"好好说实话实说是不是时候都感到孤独","process_time":"2020-01-07T03:31:43.000+0000","patrolunitname":"南京交通职业学院","finished_url":"jssl.oss-cn-beijing.aliyuncs.com/86639ad826444600939e2fd699344486.JPEG","processTime":"2020-01-07 11:31:43","equipids":"7fd4b68dad699bdfc3c23d54d8fbf49e","expired":"未超期","floorNumber":"5层","state":"已整改","finished_description":"dddxxx","processingPeriod":"2020-01-07 00:00:00","finished_user":"南交院","create_time":"2020-01-06T19:30:53.000+0000","usertype":"unit_type_admin_owner","unit_name":"南京交通职业学院","processing_period":"2020-01-07","fpEquipmentName":"测试设备","ids":"1bf6d1179b0955e28a0c6c62579c93d7","create_user":"南交院"}]}
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
         * total : 3
         * nextPage : -1
         * list : [{"ownedBuildingName":"培训东楼","equipment_status":"故障","picture_url":"jssl.oss-cn-beijing.aliyuncs.com/e48baf737a604fdb973609fbafb6038e.JPEG","positionAddr":"1234","responsible_person":"QQ","qrcode":"805D4DF20BF704","description":"好好说实话实说是不是时候都感到孤独","process_time":"2020-01-07T03:31:43.000+0000","patrolunitname":"南京交通职业学院","finished_url":"jssl.oss-cn-beijing.aliyuncs.com/86639ad826444600939e2fd699344486.JPEG","processTime":"2020-01-07 11:31:43","equipids":"7fd4b68dad699bdfc3c23d54d8fbf49e","expired":"未超期","floorNumber":"5层","state":"已整改","finished_description":"dddxxx","processingPeriod":"2020-01-07 00:00:00","finished_user":"南交院","create_time":"2020-01-06T19:30:53.000+0000","usertype":"unit_type_admin_owner","unit_name":"南京交通职业学院","processing_period":"2020-01-07","fpEquipmentName":"测试设备","ids":"1bf6d1179b0955e28a0c6c62579c93d7","create_user":"南交院"}]
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
             * ownedBuildingName : 培训东楼
             * equipment_status : 故障
             * picture_url : jssl.oss-cn-beijing.aliyuncs.com/e48baf737a604fdb973609fbafb6038e.JPEG
             * positionAddr : 1234
             * responsible_person : QQ
             * qrcode : 805D4DF20BF704
             * description : 好好说实话实说是不是时候都感到孤独
             * process_time : 2020-01-07T03:31:43.000+0000
             * patrolunitname : 南京交通职业学院
             * finished_url : jssl.oss-cn-beijing.aliyuncs.com/86639ad826444600939e2fd699344486.JPEG
             * processTime : 2020-01-07 11:31:43
             * equipids : 7fd4b68dad699bdfc3c23d54d8fbf49e
             * expired : 未超期
             * floorNumber : 5层
             * state : 已整改
             * finished_description : dddxxx
             * processingPeriod : 2020-01-07 00:00:00
             * finished_user : 南交院
             * create_time : 2020-01-06T19:30:53.000+0000
             * usertype : unit_type_admin_owner
             * unit_name : 南京交通职业学院
             * processing_period : 2020-01-07
             * fpEquipmentName : 测试设备
             * ids : 1bf6d1179b0955e28a0c6c62579c93d7
             * create_user : 南交院
             */

            private String ownedBuildingName;
            private String equipment_status;
            private String picture_url;
            private String positionAddr;
            private String responsible_person;
            private String qrcode;
            private String description;
            private String process_time;
            private String patrolunitname;
            private String finished_url;
            private String processTime;
            private String equipids;
            private String expired;
            private String floorNumber;
            private String state;
            private String finished_description;
            private String processingPeriod;
            private String finished_user;
            private String create_time;
            private String usertype;
            private String unit_name;
            private String processing_period;
            private String fpEquipmentName;
            private String ids;
            private String create_user;

            public String getOwnedBuildingName() {
                return ownedBuildingName;
            }

            public void setOwnedBuildingName(String ownedBuildingName) {
                this.ownedBuildingName = ownedBuildingName;
            }

            public String getEquipment_status() {
                return equipment_status;
            }

            public void setEquipment_status(String equipment_status) {
                this.equipment_status = equipment_status;
            }

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public String getPositionAddr() {
                return positionAddr;
            }

            public void setPositionAddr(String positionAddr) {
                this.positionAddr = positionAddr;
            }

            public String getResponsible_person() {
                return responsible_person;
            }

            public void setResponsible_person(String responsible_person) {
                this.responsible_person = responsible_person;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getProcess_time() {
                return process_time;
            }

            public void setProcess_time(String process_time) {
                this.process_time = process_time;
            }

            public String getPatrolunitname() {
                return patrolunitname;
            }

            public void setPatrolunitname(String patrolunitname) {
                this.patrolunitname = patrolunitname;
            }

            public String getFinished_url() {
                return finished_url;
            }

            public void setFinished_url(String finished_url) {
                this.finished_url = finished_url;
            }

            public String getProcessTime() {
                return processTime;
            }

            public void setProcessTime(String processTime) {
                this.processTime = processTime;
            }

            public String getEquipids() {
                return equipids;
            }

            public void setEquipids(String equipids) {
                this.equipids = equipids;
            }

            public String getExpired() {
                return expired;
            }

            public void setExpired(String expired) {
                this.expired = expired;
            }

            public String getFloorNumber() {
                return floorNumber;
            }

            public void setFloorNumber(String floorNumber) {
                this.floorNumber = floorNumber;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getFinished_description() {
                return finished_description;
            }

            public void setFinished_description(String finished_description) {
                this.finished_description = finished_description;
            }

            public String getProcessingPeriod() {
                return processingPeriod;
            }

            public void setProcessingPeriod(String processingPeriod) {
                this.processingPeriod = processingPeriod;
            }

            public String getFinished_user() {
                return finished_user;
            }

            public void setFinished_user(String finished_user) {
                this.finished_user = finished_user;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public String getProcessing_period() {
                return processing_period;
            }

            public void setProcessing_period(String processing_period) {
                this.processing_period = processing_period;
            }

            public String getFpEquipmentName() {
                return fpEquipmentName;
            }

            public void setFpEquipmentName(String fpEquipmentName) {
                this.fpEquipmentName = fpEquipmentName;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
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
