package by.rusak.configuration;

//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CacheConfig {
/*    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("roles");
        cacheManager.setCaffeine(cacheProperties());
        return cacheManager;
    }

    public Caffeine<Object, Object> cacheProperties() {
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(20)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats();
    }*/
}

