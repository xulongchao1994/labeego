package com.hechuang.labeego.bean;

public class Shoppingcar_Bianji_bean {

    /**
     * data : {"status":1,"message":"修改成功","list":""}
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
         * message : 修改成功
         * list :
         */

        private int status;
        private String message;
        private String list;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list='" + list + '\'' +
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

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
