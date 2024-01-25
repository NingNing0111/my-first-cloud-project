<template>
  <el-main>
    <div class="detail-header">
      <h1>{{ content.title }}</h1>
      <div class="detail-header-overview">
        <el-row align="middle" :gutter="1" style="margin-top: 10px">
          <span style="margin: 10px"> </span>
          <el-col :span="4">
            <el-row align="middle">
              <el-icon>
                <Clock />
              </el-icon>
              <el-text>
                于
                {{
                  new Date(content.publishTime).getFullYear() +
                  "-" +
                  (new Date(content.publishTime).getMonth() + 1 > 10
                    ? new Date(content.publishTime).getMonth() + 1
                    : "0" + (new Date(content.publishTime).getMonth() + 1)) +
                  "-" +
                  (new Date(content.publishTime).getDate() > 10
                    ? new Date(content.publishTime).getDate()
                    : "0" + new Date(content.publishTime).getDate())
                }}
                发布
              </el-text>
            </el-row>
          </el-col>

          <el-col :span="3">
            <el-row align="middle">
              <el-icon>
                <View />
              </el-icon>
              <el-text>阅读量{{ content.view }}</el-text>
            </el-row>
          </el-col>

          <el-col :span="3">
            <el-row align="middle">
              <el-icon>
                <Star />
              </el-icon>
              <el-button link>收藏量{{ content.collect }}</el-button>
            </el-row>
          </el-col>

          <el-col :span="3">
            <el-row align="middle">
              <el-icon>
                <img src="/src/assets/like.svg" />
              </el-icon>
              <el-button link>点赞数{{ content.likes }}</el-button>
            </el-row>
          </el-col>
        </el-row>

        <el-row align="middle" style="margin-top: 10px">
          <el-text style="margin-left: 10px">文章标签：</el-text>
          <el-tag
            v-for="item in content.category"
            :style="'margin-left: 10px;' + 'color:' + randomRgbaColor()"
          >
            {{ item }}
          </el-tag>
        </el-row>
      </div>
    </div>

    <div class="detail-content">
      <div class="detail-main" v-html="content.content"></div>
    </div>
    <div class="detail-comment">
      <el-form label-position="top">
        <el-form-item label="发表评论">
          <el-input
            type="textarea"
            placeholder="欢迎高质量的评论"
            resize="none"
            v-model="commentContent"
          />
        </el-form-item>
        <el-form-item>
          <el-row justify="end" style="width: 100%">
            <el-button show-word-limit type="primary" @click="submit"
              >发表</el-button
            >
          </el-row>
        </el-form-item>
      </el-form>
    </div>
    <div class="detail-comment-content" v-for="item in comments">
      <el-row align="middle" style="margin: 10px; padding-top: 5px">
        <el-col :span="1">
          <el-avatar :src="item.userAvatarUrl"> </el-avatar>
        </el-col>
        <el-col :span="20">
          <div class="comment-info">
            <el-text style="font-size: 14px">{{ item.username }}</el-text>
            <el-button link style="margin-left: 10px; color: blue"
              >回复</el-button
            >
            <el-col>
              <el-text>{{
                new Date(item.time).getFullYear() +
                "-" +
                (new Date(item.time).getMonth() + 1 > 10
                  ? new Date(item.time).getMonth() + 1
                  : "0" + (new Date(item.time).getMonth() + 1)) +
                "-" +
                (new Date(item.time).getDate() > 10
                  ? new Date(item.time).getDate()
                  : "0" + new Date(item.time).getDate())
              }}</el-text>
            </el-col>
          </div>
        </el-col>
      </el-row>
      <el-row align="middle" style="padding-top: 5px">
        <el-col :span="1"> </el-col>
        <div class="comment-content">
          <el-text>{{ item.content }}</el-text>
        </div>
      </el-row>

      <div
        class="sub-comment"
        v-if="item.childComment.length > 0"
        v-for="child in item.childComment"
      >
        <el-row align="middle" style="margin: 10px; padding-top: 5px">
          <el-col :span="1">
            <el-avatar :src="child.userAvatarUrl"> </el-avatar>
          </el-col>
          <el-col :span="20">
            <div class="comment-info">
              <el-text style="font-size: 14px">{{ child.username }}</el-text>
              <el-button link style="margin-left: 10px; color: blue"
                >回复</el-button
              >
              <el-col>
                <el-text>{{
                  new Date(child.time).getFullYear() +
                  "-" +
                  (new Date(child.time).getMonth() + 1 > 10
                    ? new Date(child.time).getMonth() + 1
                    : "0" + (new Date(child.time).getMonth() + 1)) +
                  "-" +
                  (new Date(child.time).getDate() > 10
                    ? new Date(child.time).getDate()
                    : "0" + new Date(child.time).getDate())
                }}</el-text>
              </el-col>
            </div>
          </el-col>
        </el-row>

        <el-row align="middle" style="padding-top: 5px">
          <el-col :span="1"> </el-col>
          <div class="comment-content">
            <el-text>{{ child.content }}</el-text>
          </div>
        </el-row>
      </div>
    </div>
    <div style="min-height: 100px"></div>
  </el-main>
</template>

<script setup lang="ts">
import { CommentsDetail } from "@/common/detail";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
import { randomRgbaColor } from "@/utils/index";
import { toComments } from "@/api/home/detail";

const commentContent = ref("");
const replyContent = ref("");
defineProps({
  content: {
    type: Object as () => KnowledgeIndexContent,
    required: true,
  },
  comments: {
    type: Array as () => CommentsDetail[],
    required: true,
  },
});

const submit = () => {
  toComments(commentContent.value, null);
};
const reply = (parentId: number) => {
  toComments(replyContent.value, parentId);
};
</script>

<style scoped lang="less">
.detail-header {
  height: 100px;
}

.detail-header-overview {
  height: 80px;
  background-color: aliceblue;
}

.detail-content {
  // min-height: 400px;
  margin-top: 50px;
  width: 100%;
}
.detail-main {
  // min-height: 200px;
  border-top: 1px solid black;
  padding-top: 5px;
  border-bottom: 5px solid black;
  margin-bottom: 5px;
}
.detail-comment {
  // min-height: 100px;
  margin-top: 20px;
}

.detail-comment-content {
  // min-height: 100px;
  margin-top: 10px;
  background-color: rgba(239, 243, 243, 0.8);
}
.el-textarea__inner {
  resize: none;
}

.comment-info {
  margin-left: 10px;
}

.comment-content {
  font-size: 16px;
  color: black;
  font-weight: 800;
  border-radius: 5px;
  // background-color: rgba(255, 254, 254, 0.861);
  // border: 1px solid rgba(240, 236, 236, 0.9);
  margin-left: 20px;
}

.sub-comment {
  margin-left: 50px;
  // background-color: blanchedalmond;
}
</style>
