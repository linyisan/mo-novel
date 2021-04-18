package com.heng.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 书单-小说关联
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@TableName("t_booklist_book")
@ApiModel(value="BooklistBook对象", description="书单-小说关联")
public class BooklistBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "书单ID")
    private Integer booklistId;

    @ApiModelProperty(value = "小说ID")
    private Integer bookId;

    @ApiModelProperty(value = "推荐语")
    private String bookReview;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBooklistId() {
        return booklistId;
    }

    public void setBooklistId(Integer booklistId) {
        this.booklistId = booklistId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookReview() {
        return bookReview;
    }

    public void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    @Override
    public String toString() {
        return "BooklistBook{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", booklistId=" + booklistId +
        ", bookId=" + bookId +
        ", bookReview=" + bookReview +
        "}";
    }
}
