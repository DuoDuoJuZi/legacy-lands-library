package net.legacy.library.cache.factory;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.experimental.UtilityClass;
import net.legacy.library.cache.service.CacheServiceInterface;
import net.legacy.library.cache.service.caffeine.CaffeineAsyncCacheService;
import net.legacy.library.cache.service.caffeine.CaffeineCacheService;
import net.legacy.library.cache.service.redis.RedisCacheService;
import net.legacy.library.cache.service.redis.RedisCacheServiceInterface;
import org.redisson.config.Config;

/**
 * Factory for creating cache service instances.
 *
 * <p>Provides centralized creation of different cache implementations
 * with various configuration options.
 *
 * @author qwq-dev
 * @since 2024-12-21 20:10
 */
@UtilityClass
public final class CacheServiceFactory {
    /**
     * Creates a {@link RedisCacheService} with the specified configuration.
     *
     * @param config the Redis configuration
     * @return a new {@link RedisCacheService} instance
     * @see RedisCacheService
     * @see RedisCacheServiceInterface
     * @see Config
     */
    public static RedisCacheServiceInterface createRedisCache(Config config) {
        return new RedisCacheService(config);
    }

    /**
     * Creates a {@link CaffeineCacheService} with custom configuration.
     *
     * @param cache the Caffeine cache
     * @param <K>   the cache key type
     * @param <V>   the cache value type
     * @return a new {@link CaffeineCacheService} instance
     * @see CacheServiceInterface
     * @see Cache
     * @see CaffeineCacheService
     */
    public static <K, V> CacheServiceInterface<Cache<K, V>, V> createCaffeineCache(Cache<K, V> cache) {
        return new CaffeineCacheService<>(cache);
    }

    /**
     * Creates a Caffeine synchronous cache service with default configuration.
     *
     * @param <K> the cache key type
     * @param <V> the cache value type
     * @return a new Caffeine cache service instance
     * @see CacheServiceInterface
     * @see Cache
     * @see CaffeineCacheService
     */
    public static <K, V> CacheServiceInterface<Cache<K, V>, V> createCaffeineCache() {
        return new CaffeineCacheService<>();
    }

    /**
     * Creates a Caffeine asynchronous cache service with custom configuration.
     *
     * @param asyncCache the Caffeine async cache
     * @param <K>        the cache key type
     * @param <V>        the cache value type
     * @return a new async Caffeine cache service instance
     * @see CacheServiceInterface
     * @see AsyncCache
     * @see CaffeineAsyncCacheService
     */
    public static <K, V> CacheServiceInterface<AsyncCache<K, V>, V> createCaffeineAsyncCache(AsyncCache<K, V> asyncCache) {
        return new CaffeineAsyncCacheService<>(asyncCache);
    }

    /**
     * Creates a Caffeine asynchronous cache service with default configuration.
     *
     * @param <K> the cache key type
     * @param <V> the cache value type
     * @return a new async Caffeine cache service instance
     * @see CacheServiceInterface
     * @see AsyncCache
     * @see CaffeineAsyncCacheService
     */
    public static <K, V> CacheServiceInterface<AsyncCache<K, V>, V> createCaffeineAsyncCache() {
        return new CaffeineAsyncCacheService<>();
    }
}
