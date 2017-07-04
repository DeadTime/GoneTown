package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.Lrc;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/6/23.
 * http://route.showapi.com/213-2?showapi_appid=40859&showapi_sign=d123e9e506bd47a2b81279d0f75fef63&musicid=108401698
 */

public interface LrcService {
    @GET("213-2")
    Observable<Lrc> setLrcData(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign, @Query("musicid") String musicid);
}
