package com.heng.dto;

import lombok.Data;

/**
 * 统计分类数量
 * @author LJohn
 * @since 2021-05-15
 */
@Data
public class CategoryCountDO {
    private Long categoryId;
    private Long count;
}
