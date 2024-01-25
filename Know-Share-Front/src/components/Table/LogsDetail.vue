<template>
  <el-descriptions title="" :column="2" border>
    <el-descriptions-item>
      <template #label> ID </template>
      {{ data.id }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 日志等级 </template>
      {{ data.level }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 日志来源 </template>
      {{ data.source }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 时间 </template>
      {{ parseTime(data.date) }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 信息 </template>
      {{ data.message }}
    </el-descriptions-item>
  </el-descriptions>
</template>

<script lang="ts" setup>
import { parseTime } from "@/utils";
const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
</script>
