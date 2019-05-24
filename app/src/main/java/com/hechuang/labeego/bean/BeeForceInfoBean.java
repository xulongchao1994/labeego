package com.hechuang.labeego.bean;

import java.util.List;

public class BeeForceInfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"amount":"1387.963","title":"交易成功","typename":"赠送","infolist":[{"infolist":"53840.253","title":"账户余额","amount":"购买商品赠送(久零订单:2018082811271529796)"},{"amount":"购买商品赠送(久零订单:2018082811271529796)","title":"备注"},{"amount":"2018-08-28 11:27:39","title":"交易时间"}]}}
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
         * list : {"amount":"1387.963","title":"交易成功","typename":"赠送","infolist":[{"infolist":"53840.253","title":"账户余额"},{"amount":"购买商品赠送(久零订单:2018082811271529796)","title":"备注"},{"amount":"2018-08-28 11:27:39","title":"交易时间"}]}
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
             * amount : 1387.963
             * title : 交易成功
             * typename : 赠送
             * infolist : [{"infolist":"53840.253","title":"账户余额"},{"amount":"购买商品赠送(久零订单:2018082811271529796)","title":"备注"},{"amount":"2018-08-28 11:27:39","title":"交易时间"}]
             */

            private String amount;
            private String title;
            private String typename;
            private List<InfolistBean> infolist;

            @Override
            public String toString() {
                return "ListBean{" +
                        "amount='" + amount + '\'' +
                        ", title='" + title + '\'' +
                        ", typename='" + typename + '\'' +
                        ", infolist=" + infolist +
                        '}';
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public List<InfolistBean> getInfolist() {
                return infolist;
            }

            public void setInfolist(List<InfolistBean> infolist) {
                this.infolist = infolist;
            }

            public static class InfolistBean {
                @Override
                public String toString() {
                    return "InfolistBean{" +
                            "infolist='" + infolist + '\'' +
                            ", title='" + title + '\'' +
                            '}';
                }

                /**
                 * infolist : 53840.253
                 * title : 账户余额
                 */

                private String infolist;
                private String title;

                public String getInfolist() {
                    return infolist;
                }

                public void setInfolist(String infolist) {
                    this.infolist = infolist;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

            }
        }
    }
}
