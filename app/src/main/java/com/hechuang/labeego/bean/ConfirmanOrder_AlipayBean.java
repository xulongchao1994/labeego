package com.hechuang.labeego.bean;

public class ConfirmanOrder_AlipayBean {


    /**
     * data : {"list":{"response":"alipay_sdk=alipay-sdk-php-20180705&app_id=2018123062703581&biz_content=%7B%22body%22%3A%22%22%2C%22subject%22%3A+%22%E5%95%86%E5%9F%8E%E8%B4%AD%E7%89%A9-20190114113018443981%22%2C%22out_trade_no%22%3A+%2220190114113018443981%22%2C%22timeout_express%22%3A+%22%22%2C%22total_amount%22%3A+%22183.24%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2019-01-14+11%3A30%3A18&version=1.0&sign=CGuY%2F8NcMQPy%2BZwdaWpcjxijx7fRqfbb%2FqpKsluvyARO8YggXEy%2F%2FFavxlEWjxdS3O39d8gRy%2B2Uch065wKU5ZSsc9TDQUElE180ZAiO053nAZOHvTY6%2BnnPAOOrbO8DnOu5SxYlYEfz6YPq4hEzpAVU4WkFaJ4dwhIW%2BI%2BRKaGbzuNggdZuMOYAtI5qO1iRkdMxC3rYSi9YVm8b8PoWiDKwYJvi%2BGQm6ZjiXIaNXG2TcRE3qkqyYLOeU5uCb5tgr4WpB%2FPcjUnPhaUdvyPgPCKFK903TbWUy37MEW3PP9TUkvMjkHNloHZR%2F1U1W0p21eR7H9kbqWq2Llec7OrCNw%3D%3D"},"status":1,"msg":"加载成功"}
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
         * list : {"response":"alipay_sdk=alipay-sdk-php-20180705&app_id=2018123062703581&biz_content=%7B%22body%22%3A%22%22%2C%22subject%22%3A+%22%E5%95%86%E5%9F%8E%E8%B4%AD%E7%89%A9-20190114113018443981%22%2C%22out_trade_no%22%3A+%2220190114113018443981%22%2C%22timeout_express%22%3A+%22%22%2C%22total_amount%22%3A+%22183.24%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2019-01-14+11%3A30%3A18&version=1.0&sign=CGuY%2F8NcMQPy%2BZwdaWpcjxijx7fRqfbb%2FqpKsluvyARO8YggXEy%2F%2FFavxlEWjxdS3O39d8gRy%2B2Uch065wKU5ZSsc9TDQUElE180ZAiO053nAZOHvTY6%2BnnPAOOrbO8DnOu5SxYlYEfz6YPq4hEzpAVU4WkFaJ4dwhIW%2BI%2BRKaGbzuNggdZuMOYAtI5qO1iRkdMxC3rYSi9YVm8b8PoWiDKwYJvi%2BGQm6ZjiXIaNXG2TcRE3qkqyYLOeU5uCb5tgr4WpB%2FPcjUnPhaUdvyPgPCKFK903TbWUy37MEW3PP9TUkvMjkHNloHZR%2F1U1W0p21eR7H9kbqWq2Llec7OrCNw%3D%3D"}
         * status : 1
         * msg : 加载成功
         */

        private ListBean list;
        private int status;
        private String msg;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public static class ListBean {
            /**
             * response : alipay_sdk=alipay-sdk-php-20180705&app_id=2018123062703581&biz_content=%7B%22body%22%3A%22%22%2C%22subject%22%3A+%22%E5%95%86%E5%9F%8E%E8%B4%AD%E7%89%A9-20190114113018443981%22%2C%22out_trade_no%22%3A+%2220190114113018443981%22%2C%22timeout_express%22%3A+%22%22%2C%22total_amount%22%3A+%22183.24%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2019-01-14+11%3A30%3A18&version=1.0&sign=CGuY%2F8NcMQPy%2BZwdaWpcjxijx7fRqfbb%2FqpKsluvyARO8YggXEy%2F%2FFavxlEWjxdS3O39d8gRy%2B2Uch065wKU5ZSsc9TDQUElE180ZAiO053nAZOHvTY6%2BnnPAOOrbO8DnOu5SxYlYEfz6YPq4hEzpAVU4WkFaJ4dwhIW%2BI%2BRKaGbzuNggdZuMOYAtI5qO1iRkdMxC3rYSi9YVm8b8PoWiDKwYJvi%2BGQm6ZjiXIaNXG2TcRE3qkqyYLOeU5uCb5tgr4WpB%2FPcjUnPhaUdvyPgPCKFK903TbWUy37MEW3PP9TUkvMjkHNloHZR%2F1U1W0p21eR7H9kbqWq2Llec7OrCNw%3D%3D
             */

            private String response;

            public String getResponse() {
                return response;
            }

            public void setResponse(String response) {
                this.response = response;
            }
        }
    }
}
