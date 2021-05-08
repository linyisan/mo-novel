package com.heng.vo;

import com.heng.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private Long id;
    private String name;
    private Set<Role> roles;
    private String avatar;
}
