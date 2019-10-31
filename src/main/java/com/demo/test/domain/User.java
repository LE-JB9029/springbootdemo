package com.demo.test.domain;

import com.demo.common.domain.LongId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends LongId {
    private String username;
    private String email;
    private String password;
    private String createTime;
    private String lastLoginTime;
    private String status;
}
