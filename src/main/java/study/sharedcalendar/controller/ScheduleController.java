package study.sharedcalendar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.Schedule;
import study.sharedcalendar.service.ScheduleService;

@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;

	@GetMapping("{connectorUserId}")
	public void calenderSession(@PathVariable String connectorUserId) {
		scheduleService.setCalendarSession(connectorUserId);
	}

	@PutMapping()
	public void createSchedule(@RequestBody @Valid Schedule schedule) {
		scheduleService.createSchedule(schedule);
	}

	@PostMapping("modify")
	public void modifySchedule(@RequestBody @Valid Schedule schedule) {
		scheduleService.modifySchedule(schedule);
	}

	@DeleteMapping()
	public void deleteSchedule(@RequestBody Schedule schedule) {
		scheduleService.deleteSchedule(schedule);
	}

	@GetMapping("/{year}/{month}")
	public List<Schedule> findMonthSchedule(@PathVariable int year, @PathVariable int month,
		@RequestBody(required = false) List<Schedule> scheduleList) {
		return scheduleService.findMonthSchedule(year, month, scheduleList);
	}

	@GetMapping("/{year}/{month}/{day}")
	public List<Schedule> findDaySchedule(@PathVariable int year, @PathVariable int month,
		@PathVariable int day, @RequestBody(required = false) List<Schedule> scheduleList) {
		return scheduleService.findDaySchedule(year, month, day, scheduleList);
	}
}
