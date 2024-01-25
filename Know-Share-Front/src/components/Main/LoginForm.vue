<template>
  <el-container>
    <el-main>
      <div class="login-form" style="margin: auto">
        <el-row justify="center" align="middle">
          <el-col :span="12">
            <el-row justify="center" align="middle" style="margin-top: 10px">
              <el-col :span="6">
                <el-image src="src/assets/ape-logo.png" />
              </el-col>
              <el-col :span="17" :push="1">
                <el-text style="font-size: large; color: black"
                  >Know Share Hub</el-text
                >
              </el-col>
            </el-row>
            <el-row justify="center" align="middle">
              <el-row :span="8">
                <h1 style="color: royalblue">用户登录</h1>
              </el-row>
            </el-row>
          </el-col>
        </el-row>

        <el-form :model="loginParam" label-position="left" :rules="rules">
          <el-row justify="start" style="margin-top: 40px">
            <el-col :push="3" :span="18">
              <el-form-item label="邮箱:" prop="email">
                <el-input
                  type="email"
                  prefix-icon="Message"
                  placeholder="请输入邮箱"
                  v-model="loginParam.email"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row justify="start" style="margin-top: 10px">
            <el-col :push="3" :span="18">
              <el-form-item label="密码:" prop="password">
                <el-input
                  type="password"
                  placeholder="请输入密码"
                  prefix-icon="Lock"
                  show-password
                  v-model="loginParam.password"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row justify="center" style="margin-top: 10px">
            <el-col :span="8">
              <el-text>没有账号？</el-text>
              <el-button type="primary" text>
                <router-link to="register" style="color: blue"
                  >注册</router-link
                >
              </el-button>
            </el-col>
          </el-row>
        </el-form>

        <el-row justify="center" style="margin-top: 10px">
          <el-col :span="16">
            <el-button
              round
              style="width: 100%"
              type="primary"
              @click="$emit('login')"
              >登录</el-button
            >
          </el-col>
        </el-row>
      </div>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { LoginParam } from "@/common/user";
import { rules } from "./formRule";

defineProps({
  loginParam: {
    type: Object as () => LoginParam,
    required: true,
  },
});
</script>

<style scoped lang="less">
.login-form {
  height: 400px;
  width: 400px;
  box-shadow: 5px 5px 10px 0px rgb(0, 0, 0, 0.5);
  border-radius: 40px;
  // background-color: pink;
}
</style>
./formRule
