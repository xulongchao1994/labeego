package com.hechuang.labeego.bean;

import java.util.List;

public class Confirmanorder_Shoppingcar_Bean {

    /**
     * data : {"status":1,"message":"数据获取成功","ReceiveName":"测试","Id":"1270","Mobile":"18838984545","IsDefault":"1","diqu":"北京市北京市东城区川沙路","zongjia":"3000元","list":[{"supname":"上海九美医疗器械有限公司","products":[{"shopcartid":"1032","ProId":"124","ProNum":"2","StyleId":"154","SupId":"7","Pv":"送1000.00","ShopPrice":"1000.00元","Agentd":"hc18337582538","Supplier":"上海九美医疗器械有限公司","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-28/152747673282416.jpg","ProName":"九美-手机眼镜（送九美-蒸汽热敷眼罩 7片/盒）","StyleName":"副","money":"2000元"}],"zong":"2000元(送2000蜂力)"},{"supname":"和创","products":[{"shopcartid":"1031","ProId":"150","ProNum":"1","StyleId":"179","SupId":"1","Pv":"送150.00","ShopPrice":"1000.00元","Agentd":"hc18337582538","Supplier":"和创","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152773365297621.jpg","ProName":"闹闹测试","StyleName":"1000/张","money":"1000元"}],"zong":"1000元(送150蜂力)"}]}
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
         * ReceiveName : 测试
         * Id : 1270
         * Mobile : 18838984545
         * IsDefault : 1
         * diqu : 北京市北京市东城区川沙路
         * zongjia : 3000元
         * list : [{"supname":"上海九美医疗器械有限公司","products":[{"shopcartid":"1032","ProId":"124","ProNum":"2","StyleId":"154","SupId":"7","Pv":"送1000.00","ShopPrice":"1000.00元","Agentd":"hc18337582538","Supplier":"上海九美医疗器械有限公司","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-28/152747673282416.jpg","ProName":"九美-手机眼镜（送九美-蒸汽热敷眼罩 7片/盒）","StyleName":"副","money":"2000元"}],"zong":"2000元(送2000蜂力)"},{"supname":"和创","products":[{"shopcartid":"1031","ProId":"150","ProNum":"1","StyleId":"179","SupId":"1","Pv":"送150.00","ShopPrice":"1000.00元","Agentd":"hc18337582538","Supplier":"和创","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-31/152773365297621.jpg","ProName":"闹闹测试","StyleName":"1000/张","money":"1000元"}],"zong":"1000元(送150蜂力)"}]
         */

        private String status;
        private String message;
        private String ReceiveName;
        private String Id;
        private String Mobile;
        private String IsDefault;
        private String diqu;
        private String zongjia;
        private String order_token;
        private List<ListBean> list;

        public DataBean(String status, String message, String receiveName, String id, String mobile, String isDefault, String diqu, String zongjia, String order_token, List<ListBean> list) {
            this.status = status;
            this.message = message;
            ReceiveName = receiveName;
            Id = id;
            Mobile = mobile;
            IsDefault = isDefault;
            this.diqu = diqu;
            this.zongjia = zongjia;
            this.order_token = order_token;
            this.list = list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", ReceiveName='" + ReceiveName + '\'' +
                    ", Id='" + Id + '\'' +
                    ", Mobile='" + Mobile + '\'' +
                    ", IsDefault='" + IsDefault + '\'' +
                    ", diqu='" + diqu + '\'' +
                    ", zongjia='" + zongjia + '\'' +
                    ", order_token='" + order_token + '\'' +
                    ", list=" + list +
                    '}';
        }

        public void setOrder_token(String order_token) {
            this.order_token = order_token;
        }

