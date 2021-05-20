package com.heng.vo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
    private String orderBy = "create_time";
    private Boolean isASC = true;

    public void setPage(Integer page)
    {
        this.page = page <= 1 ? 0 : page-1;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = StringUtils.isBlank(orderBy) ? this.orderBy : orderBy;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit < 0 ? 10 : limit;
    }

}
