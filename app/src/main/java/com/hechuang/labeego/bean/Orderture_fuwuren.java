package com.hechuang.labeego.bean;

public class Orderture_fuwuren {

    /**
     * data : {"status":1,"message":"数据获取成功","list":{"TrueName":"内部占有","Mobile":"13500100008"}}
     */

    private DataBean data;

    public Orderture_fuwuren(DataBean data) {
        this.data = data;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean(String status, String message, ListBean list) {
            this.status = status;
            this.message = message;
            this.list = list;
        }

        /**
         * status : 1
         * message : 数据获取成功
         * list : {"TrueName":"内部占有","Mobile":"13500100008"}
         */

        private String status;
        private String message;
        private ListBean list;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * TrueName : 内部占有
             * Mobile : 13500100008
             */

            private String TrueName;
            private String Mobile;

            public ListBean(String trueName, String mobile) {
                TrueName = trueName;
                Mobile = mobile;
            }

            public String getTrueName() {
                return TrueName;
            }

            public void setTrueName(String TrueName) {
                this.TrueName = TrueName;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }
        }
    }
}
