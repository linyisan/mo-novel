package com.heng.vo;

import com.heng.common.BaseQuery;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 小说搜索参数
 * @author LJohn
 * @since 2021-04-26
 */
@Data
@Accessors(chain = true)
public class BookQueryVo extends BaseQuery {
    // 关键字: 书名，作者
    private String keyword;

    // 书名
    private String title;

    // 作者
    private String authorName;

    // 频道: 男女频
    private Byte channel;

    // 作品分类ID
    private Integer categoryId;

    // 是否完结
    private Byte bookStatus;

    // 作品字数最小：万字以下
    private Integer wordCountMin;

    // 作品字数最大：-万字, 万字以上
    private Integer wordCountMax;

    // 实时时间：当前时间-updatePeriod
    private Date updateTimeMin;

    // 更新时间x日内
    private Long updatePeriod;

    // 排序方式：order by #{updateTime, visitCount, wordCount}
    private String sort;

    public Date getUpdateTimeMin()
    {
        if(null == this.updatePeriod) return updateTimeMin;
        long currentTimeMillis = System.currentTimeMillis();
        long period = this.updatePeriod * 24 * 3600 * 1000;
        return new Date(currentTimeMillis - period);
    }
}
