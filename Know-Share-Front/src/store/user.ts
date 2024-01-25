import { defineStore } from "pinia";
import { UserInfo } from "@/common/user";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";

export const useUserStore = defineStore("user", {
  state: () => {
    const userInfoJson = localStorage.getItem("kc_user") as string;

    const userInfoData = userInfoJson
      ? (JSON.parse(userInfoJson) as UserInfo)
      : null;
    const userInfo: UserInfo | null = userInfoData as UserInfo | null;
    const token = localStorage.getItem("kc_token") as string;
    const collectList: KnowledgeIndexContent[] = [];
    const collectPage: number = 1;
    const collectSize: number = 10;
    const collectTotal: number = 10;
    return {
      userInfo,
      token,
      collectList,
      collectPage,
      collectSize,
      collectTotal,
    };
  },
  getters: {
    getUserInfo(): UserInfo | null {
      return this.userInfo;
    },
    getUserRoles(): string[] {
      return this.userInfo?.roles || [];
    },
    getToken(): string {
      return this.token;
    },
    getCollectList(): KnowledgeIndexContent[] {
      return this.collectList;
    },
    getCollectPage(): number {
      return this.collectPage;
    },
    getCollectSize(): number {
      return this.collectSize;
    },
    getCollectTotal(): number {
      return this.collectTotal;
    },
  },
  actions: {
    setUserInfo(userInfo: UserInfo | null) {
      this.$patch({ userInfo });
      localStorage.setItem("kc_user", JSON.stringify(userInfo));
    },
    setToken(token: string) {
      this.$patch({ token });
      localStorage.setItem("kc_token", token);
    },
    setCollectList(collectList: KnowledgeIndexContent[]) {
      this.$patch({ collectList });
    },
    setCollectPage(collectPage: number) {
      this.$patch({ collectPage });
    },
    setCollectSize(collectSize: number) {
      this.$patch({ collectSize });
    },
    setCollectTotal(collectTotal: number) {
      this.$patch({ collectTotal });
    },
  },
});

export const initTestUser = (role: string) => {
  const testUser = useUserStore();
  const testUserInfo: UserInfo = {
    id: 1,
    username: "Ning",
    email: "zdncode@gmail.com",
    avatarUrl: "https://pgthinker.me/wp-content/uploads/2024/01/ape-logo.png",
    gender: "man",
    createTime: new Date(),
    roles: [role],
  };
  testUser.setUserInfo(testUserInfo);
  testUser.setToken("123");
};
