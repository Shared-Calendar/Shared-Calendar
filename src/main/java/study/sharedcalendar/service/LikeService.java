package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.sharedcalendar.exception.ThreadException;
import study.sharedcalendar.mapper.LikeMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
	private final RedissonClient redissonClient;
	private final LikeMapper likeMapper;
	private final PlatformTransactionManager transactionManager;

	public void like(int userId, int sharedScheduleId) {
		if (!likeExist(userId, sharedScheduleId)) {
			createLike(userId, sharedScheduleId);
		} else if (!isLike(userId, sharedScheduleId)) {
			updateLike(userId, sharedScheduleId);
		}
	}

	public void createLike(int userId, int sharedScheduleId) {
		RLock lock = redissonClient.getLock(sharedScheduleId + " lock");
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);

		try {
			if (!lock.tryLock(3, 3, TimeUnit.SECONDS))
				throw new ThreadException(GET_LOCK_FAILED);

			likeMapper.createLike(userId, sharedScheduleId);
			log.debug("좋아요 추가");

			likeMapper.incrementLike(sharedScheduleId);
			log.debug("좋아요 개수 증가");

			transactionManager.commit(status);
			log.debug("좋아요 커밋 완료");
		} catch (InterruptedException e) {
			transactionManager.rollback(status);
			log.debug("좋아요 롤백");

			throw new ThreadException(GET_LOCK_FAILED);
		} finally {
			if (lock.isLocked() && lock.isHeldByCurrentThread())
				lock.unlock();
		}
	}

	public void updateLike(int userId, int sharedScheduleId) {
		RLock lock = redissonClient.getLock(sharedScheduleId + " lock");
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);

		try {
			if (!lock.tryLock(3, 3, TimeUnit.SECONDS))
				throw new ThreadException(GET_LOCK_FAILED);

			likeMapper.updateLike(userId, sharedScheduleId);
			log.debug("좋아요 업데이트");

			likeMapper.incrementLike(sharedScheduleId);
			log.debug("좋아요 개수 증가");

			transactionManager.commit(status);
			log.debug("좋아요 커밋 완료");
		} catch (InterruptedException e) {
			transactionManager.rollback(status);
			log.debug("좋아요 롤백");

			throw new ThreadException(GET_LOCK_FAILED);
		} finally {
			if (lock.isLocked() && lock.isHeldByCurrentThread())
				lock.unlock();
		}
	}

	private boolean likeExist(int userId, int sharedScheduleId) {
		return likeMapper.likeExist(userId, sharedScheduleId);
	}

	private boolean isLike(int userId, int sharedScheduleId) {
		return likeMapper.isLike(userId, sharedScheduleId);
	}
}
