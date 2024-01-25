import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "path";
const pathSrc = path.resolve(__dirname, "src");
import AutoImport from "unplugin-auto-import/vite";
import Components from "unplugin-vue-components/vite";
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    port: 3000,
    open: false,
  },
  plugins: [
    vue(),
    AutoImport({
      include: [/\.[tj]sx?$/, /\.vue$/, /\.vue\?vue/, /\.md$/],
      imports: ["vue", "vue-router", "pinia"],
      eslintrc: {
        enabled: false,
        globalsPropValue: true,
      },
      dts: "./auto-imports.d.ts",
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      "@": pathSrc,
    },
  },
});
