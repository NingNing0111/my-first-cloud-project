import request from "@/http";
import { UserApi } from "../common";
import { User } from "@/common/manage";
import { useManageStore } from "@/store/manage";
const userManage = useManageStore();
// 分页查找用户
export const queryUserByPage = () => {
  request
    .get(
      UserApi.Page +
        "/page/" +
        (userManage.getUsersPage - 1) +
        "/size/" +
        userManage.getUsersSize
    )
    .then((res) => {
      const data = res.data;

      // 获取总数和
      const total = data.total as number;
      // 获取用户数据
      const users = data.users as User[];
      userManage.users = users;
      userManage.userTotal = total;
    })
    .catch((err) => {});
};

// 封禁账号
export const banUser = (id: number) => {
  request
    .put(UserApi.BanUser + id)
    .then((res) => {
      console.log(res);

      ElMessage({
        message: res.message,
        type: "success",
      });
      userManage.setUsers([]);
      queryUserByPage();
    })
    .catch((err) => {
      ElMessage({
        message: res.message,
        type: "error",
      });
    });
};

// 删除账号
export const deleteUser = (id: number) => {
  request
    .delete(UserApi.Delete + id)
    .then((res) => {
      console.log(res);
      ElMessage({
        message: res.message,
        type: "success",
      });
      userManage.setUsers([]);
      queryUserByPage();
    })
    .catch((err) => {});
};
