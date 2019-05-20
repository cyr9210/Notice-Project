package me.bong.noticeproject.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/{page}")
    public String findall(Model model,@PathVariable("page") int page){
        Page<Notice> all = noticeService.findAll(page);
        model.addAttribute("list", all);

        long countall = noticeService.countAll();
        model.addAttribute("size", countall);

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

    @GetMapping("/search")
    public String search(Search search, Model model){

        if(search.getCheck().equals("title")){
            searchTitle(search.getKeyword(), model, search.getPage());
            return "main";
        }else if(search.getCheck().equals("writer")){
            searchWriter(search.getKeyword(), model, search.getPage());
            return "main";
        }else if (search.getCheck().equals("title,writer")){
            searchAll(search.getKeyword(), model, search.getPage());
            return "main";
        }
        return "redirect:/notice/0";
    }


    public void searchTitle(String keyword, Model model, int page){
        Page<Notice> search = noticeService.searchTitle(keyword, page);
        model.addAttribute("list", search);
        model.addAttribute("size", search.getTotalElements());
    }

    public void searchWriter(String keyword, Model model, int page){
        Page<Notice> search = noticeService.searchWriter(keyword, page);
        model.addAttribute("list", search);
        model.addAttribute("size", search.getTotalElements());
    }

    public void searchAll(String keyword, Model model, int page){
        Page<Notice> search = noticeService.searchAll(keyword, page);
        model.addAttribute("list", search);
        model.addAttribute("size", search.getTotalElements());
    }


}
