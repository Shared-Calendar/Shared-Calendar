package study.sharedcalendar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
	int createLike(@Param("userId") int userId, @Param("sharedScheduleId") int sharedScheduleId);

	boolean likeExist(@Param("userId") int userId, @Param("sharedScheduleId") int sharedScheduleId);

	void updateLike(@Param("userId") int userId, @Param("sharedScheduleId") int sharedScheduleId);

	void incrementLike(int sharedScheduleId);

	boolean isLike(@Param("userId") int userId, @Param("sharedScheduleId") int sharedScheduleId);

	void updateUnlike(@Param("userId") int userId, @Param("sharedScheduleId") int sharedScheduleId);

	void decrementLike(int sharedScheduleId);
}
