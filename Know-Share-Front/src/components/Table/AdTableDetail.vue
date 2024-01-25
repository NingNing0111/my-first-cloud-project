<template>
  <el-descriptions :column="2" border>
    <el-descriptions-item>
      <template #label> 广告ID </template>
      {{ data.id }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 标题 </template>
      {{ data.title }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 描述 </template>
      {{ data.description }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 广告图片 </template>
      <img :src="data.imageUrl" style="width: 120px" alt="" />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 图片跳转链接 </template>
      <el-button link type="primary">
        {{ data.targetUrl }}
      </el-button>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 展示状态 </template>
      {{ data.isShow ? "展示" : "不展示" }}
    </el-descriptions-item>
  </el-descriptions>
</template>

<script lang="ts" setup>
const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
</script>
