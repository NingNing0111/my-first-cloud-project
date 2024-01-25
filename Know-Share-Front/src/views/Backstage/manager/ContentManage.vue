<template>
  <div>
    <el-form v-model="queryParam" style="margin-top: 10px">
      <el-row align="middle">
        <el-form-item label="标题:" style="margin-left: 25px">
          <el-input placeholder="请输入关键字..." v-model="queryParam.title" />
        </el-form-item>
        <el-form-item label="概述:" style="margin-left: 25px">
          <el-input
            placeholder="请输入关键字..."
            v-model="queryParam.overview"
          />
        </el-form-item>

        <el-form-item label="用户ID" style="margin-left: 25px">
          <el-input
            type="number"
            v-model="queryParam.userId"
            placeholder="请输入用户ID"
          />
        </el-form-item>

        <el-form-item style="margin-left: 25px">
          <el-button type="primary" @click="search">搜索</el-button>
          <el-button type="warning" @click="search">刷新</el-button>
        </el-form-item>
      </el-row>
    </el-form>

    <div style="margin: 10px; min-height: 400px" v-loading="isLoading">
      <el-table :data="knowData">
        <el-table-column type="index" :index="indexMethod" />
        <el-table-column label="发表时间" align="center" width="120" sortable>
          <template #default="scope">
            {{ parseTime(scope.row.publishTime) }}
          </template>
        </el-table-column>
        <el-table-column
          prop="id"
          label="内容ID"
          width="100"
          align="center"
          sortable
        />
        <el-table-column
          prop="userId"
          label="用户ID"
          width="100"
          align="center"
          sortable
        />
        <el-table-column label="标题" align="center" show-overflow-tooltip>
          <template #default="scope">
            <el-text truncated>
              {{ scope.row.title }}
            </el-text>
          </template>
        </el-table-column>
        <el-table-column
          prop="overview"
          label="概述"
          show-overflow-tooltip
          align="center"
        >
          <template #default="scope">
            <el-text truncated>
              {{ scope.row.overview }}
            </el-text>
          </template>
        </el-table-column>

        <el-table-column label="敏感状态" width="120" align="center">
          <template #default="scope">
            <el-tag v-if="scope.row.isSensitive" type="danger"
              >存在敏感词</el-tag
            >
            <el-tag v-else type="success">正常</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="显示状态" width="80">
          <template #default="scope">
            <el-tag v-if="scope.row.isShow" type="success">正常 </el-tag>
            <el-tag v-else type="danger">封禁</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              @click="toDetail(scope.row.id, scope.row.userId)"
              >查看</el-button
            >
            <el-button
              v-if="scope.row.isShow"
              size="small"
              type="danger"
              @click="banContent(scope.row.id)"
              >封禁</el-button
            >
            <el-button
              v-else
              size="small"
              type="warning"
              @click="banContent(scope.row.id)"
              >解封</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div style="margin-top: 10px">
      <el-row justify="center">
        <el-pagination
          v-model:current-page="currPage"
          v-model:page-size="currSize"
          :page-sizes="[10, 50, 100, 200]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </el-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
import {
  KnowSearchParam,
  KnowledgeIndexContent,
  KnowManageContent,
} from "@/common/knowledgeContent";
import { searchByParam, changeShow } from "@/api/manage/know";
import { parseTime } from "@/utils";
import { toDetail } from "@/api/home/detail";

const currPage = ref(1);
const currSize = ref(10);
const total = ref(0);
const defaultSearchParam: KnowSearchParam = {
  title: null,
  overview: null,
  userId: null,
};
const indexMethod = (index: number): number => {
  return index + 1;
};
const queryParam = ref<KnowSearchParam>(defaultSearchParam);
const knowData = ref<KnowManageContent[]>([]);
const isLoading = ref(false);

const search = () => {
  isLoading.value = true;
  knowData.value = [];
  searchByParam(currPage.value, currSize.value, queryParam.value)
    .then((res) => {
      console.log("====>", res);
      knowData.value = res.data;
      total.value = res.total;
    })
    .catch((err) => {
      console.log(err);
    })
    .finally(() => {
      isLoading.value = false;
    });
};

onMounted(() => {
  search();
});

const banContent = (id: number) => {
  ElMessageBox.confirm("你的操作将改变显示状态，确定这样做吗？", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      changeShow(id);
      ElMessage({
        type: "success",
        message: "修改成功",
      });
      search();
    })
    .catch(() => {});
};
</script>

<style scoped lang="less"></style>
