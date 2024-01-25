import { User, Advertise } from "@/common/manage";
import { number } from "echarts";
import { defineStore } from "pinia";
export const useManageStore = defineStore("manageUser", {
  state: () => {
    let users: User[] = [];
    let userTotal: number = 10;
    let usersPage: number = 1;
    let usersSize: number = 10;
    let searchname: string = "";
    return {
      users,
      userTotal,
      usersPage,
      usersSize,
      searchname,
    };
  },
  getters: {
    getUsers(): User[] {
      return this.users;
    },
    getUsersTotal(): number {
      return this.userTotal;
    },
    getUsersPage(): number {
      return this.usersPage;
    },
    getUsersSize(): number {
      return this.usersSize;
    },
    getSearchName(): string {
      return this.searchname;
    },
  },
  actions: {
    setUsers(users: User[]) {
      this.$patch({ users });
    },
    setUsersTotal(userTotal: number) {
      this.$patch({ userTotal });
    },
    setUsersPage(usersPage: number) {
      this.$patch({ usersPage });
    },
    setUsersSize(usersSize: number) {
      this.$patch({ usersSize });
    },
    setSearchName(searchname: string) {
      this.$patch({ searchname });
    },
  },
});

export const useAdvertiseStore = defineStore("manageAdvertise", {
  state: () => {
    let advertises: Advertise[] = [];
    let advertisesTotal: number = 0;
    let advertisesPage: number = 1;
    let advertisesSize: number = 10;
    return {
      advertises,
      advertisesTotal,
      advertisesPage,
      advertisesSize,
    };
  },
  getters: {
    getAdvertises(): Advertise[] {
      return this.advertises;
    },
    getTotal(): number {
      return this.advertisesTotal;
    },
    getPage(): number {
      return this.advertisesPage;
    },
    getSize(): number {
      return this.advertisesSize;
    },
  },
  actions: {
    setAdvertises(advertises: Advertise[]) {
      this.$patch({ advertises });
    },
    setTotal(advertisesTotal: number) {
      this.$patch({ advertisesTotal });
    },
    setPage(advertisesPage: number) {
      this.$patch({ advertisesPage });
    },
    setSize(advertisesSize: number) {
      this.$patch({ advertisesSize });
    },
  },
});
