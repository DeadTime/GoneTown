package com.zxd.blackt.blackt.Entity;

import java.util.List;

/**
 * Created by zhuangxd on 2017/6/22.
 */

public class PlayMusic {
    private ResBody showapi_res_body;//返回的内容
    private String showapi_res_error;//错误信息
    /**
     * 0成功
     * -1，系统调用错误
     * -2，可调用次数或金额为0
     * -3，读取超时
     * -4，服务端返回数据解析错误
     * -5，后端服务器DNS解析错误
     * -6，服务不存在或未上线
     * -1000，系统维护
     * -1002，showapi_appid字段必传
     * -1003，showapi_sign字段必传
     * -1004，签名sign验证有误
     * -1005，showapi_timestamp无效
     * -1006，app无权限调用接口
     * -1007，没有订购套餐
     * -1008，服务商关闭对您的调用权限
     * -1010，找不到您的应用
     * -1011，子授权app_child_id无效
     * -1012，子授权已过期或失效
     * -1013，子授权ip受限
     */
    private String showapi_res_code;

    public ResBody getShowapi_res_body() {
        return showapi_res_body;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public class ResBody {
        private PageBean pagebean;//数据的分页对象

        public PageBean getPagebean() {
            return pagebean;
        }
    }

    public class PageBean {
        private List<Song> contentlist;//歌曲内容

        public List<Song> getContentlist() {
            return contentlist;
        }
    }

    public class Song {
        private String downUrl;//下载路径
        private String albumpic_big;//图片
        private String m4a;//流媒体路径
        private String singername;//歌手
        private String songname;//歌曲名字
        private int songid;//歌曲ID

        public int getSongid() {
            return songid;
        }

        public String getM4a() {
            return m4a;
        }

        public String getDownUrl() {
            return downUrl;
        }

        public String getAlbumpic_big() {
            return albumpic_big;
        }

        public String getSingername() {
            return singername;
        }

        public String getSongname() {
            return songname;
        }
    }

}
