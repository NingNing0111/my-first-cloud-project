import axios from "axios";
import { BASE_URL, HEADER } from "./config";
import { ElMessage } from "element-plus";
const NETWORK_ERROR = "网络请求异常，请稍后重试";

const service = axios.create({
  baseURL: BASE_URL,
  // timeout: 10000,
  withCredentials: false,
  headers: HEADER,
});

const request = {
  get(url: string, params?: any) {
    return service.get(url, { params });
  },
  post(url: string, data?: any) {
    return service.post(url, data);
  },
  put(url: string, data?: any) {
    return service.put(url, data);
  },
  delete(url: string, params?: any) {
    return service.delete(url, { params });
  },
};

service.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token !== null) {
      config.headers.Authorization = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    ElMessage({
      message: NETWORK_ERROR,
      type: "error",
    });
    return Promise.reject(error);
  }
);

// 创建响应拦截
service.interceptors.response.use(
  (res: any) => {
    console.log(res);

    return res.data;
  },
  (error) => {
    let message = "";
    console.log(error);

    if (error && error.response) {
      switch (error.response.status) {
        case 302:
          message = "接口重定向了！";
          break;
        case 400:
          message = "参数不正确！";
          break;
        case 401:
          message = "您未登录，或者登录已经超时，请先登录！";
          break;
        case 403:
          message = "您没有权限操作！";
          break;
        case 404:
          message = `请求地址出错: ${error.response.config.url}`;
          break;
        case 408:
          message = "请求超时！";
          break;
        case 409:
          message = "系统已存在相同数据！";
          break;
        case 500:
          ElMessage({
            message: NETWORK_ERROR,
            type: "error",
          });
          break;
        case 501:
          message = "服务未实现！";
          break;
        case 502:
          message = "网关错误！";
          break;
        case 503:
          message = "服务不可用！";
          break;
        case 504:
          message = "服务暂时无法访问，请稍后再试！";
          break;
        case 505:
          message = "HTTP 版本不受支持！";
          break;
        default:
          message = "异常问题，请联系管理员！";
          break;
      }
    }
    return Promise.reject(message);
  }
);

export default request;
