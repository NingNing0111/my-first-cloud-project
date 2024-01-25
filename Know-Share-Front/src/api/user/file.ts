import request from "@/http";
import { FileAPi } from "../common";
import axios from "axios";
import { BASE_URL } from "@/http/config";
import { FileType } from "@/common/user/backobject";

const service = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "multipart/form-data",
  },
  withCredentials: false,
});
// 上传文件
export const uploadFile = async (
  file: File,
  userId: number
): Promise<FileType> => {
  let res = await service.post(FileAPi.Upload, {
    file: file,
    userId: userId,
  });
  if (res.data.data) {
    return res.data.data;
  }
  return {
    name: res.data.message,
    url: "#",
    type: "异常",
    id: null,
  };
};

// 查询用户的所有文件
export const queryFileByUserId = async (
  userId: number
): Promise<FileType[]> => {
  let res = await request.get(FileAPi.QueryByUserId + userId);
  if (res.data) {
    console.log("=====>", res.data);
    return res.data;
  } else {
    return [];
  }
};

export const deleteFileById = async (id: number) => {
  let res = await request.delete(FileAPi.DeleteById + id);
};
