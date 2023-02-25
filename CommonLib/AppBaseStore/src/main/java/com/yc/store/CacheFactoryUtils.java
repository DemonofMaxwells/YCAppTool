package com.yc.store;

import android.content.Context;

import com.yc.store.config.CacheConstants;
import com.yc.store.factory.CacheFactory;
import com.yc.store.factory.DiskFactory;
import com.yc.store.factory.LruCacheFactory;
import com.yc.store.factory.MemoryFactory;
import com.yc.store.factory.MmkvFactory;
import com.yc.store.factory.SpFactory;
import com.yc.store.factory.StoreFactory;


public final class CacheFactoryUtils {

    public static CacheFactory getPlayer(@CacheConstants.CacheType int type) {
        if (type == CacheConstants.CacheType.TYPE_DISK) {
            return DiskFactory.create();
        } else if (type == CacheConstants.CacheType.TYPE_LRU) {
            return LruCacheFactory.create();
        } else if (type == CacheConstants.CacheType.TYPE_MEMORY) {
            return MemoryFactory.create();
        } else if (type == CacheConstants.CacheType.TYPE_MMKV) {
            return MmkvFactory.create();
        } else if (type == CacheConstants.CacheType.TYPE_SP) {
            return SpFactory.create();
        } else if (type == CacheConstants.CacheType.TYPE_STORE) {
            return StoreFactory.create();
        } else {
            return MmkvFactory.create();
        }
    }

    public static ICacheable getCacheImpl(Context context, @CacheConstants.CacheType int type) {
        if (type == CacheConstants.CacheType.TYPE_DISK) {
            return DiskFactory.create().createCache(context);
        } else if (type == CacheConstants.CacheType.TYPE_LRU) {
            return LruCacheFactory.create().createCache(context);
        } else if (type == CacheConstants.CacheType.TYPE_MEMORY) {
            return MemoryFactory.create().createCache(context);
        } else if (type == CacheConstants.CacheType.TYPE_MMKV) {
            return MmkvFactory.create().createCache(context);
        } else if (type == CacheConstants.CacheType.TYPE_SP) {
            return SpFactory.create().createCache(context);
        } else if (type == CacheConstants.CacheType.TYPE_STORE) {
            return StoreFactory.create().createCache(context);
        } else {
            return MmkvFactory.create().createCache(context);
        }
    }
    
}
