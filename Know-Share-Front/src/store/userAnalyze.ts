import { defineStore } from "pinia";
import { UserByCountInfo } from "@/common/analyze";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
export const useUserAnalyze = defineStore("userAnalyze", {
  state() {
    let isPublishPage: number = 1;
    let isPublishSize: number = 10;
    let isPublishTotal: number = 10;
    let categoryPercentage: object = {};
    let countInfo: UserByCountInfo = {
      knowCount: 0,
      collectCount: 0,
      likeCount: 0,
      viewCount: 0,
    };
    let userContentList: KnowledgeIndexContent[] = [];
    return {
      categoryPercentage,
      countInfo,
      userContentList,
      isPublishPage,
      isPublishSize,
      isPublishTotal,
    };
  },
  getters: {
    getCategoryPercentage(): object {
      return this.categoryPercentage;
    },
    getCountInfo(): UserByCountInfo {
      return this.countInfo;
    },
    getUserContentList(): KnowledgeIndexContent[] {
      return this.userContentList;
    },
    getPage(): number {
      return this.isPublishPage;
    },
    getSize(): number {
      return this.isPublishSize;
    },
    getTotal(): number {
      return this.isPublishTotal;
    },
  },
  actions: {
    setCategoryPercentage(categoryPercentage: object) {
      this.$patch({ categoryPercentage });
    },
    setCountInfo(countInfo: UserByCountInfo) {
      this.$patch({ countInfo });
    },

    resetCountInfo() {
      let countInfo: UserByCountInfo = {
        knowCount: 0,
        collectCount: 0,
        likeCount: 0,
        viewCount: 0,
      };
      this.$patch({ countInfo });
    },
    setContentList(userContentList: KnowledgeIndexContent[]) {
      this.$patch({ userContentList });
    },
    resetContentList() {
      let userContentList: KnowledgeIndexContent[] = [];
      this.$patch({ userContentList });
    },
    setPage(isPublishPage: number) {
      this.$patch({ isPublishPage });
    },
    setSize(isPublishSize: number) {
      this.$patch({ isPublishSize });
    },
    setTotal(isPublishTotal: number) {
      this.$patch({ isPublishTotal });
    },
  },
});
