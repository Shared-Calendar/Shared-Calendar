package study.sharedcalendar.service;

import static study.sharedcalendar.constant.UserConstant.*;

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
		int loginId = loginService.getLoginSession();
		return (USER_INVITE_URL + connectionMapper.getInviteUrlCode(loginId));
	}

	public void changeInviteCode() {
		int loginId = loginService.getLoginSession();
		userService.modifyInviteUrlCode(loginId);
	}

	public void createConnection(String connectorCode) {
		int loginId = loginService.getLoginSession();
		int connectorId = userService.getIdByInviteCode(connectorCode);

		if (connectionMapper.getInviteUrlCode(loginId).equals(connectorCode)) {
			throw new AuthorizationException(ErrorCode.SELF_INVITATION);
		}

		Connection connection = connectionMapper.getConnection(loginId, connectorId);
		if (connection != null) {
			if (connection.isActivate()) {
				throw new DuplicateException(ErrorCode.CONNECT_DUPLICATE);
			} else {
				connectionMapper.modifyActivate(connection.getId(), true);
			}
		}

		connectionMapper.createConnection(loginId, connectorId);
	}
}