import { defineStore } from "pinia";
import { KnowledgeContent } from "@/common/knowledgeContent";
import { useUserStore } from "./user";

const currUser = useUserStore();

export const useEditorStore = defineStore("editor", {
  state: () => {
    // 从浏览器缓存中获取历史记录
    let contentJson = localStorage.getItem("kc_editor") as string;
    console.log("缓存历史:", contentJson);

    let contentText = localStorage.getItem("kc_text")
      ? (localStorage.getItem("kc_text") as string)
      : "Know Share Hub";
    let contentMd = localStorage.getItem("kc_md")
      ? (localStorage.getItem("kc_md") as string)
      : "# Know Share Hub";
    let knowledgeContent: KnowledgeContent = contentJson
      ? (JSON.parse(contentJson) as KnowledgeContent)
      : ({
          id: "",
          kcId: -1,
          kcTitle: "",
          kcOverview: "",
          kcImage: "",
          userId: currUser.getUserInfo?.id as number,
          kcPublishTime: new Date(),
          kcCategory: [],
          isPublish: false,
          isKcShow: false,
          content: "Know Share Hub",
        } as KnowledgeContent);
    return {
      knowledgeContent,
      contentText,
      contentMd,
    };
  },
  getters: {
    getContent(): KnowledgeContent {
      return this.knowledgeContent;
    },
    getText(): string {
      return this.contentText;
    },
    getMd(): string {
      return this.contentMd;
    },
  },
  actions: {
    setContent(knowledgeContent: KnowledgeContent) {
      this.$patch({ knowledgeContent });
    },
    deleteContent() {
      localStorage.removeItem("kc_editor");
      localStorage.removeItem("kc_text");
      localStorage.removeItem("kc_md");
      this.knowledgeContent = {} as KnowledgeContent;
      this.contentMd = "";
      this.contentText = "";
    },
    setText(contentText: string) {
      this.$patch({ contentText });
    },
    setMd(contentMd: string) {
      this.$patch({ contentMd });
    },
  },
});
