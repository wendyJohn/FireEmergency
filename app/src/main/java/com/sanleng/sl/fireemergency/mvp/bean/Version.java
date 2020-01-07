package com.sanleng.sl.fireemergency.mvp.bean;

import java.util.List;

public class Version {

    /**
     * msg :
     * data : {"content":[{"appVersion":"2","appName":"测试","platformType":"chemicals_cab_ll","osType":"os_android","appDescribe":"测试","downloadUrl":"jssl.oss-cn-beijing.aliyuncs.com/5f6173bd1b6f4ee690948fbb038f269e.apk","ids":"016ecf570e82117772046ecf570e0001"}],"totalPages":1,"totalElements":1,"last":true,"first":true,"sort":[{"direction":"DESC","property":"name","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}],"numberOfElements":1,"size":10,"number":0}
     * status : 0
     */

    private String msg;
    private DataBean data;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * content : [{"appVersion":"2","appName":"测试","platformType":"chemicals_cab_ll","osType":"os_android","appDescribe":"测试","downloadUrl":"jssl.oss-cn-beijing.aliyuncs.com/5f6173bd1b6f4ee690948fbb038f269e.apk","ids":"016ecf570e82117772046ecf570e0001"}]
         * totalPages : 1
         * totalElements : 1
         * last : true
         * first : true
         * sort : [{"direction":"DESC","property":"name","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
         * numberOfElements : 1
         * size : 10
         * number : 0
         */

        private int totalPages;
        private int totalElements;
        private boolean last;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private List<ContentBean> content;
        private List<SortBean> sort;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<SortBean> getSort() {
            return sort;
        }

        public void setSort(List<SortBean> sort) {
            this.sort = sort;
        }

        public static class ContentBean {
            /**
             * appVersion : 2
             * appName : 测试
             * platformType : chemicals_cab_ll
             * osType : os_android
             * appDescribe : 测试
             * downloadUrl : jssl.oss-cn-beijing.aliyuncs.com/5f6173bd1b6f4ee690948fbb038f269e.apk
             * ids : 016ecf570e82117772046ecf570e0001
             */

            private String appVersion;
            private String appName;
            private String platformType;
            private String osType;
            private String appDescribe;
            private String downloadUrl;
            private String ids;

            public String getAppVersion() {
                return appVersion;
            }

            public void setAppVersion(String appVersion) {
                this.appVersion = appVersion;
            }

            public String getAppName() {
                return appName;
            }

            public void setAppName(String appName) {
                this.appName = appName;
            }

            public String getPlatformType() {
                return platformType;
            }

            public void setPlatformType(String platformType) {
                this.platformType = platformType;
            }

            public String getOsType() {
                return osType;
            }

            public void setOsType(String osType) {
                this.osType = osType;
            }

            public String getAppDescribe() {
                return appDescribe;
            }

            public void setAppDescribe(String appDescribe) {
                this.appDescribe = appDescribe;
            }

            public String getDownloadUrl() {
                return downloadUrl;
            }

            public void setDownloadUrl(String downloadUrl) {
                this.downloadUrl = downloadUrl;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }
        }

        public static class SortBean {
            /**
             * direction : DESC
             * property : name
             * ignoreCase : false
             * nullHandling : NATIVE
             * ascending : false
             * descending : true
             */

            private String direction;
            private String property;
            private boolean ignoreCase;
            private String nullHandling;
            private boolean ascending;
            private boolean descending;

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public boolean isIgnoreCase() {
                return ignoreCase;
            }

            public void setIgnoreCase(boolean ignoreCase) {
                this.ignoreCase = ignoreCase;
            }

            public String getNullHandling() {
                return nullHandling;
            }

            public void setNullHandling(String nullHandling) {
                this.nullHandling = nullHandling;
            }

            public boolean isAscending() {
                return ascending;
            }

            public void setAscending(boolean ascending) {
                this.ascending = ascending;
            }

            public boolean isDescending() {
                return descending;
            }

            public void setDescending(boolean descending) {
                this.descending = descending;
            }
        }
    }
}
