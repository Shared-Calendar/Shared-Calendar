package study.sharedcalendar.service;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
	private final StringRedisTemplate stringRedisTemplate;

	public String getData(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public void setDataExpire(String key, String value, long duration) {
		Duration expireDuration = Duration.ofSeconds(duration);
		stringRedisTemplate.opsForValue().set(key, value, expireDuration);
	}

	public void setData(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	public boolean checkData(String key, String value) {
		String data = getData(key);

		if (data != null && data.equals(value)) {
			deleteData(key);
			return true;
		}
		return false;
	}

	public void deleteData(String key) {
		stringRedisTemplate.delete(key);
	}
}
