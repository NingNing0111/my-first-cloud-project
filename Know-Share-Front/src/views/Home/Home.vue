<template>
  <div style="height: 100%; overflow: auto">
    <Header :search="search" />

    <el-main>
      <!--  广告部分 -->
      <el-row justify="center">
        <el-col :span="20">
          <el-carousel height="80px">
            <el-carousel-item v-for="item in 4" :key="item">
              <el-image
                :crossorigin="null"
                src="https://img-operation.csdnimg.cn/csdn/silkroad/img/1703210846484.png"
              />
            </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
      <el-row justify="center" style="margin-top: 20px">
        <!-- 页头菜单栏 -->

        <el-col :span="15" :pull="2">
          <el-tabs v-model="currTab" @tab-change="changeTab">
            <el-tab-pane
              v-for="item in tabsList"
              :label="item.label"
              :name="item.name"
            >
            </el-tab-pane>
          </el-tabs>
        </el-col>
        <!-- 内容展示 -->
        <el-col :span="15" v-loading="searchStore.getIsSearch">
          <div
            v-if="searchStore.getContents.length > 0"
            class="home-cards"
            v-for="content in searchStore.getContents"
          >
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
                      <el-button
                        link
                        style="font-size: 20px"
                        @click="toDetail(content.id, content.userId)"
                        >{{ content.title }}</el-button
                      >
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
                <el-row align="bottom" style="margin-top: 5px">
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
                  <el-col :span="10">
                    <el-row justify="end" style="color: rgb(39, 39, 165)">
                      <span>{{
                        new Date(content.publishTime).getFullYear() +
                        "-" +
                        (new Date(content.publishTime).getMonth() + 1 > 10
                          ? new Date(content.publishTime).getMonth() + 1
                          : "0" +
                            (new Date(content.publishTime).getMonth() + 1)) +
                        "-" +
                        (new Date(content.publishTime).getDate() > 10
                          ? new Date(content.publishTime).getDate()
                          : "0" + new Date(content.publishTime).getDate())
                      }}</span>
                    </el-row>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>

          <el-col v-else :span="15">
            <el-row justify="center">
              <h3>未查询到任何知识内容</h3>
            </el-row>
          </el-col>
        </el-col>

        <!-- 右边栏 -->
        <el-col :span="4" :push="1">
          <AsideList
            :views="searchStore.getViewsOfContents"
            :likes="searchStore.getLikesOfContents"
            :toDetail="toDetail"
          />
        </el-col>
      </el-row>
    </el-main>
    <el-footer>
      <el-row justify="center">
        <el-pagination
          :current-page="searchStore.getPage"
          :page-size="searchStore.getSize"
          :total="searchStore.getTotal"
          @current-change="changePage"
          layout="prev, pager, next"
        />
      </el-row>
    </el-footer>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { Tab } from "@/common/knowledgeContent";
import { useSearchStore } from "@/store/home";
import {
  search,
  pageByWeight,
  pageByLikeList,
  pageByViewList,
} from "@/api/home/search";
import { useRouter } from "vue-router";
const router = useRouter();
const searchStore = useSearchStore();
const currTab = ref("default");
const tabsList: Tab[] = [
  {
    label: "默认排序",
    name: "default",
  },
  {
    label: "热门内容",
    name: "view",
  },
  {
    label: "最多收藏",
    name: "collect",
  },

  {
    label: "热门讨论",
    name: "comment",
  },
];
onMounted(() => {
  pageByWeight(currTab.value);
  pageByLikeList();
  pageByViewList();
});

const randomRgbaColor = () => {
  //随机生成RGB颜色
  var r = Math.floor(Math.random() * 256); //随机生成256以内r值
  var g = Math.floor(Math.random() * 256); //随机生成256以内g值
  var b = Math.floor(Math.random() * 256); //随机生成256以内b值
  return `rgb(${r},${g},${b})`; //返回rgba(r,g,b,a)格式颜色
};

const changePage = (e: number) => {
  searchStore.setPage(e);
  pageByWeight(currTab.value);
};

const changeTab = (e: any) => {
  searchStore.setPage(1);
  pageByWeight(e);
};

const toDetail = (kcId: number, userId: number) => {
  router.push({
    name: "detail",
    params: {
      kcId: kcId,
      userId: userId,
    },
  });
};
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
