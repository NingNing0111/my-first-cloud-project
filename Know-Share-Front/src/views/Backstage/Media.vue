<template>
  <el-upload class="upload-demo" drag :http-request="upload" multiple>
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">拖拽文件或 <em>点击上传</em></div>
    <template #tip>
      <div class="el-upload__tip" style="text-align: center">
        文件大小限制为128MB
      </div>
    </template>
  </el-upload>

  <div class="medio-main">
    <el-form style="margin: 10px">
      <el-row :gutter="24" justify="end">
        <el-form-item label="资源名称:" style="margin-right: 10px">
          <el-input placeholder="请输入资源名..." />
        </el-form-item>
        <el-form-item label="资源类型:" style="margin-right: 10px">
          <el-select
            v-model="selectType"
            placeholder="请选择"
            style="width: 100px"
          >
            <el-option
              v-for="item in fileTypeList"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item style="margin-right: 10px">
          <el-button type="primary">确定</el-button>
          <el-button type="warning" @click="updateFileData"> 刷新 </el-button>
        </el-form-item>
      </el-row>
    </el-form>
    <el-table :data="fileList" border stripe>
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="name" label="名称" />
      <el-table-column prop="type" label="类型" />
      <el-table-column label="下载地址">
        <template #default="scope">
          <el-text truncated>
            <a :href="scope.row.url">{{ scope.row.url }}</a>
          </el-text>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope" align="center">
          <el-row>
            <el-button
              type="warning"
              size="small"
              @click="copyToClipboard(scope.row.url)"
              >复制</el-button
            >
            <el-button
              type="primary"
              size="small"
              @click="downloadFile(scope.row.url, scope.row.name)"
              >下载</el-button
            >
            <el-button
              type="danger"
              size="small"
              @click="deleteByFileId(scope.row.id)"
              >删除</el-button
            >
          </el-row>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
import { uploadFile, queryFileByUserId, deleteFileById } from "@/api/user/file";
import { useUserStore } from "@/store/user";
import { FileType } from "@/common/user/backobject";

const currUserStore = useUserStore();
const fileList = ref<FileType[]>([]);
const fileTypeList = ref<string[]>([
  "PNG",
  "JPEG",
  "GIF",
  "TIFF",
  "RTF",
  "DOC",
  "XLS",
  "MDB",
  "BMP",
  "DWG",
  "PSD",
  "XML",
  "HTML",
  "PDF",
  "ZIP",
  "RAR",
  "WAV",
  "AVI",
]);
const selectType = ref("PNG");
const updateFileData = () => {
  queryFileByUserId(currUserStore.getUserInfo?.id)
    .then((res) => {
      fileList.value = res;
    })
    .catch((err: any) => {
      ElMessage({
        type: "error",
        message: "服务端异常",
      });
    });
};
onMounted(() => {
  updateFileData();
});

const upload = (e: any) => {
  console.log("上传:====>", e.file);
  uploadFile(e.file, currUserStore.getUserInfo?.id)
    .then((res) => {
      updateFileData();
    })
    .catch((err: any) => {
      ElMessage({
        type: "error",
        message: "上传异常",
      });
    });
};

const downloadFile = (fileUrl: string, fileName: string): void => {
  const link = document.createElement("a");
  link.href = fileUrl;
  link.download = fileName;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
};

const copyToClipboard = (text: string): boolean => {
  const textarea = document.createElement("textarea");
  textarea.value = text;
  document.body.appendChild(textarea);
  textarea.select();
  const result = document.execCommand("copy");
  document.body.removeChild(textarea);
  ElMessage({
    type: "success",
    message: "复制成功",
  });
  return result;
};

const deleteByFileId = (id: number) => {
  ElMessageBox.confirm("确定要删除吗？", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deleteFileById(id);
      updateFileData();
    })
    .catch(() => {});
};
</script>

<style scoped lang="less">
.media-header {
  display: flex;
  justify-content: end;
}

.media-main {
}
</style>
