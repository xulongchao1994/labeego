package com.hechuang.labeego.bean;

import java.util.List;

public class Allianceshop_shop_bean {

    /**
     * data : {"list":[{"ProId":"","ProName":"","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475815765198.jpg","price":"","marketprice":"","url":""},{"ProId":"22","ProName":"牛肉","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154477416634845.jpg","price":"40.00元","marketprice":"45.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/22"},{"ProId":"16","ProName":"雪碧","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475815765198.jpg","price":"6.00元","marketprice":"6.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/16"},{"ProId":"13","ProName":"马卡龙","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475781868178.jpg","price":"55.00元","marketprice":"58.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/13"},{"ProId":"11","ProName":"江小白","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475768247597.jpg","price":"99.00元","marketprice":"100.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/11"}],"msg":"加载成功！","status":1}
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
         * list : [{"ProId":"","ProName":"","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475815765198.jpg","price":"","marketprice":"","url":""},{"ProId":"22","ProName":"牛肉","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154477416634845.jpg","price":"40.00元","marketprice":"45.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/22"},{"ProId":"16","ProName":"雪碧","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475815765198.jpg","price":"6.00元","marketprice":"6.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/16"},{"ProId":"13","ProName":"马卡龙","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475781868178.jpg","price":"55.00元","marketprice":"58.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/13"},{"ProId":"11","ProName":"江小白","ProImg":"http://192.168.10.219:8066/Upload/product/2018-12-14/154475768247597.jpg","price":"99.00元","marketprice":"100.00元","url":"http://192.168.10.219:8066/index.php/Home/Unproduct/product_details/proid/11"}]
         * msg : 加载成功！
         * status : 1
         */

        private String msg;
        private int status;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ProId :
             * ProName :
             * ProImg : http://192.168.10.219:8066/Upload/product/2018-12-14/154475815765198.jpg
             * price :
             * marketprice :
             * url :
             */

            private String ProId;
            private String ProName;
            private String ProImg;
            private String price;
            private String marketprice;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
