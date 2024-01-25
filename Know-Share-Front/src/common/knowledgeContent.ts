export interface KnowledgeContent {
  id: string;
  kcId: number;
  kcTitle: string;
  kcOverview: string;
  kcImage: string;
  userId: number;
  kcPublishTime: Date;
  kcCategory: string[];
  isKcShow: boolean;
  isPublish: boolean;
  content: string;
}

export interface KnowledgeIndexContent {
  id: number;
  title: string;
  overview: string;
  image: string;
  username: string;
  publishTime: Date;
  likes: number;
  collect: number;
  comment: number;
  view: number;
  category: string[];
  userId: number | void;
  content: string;
}

export interface KnowManageContent {
  id: number;
  title: string;
  overview: string;
  publishTime: Date;
  category: string[];
  userId: number;
  isShow: boolean;
  isSensitive: boolean;
}

export interface Tab {
  label: string;
  name: string;
}

export interface Pagination {
  currPage: number;
  total: number;
  sizeChange: Function | null;
  currentChang: Function | null;
  pageSize: number;
}

export interface KnowSearchParam {
  title: string | null;
  overview: string | null;
  userId: number | null;
}
