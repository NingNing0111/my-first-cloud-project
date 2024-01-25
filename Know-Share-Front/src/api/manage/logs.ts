import request from "@/http";
import { Logs } from "@/common/manage";
import { LogsApi } from "@/api/common";

export const querySystemLogs = async (
  page: number,
  size: number
): Promise<object> => {
  let content = [] as Logs[];
  let total = 0;
  try {
    let res = await request.get(
      LogsApi.PageSystemLogs + "/page/" + (page - 1) + "/size/" + size
    );

    if (res.data.content) {
      console.log(res.data);
      total = res.data.totalElements;
      content = res.data.content as Logs[];
    }
    return {
      total: total,
      content: content,
    };
  } catch (err) {
    ElMessage({
      type: "error",
      message: "数据请求异常",
    });
    return {
      total: total,
      content: content,
    };
  }
};

export const deleteByBatch = (ids: number[]) => {
  request
    .post(LogsApi.DeleteBatchById, ids)
    .then((res) => {
      ElMessage({
        type: res.status,
        message: "删除成功",
      });
    })
    .catch((err) => {});
};
