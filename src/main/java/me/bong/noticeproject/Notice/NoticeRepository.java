package me.bong.noticeproject.Notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findAll(Pageable pageable);

    Page<Notice> findByTitleContainsIgnoreCase (String keyword, Pageable pageable);

    Page<Notice> findByWriter_NameContainsIgnoreCase(String keyword, Pageable pageable);

    Page<Notice> findByTitleContainsOrWriter_NameContainsAllIgnoreCase(String keyword, String same, Pageable pageable);
}
