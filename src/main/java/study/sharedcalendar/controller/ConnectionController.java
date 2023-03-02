package study.sharedcalendar.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.service.ConnectionService;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionController {
	private final ConnectionService connectionService;

	@PostMapping("/get-url")
	public String getInviteUrl() {
		return connectionService.getInviteUrl();
	}

	@PostMapping("create/{connectorCode}")
	public void createConnection(@PathVariable String connectorCode) {
		connectionService.createConnection(connectorCode);
	}

}
