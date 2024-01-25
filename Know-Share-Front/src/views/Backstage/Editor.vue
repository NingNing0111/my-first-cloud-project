<template>
  <el-row justify="space-between" :gutter="1">
    <h2>分享你的知识内容</h2>
    <el-col :span="4">
      <el-select v-model="selectEditor">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-col>
  </el-row>

  <el-row style="margin-top: 10px" justify="space-between">
    <el-col :span="17">
      <el-row>
        <el-input
          v-model="editorStore.getContent.kcTitle"
          placeholder="请输入标题"
        />
      </el-row>
      <el-row style="margin-top: 30px; background-color: pink">
        <MarkdownEditor
          :content="editorStore.getContent.content"
          v-if="selectEditor === 'markdown'"
        />
        <TextEditor :content="editorStore.getContent.content" v-else />
      </el-row>
    </el-col>

    <el-col :span="6">
      <el-form
        label-position="top"
        label-width="100px"
        v-model="editorStore.getContent"
      >
        <el-form-item label="标签">
          <el-input
            v-model="tabsValue"
            placeholder="输入标签，可以使用回车隔开"
            @keydown="inputTab"
          />
          <el-row style="margin-top: 4px">
            <el-tag
              closable
              v-for="item in editorStore.getContent.kcCategory"
              @close="handleClose(item)"
              :style="
                'background-color:' +
                randomRgbaColor() +
                '; color: white;margin:2px'
              "
            >
              {{ item }}
            </el-tag>
          </el-row>
        </el-form-item>
        <el-form-item label="特色图片">
          <el-input
            placeholder="输入图片地址"
            v-model="editorStore.getContent.kcImage"
          />
        </el-form-item>
        <el-form-item label="是否发布">
          <el-switch v-model="editorStore.getContent.isPublish" />
        </el-form-item>
        <el-form-item label="内容摘要">
          <el-input
            :rows="3"
            type="textarea"
            v-model="editorStore.getContent.kcOverview"
            placeholder="请输入内容摘要"
          />
        </el-form-item>
        <el-row justify="end" style="margin-top: 30px">
          <el-button>预览</el-button>
          <el-button @click="save">保存</el-button>、
          <el-button type="primary" @click="submit">提交</el-button>
        </el-row>
      </el-form>
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import MarkdownEditor from "./MarkdownEditor.vue";
import TextEditor from "./TextEditor.vue";
import { useEditorStore } from "@/store/editor";
import { publishContent } from "@/api/user/publish";
import { marked } from "marked";
import { randomRgbaColor } from "@/utils/index";

const editorStore = useEditorStore();
const tabsValue = ref("");
const handleClose = (e: string) => {
  editorStore.getContent.kcCategory = editorStore.getContent.kcCategory.filter(
    (item) => item !== e
  );
};
const inputTab = (e: any) => {
  if (e.key === "Enter") {
    editorStore.getContent.kcCategory.push(tabsValue.value);
    tabsValue.value = "";
  }
};
const selectEditor = ref("markdown");
const options = [
  {
    label: "Markdown",
    value: "markdown",
  },
  {
    label: "富文本编辑器",
    value: "text",
  },
];

const submit = () => {
  if (selectEditor.value === "markdown") {
    editorStore.getContent.content = marked(editorStore.getMd) as string;
  } else {
    editorStore.getContent.content = editorStore.getText;
  }
  publishContent();
};

const save = () => {
  localStorage.setItem("kc_editor", JSON.stringify(editorStore.getContent));
  if (selectEditor.value === "markdown") {
    localStorage.setItem("kc_md", editorStore.getContent.content);
  } else {
    localStorage.setItem("kc_text", editorStore.getContent.content);
  }
};
</script>

<style></style>
