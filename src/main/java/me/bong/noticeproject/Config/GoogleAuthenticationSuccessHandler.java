package me.bong.noticeproject.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.bong.noticeproject.Account.Account;
import me.bong.noticeproject.Account.AccountRepository;
import me.bong.noticeproject.Account.LoginAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GoogleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpSession httpSession;

    private final ObjectMapper objectMapper;

    private final AccountRepository accountRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginAccount loginAccount = getGoogleUser(authentication);
        httpSession.setAttribute(SessionConstants.LOGIN_USER, loginAccount);

        Optional<Account> byEmail = accountRepository.findByEmail(loginAccount.getEmail());

        if(!byEmail.isPresent()){
            Account account = new Account();
            account.setEmail(loginAccount.getEmail());
            account.setName(loginAccount.getName());

            accountRepository.save(account);
        }

        httpServletResponse.sendRedirect("/");
    }

    private LoginAccount getGoogleUser(Authentication authentication) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        return objectMapper.convertValue(oAuth2Authentication.getUserAuthentication().getDetails(), LoginAccount.class);
    }

}
