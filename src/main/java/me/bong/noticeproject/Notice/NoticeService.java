package me.bong.noticeproject.Notice;

import me.bong.noticeproject.Account.Account;
import me.bong.noticeproject.Account.AccountRepository;
import me.bong.noticeproject.Account.LoginAccount;
import me.bong.noticeproject.Config.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HttpSession session;

    public Page<Notice> findAll(int page){
        PageRequest pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "created"));
        return noticeRepository.findAll(pageable);
    }

    public long countAll(){
        return noticeRepository.count();
    }

    public Notice findById(Long id){
        return noticeRepository.findById(id).get();
    }

    public void save(Notice notice){
        Optional<Account> byEmail = accountRepository.findByEmail(notice.getWriter_email());
        Account writer = byEmail.get();
        notice.setWriter(writer);

        noticeRepository.save(notice);
    }

    public void delete(Long id){
        noticeRepository.deleteById(id);
    }

    public Page<Notice> searchTitle(String keyword, int page){
        PageRequest pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "created"));
        return noticeRepository.findByTitleContainsIgnoreCase(keyword, pageable);
    }

    public Page<Notice> searchWriter(String keyword, int page){
        PageRequest pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "created"));
        return noticeRepository.findByWriter_NameContainsIgnoreCase(keyword, pageable);
    }

    public Page<Notice> searchAll(String keyword, int page){
        PageRequest pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "created"));
        return noticeRepository.findByTitleContainsOrWriter_NameContainsAllIgnoreCase(keyword, keyword, pageable);
    }

    public Page<Notice> myNotice(int page){
        PageRequest pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "created"));
        LoginAccount loginUser = (LoginAccount) session.getAttribute(SessionConstants.LOGIN_USER);
        return noticeRepository.findByWriter_email(loginUser.getEmail(), pageable);
    }

}
