package com.hechuang.labeego.bean;

import java.util.List;

public class Order_tijiao {

    /**
     * data : {"status":1,"message":"提交订单成功","list":["20180609165932328350","20180609165932669860"]}
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
         * message : 提交订单成功
         * list : ["20180609165932328350","20180609165932669860"]
         */

        private String status;
        private String message;
        private List<String> list;

        public DataBean(String status, String message, List<String> list) {
            this.status = status;
            this.message = message;
            this.list = list;
        }

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

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Order_tijiao{" +
                "data=" + data +
                '}';
    }
}
