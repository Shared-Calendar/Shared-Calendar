package study.sharedcalendar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.service.ConnectionService;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionController {
	private final ConnectionService connectionService;

	@GetMapping
	public String getInviteUrl() {
		return connectionService.getInviteUrl();
	}

	@PatchMapping
	public void changeInviteUrlCode() {
		connectionService.changeInviteCode();
	}

	@PutMapping("{connectorCode}")
	public void createConnection(@PathVariable String connectorCode) {
		connectionService.createConnection(connectorCode);
	}
}