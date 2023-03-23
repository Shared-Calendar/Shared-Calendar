package study.sharedcalendar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import study.sharedcalendar.dto.Schedule;

@Mapper
public interface ScheduleMapper {

	void createSchedule(Schedule schedule);

	void modifySchedule(Schedule schedule);

	void deleteSchedule(int id);

	Boolean isSchedule(Schedule schedule);

	List<Schedule> findMonthSchedule(@Param("year") int year, @Param("month") int month,
		@Param("date")
		String date, @Param("userId") int userId, @Param("connecteeId") int connecteeId);

	List<Schedule> findDaySchedule(@Param("year") int year, @Param("month") int month,
		@Param("day") int day, @Param("id")
	int id, @Param("userId") int userId, @Param("connecteeId") int connecteeId);
}
