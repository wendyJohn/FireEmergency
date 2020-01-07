package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class PatroTasks {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":1,"nextPage":-1,"list":[{"equiment_typeVal":["火灾自动报警系统"],"task_status":"1","patrol_point":0,"ttask_id":"389fe65d1db911e89b40000c29b01331","end_time":"2018-03-02 09:41:34","end_describe":"","equiment_type":["check_rule_fire_auto_sys"],"unit_name":"海亮广场管理集团有限公司","start_time":"2018-03-02 00:00:00","organ_name":"海亮广场管理集团有限公司","last_time":"2018-03-03 00:00:00","task_typeVal":"单次任务","ids":"38c13cf81db911e89b40000c29b01331","task_title":"发布一个任务给海亮","all_point":1,"task_content":"1222","task_type":"once_task","unit_id":"00e31db02e024a05bfe8f91bf79d2be7","organ_id":""}]}
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
         * total : 1
         * nextPage : -1
         * list : [{"equiment_typeVal":["火灾自动报警系统"],"task_status":"1","patrol_point":0,"ttask_id":"389fe65d1db911e89b40000c29b01331","end_time":"2018-03-02 09:41:34","end_describe":"","equiment_type":["check_rule_fire_auto_sys"],"unit_name":"海亮广场管理集团有限公司","start_time":"2018-03-02 00:00:00","organ_name":"海亮广场管理集团有限公司","last_time":"2018-03-03 00:00:00","task_typeVal":"单次任务","ids":"38c13cf81db911e89b40000c29b01331","task_title":"发布一个任务给海亮","all_point":1,"task_content":"1222","task_type":"once_task","unit_id":"00e31db02e024a05bfe8f91bf79d2be7","organ_id":""}]
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
             * equiment_typeVal : ["火灾自动报警系统"]
             * task_status : 1
             * patrol_point : 0
             * ttask_id : 389fe65d1db911e89b40000c29b01331
             * end_time : 2018-03-02 09:41:34
             * end_describe :
             * equiment_type : ["check_rule_fire_auto_sys"]
             * unit_name : 海亮广场管理集团有限公司
             * start_time : 2018-03-02 00:00:00
             * organ_name : 海亮广场管理集团有限公司
             * last_time : 2018-03-03 00:00:00
             * task_typeVal : 单次任务
             * ids : 38c13cf81db911e89b40000c29b01331
             * task_title : 发布一个任务给海亮
             * all_point : 1
             * task_content : 1222
             * task_type : once_task
             * unit_id : 00e31db02e024a05bfe8f91bf79d2be7
             * organ_id :
             */

            private String task_status;
            private int patrol_point;
            private String ttask_id;
            private String end_time;
            private String end_describe;
            private String unit_name;
            private String start_time;
            private String organ_name;
            private String last_time;
            private String task_typeVal;
            private String ids;
            private String task_title;
            private int all_point;
            private String task_content;
            private String task_type;
            private String unit_id;
            private String organ_id;
            private List<String> equiment_typeVal;
            private List<String> equiment_type;

            public String getTask_status() {
                return task_status;
            }

            public void setTask_status(String task_status) {
                this.task_status = task_status;
            }

            public int getPatrol_point() {
                return patrol_point;
            }

            public void setPatrol_point(int patrol_point) {
                this.patrol_point = patrol_point;
            }

            public String getTtask_id() {
                return ttask_id;
            }

            public void setTtask_id(String ttask_id) {
                this.ttask_id = ttask_id;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getEnd_describe() {
                return end_describe;
            }

            public void setEnd_describe(String end_describe) {
                this.end_describe = end_describe;
            }

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getOrgan_name() {
                return organ_name;
            }

            public void setOrgan_name(String organ_name) {
                this.organ_name = organ_name;
            }

            public String getLast_time() {
                return last_time;
            }

            public void setLast_time(String last_time) {
                this.last_time = last_time;
            }

            public String getTask_typeVal() {
                return task_typeVal;
            }

            public void setTask_typeVal(String task_typeVal) {
                this.task_typeVal = task_typeVal;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getTask_title() {
                return task_title;
            }

            public void setTask_title(String task_title) {
                this.task_title = task_title;
            }

            public int getAll_point() {
                return all_point;
            }

            public void setAll_point(int all_point) {
                this.all_point = all_point;
            }

            public String getTask_content() {
                return task_content;
            }

            public void setTask_content(String task_content) {
                this.task_content = task_content;
            }

            public String getTask_type() {
                return task_type;
            }

            public void setTask_type(String task_type) {
                this.task_type = task_type;
            }

            public String getUnit_id() {
                return unit_id;
            }

            public void setUnit_id(String unit_id) {
                this.unit_id = unit_id;
            }

            public String getOrgan_id() {
                return organ_id;
            }

            public void setOrgan_id(String organ_id) {
                this.organ_id = organ_id;
            }

            public List<String> getEquiment_typeVal() {
                return equiment_typeVal;
            }

            public void setEquiment_typeVal(List<String> equiment_typeVal) {
                this.equiment_typeVal = equiment_typeVal;
            }

            public List<String> getEquiment_type() {
                return equiment_type;
            }

            public void setEquiment_type(List<String> equiment_type) {
                this.equiment_type = equiment_type;
            }
        }
    }
}
