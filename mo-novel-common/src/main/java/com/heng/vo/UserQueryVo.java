package com.heng.vo;

import lombok.Data;

/**
 * @author LJohn
 * @since 2021-05-05
 */
@Data
public class UserQueryVo extends BaseQuery {
    private String username;
    private String mobile;
    private String email;
    private Byte sex;
    private Long roleId;
}
