package org.thingsboard.server.dao.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Ticker;
import com.github.benmanes.caffeine.cache.Weigher;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnProperty(prefix = "cache", value = "type", havingValue = "caffeine", matchIfMissing = true)
@ConfigurationProperties(prefix = "caffeine")
@EnableCaching
@Data
@Slf4j
public class CaffeineCacheConfiguration {

    private Map<String, CacheSpecs> specs;

    @Bean
    public CacheManager cacheManager() {
        log.trace("Initializing cache: {}", Arrays.toString(RemovalCause.values()));
        SimpleCacheManager manager = new SimpleCacheManager();
        if (specs != null) {
            List<CaffeineCache> caches =
                    specs.entrySet().stream()
                            .map(entry -> buildCache(entry.getKey(),
                                    entry.getValue()))
                            .collect(Collectors.toList());
            manager.setCaches(caches);
        }
        return manager;
    }

    private CaffeineCache buildCache(String name, CacheSpecs cacheSpec) {
        final Caffeine<Object, Object> caffeineBuilder
                = Caffeine.newBuilder()
                .weigher(collectionSafeWeigher())
                .maximumWeight(cacheSpec.getMaxSize())
                .expireAfterWrite(cacheSpec.getTimeToLiveInMinutes(), TimeUnit.MINUTES)
                .ticker(ticker());
        return new CaffeineCache(name, caffeineBuilder.build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }

    @Bean
    public KeyGenerator previousDeviceCredentialsId() {
        return new PreviousDeviceCredentialsIdKeyGenerator();
    }

    private Weigher<? super Object, ? super Object> collectionSafeWeigher() {
        return (Weigher<Object, Object>) (key, value) -> {
            if(value instanceof Collection) {
                return ((Collection) value).size();
            }
            return 1;
        };
    }
}
