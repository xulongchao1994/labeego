package com.hechuang.labeego.bean;

public class SetNameBean {

    /**
     * data : {"status":1,"message":"修改成功!"}
     */

    private DataBean data;

    @Override
    public String toString() {
        return "SetNameBean{" +
                "data=" + data +
                '}';
    }

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
                    '}';
        }

        /**
         * status : 1
         * message : 修改成功!
         */

        private int status;
        private String message;

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
    }
}
