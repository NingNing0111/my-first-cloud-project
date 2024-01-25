<template>
  <Header :search="search" />
  <el-container
    style="height: 100%; overflow: hidden"
    v-loading="detailStore.getIsLoding"
  >
    <AsideDetail
      :user="detailStore.getCurrUser"
      :titleList="detailStore.getTitleList"
      :like="like"
      :collect="collect"
    />
    <MainDetail
      :content="detailStore.getCurrContent"
      :comments="detailStore.getComments"
    />
  </el-container>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router";
import { searchById } from "@/api/home/search";
import { useDetailStore } from "@/store/detail";
import {
  getUserAllCounts,
  queryCommentsByKcId,
  toLike,
  toCollect,
} from "@/api/home/detail";
import "@/assets/css/detail.css";
const detailStore = useDetailStore();
const router = useRoute();
const like = () => {
  toLike(detailStore.getCurrContent.id);
};
const collect = () => {
  toCollect(detailStore.getCurrContent.id);
};
onMounted(() => {
  let kcIdStr = router.params.kcId as string;
  let userIdStr = router.params.userId as string;
  let kcId = parseInt(kcIdStr, 10);
  let userId = parseInt(userIdStr, 10);

  searchById(kcId);
  getUserAllCounts(userId);
  queryCommentsByKcId(kcId);
});
const search = () => {};
</script>

<style scoped lang="less"></style>
