import request from "@/http";
import { SensitiveApi } from "../common";

export const addSensitiveWord = (word: string) => {
  request
    .post(SensitiveApi.AddSensitive, {
      word: word,
    })
    .then((res) => {
      ElMessage({
        type: res.data.status,
        message: res.data.message,
      });
    })
    .catch((err) => {});
};

export const deleteSensitiveWord = (word: string) => {
  request
    .delete(SensitiveApi.Delete + word)
    .then((res) => {
      console.log("敏感词删除===>", res);
    })
    .catch((err) => {});
};

export const checkSensitiveWord = async (text: string): Promise<object> => {
  try {
    let res = await request.post(SensitiveApi.Check, {
      text: text,
    });
    console.log("敏感词检测结果===>", res);
    return {
      isSensitive: res.data.isSensitive,
      statement: res.data.statement,
    };
  } catch {
    ElMessage({
      type: "error",
      message: "检测异常",
    });
    return {
      isSensitive: null,
      statement: text,
    };
  }
};

export const updateSensitiveWord = () => {
  request
    .put(SensitiveApi.UpdateSensitive)
    .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    });
};
