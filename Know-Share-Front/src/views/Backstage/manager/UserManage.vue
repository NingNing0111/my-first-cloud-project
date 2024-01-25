<template>
  <div class="container">
    <div class="search-box">
      <el-input
        v-model="userManage.searchname"
        placeholder="用户名"
        class="search-input mr10"
        clearable
      ></el-input>
      <el-button type="primary" icon="Search" @click="handleSearch"
        >搜索</el-button
      >
    </div>
    <el-table
      show-overflow-tooltip
      :data="userManage.getUsers"
      border
      class="table"
      ref="multipleTable"
      v-loading="userManage.getUsers.length === 0"
    >
      <el-table-column
        prop="id"
        label="ID"
        width="55"
        align="center"
      ></el-table-column>

      <el-table-column prop="username" label="用户名" align="center">
      </el-table-column>

      <el-table-column label="邮箱" align="center" prop="email">
      </el-table-column>
      <el-table-column label="头像" align="center">
        <template #default="scope">
          <el-image
            class="table-td-thumb"
            :src="scope.row.avatarUrl"
            :crossorigin="null"
            :z-index="10"
            :preview-src-list="[scope.row.avatarUrl]"
            preview-teleported
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="gender" label="性别" align="center">
        <template #default="scope">
          <span>{{ scope.row.gender === "man" ? "男" : "女" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账户状态" align="center">
        <template #default="scope">
          <el-tag :type="!scope.row.isBan ? 'success' : 'danger'">
            {{ scope.row.isBan ? "封禁" : "正常" }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="角色">
        <template #default="scope">
          <el-tag
            :type="scope.row.roles.includes('ADMIN') ? 'danger' : 'success'"
          >
            {{ scope.row.roles.includes("ADMIN") ? "管理员" : "用户" }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        prop="createTime"
        label="注册时间"
        align="center"
      ></el-table-column>

      <el-table-column label="操作" width="280" align="center">
        <template #default="scope">
          <el-button
            type="primary"
            size="small"
            icon="View"
            @click="handleView(scope.row)"
          >
            查看
          </el-button>
          <el-button
            v-if="scope.row.isBan"
            type="success"
            size="small"
            icon="Unlock"
            @click="banUser(scope.row.id)"
          >
            解封
          </el-button>
          <el-button
            v-else
            type="warning"
            size="small"
            icon="Lock"
            @click="banUser(scope.row.id)"
          >
            封禁
          </el-button>

          <el-button
            type="danger"
            size="small"
            icon="Delete"
            @click="deleteUser(scope.row.id)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination">
      <el-pagination
        background
        layout="total, prev, pager, next"
        :current-page="userManage.getUsersPage"
        :page-size="userManage.getUsersSize"
        :total="userManage.getUsersTotal"
        @current-change="handlePageChange"
      ></el-pagination>
    </div>
  </div>
  <el-dialog
    title="查看用户详情"
    v-model="isView"
    width="700px"
    destroy-on-close
  >
    <UserTableDetail :data="viewData" />
  </el-dialog>
</template>

<script setup lang="ts" name="basetable">
import { useManageStore } from "@/store/manage";
import { queryUserByPage, banUser, deleteUser } from "@/api/manage/user";
const userManage = useManageStore();
const isView = ref(false);
const viewData = ref();
onMounted(() => {
  queryUserByPage();
});
const handlePageChange = (e: number) => {
  userManage.setUsers([]);
  userManage.setUsersPage(e);
  queryUserByPage();
};
const handleSearch = () => {};
const handleView = (row: any) => {
  isView.value = true;
  viewData.value = row;
};
</script>
<style scoped>
.search-box {
  margin-bottom: 20px;
}

.search-input {
  width: 200px;
}

.mr10 {
  margin-right: 10px;
}
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
</style>
