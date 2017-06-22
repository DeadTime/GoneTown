package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.PlayMusic;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/6/22.
 * http://route.showapi.com/213-1/?showapi_appid=40859&showapi_sign=d123e9e506bd47a2b81279d0f75fef63&keyword=%E6%B5%B7%E9%98%94%E5%A4%A9%E7%A9%BA
 */

public interface PlayMusicService {
    @GET("213-1")
    Observable<PlayMusic> setPlayMusic(@Query("showapi_appid")String showapi_appid,
                                       @Query("showapi_sign")String showapi_sign,
                                       @Query("keyword")String keyword);
}
