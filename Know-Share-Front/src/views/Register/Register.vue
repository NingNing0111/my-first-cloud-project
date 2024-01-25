<template>
  <div>
    <Header />
    <RegisterForm
      :register-param="registerParam"
      @register="register(registerParam)"
      @sendCode="sendCode(registerParam.email)"
    />
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { RegisterParam } from "@/common/user";
import { sendCode, register } from "@/api/user/register";

const registerParam = ref<RegisterParam>({
  nickname: "",
  email: "",
  password: "",
  code: "",
  gender: "",
});
</script>

<style scoped lang="less"></style>
