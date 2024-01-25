<template>
  <el-main>
    <el-row>
      <h1>消息中心</h1>
    </el-row>
    <el-row justify="center">
      <el-tabs v-model="currTab" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane
          v-for="item in tabs"
          :label="item.label"
          :name="item.name"
        />
      </el-tabs>
    </el-row>
    <el-row justify="center" style="margin-top: 20px">
      <MessageCards :message="messageContent" />
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
import { ref, reactive, computed } from "vue";
import { Pagination, Tab } from "@/common/knowledgeContent";
import { MessageContent } from "@/common/user/backobject";
const messageContent = ref<MessageContent>({
  avatarUrl: "https://pgthinker.me/wp-content/uploads/2024/01/1725396762.png",
  title: "来自xxx的消息",
  content: "这是消息内容",
  time: new Date().toDateString(),
});
const currTab = ref("timeAsc");
const tabs = ref<Tab[]>([
  {
    label: "评论消息",
    name: "timeAsc",
  },
  {
    label: "赞和收藏",
    name: "timeDesc",
  },
  {
    label: "系统消息",
    name: "commentValue",
  },
]);
const handleClick = (c: any, e: any) => {
  console.log(c.props.name);
};
const pagination = ref<Pagination>({
  currPage: 1,
  pageSize: 10,
  total: 100,
  sizeChange: () => {},
  currentChang: () => {},
});
</script>

<style scoped lang="less"></style>
