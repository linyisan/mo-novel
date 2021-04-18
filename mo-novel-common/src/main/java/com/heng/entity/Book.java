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
 * 小说信息
 * </p>
 *
 * @author LJohn
 * @since 2021-04-09
 */
@TableName("t_book")
@ApiModel(value="Book对象", description="小说信息")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "书名")
    private String title;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "作者名")
    private String authorName;

    @ApiModelProperty(value = "更新状态：1连载，2完结")
    private Integer status;

    @ApiModelProperty(value = "小说总字数")
    private Integer wordCount;

    @ApiModelProperty(value = "频道：0男频，1女频")
    private Integer chanel;

    @ApiModelProperty(value = "小说分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "小说简介")
    private String introduction;

    @ApiModelProperty(value = "小说原网站")
    private String sourceUrl;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getChanel() {
        return chanel;
    }

    public void setChanel(Integer chanel) {
        this.chanel = chanel;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", title=" + title +
        ", cover=" + cover +
        ", authorName=" + authorName +
        ", status=" + status +
        ", wordCount=" + wordCount +
        ", chanel=" + chanel +
        ", categoryId=" + categoryId +
        ", introduction=" + introduction +
        ", sourceUrl=" + sourceUrl +
        "}";
    }
}
