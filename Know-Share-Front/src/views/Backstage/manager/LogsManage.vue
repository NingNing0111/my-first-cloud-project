<template>
  <div class="logs-header">
    <el-text style="font-size: 16px">第三方工具：</el-text>
    <el-button link type="primary">
      <a :href="AppConfig.Kibana">
        <el-tag size="large"> Kibana </el-tag>
      </a>
    </el-button>

    <el-button link type="primary">
      <a :href="AppConfig.Eureka">
        <el-tag size="large"> Eureka </el-tag>
      </a>
    </el-button>
    <el-button link type="primary">
      <a :href="AppConfig.Git">
        <el-tag size="large"> 项目地址 </el-tag>
      </a>
    </el-button>
    <el-button link type="primary">
      <a href="#">
        <el-tag size="large"> Admin监控 </el-tag>
      </a>
    </el-button>
  </div>
  <el-divider />

  <div style="margin: 5px">
    <div class="table-header">
      <el-button type="danger" @click="toDeleteByBatch">批量删除</el-button>
    </div>
    <el-table
      :data="logsTable"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      v-loading="isLoading"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column fixed prop="id" label="ID" width="100" align="center" />
      <el-table-column prop="level" label="日志等级" align="center" />
      <el-table-column
        prop="source"
        label="来源"
        show-overflow-tooltip
        align="center"
      />
      <el-table-column label="时间" align="center">
        <template #default="scope">
          <div
            style="display: flex; align-items: center; justify-content: center"
          >
            <el-icon><timer /></el-icon>
            <span style="margin-left: 10px">
              {{ parseTime(scope.row.date) }}
            </span>
          </div>
        </template>
      </el-table-column>

      <el-table-column prop="message" label="日志信息" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-row justify="center">
            <el-button type="primary" size="small" @click="toDetail(scope.row)"
              >查看</el-button
            >
            <el-button
              type="danger"
              size="small"
              @click="toDelete(scope.row.id)"
              >删除</el-button
            >
          </el-row>
        </template>
      </el-table-column>
    </el-table>

    <el-row justify="center" style="margin-top: 10px">
      <el-pagination
        v-model:current-page="page"
        v-model:page-size="size"
        :page-sizes="[100, 200, 300, 400]"
        :small="true"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-row>

    <el-dialog
      title="查看日志详情"
      v-model="isView"
      width="700px"
      destroy-on-close
    >
      <LogsDetail :data="viewData" />
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { querySystemLogs, deleteByBatch } from "@/api/manage/logs";
import { Logs } from "@/common/manage";
import { parseTime } from "@/utils";
import { AppConfig } from "@/config/application";
const page = ref(1);
const size = ref(10);
const total = ref(0);
const logsTable = ref<Logs[]>([]);
const targetSelect = ref<number[]>([]);
const viewData = ref();
const isView = ref(false);
const isLoading = ref(false);
onMounted(() => {
  updateDate();
});

const updateDate = () => {
  isLoading.value = true;
  querySystemLogs(page.value, size.value)
    .then((res) => {
      console.log(res);

      logsTable.value = res.content;
      total.value = res.total;
      console.log(logsTable.value);
    })
    .catch((err) => {
      console.log(err);
    })
    .finally(() => {
      isLoading.value = false;
    });
};
const toDeleteByBatch = () => {
  deleteByBatch(targetSelect.value);
  targetSelect.value = [];
  updateDate();
};
const handleSizeChange = (e: any) => {
  size.value = e;
  updateDate();
};
const handleCurrentChange = (e: any) => {
  page.value = e;
  updateDate();
};
const handleSelectionChange = (e: any) => {
  targetSelect.value = [];
  e.forEach((item) => {
    targetSelect.value.push(item.id);
  });
};
const toDelete = (e) => {
  console.log(e);
  deleteByBatch([e]);
  updateDate();
};
const toDetail = (row: any) => {
  isView.value = true;
  viewData.value = row;
};
</script>

<style scoped lang="less">
.logs-header {
  margin: 10px;
  padding: 5px;
}

.table-header {
  margin: 10px;
  display: flex;
  justify-content: end;
}
</style>
