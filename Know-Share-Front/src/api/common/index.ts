export const enum UaaApi {
  Login = "/uaa/auth",
}
export const enum RegisterApi {
  Register = "/register/new",
  Code = "/register/code",
}

export const enum KcContentApi {
  SearchPage = "/es",
  SearchPageByContent = "/es/content",
  SearchPageByTitle = "/es/title",
  SearchPageByOverview = "/es/overview",
  SearchPageByCategory = "/es/category",
  SearchPageByCollect = "/es/collect",
  SearchPageByView = "/es/view",
  SearchPageByComment = "/es/comment",
  SearchPageByLikes = "/es/likes",
  SearchPageByUserId = "/es/",
  SearchCollectPageByUserId = "/know/collect/",
  SearchByParam = "/es/query",
  Publish = "/know/add",
  DetailById = "/es/",
  ViewAddById = "/know/detail/",
  ChangeShow = "/know/",
}

export const enum UserApi {
  Page = "/user",
  Delete = "/user/",
  FindByEmail = "/user/email",
  BanUser = "/user/",
  FindById = "/user/",
}

export const enum AdvertiseApi {
  Page = "/advertise",
  Delete = "/advertise/",
  Update = "/advertise/",
  FindById = "/advertise/",
  Add = "/advertise/add",
}

export const enum CommentApi {
  Add = "/comments/",
  QueryByKcId = "/comments/",
}

export const enum UserAnalyzeApi {
  QueryViewsLikesColletsByUserId = "/userknow/",
  CategoryPercentageByUserId = "/category/",
  CountUser = "/analyzeuser/user/count",
  CountUserByYear = "/analyzeuser/register/year/",
}

export const enum LikeApi {
  Like = "/likes/",
}

export const enum CollectApi {
  Collect = "/collect/",
}

export const enum LogsApi {
  PageSystemLogs = "/logs/system",
  DeleteBatchById = "/logs/delete/batch",
}

export const enum SensitiveApi {
  AddSensitive = "/sensitive/add",
  UpdateSensitive = "/sensitive/update",
  Check = "/sensitive/check",
  Delete = "/sensitive/",
}

export const enum FileAPi {
  Upload = "/file/insert",
  QueryByUserId = "/file/",
  DeleteById = "/file/",
}

export const enum KnowAnalyzeApi {
  SensitivePercentage = "/analyzeknow/percentage/sensitive",
  CountView = "/analyzeknow/sum/view",
  CountKnow = "/analyzeknow/count/know",
  CountKnowByYear = "/analyzeknow/year/",
}

export const enum ReportApi {
  Report = "/report/",
  QueryReport = "/report",
  DeleteReport = "/report/",
  ChangeReport = "/report/",
}
