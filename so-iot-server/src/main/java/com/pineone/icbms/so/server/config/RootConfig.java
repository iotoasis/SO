package com.pineone.icbms.so.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.pineone.icbms.so.iot.resources.message.VirtualDeviceControlMessage;
import com.pineone.icbms.so.iot.resources.occurrence.DefaultOccurrence;

/**
 * Created by existmaster on 2015. 10. 28..
 */
@Configuration
public class RootConfig
{

	private static final String	REDIS_SERVER_IP			= "166.104.112.37";	// "127.0.0.1";
	private static final int	REDIS_SERVER_PORT		= 36379;			// 6379;
	private static final String	REDIS_SERVER_PASSWORD	= "vkdls1dnjs";
	private static final String	TEST_REDIS_SERVER_IP	= "192.168.99.100";
	private static final int	TEST_REDIS_SERVER_PORT	= 32768;
	private static final String	APP_CONFIG_FILE_PATH	= "application.xml";

	/**
	 * 프로퍼티 홀더는 다른 빈들이 사용하는 프로퍼티들을 로딩하기 때문에, static 메소드로 실행된다. 다른 일반 빈들이 만들어지기전에
	 * 먼저 만들어져야 한다.
	 * 
	 * @return
	 */

	@Bean
	public JedisConnectionFactory jedisConnectionFactory()
	{
		/**
		 * Jedis Configration Server : 192.168.1.186 Port : 6379
		 */
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(REDIS_SERVER_IP);
		jedisConnectionFactory.setPort(REDIS_SERVER_PORT);
		jedisConnectionFactory.setPassword(REDIS_SERVER_PASSWORD);

		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, DefaultOccurrence> occurrenceRedisTemplate()
	{
		RedisTemplate<String, DefaultOccurrence> redisTemplate = new RedisTemplate<String, DefaultOccurrence>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());

		return redisTemplate;
	}

	@Bean
	public RedisTemplate<String, VirtualDeviceControlMessage> virtualDeviceControlMessageRedisTemplate()
	{
		RedisTemplate<String, VirtualDeviceControlMessage> redisTemplate = new RedisTemplate<String, VirtualDeviceControlMessage>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());

		return redisTemplate;
	}

}