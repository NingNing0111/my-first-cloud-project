import { KcContentApi } from "../common";
import request from "@/http";
import { useEditorStore } from "@/store/editor";

const editor = useEditorStore();

export const publishContent = () => {
  request
    .post(KcContentApi.Publish, editor.getContent)
    .then((res) => {
      console.log(res);
      //   editor.deleteContent();
    })
    .catch((err) => {});
  editor.deleteContent();
};
