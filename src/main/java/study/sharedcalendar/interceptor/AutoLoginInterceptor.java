package study.sharedcalendar.interceptor;

import static study.sharedcalendar.constant.ErrorCode.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.RequiredArgsConstructor;
import study.sharedcalendar.exception.AuthorizationException;
import study.sharedcalendar.service.UserService;

@Component
@RequiredArgsConstructor
public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession(false);
        if(httpSession!=null) {
            Integer user = (Integer) httpSession.getAttribute("id");
            if(user!=null) {
                return true;
            }
        }

        throw new AuthorizationException(NO_MATCHING_USER_PASSWORD);
    }
}
