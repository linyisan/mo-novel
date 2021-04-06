<template>
  <el-card class="box-card book-comment">
    <div style="overflow: auto">
      <a style="float: left;vertical-align: middle">
        <el-avatar src="#"></el-avatar>
        <span style="vertical-align: middle">评论者</span>
      </a>
      <el-rate style="float: right;vertical-align: middle" disabled :value="4"></el-rate>
<!--      <div style="clear: both"></div>-->
<!--      float不占空间，使用clear:both撑起-->
    </div>
    <div v-for="o in 5" :key="o" class="text item">
      {{'列表内容 ' + o }}
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'commentCard'
};
</script>

<style scoped>
  .book-comment {
    padding: 30px;
    margin-bottom: 10px;
    max-width: 830px;
  }
</style>
