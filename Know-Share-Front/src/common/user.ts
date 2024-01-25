export interface UserInfo {
  id: number;
  username: string;
  email: string;
  avatarUrl: string;
  gender: string;
  createTime: string;
  roles: string[];
}

export interface UserDetail {
  id: number;
  username: string;
  gender: string;
  avatarUrl: string;
  likesCount: number;
  contentCount: number;
  collectCount: number;
  viewCount: number;
}

export interface LoginParam {
  email: string;
  password: string;
}

export interface RegisterParam {
  nickname: string;
  email: string;
  password: string;
  code: string;
  gender: string;
}
