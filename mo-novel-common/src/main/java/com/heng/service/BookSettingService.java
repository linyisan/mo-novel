package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.dto.BookSettingQuery;
import com.heng.entity.BookSetting;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页小说设置表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-15
 */
public interface BookSettingService extends IService<BookSetting> {
    /**
     * 查询首页小说设置列表数据
     * @return map(类型, 以类型分组)
     * */
    Map<Byte, List<BookSetting>> getAllBookSetting();

    ResponseDTO searchBookSetting(BookSettingQuery query);
}
