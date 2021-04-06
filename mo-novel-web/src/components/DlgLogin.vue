<template>
  <div role="dialog" aria-modal="true" aria-label="登录" class="el-dialog el-dialog--center ys-login"
       style="margin-top: 15vh; width: 300px;">
    <div class="el-dialog__header"><span class="el-dialog__title">登录</span>
      <button type="button" aria-label="Close" class="el-dialog__headerbtn"><i
        class="el-dialog__close el-icon el-icon-close"></i></button>
    </div>
    <div class="el-dialog__body">
      <form class="el-form">
        <div class="el-form-item is-success is-required"><label for="name" class="el-form-item__label">邮箱</label>
          <div class="el-form-item__content">
            <div class="el-input"><input type="text" autocomplete="off" class="el-input__inner" id="name"></div>
          </div>
        </div>
        <div class="el-form-item is-success is-required" style="margin-bottom: 20px;"><label for="password"
                  class="el-form-item__label">密码</label>
          <div class="el-form-item__content">
            <div class="el-input"><!----><input type="password" autocomplete="off" class="el-input__inner" id="password"><!---->
              <!----><!----><!----></div><!----></div>
        </div>
        <div class="el-form-item" style="display: flex; justify-content: center;"><!---->
          <div class="el-form-item__content">
            <button type="button" class="el-button el-button--primary" style="width: 200px;"><!---->
              <!----><span>登录</span></button><!----></div>
        </div>
      </form>
    </div><!----></div>
</template>

<script>
export default {
  name: 'DlgLogin'
};
</script>

<style scoped>

</style>
