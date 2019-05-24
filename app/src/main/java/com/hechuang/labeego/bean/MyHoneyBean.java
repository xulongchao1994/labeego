package com.hechuang.labeego.bean;

import java.util.List;

public class MyHoneyBean {

    /**
     * data : {"status":1,"message":"数据获取成功!","list":{"header":{"title":"蜂浆数量","count":"203.44"},"price_trend":{"title":"价格走势","url":"http://lafeng.99xyg.com/index.php/Home/ECharts/honey_harts"},"centre":[{"title":"转换蜂币","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_beemoney"},{"title":"转入啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_labee"}],"record":[{"y":"2018年08月","list":[{"Id":"472760","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"203.44","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"472759","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"152.48","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472758","UserId":"9611230","TypeName":"转入","Amount":"50.81","BalanceAmount":"101.52","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472757","UserId":"9611230","TypeName":"转入","Amount":"50.71","BalanceAmount":"50.71","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 11:30:40"}]}]}}
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
         * list : {"header":{"title":"蜂浆数量","count":"203.44"},"price_trend":{"title":"价格走势","url":"http://lafeng.99xyg.com/index.php/Home/ECharts/honey_harts"},"centre":[{"title":"转换蜂币","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_beemoney"},{"title":"转入啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_labee"}],"record":[{"y":"2018年08月","list":[{"Id":"472760","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"203.44","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"472759","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"152.48","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472758","UserId":"9611230","TypeName":"转入","Amount":"50.81","BalanceAmount":"101.52","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472757","UserId":"9611230","TypeName":"转入","Amount":"50.71","BalanceAmount":"50.71","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 11:30:40"}]}]}
         */

        private int status;
        private String message;
        private ListBeanX list;

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
            /**
             * header : {"title":"蜂浆数量","count":"203.44"}
             * price_trend : {"title":"价格走势","url":"http://lafeng.99xyg.com/index.php/Home/ECharts/honey_harts"}
             * centre : [{"title":"转换蜂币","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_beemoney"},{"title":"转入啦蜂","url":"http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_labee"}]
             * record : [{"y":"2018年08月","list":[{"Id":"472760","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"203.44","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"472759","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"152.48","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472758","UserId":"9611230","TypeName":"转入","Amount":"50.81","BalanceAmount":"101.52","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472757","UserId":"9611230","TypeName":"转入","Amount":"50.71","BalanceAmount":"50.71","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 11:30:40"}]}]
             */

            private HeaderBean header;
            private PriceTrendBean price_trend;
            private List<CentreBean> centre;
            private List<RecordBean> record;

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public PriceTrendBean getPrice_trend() {
                return price_trend;
            }

            public void setPrice_trend(PriceTrendBean price_trend) {
                this.price_trend = price_trend;
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
                 * title : 蜂浆数量
                 * count : 203.44
                 */

                private String title;
                private String count;

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

                @Override
                public String toString() {
                    return "HeaderBean{" +
                            "title='" + title + '\'' +
                            ", count='" + count + '\'' +
                            '}';
                }
            }

            public static class PriceTrendBean {
                /**
                 * title : 价格走势
                 * url : http://lafeng.99xyg.com/index.php/Home/ECharts/honey_harts
                 */

                private String title;
                private String url;

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

                @Override
                public String toString() {
                    return "PriceTrendBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }
            }

            public static class CentreBean {
                /**
                 * title : 转换蜂币
                 * url : http://lafeng.99xyg.com/index.php/Home/User/apiserum_to_beemoney
                 */

                private String title;
                private String url;

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

                @Override
                public String toString() {
                    return "CentreBean{" +
                            "title='" + title + '\'' +
                            ", url='" + url + '\'' +
                            '}';
                }
            }

            public static class RecordBean {
                /**
                 * y : 2018年08月
                 * list : [{"Id":"472760","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"203.44","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:24"},{"Id":"472759","UserId":"9611230","TypeName":"转入","Amount":"50.96","BalanceAmount":"152.48","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472758","UserId":"9611230","TypeName":"转入","Amount":"50.81","BalanceAmount":"101.52","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 17:55:23"},{"Id":"472757","UserId":"9611230","TypeName":"转入","Amount":"50.71","BalanceAmount":"50.71","curtprice":"","FromWho":"","Memo":"动态释放","AddTime":"2018-08-07 11:30:40"}]
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
                     * Id : 472760
                     * UserId : 9611230
                     * TypeName : 转入
                     * Amount : 50.96
                     * BalanceAmount : 203.44
                     * curtprice :
                     * FromWho :
                     * Memo : 动态释放
                     * AddTime : 2018-08-07 17:55:24
                     */

                    private String Id;
                    private String UserId;
                    private String TypeName;
                    private String Amount;
                    private String BalanceAmount;
                    private String curtprice;
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
                                ", curtprice='" + curtprice + '\'' +
                                ", FromWho='" + FromWho + '\'' +
                                ", Memo='" + Memo + '\'' +
                                ", AddTime='" + AddTime + '\'' +
                                '}';
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

                    public String getCurtprice() {
                        return curtprice;
                    }

                    public void setCurtprice(String curtprice) {
                        this.curtprice = curtprice;
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

            @Override
            public String toString() {
                return "ListBeanX{" +
                        "header=" + header +
                        ", price_trend=" + price_trend +
                        ", centre=" + centre +
                        ", record=" + record +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyHoneyBean{" +
                "data=" + data +
                '}';
    }
}
