package com.hechuang.labeego.bean;

import java.util.List;

public class ShopGoodInfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":{"ProId":"1","ProName":"测试商品1","Unit":"套","MinPurchase":"1","OrderStep":"1","ProContent":"<p><span style=\"color: rgb(255, 0, 0);\">123风飒飒大V<\/span>是的VB是大V社范围范围范围威风范<strong>围而无法为<\/strong><\/p>","SupplierId":"4","shouhou":"123213213123213","imgs":["http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg"],"isshipping":"","price":"￥5.00","adbeefee":"可抵9990.02","goodsstyle":[{"price":"￥5.00","Kucun":"123","StyleName":"123","StyleId":"1","adbeefee":"可抵9990.02","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg"}],"Supplier":"茅台镇珍豪酒业有限公司"}}
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
         * list : {"ProId":"1","ProName":"测试商品1","Unit":"套","MinPurchase":"1","OrderStep":"1","ProContent":"<p><span style=\"color: rgb(255, 0, 0);\">123风飒飒大V<\/span>是的VB是大V社范围范围范围威风范<strong>围而无法为<\/strong><\/p>","SupplierId":"4","shouhou":"123213213123213","imgs":["http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg"],"isshipping":"","price":"￥5.00","adbeefee":"可抵9990.02","goodsstyle":[{"price":"￥5.00","Kucun":"123","StyleName":"123","StyleId":"1","adbeefee":"可抵9990.02","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg"}],"Supplier":"茅台镇珍豪酒业有限公司"}
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
             * ProId : 1
             * ProName : 测试商品1
             * Unit : 套
             * MinPurchase : 1
             * OrderStep : 1
             * ProContent : <p><span style="color: rgb(255, 0, 0);">123风飒飒大V</span>是的VB是大V社范围范围范围威风范<strong>围而无法为</strong></p>
             * SupplierId : 4
             * shouhou : 123213213123213
             * imgs : ["http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg"]
             * isshipping :
             * price : ￥5.00
             * adbeefee : 可抵9990.02
             * goodsstyle : [{"price":"￥5.00","Kucun":"123","StyleName":"123","StyleId":"1","adbeefee":"可抵9990.02","ProImg":"http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg"}]
             * Supplier : 茅台镇珍豪酒业有限公司
             */

            private String ProId;
            private String ProName;
            private String Unit;
            private String MinPurchase;
            private String OrderStep;
            private String ProContent;
            private String SupplierId;
            private String shouhou;
            private String isshipping;
            private String price;
            private String adbeefee;
            private String Supplier;
            private List<String> imgs;
            private List<GoodsstyleBean> goodsstyle;

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

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public String getMinPurchase() {
                return MinPurchase;
            }

            public void setMinPurchase(String MinPurchase) {
                this.MinPurchase = MinPurchase;
            }

            public String getOrderStep() {
                return OrderStep;
            }

            public void setOrderStep(String OrderStep) {
                this.OrderStep = OrderStep;
            }

            public String getProContent() {
                return ProContent;
            }

            public void setProContent(String ProContent) {
                this.ProContent = ProContent;
            }

            public String getSupplierId() {
                return SupplierId;
            }

            public void setSupplierId(String SupplierId) {
                this.SupplierId = SupplierId;
            }

            public String getShouhou() {
                return shouhou;
            }

            public void setShouhou(String shouhou) {
                this.shouhou = shouhou;
            }

            public String getIsshipping() {
                return isshipping;
            }

            public void setIsshipping(String isshipping) {
                this.isshipping = isshipping;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getAdbeefee() {
                return adbeefee;
            }

            public void setAdbeefee(String adbeefee) {
                this.adbeefee = adbeefee;
            }

            public String getSupplier() {
                return Supplier;
            }

            public void setSupplier(String Supplier) {
                this.Supplier = Supplier;
            }

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }

            public List<GoodsstyleBean> getGoodsstyle() {
                return goodsstyle;
            }

            public void setGoodsstyle(List<GoodsstyleBean> goodsstyle) {
                this.goodsstyle = goodsstyle;
            }

            public static class GoodsstyleBean {
                /**
                 * price : ￥5.00
                 * Kucun : 123
                 * StyleName : 123
                 * StyleId : 1
                 * adbeefee : 可抵9990.02
                 * ProImg : http://lafeng.hetianpay.com/Upload/product/2018-12-12/154461008085506.jpg
                 */

                private String price;
                private String Kucun;
                private String StyleName;
                private String StyleId;
                private String adbeefee;
                private String ProImg;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getKucun() {
                    return Kucun;
                }

                public void setKucun(String Kucun) {
                    this.Kucun = Kucun;
                }

                public String getStyleName() {
                    return StyleName;
                }

                public void setStyleName(String StyleName) {
                    this.StyleName = StyleName;
                }

                public String getStyleId() {
                    return StyleId;
                }

                public void setStyleId(String StyleId) {
                    this.StyleId = StyleId;
                }

                public String getAdbeefee() {
                    return adbeefee;
                }

                public void setAdbeefee(String adbeefee) {
                    this.adbeefee = adbeefee;
                }

                public String getProImg() {
                    return ProImg;
                }

                public void setProImg(String ProImg) {
                    this.ProImg = ProImg;
                }
            }
        }
    }
}
