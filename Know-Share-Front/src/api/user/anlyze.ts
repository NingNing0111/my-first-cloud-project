import { KcContentApi, UserAnalyzeApi } from "../common";
import request from "@/http";
import { useUserAnalyze } from "@/store/userAnalyze";
import { UserByCountInfo } from "@/common/analyze";
import { useUserStore } from "@/store/user";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";

const userAnalyzeStore = useUserAnalyze();
const currUserStore = useUserStore();
// 获取用户知识领域百分比数据
export const dominPercentage = async () => {
  userAnalyzeStore.setCategoryPercentage({});
  let res = request
    .get(
      UserAnalyzeApi.CategoryPercentageByUserId + currUserStore.getUserInfo?.id
    )
    .then((res) => {
      if (res.data) {
        userAnalyzeStore.setCategoryPercentage(res.data);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

// 获取用户知识内容总阅读量、内容点赞总数、知识内容总数
export const userCount = async () => {
  userAnalyzeStore.resetCountInfo();
  let res = request
    .get(
      UserAnalyzeApi.QueryViewsLikesColletsByUserId +
        currUserStore.getUserInfo?.id
    )
    .then((res) => {
      if (res.data) {
        let countInfo = res.data as UserByCountInfo;
        console.log("用户数据统计===>", countInfo);

        userAnalyzeStore.setCountInfo(countInfo);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

// 获取用户的知识内容
export const queryPageByUserId = async () => {
  userAnalyzeStore.setContentList([]);
  let res = request
    .get(
      KcContentApi.SearchPageByUserId +
        currUserStore.getUserInfo?.id +
        "/page/" +
        (userAnalyzeStore.getPage - 1) +
        "/size/" +
        userAnalyzeStore.getSize
    )
    .then((res) => {
      let total = res.data.totalElements;
      userAnalyzeStore.setTotal(total);
      let contentList;
      if (res.data.content) {
        contentList = res.data.content;

        contentList.forEach((e: any) => {
          const know: KnowledgeIndexContent = {
            id: e.kcId,
            title: e.kcTitle,
            overview: e.kcOverview,
            image: e.image,
            username: currUserStore.getUserInfo?.username as string,
            publishTime: e.kcPublishTime,
            likes: e.likes,
            collect: e.collect,
            comment: e.comment,
            view: e.view,
            category: e.kcCategory,
            userId: e.userId,
            content: e.content,
          };
          userAnalyzeStore.getUserContentList.push(know);
        });
      }
    })
    .catch((err) => {
      console.log("err===>", err);
    });
};

// 获取用户收藏的知识内容
export const queryCollectPageByUserId = async () => {
  currUserStore.setCollectList([]);
  let res = request
    .get(
      KcContentApi.SearchCollectPageByUserId +
        currUserStore.getUserInfo?.id +
        "/page/" +
        (currUserStore.getCollectPage - 1) +
        "/size/" +
        currUserStore.getCollectSize
    )
    .then((res) => {
      let contentList;
      if (res.data.records) {
        let total = res.data.total;
        currUserStore.setCollectTotal(total);
        contentList = res.data.records;
        contentList.forEach((e: any) => {
          const know: KnowledgeIndexContent = {
            id: e.kcId,
            title: e.kcTitle,
            overview: e.kcOverview,
            image: e.image,
            username: "",
            publishTime: e.kcPublishTime,
            likes: e.likes,
            collect: e.collect,
            comment: e.comment,
            view: e.view,
            category: e.kcCategory,
            userId: e.userId,
            content: e.content,
          };
          currUserStore.getCollectList.push(know);
        });
      }
    })
    .catch((err) => {
      console.log(err);
      currUserStore.setCollectList([]);
    });
};
