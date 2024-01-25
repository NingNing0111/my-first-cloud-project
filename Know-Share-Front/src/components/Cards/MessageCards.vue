<template>
  <div class="message-item">
    <el-row align="middle">
      <el-col :span="4">
        <el-row justify="center">
          <el-avatar :src="message.avatarUrl" shape="square" size="large" />
        </el-row>
      </el-col>
      <el-col :span="14" :pull="1">
        <el-row>
          <el-col>
            <h3>{{ message.title }}</h3>
          </el-col>
          <el-col style="margin-top: 5px">
            <el-text :line-clamp="2" truncated>
              {{ message.content }}
            </el-text>
          </el-col>
        </el-row>
        <el-row align="bottom" style="margin: auto">
          <el-col>
            <h5 style="color: rgb(39, 107, 211)">{{ message.time }}</h5>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="4">
        <el-row justify="end">
          <el-col :span="1">
            <el-button type="primary">查看</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { MessageContent } from "@/common/user/backobject";
defineProps({
  message: {
    type: Object as () => MessageContent,
    required: true,
  },
});
</script>

<style scoped lang="less">
.message-item {
  width: 90%;
  height: 100px;
  //   background-color: pink;
  margin: 10px;
  box-shadow: 1px 1px 3px 1px rgb(115, 110, 110, 0.5);
  border-radius: 10px;
}

.message-image {
  width: 100px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-content: center;
}
</style>
