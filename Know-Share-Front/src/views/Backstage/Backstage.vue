<template>
  <BackstageHeader />
  <BackstageSidebar ref="name" />
  <div class="content-box" :class="{ 'content-collapse': sidebar.collapse }">
    <BackstageTags />
    <div class="content">
      <router-view v-slot="{ Component }">
        <transition name="move" mode="out-in">
          <keep-alive :include="tags.nameList">
            <component :is="Component"></component>
          </keep-alive>
        </transition>
      </router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useSidebarStore } from "@/store/sidebar";
import { useTagsStore } from "@/store/tags";
const tags = useTagsStore();
const sidebar = useSidebarStore();
</script>

<style scoped lang="less"></style>
