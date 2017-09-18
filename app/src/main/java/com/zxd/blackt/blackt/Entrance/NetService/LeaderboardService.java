package com.zxd.blackt.blackt.Entrance.NetService;

import com.zxd.blackt.blackt.Entity.LeaderBoards;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhuangxd on 2017/9/18.
 */

public interface LeaderboardService {
    @GET("213-4")
    Observable<LeaderBoards> getLeaderBoards(@Query("showapi_appid") String showapi_appid, @Query("showapi_sign") String showapi_sign, @Query("topid") String topid);
}
