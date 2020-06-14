package com.rmpk.dc.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.spring.cache.HazelcastCacheManager;


@Configuration
@EnableCaching
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
		return new HazelcastCacheManager(hazelcastInstance());
	}

	@Bean
	public HazelcastInstance hazelcastInstance() {
		Config config = new Config();
		NetworkConfig networkConfig = config.getNetworkConfig();
		networkConfig.setPort(5702).setPortAutoIncrement(false);
		networkConfig.getInterfaces().setEnabled(false);
		config.setNetworkConfig(networkConfig);
		config.setProperty("hazelcast.logging.type", "slf4j");
		
		// setup map configuration
		EvictionConfig evictionConfig = new EvictionConfig()
				.setEvictionPolicy(EvictionPolicy.LRU)
				.setSize(90)
				.setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE);
		MapConfig mapConfig = new MapConfig("movieMap")
				.setInMemoryFormat(InMemoryFormat.BINARY)
				.setAsyncBackupCount(0)
				.setBackupCount(1)
				.setTimeToLiveSeconds(3600)
				.setEvictionConfig(evictionConfig);
		config.addMapConfig(mapConfig);
		
		HazelcastInstance newHazelcastInstance = Hazelcast.newHazelcastInstance(config);
		//IMap<Object, Object> movieMap = newHazelcastInstance.getMap("movieMap");
		
		return newHazelcastInstance;
	}

}
