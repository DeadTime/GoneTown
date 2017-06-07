package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.Top;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/5/9.
 */

public interface TopService {
    @GET("index")
    Observable<Top> getTopData(@Query("type") String type, @Query("key") String key);
}
