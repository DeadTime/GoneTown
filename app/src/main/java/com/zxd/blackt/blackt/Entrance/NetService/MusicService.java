package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.Musics;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/6/20.
 */

public interface MusicService {
    @GET("ting")
    Observable<Musics> setMusicData(@Query("format") String format, @Query("calback") String calback, @Query("from") String from, @Query("method") String method, @Query("type") int type, @Query("size") int size, @Query("offset") int offset);
}
