package me.bong.noticeproject;

import me.bong.noticeproject.Account.Account;
import me.bong.noticeproject.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = new Account();
        account.setName("bong");
        account.setEmail("cyr9210@nate.com");
        account.setPassword("1234");

        accountService.save(account);
    }
}
