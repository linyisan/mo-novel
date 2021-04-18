<template>
  <el-menu
    default-active="2"
    class="el-menu-demo"
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="1"><router-link to="/bookdetail">详细页</router-link> </el-menu-item>
    <el-menu-item index="1"><router-link to="/test">测试页</router-link> </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'Header'
};
</script>

<style scoped>

</style>
