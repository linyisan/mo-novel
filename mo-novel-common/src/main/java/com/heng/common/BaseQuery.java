package com.heng.common;

import lombok.*;

/**
 * @author LJohn
 * @since 2021-05-05
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseQuery {
    private Integer page = 0;
    private Integer limit = 10;

    public void setPage(Integer page)
    {
        this.page = page <= 1 ? 0 : page-1;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit < 0 ? 10 : limit;
    }
}
