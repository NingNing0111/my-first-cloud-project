import request from "@/http";
import { UserAnalyzeApi, KnowAnalyzeApi } from "../common";
import { number } from "echarts";

// 获取平台用户总数
export const countUser = async (): Promise<number> => {
  let res = await request.get(UserAnalyzeApi.CountUser);
  if (res.data) {
    return res.data.count;
  }
  return 0;
};
// 获取平台知识内容总数
export const countKnow = async (): Promise<number> => {
  let res = await request.get(KnowAnalyzeApi.CountKnow);
  if (res.data) {
    return res.data.count;
  }
  return 0;
};
// 获取平台总观看量
export const countView = async (): Promise<number> => {
  let res = await request.get(KnowAnalyzeApi.CountView);
  if (res.data) {
    return res.data.views;
  }
  return 0;
};
// 获取平台敏感词检测率
export const sensitivePercentage = async (): Promise<number> => {
  let res = await request.get(KnowAnalyzeApi.SensitivePercentage);
  if (res.data) {
    return res.data;
  }
  return 0;
};
// 获取某年每月用户注册量
export const countRegisterUserByYear = async (
  year: number
): Promise<object> => {
  let res = await request.get(UserAnalyzeApi.CountUserByYear + year);
  if (res.data) {
    return res.data;
  }
  return {};
};
// 获取某年每月知识内容发布量
export const countKnowByYear = async (year: number): Promise<object> => {
  let res = await request.get(KnowAnalyzeApi.CountKnowByYear + year);
  if (res.data) {
    return res.data;
  }
  return {};
};
