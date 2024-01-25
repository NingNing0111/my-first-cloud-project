<template>
  <el-row style="height: 200px" justify="start">
    <el-col :span="3">
      <img :src="HotSvg" style="height: 20px; width: 20px" />
    </el-col>
    <el-col :span="12">
      <h3>热门榜</h3>
      <el-col v-for="(item, index) in views" style="margin-top: 5px">
        <el-badge v-if="index < 3" value="热">
          <el-button
            link
            @click="toDetail(item.id, item.userId)"
            class="button-item"
          >
            <el-text truncated style="width: 200px">
              {{ item.title }}
            </el-text>
          </el-button>
        </el-badge>
        <el-button
          v-if="index >= 3 && index < 6"
          link
          class="button-item"
          @click="toDetail(item.id, item.userId)"
        >
          <el-text truncated style="width: 200px">
            {{ item.title }}
          </el-text>
        </el-button>
      </el-col>
    </el-col>
  </el-row>
  <el-row style="height: 200px">
    <el-col :span="3">
      <img :src="GoodStar" style="height: 20px; width: 20px" />
    </el-col>
    <el-col :span="12">
      <h3>好评榜</h3>
      <el-col v-for="(item, index) in likes" style="margin-top: 5px">
        <el-badge :value="index < 3 ? '热' : ''">
          <el-button
            link
            class="button-item"
            @click="toDetail(item.id, item.userId)"
          >
            <el-text truncated style="width: 200px">
              {{ item.title }}
            </el-text>
          </el-button>
        </el-badge>
        <el-button
          v-if="index >= 3 && index < 6"
          link
          class="button-item"
          @click="toDetail(item.id, item.userId)"
        >
          <el-text truncated style="width: 200px">
            {{ item.title }}
          </el-text>
        </el-button>
      </el-col>
    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import HotSvg from "@/assets/hot.svg";
import GoodStar from "@/assets/goodStar.svg";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
import { User } from "@/common/manage";

defineProps({
  views: {
    type: Array as () => KnowledgeIndexContent[],
    requied: true,
  },
  likes: {
    type: Array as () => KnowledgeIndexContent[],
    requied: true,
  },
  author: {
    type: Array as () => User[],
    requied: true,
  },
  toDetail: {
    type: Function,
    required: true,
  },
});
</script>

<style scoped lang="less">
.button-item {
  :hover {
    color: orange;
  }
}
</style>
