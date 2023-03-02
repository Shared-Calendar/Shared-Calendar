package study.sharedcalendar.service;

import static study.sharedcalendar.constant.ErrorCode.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.dto.SignUpReq;
import study.sharedcalendar.exception.DuplicateException;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper userMapper;
	private final EncryptionService encryptionService;

	private String createInviteCode() {
		return RandomStringUtils.randomAlphabetic(12);
	}

	public void signUp(SignUpReq signUpReq) {
		if (userIdExist(signUpReq.getUserId())) {
			throw new DuplicateException(ID_DUPLICATE);
		}

		String encryptedPassword = encryptionService.encrypt(signUpReq.getPassword());
		SignUpReq signUpSignUpReq = SignUpReq.builder()
			.userId(signUpReq.getUserId())
			.password(encryptedPassword)
			.email(signUpReq.getEmail())
			.inviteUrlCode(createInviteCode())
			.build();

		userMapper.createUser(signUpSignUpReq);
	}

	public void userIdDuplicationCheck(String userId) {
		if (userIdExist(userId)) {
			throw new DuplicateException(ID_DUPLICATE);
		}
	}

	public boolean userIdExist(String userId) {
		return userMapper.userIdExist(userId);
	}

	public int getIdByInviteCode(String inviteCode) {
		Integer id = userMapper.getIdByInviteCode(inviteCode);
		if (id == null) {
			throw new NoMatchedUserException(NO_MATCHING_USER_ID);
		}
		return id;
	}

}
