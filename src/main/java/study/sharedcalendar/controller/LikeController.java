package study.sharedcalendar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.service.LikeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {
	private final LikeService likeService;

	@PostMapping()
	public void like(@RequestParam int userId, @RequestParam int sharedScheduleId){
		likeService.like(userId, sharedScheduleId);
	}

}
