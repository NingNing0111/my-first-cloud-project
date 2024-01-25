import { KcContentApi } from "@/api/common";
import request from "@/http";
import { KnowledgeIndexContent } from "@/common/knowledgeContent";
import { useSearchStore } from "@/store/home";
import { UserApi } from "../common";
import { useDetailStore } from "@/store/detail";
import { User } from "@/common/manage";
import { getTitle } from "@/utils";

const searchStore = useSearchStore();
const detailStore = useDetailStore();

export const findUserById = async (id: number): Promise<User> => {
  try {
    const res = await request.get(UserApi.FindById + id);
    return res.data;
  } catch (err) {
    return {} as User;
  }
};

// 首页分页查询知识内容
const queryPage = async (api: KcContentApi) => {
  searchStore.setIsSearch(true);
  searchStore.getContents.splice(0, searchStore.getContents.length);

  try {
    const res = await request.get(
      api +
        "/page/" +
        (searchStore.getPage - 1) +
        "/size/" +
        searchStore.getSize,

      {
        keyword: searchStore.getKeyWord,
      }
    );
    let contentList;
    if (res.data.content) {
      contentList = res.data.content;
    } else {
      contentList = res.data;
    }
    contentList.forEach((e: any) => {
      const know: KnowledgeIndexContent = {
        id: e.kcId,
        title: e.kcTitle,
        overview: e.kcOverview,
        image: e.image,
        username: "",
        publishTime: e.kcPublishTime,
        likes: e.likes,
        collect: e.collect,
        comment: e.comment,
        view: e.view,
        category: e.kcCategory,
        userId: e.userId,
        content: e.content,
      };
      searchStore.getContents.push(know);
    });

    searchStore.setTotal(res.data.totalElements);
  } catch (err) {
    console.log("错误：", err);
  } finally {
    searchStore.setIsSearch(false);
  }
};

// 获取首页详情
export const queryContentPage = async () => {
  await queryPage(KcContentApi.SearchPage);
};

// 根据知识内容查询
export const searchByContent = async () => {
  await queryPage(KcContentApi.SearchPageByContent);
};

// 根据知识内容标题查询
export const searchByTitle = async () => {
  await queryPage(KcContentApi.SearchPageByTitle);
};

// 根据知识内容概述查询
export const searchOverview = async () => {
  await queryPage(KcContentApi.SearchPageByOverview);
};
// 根据知识内容标签查询
export const searchByCategory = async () => {
  await queryPage(KcContentApi.SearchPageByCategory);
};

// 搜索
export const search = (type: string, keyword: string) => {
  if (keyword === "") {
    ElMessage({
      message: "请输入搜索内容",
      type: "warning",
    });
  } else {
    searchStore.setKeyword(keyword);
    console.log(type);

    try {
      if (type === "content") {
        searchByContent();
      }
      if (type === "title") {
        searchByTitle();
      }
      if (type === "overview") {
        searchOverview();
      }
      if (type === "category") {
        searchByCategory();
      }
    } catch (err) {
      console.log(err);
    }
  }
};

// 观看量分页排序
export const pageByView = async () => {
  await queryPage(KcContentApi.SearchPageByView);
};

// 收藏量分页排序
export const pageByCollect = async () => {
  await queryPage(KcContentApi.SearchPageByCollect);
};

// 讨论数分页排序
export const pageByComment = async () => {
  await queryPage(KcContentApi.SearchPageByComment);
};

// 权重搜索
export const pageByWeight = (type: string) => {
  if (type === "default") {
    queryContentPage();
  }
  if (type === "view") {
    pageByView();
  }
  if (type === "collect") {
    pageByCollect();
  }
  if (type === "comment") {
    pageByComment();
  }
};

// 好评榜
export const pageByLikeList = async () => {
  try {
    searchStore.setLikesOfContent([]);

    const res = await request.get(
      KcContentApi.SearchPageByLikes +
        "/page/" +
        (searchStore.getPage - 1) +
        "/size/" +
        searchStore.getSize,

      {
        keyword: searchStore.getKeyWord,
      }
    );
    let contentList;
    if (res.data.content) {
      contentList = res.data.content;
    } else {
      contentList = res.data;
    }
    contentList = contentList.splice(0, 5);

    contentList.forEach((e: any) => {
      const know: KnowledgeIndexContent = {
        id: e.kcId,
        title: e.kcTitle,
        overview: e.kcOverview,
        image: e.image,
        username: "",
        publishTime: e.kcPublishTime,
        likes: e.likes,
        collect: e.collect,
        comment: e.comment,
        view: e.view,
        category: e.kcCategory,
        userId: e.userId,
        content: e.content,
      };
      searchStore.getLikesOfContents.push(know);
    });

    searchStore.setTotal(res.data.totalElements);
  } catch (err) {
    console.log("错误：", err);
  } finally {
    searchStore.setIsSearch(false);
  }
};

// 热门榜
export const pageByViewList = async () => {
  try {
    searchStore.setViewsOfContent([]);
    const res = await request.get(
      KcContentApi.SearchPageByView +
        "/page/" +
        (searchStore.getPage - 1) +
        "/size/" +
        searchStore.getSize,

      {
        keyword: searchStore.getKeyWord,
      }
    );
    let contentList;
    if (res.data.content) {
      contentList = res.data.content;
    } else {
      contentList = res.data;
    }
    contentList = contentList.splice(0, 5);

    contentList.forEach((e: any) => {
      const know: KnowledgeIndexContent = {
        id: e.kcId,
        title: e.kcTitle,
        overview: e.kcOverview,
        image: e.image,
        username: "",
        publishTime: e.kcPublishTime,
        likes: e.likes,
        collect: e.collect,
        comment: e.comment,
        view: e.view,
        category: e.kcCategory,
        userId: e.userId,
        content: e.content,
      };
      searchStore.getViewsOfContents.push(know);
    });

    searchStore.setTotal(res.data.totalElements);
  } catch (err) {
  } finally {
    searchStore.setIsSearch(false);
  }
};

// 根据ID获取内容详情
export const searchById = async (id: number) => {
  detailStore.setIsLoading(true);
  searchStore.setCurrContent({} as KnowledgeIndexContent);
  detailStore.setTitleList([]);
  const res = await request.get(KcContentApi.DetailById + id);
  await addViewById(id);
  let e = res.data;
  const know: KnowledgeIndexContent = {
    id: e.kcId,
    title: e.kcTitle,
    overview: e.kcOverview,
    image: e.image,
    username: "",
    publishTime: e.kcPublishTime,
    likes: e.likes,
    collect: e.collect,
    comment: e.comment,
    view: e.view,
    category: e.kcCategory,
    userId: e.userId,
    content: e.content,
  };
  detailStore.setContent(know);
  detailStore.setIsLoading(false);
  detailStore.setTitleList(getTitle(know.content));
};

// 文章浏览量+1
export const addViewById = async (id: number) => {
  await request.get(KcContentApi.ViewAddById + id);
};
