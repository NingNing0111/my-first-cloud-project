<template>
  <el-aside class="detail-aside-box">
    <div class="user-box">
      <el-row align="middle" justify="center" class="user-header">
        <el-col :span="6">
          <el-avatar
            :src="user.avatarUrl"
            style="width: 100%; height: 100%"
            size="large"
          />
        </el-col>
        <el-col :span="12" :push="1">
          <el-text truncated style="font-size: 14px; color: black">
            {{ user.username }}
            <el-icon style="color: blue; font-weight: 800; font-size: 18px">
              <Male v-if="user.gender === 'man'" />
              <Female v-else />
            </el-icon>
          </el-text>
        </el-col>
      </el-row>
      <el-divider>
        <el-icon><star-filled /></el-icon>
      </el-divider>
      <el-row
        align="middle"
        justify="space-between"
        :gutter="2"
        style="margin: 6px"
      >
        <el-col :span="5">
          <el-row justify="center">
            <el-text class="number-item">{{ user.contentCount }}</el-text>
            <el-text> 内容数 </el-text>
          </el-row>
        </el-col>
        <el-col :span="5">
          <el-row justify="center">
            <el-text class="number-item"> {{ user.likesCount }}</el-text>
            <el-text>获赞数</el-text>
          </el-row>
        </el-col>
        <el-col :span="5">
          <el-row justify="center">
            <el-text class="number-item"> {{ user.collectCount }} </el-text>
            <el-text> 收藏数 </el-text>
          </el-row>
        </el-col>
        <el-col :span="5">
          <el-row justify="center">
            <el-text class="number-item"> {{ user.viewCount }} </el-text>
            <el-text> 浏览量 </el-text>
          </el-row>
        </el-col>
      </el-row>

      <el-row justify="space-between" style="margin-top: 40px">
        <el-col :span="4" :push="1">
          <el-button text @click="like">
            <el-icon>
              <img :src="Like" />
            </el-icon>
            <el-text> 点赞 </el-text>
          </el-button>
        </el-col>
        <el-col :span="4" :pull="3">
          <el-button type="primary" text @click="collect">
            <el-icon>
              <img :src="Star" />
            </el-icon>
            <el-text> 收藏 </el-text>
          </el-button>
        </el-col>
        <el-col :span="4" :pull="3">
          <el-button type="error">举报</el-button>
        </el-col>
      </el-row>
    </div>

    <div class="content-table">
      <el-row justify="center">
        <h1>目录</h1>
        <el-col>
          <el-scrollbar height="280px">
            <div style="margin-top: 5px; margin-left: 5px">
              <ul>
                <template v-for="(item, index) in titleList">
                  <li
                    :style="{
                      paddingLeft: item.level * 22 - 44 + 'px',
                      color: 'blue',
                    }"
                  >
                    {{ index + 1 }}. {{ item.text }}
                  </li>
                </template>
              </ul>
            </div>
          </el-scrollbar>
        </el-col>
      </el-row>
    </div>
  </el-aside>
</template>

<script setup lang="ts">
import { UserDetail } from "@/common/user";
import Like from "@/assets/like.svg";
import Star from "@/assets/star.svg";
import { TOCEntry } from "@/utils";
const props = defineProps({
  titleList: {
    type: Array as () => TOCEntry[],
    default: [],
  },
  user: {
    type: Object as () => UserDetail,
    required: true,
  },
  like: {
    type: Function,
    required: true,
  },
  collect: {
    type: Function,
    required: true,
  },
});
//
</script>

<style scoped lang="less">
.detail-aside-box {
  margin: 10px;
  width: 300px;
  height: 100%;
}

.user-header {
  height: 100px;
}

.user-box {
  height: 300px;
  box-shadow: 0px 1px 3px 2px rgba(98, 92, 92, 0.9);
  //   background-color: pink;
  border-radius: 15px;
  margin: 10px;
}

.content-table {
  // background-color: pink;
  height: 350px;
  box-shadow: 0px 1px 3px 2px rgba(98, 92, 92, 0.9);
  border-radius: 15px;
  margin: 10px;
  margin-top: 20px;
}

.number-item {
  font-size: 16px;
  font-weight: 900;
  color: black;
  padding: auto;
  width: 50px;
  text-align: center;
}
</style>
