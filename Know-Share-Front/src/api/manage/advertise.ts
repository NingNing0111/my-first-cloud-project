import { useAdvertiseStore } from "@/store/manage";
import { Advertise } from "@/common/manage";
import request from "@/http";
import { AdvertiseApi } from "../common";

const advertiseManage = useAdvertiseStore();
// 分页查询
export const queryADByPage = () => {
  request
    .get(
      AdvertiseApi.Page +
        "/page/" +
        (advertiseManage.getPage - 1) +
        "/size/" +
        advertiseManage.getSize
    )
    .then((res) => {
      console.log(res.data.total);
      advertiseManage.setAdvertises(res.data.advertises.content as Advertise[]);
      advertiseManage.setTotal(res.data.total);
    })
    .catch((err) => {});
};
// 删除广告
export const deleteAdById = (id: number) => {
  request
    .delete(AdvertiseApi.Delete + id)
    .then((res) => {
      ElMessage({
        message: res.message,
        type: "success",
      });
      advertiseManage.setAdvertises([]);
      queryADByPage();
    })
    .catch((err) => {
      ElMessage({
        message: res.message,
        type: "error",
      });
    });
};
// 更新广告
export const updateAd = (advertise: Advertise) => {
  console.log(advertise);

  request
    .put(AdvertiseApi.Update + advertise.id, advertise)
    .then((res) => {
      console.log(res);

      ElMessage({
        message: res.message,
        type: res.status,
      });
      queryADByPage();
    })
    .catch((err) => {});
};
// 改变广告状态
export const updateShow = (advertise: Advertise) => {
  advertise.isShow = !advertise.isShow;
  updateAd(advertise);
  queryADByPage();
};

// 添加广告
export const addAd = (advertise: Advertise) => {
  advertise.startDate = "2024-01-16";
  advertise.endDate = "2024-01-26";
  request
    .post(AdvertiseApi.Add, advertise)
    .then((res) => {
      ElMessage({
        message: res.message,
        type: res.status,
      });
      queryADByPage();
    })
    .catch((err) => {});
};
