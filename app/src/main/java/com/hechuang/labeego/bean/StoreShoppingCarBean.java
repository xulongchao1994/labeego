package com.hechuang.labeego.bean;

import java.util.List;

public class StoreShoppingCarBean {

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

            private String SupName;
            private List<ProductBean> no;

            public ListBean(String SupName, List<ProductBean> product) {
                this.SupName = SupName;
                this.no = product;
            }

            public String getName() {
                return SupName;
            }

            public void setName(String name) {
                this.SupName = name;
            }

            public List<ProductBean> getProduct() {
                return no;
            }

            public void setProduct(List<ProductBean> product) {
                this.no = product;
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

                private String ProId;//商品id
                private String ProNum;//商品数量
                private String StyleId;//规格ID
                private String SupId;//商家ID
                private String Subprice;//商品总价格
                private String ProName;//商品名称
                private String minpurchase;//最小起订量
                private String orderstep;//起订量倍数
                private String proimg;//商品图片
                private String StyleName;//规格名称
                private String kucun;//商品库存
                private String price;//商品价格
                private String storename;
                private String shopcartid;
                private Boolean isshowtitle;
                private Boolean ischeck;
                private Boolean isstorecheck;
                private Boolean isbianji;

                public ProductBean(
                        String shopcartid,
                        String proId, String proNum, String styleId, String supId,  String shopPrice, String proName,
                        String minPurchase, String orderStep, String proImg, String styleName, String kucun, String supplier, String money, String storename
                        , String price
                        , Boolean isshowtitle, Boolean ischeck, Boolean isstorecheck, Boolean isbianji
                ) {
                    this.shopcartid = shopcartid;
                    ProId = proId;
                    ProNum = proNum;
                    StyleId = styleId;
                    SupId = supId;
                    Subprice = shopPrice;
                    ProName = proName;
                    this.minpurchase = minPurchase;
                    this.orderstep = orderStep;
                    this.proimg = proImg;
                    StyleName = styleName;
                    this.kucun = kucun;
                    this.price = price;
                    this.storename = storename;
                    this.isshowtitle = isshowtitle;
                    this.ischeck = ischeck;
                    this.isstorecheck = isstorecheck;
                    this.isbianji = isbianji;
                }

                public void setShopcartid(String shopcartid) {
                    this.shopcartid = shopcartid;
                }

                public String getShopcartid() {
                    return shopcartid;
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


                public String getSubprice() {
                    return Subprice;
                }

                public void setSubprice(String ShopPrice) {
                    this.Subprice = ShopPrice;
                }

                public String getProName() {
                    return ProName;
                }

                public void setProName(String ProName) {
                    this.ProName = ProName;
                }

                public String getMinPurchase() {
                    return minpurchase;
                }

                public void setMinPurchase(String MinPurchase) {
                    this.minpurchase = MinPurchase;
                }

                public String getOrderStep() {
                    return orderstep;
                }

                public void setOrderStep(String OrderStep) {
                    this.orderstep = OrderStep;
                }

                public String getProImg() {
                    return proimg;
                }

                public void setProImg(String ProImg) {
                    this.proimg = ProImg;
                }

                public String getStyleName() {
                    return StyleName;
                }

                public void setStyleName(String StyleName) {
                    this.StyleName = StyleName;
                }

                public String getKucun() {
                    return kucun;
                }

                public void setKucun(String Kucun) {
                    this.kucun = Kucun;
                }

                public String getprice() {
                    return price;
                }

                public void setprice(String subprice) {
                    price = subprice;
                }

                @Override
                public String toString() {
                    return "ProductBean{" +
                            ", ProId='" + ProId + '\'' +
                            ", ProNum='" + ProNum + '\'' +
                            ", StyleId='" + StyleId + '\'' +
                            ", SupId='" + SupId + '\'' +
                            ", ShopPrice='" + Subprice + '\'' +
                            ", ProName='" + ProName + '\'' +
                            ", MinPurchase='" + minpurchase + '\'' +
                            ", OrderStep='" + orderstep + '\'' +
                            ", ProImg='" + proimg + '\'' +
                            ", StyleName='" + StyleName + '\'' +
                            ", Kucun='" + kucun + '\'' +
                            ", Subprice='" + Subprice + '\'' +
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
                        "name='" + SupName + '\'' +
                        ", product=" + no +
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
