package com.hechuang.labeego.bean;

import java.util.List;

public class GoodsListBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"ProId":"42","ProName":"20000组合礼包2【11支芦荟凝胶、30盒私护凝胶、12瓶双清口服液、7盒钙冲剂、6箱老酱酒、12瓶有机凤梨酵素】","ProImg":"http://www.labeego.com/Upload/Product/2018-05-23/152706730618706.jpg","integral":"送 20000.00","price":"￥20000.00","marketprice":"￥20000.00"}]}
     */

    private DataBean data;

    public GoodsListBean(DataBean data) {
        this.data = data;
    }

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
         * list : [{"ProId":"42","ProName":"20000组合礼包2【11支芦荟凝胶、30盒私护凝胶、12瓶双清口服液、7盒钙冲剂、6箱老酱酒、12瓶有机凤梨酵素】","ProImg":"http://www.labeego.com/Upload/Product/2018-05-23/152706730618706.jpg","integral":"送 20000.00","price":"￥20000.00","marketprice":"￥20000.00"}]
         */

        private int status;
        private String message;
        public List<ListBean> list;

        public DataBean(int status, String message, List<ListBean> list) {
            this.status = status;
            this.message = message;
            this.list = list;
        }

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
            /**
             * ProId : 42
             * ProName : 20000组合礼包2【11支芦荟凝胶、30盒私护凝胶、12瓶双清口服液、7盒钙冲剂、6箱老酱酒、12瓶有机凤梨酵素】
             * ProImg : http://www.labeego.com/Upload/Product/2018-05-23/152706730618706.jpg
             * integral : 送 20000.00
             * price : ￥20000.00
             * marketprice : ￥20000.00
             */

            private String ProId;
            private String ProName;
            private String ProImg;
            private String integral;
            private String price;
            private String marketprice;
            private String yishou;

            @Override
            public String toString() {
                return "ListBean{" +
                        "ProId='" + ProId + '\'' +
                        ", ProName='" + ProName + '\'' +
                        ", ProImg='" + ProImg + '\'' +
                        ", integral='" + integral + '\'' +
                        ", price='" + price + '\'' +
                        ", marketprice='" + marketprice + '\'' +
                        ", yishou='" + yishou + '\'' +
                        '}';
            }

            public ListBean(String proId, String proName, String proImg, String integral, String price, String marketprice, String yishou) {
                ProId = proId;
                ProName = proName;
                ProImg = proImg;
                this.integral = integral;
                this.price = price;
                this.marketprice = marketprice;
                this.yishou = yishou;
            }

            public void setYishou(String yishou) {
                this.yishou = yishou;
            }

            public String getYishou() {
                return yishou;
            }

            public String getProId() {
                return ProId;
            }

            public void setProId(String ProId) {
                this.ProId = ProId;
            }

            public String getProName() {
                return ProName;
            }

            public void setProName(String ProName) {
                this.ProName = ProName;
            }

            public String getProImg() {
                return ProImg;
            }

            public void setProImg(String ProImg) {
                this.ProImg = ProImg;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }
        }
    }
}
