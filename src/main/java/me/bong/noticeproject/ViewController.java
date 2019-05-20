package me.bong.noticeproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String main(){
        return "redirect:/notice/0";
    }

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/register")
    public void register(){

    }



    @GetMapping("/post/read")
    public String read(){
        return "read";
    }

}
