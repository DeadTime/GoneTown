package com.zxd.blackt.blackt.Entrance;

import com.zxd.blackt.blackt.Entity.Top;
import com.zxd.blackt.blackt.Entrance.NetService.TopService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhuangxd on 2017/5/4.
 */

public class Entrances {
    /**
     * a18eeea4c51df5dd23a1dc3c50cec2e6
     * top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     * http://v.juhe.cn/toutiao/index?type=top&key=a18eeea4c51df5dd23a1dc3c50cec2e6
     * http://09.imgmini.eastday.com/mobile/20170509/20170509163614_67738584d083ebc00ac87ae552125bb4_6_mwpm_03200403.jpeg
     * http://mini.eastday.com/mobile/170510103049084.html
     */
    private static String BASEURL = "http://v.juhe.cn/toutiao/";
    private static final int DEFAULT_TIMEOUT = 5;
    private final Retrofit retrofit;

    //构造私有方法
    private Entrances() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder();
        okhttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEURL)
                .build();
    }

    private static class SingleEntrances {
        private static final Entrances sEntrances = new Entrances();
    }

    public static Entrances getEntrances() {
        return SingleEntrances.sEntrances;
    }

    public void setTopData(Subscriber<Top> topSubscriber, String type, String key) {
        TopService topService = retrofit.create(TopService.class);
        topService.setTopData(type, key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topSubscriber);
    }

}
