import request from "@/http";
import { KcContentApi } from "../common";
import {
  KnowSearchParam,
  KnowledgeIndexContent,
  KnowManageContent,
} from "@/common/knowledgeContent";

// 根据参数检索知识内容
export const searchByParam = async (
  page: number,
  size: number,
  param: KnowSearchParam
): Promise<object> => {
  let res = await request.get(
    KcContentApi.SearchByParam + "/page/" + (page - 1) + "/size/" + size,
    param
  );
  if (res.data) {
    let knowData: KnowManageContent[] = [];
    res.data.content.forEach((e) => {
      const know: KnowManageContent = {
        id: e.kcId,
        title: e.kcTitle,
        overview: e.kcOverview,
        publishTime: e.kcPublishTime,
        category: e.kcCategory,
        userId: e.userId,
        isShow: e.isKcShow,
        isSensitive: e.isSensitive,
      };
      knowData.push(know);
    });
    return {
      data: knowData,
      total: res.data.totalElements,
    };
  }
  return {
    data: [],
    total: 0,
  };
};

// 通过ID修改知识内容显示状态
export const changeShow = async (kcId: number) => {
  console.log(kcId);

  let res = request.put(KcContentApi.ChangeShow + kcId);
  console.log(res);
};
