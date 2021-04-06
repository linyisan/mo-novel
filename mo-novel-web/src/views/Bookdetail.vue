<template>
  <el-container>
    <el-header style="height: auto">
      <div class="book-info">
        <div class="book-info-cover">
          <el-image alt="加载失败"></el-image>
        </div>
        <div class="book-info-detail">
          <h1 class="book-name">开局主神挂了</h1>
          <p class="book-author">青楼书生</p>
          <p class="book-word-count">13万字</p>
          <p class="book-status">更新时间：15 小时 ·连载</p>
          <div class="book-info-btns">
            <el-button type="primary">立即阅读</el-button>
            <el-button>加入书架</el-button>
            <el-button>收入书单</el-button>
            <el-dropdown trigger="click" size="medium">
            <span class="el-dropdown-link">
              <i class="el-icon-more"></i>
            </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>错误反馈</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>
      <div class="book-score">
        <div class="book-score-overview">
          <h2 style="display: inline-block">6.3</h2><span class="score-count">97个评分</span>
        </div>
        <div class="book-score-charts"></div>
        <el-divider></el-divider>
        我的评分：
        <el-rate></el-rate>
      </div>
    </el-header>
    <el-main>
      <comment-card></comment-card>
      <comment-card></comment-card>
      <comment-card></comment-card>
      <comment-card></comment-card>
      <div class="hidden-sm-and-down" style="position: fixed; right: 10px; width: 280px;">
        <el-card class="hidden-sm-and-down sticky">
          <h1>收录书单1751次</h1>
          <p>这是一个必须大力批判的书单</p>
          <p>这是一个必须大力批判的书单</p>
          <p>这是一个必须大力批判的书单</p>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import CommentCard from '../components/commentCard';

export default {
  name: 'bookdetail',
  components: { CommentCard }
};
</script>

<style scoped>

  .sticky {
    position: fixed;
    top: 68px;
    width: 280px;
  }
</style>
