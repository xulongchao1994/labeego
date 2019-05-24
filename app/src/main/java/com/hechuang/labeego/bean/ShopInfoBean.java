package com.hechuang.labeego.bean;

import java.util.List;

public class ShopInfoBean {

    /**
     * data : {"list":{"Name":"茅台镇珍豪酒业有限公司","Address":"北京市北京市东城区仓库","product":[{"ProId":"22","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154477416634845.jpg","ProName":"牛肉","price":"40.00元","marketprice":"45.00元","adbeefee":"可抵扣1.40","proNum":"3人付款","isshipping":""}]},"msg":"加载成功！","status":1}
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
         * list : {"Name":"茅台镇珍豪酒业有限公司","Address":"北京市北京市东城区仓库","product":[{"ProId":"22","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154477416634845.jpg","ProName":"牛肉","price":"40.00元","marketprice":"45.00元","adbeefee":"可抵扣1.40","proNum":"3人付款","isshipping":""}]}
         * msg : 加载成功！
         * status : 1
         */

        private ListBean list;
        private String msg;
        private int status;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

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

        public static class ListBean {
            /**
             * Name : 茅台镇珍豪酒业有限公司
             * Address : 北京市北京市东城区仓库
             * product : [{"ProId":"22","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154477416634845.jpg","ProName":"牛肉","price":"40.00元","marketprice":"45.00元","adbeefee":"可抵扣1.40","proNum":"3人付款","isshipping":""}]
             */

            private String Name;
            private String Address;
            private String imgpath;
            private List<ProductBean> product;

            public String getImgpath() {
                return imgpath;
            }

            public void setImgpath(String imgpath) {
                this.imgpath = imgpath;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public List<ProductBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBean> product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * ProId : 22
                 * ProImg : http://192.168.10.219:8066/Upload/product/2018-12-14/154477416634845.jpg
                 * ProName : 牛肉
                 * price : 40.00元
                 * marketprice : 45.00元
                 * adbeefee : 可抵扣1.40
                 * proNum : 3人付款
                 * isshipping :
                 */

                private String ProId;
                private String ProImg;
                private String ProName;
                private String price;
                private String marketprice;
                private String adbeefee;
                private String proNum;
                private String isshipping;
                private String url;

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return url;
                }

                public String getProId() {
                    return ProId;
                }

                public void setProId(String ProId) {
                    this.ProId = ProId;
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

                public String getAdbeefee() {
                    return adbeefee;
                }

                public void setAdbeefee(String adbeefee) {
                    this.adbeefee = adbeefee;
                }

                public String getProNum() {
                    return proNum;
                }

                public void setProNum(String proNum) {
                    this.proNum = proNum;
                }

                public String getIsshipping() {
                    return isshipping;
                }

                public void setIsshipping(String isshipping) {
                    this.isshipping = isshipping;
                }
            }
        }
    }
}
