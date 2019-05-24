package com.hechuang.labeego.bean;

public class Login_Bean {

    /**
     * data : {"list":{"userid":"5","username":"18039511208","usertype":"3","sessionid":"1ve5tee152n0a9tdu440i5ius4","token":"d0f1ea76e54efac8a10e401a9ca7c492"},"status":1,"msg":"登录成功！"}
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
         * list : {"userid":"5","username":"18039511208","usertype":"3","sessionid":"1ve5tee152n0a9tdu440i5ius4","token":"d0f1ea76e54efac8a10e401a9ca7c492"}
         * status : 1
         * msg : 登录成功！
         */

        private ListBean list;
        private int status;
        private String msg;
        private String userid;
        private String token;

        @Override
        public String toString() {
            return "DataBean{" +
                    "list=" + list +
                    ", status=" + status +
                    ", msg='" + msg + '\'' +
                    ", userid='" + userid + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }

        public String getUserid() {
            return userid;
        }

        public String getToken() {
            return token;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

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

        public static class ListBean {
            /**
             * userid : 5
             * username : 18039511208
             * usertype : 3
             * sessionid : 1ve5tee152n0a9tdu440i5ius4
             * token : d0f1ea76e54efac8a10e401a9ca7c492
             */

            private String url;
            private String status;
            private String userid;
            private String username;
            private String usertype;
            private String sessionid;
            private String token;

            @Override
            public String toString() {
                return "ListBean{" +
                        "url='" + url + '\'' +
                        ", status='" + status + '\'' +
                        ", userid='" + userid + '\'' +
                        ", username='" + username + '\'' +
                        ", usertype='" + usertype + '\'' +
                        ", sessionid='" + sessionid + '\'' +
                        ", token='" + token + '\'' +
                        '}';
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUrl() {
                return url;
            }

            public String getStatus() {
                return status;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUsertype() {
                return usertype;
            }

            public void setUsertype(String usertype) {
                this.usertype = usertype;
            }

            public String getSessionid() {
                return sessionid;
            }

            public void setSessionid(String sessionid) {
                this.sessionid = sessionid;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
