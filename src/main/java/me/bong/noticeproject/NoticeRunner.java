package me.bong.noticeproject;

import me.bong.noticeproject.Account.Account;
import me.bong.noticeproject.Account.AccountRepository;
import me.bong.noticeproject.Notice.Notice;
import me.bong.noticeproject.Notice.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Order(2)
public class NoticeRunner implements ApplicationRunner {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Account> allUser = accountRepository.findAll();
        for (int i = 0; i < allUser.size(); i++) {
            saveNotice(allUser, i);
        }

        for (int i = 6; i < 20; i++) {
            testNotice(allUser.get(0), i);
        }

    }

    private void saveNotice(List<Account> allUser, int index) {
        Notice notice = new Notice();
        notice.setTitle((index+1)+"번째 공지입니다.");
        notice.setContent("블라블라블라");
        notice.setWriter(allUser.get(index));

        noticeRepository.save(notice);
    }

    private void testNotice(Account account, int n){
        Notice notice = new Notice();
        notice.setTitle(n+"번째 공지입니다.");
        notice.setContent("블라블라블라");
        notice.setWriter(account);

        noticeRepository.save(notice);
    }
}
