<template>
  <RouterView :key="key" />
</template>

<script setup lang="ts">
import router from "@/router";
const key = computed(() => {
  return router.currentRoute.value.path;
});
</script>

<style>
@import "@/assets/css/main.css";
@import "@/assets/css/color-dark.scss";
</style>
