package com.hechuang.labeego.bean;

public class UserInfoBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"truename":{"truename":"闹闹","title":"姓名"},"mobile":{"mobile":"18738111470","title":"手机"},"idcardno":{"idcardno":"","title":"身份证号"}}}
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
         * list : {"truename":{"truename":"闹闹","title":"姓名"},"mobile":{"mobile":"18738111470","title":"手机"},"idcardno":{"idcardno":"","title":"身份证号"}}
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
             * truename : {"truename":"闹闹","title":"姓名"}
             * mobile : {"mobile":"18738111470","title":"手机"}
             * idcardno : {"idcardno":"","title":"身份证号"}
             */

            private TruenameBean truename;
            private MobileBean mobile;
            private IdcardnoBean idcardno;

            public TruenameBean getTruename() {
                return truename;
            }

            public void setTruename(TruenameBean truename) {
                this.truename = truename;
            }

            public MobileBean getMobile() {
                return mobile;
            }

            public void setMobile(MobileBean mobile) {
                this.mobile = mobile;
            }

            public IdcardnoBean getIdcardno() {
                return idcardno;
            }

            public void setIdcardno(IdcardnoBean idcardno) {
                this.idcardno = idcardno;
            }

            public static class TruenameBean {
                /**
                 * truename : 闹闹
                 * title : 姓名
                 */

                private String truename;
                private String title;

                public String getTruename() {
                    return truename;
                }

                public void setTruename(String truename) {
                    this.truename = truename;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class MobileBean {
                /**
                 * mobile : 18738111470
                 * title : 手机
                 */

                private String mobile;
                private String title;

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class IdcardnoBean {
                /**
                 * idcardno :
                 * title : 身份证号
                 */

                private String idcardno;
                private String title;

                public String getIdcardno() {
                    return idcardno;
                }

                public void setIdcardno(String idcardno) {
                    this.idcardno = idcardno;
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
