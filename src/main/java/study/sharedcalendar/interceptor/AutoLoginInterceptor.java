package study.sharedcalendar.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import study.sharedcalendar.dto.UserDTO;
import study.sharedcalendar.exception.RuntimeExceptionVO;
import study.sharedcalendar.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static study.sharedcalendar.exception.RuntimeExceptionCode.NO_LOGIN_INFORMATION;
import static study.sharedcalendar.service.SessionService.getSession;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session!=null) {
            UserDTO user = (UserDTO)getSession(session);
            if(user!=null && userService.findUser(user)!=null) {
                return true;
            }
        }

        throw new RuntimeExceptionVO(NO_LOGIN_INFORMATION);
    }
}
