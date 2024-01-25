import request from "@/http";
import { RegisterParam } from "@/common/user";
import { RegisterApi } from "../common";
import router from "@/router";

// 注册
export const register = (param: RegisterParam) => {
  request
    .post(RegisterApi.Register, param)
    .then((res: any) => {
      setTimeout(() => {
        router.push("/login");
        setTimeout(() => {
          location.reload();
        }, 1000);
      }, 500);
      ElMessage({
        message: res.message,
        type: "success",
      });
    })
    .catch((err) => {
      ElMessage({
        message: "注册异常",
        type: "error",
      });
    });
};
// 验证码发送
export const sendCode = (email: string) => {
  request
    .get(RegisterApi.Code + "/" + email)
    .then((res: any) => {
      ElMessage({
        message: res.message,
        type: "success",
      });
    })
    .catch((err) => {
      console.log(err);

      ElMessage({
        message: "验证码发送异常",
        type: "error",
      });
    });
};
