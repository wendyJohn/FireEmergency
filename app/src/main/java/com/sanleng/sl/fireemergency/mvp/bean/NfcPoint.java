package com.sanleng.sl.fireemergency.mvp.bean;

public class NfcPoint {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"equipment":{"ids":"7fd4b68dad699bdfc3c23d54d8fbf49e","unitCode":"0c7f1aba9d15449ab65bf46b51ccdb87","unitName":"南京交通职业学院","equipmentName":"测试设备","equipmentType":"mass_gateway","subSystem":null,"buyTime":null,"maintenanceTime":null,"createUser":null,"ownedBuilding":"培训东楼","floorNumber":"5层","positionAddr":"1234","qrcodeCode":"805D4DF20BF704","garphicCode":null,"coordinate":null,"districtNum":null,"itemNum":null,"lat":"","lng":"","createTime":null,"proLineCode":"check_rule_fire_warter_faclilities","lastStatus":"0","activateStatus":"激活","lastStatusNames":"正常","warmStatus":null,"inspectionTime":null,"patrolrecord_id":null,"ownedBuildingVal":null,"floorNumberVal":null,"fireControl":null,"patroldays":0,"bindTaskid":null},"state":"1","message":"qrcode已被绑定"}
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
         * equipment : {"ids":"7fd4b68dad699bdfc3c23d54d8fbf49e","unitCode":"0c7f1aba9d15449ab65bf46b51ccdb87","unitName":"南京交通职业学院","equipmentName":"测试设备","equipmentType":"mass_gateway","subSystem":null,"buyTime":null,"maintenanceTime":null,"createUser":null,"ownedBuilding":"培训东楼","floorNumber":"5层","positionAddr":"1234","qrcodeCode":"805D4DF20BF704","garphicCode":null,"coordinate":null,"districtNum":null,"itemNum":null,"lat":"","lng":"","createTime":null,"proLineCode":"check_rule_fire_warter_faclilities","lastStatus":"0","activateStatus":"激活","lastStatusNames":"正常","warmStatus":null,"inspectionTime":null,"patrolrecord_id":null,"ownedBuildingVal":null,"floorNumberVal":null,"fireControl":null,"patroldays":0,"bindTaskid":null}
         * state : 1
         * message : qrcode已被绑定
         */

        private EquipmentBean equipment;
        private String state;
        private String message;

        public EquipmentBean getEquipment() {
            return equipment;
        }

        public void setEquipment(EquipmentBean equipment) {
            this.equipment = equipment;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public static class EquipmentBean {
            /**
             * ids : 7fd4b68dad699bdfc3c23d54d8fbf49e
             * unitCode : 0c7f1aba9d15449ab65bf46b51ccdb87
             * unitName : 南京交通职业学院
             * equipmentName : 测试设备
             * equipmentType : mass_gateway
             * subSystem : null
             * buyTime : null
             * maintenanceTime : null
             * createUser : null
             * ownedBuilding : 培训东楼
             * floorNumber : 5层
             * positionAddr : 1234
             * qrcodeCode : 805D4DF20BF704
             * garphicCode : null
             * coordinate : null
             * districtNum : null
             * itemNum : null
             * lat :
             * lng :
             * createTime : null
             * proLineCode : check_rule_fire_warter_faclilities
             * lastStatus : 0
             * activateStatus : 激活
             * lastStatusNames : 正常
             * warmStatus : null
             * inspectionTime : null
             * patrolrecord_id : null
             * ownedBuildingVal : null
             * floorNumberVal : null
             * fireControl : null
             * patroldays : 0
             * bindTaskid : null
             */

            private String ids;
            private String unitCode;
            private String unitName;
            private String equipmentName;
            private String equipmentType;
            private Object subSystem;
            private Object buyTime;
            private Object maintenanceTime;
            private Object createUser;
            private String ownedBuilding;
            private String floorNumber;
            private String positionAddr;
            private String qrcodeCode;
            private Object garphicCode;
            private Object coordinate;
            private Object districtNum;
            private Object itemNum;
            private String lat;
            private String lng;
            private Object createTime;
            private String proLineCode;
            private String lastStatus;
            private String activateStatus;
            private String lastStatusNames;
            private Object warmStatus;
            private Object inspectionTime;
            private Object patrolrecord_id;
            private Object ownedBuildingVal;
            private Object floorNumberVal;
            private Object fireControl;
            private int patroldays;
            private Object bindTaskid;

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getUnitCode() {
                return unitCode;
            }

            public void setUnitCode(String unitCode) {
                this.unitCode = unitCode;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getEquipmentName() {
                return equipmentName;
            }

            public void setEquipmentName(String equipmentName) {
                this.equipmentName = equipmentName;
            }

            public String getEquipmentType() {
                return equipmentType;
            }

            public void setEquipmentType(String equipmentType) {
                this.equipmentType = equipmentType;
            }

            public Object getSubSystem() {
                return subSystem;
            }

            public void setSubSystem(Object subSystem) {
                this.subSystem = subSystem;
            }

            public Object getBuyTime() {
                return buyTime;
            }

            public void setBuyTime(Object buyTime) {
                this.buyTime = buyTime;
            }

            public Object getMaintenanceTime() {
                return maintenanceTime;
            }

            public void setMaintenanceTime(Object maintenanceTime) {
                this.maintenanceTime = maintenanceTime;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public String getOwnedBuilding() {
                return ownedBuilding;
            }

            public void setOwnedBuilding(String ownedBuilding) {
                this.ownedBuilding = ownedBuilding;
            }

            public String getFloorNumber() {
                return floorNumber;
            }

            public void setFloorNumber(String floorNumber) {
                this.floorNumber = floorNumber;
            }

            public String getPositionAddr() {
                return positionAddr;
            }

            public void setPositionAddr(String positionAddr) {
                this.positionAddr = positionAddr;
            }

            public String getQrcodeCode() {
                return qrcodeCode;
            }

            public void setQrcodeCode(String qrcodeCode) {
                this.qrcodeCode = qrcodeCode;
            }

            public Object getGarphicCode() {
                return garphicCode;
            }

            public void setGarphicCode(Object garphicCode) {
                this.garphicCode = garphicCode;
            }

            public Object getCoordinate() {
                return coordinate;
            }

            public void setCoordinate(Object coordinate) {
                this.coordinate = coordinate;
            }

            public Object getDistrictNum() {
                return districtNum;
            }

            public void setDistrictNum(Object districtNum) {
                this.districtNum = districtNum;
            }

            public Object getItemNum() {
                return itemNum;
            }

            public void setItemNum(Object itemNum) {
                this.itemNum = itemNum;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public String getProLineCode() {
                return proLineCode;
            }

            public void setProLineCode(String proLineCode) {
                this.proLineCode = proLineCode;
            }

            public String getLastStatus() {
                return lastStatus;
            }

            public void setLastStatus(String lastStatus) {
                this.lastStatus = lastStatus;
            }

            public String getActivateStatus() {
                return activateStatus;
            }

            public void setActivateStatus(String activateStatus) {
                this.activateStatus = activateStatus;
            }

            public String getLastStatusNames() {
                return lastStatusNames;
            }

            public void setLastStatusNames(String lastStatusNames) {
                this.lastStatusNames = lastStatusNames;
            }

            public Object getWarmStatus() {
                return warmStatus;
            }

            public void setWarmStatus(Object warmStatus) {
                this.warmStatus = warmStatus;
            }

            public Object getInspectionTime() {
                return inspectionTime;
            }

            public void setInspectionTime(Object inspectionTime) {
                this.inspectionTime = inspectionTime;
            }

            public Object getPatrolrecord_id() {
                return patrolrecord_id;
            }

            public void setPatrolrecord_id(Object patrolrecord_id) {
                this.patrolrecord_id = patrolrecord_id;
            }

            public Object getOwnedBuildingVal() {
                return ownedBuildingVal;
            }

            public void setOwnedBuildingVal(Object ownedBuildingVal) {
                this.ownedBuildingVal = ownedBuildingVal;
            }

            public Object getFloorNumberVal() {
                return floorNumberVal;
            }

            public void setFloorNumberVal(Object floorNumberVal) {
                this.floorNumberVal = floorNumberVal;
            }

            public Object getFireControl() {
                return fireControl;
            }

            public void setFireControl(Object fireControl) {
                this.fireControl = fireControl;
            }

            public int getPatroldays() {
                return patroldays;
            }

            public void setPatroldays(int patroldays) {
                this.patroldays = patroldays;
            }

            public Object getBindTaskid() {
                return bindTaskid;
            }

            public void setBindTaskid(Object bindTaskid) {
                this.bindTaskid = bindTaskid;
            }
        }
    }
}
