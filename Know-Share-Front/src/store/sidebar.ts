import { defineStore } from "pinia";

export const useSidebarStore = defineStore("sidebar", {
  state: () => {
    return {
      collapse: false,
      openMenu: true,
    };
  },
  getters: {},
  actions: {
    handleCollapse() {
      console.log(this.collapse ? "折叠" : "打开");

      this.collapse = !this.collapse;
    },
    handleMenu() {
      this.openMenu = !this.openMenu;
    },
  },
});
