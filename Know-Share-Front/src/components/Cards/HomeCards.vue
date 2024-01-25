<template>
  <div class="home-cards">
    <el-row justify="center" :gutter="2">
      <el-col :span="4">
        <el-row align="middle" justify="center" style="height: 100px">
          <el-col :span="20">
            <el-image :src="content.image" :crossorigin="null" />
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="20">
        <el-row style="height: 100px; margin-right: 5px">
          <el-col style="height: 30px">
            <el-text truncated size="large">
              <el-button link style="font-size: 20px">{{
                content.title
              }}</el-button>
            </el-text>
          </el-col>
          <el-col style="height: 40px; margin-top: 5px">
            <el-text line-clamp="2">{{ content.overview }}</el-text>
          </el-col>
          <el-col style="height: 30px; margin-top: 5px">
            <el-text>标签：</el-text>
            <el-tag
              v-for="item in content.category"
              :style="{
                backgroundColor: randomRgbaColor(),
                color: 'white',
                'margin-left': '4' + 'px',
              }"
            >
              {{ item }}
            </el-tag>
          </el-col>
        </el-row>
        <el-row align="bottom" style="margin-top: 5px" v-if="isShowInfo">
          <el-col :span="3">
            <el-button link>
              <el-icon>
                <View />
              </el-icon>
              <el-text>{{ content.view }} 阅读量</el-text>
            </el-button>
          </el-col>

          <el-col :span="3">
            <el-button link>
              <el-icon>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="12"
                  height="12"
                  fill="currentColor"
                  class="bi bi-heart"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"
                  />
                </svg>
              </el-icon>
              <el-text>{{ content.likes }} 人点赞</el-text>
            </el-button>
          </el-col>

          <el-col :span="3">
            <el-button link>
              <el-icon>
                <Comment />
              </el-icon>
              <el-text>{{ content.comment }} 条评论</el-text>
            </el-button>
          </el-col>

          <el-col :span="4">
            <el-button link>
              <el-icon>
                <Star />
              </el-icon>
              <el-text> {{ content.collect }} 人收藏</el-text>
            </el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
import { randomRgbaColor } from "@/utils/index";
defineProps({
  content: {
    type: Object as () => KnowledgeIndexContent,
    required: true,
  },
  isShowInfo: {
    type: Boolean,
    required: false,
    default: true,
  },
});
</script>

<style scoped lang="less">
.home-cards {
  height: 130px;
  border-radius: 10px;
  border: 1px solid rgb(202, 199, 199);
  box-shadow: 0px 1px 3px 0px rgb(202, 199, 199, 0.9);
  margin-bottom: 20px;
}
</style>
