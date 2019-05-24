package com.hechuang.labeego.bean;

public class NewAddress_seccess_bean {

    /**
     * data : {"status":1,"msg":"添加地址成功","list":""}
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
         * msg : 添加地址成功
         * list :
         */

        private int status;
        private String msg;
        private String list;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
