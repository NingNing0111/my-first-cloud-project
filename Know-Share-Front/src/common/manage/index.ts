// 用户管理
export interface User {
  id: number;
  username: string;
  email: string;
  avatarUrl: string;
  gender: string;
  createTime: string;
  roles: string[];
  isBan: boolean;
}

export interface QueryUser {
  page: number;
  size: number;
  name: string;
}

// 广告管理
export interface Advertise {
  id: number | null;
  title: string;
  description: string;
  imageUrl: string;
  targetUrl: string;
  startDate: string;
  endDate: string;
  createAt: string | null;
  isShow: boolean;
}

// 日志管理
export interface Logs {
  id: number;
  date: string;
  level: string;
  source: string;
  message: string;
}

// 举报管理
export interface Report {
  id: number;
  reporterId: number;
  kcId: number;
  content: string;
  reportTime: string;
  isHandle: boolean;
}
