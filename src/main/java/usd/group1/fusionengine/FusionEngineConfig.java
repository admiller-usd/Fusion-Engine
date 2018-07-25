/*                                                 *
 * ----- GROUP 1 ----- GROUP 1 ----- GROUP 1 ----- *
 *                  Programmers:                   *
 *                  Austin Miller                  *
 *                 Kathrine Lavieri                *
 *                                                 */
package usd.group1.fusionengine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import usd.group1.fusionengine.redis.entities.CoordinatePair;

/**
 * A Configuration class for the Fusion Engine where we
 * can declare all of our beans
 */
@Configuration
public class FusionEngineConfig {

    @Value("${REDIS_URL}")
    private String redisUrlEnvironment;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisFactory = new JedisConnectionFactory();
        jedisFactory.setHostName(System.getenv(redisUrlEnvironment));
        return jedisFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Repository
    public interface CoordinateRepository extends CrudRepository<CoordinatePair, String> {}

    @Bean
    public FusionEngineRedisStore fusionEngineRedisStore() {
        return new FusionEngineRedisStore();
    }

}
