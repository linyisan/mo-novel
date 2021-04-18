package com.heng.vo;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginVo {
    private String username;
    private String password;
}
