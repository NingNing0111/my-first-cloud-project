import request from "@/http";
import { LoginParam, UserInfo } from "@/common/user";
import { useUserStore } from "@/store/user";
import { UaaApi } from "../common/index";
import router from "@/router";
const user = useUserStore();

// 登录
export const login = async (param: LoginParam) => {
  let res = await request
    .post(UaaApi.Login, param)
    .then((res) => {
      let response = res.data;

      if (res.status === "success") {
        const resUser: UserInfo = {
          id: JSON.parse(response.id) as number,
          username: response.username,
          email: response.email,
          avatarUrl: response.avatarUrl,
          gender: response.gender,
          createTime: response.createTime,
          roles: response.roles,
        };

        localStorage.setItem("kc_user", JSON.stringify(resUser));
        localStorage.setItem("kc_token", response.token);
        user.setToken(response.token);
        user.setUserInfo(resUser);

        setTimeout(() => {
          router.push("/");
        }, 500);
        ElMessage({
          message: "登录成功",
          type: res.status,
        });
      } else {
        ElMessage({
          message: "用户名或密码错误",
          type: res.status,
        });
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

// 登出
export const logout = () => {
  localStorage.removeItem("kc_user");
  localStorage.removeItem("kc_token");
  user.setUserInfo(null);
  user.setToken("");
  setTimeout(() => {
    router.push("/");
  }, 1000);
  ElMessage({
    message: "退出成功",
    type: "success",
  });
};
