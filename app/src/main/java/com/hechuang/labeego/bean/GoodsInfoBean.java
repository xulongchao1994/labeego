package com.hechuang.labeego.bean;

import java.util.List;

public class GoodsInfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":{"ProId":"42","ProName":"20000组合礼包2【11支芦荟凝胶、30盒私护凝胶、12瓶双清口服液、7盒钙冲剂、6箱老酱酒、12瓶有机凤梨酵素】","Unit":"礼包","MinPurchase":"1","OrderStep":"1","ProContent":"<p><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068376226519.jpg\" title=\"1527068376226519.jpg\" alt=\"1491038608.jpg\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068382144974.jpg\" title=\"1527068382144974.jpg\" alt=\"1513331275.jpg\" width=\"797\" height=\"573\" style=\"width: 797px; height: 573px;\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068387804823.jpg\" title=\"1527068387804823.jpg\" alt=\"1513416222.jpg\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068395214857.jpg\" title=\"1527068395214857.jpg\" alt=\"1513417398.jpg\" width=\"787\" height=\"600\" style=\"width: 787px; height: 600px;\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068401136729.jpg\" title=\"1527068401136729.jpg\" alt=\"1515138211.jpg\" width=\"785\" height=\"800\" style=\"width: 785px; height: 800px;\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068406120691.jpg\" title=\"1527068406120691.jpg\" alt=\"1525432331.jpg\" width=\"785\" height=\"800\" style=\"width: 785px; height: 800px;\"/><\/p>","shouhou":"包邮","goodsnum":"","imgs":["http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg"],"price":"￥20000.00","integral":"送 20000.00","goodsstyle":[{"Kucun":"99999","StyleName":"20000组合礼包2","StyleId":"71","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg"}],"Supplier":"公司代发"}}
     */

    private DataBean data;

    public GoodsInfoBean(DataBean data) {
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
         * list : {"ProId":"42","ProName":"20000组合礼包2【11支芦荟凝胶、30盒私护凝胶、12瓶双清口服液、7盒钙冲剂、6箱老酱酒、12瓶有机凤梨酵素】","Unit":"礼包","MinPurchase":"1","OrderStep":"1","ProContent":"<p><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068376226519.jpg\" title=\"1527068376226519.jpg\" alt=\"1491038608.jpg\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068382144974.jpg\" title=\"1527068382144974.jpg\" alt=\"1513331275.jpg\" width=\"797\" height=\"573\" style=\"width: 797px; height: 573px;\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068387804823.jpg\" title=\"1527068387804823.jpg\" alt=\"1513416222.jpg\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068395214857.jpg\" title=\"1527068395214857.jpg\" alt=\"1513417398.jpg\" width=\"787\" height=\"600\" style=\"width: 787px; height: 600px;\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068401136729.jpg\" title=\"1527068401136729.jpg\" alt=\"1515138211.jpg\" width=\"785\" height=\"800\" style=\"width: 785px; height: 800px;\"/><img src=\"http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068406120691.jpg\" title=\"1527068406120691.jpg\" alt=\"1525432331.jpg\" width=\"785\" height=\"800\" style=\"width: 785px; height: 800px;\"/><\/p>","shouhou":"包邮","goodsnum":"","imgs":["http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg"],"price":"￥20000.00","integral":"送 20000.00","goodsstyle":[{"Kucun":"99999","StyleName":"20000组合礼包2","StyleId":"71","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg"}],"Supplier":"公司代发"}
         */

        private int status;
        private String message;
        private ListBean list;

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
        }

        public DataBean(int status, String message, ListBean list) {
            this.status = status;
            this.message = message;
            this.list = list;
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

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ProId : 42
             * ProName : 20000组合礼包2【11支芦荟凝胶、30盒私护凝胶、12瓶双清口服液、7盒钙冲剂、6箱老酱酒、12瓶有机凤梨酵素】
             * Unit : 礼包
             * MinPurchase : 1
             * OrderStep : 1
             * ProContent : <p><img src="http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068376226519.jpg" title="1527068376226519.jpg" alt="1491038608.jpg"/><img src="http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068382144974.jpg" title="1527068382144974.jpg" alt="1513331275.jpg" width="797" height="573" style="width: 797px; height: 573px;"/><img src="http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068387804823.jpg" title="1527068387804823.jpg" alt="1513416222.jpg"/><img src="http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068395214857.jpg" title="1527068395214857.jpg" alt="1513417398.jpg" width="787" height="600" style="width: 787px; height: 600px;"/><img src="http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068401136729.jpg" title="1527068401136729.jpg" alt="1515138211.jpg" width="785" height="800" style="width: 785px; height: 800px;"/><img src="http://lafeng.hetianpay.com/upload/ProInfo/image/20180523/1527068406120691.jpg" title="1527068406120691.jpg" alt="1525432331.jpg" width="785" height="800" style="width: 785px; height: 800px;"/></p>
             * shouhou : 包邮
             * goodsnum :
             * imgs : ["http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg"]
             * price : ￥20000.00
             * integral : 送 20000.00
             * goodsstyle : [{"Kucun":"99999","StyleName":"20000组合礼包2","StyleId":"71","ProImg":"http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg"}]
             * Supplier : 公司代发
             */

            private String ProId;
            private String ProName;
            private String Unit;
            private String MinPurchase;
            private String OrderStep;
            private String ProContent;
            private String shouhou;
            private String goodsnum;
            private String price;
            private String integral;
            private String Supplier;
            private List<String> imgs;
            private List<GoodsstyleBean> goodsstyle;
            private String avg;
            private String count;
            private List<UserinfoBean> userinfo;

            public ListBean(String proId, String proName, String unit, String minPurchase, String orderStep, String proContent, String shouhou, String goodsnum, String price, String integral,
                            String supplier, List<String> imgs, List<GoodsstyleBean> goodsstyle, String avg, List<UserinfoBean> userinfo,String count) {
                ProId = proId;
                ProName = proName;
                Unit = unit;
                MinPurchase = minPurchase;
                OrderStep = orderStep;
                ProContent = proContent;
                this.shouhou = shouhou;
                this.goodsnum = goodsnum;
                this.price = price;
                this.integral = integral;
                Supplier = supplier;
                this.imgs = imgs;
                this.goodsstyle = goodsstyle;
                this.avg = avg;
                this.userinfo = userinfo;
                this.count = count;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "ProId='" + ProId + '\'' +
                        ", ProName='" + ProName + '\'' +
                        ", Unit='" + Unit + '\'' +
                        ", MinPurchase='" + MinPurchase + '\'' +
                        ", OrderStep='" + OrderStep + '\'' +
                        ", ProContent='" + ProContent + '\'' +
                        ", shouhou='" + shouhou + '\'' +
                        ", goodsnum='" + goodsnum + '\'' +
                        ", price='" + price + '\'' +
                        ", integral='" + integral + '\'' +
                        ", Supplier='" + Supplier + '\'' +
                        ", imgs=" + imgs +
                        ", goodsstyle=" + goodsstyle +
                        ", avg='" + avg + '\'' +
                        ", count='" + count + '\'' +
                        ", userinfo=" + userinfo +
                        '}';
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getCount() {
                return count;
            }

            public void setUserinfo(List<UserinfoBean> userinfo) {
                this.userinfo = userinfo;
            }

            public List<UserinfoBean> getUserinfo() {
                return userinfo;
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

            public String getShouhou() {
                return shouhou;
            }

            public void setShouhou(String shouhou) {
                this.shouhou = shouhou;
            }

            public String getGoodsnum() {
                return goodsnum;
            }

            public void setGoodsnum(String goodsnum) {
                this.goodsnum = goodsnum;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIntegral() {
                return integral;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
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

            public void setAvg(String avg) {
                this.avg = avg;
            }

//            public void setUserinfo(List<UserinfoBean> userinfo) {
//                this.userinfo = userinfo;
//            }

            public String getAvg() {
                return avg;
            }

//            public List<UserinfoBean> getUserinfo() {
//                return userinfo;
//            }

            public void setGoodsstyle(List<GoodsstyleBean> goodsstyle) {
                this.goodsstyle = goodsstyle;
            }

            public static class UserinfoBean {
                private String turename;
                private String avatarurl;
                private String comment;

                public UserinfoBean(String turename, String avatarurl, String comment) {
                    this.turename = turename;
                    this.avatarurl = avatarurl;
                    this.comment = comment;
                }

                @Override
                public String toString() {
                    return "UserinfoBean{" +
                            "turename='" + turename + '\'' +
                            ", avatarurl='" + avatarurl + '\'' +
                            ", comment='" + comment + '\'' +
                            '}';
                }

                public void setTurename(String turename) {
                    this.turename = turename;
                }

                public void setAvatarurl(String avatarurl) {
                    this.avatarurl = avatarurl;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getTurename() {
                    return turename;
                }

                public String getAvatarurl() {
                    return avatarurl;
                }

                public String getComment() {
                    return comment;
                }
            }

            public static class GoodsstyleBean {
                /**
                 * Kucun : 99999
                 * StyleName : 20000组合礼包2
                 * StyleId : 71
                 * ProImg : http://lafeng.hetianpay.com/Upload/Product/2018-05-23/152706730618706.jpg
                 */

                private String Kucun;
                private String StyleName;
                private String StyleId;
                private String ProImg;
                private String integral;
                private String price;

                public GoodsstyleBean(String kucun, String styleName, String styleId, String proImg, String integral, String price) {
                    Kucun = kucun;
                    StyleName = styleName;
                    StyleId = styleId;
                    ProImg = proImg;
                    this.integral = integral;
                    this.price = price;
                }

                @Override
                public String toString() {
                    return "GoodsstyleBean{" +
                            "Kucun='" + Kucun + '\'' +
                            ", StyleName='" + StyleName + '\'' +
                            ", StyleId='" + StyleId + '\'' +
                            ", ProImg='" + ProImg + '\'' +
                            ", integral='" + integral + '\'' +
                            ", price='" + price + '\'' +
                            '}';
                }

                public void setIntegral(String integral) {
                    this.integral = integral;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getIntegral() {
                    return integral;
                }

                public String getPrice() {
                    return price;
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
