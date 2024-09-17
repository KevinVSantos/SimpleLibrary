package br.com.KevinVSantos.SimpleLibrary.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import java.util.*;

public class RuntimeCacheResolverConfig extends SimpleCacheResolver {

    public static final Map<String, List<String>> evictMap = new HashMap<>();

    protected RuntimeCacheResolverConfig(CacheManager cacheManager) {
        super(cacheManager);
        evictMap.put("GenreController", Arrays.asList("SubGenreController", "BookController"));
        evictMap.put("SubGenreController", Arrays.asList("BookController"));
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        var controllerName = context.getTarget().getClass().getSimpleName();

        var evictAnnotation = context.getMethod().getAnnotation(CacheEvict.class);

        if(evictAnnotation != null && evictMap.containsKey(controllerName))
        {
            evictMap.get(controllerName).forEach(listEvict -> {
                getCacheManager().getCache(listEvict).clear();
            });
        }

        return Arrays.asList(controllerName);
    }
}
