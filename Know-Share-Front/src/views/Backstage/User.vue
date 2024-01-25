<template>
  <div>
    <el-row :gutter="20" v-loading="isLoading">
      <el-col :span="8">
        <el-card shadow="hover" class="mgb20">
          <div class="user-info">
            <el-avatar :size="120" :src="user.getUserInfo?.avatarUrl" />
            <div class="user-info-cont">
              <div class="user-info-name">{{ user.getUserInfo?.username }}</div>
              <div>{{ role }}</div>
            </div>
          </div>
          <el-descriptions title="我的信息" border :column="1">
            <el-descriptions-item label="昵称">{{
              user.getUserInfo?.username
            }}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{
              user.getUserInfo?.email
            }}</el-descriptions-item>
            <el-descriptions-item label="性别">
              {{ user.getUserInfo?.gender === "man" ? "男" : "女" }}
            </el-descriptions-item>
            <el-descriptions-item label="注册时间">
              {{ user.getUserInfo?.createTime }}
            </el-descriptions-item>
          </el-descriptions>

          <!-- <div style="display: flex; justify-content: end; margin-top: 10px">
            <el-button type="primary" size="small">编辑</el-button>
          </div> -->
        </el-card>
        <el-card shadow="hover">
          <template #header>
            <div class="clearfix">
              <span>知识领域</span>
            </div>
          </template>
          <div v-for="(key, item) in userAnalyze.getCategoryPercentage">
            {{ item }}
            <el-progress
              :percentage="key"
              :color="randomRgbaColor"
            ></el-progress>
          </div>
        </el-card>
      </el-col>
      <el-col :span="16">
        <el-row :gutter="20" class="mgb20">
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-1">
                <el-icon class="grid-con-icon"><User /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">
                    {{ userAnalyze.getCountInfo.viewCount }}
                  </div>
                  <div>知识内容阅读量</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-2">
                <el-icon class="grid-con-icon"><ChatDotRound /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">
                    {{ userAnalyze.getCountInfo.likeCount }}
                  </div>
                  <div>内容点赞总数</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-3">
                <el-icon class="grid-con-icon"><Goods /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">
                    {{ userAnalyze.getCountInfo.knowCount }}
                  </div>
                  <div>知识内容数量</div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>

        <el-card shadow="hover">
          <template #header>
            <div class="clearfix">
              <el-row justify="center">
                <span>我发表的知识内容</span>
              </el-row>
            </div>
          </template>
          <div style="min-height: 400px">
            <el-row
              v-for="item in userAnalyze.getUserContentList"
              style="border-bottom: 1px solid black"
            >
              <el-col :span="18" style="color: cornflowerblue">
                {{
                  new Date(item.publishTime).getFullYear() +
                  "-" +
                  (new Date(item.publishTime).getMonth() + 1 > 10
                    ? new Date(item.publishTime).getMonth() + 1
                    : "0" + (new Date(item.publishTime).getMonth() + 1)) +
                  "-" +
                  (new Date(item.publishTime).getDate() > 10
                    ? new Date(item.publishTime).getDate()
                    : "0" + new Date(item.publishTime).getDate())
                }}
                &emsp;
                {{ item.title }}
              </el-col>
              <el-col :span="4" :push="1" style="margin-bottom: 10px">
                <el-button
                  type="primary"
                  size="small"
                  @click="toDetail(item.id, user.getUserInfo?.id)"
                  >查看</el-button
                >
                <el-button size="small">编辑</el-button>
              </el-col>
            </el-row>
          </div>

          <template #footer>
            <el-row justify="center">
              <el-pagination
                layout="prev, pager, next"
                :total="userAnalyze.getTotal"
              />
            </el-row>
          </template>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts" name="dashboard">
import { useUserStore } from "@/store/user";
import {
  dominPercentage,
  userCount,
  queryPageByUserId,
  queryCollectPageByUserId,
} from "@/api/user/anlyze";
import { useUserAnalyze } from "@/store/userAnalyze";
import { randomRgbaColor } from "@/utils";
import { useRouter } from "vue-router";
const router = useRouter();
const user = useUserStore();
const userAnalyze = useUserAnalyze();
const role: string = user.getUserRoles.includes("ADMIN")
  ? "管理员"
  : "普通用户";
const isLoading = ref(false);
onMounted(() => {
  isLoading.value = true;
  Promise.all([
    dominPercentage(),
    userCount(),
    queryPageByUserId(),
    queryCollectPageByUserId(),
  ])
    .then((res) => {})
    .catch((err) => {})
    .finally(() => {
      isLoading.value = false;
    });
});

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

<style scoped>
.el-row {
  margin-bottom: 20px;
}

.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}

.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
  color: rgb(100, 213, 114);
}

.grid-con-3 .grid-con-icon {
  background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
  color: rgb(242, 94, 67);
}

.user-info {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 2px solid #ccc;
  margin-bottom: 20px;
}

.user-info-cont {
  padding-left: 50px;
  flex: 1;
  font-size: 14px;
  color: #999;
}

.user-info-cont div:first-child {
  font-size: 14px;
  color: #222;
}

.user-info-list {
  font-size: 14px;
  color: #999;
  line-height: 25px;
}

.user-info-list span {
  margin-left: 70px;
}

.mgb20 {
  margin-bottom: 20px;
}

.todo-item {
  font-size: 14px;
}

.todo-item-del {
  text-decoration: line-through;
  color: #999;
}

.schart {
  width: 100%;
  height: 300px;
}
</style>
