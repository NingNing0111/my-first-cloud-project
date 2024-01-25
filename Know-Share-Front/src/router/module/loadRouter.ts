const routerList = [
  {
    path: "/login",
    component: () => import("@/views/Login/Login.vue"),
    meta: {
      title: "Know Share Hub 登录",
      roles: "VISITOR",
    },
  },
  {
    path: "/details/:userId/:kcId",
    component: () => import("@/views/Home/Details.vue"),
    name: "detail",
    meta: {
      title: "知识内容详情页",
      roles: "VISITOR",
    },
  },
  {
    path: "/register",
    component: () => import("@/views/Register/Register.vue"),
    meta: {
      title: "Know Share Hub 注册",
      roles: "VISITOR",
    },
  },
  {
    path: "/",
    component: () => import("@/views/Home/Home.vue"),
    meta: {
      roles: "VISITOR",
    },
  },
  {
    path: "/403",
    component: () => import("@/views/403.vue"),
  },
  {
    path: "/supplier",
    name: "supplierAllBack",
    component: () => import("@/views/supplierAllBack.vue"),
  },
  {
    path: "/home",
    component: () => import("@/views/Backstage/Backstage.vue"),
    children: [
      {
        path: "/home/user",
        meta: {
          keepAlive: true,
        },
        redirect: "/home/user/info",
        children: [
          {
            path: "media",
            meta: {
              title: "媒体库",
              roles: "USER",
              keepAlive: true,
            },
            name: "media",
            component: () => import("@/views/Backstage/Media.vue"),
          },
          {
            path: "info",
            meta: {
              title: "我的信息",
              roles: "USER",
              keepAlive: true,
            },
            name: "userInfo",
            component: () => import("@/views/Backstage/User.vue"),
          },
          {
            path: "editor",
            meta: {
              title: "发布内容",
              roles: "USER",
              keepAlive: true,
            },
            name: "userEditor",
            component: () => import("@/views/Backstage/Editor.vue"),
          },

          {
            path: "/home/user/collect",
            meta: {
              title: "我的收藏",
              roles: "USER",
              keepAlive: true,
            },
            name: "userCollect",
            component: () => import("@/views/Backstage/Collect.vue"),
          },
          {
            path: "/home/user/message",
            meta: {
              title: "我的消息",
              roles: "USER",
              keepAlive: true,
            },
            name: "userMessage",
            component: () => import("@/views/Backstage/Message.vue"),
          },
        ],
      },
      {
        path: "/home/manage",
        meta: {
          keepAlive: true,
        },
        redirect: "/home/manage/dashboard",
        children: [
          {
            path: "dashboard",
            meta: {
              title: "系统首页",
              roles: "ADMIN",
            },
            name: "dashboard",
            component: () => import("@/views/Backstage/manager/Dashboard.vue"),
          },
          {
            path: "user",
            meta: {
              title: "用户管理",
              roles: "ADMIN",
            },
            name: "userManage",
            component: () => import("@/views/Backstage/manager/UserManage.vue"),
          },
          {
            path: "advertise",
            meta: {
              title: "广告管理",
              roles: "ADMIN",
            },
            name: "advertiseManage",
            component: () =>
              import("@/views/Backstage/manager/AdvertiseManage.vue"),
          },
          {
            path: "content",
            meta: {
              title: "内容管理",
              roles: "ADMIN",
            },
            name: "contentManage",
            component: () =>
              import("@/views/Backstage/manager/ContentManage.vue"),
          },
          {
            path: "logs",
            meta: {
              title: "系统日志",
              roles: "ADMIN",
            },
            name: "logsManage",
            component: () => import("@/views/Backstage/manager/LogsManage.vue"),
          },
          {
            path: "report",
            children: [
              {
                path: "content",
                meta: {
                  title: "知识内容举报管理",
                  roles: "ADMIN",
                },
                name: "reportContent",
                component: () =>
                  import("@/views/Backstage/manager/ContentReportManage.vue"),
              },
            ],
          },
          {
            path: "sensitive",
            meta: {
              title: "敏感词管理",
              roles: "ADMIN",
            },
            name: "sensitiveManage",
            component: () =>
              import("@/views/Backstage/manager/SensitiveManage.vue"),
          },
        ],
      },
    ],
  },
];

export default routerList;
