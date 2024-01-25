import { createRouter, createWebHistory } from "vue-router";
import "nprogress/nprogress.css";
import NProgress from "nprogress";
import { useUserStore } from "@/store/user";

import routerList from "./module/loadRouter";
const router = createRouter({
  history: createWebHistory(),
  routes: routerList,
  linkActiveClass: "router-active",
  linkExactActiveClass: "router-exact-active",
});
router.beforeEach((to, from, next) => {
  NProgress.start();
  const user = useUserStore();
  const roles = user.getUserRoles;
  const needRole = to.meta.roles as string;
  // 访问路径是游客访问的路径则跳转
  if (needRole === "VISITOR") {
    // 游客可访问的路径
    next();
  } else {
    if (!roles && to.path !== "/login") {
      next("/login");
    } else if (needRole && !roles.includes(needRole)) {
      next("/403");
    } else {
      next();
    }
  }
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
