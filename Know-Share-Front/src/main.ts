import { createApp } from "vue";
import App from "./App.vue";
import ElementPlus from "element-plus";
import router from "./router";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import "./main.css";
import { createPinia } from "pinia";
import "@/assets/css/icon.css";
import { useUserStore, initTestUser } from "@/store/user";
import "default-passive-events";
import zhCn from "element-plus/dist/locale/zh-cn.mjs";
// import "highlight.js/styles/atom-one-dark.css";
// import "highlight.js/lib/common";
// import hljsVuePlugin from "@highlightjs/vue-plugin";

const init = () => {
  const app = createApp(App);
  for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
  }
  app.use(createPinia());
  app.use(ElementPlus, {
    locale: zhCn,
  });
  app.use(router);
  // app.use(hljsVuePlugin);
  // initTestUser("ADMIN");
  const user = useUserStore();

  app.directive("role", {
    mounted(el, binding) {
      const roles = user.getUserRoles;
      if (!roles.includes(String(binding.value))) {
        el["hidden"] = true;
      }
    },
  });

  app.mount("#app");
};

init();
