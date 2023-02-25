package com.yc.store.factory;

import android.content.Context;

import com.yc.store.config.CacheInitHelper;
import com.yc.store.memory.MemoryCacheImpl;
import com.yc.store.mmkv.MmkvCacheImpl;

public class MmkvFactory implements CacheFactory<MmkvCacheImpl>{

    public static MmkvFactory create() {
        return new MmkvFactory();
    }

    @Override
    public MmkvCacheImpl createCache(Context context) {
        String mmkvPath = CacheInitHelper.INSTANCE.getMmkvPath();
        MmkvCacheImpl.Companion.initRootPath(mmkvPath);
        MmkvCacheImpl.Builder builder = new MmkvCacheImpl.Builder();
        builder.setFileName("ycMmkv");
        MmkvCacheImpl mmkvCache = builder.build();
        return mmkvCache;
    }
}
