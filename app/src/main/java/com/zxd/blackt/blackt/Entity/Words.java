package com.zxd.blackt.blackt.Entity;

import java.util.List;

/**
 * Created by zhuangxd on 2017/9/12.
 */

public class Words {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"ret_message":"Success","data":[{"english":"You have to do the best what God gave you.","chinese":"你必须尽力发挥上帝所赐予你的。"},{"english":"The mighty desert is burning for the love of a bladeof grass who shakes her head and laughs and flies away.","chinese":"无垠的沙漠热烈追求一叶绿草的爱，她摇摇头笑着飞开了。"}]}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * ret_message : Success
         * data : [{"english":"You have to do the best what God gave you.","chinese":"你必须尽力发挥上帝所赐予你的。"},{"english":"The mighty desert is burning for the love of a bladeof grass who shakes her head and laughs and flies away.","chinese":"无垠的沙漠热烈追求一叶绿草的爱，她摇摇头笑着飞开了。"}]
         */

        private int ret_code;
        private String ret_message;
        private List<DataBean> data;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_message() {
            return ret_message;
        }

        public void setRet_message(String ret_message) {
            this.ret_message = ret_message;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * english : You have to do the best what God gave you.
             * chinese : 你必须尽力发挥上帝所赐予你的。
             */

            private String english;
            private String chinese;

            public String getEnglish() {
                return english;
            }

            public void setEnglish(String english) {
                this.english = english;
            }

            public String getChinese() {
                return chinese;
            }

            public void setChinese(String chinese) {
                this.chinese = chinese;
            }
        }
    }
}
