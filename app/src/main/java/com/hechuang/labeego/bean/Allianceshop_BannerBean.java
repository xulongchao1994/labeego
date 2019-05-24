package com.hechuang.labeego.bean;

import java.util.List;

public class Allianceshop_BannerBean {

    /**
     * data : {"imgs":[{"id":"1","style":0,"url":"","img":"http://192.168.10.219:8066/Public/temp/20180617/617/04-old.png"},{"id":"2","style":0,"url":"","img":"http://192.168.10.219:8066/Public/temp/20180617/617/05.png"},{"id":"3","style":0,"url":"","img":"http://192.168.10.219:8066/Public/temp/20180617/617/03.png"}],"msg":"加载成功","status":1}
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
         * imgs : [{"id":"1","style":0,"url":"","img":"http://192.168.10.219:8066/Public/temp/20180617/617/04-old.png"},{"id":"2","style":0,"url":"","img":"http://192.168.10.219:8066/Public/temp/20180617/617/05.png"},{"id":"3","style":0,"url":"","img":"http://192.168.10.219:8066/Public/temp/20180617/617/03.png"}]
         * msg : 加载成功
         * status : 1
         */

        private String msg;
        private int status;
        private List<ImgsBean> imgs;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class ImgsBean {
            /**
             * id : 1
             * style : 0
             * url :
             * img : http://192.168.10.219:8066/Public/temp/20180617/617/04-old.png
             */

            private String id;
            private int style;
            private String url;
            private String img;
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getStyle() {
                return style;
            }

            public void setStyle(int style) {
                this.style = style;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
