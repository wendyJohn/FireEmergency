package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class Gas {


    /**
     * state : ok
     * page : {"totalCount":2,"pageSize":10,"totalPage":1,"currPage":1,"nextPage":-1,"list":[{"yaxis_plan":0,"crt_key":"ffffff100000c63e","yaxis_location":0,"loop_no":"0035","owned_building_name":"教学楼A","floor_number":"3层","device_type":"JYT-GD-3002C","location_pointer_flag":0,"xaxis_plan":0,"current_value":"768.0","device_name":"光电探测器","plan_pointer_flg":0,"unitname":"南京交通职业学院","engine_code":"0002","ids":"0003e753188f4f9b95c41a60604eb693","sys_name":"火灾报警系统","unit_code":"0c7f1aba9d15449ab65bf46b51ccdb87","xaxis_location":0,"position":"干洗区","tag_no":"0119"}]}
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
         * totalCount : 2
         * pageSize : 10
         * totalPage : 1
         * currPage : 1
         * nextPage : -1
         * list : [{"yaxis_plan":0,"crt_key":"ffffff100000c63e","yaxis_location":0,"loop_no":"0035","owned_building_name":"教学楼A","floor_number":"3层","device_type":"JYT-GD-3002C","location_pointer_flag":0,"xaxis_plan":0,"current_value":"768.0","device_name":"光电探测器","plan_pointer_flg":0,"unitname":"南京交通职业学院","engine_code":"0002","ids":"0003e753188f4f9b95c41a60604eb693","sys_name":"火灾报警系统","unit_code":"0c7f1aba9d15449ab65bf46b51ccdb87","xaxis_location":0,"position":"干洗区","tag_no":"0119"}]
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
             * yaxis_plan : 0
             * crt_key : ffffff100000c63e
             * yaxis_location : 0
             * loop_no : 0035
             * owned_building_name : 教学楼A
             * floor_number : 3层
             * device_type : JYT-GD-3002C
             * location_pointer_flag : 0
             * xaxis_plan : 0
             * current_value : 768.0
             * device_name : 光电探测器
             * plan_pointer_flg : 0
             * unitname : 南京交通职业学院
             * engine_code : 0002
             * ids : 0003e753188f4f9b95c41a60604eb693
             * sys_name : 火灾报警系统
             * unit_code : 0c7f1aba9d15449ab65bf46b51ccdb87
             * xaxis_location : 0
             * position : 干洗区
             * tag_no : 0119
             */

            private int yaxis_plan;
            private String crt_key;
            private int yaxis_location;
            private String loop_no;
            private String owned_building_name;
            private String floor_number;
            private String device_type;
            private int location_pointer_flag;
            private int xaxis_plan;
            private String current_value;
            private String device_name;
            private int plan_pointer_flg;
            private String unitname;
            private String engine_code;
            private String ids;
            private String sys_name;
            private String unit_code;
            private int xaxis_location;
            private String position;
            private String tag_no;

            public int getYaxis_plan() {
                return yaxis_plan;
            }

            public void setYaxis_plan(int yaxis_plan) {
                this.yaxis_plan = yaxis_plan;
            }

            public String getCrt_key() {
                return crt_key;
            }

            public void setCrt_key(String crt_key) {
                this.crt_key = crt_key;
            }

            public int getYaxis_location() {
                return yaxis_location;
            }

            public void setYaxis_location(int yaxis_location) {
                this.yaxis_location = yaxis_location;
            }

            public String getLoop_no() {
                return loop_no;
            }

            public void setLoop_no(String loop_no) {
                this.loop_no = loop_no;
            }

            public String getOwned_building_name() {
                return owned_building_name;
            }

            public void setOwned_building_name(String owned_building_name) {
                this.owned_building_name = owned_building_name;
            }

            public String getFloor_number() {
                return floor_number;
            }

            public void setFloor_number(String floor_number) {
                this.floor_number = floor_number;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public int getLocation_pointer_flag() {
                return location_pointer_flag;
            }

            public void setLocation_pointer_flag(int location_pointer_flag) {
                this.location_pointer_flag = location_pointer_flag;
            }

            public int getXaxis_plan() {
                return xaxis_plan;
            }

            public void setXaxis_plan(int xaxis_plan) {
                this.xaxis_plan = xaxis_plan;
            }

            public String getCurrent_value() {
                return current_value;
            }

            public void setCurrent_value(String current_value) {
                this.current_value = current_value;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public int getPlan_pointer_flg() {
                return plan_pointer_flg;
            }

            public void setPlan_pointer_flg(int plan_pointer_flg) {
                this.plan_pointer_flg = plan_pointer_flg;
            }

            public String getUnitname() {
                return unitname;
            }

            public void setUnitname(String unitname) {
                this.unitname = unitname;
            }

            public String getEngine_code() {
                return engine_code;
            }

            public void setEngine_code(String engine_code) {
                this.engine_code = engine_code;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getSys_name() {
                return sys_name;
            }

            public void setSys_name(String sys_name) {
                this.sys_name = sys_name;
            }

            public String getUnit_code() {
                return unit_code;
            }

            public void setUnit_code(String unit_code) {
                this.unit_code = unit_code;
            }

            public int getXaxis_location() {
                return xaxis_location;
            }

            public void setXaxis_location(int xaxis_location) {
                this.xaxis_location = xaxis_location;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getTag_no() {
                return tag_no;
            }

            public void setTag_no(String tag_no) {
                this.tag_no = tag_no;
            }
        }
    }
}
