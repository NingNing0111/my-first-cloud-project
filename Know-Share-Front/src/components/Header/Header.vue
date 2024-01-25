<template>
  <el-header class="index-header">
    <el-row justify="space-between" align="middle">
      <el-col class="logo" :span="1">
        <router-link to="/">
          <el-image
            class="logo-image"
            src="/src/assets/ape-logo.png"
            fit="scale-down"
          />
        </router-link>
      </el-col>

      <el-col :span="12">
        <el-row justify="end" align="middle">
          <el-col :span="18">
            <el-input
              v-model="keyword"
              type="text"
              placeholder="搜索"
              prefix-icon="Search"
            >
              <template #append>
                <el-select v-model="selectValue" style="width: 80px">
                  <el-option
                    v-for="item in selectList"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </template>
            </el-input>
          </el-col>

          <el-col :span="4" style="margin: 10px">
            <el-button type="primary" @click="toSearch">搜索</el-button>
          </el-col>
        </el-row>
      </el-col>

      <el-col :span="6" v-if="user.getUserInfo !== null">
        <el-row justify="space-between" align="middle">
          <el-col :span="3">
            <el-avatar @click="clickAvatar" :src="user.getUserInfo.avatarUrl" />
          </el-col>
          <el-col :span="5" :pull="1">
            <el-button link @click="myhome">个人中心</el-button>
          </el-col>
          <el-col :span="5" :pull="2" v-if="user.getUserRoles.includes('USER')">
            <el-button link @click="myMessage">我的消息</el-button>
          </el-col>
          <el-col :span="8">
            <el-button type="primary" round @click="toPublish">
              发布内容
            </el-button>
          </el-col>
        </el-row>
      </el-col>

      <el-col :span="6" v-else>
        <el-row justify="end">
          <el-col :span="5">
            <router-link to="/login">
              <el-button> 登录 </el-button>
            </router-link>
          </el-col>

          <el-col :span="5">
            <router-link to="/register">
              <el-button type="primary"> 注册 </el-button>
            </router-link>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </el-header>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useUserStore } from "@/store/user";
import router from "@/router";

const props = defineProps({
  search: {
    type: Function,
    required: false,
  },
});

const keyword = ref("");
const selectValue = ref("content");
const selectList = ref([
  {
    label: "内容",
    value: "content",
  },
  {
    label: "标题",
    value: "title",
  },
  {
    label: "概述",
    value: "overview",
  },
  {
    label: "标签",
    value: "category",
  },
]);

const toSearch = () => {
  if (props.search != undefined) {
    props.search(selectValue.value, keyword.value);
  }
};

const toPublish = () => {
  router.push("/home/user/editor");
};

const user = useUserStore();

const myhome = () => {
  if (user.getUserRoles.length > 0 && user.getUserRoles.includes("ADMIN")) {
    router.push("/home/manage");
  } else {
    router.push("/home/user");
  }
};
const clickAvatar = () => {};
const myMessage = () => {
  router.push("/home/user/message");
};
</script>

<style scoped lang="less">
.index-header {
  height: 60px;
  // background-color: rgb(58, 183, 245);
  font-size: 14px;
  margin: 10px;
  border-bottom: 1px solid rgb(195, 191, 191);
  box-shadow: 1px 1px 2px 0px rgb(195, 191, 191, 0.5);
  border-radius: 10px;
}

.logo {
  height: 50px;
  width: 30px;
}

.logo-image {
  height: 100%;
}
</style>
