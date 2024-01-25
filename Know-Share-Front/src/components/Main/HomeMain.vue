<template>
  <el-container>
    <el-main>
      <el-row justify="center">
        <el-col :span="20">
          <el-carousel height="80px">
            <el-carousel-item v-for="item in 4" :key="item">
              <el-image
                :crossorigin="null"
                src="https://img-operation.csdnimg.cn/csdn/silkroad/img/1703210846484.png"
              />
            </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
      <el-row justify="center" style="margin-top: 20px">
        <el-col :span="15" :pull="2">
          <el-tabs v-model="currTab">
            <el-tab-pane
              v-for="item in tabsList"
              :label="item.label"
              :name="item.name"
            >
            </el-tab-pane>
          </el-tabs>
        </el-col>
        <el-col :span="15">
          <HomeCards v-for="item in contentList" :content="item" />
        </el-col>
        <el-col :span="4" :push="1">
          <AsideList />
        </el-col>
      </el-row>
    </el-main>
    <el-footer>
      <el-row justify="center">
        <el-col :span="10">
          <el-pagination
            :current-page="pagination.currPage"
            :page-size="pagination.pageSize"
            :total="pagination.total"
            @size-change="pagination.sizeChange"
            @current-change="pagination.currentChang"
            layout="prev, pager, next"
          />
        </el-col>
      </el-row>
    </el-footer>
  </el-container>
</template>

<script setup lang="ts">
import { ref } from "vue";
import {
  KnowledgeIndexContent,
  Tab,
  Pagination,
} from "@/common/knowledgeContent";
const currTab = ref("PopularContent");
defineProps({
  contentList: {
    type: Array as () => KnowledgeIndexContent[],
    required: true,
  },
  tabsList: {
    type: Array as () => Tab[],
    required: true,
  },
  pagination: {
    type: Object as () => Pagination,
    required: true,
  },
});
</script>

<style scoped lang="less">
.home {
  background-color: aqua;
}
</style>
