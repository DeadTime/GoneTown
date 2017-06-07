package com.zxd.blackt.blackt.Entity;

import java.util.List;

/**
 * Created by zhuangxd on 2017/5/9.
 */

public class Top {
    private TopResult result;
    private int error_code;
    private String reason;

    public TopResult getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public class TopResult {
        private String stat;
        private List<TopData> data;

        public String getStat() {
            return stat;
        }

        public List<TopData> getData() {
            return data;
        }
    }

    public class TopData {
        private String category;
        private String thumbnail_pic_s;
        private String uniquekey;
        private String title;
        private String date;
        private String author_name;
        private String thumbnail_pic_s03;
        private String thumbnail_pic_s02;
        private String url;

        public String getCategory() {
            return category;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public String getUniquekey() {
            return uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public String getUrl() {
            return url;
        }
    }
}
