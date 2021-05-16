package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.Notice;
import com.heng.vo.NoticeQuery;

/**
 * <p>
 * 站内消息 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-16
 */
public interface NoticeService extends IService<Notice> {

    ResponseDTO searchNotice(NoticeQuery query);
}
