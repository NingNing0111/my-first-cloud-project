import { defineStore } from "pinia";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
import { UserDetail } from "@/common/user";
import { TOCEntry } from "@/utils";
import { CommentsDetail } from "@/common/detail";

export const useDetailStore = defineStore("detail", {
  state: () => {
    let currUser: UserDetail = {} as UserDetail;
    let currContent: KnowledgeIndexContent = {} as KnowledgeIndexContent;
    let isLoading: boolean = false;
    let titleList: TOCEntry[] = [];
    let comments: CommentsDetail[] = [];
    return {
      currUser,
      currContent,
      isLoading,
      titleList,
      comments,
    };
  },
  getters: {
    getCurrUser(): UserDetail {
      return this.currUser;
    },
    getCurrContent(): KnowledgeIndexContent {
      return this.currContent;
    },
    getIsLoding(): boolean {
      return this.isLoading;
    },
    getTitleList(): TOCEntry[] {
      return this.titleList;
    },
    getComments(): CommentsDetail[] {
      return this.comments;
    },
  },
  actions: {
    setUser(currUser: UserDetail) {
      this.$patch({ currUser });
    },
    setContent(currContent: KnowledgeIndexContent) {
      this.$patch({ currContent });
    },
    setIsLoading(isLoading: boolean) {
      this.$patch({ isLoading });
    },
    setTitleList(titleList: TOCEntry[]) {
      this.$patch({ titleList });
    },
    setComments(comments: CommentsDetail[]) {
      this.$patch({ comments });
    },
  },
});
