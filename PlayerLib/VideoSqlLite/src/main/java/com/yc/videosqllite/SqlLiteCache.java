package com.yc.videosqllite;

import com.yc.applrudisk.DiskHelperUtils;
import com.yc.toolutils.AppLogUtils;

import java.io.File;

/**
 * <pre>
 *     @author yangchong
 *     email  : yangchong211@163.com
 *     time  : 2020/8/6
 *     desc  : 磁盘缓存工具
 *     revise:
 * </pre>
 */
public class SqlLiteCache {

    private InterDiskCache interDiskCache;
    public final SafeKeyGenerator safeKeyGenerator;

    public SqlLiteCache() {
        CacheConfig cacheConfig = LocationManager.getInstance().getCacheConfig();
        File path = new File(DiskHelperUtils.getBaseCachePath());
        String pathString = path.getPath();
        AppLogUtils.d("SqlLiteCache-----pathString路径输出地址-"+pathString);
        this.safeKeyGenerator = new SafeKeyGenerator();
        interDiskCache = DiskLruCacheWrapper.get(path,safeKeyGenerator);
    }

    /**
     * 存数据
     * @param url                           链接
     * @param location                      视频数据
     */
    public synchronized void put(String url , VideoLocation location){
        if (location==null){
            return;
        }
        String safeKey = safeKeyGenerator.getSafeKey(url);
        location.setUrlMd5(safeKey);
        String json = location.toJson();
        AppLogUtils.d("SqlLiteCache-----put--json--"+json);
        interDiskCache.put(url,json);
    }

    /**
     * 取数据
     * @param url                           链接
     * @return
     */
    public synchronized long get(String url){
        String data = interDiskCache.get(url);
        if (data==null || data.length()==0){
            return -1;
        }
        AppLogUtils.d("SqlLiteCache-----get---"+data);
        VideoLocation location = VideoLocation.toObject(data);
        return location.getPosition();
    }

    /**
     * 移除数据
     * @param url                           链接
     * @return
     */
    public synchronized boolean remove(String url){
        return interDiskCache.remove(url);
    }

    /**
     * 是否包含
     * @param url                           链接
     * @return
     */
    public synchronized boolean containsKey(String url){
        return interDiskCache.containsKey(url);
    }

    /**
     * 清楚所有数据
     * @return                              是否清楚完毕
     */
    public synchronized void clearAll(){
        interDiskCache.clear();
    }


}
