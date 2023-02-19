package study.sharedcalendar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final HttpSession httpSession;

    public void setLoginSession(String userId) {
        httpSession.setAttribute("userId", userId);
    }

    public void unsetLoginSession() {
        httpSession.invalidate();
    }
}
