<template>
  <el-descriptions title="" :column="2" border>
    <el-descriptions-item>
      <template #label> 用户ID </template>
      {{ data.id }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 用户名 </template>
      {{ data.username }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 邮箱 </template>
      {{ data.email }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 头像 </template>
      <img :src="data.avatarUrl" style="width: 120px" alt="" />
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 性别 </template>
      {{ data.gender ? "男" : "女" }}
    </el-descriptions-item>
    <el-descriptions-item :span="2">
      <template #label> 账户状态 </template>
      {{ data.isBan ? "封禁" : "正常" }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 角色 </template>
      {{ data.roles }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 注册时间 </template>
      {{ data.createTime }}
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