        public String getOrder_token() {
            return order_token;
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

        public String getReceiveName() {
            return ReceiveName;
        }

        public void setReceiveName(String ReceiveName) {
            this.ReceiveName = ReceiveName;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(String IsDefault) {
            this.IsDefault = IsDefault;
        }

        public String getDiqu() {
            return diqu;
        }

        public void setDiqu(String diqu) {
            this.diqu = diqu;
        }

        public String getZongjia() {
            return zongjia;
        }

        public void setZongjia(String zongjia) {
            this.zongjia = zongjia;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * supname : 上海九美医疗器械有限公司
             * products : [{"shopcartid":"1032","ProId":"124","ProNum":"2","StyleId":"154","SupId":"7","Pv":"送1000.00","ShopPrice":"1000.00元","Agentd":"hc18337582538","Supplier":"上海九美医疗器械有限公司","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-28/152747673282416.jpg","ProName":"九美-手机眼镜（送九美-蒸汽热敷眼罩 7片/盒）","StyleName":"副","money":"2000元"}]
             * zong : 2000元(送2000蜂力)
             */

            private String supname;
            private String zong;
            private String message;
            private String SupId;
            private List<ProductsBean> products;

            public ListBean(String supname, String zong, String message, String supId, List<ProductsBean> products) {
                this.supname = supname;
                this.zong = zong;
                this.message = message;
                SupId = supId;
                this.products = products;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "supname='" + supname + '\'' +
                        ", zong='" + zong + '\'' +
                        ", message='" + message + '\'' +
                        ", SupId='" + SupId + '\'' +
                        ", products=" + products +
                        '}';
            }

            public void setSupId(String supId) {
                SupId = supId;
            }

            public String getSupId() {
                return SupId;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }

            public String getSupname() {
                return supname;
            }

            public void setSupname(String supname) {
                this.supname = supname;
            }

            public String getZong() {
                return zong;
            }

            public void setZong(String zong) {
                this.zong = zong;
            }

            public List<ProductsBean> getProducts() {
                return products;
            }

            public void setProducts(List<ProductsBean> products) {
                this.products = products;
            }

            public static class ProductsBean {
                /**
                 * shopcartid : 1032
                 * ProId : 124
                 * ProNum : 2
                 * StyleId : 154
                 * SupId : 7
                 * Pv : 送1000.00
                 * ShopPrice : 1000.00元
                 * Agentd : hc18337582538
                 * Supplier : 上海九美医疗器械有限公司
                 * ProImg : http://lafeng.hetianpay.com/Upload/Product/2018-05-28/152747673282416.jpg
                 * ProName : 九美-手机眼镜（送九美-蒸汽热敷眼罩 7片/盒）
                 * StyleName : 副
                 * money : 2000元
                 */

                private String shopcartid;
                private String ProId;
                private String ProNum;
                private String StyleId;
                private String SupId;
                private String Pv;
                private String ShopPrice;
                private String Agentd;
                private String Supplier;
                private String ProImg;
                private String ProName;
                private String StyleName;
                private String money;
                private String zong;

                @Override
                public String toString() {
                    return "ProductsBean{" +
                            "shopcartid='" + shopcartid + '\'' +
                            ", ProId='" + ProId + '\'' +
                            ", ProNum='" + ProNum + '\'' +
                            ", StyleId='" + StyleId + '\'' +
                            ", SupId='" + SupId + '\'' +
                            ", Pv='" + Pv + '\'' +
                            ", ShopPrice='" + ShopPrice + '\'' +
                            ", Agentd='" + Agentd + '\'' +
                            ", Supplier='" + Supplier + '\'' +
                            ", ProImg='" + ProImg + '\'' +
                            ", ProName='" + ProName + '\'' +
                            ", StyleName='" + StyleName + '\'' +
                            ", money='" + money + '\'' +
                            '}';
                }

                public ProductsBean(String shopcartid, String proId, String proNum, String styleId, String supId, String pv,
                                    String shopPrice, String agentd, String supplier, String proImg, String proName, String styleName, String money

                        , String zong) {
                    this.shopcartid = shopcartid;
                    ProId = proId;
                    ProNum = proNum;
                    StyleId = styleId;
                    SupId = supId;
                    Pv = pv;
                    ShopPrice = shopPrice;
                    Agentd = agentd;
                    Supplier = supplier;
                    ProImg = proImg;
                    ProName = proName;
                    StyleName = styleName;
                    this.money = money;
                }

                public void setZong(String zong) {
                    this.zong = zong;
                }

                public String getZong() {
                    return zong;
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

                public String getAgentd() {
                    return Agentd;
                }

                public void setAgentd(String Agentd) {
                    this.Agentd = Agentd;
                }

                public String getSupplier() {
                    return Supplier;
                }

                public void setSupplier(String Supplier) {
                    this.Supplier = Supplier;
                }

                public String getProImg() {
                    return ProImg;
                }

                public void setProImg(String ProImg) {
                    this.ProImg = ProImg;
                }

                public String getProName() {
                    return ProName;
                }

                public void setProName(String ProName) {
                    this.ProName = ProName;
                }

                public String getStyleName() {
                    return StyleName;
                }

                public void setStyleName(String StyleName) {
                    this.StyleName = StyleName;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }
            }
        }
    }
}
