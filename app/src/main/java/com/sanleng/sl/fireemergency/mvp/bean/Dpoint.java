package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class Dpoint {
    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":3,"nextPage":-1,"list":[{"ids":"dd82c568a6f03f6d52ef155b6a8ca36b","unitCode":"0c7f1aba9d15449ab65bf46b51ccdb87","unitName":null,"equipmentName":"点位2","equipmentType":"mass_gateway","subSystem":null,"buyTime":null,"maintenanceTime":null,"createUser":"南交院","ownedBuilding":"359c1f83d4244062ad762043a3522400","floorNumber":"ec5c9c4a8f074815abe4399242fd9eb3","positionAddr":"1","qrcodeCode":"815D4DF2C56604","garphicCode":null,"coordinate":null,"districtNum":null,"itemNum":null,"lat":"","lng":"","createTime":null,"proLineCode":"check_rule_sprinkler_sys","lastStatus":"0","activateStatus":"激活","lastStatusNames":"正常","warmStatus":"black","inspectionTime":"2020-01-07T05:42:16.000+0000","patrolrecord_id":null,"ownedBuildingVal":"学生宿舍楼B栋","floorNumberVal":"5层","fireControl":null,"patroldays":0,"bindTaskid":null}]}
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
         * list : [{"ids":"dd82c568a6f03f6d52ef155b6a8ca36b","unitCode":"0c7f1aba9d15449ab65bf46b51ccdb87","unitName":null,"equipmentName":"点位2","equipmentType":"mass_gateway","subSystem":null,"buyTime":null,"maintenanceTime":null,"createUser":"南交院","ownedBuilding":"359c1f83d4244062ad762043a3522400","floorNumber":"ec5c9c4a8f074815abe4399242fd9eb3","positionAddr":"1","qrcodeCode":"815D4DF2C56604","garphicCode":null,"coordinate":null,"districtNum":null,"itemNum":null,"lat":"","lng":"","createTime":null,"proLineCode":"check_rule_sprinkler_sys","lastStatus":"0","activateStatus":"激活","lastStatusNames":"正常","warmStatus":"black","inspectionTime":"2020-01-07T05:42:16.000+0000","patrolrecord_id":null,"ownedBuildingVal":"学生宿舍楼B栋","floorNumberVal":"5层","fireControl":null,"patroldays":0,"bindTaskid":null}]
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
             * ids : dd82c568a6f03f6d52ef155b6a8ca36b
             * unitCode : 0c7f1aba9d15449ab65bf46b51ccdb87
             * unitName : null
             * equipmentName : 点位2
             * equipmentType : mass_gateway
             * subSystem : null
             * buyTime : null
             * maintenanceTime : null
             * createUser : 南交院
             * ownedBuilding : 359c1f83d4244062ad762043a3522400
             * floorNumber : ec5c9c4a8f074815abe4399242fd9eb3
             * positionAddr : 1
             * qrcodeCode : 815D4DF2C56604
             * garphicCode : null
             * coordinate : null
             * districtNum : null
             * itemNum : null
             * lat :
             * lng :
             * createTime : null
             * proLineCode : check_rule_sprinkler_sys
             * lastStatus : 0
             * activateStatus : 激活
             * lastStatusNames : 正常
             * warmStatus : black
             * inspectionTime : 2020-01-07T05:42:16.000+0000
             * patrolrecord_id : null
             * ownedBuildingVal : 学生宿舍楼B栋
             * floorNumberVal : 5层
             * fireControl : null
             * patroldays : 0
             * bindTaskid : null
             */

            private String ids;
            private String unitCode;
            private Object unitName;
            private String equipmentName;
            private String equipmentType;
            private Object subSystem;
            private Object buyTime;
            private Object maintenanceTime;
            private String createUser;
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
            private String warmStatus;
            private String inspectionTime;
            private Object patrolrecord_id;
            private String ownedBuildingVal;
            private String floorNumberVal;
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

            public Object getUnitName() {
                return unitName;
            }

            public void setUnitName(Object unitName) {
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

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
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

            public String getWarmStatus() {
                return warmStatus;
            }

            public void setWarmStatus(String warmStatus) {
                this.warmStatus = warmStatus;
            }

            public String getInspectionTime() {
                return inspectionTime;
            }

            public void setInspectionTime(String inspectionTime) {
                this.inspectionTime = inspectionTime;
            }

            public Object getPatrolrecord_id() {
                return patrolrecord_id;
            }

            public void setPatrolrecord_id(Object patrolrecord_id) {
                this.patrolrecord_id = patrolrecord_id;
            }

            public String getOwnedBuildingVal() {
                return ownedBuildingVal;
            }

            public void setOwnedBuildingVal(String ownedBuildingVal) {
                this.ownedBuildingVal = ownedBuildingVal;
            }

            public String getFloorNumberVal() {
                return floorNumberVal;
            }

            public void setFloorNumberVal(String floorNumberVal) {
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
