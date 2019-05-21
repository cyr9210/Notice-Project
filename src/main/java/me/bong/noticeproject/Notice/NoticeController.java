package me.bong.noticeproject.Notice;

import me.bong.noticeproject.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;


    @GetMapping("/{page}")
    public String findall(Model model, @PathVariable("page") int page){
        Page<Notice> all = noticeService.findAll(page);
        if(page < 0 || page >= all.getTotalPages()){
            throw new Application.urlNotfoundException();
        }

        model.addAttribute("list", all);

        long countall = noticeService.countAll();

        return "main";
    }

    @GetMapping("/read/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        Notice byId = noticeService.findById(id);
        model.addAttribute("notice", byId);
        return "read";
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @PostMapping("/save")
    public String save(Notice notice){
        noticeService.save(notice);

        return "redirect:/notice/read/" + notice.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/notice/0";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Notice byId = noticeService.findById(id);
        model.addAttribute("notice", byId);

        return "update";
    }

    @PostMapping("/update")
    public String update(Notice notice){
        Notice byId = noticeService.findById(notice.getId());
        byId.setTitle(notice.getTitle());
        byId.setContent(notice.getContent());
        byId.setModified(new Date());

        return "redirect:/notice/read/" + notice.getId();
    }

    @GetMapping("/search/{page}")
    public String search(Search search, Model model, @PathVariable int page, HttpServletResponse response){
        Cookie keyword = new Cookie("keyword", search.getKeyword());

        keyword.setMaxAge(60*60); //쿠키 유지 시간
        keyword.setPath("/notice/search/"); // 쿠키접근허용경로

        System.out.println("쿠키저장" + keyword.getValue());

        response.addCookie(keyword);

        if(search.getCheck().equals("title")){
            return "redirect:title/"+page;
        }else if(search.getCheck().equals("writer")){
            return "redirect:writer/"+page;
        }else if (search.getCheck().equals("title,writer")){
            return "redirect:all/"+page;
        }
        return "redirect:/notice/0";
    }

    @GetMapping("/search/title/{page}")
    public String searchTitle(@CookieValue(defaultValue = "", value = "keyword") String keyword, Model model, @PathVariable int page){
        System.out.println("쿠키사용" + keyword);
        Page<Notice> search = noticeService.searchTitle(keyword, page);
        model.addAttribute("list", search);
        return "main";
    }

    @GetMapping("/search/writer/{page}")
    public String searchWriter(@CookieValue(defaultValue = "", value = "keyword") String keyword, Model model, @PathVariable int page){
        Page<Notice> search = noticeService.searchWriter(keyword, page);
        model.addAttribute("list", search);
        return "main";
    }

    @GetMapping("/search/all/{page}")
    public String searchAll(@CookieValue(defaultValue = "", value = "keyword") String keyword, Model model, @PathVariable  int page){
        Page<Notice> search = noticeService.searchAll(keyword, page);
        model.addAttribute("list", search);
        return "main";
    }

    @GetMapping("/myNotice/{page}")
    public String myNotice(@PathVariable int page, Model model){
        Page<Notice> myNotice = noticeService.myNotice(page);
        model.addAttribute("list", myNotice);
        return "main";
    }

}
