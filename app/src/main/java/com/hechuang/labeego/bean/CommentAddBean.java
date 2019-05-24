package com.hechuang.labeego.bean;

public class CommentAddBean {

    /**
     * data : {"status":1,"message":"添加成功"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CommentAddBean{" +
                "data=" + data +
                '}';
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 1
         * message : 添加成功
         */

        private int status;
        private String message;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
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
    }
}
