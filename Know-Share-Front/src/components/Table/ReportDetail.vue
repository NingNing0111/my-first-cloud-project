<template>
  <el-descriptions :column="2" border>
    <el-descriptions-item>
      <template #label> ID </template>
      {{ data.id }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 知识内容ID </template>
      {{ data.kcId }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 举报人ID </template>
      {{ data.reporterId }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label> 举报内容 </template>
      {{ data.content }}
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label> 举报时间 </template>
      <sapn style="color: blue">
        {{ data.reportTime }}
      </sapn>
    </el-descriptions-item>

    <el-descriptions-item>
      <template #label> 是否已处理 </template>
      <el-tag v-if="data.isHandle" type="success"> 已处理 </el-tag>
      <el-tag v-else type="danger">未处理</el-tag>
    </el-descriptions-item>
  </el-descriptions>
</template>

<script lang="ts" setup>
defineProps({
  data: {
    type: Object,
    required: true,
  },
});
</script>
