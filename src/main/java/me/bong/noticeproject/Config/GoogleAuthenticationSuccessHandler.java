package me.bong.noticeproject.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.bong.noticeproject.Account.GoogleAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GoogleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpSession httpSession;

    private final ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpSession.setAttribute(SessionConstants.LOGIN_USER, getGoogleUser(authentication));
        httpServletResponse.sendRedirect("/");
    }

    private GoogleAccount getGoogleUser(Authentication authentication) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        return objectMapper.convertValue(oAuth2Authentication.getUserAuthentication().getDetails(), GoogleAccount.class);
    }

}
