<template>
  <el-form :model="form" label-width="100px" label-position="top">
    <el-form-item label="标题">
      <el-input v-model="form.title" />
    </el-form-item>
    <el-form-item label="描述">
      <el-input v-model="form.description" type="textarea" />
    </el-form-item>
    <el-form-item label="图片地址">
      <el-input v-model="form.imageUrl" />
    </el-form-item>
    <el-form-item label="跳转地址">
      <el-input v-model="form.targetUrl" />
    </el-form-item>
    <el-form-item label="是否展示">
      <el-switch v-model="form.isShow" />
    </el-form-item>
  </el-form>
  <el-row justify="end">
    <el-button type="primary" @click="submit">{{
      props.isEdit ? "提交" : "添加"
    }}</el-button>
  </el-row>
</template>

<script lang="ts" setup>
import { Advertise } from "@/common/manage";

const props = defineProps({
  data: {
    type: Object as () => Advertise,
    required: true,
  },
  update: {
    type: Function,
    required: true,
  },
  isEdit: {
    type: Boolean,
    required: true,
  },
  add: {
    type: Function,
    required: true,
  },
});
const defaultData: Advertise = {
  title: "",
  description: "",
  imageUrl: "",
  targetUrl: "",
  startDate: "",
  endDate: "",
  isShow: false,
  id: null,
  createAt: null,
};

const form = ref<Advertise>(props.isEdit ? props.data : defaultData);

onMounted(() => {
  console.log(form);
});

const submit = () => {
  if (props.isEdit) {
    props.update(form.value);
  } else {
    props.add(form.value);
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
