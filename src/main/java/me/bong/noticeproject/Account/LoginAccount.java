package me.bong.noticeproject.Account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginAccount implements Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

//    @JsonProperty("picture")
//    private String picture;

}
