<template>
  <div>
    <div class="container">
      <el-row>
        <el-button
          type="primary"
          style="width: 100%"
          round
          :icon="Plus"
          @click="handleAdd"
        >
          添加广告
        </el-button>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-table
          :data="advertiseManage.getAdvertises"
          border
          class="table"
          ref="multipleTable"
          header-cell-class-name="table-header"
          v-loading="advertiseManage.getAdvertises.length === 0"
        >
          <el-table-column
            prop="id"
            label="ID"
            width="55"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="title"
            label="广告标题"
            width="200"
            align="center"
            show-overflow-tooltip
          >
          </el-table-column>
          <el-table-column
            prop="description"
            label="描述"
            width="300"
            align="center"
            show-overflow-tooltip
          ></el-table-column>

          <el-table-column label="广告图片" align="center" width="100">
            <template #default="scope">
              <el-image
                class="advertise-image"
                :src="scope.row.imageUrl"
                :crossorigin="null"
                :z-index="10"
                :preview-src-list="[scope.row.avatarUrl]"
                preview-teleported
              >
              </el-image>
            </template>
          </el-table-column>

          <el-table-column
            label="图片跳转地址"
            align="center"
            width="150"
            show-overflow-tooltip
          >
            <template #default="scope">
              <a :href="scope.row.targetUrl">{{ scope.row.targetUrl }}</a>
            </template>
          </el-table-column>
          <el-table-column label="展示状态" align="center">
            <template #default="scope">
              <el-tag :type="scope.row.isShow ? 'success' : 'danger'">{{
                scope.row.isShow ? "开启" : "关闭"
              }}</el-tag>
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center" width="280">
            <template #default="scope">
              <el-button
                type="primary"
                size="small"
                @click="handleView(scope.row)"
                >查看</el-button
              >
              <el-button
                :type="scope.row.isShow ? 'warning' : 'success'"
                @click="updateShow(scope.row)"
                size="small"
              >
                {{ scope.row.isShow ? "关闭" : "开启" }}
              </el-button>
              <el-button
                type="warning"
                size="small"
                @click="handleEdit(scope.row)"
                >编辑</el-button
              >

              <el-button
                type="danger"
                size="small"
                @click="deleteAdById(scope.row.id)"
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
            :current-page="advertiseManage.getPage"
            :page-size="advertiseManage.getSize"
            :total="advertiseManage.getTotal"
            @current-change="handlePageChange"
          ></el-pagination>
        </div>
      </el-row>
    </div>
    <el-dialog title="广告详情" v-model="isView">
      <AdTableDetail :data="rowData" />
    </el-dialog>
    <el-dialog v-model="isOpen" :title="isEdit ? '编辑商品' : '添加商品'">
      <AdTableEditor
        :data="rowData"
        :update="saveData"
        :add="addAdvertise"
        :is-edit="isEdit"
      />
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { Plus } from "@element-plus/icons-vue";
import { useAdvertiseStore } from "@/store/manage";
import {
  queryADByPage,
  deleteAdById,
  updateAd,
  updateShow,
  addAd,
} from "@/api/manage/advertise";
import { Advertise } from "@/common/manage";
const advertiseManage = useAdvertiseStore();
const rowData = ref();
const isView = ref(false);
const isEdit = ref(false);
const isOpen = ref(false);
const defaultData: Advertise = {
  title: "",
  description: "",
  imageUrl: "",
  targetUrl: "",
  startDate: "",
  endDate: "",
  isShow: false,
  id: null,
  createAt: null,
};

onMounted(() => {
  queryADByPage();
});
const addAdvertise = (e: Advertise) => {
  addAd(e);
  isOpen.value = false;
};
const saveData = (e: Advertise) => {
  updateAd(e);
  isOpen.value = false;
};

const handleView = (data: any) => {
  isView.value = true;
  rowData.value = data;
  console.log(advertiseManage.getSize);
};
const handlePageChange = (e: number) => {
  advertiseManage.setAdvertises([]);
  advertiseManage.setPage(e);
  queryADByPage();
};
const handleEdit = (data: any) => {
  isOpen.value = true;
  isEdit.value = true;
  rowData.value = data;
};
const handleAdd = () => {
  isOpen.value = true;
  isEdit.value = false;
  rowData.value = defaultData;
};
</script>

<style scoped lang="less">
.advertise-image {
  width: 80px;
  height: 80px;
  margin: auto;
  display: block;
}
</style>
