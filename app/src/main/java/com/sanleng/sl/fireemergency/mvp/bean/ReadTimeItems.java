package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class ReadTimeItems {

    /**
     * state : ok
     * page : {"totalCount":11,"pageSize":1,"totalPage":11,"currPage":1,"nextPage":2,"list":[{"detector_id":"0447f4aa5b6d11e98fa300163e047d1c","device_name":"南交院配电箱","detector_name":"temperature_detector","detector_model":"2","build_name":"悠谷4号楼","floor_name":null,"buildids":"ba58c5f730da48bb8e717da0dee187c6","floorids":"483295e77d254d6aaaf199f50d8cadf9","contact_tel":"13961769562","contact_name":"周俐星","mac":"ffffff100000efbf","detector_address_number":"2","detector_port":"akwd","upper_limit":"","lower_limit":"","current_value":"12.3","state":"0"}]}
     * statecode : PAGE_DATA_SUCCESS
     * message : 分页数据获取成功
     */

    private String state;
    private PageBean page;
    private String statecode;
    private String message;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class PageBean {
        /**
         * totalCount : 11
         * pageSize : 1
         * totalPage : 11
         * currPage : 1
         * nextPage : 2
         * list : [{"detector_id":"0447f4aa5b6d11e98fa300163e047d1c","device_name":"南交院配电箱","detector_name":"temperature_detector","detector_model":"2","build_name":"悠谷4号楼","floor_name":null,"buildids":"ba58c5f730da48bb8e717da0dee187c6","floorids":"483295e77d254d6aaaf199f50d8cadf9","contact_tel":"13961769562","contact_name":"周俐星","mac":"ffffff100000efbf","detector_address_number":"2","detector_port":"akwd","upper_limit":"","lower_limit":"","current_value":"12.3","state":"0"}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private int nextPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
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
             * detector_id : 0447f4aa5b6d11e98fa300163e047d1c
             * device_name : 南交院配电箱
             * detector_name : temperature_detector
             * detector_model : 2
             * build_name : 悠谷4号楼
             * floor_name : null
             * buildids : ba58c5f730da48bb8e717da0dee187c6
             * floorids : 483295e77d254d6aaaf199f50d8cadf9
             * contact_tel : 13961769562
             * contact_name : 周俐星
             * mac : ffffff100000efbf
             * detector_address_number : 2
             * detector_port : akwd
             * upper_limit :
             * lower_limit :
             * current_value : 12.3
             * state : 0
             */

            private String detector_id;
            private String device_name;
            private String detector_name;
            private String detector_model;
            private String build_name;
            private Object floor_name;
            private String buildids;
            private String floorids;
            private String contact_tel;
            private String contact_name;
            private String mac;
            private String detector_address_number;
            private String detector_port;
            private String upper_limit;
            private String lower_limit;
            private String current_value;
            private String state;

            public String getDetector_id() {
                return detector_id;
            }

            public void setDetector_id(String detector_id) {
                this.detector_id = detector_id;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getDetector_name() {
                return detector_name;
            }

            public void setDetector_name(String detector_name) {
                this.detector_name = detector_name;
            }

            public String getDetector_model() {
                return detector_model;
            }

            public void setDetector_model(String detector_model) {
                this.detector_model = detector_model;
            }

            public String getBuild_name() {
                return build_name;
            }

            public void setBuild_name(String build_name) {
                this.build_name = build_name;
            }

            public Object getFloor_name() {
                return floor_name;
            }

            public void setFloor_name(Object floor_name) {
                this.floor_name = floor_name;
            }

            public String getBuildids() {
                return buildids;
            }

            public void setBuildids(String buildids) {
                this.buildids = buildids;
            }

            public String getFloorids() {
                return floorids;
            }

            public void setFloorids(String floorids) {
                this.floorids = floorids;
            }

            public String getContact_tel() {
                return contact_tel;
            }

            public void setContact_tel(String contact_tel) {
                this.contact_tel = contact_tel;
            }

            public String getContact_name() {
                return contact_name;
            }

            public void setContact_name(String contact_name) {
                this.contact_name = contact_name;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getDetector_address_number() {
                return detector_address_number;
            }

            public void setDetector_address_number(String detector_address_number) {
                this.detector_address_number = detector_address_number;
            }

            public String getDetector_port() {
                return detector_port;
            }

            public void setDetector_port(String detector_port) {
                this.detector_port = detector_port;
            }

            public String getUpper_limit() {
                return upper_limit;
            }

            public void setUpper_limit(String upper_limit) {
                this.upper_limit = upper_limit;
            }

            public String getLower_limit() {
                return lower_limit;
            }

            public void setLower_limit(String lower_limit) {
                this.lower_limit = lower_limit;
            }

            public String getCurrent_value() {
                return current_value;
            }

            public void setCurrent_value(String current_value) {
                this.current_value = current_value;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}

