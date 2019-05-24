package com.hechuang.labeego.bean;

public class StoreGoodsinfo_buy_bean {

    /**
     * data : {"status":1,"msg":"成功","list":{"shopcartid":1}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public StoreGoodsinfo_buy_bean(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 1
         * msg : 成功
         * list : {"styleid":1}
         */

        private String status;
        private String msg;
        private ListBean list;

        public DataBean(String status, String msg, ListBean list) {
            this.status = status;
            this.msg = msg;
            this.list = list;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            public ListBean(String shopcartid) {
                this.styleid = shopcartid;
            }

            /**
             * shopcartid : 1
             */

            private String styleid;

            public void setStyleid(String styleid) {
                this.styleid = styleid;
            }

            public String getStyleid() {
                return styleid;
            }
        }
    }
}
