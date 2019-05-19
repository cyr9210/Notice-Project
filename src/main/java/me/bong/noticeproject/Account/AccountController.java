package me.bong.noticeproject.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public String register(Account account){
        System.out.println("==========register==========");
        System.out.println(account);

        accountService.save(account);
        return "redirect:/login";
    }
}
