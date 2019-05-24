package com.hechuang.labeego.bean;

import java.util.List;

public class Commentbean {
    /**
     * data : {"status":1,"message":"数据获取成功!","list":[{"proid":"7","proimg":"/Upload/Product/2018-05-18/152661512592045.jpg"}]}
     */

    private Commentbean.DataBean data;

    public Commentbean.DataBean getData() {
        return data;
    }

    public void setData(Commentbean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * status : 1
         * message : 数据获取成功!
         * list : [{"proid":"7","proimg":"/Upload/Product/2018-05-18/152661512592045.jpg"}]
         */

        private int status;
        private String message;
        private List<Commentbean.DataBean.ListBean> list;

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

        public List<Commentbean.DataBean.ListBean> getList() {
            return list;
        }

        public void setList(List<Commentbean.DataBean.ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * proid : 7
             * proimg : /Upload/Product/2018-05-18/152661512592045.jpg
             */
            private boolean yincang;
            private String proid;
            private String proimg;
            private String comment;
            private String comment_str;
            private String comment_context;
            private boolean isniming;

            public void setYincang(boolean yincang) {
                this.yincang = yincang;
            }

            public boolean isYincang() {
                return yincang;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public void setComment_str(String comment_str) {
                this.comment_str = comment_str;
            }

            public void setComment_context(String comment_context) {
                this.comment_context = comment_context;
            }

            public void setIsniming(boolean isniming) {
                this.isniming = isniming;
            }

            public String getComment() {
                return comment;
            }

            public String getComment_str() {
                return comment_str;
            }

            public String getComment_context() {
                return comment_context;
            }

            public boolean isIsniming() {
                return isniming;
            }

            public String getProid() {
                return proid;
            }

            public void setProid(String proid) {
                this.proid = proid;
            }

            public String getProimg() {
                return proimg;
            }

            public void setProimg(String proimg) {
                this.proimg = proimg;
            }
        }
    }
}
