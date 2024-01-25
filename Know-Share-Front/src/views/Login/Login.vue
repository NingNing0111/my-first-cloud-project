<template>
  <Header />
  <LoginForm :login-param="loginParam" @login="toLogin" v-loading="isLoading" />
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
import { LoginParam } from "@/common/user";
import { login } from "@/api/user/uaa";
const isLoading = ref(false);
const toLogin = () => {
  isLoading.value = false;
  login(loginParam.value)
    .then((res) => {})
    .catch((err) => {})
    .finally(() => {
      isLoading.value = false;
    });
};
const loginParam = ref<LoginParam>({
  email: "",
  password: "",
});
</script>

<style scoped lang="less"></style>
