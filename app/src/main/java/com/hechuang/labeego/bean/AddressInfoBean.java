package com.hechuang.labeego.bean;

public class AddressInfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功","list":{"Id":"1902","UserId":"96112304","ReceiveName":"测试01","Province":"河南省","City":"郑州市","County":"新郑市","Address":"龙湖","Mobile":"18808889666","IsDefault":"1"}}
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
         * list : {"Id":"1902","UserId":"96112304","ReceiveName":"测试01","Province":"河南省","City":"郑州市","County":"新郑市","Address":"龙湖","Mobile":"18808889666","IsDefault":"1"}
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
             * Id : 1902
             * UserId : 96112304
             * ReceiveName : 测试01
             * Province : 河南省
             * City : 郑州市
             * County : 新郑市
             * Address : 龙湖
             * Mobile : 18808889666
             * IsDefault : 1
             */

            private String Id;
            private String UserId;
            private String ReceiveName;
            private String Province;
            private String City;
            private String County;
            private String Address;
            private String Mobile;
            private String IsDefault;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getReceiveName() {
                return ReceiveName;
            }

            public void setReceiveName(String ReceiveName) {
                this.ReceiveName = ReceiveName;
            }

            public String getProvince() {
                return Province;
            }

            public void setProvince(String Province) {
                this.Province = Province;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getCounty() {
                return County;
            }

            public void setCounty(String County) {
                this.County = County;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
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
        }
    }
}
