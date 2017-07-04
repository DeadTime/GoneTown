package com.zxd.blackt.blackt.Entrance;

import com.zxd.blackt.blackt.Entity.Lrc;
import com.zxd.blackt.blackt.Entity.PlayMusic;
import com.zxd.blackt.blackt.Entity.Top;
import com.zxd.blackt.blackt.Entrance.NetService.LrcService;
import com.zxd.blackt.blackt.Entrance.NetService.PlayMusicService;
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
    private static String PLAYMUSICURL = "http://route.showapi.com/";
    private String SHOWAPPID = "40859";
    private String SHOWSIGN = "d123e9e506bd47a2b81279d0f75fef63";
    private static final int DEFAULT_TIMEOUT = 5;
    private final Retrofit retrofit;
    private final Retrofit retrofit_play;
    private final Retrofit retrofit_lrc;

    public String getSHOWAPPID() {
        return SHOWAPPID;
    }

    public String getSHOWSIGN() {
        return SHOWSIGN;
    }

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

        retrofit_play = new Retrofit.Builder()
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(PLAYMUSICURL)
                .build();

        retrofit_lrc = new Retrofit.Builder()
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(PLAYMUSICURL)
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

    public void playMusic(Subscriber<PlayMusic> playMusicSubscriber, String showappid, String showsign, String keyword) {
        PlayMusicService playMusicService = retrofit_play.create(PlayMusicService.class);
        playMusicService.setPlayMusic(showappid, showsign, keyword)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(playMusicSubscriber);
    }

    public void setLrcData(Subscriber<Lrc> lrcSubscriber, String showappid, String showsign, String musicid) {
        LrcService lrcService = retrofit_play.create(LrcService.class);
        lrcService.setLrcData(showappid, showsign, musicid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lrcSubscriber);
    }

}
