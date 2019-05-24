package com.hechuang.labeego.bean;

public class LoginviewBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":{"hcuser":"公共空间","reguser":"进社区","hcurl":"http://lafeng.99xyg.com/index.php/Home/FreeLogin/HcUser_reg","regurl":"http://lafeng.99xyg.com/index.php/Home/FreeLogin/reg"}}
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
         * list : {"hcuser":"公共空间","reguser":"进社区","hcurl":"http://lafeng.99xyg.com/index.php/Home/FreeLogin/HcUser_reg","regurl":"http://lafeng.99xyg.com/index.php/Home/FreeLogin/reg"}
         */

        private int status;
        private String message;
        private ListBean list;

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

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * hcuser : 公共空间
             * reguser : 进社区
             * hcurl : http://lafeng.99xyg.com/index.php/Home/FreeLogin/HcUser_reg
             * regurl : http://lafeng.99xyg.com/index.php/Home/FreeLogin/reg
             */

            private String hcuser;
            private String reguser;
            private String hcurl;
            private String regurl;

            public String getHcuser() {
                return hcuser;
            }

            public void setHcuser(String hcuser) {
                this.hcuser = hcuser;
            }

            public String getReguser() {
                return reguser;
            }

            public void setReguser(String reguser) {
                this.reguser = reguser;
            }

            public String getHcurl() {
                return hcurl;
            }

            public void setHcurl(String hcurl) {
                this.hcurl = hcurl;
            }

            public String getRegurl() {
                return regurl;
            }

            public void setRegurl(String regurl) {
                this.regurl = regurl;
            }
        }
    }
}
