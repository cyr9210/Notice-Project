package me.bong.noticeproject;

import me.bong.noticeproject.Account.Account;
import me.bong.noticeproject.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveAccount("최용락", "cyr9210@nate.com");
        saveAccount("이윤중", "lee@naver.com");
        saveAccount("박정훈", "park@daum.net");
        saveAccount("김동주", "kim@naver.com");
        saveAccount("허민수", "heo@kumho.com");
    }

    private void saveAccount(String name, String email) {
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setPassword("1234");

        accountService.save(account);
    }
}
