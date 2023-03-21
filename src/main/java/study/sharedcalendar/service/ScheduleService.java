package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;
import static study.sharedcalendar.constant.ScheduleConstant.*;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.Schedule;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.ScheduleMapper;

@Service
@RequiredArgsConstructor
public class ScheduleService {
	private final UserService userService;
	private final LoginService loginService;
	private final ScheduleMapper scheduleMapper;
	private final ConnectionService connectionService;
	private final HttpSession httpSession;

	public void setCalendarSession(String connectorUserId) {
		int loginId = loginService.getLoginSession();
		int connecteeId = userService.getIdByUserId(connectorUserId);

		if (connectionService.isConnect(loginId, connecteeId)) {
			httpSession.setAttribute(SCHEDULE_SESSION_ID,
				userService.getIdByUserId(connectorUserId));
		}
	}

	public int getCalendarSession() {
		Integer id = (Integer)httpSession.getAttribute(SCHEDULE_SESSION_ID);
		if (id == null) {
			throw new NoMatchedUserException(NO_MATCHING_CONNECTION);
		}
		return id;
	}

	public void createSchedule(Schedule schedule) {
		int loginId = loginService.getLoginSession();
		int connectorId = getCalendarSession();

		Schedule saveSchedule = Schedule.builder()
			.userId(loginId)
			.connecteeId(connectorId)
			.content(schedule.getContent())
			.date(schedule.getDate())
			.activate(schedule.isActivate())
			.build();

		scheduleMapper.createSchedule(saveSchedule);
	}

	@Transactional
	public void modifySchedule(Schedule schedule) {
		if (!scheduleMapper.isSchedule(schedule)) {
			throw new AuthorizationException(NO_MATCHING_SCHEDULE);
		}

		scheduleMapper.modifySchedule(schedule);
	}

	@Transactional
	public void deleteSchedule(Schedule schedule) {
		if (!scheduleMapper.isSchedule(schedule)) {
			throw new AuthorizationException(NO_MATCHING_SCHEDULE);
		}

		scheduleMapper.deleteSchedule(schedule.getId());
	}

	public List<Schedule> findMonthSchedule(int year, int month, List<Schedule> scheduleList) {
		int loginId = loginService.getLoginSession();
		int connecteeId = getCalendarSession();
		List<Schedule> scheduleMonthList;

		if (scheduleList == null) {
			LocalDate date = LocalDate.of(year, month, 1);
			scheduleMonthList = scheduleMapper.findMonthSchedule(year, month,
				date.toString(), loginId, connecteeId);
		} else {
			Schedule lastViewSchedule = scheduleList.get(scheduleList.size() - 1);
			scheduleMonthList = scheduleMapper.findMonthSchedule(year, month,
				lastViewSchedule.getDate().toString(), loginId, connecteeId);
		}

		if (scheduleMonthList.isEmpty()) {
			throw new AuthorizationException(NO_SCHEDULE_ANYMORE);
		}

		return scheduleMonthList;
	}

	public List<Schedule> findDaySchedule(int year, int month, int day,
		List<Schedule> scheduleList) {
		int loginId = loginService.getLoginSession();
		int connecteeId = getCalendarSession();
		List<Schedule> scheduleDayList;

		if (scheduleList == null) {
			scheduleDayList = scheduleMapper.findDaySchedule(year, month, day,
				0, loginId, connecteeId);
		} else {
			Schedule lastViewSchedule = scheduleList.get(scheduleList.size() - 1);
			scheduleDayList = scheduleMapper.findDaySchedule(year, month, day,
				lastViewSchedule.getId(), loginId, connecteeId);
		}

		if (scheduleDayList.isEmpty()) {
			throw new AuthorizationException(NO_SCHEDULE_ANYMORE);
		}

		return scheduleDayList;
	}
}
