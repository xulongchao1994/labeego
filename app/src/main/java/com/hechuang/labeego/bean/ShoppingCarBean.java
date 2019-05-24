package com.hechuang.labeego.bean;

import java.util.List;

public class ShoppingCarBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":[{"name":"公司代发","product":[{"shopcartid":"1004","ProId":"49","ProNum":"1","StyleId":"78","SupId":"8","Pv":"送 20000.00","ShopPrice":"￥20000.00","ProName":"20000组合礼包4【18支芦荟凝胶、7盒私护凝胶、12瓶双清口服液、30盒钙冲剂、10箱老酱酒、12瓶有机凤梨酵素】","MinPurchase":"1","OrderStep":"1","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706788163563.jpg","StyleName":"20000组合礼包4","Kucun":"99999","Supplier":"公司代发","money":"￥20000"}]}]}
     */

    public DataBean data;

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
         * list : [{"name":"公司代发","product":[{"shopcartid":"1004","ProId":"49","ProNum":"1","StyleId":"78","SupId":"8","Pv":"送 20000.00","ShopPrice":"￥20000.00","ProName":"20000组合礼包4【18支芦荟凝胶、7盒私护凝胶、12瓶双清口服液、30盒钙冲剂、10箱老酱酒、12瓶有机凤梨酵素】","MinPurchase":"1","OrderStep":"1","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706788163563.jpg","StyleName":"20000组合礼包4","Kucun":"99999","Supplier":"公司代发","money":"￥20000"}]}]
         */

        public String status;
        public String message;
        public List<ListBean> list;

        public DataBean(String status, String message, List<ListBean> list) {
            this.status = status;
            this.message = message;
            this.list = list;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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
             * name : 公司代发
             * product : [{"shopcartid":"1004","ProId":"49","ProNum":"1","StyleId":"78","SupId":"8","Pv":"送 20000.00","ShopPrice":"￥20000.00","ProName":"20000组合礼包4【18支芦荟凝胶、7盒私护凝胶、12瓶双清口服液、30盒钙冲剂、10箱老酱酒、12瓶有机凤梨酵素】","MinPurchase":"1","OrderStep":"1","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706788163563.jpg","StyleName":"20000组合礼包4","Kucun":"99999","Supplier":"公司代发","money":"￥20000"}]
             */

            private String name;
            private List<ProductBean> product;

            public ListBean(String name, List<ProductBean> product) {
                this.name = name;
                this.product = product;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ProductBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBean> product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * shopcartid : 1004
                 * ProId : 49
                 * ProNum : 1
                 * StyleId : 78
                 * SupId : 8
                 * Pv : 送 20000.00
                 * ShopPrice : ￥20000.00
                 * ProName : 20000组合礼包4【18支芦荟凝胶、7盒私护凝胶、12瓶双清口服液、30盒钙冲剂、10箱老酱酒、12瓶有机凤梨酵素】
                 * MinPurchase : 1
                 * OrderStep : 1
                 * ProImg : http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706788163563.jpg
                 * StyleName : 20000组合礼包4
                 * Kucun : 99999
                 * Supplier : 公司代发
                 * money : ￥20000
                 */

                private String shopcartid;
                private String ProId;
                private String ProNum;
                private String StyleId;
                private String SupId;
                private String Pv;
                private String ShopPrice;
                private String ProName;
                private String MinPurchase;
                private String OrderStep;
                private String ProImg;
                private String StyleName;
                private String Kucun;
                private String Supplier;
                private String money;
                private String storename;
                private Boolean isshowtitle;
                private Boolean ischeck;
                private Boolean isstorecheck;
                private Boolean isbianji;

                public ProductBean(String shopcartid, String proId, String proNum, String styleId, String supId, String pv, String shopPrice, String proName,
                                   String minPurchase, String orderStep, String proImg, String styleName, String kucun, String supplier, String money, String storename
                        , Boolean isshowtitle, Boolean ischeck, Boolean isstorecheck, Boolean isbianji
                ) {
                    this.shopcartid = shopcartid;
                    ProId = proId;
                    ProNum = proNum;
                    StyleId = styleId;
                    SupId = supId;
                    Pv = pv;
                    ShopPrice = shopPrice;
                    ProName = proName;
                    MinPurchase = minPurchase;
                    OrderStep = orderStep;
                    ProImg = proImg;
                    StyleName = styleName;
                    Kucun = kucun;
                    Supplier = supplier;
                    this.money = money;
                    this.storename = storename;
                    this.isshowtitle = isshowtitle;
                    this.ischeck = ischeck;
                    this.isstorecheck = isstorecheck;
                    this.isbianji = isbianji;
                }

                public Boolean getIsbianji() {
                    return isbianji;
                }

                public void setIsbianji(Boolean isbianji) {
                    this.isbianji = isbianji;
                }

                public void setIsstorecheck(Boolean isstorecheck) {
                    this.isstorecheck = isstorecheck;
                }

                public Boolean getIsstorecheck() {
                    return isstorecheck;
                }

                public void setIscheck(Boolean ischeck) {
                    this.ischeck = ischeck;
                }

                public Boolean getIscheck() {
                    return ischeck;
                }

                public void setIsshowtitle(Boolean isshowtitle) {
                    this.isshowtitle = isshowtitle;
                }

                public Boolean getIsshowtitle() {
                    return isshowtitle;
                }

                public void setStorename(String storename) {
                    this.storename = storename;
                }

                public String getStorename() {
                    return storename;
                }

                public String getShopcartid() {
                    return shopcartid;
                }

                public void setShopcartid(String shopcartid) {
                    this.shopcartid = shopcartid;
                }

                public String getProId() {
                    return ProId;
                }

                public void setProId(String ProId) {
                    this.ProId = ProId;
                }

                public String getProNum() {
                    return ProNum;
                }

                public void setProNum(String ProNum) {
                    this.ProNum = ProNum;
                }

                public String getStyleId() {
                    return StyleId;
                }

                public void setStyleId(String StyleId) {
                    this.StyleId = StyleId;
                }

                public String getSupId() {
                    return SupId;
                }

                public void setSupId(String SupId) {
                    this.SupId = SupId;
                }

                public String getPv() {
                    return Pv;
                }

                public void setPv(String Pv) {
                    this.Pv = Pv;
                }

                public String getShopPrice() {
                    return ShopPrice;
                }

                public void setShopPrice(String ShopPrice) {
                    this.ShopPrice = ShopPrice;
                }

                public String getProName() {
                    return ProName;
                }

                public void setProName(String ProName) {
                    this.ProName = ProName;
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

                public String getProImg() {
                    return ProImg;
                }

                public void setProImg(String ProImg) {
                    this.ProImg = ProImg;
                }

                public String getStyleName() {
                    return StyleName;
                }

                public void setStyleName(String StyleName) {
                    this.StyleName = StyleName;
                }

                public String getKucun() {
                    return Kucun;
                }

                public void setKucun(String Kucun) {
                    this.Kucun = Kucun;
                }

                public String getSupplier() {
                    return Supplier;
                }

                public void setSupplier(String Supplier) {
                    this.Supplier = Supplier;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                @Override
                public String toString() {
                    return "ProductBean{" +
                            "shopcartid='" + shopcartid + '\'' +
                            ", ProId='" + ProId + '\'' +
                            ", ProNum='" + ProNum + '\'' +
                            ", StyleId='" + StyleId + '\'' +
                            ", SupId='" + SupId + '\'' +
                            ", Pv='" + Pv + '\'' +
                            ", ShopPrice='" + ShopPrice + '\'' +
                            ", ProName='" + ProName + '\'' +
                            ", MinPurchase='" + MinPurchase + '\'' +
                            ", OrderStep='" + OrderStep + '\'' +
                            ", ProImg='" + ProImg + '\'' +
                            ", StyleName='" + StyleName + '\'' +
                            ", Kucun='" + Kucun + '\'' +
                            ", Supplier='" + Supplier + '\'' +
                            ", money='" + money + '\'' +
                            ", storename='" + storename + '\'' +
                            ", isshowtitle=" + isshowtitle +
                            ", ischeck=" + ischeck +
                            ", isstorecheck=" + isstorecheck +
                            ", isbianji=" + isbianji +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "name='" + name + '\'' +
                        ", product=" + product +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status='" + status + '\'' +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShoppingCarBean{" +
                "data=" + data +
                '}';
    }
}
