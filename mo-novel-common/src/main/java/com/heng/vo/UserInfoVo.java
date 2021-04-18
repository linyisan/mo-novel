package com.heng.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private String[] roles;
    private String introduction;
    private String avatar;
    private String name;
}
