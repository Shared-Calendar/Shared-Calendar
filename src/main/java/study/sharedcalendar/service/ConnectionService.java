package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ConnectionConstant.*;
import static study.sharedcalendar.constant.ErrorCode.*;

import java.util.List;

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
			throw new AuthorizationException(ErrorCode.SELF_CONNECTION_REQUEST);
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

	public int countConnection() {
		int loginId = loginService.getLoginSession();
		return connectionMapper.countConnection(loginId);
	}

	public void deleteConnection(String connectorUserId) {
		int loginId = loginService.getLoginSession();
		int connectorId = userService.getIdByUserId(connectorUserId);
		if (loginId == connectorId) {
			throw new AuthorizationException(SELF_DISCONNECTION_REQUEST);
		}
		Connection connection = connectionMapper.getConnection(loginId, connectorId);
		connectionMapper.modifyActivate(connection.getId(), false);
	}

	public List<String> findConnection(List<String> connection) {
		int loginId = loginService.getLoginSession();
		if (connectionMapper.countConnection(loginId) == 0) {
			throw new AuthorizationException(NO_CONNECT_ANYONE);
		}

		int offset;
		if (connection == null) {
			offset = 0;
		} else {
			String lastConnection = connection.get(connection.size() - 1);
			offset = connectionMapper.getConnection(loginId,
				userService.getIdByUserId(lastConnection)).getId();
		}

		List<String> connectionList = connectionMapper.findConnection(loginId, offset);

		if (connectionList.isEmpty()) {
			throw new AuthorizationException(NO_VIEW_ANYONE);
		}
		return connectionList;
	}

	public List<String> findRecentConnection() {
		int loginId = loginService.getLoginSession();
		if (connectionMapper.countConnection(loginId) == 0) {
			throw new AuthorizationException(NO_CONNECT_ANYONE);
		}
		return connectionMapper.findRecentConnection(loginId);
	}
}
