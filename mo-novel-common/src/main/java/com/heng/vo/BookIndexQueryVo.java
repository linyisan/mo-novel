package com.heng.vo;

import com.heng.common.BaseQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author LJohn
 * @since 2021-05-05
 */
@Data
public class BookIndexQueryVo extends BaseQuery {
//    @NotNull(message = "小说ID不能为空")
    private Long bookId;
    private String title;
    private Byte status;
}
