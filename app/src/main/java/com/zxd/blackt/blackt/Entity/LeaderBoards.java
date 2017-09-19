package com.zxd.blackt.blackt.Entity;

import java.util.List;

/**
 * Created by zhuangxd on 2017/9/18.
 */

public class LeaderBoards {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"pagebean":{"songlist":[{"songname":"追梦赤子心","seconds":296,"albummid":"004LZKQx2QFfHp","songid":203785058,"singerid":204664,"albumpic_big":"http://i.gtimg.cn/music/photo/mid_album_300/H/p/004LZKQx2QFfHp.jpg","albumpic_small":"http://i.gtimg.cn/music/photo/mid_album_90/H/p/004LZKQx2QFfHp.jpg","downUrl":"http://dl.stream.qqmusic.qq.com/203785058.mp3?vkey=0CB52C8E185A67DFFBBDA456FFD3D4F2C729DB27E9A3B760261928D561C09BB024E0B3DD82E304DED521B6EF659308E3C7F64A3F6E0E5763&guid=2718671044","url":"http://ws.stream.qqmusic.qq.com/203785058.m4a?fromtag=46","singername":"鹿晗","albumid":2229194},{"songname":"再遇不到你这样的人","seconds":321,"albummid":"002iSIhS1IapJh","songid":203795965,"singerid":89698,"albumpic_big":"http://i.gtimg.cn/music/photo/mid_album_300/J/h/002iSIhS1IapJh.jpg","albumpic_small":"http://i.gtimg.cn/music/photo/mid_album_90/J/h/002iSIhS1IapJh.jpg","downUrl":"http://dl.stream.qqmusic.qq.com/203795965.mp3?vkey=0CB52C8E185A67DFFBBDA456FFD3D4F2C729DB27E9A3B760261928D561C09BB024E0B3DD82E304DED521B6EF659308E3C7F64A3F6E0E5763&guid=2718671044","url":"http://ws.stream.qqmusic.qq.com/203795965.m4a?fromtag=46","singername":"庄心妍","albumid":2230387}],"total_song_num":100,"ret_code":0,"update_time":"2017-09-14","color":8983809,"cur_song_num":100,"comment_num":4773,"code":0,"currentPage":1,"song_begin":0,"totalpage":1}}
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
         * pagebean : {"songlist":[{"songname":"追梦赤子心","seconds":296,"albummid":"004LZKQx2QFfHp","songid":203785058,"singerid":204664,"albumpic_big":"http://i.gtimg.cn/music/photo/mid_album_300/H/p/004LZKQx2QFfHp.jpg","albumpic_small":"http://i.gtimg.cn/music/photo/mid_album_90/H/p/004LZKQx2QFfHp.jpg","downUrl":"http://dl.stream.qqmusic.qq.com/203785058.mp3?vkey=0CB52C8E185A67DFFBBDA456FFD3D4F2C729DB27E9A3B760261928D561C09BB024E0B3DD82E304DED521B6EF659308E3C7F64A3F6E0E5763&guid=2718671044","url":"http://ws.stream.qqmusic.qq.com/203785058.m4a?fromtag=46","singername":"鹿晗","albumid":2229194}],"total_song_num":100,"ret_code":0,"update_time":"2017-09-14","color":8983809,"cur_song_num":100,"comment_num":4773,"code":0,"currentPage":1,"song_begin":0,"totalpage":1}
         */

        private int ret_code;
        private PagebeanBean pagebean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public static class PagebeanBean {
            /**
             * songlist : [{"songname":"追梦赤子心","seconds":296,"albummid":"004LZKQx2QFfHp","songid":203785058,"singerid":204664,"albumpic_big":"http://i.gtimg.cn/music/photo/mid_album_300/H/p/004LZKQx2QFfHp.jpg","albumpic_small":"http://i.gtimg.cn/music/photo/mid_album_90/H/p/004LZKQx2QFfHp.jpg","downUrl":"http://dl.stream.qqmusic.qq.com/203785058.mp3?vkey=0CB52C8E185A67DFFBBDA456FFD3D4F2C729DB27E9A3B760261928D561C09BB024E0B3DD82E304DED521B6EF659308E3C7F64A3F6E0E5763&guid=2718671044","url":"http://ws.stream.qqmusic.qq.com/203785058.m4a?fromtag=46","singername":"鹿晗","albumid":2229194}]
             * total_song_num : 100
             * ret_code : 0
             * update_time : 2017-09-14
             * color : 8983809
             * cur_song_num : 100
             * comment_num : 4773
             * code : 0
             * currentPage : 1
             * song_begin : 0
             * totalpage : 1
             */

            private int total_song_num;
            private int ret_code;
            private String update_time;
            private int color;
            private int cur_song_num;
            private int comment_num;
            private int code;
            private int currentPage;
            private int song_begin;
            private int totalpage;
            private List<SonglistBean> songlist;

            public int getTotal_song_num() {
                return total_song_num;
            }

            public void setTotal_song_num(int total_song_num) {
                this.total_song_num = total_song_num;
            }

            public int getRet_code() {
                return ret_code;
            }

            public void setRet_code(int ret_code) {
                this.ret_code = ret_code;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getColor() {
                return color;
            }

            public void setColor(int color) {
                this.color = color;
            }

            public int getCur_song_num() {
                return cur_song_num;
            }

            public void setCur_song_num(int cur_song_num) {
                this.cur_song_num = cur_song_num;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getSong_begin() {
                return song_begin;
            }

            public void setSong_begin(int song_begin) {
                this.song_begin = song_begin;
            }

            public int getTotalpage() {
                return totalpage;
            }

            public void setTotalpage(int totalpage) {
                this.totalpage = totalpage;
            }

            public List<SonglistBean> getSonglist() {
                return songlist;
            }

            public void setSonglist(List<SonglistBean> songlist) {
                this.songlist = songlist;
            }

            public static class SonglistBean {
                /**
                 * songname : 追梦赤子心
                 * seconds : 296
                 * albummid : 004LZKQx2QFfHp
                 * songid : 203785058
                 * singerid : 204664
                 * albumpic_big : http://i.gtimg.cn/music/photo/mid_album_300/H/p/004LZKQx2QFfHp.jpg
                 * albumpic_small : http://i.gtimg.cn/music/photo/mid_album_90/H/p/004LZKQx2QFfHp.jpg
                 * downUrl : http://dl.stream.qqmusic.qq.com/203785058.mp3?vkey=0CB52C8E185A67DFFBBDA456FFD3D4F2C729DB27E9A3B760261928D561C09BB024E0B3DD82E304DED521B6EF659308E3C7F64A3F6E0E5763&guid=2718671044
                 * url : http://ws.stream.qqmusic.qq.com/203785058.m4a?fromtag=46
                 * singername : 鹿晗
                 * albumid : 2229194
                 */

                private String songname;
                private int seconds;
                private String albummid;
                private int songid;
                private int singerid;
                private String albumpic_big;
                private String albumpic_small;
                private String downUrl;
                private String url;
                private String singername;
                private int albumid;

                public String getSongname() {
                    return songname;
                }

                public void setSongname(String songname) {
                    this.songname = songname;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public String getAlbummid() {
                    return albummid;
                }

                public void setAlbummid(String albummid) {
                    this.albummid = albummid;
                }

                public int getSongid() {
                    return songid;
                }

                public void setSongid(int songid) {
                    this.songid = songid;
                }

                public int getSingerid() {
                    return singerid;
                }

                public void setSingerid(int singerid) {
                    this.singerid = singerid;
                }

                public String getAlbumpic_big() {
                    return albumpic_big;
                }

                public void setAlbumpic_big(String albumpic_big) {
                    this.albumpic_big = albumpic_big;
                }

                public String getAlbumpic_small() {
                    return albumpic_small;
                }

                public void setAlbumpic_small(String albumpic_small) {
                    this.albumpic_small = albumpic_small;
                }

                public String getDownUrl() {
                    return downUrl;
                }

                public void setDownUrl(String downUrl) {
                    this.downUrl = downUrl;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getSingername() {
                    return singername;
                }

                public void setSingername(String singername) {
                    this.singername = singername;
                }

                public int getAlbumid() {
                    return albumid;
                }

                public void setAlbumid(int albumid) {
                    this.albumid = albumid;
                }
            }
        }
    }
}
