package com.heng.vo;

import lombok.Data;

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
