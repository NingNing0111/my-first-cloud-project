import { defineStore } from "pinia";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
import { User } from "@/common/manage";

export const useSearchStore = defineStore("search", {
  state: () => {
    let knowIndexContents: KnowledgeIndexContent[] = [];
    let viewsOfContents: KnowledgeIndexContent[] = [];
    let starOfUser: User[] = [];
    let likesOfContents: KnowledgeIndexContent[] = [];
    let knowIndexContentsTotal: number = 0;
    let knowIndexPage: number = 1;
    let knowIndexSize: number = 10;
    let keyword: string = "";
    let isSearch: boolean = false;
    let currContent: KnowledgeIndexContent = {} as KnowledgeIndexContent;
    return {
      knowIndexContents,
      knowIndexContentsTotal,
      knowIndexPage,
      knowIndexSize,
      keyword,
      isSearch,
      viewsOfContents,
      starOfUser,
      likesOfContents,
      currContent,
    };
  },
  getters: {
    getContents(): KnowledgeIndexContent[] {
      return this.knowIndexContents;
    },
    getTotal(): number {
      return this.knowIndexContentsTotal;
    },
    getPage(): number {
      return this.knowIndexPage;
    },
    getSize(): number {
      return this.knowIndexSize;
    },
    getKeyWord(): string {
      return this.keyword;
    },
    getIsSearch(): boolean {
      return this.isSearch;
    },
    getViewsOfContents(): KnowledgeIndexContent[] {
      return this.viewsOfContents;
    },
    getStarOfUser(): User[] {
      return this.starOfUser;
    },
    getLikesOfContents(): KnowledgeIndexContent[] {
      return this.likesOfContents;
    },
    getCurrContents(): KnowledgeIndexContent {
      return this.currContent;
    },
  },
  actions: {
    setContents(knowIndexContents: KnowledgeIndexContent[]) {
      this.$patch({ knowIndexContents });
    },
    setTotal(knowIndexContentsTotal: number) {
      this.$patch({ knowIndexContentsTotal });
    },
    setPage(knowIndexPage: number) {
      this.$patch({ knowIndexPage });
    },
    setSize(knowIndexSize: number) {
      this.$patch({ knowIndexSize });
    },
    setIsSearch(isSearch: boolean) {
      this.$patch({ isSearch });
    },
    setKeyword(keyword: string) {
      this.$patch({ keyword });
    },
    setViewsOfContent(viewsOfContents: KnowledgeIndexContent[]) {
      this.$patch({ viewsOfContents });
    },
    setStarOfuser(starOfUser: User[]) {
      this.$patch({ starOfUser });
    },
    setLikesOfContent(likesOfContents: KnowledgeIndexContent[]) {
      this.$patch({ likesOfContents });
    },
    setCurrContent(currContent: KnowledgeIndexContent) {
      this.$patch({ currContent });
    },
  },
});
