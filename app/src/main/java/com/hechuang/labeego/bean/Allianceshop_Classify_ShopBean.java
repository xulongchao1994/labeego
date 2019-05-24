package com.hechuang.labeego.bean;

import java.util.List;

public class Allianceshop_Classify_ShopBean {

    /**
     * data : {"imgs":[{"id":"1","style":2,"url":"http://192.168.10.219:8066/index.php/Home/Unproduct/S_area_list/id/1","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/furniture.png","name":"蜂币专区"},{"id":"2","style":2,"url":"http://192.168.10.219:8066/index.php/Home/Unproduct/S_area_list/id/2","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/Shoebag.png","name":"蜂抢购"},{"id":"3","style":2,"url":"http://newlaf.out.999000.cn/web/home.php/Login/rlblogin/user_name/9611230_lf","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/Mother.png","name":"久零商城"},{"id":"4","style":2,"url":"","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/chemicals.png","name":"秒杀活动"},{"id":"5","style":2,"url":"","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/Clothing.png","name":"天天特卖"}],"msg":"加载成功","status":1}
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
         * imgs : [{"id":"1","style":2,"url":"http://192.168.10.219:8066/index.php/Home/Unproduct/S_area_list/id/1","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/furniture.png","name":"蜂币专区"},{"id":"2","style":2,"url":"http://192.168.10.219:8066/index.php/Home/Unproduct/S_area_list/id/2","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/Shoebag.png","name":"蜂抢购"},{"id":"3","style":2,"url":"http://newlaf.out.999000.cn/web/home.php/Login/rlblogin/user_name/9611230_lf","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/Mother.png","name":"久零商城"},{"id":"4","style":2,"url":"","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/chemicals.png","name":"秒杀活动"},{"id":"5","style":2,"url":"","img":"http://192.168.10.219:8066/Public/static/labeego/images/shopicon/Clothing.png","name":"天天特卖"}]
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
             * style : 2
             * url : http://192.168.10.219:8066/index.php/Home/Unproduct/S_area_list/id/1
             * img : http://192.168.10.219:8066/Public/static/labeego/images/shopicon/furniture.png
             * name : 蜂币专区
             */

            private String id;
            private int style;
            private String url;
            private String img;
            private String name;
            private String msg;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
