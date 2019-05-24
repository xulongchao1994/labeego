package com.hechuang.labeego.bean;

public class Address_morenBean {

    /**
     * data : {"status":1,"msg":"地址设置成功","list":""}
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
                    ", msg='" + message + '\'' +
                    ", list='" + list + '\'' +
                    '}';
        }

        /**
         * status : 1
         * msg : 地址设置成功
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

        public String getMsg() {
            return message;
        }

        public void setMsg(String msg) {
            this.message = msg;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
