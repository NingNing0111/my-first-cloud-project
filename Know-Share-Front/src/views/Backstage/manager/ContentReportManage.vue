<template>
  <div class="report-main">
    <el-form>
      <el-row>
        <el-form-item label="是否处理过:">
          <el-select v-model="isHandle" style="width: 100px" size="small">
            <el-option :value="0" label="默认"> </el-option>
            <el-option :value="1" label="已处理"></el-option>
            <el-option :value="2" label="未处理"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item style="margin-left: 30px">
          <el-button type="primary" size="small" @click="updateData"
            >查询</el-button
          >
          <el-button type="warning" size="small" @click="updateData"
            >刷新</el-button
          >
        </el-form-item>
      </el-row>
    </el-form>

    <el-table :data="data" style="min-height: 400px" v-loading="isLoading">
      <el-table-column type="index" :index="indexMethod" />
      <el-table-column prop="id" label="ID" align="center" />
      <el-table-column
        prop="reportTime"
        label="举报时间"
        align="center"
        sortable
      />
      <el-table-column prop="reporterId" label="举报人ID" align="center" />
      <el-table-column prop="kcId" label="知识内容ID" align="center" />
      <el-table-column
        prop="content"
        label="举报理由"
        align="center"
        show-overflow-tooltip
      />
      <el-table-column label="处理状态" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.isHandle" type="success"> 已处理 </el-tag>
          <el-tag v-else type="danger"> 未处理 </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template #default="scope">
          <el-button type="success" size="small" @click="toDetail(scope.row)"
            >查看</el-button
          >
          <el-button
            type="danger"
            size="small"
            v-if="scope.row.isHandle"
            @click="deleteData(scope.row.id)"
            >删除</el-button
          >
          <el-button
            type="primary"
            size="small"
            v-else
            @click="handle(scope.row.id)"
            >处理</el-button
          >
        </template>
      </el-table-column>
    </el-table>

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

    <el-dialog title="举报详情" v-model="isView" width="700px" destroy-on-close>
      <ReportDetail :data="viewData" />
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from "vue";
import {
  getReportData,
  deleteReportData,
  handleReportData,
} from "@/api/manage/report";
import { Report } from "@/common/manage";
const total = ref(0);
const currPage = ref(1);
const currSize = ref(10);
const data = ref<Report[]>([]);
const isHandle = ref(0);
const isView = ref(false);
const viewData = ref();
const isLoading = ref(false);

const indexMethod = (index: number) => {
  return index + 1;
};
const updateData = () => {
  isLoading.value = true;
  getReportData(
    currPage.value,
    currSize.value,
    isHandle.value === 0 ? null : isHandle.value === 1
  )
    .then((res) => {
      console.log(res);
      total.value = res.total;
      data.value = res.data;
    })
    .catch((err) => {
      ElMessage({
        type: "error",
        message: "请求异常",
      });
    })
    .finally(() => {
      isLoading.value = false;
    });
};
const toDetail = (obj: any) => {
  isView.value = true;
  viewData.value = obj;
};
onMounted(() => {
  updateData();
});

const handle = (id: number) => {
  ElMessageBox.confirm("您确定已经处理完了吗？", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      handleReportData(id);
      ElMessage({
        type: "success",
        message: "处理成功",
      });
      updateData();
    })
    .catch(() => {});
};

const handleSizeChange = (e: number) => {
  currSize.value = e;
  updateData();
};

const handleCurrentChange = (e: number) => {
  currPage.value = e;
  updateData();
};
const deleteData = (id: number) => {
  ElMessageBox.confirm("您确定要删除这条记录吗？", "警告", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      deleteReportData(id);
      ElMessage({
        type: "success",
        message: "删除成功",
      });
      updateData();
    })
    .catch(() => {});
};
</script>

<style scoped lang="less"></style>
