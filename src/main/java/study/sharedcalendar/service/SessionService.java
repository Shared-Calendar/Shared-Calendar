package study.sharedcalendar.service;

import org.springframework.stereotype.Service;
import study.sharedcalendar.exception.RuntimeExceptionVO;
import javax.servlet.http.HttpSession;
import static study.sharedcalendar.exception.RuntimeExceptionCode.NO_LOGIN_INFORMATION;

@Service
public class SessionService {

    public static void createSession(HttpSession session, Object value){
         session.setAttribute(session.getId(), value);
    }

    public static Object getSession(HttpSession session){
        if(session == null){
            throw new RuntimeExceptionVO(NO_LOGIN_INFORMATION);
        }

        return session.getAttribute(session.getId());
    }

    public static void deleteSession(HttpSession session){
        if(session != null){
            session.invalidate();
        }

        throw new RuntimeExceptionVO(NO_LOGIN_INFORMATION);
    }

}
