package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.Words;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/5/9.
 */

public interface WordService {
    @GET("1211-1")
    Observable<Words> setWords(@Query("showapi_appid") String showapi_appid,
                               @Query("showapi_sign") String showapi_sign,
                               @Query("count") String count);
}
