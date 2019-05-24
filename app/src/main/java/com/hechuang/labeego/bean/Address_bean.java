package com.hechuang.labeego.bean;

import java.util.List;

public class Address_bean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"Id":"1260","ReceiveName":"测试账号","Mobile":"18898584548","IsDefault":"1","diqu":"河南省郑州市惠济区英才街"},{"Id":"1261","ReceiveName":"测试账号","Mobile":"18898584549","IsDefault":"0","diqu":"河南省郑州市惠济区英才街"},{"Id":"1262","ReceiveName":"测试账号","Mobile":"18898584545","IsDefault":"0","diqu":"河南省郑州市惠济区英才街"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 1
         * message : 数据获取成功
         * list : [{"Id":"1260","ReceiveName":"测试账号","Mobile":"18898584548","IsDefault":"1","diqu":"河南省郑州市惠济区英才街"},{"Id":"1261","ReceiveName":"测试账号","Mobile":"18898584549","IsDefault":"0","diqu":"河南省郑州市惠济区英才街"},{"Id":"1262","ReceiveName":"测试账号","Mobile":"18898584545","IsDefault":"0","diqu":"河南省郑州市惠济区英才街"}]
         */

        private int status;
        private String message;
        private List<ListBean> list;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * Id : 1260
             * ReceiveName : 测试账号
             * Mobile : 18898584548
             * IsDefault : 1
             * diqu : 河南省郑州市惠济区英才街
             */

            private String Id;
            private String ReceiveName;
            private String Mobile;
            private String IsDefault;
            private String diqu;
            private Boolean isbianji;

            @Override
            public String toString() {
                return "ListBean{" +
                        "Id='" + Id + '\'' +
                        ", ReceiveName='" + ReceiveName + '\'' +
                        ", Mobile='" + Mobile + '\'' +
                        ", IsDefault='" + IsDefault + '\'' +
                        ", diqu='" + diqu + '\'' +
                        '}';
            }

            public Boolean getIsbianji() {
                return isbianji;
            }

            public void setIsbianji(Boolean isbianji) {
                this.isbianji = isbianji;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getReceiveName() {
                return ReceiveName;
            }

            public void setReceiveName(String ReceiveName) {
                this.ReceiveName = ReceiveName;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getIsDefault() {
                return IsDefault;
            }

            public void setIsDefault(String IsDefault) {
                this.IsDefault = IsDefault;
            }

            public String getDiqu() {
                return diqu;
            }

            public void setDiqu(String diqu) {
                this.diqu = diqu;
            }
        }
    }
}
