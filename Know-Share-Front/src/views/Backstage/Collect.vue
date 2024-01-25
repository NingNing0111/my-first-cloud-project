<template>
  <el-main>
    <el-row justify="center">
      <div>
        <HomeCards
          v-for="item in currUser.getCollectList"
          :content="item"
          style="width: 100%"
          :is-show-info="false"
        />
      </div>
      <el-pagination
        :current-page="pagination.currPage"
        :page-size="pagination.pageSize"
        :total="pagination.total"
        @size-change="pagination.sizeChange"
        @current-change="pagination.currentChang"
        layout="prev, pager, next"
      />
    </el-row>
  </el-main>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { Pagination } from "@/common/knowledgeContent";
import { queryCollectPageByUserId } from "@/api/user/anlyze";
import { useUserStore } from "@/store/user";
const currUser = useUserStore();
const pagination = ref<Pagination>({
  currPage: 1,
  pageSize: 10,
  total: 100,
  sizeChange: () => {},
  currentChang: () => {},
});

onMounted(() => {
  queryCollectPageByUserId();
});
</script>

<style scoped lang="less"></style>
