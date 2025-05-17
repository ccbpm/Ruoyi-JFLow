<template>
  <div v-if="!item.hidden">
    <template
      v-if="hasOneShowingChild(item.children, item) && (!onlyOneChild.children || onlyOneChild.noShowingChildren) && !item.alwaysShow">

      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path, onlyOneChild.query)">
        <el-menu-item :index="resolvePath(onlyOneChild.path)" :class="{ 'submenu-title-noDropdown': !isNest }">

          <item  :icon="onlyOneChild.meta.icon || (item.meta && item.meta.icon)"
            :title="onlyOneChild.meta.title" />
        </el-menu-item>
      </app-link>
    </template>

    <el-submenu v-else ref="subMenu" :index="resolvePath(item.path)" popper-append-to-body>
      <template slot="title" v-if="isAdmin==true">
        <el-tooltip class="item" placement="right" popper-class="tooltip-bg">
          <template slot="content">
            <span style="cursor: pointer;" @click="addCCFastRoute(item)">
              <i class="el-icon-circle-plus-outline"></i>
              添加CCFAST菜单
            </span>
          </template>
          <div class="popover-container">
            <svg-icon :icon-class="item.meta.icon" />
            <span>{{ item.meta.title }}</span>
          </div>
        </el-tooltip>
      </template>
      <template slot="title" v-if="isAdmin==false">
        <item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title" />
      </template>
      <sidebar-item v-for="(child, index) in item.children" :key="child.path + index" :is-nest="true" :item="child"
        :base-path="resolvePath(child.path)" class="nest-menu" />
    </el-submenu>
    <!-- 驰骋新建菜单对话框 -->
    <el-drawer :title="title" :visible.sync="ccopen" size="80%" @close="handleClose(closeEvent, isAddCCMenu)"
      append-to-body>
      <template #header>
        <h4 :id="title" style="height: 2vh">{{ title }}</h4>
      </template>
      <iframe :key="iframeUrl" :src="iframeUrl" style="width: 100%; height: 93vh;"></iframe>
    </el-drawer>
  </div>
</template>

<script>
import path from 'path'
import { isExternal } from '@/utils/validate'
import Item from './Item'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'

import {
  generQuery,
  Open_GPN_Menus,
  Menu_Designer_Url,
  Menu_Runing_Url,
  GenerLastMenuInfo,
  GenerMenuInfo,
  Menu_Delete
} from "@/Toolkit/Dev2CCFastInterface";
import { listMenu, getMenu, delMenu, addMenu, updateMenu, getCCFastMenu } from "@/api/system/menu";
import store from "@/store";

export default {
  name: 'SidebarItem',
  components: { Item, AppLink },
  mixins: [FixiOSBug],
  props: {
    // route object
    item: {
      type: Object,
      required: true
    },
    isNest: {
      type: Boolean,
      default: false
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  data() {
    this.onlyOneChild = null
    return {
      // 弹出层标题
      title: "",
      iframeUrl: "",
      ccopen: false,
      closeEvent: {},
      isAddCCMenu: false,
      // 表单参数
      form: {},
      //是否管理员
      isAdmin: true,
    }
  },
  created() {
    const super_admin = "admin";
    const roles = store.getters.roles
    const hasRole = roles.some(role => {
      return super_admin === role
    })
    console.log(hasRole)
    this.isAdmin=hasRole;

  },
  methods: {
    isDirectory(menuInfo) {
      return menuInfo.menuType === 'M';
    },
    // 表单重置
    reset() {
      this.form = {
        menuId: undefined,
        parentId: 0,
        menuName: undefined,
        icon: undefined,
        menuType: "M",
        orderNum: undefined,
        isFrame: "1",
        isCache: "0",
        visible: "0",
        status: "0"
      };
      this.resetForm("form");
    },
    async handleClose(row, isAddCCMenu) {
      if (isAddCCMenu == false) {
        return;
      }
      const sortNo = row.menuId;
      const sortName = row.menuName;
      const response = await GenerLastMenuInfo(sortNo);
      if (response.code != 200) {
        this.$modal.msgError(response.msg);
        return;
      }
      const data = response.data;
      // 这里需要判断返回的data 是否为空，如果为空，则说明菜单没有创建成功.
      if (data.length == 0) {
        console.log('用户取消创建');
        return; // 阻止继续执行
      }
      // 解析数据
      const menuRow = data[0];
      console.log('获取驰骋平台【', sortName, '】模块中最新创建的菜单【', menuRow.Name, '】菜单编号【', menuRow.No, '】');
      //查询ccfastMenuId是否已存在
      const ccfastMenus = await getCCFastMenu(menuRow.No);
      console.log('getCCFastMenu', ccfastMenus);
      if (ccfastMenus.data != undefined && response.data.length > 0) {
        console.log('已在若依平台创建驰骋最新菜单【', menuRow.Name, '】菜单编号【', menuRow.No, '】');
        return; // 阻止继续执行
      }
      console.log('开始创建驰骋最新菜单【', menuRow.Name, '】菜单编号【', menuRow.No, '】');
      this.reset()
      this.form.parentId = sortNo;
      this.form.orderNum = 0;
      this.form.menuType = 'C';
      this.form.component = 'ccfast/CommonPort';
      this.form.menuName = menuRow.Name;
      this.isFrame = '1';
      // const myurl = await Menu_GenerPortURL(menuRow);
      this.form.path = menuRow.No;
      this.form.query = generQuery(menuRow);
      this.form.ccfastMenuId = menuRow.No;
      this.form.ccfastMenuModel = menuRow.MenuModel;
      this.form.icon = 'link';

      const addResponce = await addMenu(this.form);
      console.log('addMenu', addResponce);
      if (addResponce.code != 200) {
        this.$modal.msgError(addResponce.msg);
        return;
      }
      this.$modal.msgSuccess("新增成功");
      this.ccopen = false;
      this.isAddCCMenu = false;
      // todo 刷新菜单
      window.location.reload();
    },
    addCCFastRoute(row) {
      if (row == null) {
        return;
      }
      const sortNo = row.menuId;
      const sortName = row.menuName;
      this.closeEvent = row;
      const url = Open_GPN_Menus(sortNo, sortName);
      this.iframeUrl = url;
      this.ccopen = true;
      this.isAddCCMenu = true;
      this.title = "添加ccfast菜单";
    },
    hasOneShowingChild(children = [], parent) {
      if (!children) {
        children = [];
      }
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        }
        // Temp set(will be used if only has one showing child)
        this.onlyOneChild = item
        return true
      })

      // When there is only one child router, the child router is displayed by default
      if (showingChildren.length === 1) {
        return true
      }

      // Show parent if there are no child router to display
      if (showingChildren.length === 0) {
        this.onlyOneChild = { ...parent, path: '', noShowingChildren: true }
        return true
      }

      return false
    },
    resolvePath(routePath, routeQuery) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      if (routeQuery) {
        let query = JSON.parse(routeQuery);
        return { path: path.resolve(this.basePath, routePath), query: query }
      }
      return path.resolve(this.basePath, routePath)
    }
  }
}
</script>

<style scoped>


::v-deep .el-tooltip__popper {
  background-color: #459dff !important;
}
</style>
