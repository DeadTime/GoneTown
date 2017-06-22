package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.Music;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/6/20.
 */

public interface MusicService {
    @GET("search")
    Observable<Music> setMusicData(@Query("q") String q, @Query("count") int count);
}
