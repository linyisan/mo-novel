package com.heng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "t_user", resultMap = "BaseResultMap")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 性别：0男，1女
     */
    private Integer sex;

    /**
     * 邮件
     */
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp="^1[3|4|5|6|7|8|9][0-9]{9}$", message="手机号格式不正确！")
    private String mobile;

    /**
     * 状态：0禁用，1可用
     */
    private Integer status;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色集合
     */
    @TableField(exist = false) // 非数据库字段
    private List<Role> roles;

}
