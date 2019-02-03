package com.liko.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by likoguan on 17/01/19.
 */
public class LocalCache {
    public static void main(String[] args) {
        try {
//        Cache<Integer, Integer> cache = CacheBuilder.newBuilder()
//                .build();
            LoadingCache<Integer, Integer> loadingCache = CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .expireAfterWrite(Duration.ofSeconds(10))
                    .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                    .recordStats()
                    .refreshAfterWrite(Duration.ofSeconds(5))
                    .build(new CacheLoader<Integer, Integer>() {
                        @Override
                        public Integer load(Integer key) throws Exception {
                            return key;
                        }
                    });


            for (int i = 0; i < 80; i++) {
                loadingCache.get(i);
            }

            ConcurrentMap<Integer, Integer> map = loadingCache.asMap();
            Set<Integer> set =  map.keySet();
            System.out.println(set);

            Thread.sleep(20000);

            Integer x = loadingCache.get(50);
            System.out.println(x);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
