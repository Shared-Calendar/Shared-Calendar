package study.sharedcalendar.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.constant.ErrorCode;
import study.sharedcalendar.dto.Connection;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.mapper.ConnectionMapper;

@Service
@RequiredArgsConstructor
public class ConnectionService {

	private final LoginService loginService;

	private final ConnectionMapper connectionMapper;
	private final UserService userService;

	public String getInviteUrl() {
		int loginUserId = loginService.getLoginSession();
		StringBuilder inviteUrl = new StringBuilder();
		inviteUrl.append("http://localhost:8080/connections/create/")
			.append(connectionMapper.getInviteUrlCode(loginUserId));
		return inviteUrl.toString();
	}

	public void createConnection(String connectorCode) {
		int loginUserId = loginService.getLoginSession();
		int connectorId = userService.getIdByInviteCode(connectorCode);
		if (connectionMapper.getInviteUrlCode(loginUserId).equals(connectorCode)) {
			throw new AuthorizationException(ErrorCode.SELF_INVITATION);
		}
		Connection connection = connectionMapper.getConnection(loginUserId, connectorId);
		if (connection != null) {
			if (connection.isActivate()) {
				throw new DuplicateException(ErrorCode.CONNECT_DUPLICATE);
			} else {
				connectionMapper.modifyActivate(connection.getId(), true);
			}
		}
		connectionMapper.createConnection(loginUserId, connectorId);
	}
}
