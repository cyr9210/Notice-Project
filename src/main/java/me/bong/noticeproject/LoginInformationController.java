package me.bong.noticeproject;

import lombok.RequiredArgsConstructor;
import me.bong.noticeproject.Config.SessionConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginInformationController {

    private final HttpSession httpSession;

    @GetMapping("/me")
    public Map<String, Object> me(){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("profile", httpSession.getAttribute(SessionConstants.LOGIN_USER));
        return response;
    }
}
