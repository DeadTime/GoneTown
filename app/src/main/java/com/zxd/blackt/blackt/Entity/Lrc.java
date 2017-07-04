package com.zxd.blackt.blackt.Entity;

/**
 * Created by zhuangxd on 2017/6/23.
 */

public class Lrc {
    private LrcBody showapi_res_body;
    private String showapi_res_error;
    private int showapi_res_code;

    public LrcBody getShowapi_res_body() {
        return showapi_res_body;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public class LrcBody {
        private int ret_code;
        private String lyric;//带时间轴的歌词文本
        private String lyric_txt;//歌词纯文本

        public int getRet_code() {
            return ret_code;
        }

        public String getLyric() {
            return lyric;
        }

        public String getLyric_txt() {
            return lyric_txt;
        }
    }
}
