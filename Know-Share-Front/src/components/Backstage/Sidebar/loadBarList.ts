export const items = [
  // 用户菜单
  {
    icon: "User",
    index: "/home/user/info",
    title: "我的信息",
    role: "USER",
  },
  {
    icon: "EditPen",
    index: "/home/user/editor",
    title: "发布内容",
    role: "USER",
  },
  {
    icon: "Collection",
    index: "/home/user/collect",
    title: "我的收藏",
    role: "USER",
  },
  {
    icon: "MessageBox",
    index: "/home/user/message",
    title: "我的消息",
    role: "USER",
  },
  {
    icon: "Comment",
    index: "/home/user/media",
    title: "媒体库",
    role: "USER",
  },
  // 管理员菜单
  {
    icon: "Odometer",
    index: "/home/manage/dashboard",
    title: "系统首页",
    role: "ADMIN",
  },
  {
    icon: "User",
    index: "/home/manage/user",
    title: "平台用户",
    role: "ADMIN",
  },
  {
    icon: "Document",
    index: "/home/manage/content",
    title: "平台知识内容",
    role: "ADMIN",
  },
  {
    icon: "DocumentDelete",
    index: "/home/manage/sensitive",
    title: "平台敏感词管理",
    role: "ADMIN",
  },
  {
    icon: "Shop",
    index: "/home/manage/advertise",
    title: "广告管理",
    role: "ADMIN",
  },
  {
    icon: "WarnTriangleFilled",
    index: "1",
    title: "举报管理",
    role: "ADMIN",
    subs: [
      // {
      //   index: "/home/manage/report/user",
      //   title: "用户举报管理",
      //   role: "ADMIN",
      // },
      {
        index: "/home/manage/report/content",
        title: "知识内容举报管理",
        role: "ADMIN",
      },
    ],
  },
  {
    icon: "PieChart",
    index: "2",
    title: "平台数据分析",
    role: "NONE",
    subs: [
      {
        index: "/home/manage/analyze/user",
        title: "用户数据分析",
        role: "ADMIN",
      },
      {
        index: "/home/manage/analyze/content",
        title: "知识内容数据分析",
        role: "ADMIN",
      },
    ],
  },
  {
    icon: "Coin",
    index: "/home/manage/logs",
    title: "系统日志",
    role: "ADMIN",
  },
];
