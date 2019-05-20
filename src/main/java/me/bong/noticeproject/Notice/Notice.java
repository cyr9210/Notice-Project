package me.bong.noticeproject.Notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.bong.noticeproject.Account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Notice {

    @Id @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @OneToOne
    @NotNull
    private Account writer;

    @Transient
    private String writer_email;

    @Lob
    @NotNull
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date modified = new Date();



}
