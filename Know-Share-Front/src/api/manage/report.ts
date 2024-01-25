import request from "@/http";
import { ReportApi } from "../common";
import { Report } from "@/common/manage";

// 举报数据接口
export const getReportData = async (
  page: number,
  size: number,
  isHandle: boolean | null
): Promise<object> => {
  let total = 0;
  let data: Report[] = [];
  let res = await request.get(
    ReportApi.QueryReport + "/page/" + (page - 1) + "/size/" + size,
    {
      isHandle: isHandle,
    }
  );
  if (res.data) {
    total = res.data.total;
    data = res.data.records;
  }
  return {
    total: total,
    data: data,
  };
};

// 删除举报数据
export const deleteReportData = async (id: number) => {
  let res = await request.delete(ReportApi.DeleteReport + id);
  return res.status;
};

// 处理举报
export const handleReportData = async (id: number) => {
  let res = await request.put(ReportApi.ChangeReport + id);
  return res.status;
};
