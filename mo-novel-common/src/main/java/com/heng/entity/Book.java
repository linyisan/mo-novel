package com.heng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 小说信息表
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "t_book", resultMap = "BaseResultMap")
public class Book implements Serializable {

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
     * 小说名
     */
    private String title;

    /**
     * 作者名
     */
    private String authorName;

    /**
     * 频道：0男频，1女频
     */
    private Integer channel;

    /**
     * 小说分类ID
     */
    private Integer categoryId;

    /**
     * 小说简介
     */
    private String introduction;

    /**
     * 小说封面
     */
    private String cover;

    /**
     * 状态：0下架，1连载中，2已完结
     */
    private Integer status;

    /**
     * 小说总字数
     */
    private Integer wordCount;

    /**
     * 总点击量
     */
    private Integer visitCount;

    @TableField(exist = false)
    private Category category;
}
