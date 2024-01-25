<template>
  <el-container>
    <el-main>
      <div class="register-form" style="margin: auto">
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
                <h1 style="color: royalblue">用户注册</h1>
              </el-row>
            </el-row>
          </el-col>
        </el-row>

        <el-form
          :model="registerParam"
          label-position="left"
          label-width="70px"
          :rules="rules"
        >
          <el-row justify="start" style="margin-top: 20px">
            <el-col :push="3" :span="18">
              <el-form-item label="昵称:" prop="nickname">
                <el-input
                  type="text"
                  prefix-icon="User"
                  placeholder="请输入昵称"
                  v-model="registerParam.nickname"
                />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row justify="start">
            <el-col :push="3" :span="18">
              <el-form-item label="性别:" prop="gender">
                <el-radio-group v-model="registerParam.gender">
                  <el-radio label="man"> 男 </el-radio>
                  <el-radio label="woman"> 女 </el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row justify="start">
            <el-col :push="3" :span="18">
              <el-form-item label="邮箱:" prop="email">
                <el-input
                  type="email"
                  prefix-icon="Message"
                  placeholder="请输入邮箱"
                  v-model="registerParam.email"
                />
              </el-form-item>
            </el-col>
            <el-col> </el-col>
          </el-row>

          <el-row justify="start">
            <el-col :span="18" :push="3">
              <el-form-item label="验证码:" prop="code">
                <el-row justify="start">
                  <el-col :span="12">
                    <el-input
                      prefix-icon="Promotion"
                      v-model="registerParam.code"
                      placeholder="请输入验证码"
                    />
                  </el-col>
                  <el-col :span="8" :push="1">
                    <el-button
                      type="primary"
                      @click="[$emit('sendCode'), countdown(60)]"
                      :disabled="disSend"
                      >{{ btnName }}</el-button
                    >
                  </el-col>
                </el-row>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row justify="start">
            <el-col :push="3" :span="18">
              <el-form-item label="密码:" prop="password">
                <el-input
                  type="password"
                  placeholder="请输入密码"
                  prefix-icon="Lock"
                  show-password
                  v-model="registerParam.password"
                />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row justify="center">
            <el-col :span="8">
              <el-text>已有账号？</el-text>
              <el-button type="primary" text>
                <router-link to="/login" style="color: blue">登录 </router-link>
              </el-button>
            </el-col>
          </el-row>
        </el-form>

        <el-row justify="center">
          <el-col :span="16">
            <el-button
              round
              style="width: 100%"
              type="primary"
              @click="$emit('register')"
              >注册</el-button
            >
          </el-col>
        </el-row>
      </div>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { RegisterParam } from "@/common/user";
import { rules } from "./formRule";

defineProps({
  registerParam: {
    type: Object as () => RegisterParam,
    required: true,
  },
});

const btnName = ref("获取验证码");
const disSend = ref(false);
let timer: ReturnType<typeof setInterval> | null = null;
const countdown = (count: number) => {
  if (!timer) {
    disSend.value = true;
    btnName.value = `${count}秒后重发`;

    timer = setInterval(() => {
      if (count > 1) {
        count--;
        btnName.value = `${count}秒后重发`;
      } else {
        clearInterval(timer);
        timer = null;
        disSend.value = false;
        btnName.value = "发送验证码";
      }
    }, 1000);
  }
};
</script>

<style scoped lang="less">
.register-form {
  height: 500px;
  width: 400px;
  box-shadow: 5px 5px 10px 0px rgb(0, 0, 0, 0.5);
  border-radius: 40px;
  // background-color: pink;
}
</style>
