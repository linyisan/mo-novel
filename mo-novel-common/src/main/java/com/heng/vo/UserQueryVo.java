package com.heng.vo;

import com.heng.common.BaseQuery;
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
