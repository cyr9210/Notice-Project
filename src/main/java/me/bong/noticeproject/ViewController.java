package me.bong.noticeproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/register")
    public void register(){

    }

    @GetMapping("/post/write")
    public String write(){
        return "write";
    }

    @GetMapping("/post/read")
    public String read(){
        return "read";
    }

}
