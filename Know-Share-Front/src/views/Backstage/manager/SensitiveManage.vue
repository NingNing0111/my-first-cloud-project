<template>
  <div class="sensitive-main">
    <div class="sensitive-header">
      <el-input placeholder="请输入敏感词段..." v-model="sensitiveWord" />
      <el-button
        type="primary"
        style="margin-left: 10px"
        size="small"
        @click="addWord"
        >添加</el-button
      >
      <el-button type="danger" size="small" @click="deleteWord">删除</el-button>
      <el-button type="warning" size="small" @click="updateWord"
        >刷新</el-button
      >
    </div>
    <el-divider />
    <el-row justify="space-between" style="margin: 10px">
      <h2>文本在线检测</h2>
      <div>
        <el-button type="warning" @click="reset">重置</el-button>
        <el-button type="primary" @click="check">检测</el-button>
      </div>
    </el-row>
    <div class="sensitive-check">
      <el-input
        type="textarea"
        :rows="20"
        resize="none"
        style="margin: 10px"
        v-model="text"
      />
      <div
        :style="{
          width: '100%',
          margin: '10px',
          border: isSensitive ? '2px solid red' : 'none',
        }"
      >
        <el-input
          type="textarea"
          :rows="20"
          resize="none"
          disabled
          v-model="resultText"
        />
      </div>
    </div>
    <el-row justify="end" align="middle"> </el-row>

    <el-text style="margin-left: 10px">检测结果：</el-text>
    <el-text v-if="isSensitive" style="font-size: 18px; color: red"
      >存在敏感词</el-text
    >
    <el-text v-else style="font-size: 18px">不存在敏感词</el-text>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import {
  addSensitiveWord,
  deleteSensitiveWord,
  checkSensitiveWord,
  updateSensitiveWord,
} from "@/api/manage/sensitive";
const sensitiveWord = ref<string>("");
const text = ref("");
const resultText = ref("");
const isSensitive = ref(false);

// 添加
const addWord = () => {
  addSensitiveWord(sensitiveWord.value);
  sensitiveWord.value = "";
  ElMessage({
    type: "success",
    message: "添加成功",
  });
};

// 删除
const deleteWord = () => {
  ElMessageBox.confirm("确定要删除该字段吗？", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deleteSensitiveWord(sensitiveWord.value);
      sensitiveWord.value = "";
      ElMessage({
        type: "success",
        message: "修改成功",
      });
    })
    .catch(() => {});
};

// 更新
const updateWord = () => {
  updateSensitiveWord();
};

// 检测
const check = () => {
  checkSensitiveWord(text.value)
    .then((res) => {
      isSensitive.value = res.isSensitive;
      resultText.value = res.statement;
    })
    .catch((err) => {
      console.log(err);
    });
};

// 重置
const reset = () => {
  sensitiveWord.value = "";
  text.value = "";
  resultText.value = "";
  isSensitive.value = false;
};
</script>

<style scoped lang="less">
.sensitive-header {
  display: flex;
  justify-content: space-between;
  margin: 10px;
  align-items: center;
}

.sensitive-check {
  display: flex;
  justify-content: space-between;
}
</style>
