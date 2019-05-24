package com.hechuang.labeego.bean;

import java.util.List;

public class CommentListBean {

    @Override
    public String toString() {
        return "CommentListBean{" +
                "data=" + data +
                '}';
    }

    /**
     * data : {"status":1,"message":"数据获取成功!","list":[{"comment":"你好","truename":"","avatarurl":"","id":"15","admin_comment":""},{"comment":"222","truename":"啦蜂","avatarurl":"hc2688_58b537157e4cd_716_big.jpg","id":"11","admin_comment":""}]}
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
         * message : 数据获取成功!
         * list : [{"comment":"你好","truename":"","avatarurl":"","id":"15","admin_comment":""},{"comment":"222","truename":"啦蜂","avatarurl":"hc2688_58b537157e4cd_716_big.jpg","id":"11","admin_comment":""}]
         */

        private int status;
        private String message;
        private List<ListBean> list;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list=" + list +
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "comment='" + comment + '\'' +
                        ", truename='" + truename + '\'' +
                        ", avatarurl='" + avatarurl + '\'' +
                        ", id='" + id + '\'' +
                        ", admin_comment='" + admin_comment + '\'' +
                        '}';
            }

            /**
             * comment : 你好
             * truename :
             * avatarurl :
             * id : 15
             * admin_comment :
             */

            private String comment;
            private String truename;
            private String avatarurl;
            private String id;
            private String time;
            private String admin_comment;
            private String StyleName;

            public void setStyleName(String styleName) {
                StyleName = styleName;
            }

            public String getStyleName() {
                return StyleName;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTime() {
                return time;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getTruename() {
                return truename;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public String getAvatarurl() {
                return avatarurl;
            }

            public void setAvatarurl(String avatarurl) {
                this.avatarurl = avatarurl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdmin_comment() {
                return admin_comment;
            }

            public void setAdmin_comment(String admin_comment) {
                this.admin_comment = admin_comment;
            }
        }
    }
}
