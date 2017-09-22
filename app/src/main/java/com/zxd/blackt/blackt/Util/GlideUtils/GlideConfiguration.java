package com.zxd.blackt.blackt.Util.GlideUtils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by zhuangxd on 2017/8/2.
 */

public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //自定义缓存目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                GlideCatchConfig.GLIDE_CARCH_DIR,
                GlideCatchConfig.GLIDE_CATCH_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }
}
