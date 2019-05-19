package me.bong.noticeproject.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @Test
    public void findByEmail(){
        //given
        Account account = new Account();
        account.setEmail("cyr9210@nate.com");
        account.setName("bong");
        account.setPassword("1234");
        accountService.save(account);

        //when
        Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());

        //then
        assertThat(byEmail.get().getName()).isEqualTo("bong");

    }

}