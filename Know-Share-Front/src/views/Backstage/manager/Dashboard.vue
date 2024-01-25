<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="10">
        <el-card shadow="hover" class="mgb20">
          <div class="user-info">
            <el-avatar :size="120" :src="userInfo?.avatarUrl" />
            <div class="user-info-cont">
              <div class="user-info-name">{{ userInfo?.username }}</div>
              <div>
                {{ userInfo?.roles.includes("ADMIN") ? "管理员" : "用户" }}
              </div>
            </div>
          </div>
        </el-card>
        <!-- <el-scrollbar>
          <el-card
            shadow="hover"
            class="mgb20"
            style="height: 500px; width: 500px"
          >
            <template #header>
              <el-row justify="center">
                <span>热门搜索</span>
              </el-row>
            </template>
            <div id="hotSearch"></div>
          </el-card>
        </el-scrollbar> -->
      </el-col>

      <el-col :span="14">
        <el-row :gutter="20" class="mgb20">
          <el-col :span="12">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-1">
                <el-icon class="grid-con-icon"><User /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ countOfUser }}</div>
                  <div>平台用户总数</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-2">
                <el-icon class="grid-con-icon"><Notebook /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ countOfKnow }}</div>
                  <div>平台知识内容总数</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="12" style="margin-top: 10px">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-3">
                <el-icon class="grid-con-icon"><View /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">{{ countOfViews }}</div>
                  <div>总访问量</div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="12" style="margin-top: 10px">
            <el-card shadow="hover" :body-style="{ padding: '0px' }">
              <div class="grid-content grid-con-4">
                <el-icon class="grid-con-icon"><Warning /></el-icon>
                <div class="grid-cont-right">
                  <div class="grid-num">
                    {{ (sensitiveOfPercentage * 100).toFixed(1) }}%
                  </div>
                  <div>内容检测敏感率</div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
      <el-card
        shadow="hover"
        style="height: 503px; width: 100%; margin: 10px"
        v-loading="isLodingOutPut"
      >
        <template #header>
          <el-row justify="center">
            <span>知识内容输出额</span>
          </el-row>
          <el-row>
            <el-date-picker
              :default-value="now"
              v-model="now"
              type="year"
              :change="selectYear"
            />
          </el-row>
        </template>
        <div id="knowledge-content-output"></div>
      </el-card>
    </el-row>
  </div>
</template>

<script setup lang="ts" name="dashboard">
import { useUserStore } from "@/store/user";
import { Notebook } from "@element-plus/icons-vue";
import { drawKnowledgeContentOutput, drawHotSearch } from "@/echarts/index";
import {
  countKnow,
  countUser,
  countView,
  sensitivePercentage,
  countKnowByYear,
  countRegisterUserByYear,
} from "@/api/manage/analyze";
const now = ref<Date>(new Date());
const year = ref(now.value.getFullYear());
const isLodingOutPut = ref(false);

const user = useUserStore();
const userInfo = user.getUserInfo;
const sensitiveOfPercentage = ref(0);
const countOfUser = ref(0);
const countOfViews = ref(0);
const countOfKnow = ref(0);
const months = ref<string[]>([]);
const knowData = ref<number[]>([]);
const registerData = ref<number[]>([]);

const selectYear = (e: any) => {
  console.log("选择的年份===>", e);
};

const drawOutPut = () => {
  isLodingOutPut.value = true;
  Promise.all([
    sensitivePercentage(),
    countUser(),
    countView(),
    countKnow(),
    countKnowByYear(year.value),
    countRegisterUserByYear(year.value),
  ])
    .then((res) => {
      (sensitiveOfPercentage.value = res[0]),
        (countOfUser.value = res[1]),
        (countOfViews.value = res[2]),
        (countOfKnow.value = res[3]),
        (months.value = Object.keys(res[4]));
      knowData.value = Object.values(res[4]);
      registerData.value = Object.values(res[5]);

      drawKnowledgeContentOutput(
        "knowledge-content-output",
        months.value,
        knowData.value,
        registerData.value
      );
    })
    .catch((err) => {
      handleError();
    })
    .finally(() => {
      isLodingOutPut.value = false;
    });
};
onMounted(() => {
  drawOutPut();
});

const handleError = () => {
  ElMessage({
    type: "error",
    message: "数据请求异常",
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

.grid-con-4 .grid-con-icon {
  background: rgb(235, 235, 56);
}

.grid-con-4 .grid-num {
  color: rgb(4, 4, 4);
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

#knowledge-content-output {
  width: 100%;
  height: 300px;
  /* background-color: red; */
}

#hotSearch {
  width: 100%;
  height: 400px;
}
</style>
