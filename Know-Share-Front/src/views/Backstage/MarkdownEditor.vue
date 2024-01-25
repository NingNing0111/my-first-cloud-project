<template>
  <md-editor
    style="width: 100%"
    v-model="editorStore.contentMd"
    @on-upload-img="onUploadImg"
  />
</template>

<script setup lang="ts" name="md">
import { MdEditor } from "md-editor-v3";
import "md-editor-v3/lib/style.css";
import { useEditorStore } from "@/store/editor";
const editorStore = useEditorStore();

const onUploadImg = (files: any) => {
  console.log(files);
};
</script>
