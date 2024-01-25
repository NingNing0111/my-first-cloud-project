import {
  CollectApi,
  CommentApi,
  LikeApi,
  UserAnalyzeApi,
  ReportApi,
} from "@/api/common";
import request from "@/http";
import { useDetailStore } from "@/store/detail";
import { useUserStore } from "@/store/user";
import { findUserById } from "./search";
import { UserDetail } from "@/common/user";
import { Comments, CommentsDetail } from "@/common/detail";
import router from "@/router";
const detailStore = useDetailStore();
const currUserStore = useUserStore();

// 获取用户详情信息，包括知识内容总数、收藏量、点赞量、观看量
export const getUserAllCounts = async (id: number) => {
  detailStore.setUser({} as UserDetail);
  let resCount = await request.get(
    UserAnalyzeApi.QueryViewsLikesColletsByUserId + id
  );
  let resUser = await findUserById(id);
  console.log(resUser);

  // 知识内容数
  let knowCoun = resCount.data.knowCount;
  // 收藏量
  let collectCount = resCount.data.collectCount;
  // 点赞量
  let likeCount = resCount.data.likeCount;
  // 观看量
  let viewCount = resCount.data.viewCount;
  // 昵称
  let username = resUser.username;
  // 头像
  let avatarUrl = resUser.avatarUrl;
  // 性别
  let gender = resUser.gender;
  let user: UserDetail = {
    id: id,
    username: username,
    gender: gender,
    avatarUrl: avatarUrl,
    likesCount: likeCount,
    contentCount: knowCoun,
    collectCount: collectCount,
    viewCount: viewCount,
  };
  detailStore.setUser(user);
};

// 获取某篇文章的所有评论
export const queryCommentsByKcId = async (kcId: number) => {
  let res = await request.get(CommentApi.QueryByKcId + kcId);
  let commentsList = res.data;
  detailStore.setComments([]);
  commentsList.forEach((e: any) => {
    let commentDetail: CommentsDetail = {
      id: e.commentsId,
      content: e.commentsContent,
      time: e.commentsTime,
      kcId: e.kcId,
      userAvatarUrl: e.userAvatarUrl,
      userId: e.userId,
      username: e.userNickname,
      childComment: getChildComments(e.childComment),
    };
    detailStore.getComments.push(commentDetail);
  });
};
// 递归获取子评论
const getChildComments = (child: any): any => {
  if (!child || child.length < 0) {
    return [] as CommentsDetail[];
  }
  let childComments: CommentsDetail[] = [];
  child.forEach((e: any) => {
    let comment = {
      id: e.commentsId,
      content: e.commentsContent,
      time: e.commentsTime,
      kcId: e.kcId,
      userAvatarUrl: e.userAvatarUrl,
      userId: e.userId,
      username: e.userNickname,
      childComment: getChildComments(e.childComment),
    };
    childComments.push(comment);
  });
  return childComments;
};

// 评论
export const toComments = async (
  commentsContent: string,
  isParentCommentId: number | null
) => {
  let commentsForm: Comments = {
    userId: currUserStore.getUserInfo?.id as number,
    kcId: detailStore.getCurrContent.id,
    commentsContent: commentsContent,
    isParentCommentId: isParentCommentId === null ? 0 : isParentCommentId,
  };
  console.log(commentsForm);

  let res = await request.post(CommentApi.Add, commentsForm);
};

// 点赞和收藏
export const toLikeAndCollect = (kcId: number, api: CollectApi | LikeApi) => {
  request
    .post(api, {
      userId: currUserStore.getUserInfo?.id,
      kcId: kcId,
    })
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {});
};

// 点赞
export const toLike = (kcId: number) => {
  console.log("进行了一次点赞:====>");

  toLikeAndCollect(kcId, LikeApi.Like);
};

// 收藏
export const toCollect = (kcId: number) => {
  console.log("进行了一次收藏：====>");

  toLikeAndCollect(kcId, CollectApi.Collect);
};
// 跳转文章详情页
export const toDetail = (kcId: number, userId: number) => {
  router.push({
    name: "detail",
    params: {
      kcId: kcId,
      userId: userId,
    },
  });
};

// 举报知识内容
export const report = async (kcId: number, content: string) => {
  let res = await request.post(ReportApi.Report, {
    reporterId: currUserStore.getUserInfo?.id,
    kcId: kcId,
    content: content,
  });
  return res.status;
};
