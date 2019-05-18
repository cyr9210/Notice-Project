package me.bong.noticeproject.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findByEmail(){
        //given
        Account account = new Account();
        account.setEmail("cyr9210@nate.com");
        account.setName("bong");
        accountRepository.save(account);

        //when
        Account byEmail = accountRepository.findByEmail(account.getEmail());

        //then
        assertThat(byEmail.getName()).isEqualTo("bong");

    }

}