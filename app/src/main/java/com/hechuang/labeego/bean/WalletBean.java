package com.hechuang.labeego.bean;

import java.util.List;

public class WalletBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"money_img":"http://lafeng.99xyg.com/Public/static/labeego/images/labee.png","money":"11.09","price":"￥1.080","title":"参考市值","bring_forward":[{"title":"转账","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/20.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labeeturn"},{"title":"转出","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/21.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labee_to_beehive"},{"title":"转换蜂币","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/22.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labee_to_beemoney"},{"title":"交易明细","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/23.png","url":"http://lafeng.99xyg.com/index.php/Home/Record/labee_record"}],"note_header":"转入须知","note_list":["1.助记词是离线钱包的安全秘钥，丢失无法找回！","2.转入需要经过自动网络安全确认才能到帐，因网络访问量大、确认时间长，转入通需要30分钟左右。"],"download":[{"title":"离线钱包下载","img":"http://lafeng.99xyg.com/Public/static/labeego/images/labee.png","url":"http://lafeng.99xyg.com/Public/Appload/labee.html"},{"title":"交易平台下载","img":"http://lafeng.99xyg.com/Public/static/labeego/images/okcex.png","url":"http://lafeng.99xyg.com/Public/Appload/okcex.html"}]}}
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
         * message : 数据获取成功!
         * list : {"money_img":"http://lafeng.99xyg.com/Public/static/labeego/images/labee.png","money":"11.09","price":"￥1.080","title":"参考市值","bring_forward":[{"title":"转账","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/20.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labeeturn"},{"title":"转出","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/21.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labee_to_beehive"},{"title":"转换蜂币","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/22.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labee_to_beemoney"},{"title":"交易明细","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/23.png","url":"http://lafeng.99xyg.com/index.php/Home/Record/labee_record"}],"note_header":"转入须知","note_list":["1.助记词是离线钱包的安全秘钥，丢失无法找回！","2.转入需要经过自动网络安全确认才能到帐，因网络访问量大、确认时间长，转入通需要30分钟左右。"],"download":[{"title":"离线钱包下载","img":"http://lafeng.99xyg.com/Public/static/labeego/images/labee.png","url":"http://lafeng.99xyg.com/Public/Appload/labee.html"},{"title":"交易平台下载","img":"http://lafeng.99xyg.com/Public/static/labeego/images/okcex.png","url":"http://lafeng.99xyg.com/Public/Appload/okcex.html"}]}
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
             * money_img : http://lafeng.99xyg.com/Public/static/labeego/images/labee.png
             * money : 11.09
             * price : ￥1.080
             * title : 参考市值
             * bring_forward : [{"title":"转账","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/20.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labeeturn"},{"title":"转出","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/21.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labee_to_beehive"},{"title":"转换蜂币","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/22.png","url":"http://lafeng.99xyg.com/index.php/Home/User/labee_to_beemoney"},{"title":"交易明细","img":"http://lafeng.99xyg.com/Public/static/labeego/images/personal/23.png","url":"http://lafeng.99xyg.com/index.php/Home/Record/labee_record"}]
             * note_header : 转入须知
             * note_list : ["1.助记词是离线钱包的安全秘钥，丢失无法找回！","2.转入需要经过自动网络安全确认才能到帐，因网络访问量大、确认时间长，转入通需要30分钟左右。"]
             * download : [{"title":"离线钱包下载","img":"http://lafeng.99xyg.com/Public/static/labeego/images/labee.png","url":"http://lafeng.99xyg.com/Public/Appload/labee.html"},{"title":"交易平台下载","img":"http://lafeng.99xyg.com/Public/static/labeego/images/okcex.png","url":"http://lafeng.99xyg.com/Public/Appload/okcex.html"}]
             */

            private String money_img;
            private String money;
            private String price;
            private String title;
            private String note_header;
            private List<BringForwardBean> bring_forward;
            private List<String> note_list;
            private List<DownloadBean> download;

            public String getMoney_img() {
                return money_img;
            }

            public void setMoney_img(String money_img) {
                this.money_img = money_img;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getNote_header() {
                return note_header;
            }

            public void setNote_header(String note_header) {
                this.note_header = note_header;
            }

            public List<BringForwardBean> getBring_forward() {
                return bring_forward;
            }

            public void setBring_forward(List<BringForwardBean> bring_forward) {
                this.bring_forward = bring_forward;
            }

            public List<String> getNote_list() {
                return note_list;
            }

            public void setNote_list(List<String> note_list) {
                this.note_list = note_list;
            }

            public List<DownloadBean> getDownload() {
                return download;
            }

            public void setDownload(List<DownloadBean> download) {
                this.download = download;
            }

            public static class BringForwardBean {
                /**
                 * title : 转账
                 * img : http://lafeng.99xyg.com/Public/static/labeego/images/personal/20.png
                 * url : http://lafeng.99xyg.com/index.php/Home/User/labeeturn
                 */

                private String title;
                private String img;
                private String url;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class DownloadBean {
                /**
                 * title : 离线钱包下载
                 * img : http://lafeng.99xyg.com/Public/static/labeego/images/labee.png
                 * url : http://lafeng.99xyg.com/Public/Appload/labee.html
                 */

                private String title;
                private String img;
                private String url;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
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
}
