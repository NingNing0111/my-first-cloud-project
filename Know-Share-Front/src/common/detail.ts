export interface Comments {
  userId: number;
  kcId: number;
  commentsContent: string;
  isParentCommentId: number;
}

export interface CommentsDetail {
  id: number;
  userId: number;
  kcId: number;
  content: string;
  time: string;
  userAvatarUrl: string;
  username: string;
  childComment: CommentsDetail[];
}
