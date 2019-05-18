package me.bong.noticeproject.Account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Setter @Getter @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleAccount implements Serializable {

    @Id
    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("picture")
    private String picture;

}
