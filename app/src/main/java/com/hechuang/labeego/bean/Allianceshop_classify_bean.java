package com.hechuang.labeego.bean;

import java.util.List;

public class Allianceshop_classify_bean {

    /**
     * data : {"list":[{"Id":"1","name":"家居/家电","two":[{"Id":"2","name":"家纺","three":[{"Id":"3","name":"四件套"},{"Id":"5","name":"厚被"},{"Id":"6","name":"枕头"},{"Id":"7","name":"儿童套件"},{"Id":"8","name":"毛毯"},{"Id":"9","name":"床单 被罩"},{"Id":"70","name":"测试3"}]},{"Id":"25","name":"家电","three":[{"Id":"28","name":"空气净化器"},{"Id":"10000","name":"净水器"},{"Id":"10014","name":"加湿器"}]},{"Id":"75","name":"家具智能用品","three":[]}]},{"Id":"10","name":"食品饮料","two":[{"Id":"11","name":"零食","three":[]},{"Id":"13","name":"美食","three":[{"Id":"14","name":"鸡翅/鸡爪"},{"Id":"15","name":"鸭脖/鸭腿"},{"Id":"16","name":"杏仁露"},{"Id":"17","name":"白酒"},{"Id":"18","name":"葡萄酒"}]},{"Id":"23","name":"白酒/红酒/葡萄酒/黄酒/酒","three":[]},{"Id":"10002","name":"酒水","three":[]}]},{"Id":"19","name":"养生保健","two":[{"Id":"20","name":"食品保健","three":[]},{"Id":"21","name":"手足保健","three":[]},{"Id":"22","name":"医疗用品","three":[]}]},{"Id":"24","name":"手机、数码","two":[{"Id":"26","name":"数码","three":[]},{"Id":"27","name":"手机","three":[]}]},{"Id":"29","name":"服饰鞋包","two":[{"Id":"30","name":"女装","three":[]},{"Id":"31","name":"男装","three":[]},{"Id":"32","name":"内衣","three":[{"Id":"33","name":"保暖内衣"},{"Id":"34","name":"美体衣"},{"Id":"83","name":"男士"},{"Id":"84","name":"女士"}]},{"Id":"10003","name":"行李箱","three":[]}]},{"Id":"35","name":"珠宝首饰","two":[{"Id":"36","name":"珠宝","three":[]},{"Id":"38","name":"手表","three":[{"Id":"39","name":"运动表"},{"Id":"40","name":"卡西欧"},{"Id":"41","name":"国表"},{"Id":"42","name":"时尚表"},{"Id":"43","name":"女表"},{"Id":"44","name":"儿童表"},{"Id":"45","name":"学生表"},{"Id":"46","name":"浪琴斯沃琪表"},{"Id":"47","name":"镂空机械表"},{"Id":"48","name":"皮带表"},{"Id":"49","name":"钢带表"},{"Id":"50","name":"欧米茄"},{"Id":"51","name":"电子表"},{"Id":"52","name":"陶瓷表瑞士表"},{"Id":"53","name":"日韩腕表"},{"Id":"54","name":"情侣表"},{"Id":"55","name":"光能表"},{"Id":"56","name":"怀表"},{"Id":"57","name":"表带手表配件"},{"Id":"58","name":"休闲 精钢 复古手表"},{"Id":"59","name":"中性手表"},{"Id":"60","name":"帆布表带"},{"Id":"61","name":"深度防水"}]},{"Id":"91","name":"翡翠吊坠","three":[]},{"Id":"10011","name":"银饰","three":[]}]},{"Id":"62","name":"美妆洗护","two":[{"Id":"63","name":"美妆/护肤","three":[{"Id":"64","name":"水/乳/霜"},{"Id":"65","name":"面膜"},{"Id":"66","name":"护肤套盒"}]},{"Id":"73","name":"洗护","three":[]},{"Id":"80","name":"彩妆系列","three":[]}]},{"Id":"76","name":"日用百货","two":[{"Id":"37","name":"眼镜","three":[]},{"Id":"72","name":"洗衣液","three":[]},{"Id":"74","name":"洗洁精","three":[]},{"Id":"77","name":"水杯","three":[]},{"Id":"78","name":"茶具，餐具","three":[]},{"Id":"79","name":"厨房烹具","three":[{"Id":"87","name":"汤锅"},{"Id":"88","name":"炒锅"},{"Id":"89","name":"锅具套件"}]},{"Id":"10001","name":"茶","three":[]},{"Id":"10006","name":"水果、生鲜","three":[]},{"Id":"10008","name":"皮带","three":[]},{"Id":"10009","name":"智能冲牙器","three":[]},{"Id":"10010","name":"便携式洁身器","three":[]},{"Id":"10012","name":"南北干货","three":[]},{"Id":"10013","name":"蓝牙耳机、手表","three":[]}]},{"Id":"9998","name":"店铺代金/优惠券","two":[{"Id":"9999","name":"店铺购物券","three":[]}]},{"Id":"10004","name":"教育类","two":[{"Id":"10005","name":"学习卡","three":[]}]}],"msg":"加载成功！","status":1}
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
         * list : [{"Id":"1","name":"家居/家电","two":[{"Id":"2","name":"家纺","three":[{"Id":"3","name":"四件套"},{"Id":"5","name":"厚被"},{"Id":"6","name":"枕头"},{"Id":"7","name":"儿童套件"},{"Id":"8","name":"毛毯"},{"Id":"9","name":"床单 被罩"},{"Id":"70","name":"测试3"}]},{"Id":"25","name":"家电","three":[{"Id":"28","name":"空气净化器"},{"Id":"10000","name":"净水器"},{"Id":"10014","name":"加湿器"}]},{"Id":"75","name":"家具智能用品","three":[]}]},{"Id":"10","name":"食品饮料","two":[{"Id":"11","name":"零食","three":[]},{"Id":"13","name":"美食","three":[{"Id":"14","name":"鸡翅/鸡爪"},{"Id":"15","name":"鸭脖/鸭腿"},{"Id":"16","name":"杏仁露"},{"Id":"17","name":"白酒"},{"Id":"18","name":"葡萄酒"}]},{"Id":"23","name":"白酒/红酒/葡萄酒/黄酒/酒","three":[]},{"Id":"10002","name":"酒水","three":[]}]},{"Id":"19","name":"养生保健","two":[{"Id":"20","name":"食品保健","three":[]},{"Id":"21","name":"手足保健","three":[]},{"Id":"22","name":"医疗用品","three":[]}]},{"Id":"24","name":"手机、数码","two":[{"Id":"26","name":"数码","three":[]},{"Id":"27","name":"手机","three":[]}]},{"Id":"29","name":"服饰鞋包","two":[{"Id":"30","name":"女装","three":[]},{"Id":"31","name":"男装","three":[]},{"Id":"32","name":"内衣","three":[{"Id":"33","name":"保暖内衣"},{"Id":"34","name":"美体衣"},{"Id":"83","name":"男士"},{"Id":"84","name":"女士"}]},{"Id":"10003","name":"行李箱","three":[]}]},{"Id":"35","name":"珠宝首饰","two":[{"Id":"36","name":"珠宝","three":[]},{"Id":"38","name":"手表","three":[{"Id":"39","name":"运动表"},{"Id":"40","name":"卡西欧"},{"Id":"41","name":"国表"},{"Id":"42","name":"时尚表"},{"Id":"43","name":"女表"},{"Id":"44","name":"儿童表"},{"Id":"45","name":"学生表"},{"Id":"46","name":"浪琴斯沃琪表"},{"Id":"47","name":"镂空机械表"},{"Id":"48","name":"皮带表"},{"Id":"49","name":"钢带表"},{"Id":"50","name":"欧米茄"},{"Id":"51","name":"电子表"},{"Id":"52","name":"陶瓷表瑞士表"},{"Id":"53","name":"日韩腕表"},{"Id":"54","name":"情侣表"},{"Id":"55","name":"光能表"},{"Id":"56","name":"怀表"},{"Id":"57","name":"表带手表配件"},{"Id":"58","name":"休闲 精钢 复古手表"},{"Id":"59","name":"中性手表"},{"Id":"60","name":"帆布表带"},{"Id":"61","name":"深度防水"}]},{"Id":"91","name":"翡翠吊坠","three":[]},{"Id":"10011","name":"银饰","three":[]}]},{"Id":"62","name":"美妆洗护","two":[{"Id":"63","name":"美妆/护肤","three":[{"Id":"64","name":"水/乳/霜"},{"Id":"65","name":"面膜"},{"Id":"66","name":"护肤套盒"}]},{"Id":"73","name":"洗护","three":[]},{"Id":"80","name":"彩妆系列","three":[]}]},{"Id":"76","name":"日用百货","two":[{"Id":"37","name":"眼镜","three":[]},{"Id":"72","name":"洗衣液","three":[]},{"Id":"74","name":"洗洁精","three":[]},{"Id":"77","name":"水杯","three":[]},{"Id":"78","name":"茶具，餐具","three":[]},{"Id":"79","name":"厨房烹具","three":[{"Id":"87","name":"汤锅"},{"Id":"88","name":"炒锅"},{"Id":"89","name":"锅具套件"}]},{"Id":"10001","name":"茶","three":[]},{"Id":"10006","name":"水果、生鲜","three":[]},{"Id":"10008","name":"皮带","three":[]},{"Id":"10009","name":"智能冲牙器","three":[]},{"Id":"10010","name":"便携式洁身器","three":[]},{"Id":"10012","name":"南北干货","three":[]},{"Id":"10013","name":"蓝牙耳机、手表","three":[]}]},{"Id":"9998","name":"店铺代金/优惠券","two":[{"Id":"9999","name":"店铺购物券","three":[]}]},{"Id":"10004","name":"教育类","two":[{"Id":"10005","name":"学习卡","three":[]}]}]
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
             * Id : 1
             * name : 家居/家电
             * two : [{"Id":"2","name":"家纺","three":[{"Id":"3","name":"四件套"},{"Id":"5","name":"厚被"},{"Id":"6","name":"枕头"},{"Id":"7","name":"儿童套件"},{"Id":"8","name":"毛毯"},{"Id":"9","name":"床单 被罩"},{"Id":"70","name":"测试3"}]},{"Id":"25","name":"家电","three":[{"Id":"28","name":"空气净化器"},{"Id":"10000","name":"净水器"},{"Id":"10014","name":"加湿器"}]},{"Id":"75","name":"家具智能用品","three":[]}]
             */

            private String Id;
            private String name;
            private List<TwoBean> two;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<TwoBean> getTwo() {
                return two;
            }

            public void setTwo(List<TwoBean> two) {
                this.two = two;
            }

            public static class TwoBean {
                /**
                 * Id : 2
                 * name : 家纺
                 * three : [{"Id":"3","name":"四件套"},{"Id":"5","name":"厚被"},{"Id":"6","name":"枕头"},{"Id":"7","name":"儿童套件"},{"Id":"8","name":"毛毯"},{"Id":"9","name":"床单 被罩"},{"Id":"70","name":"测试3"}]
                 */

                private String Id;
                private String name;
                private List<ThreeBean> three;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<ThreeBean> getThree() {
                    return three;
                }

                public void setThree(List<ThreeBean> three) {
                    this.three = three;
                }

                public static class ThreeBean {
                    /**
                     * Id : 3
                     * name : 四件套
                     */

                    private String Id;
                    private String name;

                    public String getId() {
                        return Id;
                    }

                    public void setId(String Id) {
                        this.Id = Id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }
    }
}
