package com.heng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heng.common.ResponseDTO;
import com.heng.entity.Comment;
import com.heng.vo.CommentQueryVo;

import java.util.List;

/**
 * <p>
 * 评价表 服务类
 * </p>
 *
 * @author LJohn
 * @since 2021-05-01
 */
public interface CommentService extends IService<Comment> {
    List<Comment> searchComment(CommentQueryVo commentQueryVo);

    ResponseDTO searchBookComment(CommentQueryVo commentQueryVo);
}
