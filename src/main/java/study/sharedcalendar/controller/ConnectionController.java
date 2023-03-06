package study.sharedcalendar.controller;

import java.util.List;

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

	@PostMapping("/url")
	public String getInviteUrl() {
		return connectionService.getInviteUrl();
	}

	@PostMapping("/change-code")
	public void changeInviteUrlCode() {
		connectionService.changeInviteCode();
	}

	@PostMapping("{connectorCode}")
	public void createConnection(@PathVariable String connectorCode) {
		connectionService.createConnection(connectorCode);
	}

	@PostMapping("/count")
	public int countConnection() {
		return connectionService.countConnection();
	}

	@PostMapping("/delete/{connectorUserId}")
	public void deleteConnection(@PathVariable String connectorUserId) {
		connectionService.deleteConnection(connectorUserId);
	}

	@PostMapping("/find")
	public List<String> findTenConnection() {
		return connectionService.findTenConnection();
	}

	@PostMapping("/find-all")
	public List<String> findAllConnection() {
		return connectionService.findAllConnection();
	}

	@PostMapping("/find-recent")
	public List<String> findRecentConnection() {
		return connectionService.findRecentConnection();
	}
}
