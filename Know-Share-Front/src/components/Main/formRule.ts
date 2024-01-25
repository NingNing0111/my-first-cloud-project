import { FormRules } from "element-plus";
export const rules = reactive<FormRules>({
  email: [
    {
      type: "email",
      required: true,
      message: "请输入正确的邮箱格式",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      message: "请输入密码",
      trigger: "blur",
    },
  ],
  nickname: [
    {
      required: true,
      message: "昵称不能为空",
      trigger: "blur",
    },
    {
      min: 5,
      max: 10,
      message: "昵称限制在5～10个字符",
      trigger: "change",
    },
  ],
  gender: [
    {
      required: true,
      message: "请选择一项",
      trigger: "blur",
    },
  ],
  code: [
    {
      required: true,
      message: "请输入验证码",
      trigger: "blur",
    },
  ],
});
