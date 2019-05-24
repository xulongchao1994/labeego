package com.hechuang.labeego.bean;

import java.util.List;

public class Shoppingcar_Guige_bean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":{"StyleName":[{"StyleName":"20000组合礼包5","StyleId":"82"}]}}
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
         * list : {"StyleName":[{"StyleName":"20000组合礼包5","StyleId":"82"}]}
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
            private List<StyleNameBean> StyleName;

            public List<StyleNameBean> getStyleName() {
                return StyleName;
            }

            public void setStyleName(List<StyleNameBean> StyleName) {
                this.StyleName = StyleName;
            }

            public static class StyleNameBean {
                /**
                 * StyleName : 20000组合礼包5
                 * StyleId : 82
                 */

                private String StyleName;
                private String StyleId;
                private String ProId;
                private String integral;
                private String price;
                private String Kucun;

                public StyleNameBean(String styleName, String styleId, String proId, String integral, String price, String kucun) {
                    StyleName = styleName;
                    StyleId = styleId;
                    ProId = proId;
                    this.integral = integral;
                    this.price = price;
                    Kucun = kucun;
                }

                public void setProId(String proId) {
                    ProId = proId;
                }

                public void setIntegral(String integral) {
                    this.integral = integral;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public void setKucun(String kucun) {
                    Kucun = kucun;
                }

                public String getProId() {
                    return ProId;
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
            }
        }
    }
}
