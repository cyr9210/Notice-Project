package me.bong.noticeproject;

import me.bong.noticeproject.Account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/register")
    public void register(){

    }

    @GetMapping("/write")
    public void write(){
        
    }

}
