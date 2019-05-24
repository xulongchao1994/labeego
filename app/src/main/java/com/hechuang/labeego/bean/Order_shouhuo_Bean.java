package com.hechuang.labeego.bean;

public class Order_shouhuo_Bean {

    /**
     * data : {"status":1,"message":"操作成功","list":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list='" + list + '\'' +
                    '}';
        }

        /**
         * status : 1
         * message : 操作成功
         * list :
         */

        private int status;
        private String message;
        private String list;

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

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
