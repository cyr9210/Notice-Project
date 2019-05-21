package me.bong.noticeproject.Notice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void search(){
        Page<Notice> searchTitle = noticeRepository.findByTitleContainsIgnoreCase("번째", PageRequest.of(0, 10));
        assertThat(searchTitle.getTotalElements()).isEqualTo(noticeRepository.count());

        Page<Notice> searchWriter = noticeRepository.findByWriter_NameContainsIgnoreCase("최용락", PageRequest.of(0, 10));
        assertThat(searchWriter.getTotalElements()).isEqualTo(95);

        //검색창이 하나로 keyword 두개가 같음.
        Page<Notice> searchKeyword = noticeRepository.findByTitleContainsOrWriter_NameContainsAllIgnoreCase("공지", "공지", PageRequest.of(0, 10));
        assertThat(searchKeyword.getTotalElements()).isEqualTo(noticeRepository.count());

        Page<Notice> searchKeyword2 = noticeRepository.findByTitleContainsOrWriter_NameContainsAllIgnoreCase("최용락", "최용락",PageRequest.of(0, 10));
        assertThat(searchKeyword2.getTotalElements()).isEqualTo(95);

    }

    @Test
    public void findbyEmail(){
        Page<Notice> byWriter_email = noticeRepository.findByWriter_email("cyr9210@nate.com", PageRequest.of(0,10));
        assertThat(byWriter_email.getTotalElements()).isEqualTo(95);
    }

}