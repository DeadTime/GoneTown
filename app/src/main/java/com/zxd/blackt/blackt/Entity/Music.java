package com.zxd.blackt.blackt.Entity;

import java.util.List;

/**
 * Created by zhuangxd on 2017/6/20.
 */

public class Music {
    private List<Musics> musics;

    public List<Musics> getMusics() {
        return musics;
    }

    public void setMusics(List<Musics> musics) {
        this.musics = musics;
    }

    public class Musics {
        private Rating rating;//评级
        private int id;//歌曲id
        private String image;//图片
        private Attrs attrs;//专辑属性
        private List<Author> author;//作者
        private String title;//歌曲名字

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Attrs getAttrs() {
            return attrs;
        }

        public void setAttrs(Attrs attrs) {
            this.attrs = attrs;
        }

        public List<Author> getAuthor() {
            return author;
        }

        public void setAuthor(List<Author> author) {
            this.author = author;
        }
    }

    public class Author {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Attrs {
        private List<String> singer;//歌手
        private List<String> pubdate;//上架时间
        private List<String> tracks;//歌曲
        private List<String> publisher;//出版社
        private String radio_url;//播放路径

        public String getRadio_url() {
            return radio_url;
        }

        public void setRadio_url(String radio_url) {
            this.radio_url = radio_url;
        }

        public List<String> getPublisher() {
            return publisher;
        }

        public void setPublisher(List<String> publisher) {
            this.publisher = publisher;
        }

        public List<String> getTracks() {
            return tracks;
        }

        public void setTracks(List<String> tracks) {
            this.tracks = tracks;
        }

        public List<String> getSinger() {
            return singer;
        }

        public void setSinger(List<String> singer) {
            this.singer = singer;
        }

        public List<String> getPubdate() {
            return pubdate;
        }

        public void setPubdate(List<String> pubdate) {
            this.pubdate = pubdate;
        }
    }

    public class Rating {
        private Float max;//最高
        private Float average;//平均
        private Float numRaters;//评价人数
        private Float min;//最低

        public Float getMax() {
            return max;
        }

        public void setMax(Float max) {
            this.max = max;
        }

        public Float getAverage() {
            return average;
        }

        public void setAverage(Float average) {
            this.average = average;
        }

        public Float getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(Float numRaters) {
            this.numRaters = numRaters;
        }

        public Float getMin() {
            return min;
        }

        public void setMin(Float min) {
            this.min = min;
        }
    }
}
