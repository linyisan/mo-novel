package com.heng.mapper;

import com.heng.common.ResponseDTO;
import com.heng.entity.Comment;
import com.heng.service.CommentService;
import com.heng.vo.CommentQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LJohn
 * @since 2021-05-11
 */
@SpringBootTest
class CommentMapperTest {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @Test
    void selectAllCommentJoinRating()
    {
        CommentQueryVo commentQueryVo = new CommentQueryVo();
        commentQueryVo.setResourceType((byte)1);
        commentQueryVo.setResourceId(1L);
        commentQueryVo.setUserId(5L);
        commentQueryVo.setContent("好");
        Set<Comment> comments = commentMapper.selectAllCommentJoinRating(commentQueryVo);
        System.out.println("结果---------------");
        comments.forEach(System.out::println);
    }

/*    @Test
    void searchComment()
    {
        CommentQueryVo commentQueryVo = new CommentQueryVo();
        commentQueryVo.setResourceType((byte)1);
        commentQueryVo.setResourceId(1L);
        commentQueryVo.setUserId(5L);
        commentQueryVo.setContent("好");
        ResponseDTO responseDTO = commentService.searchComment(commentQueryVo);
        System.out.println("responseDTO = " + responseDTO);
    }*/
}
