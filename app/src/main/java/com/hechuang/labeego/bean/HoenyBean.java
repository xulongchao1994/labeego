package com.hechuang.labeego.bean;

import java.util.List;

public class HoenyBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"header":{"title":"蜂蜜数量","count":"1179.29"},"centre":{"title":"转入啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/beehoney_to_labee/id/2"},"record":[{"y":"2018年08月","list":[{"Id":"310538","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"99.29","BalanceAmount":"1179.29","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-21 09:29:59"},{"Id":"310529","UserId":"9611230","TypeName":"转入啦蜂","Amount":"-0.99","BalanceAmount":"1080","FromWho":"9611230","Memo":"蜂王蜂蜜转入啦蜂","AddTime":"2018-08-16 15:37:08"},{"Id":"310528","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"1080.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"310527","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"987.01","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"310526","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.04","BalanceAmount":"893.03","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310525","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"799.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310524","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"794.76","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310505","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"92.58","BalanceAmount":"789.53","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:30:42"},{"Id":"310504","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"696.95","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:14"},{"Id":"310503","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.51","BalanceAmount":"691.72","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:11"}]}]}}
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
         * list : {"header":{"title":"蜂蜜数量","count":"1179.29"},"centre":{"title":"转入啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/beehoney_to_labee/id/2"},"record":[{"y":"2018年08月","list":[{"Id":"310538","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"99.29","BalanceAmount":"1179.29","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-21 09:29:59"},{"Id":"310529","UserId":"9611230","TypeName":"转入啦蜂","Amount":"-0.99","BalanceAmount":"1080","FromWho":"9611230","Memo":"蜂王蜂蜜转入啦蜂","AddTime":"2018-08-16 15:37:08"},{"Id":"310528","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"1080.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"310527","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"987.01","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"310526","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.04","BalanceAmount":"893.03","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310525","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"799.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310524","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"794.76","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310505","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"92.58","BalanceAmount":"789.53","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:30:42"},{"Id":"310504","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"696.95","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:14"},{"Id":"310503","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.51","BalanceAmount":"691.72","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:11"}]}]}
         */

        private int status;
        private String message;
        private ListBeanX list;

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

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class ListBeanX {
            @Override
            public String toString() {
                return "ListBeanX{" +
                        "header=" + header +
                        ", centre=" + centre +
                        ", record=" + record +
                        '}';
            }

            /**
             * header : {"title":"蜂蜜数量","count":"1179.29"}
             * centre : {"title":"转入啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/beehoney_to_labee/id/2"}
             * record : [{"y":"2018年08月","list":[{"Id":"310538","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"99.29","BalanceAmount":"1179.29","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-21 09:29:59"},{"Id":"310529","UserId":"9611230","TypeName":"转入啦蜂","Amount":"-0.99","BalanceAmount":"1080","FromWho":"9611230","Memo":"蜂王蜂蜜转入啦蜂","AddTime":"2018-08-16 15:37:08"},{"Id":"310528","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"1080.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"310527","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"987.01","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"310526","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.04","BalanceAmount":"893.03","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310525","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"799.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310524","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"794.76","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310505","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"92.58","BalanceAmount":"789.53","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:30:42"},{"Id":"310504","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"696.95","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:14"},{"Id":"310503","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.51","BalanceAmount":"691.72","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:11"}]}]
             */

            private HeaderBean header;
            private List<CentreBean> centre;
            private List<RecordBean> record;

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public List<CentreBean> getCentre() {
                return centre;
            }

            public void setCentre(List<CentreBean> centre) {
                this.centre = centre;
            }

            public List<RecordBean> getRecord() {
                return record;
            }

            public void setRecord(List<RecordBean> record) {
                this.record = record;
            }

            public static class HeaderBean {
                /**
                 * title : 蜂蜜数量
                 * count : 1179.29
                 */

                private String title;
                private String count;

                @Override
                public String toString() {
                    return "HeaderBean{" +
                            "title='" + title + '\'' +
                            ", count='" + count + '\'' +
                            '}';
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }
            }

            public static class CentreBean {
                /**
                 * title : 转入啦蜂
                 * url : http://lafeng.99xyg.com/index.php/Home/User/beehoney_to_labee/id/2
                 */

                private String title;
                private String url;

                @Override
                public String toString() {
                    return "CentreBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class RecordBean {
                @Override
                public String toString() {
                    return "RecordBean{" +
                            "y='" + y + '\'' +
                            ", list=" + list +
                            '}';
                }

                /**
                 * y : 2018年08月
                 * list : [{"Id":"310538","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"99.29","BalanceAmount":"1179.29","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-21 09:29:59"},{"Id":"310529","UserId":"9611230","TypeName":"转入啦蜂","Amount":"-0.99","BalanceAmount":"1080","FromWho":"9611230","Memo":"蜂王蜂蜜转入啦蜂","AddTime":"2018-08-16 15:37:08"},{"Id":"310528","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"1080.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"310527","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.98","BalanceAmount":"987.01","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"310526","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.04","BalanceAmount":"893.03","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310525","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"799.99","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310524","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"794.76","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 17:55:22"},{"Id":"310505","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"92.58","BalanceAmount":"789.53","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:30:42"},{"Id":"310504","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"5.23","BalanceAmount":"696.95","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:14"},{"Id":"310503","UserId":"9611230","TypeName":"蜂王静态释放","Amount":"93.51","BalanceAmount":"691.72","FromWho":"","Memo":"蜂王静态释放","AddTime":"2018-08-07 11:28:11"}]
                 */

                private String y;
                private List<ListBean> list;

                public String getY() {
                    return y;
                }

                public void setY(String y) {
                    this.y = y;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * Id : 310538
                     * UserId : 9611230
                     * TypeName : 蜂王静态释放
                     * Amount : 99.29
                     * BalanceAmount : 1179.29
                     * FromWho :
                     * Memo : 蜂王静态释放
                     * AddTime : 2018-08-21 09:29:59
                     */

                    private String Id;
                    private String UserId;
                    private String TypeName;
                    private String Amount;
                    private String BalanceAmount;
                    private String FromWho;
                    private String Memo;
                    private String AddTime;

                    @Override
                    public String toString() {
                        return "ListBean{" +
                                "Id='" + Id + '\'' +
                                ", UserId='" + UserId + '\'' +
                                ", TypeName='" + TypeName + '\'' +
                                ", Amount='" + Amount + '\'' +
                                ", BalanceAmount='" + BalanceAmount + '\'' +
                                ", FromWho='" + FromWho + '\'' +
                                ", Memo='" + Memo + '\'' +
                                ", AddTime='" + AddTime + '\'' +
                                '}';
                    }

                    public ListBean(String id, String userId, String typeName, String amount, String balanceAmount, String fromWho, String memo, String addTime) {
                        Id = id;
                        UserId = userId;
                        TypeName = typeName;
                        Amount = amount;
                        BalanceAmount = balanceAmount;
                        FromWho = fromWho;
                        Memo = memo;
                        AddTime = addTime;
                    }

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

                    public String getTypeName() {
                        return TypeName;
                    }

                    public void setTypeName(String TypeName) {
                        this.TypeName = TypeName;
                    }

                    public String getAmount() {
                        return Amount;
                    }

                    public void setAmount(String Amount) {
                        this.Amount = Amount;
                    }

                    public String getBalanceAmount() {
                        return BalanceAmount;
                    }

                    public void setBalanceAmount(String BalanceAmount) {
                        this.BalanceAmount = BalanceAmount;
                    }

                    public String getFromWho() {
                        return FromWho;
                    }

                    public void setFromWho(String FromWho) {
                        this.FromWho = FromWho;
                    }

                    public String getMemo() {
                        return Memo;
                    }

                    public void setMemo(String Memo) {
                        this.Memo = Memo;
                    }

                    public String getAddTime() {
                        return AddTime;
                    }

                    public void setAddTime(String AddTime) {
                        this.AddTime = AddTime;
                    }
                }
            }
        }
    }
}
