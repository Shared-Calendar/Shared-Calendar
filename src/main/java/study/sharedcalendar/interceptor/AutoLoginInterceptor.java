package study.sharedcalendar.interceptor;

import static study.sharedcalendar.constant.ErrorCode.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.constant.UserConstant;
import study.sharedcalendar.exception.NoMatchedUserException;
import study.sharedcalendar.service.UserService;

@Component
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {

	private final UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			Integer user = (Integer)httpSession.getAttribute(UserConstant.SESSION_ID);
			if (user != null) {
				return true;
			}
		}

		throw new NoMatchedUserException(NO_LOGIN_INFORMATION);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
