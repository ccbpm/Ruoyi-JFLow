<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="菜单名称" prop="menuName">
        <el-input v-model="queryParams.menuName" placeholder="请输入菜单名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="菜单状态" clearable>
          <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:menu:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="alert('开发中...');"
          v-hasPermi="['system:menu:add']">AI创建CCFast系统应用-开发中</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button type="info" plain icon="el-icon-sort" size="mini" @click="toggleExpandAll">展开/折叠</el-button>
      </el-col>

      <!--      <el-col :span="1.5">-->
      <!--        <el-button type="danger" plain icon="el-icon-plus" size="mini" @click="handleAIAdd"-->
      <!--                   v-hasPermi="['system:menu:add']">CCFAST-AI生成菜单</el-button>-->
      <!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="menuList" row-key="menuId"
      :default-expand-all="isExpandAll" :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
      <el-table-column prop="menuName" label="菜单名称" :show-overflow-tooltip="true" width="160"></el-table-column>
      <el-table-column prop="icon" label="图标" align="center" width="60">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="orderNum" label="排序" width="60"></el-table-column>
      <el-table-column prop="ccfastMenuModel" label="菜单类型" width="115">
        <template slot-scope="scope">
                    <el-button v-if="scope.row.menuType == 'M' " size="mini" type="text"
                               icon="el-icon-online" @click="handleAddCCMenu(scope.row)" v-hasPermi="['system:menu:ccmenu']"
                               style="color: #f27123">插入ccfast菜单</el-button>
          <!--          const modelTipColor = {-->
          <!--          Bill: 'faile',-->
          <!--          RefFlow: 'success',-->
          <!--          SelfUrl: 'faile',-->
          <!--          DictTable: 'primary',-->
          <!--          Func: 'faile',-->
          <!--          Windows: 'faile',-->
          <!--          StandAloneFlow: 'success',-->
          <!--          Tabs: 'warning',-->
          <!--          EntityNoName: 'info',-->
          <!--          RptWhite: 'danger'-->
          <!--          };-->
          <template v-if="scope.row.ccfastMenuModel != null">
            <el-tag v-if="scope.row.ccfastMenuModel == 'RefFlow' || scope.row.ccfastMenuModel == 'StandAloneFlow'"
              type="success">{{ scope.row.ModelTip }}</el-tag>
            <el-tag v-if="scope.row.ccfastMenuModel == 'EntityNoName'" type="primary">{{ scope.row.ModelTip }}</el-tag>
            <el-tag v-if="scope.row.ccfastMenuModel == 'RptWhite'" type="danger">{{ scope.row.ModelTip }}</el-tag>
            <el-tag v-if="scope.row.ccfastMenuModel == 'Tabs'" type="warning">{{ scope.row.ModelTip }}</el-tag>
            <el-tag
              v-if="scope.row.ccfastMenuModel == 'Bill' || scope.row.ccfastMenuModel == 'SelfUrl' || scope.row.ccfastMenuModel == 'Func'"
              type="faile">{{ scope.row.ModelTip }}</el-tag>
            <el-tag
              v-if="scope.row.ccfastMenuModel != 'Bill' && scope.row.ccfastMenuModel != 'SelfUrl'
                && scope.row.ccfastMenuModel != 'Func' && scope.row.ccfastMenuModel != 'Tabs' && scope.row.ccfastMenuModel != 'RptWhite'
                && scope.row.ccfastMenuModel != 'EntityNoName' && scope.row.ccfastMenuModel != 'RefFlow' && scope.row.ccfastMenuModel != 'StandAloneFlow'"
              type="info">{{ scope.row.ModelTip }}</el-tag>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="运行/设计" align="center" class-name="small-padding fixed-width" width="120">
        <template slot-scope="scope">
          <template v-if="scope.row.menuType == 'C' && scope.row.ccfastMenuId != null">
            <el-button size="mini" type="text" icon="el-icon-guide" @click="handleRuning(scope.row)"
              v-hasPermi="['system:menu:run']" style="color: green;">运行</el-button>
            <el-button size="mini" type="text" @click="handleDesign(scope.row)" v-hasPermi="['system:menu:design']"
              style="color: goldenrod;"><svg-icon icon-class='color'></svg-icon>设计</el-button>
          </template>
        </template>
      </el-table-column>
      <el-table-column prop="perms" label="权限标识" :show-overflow-tooltip="true" width="120"> </el-table-column>
      <el-table-column prop="component" label="组件路径" :show-overflow-tooltip="true" width="150"></el-table-column>
      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:menu:edit']">修改</el-button>
          <el-button v-if="scope.row.menuType == 'M'" size="mini" type="text" icon="el-icon-plus"
            @click="handleAdd(scope.row)" v-hasPermi="['system:menu:add']">新增</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:menu:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改菜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="680px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级菜单" prop="parentId">
              <treeselect v-model="form.parentId" :options="menuOptions" :normalizer="normalizer" :show-count="true"
                placeholder="选择上级菜单" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="form.menuType">
                <el-radio label="M">目录</el-radio>
                <el-radio label="C">菜单</el-radio>
                <el-radio label="F">按钮</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="form.menuType != 'F'">
            <el-form-item label="菜单图标" prop="icon">
              <el-popover placement="bottom-start" width="460" trigger="click" @show="$refs['iconSelect'].reset()">
                <IconSelect ref="iconSelect" @selected="selected" :active-icon="form.icon" />
                <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
                  <svg-icon v-if="form.icon" slot="prefix" :icon-class="form.icon" style="width: 25px;" />
                  <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                </el-input>
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
              <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName">
              <el-input v-model="form.menuName" placeholder="请输入菜单名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType == 'C'">
            <el-form-item prop="routeName">
              <el-input v-model="form.routeName" placeholder="请输入路由名称" />
              <span slot="label">
                <el-tooltip content="默认不填则和路由地址相同：如地址为：`user`，则名称为`User`（注意：为避免名字的冲突，特殊情况下请自定义，保证唯一性）" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                路由名称
              </span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="form.menuType != 'F'">
            <el-form-item prop="isFrame">
              <span slot="label">
                <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                是否外链
              </span>
              <el-radio-group v-model="form.isFrame">
                <el-radio label="0">是</el-radio>
                <el-radio label="1">否</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType != 'F'">
            <el-form-item prop="path">
              <span slot="label">
                <el-tooltip content="访问的路由地址，如：`user`，如外网地址需内链访问则以`http(s)://`开头" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                路由地址
              </span>
              <el-input v-model="form.path" placeholder="请输入路由地址" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="form.menuType == 'C'">
            <el-form-item prop="component">
              <span slot="label">
                <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                组件路径
              </span>
              <el-input v-model="form.component" placeholder="请输入组件路径" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType != 'M'">
            <el-form-item prop="perms">
              <el-input v-model="form.perms" placeholder="请输入权限标识" maxlength="100" />
              <span slot="label">
                <el-tooltip content="控制器中定义的权限字符，如：@PreAuthorize(`@ss.hasPermi('system:user:list')`)" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                权限字符
              </span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="form.menuType == 'C'">
            <el-form-item prop="query">
              <el-input v-model="form.query" placeholder="请输入路由参数" maxlength="255" />
              <span slot="label">
                <el-tooltip content='访问路由的默认传递参数，如：`{"id": 1, "name": "ry"}`' placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                路由参数
              </span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="form.menuType == 'C'">
            <el-form-item prop="isCache">
              <span slot="label">
                <el-tooltip content="选择是则会被`keep-alive`缓存，需要匹配组件的`name`和地址保持一致" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                是否缓存
              </span>
              <el-radio-group v-model="form.isCache">
                <el-radio label="0">缓存</el-radio>
                <el-radio label="1">不缓存</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" v-if="form.menuType != 'F'">
            <el-form-item prop="visible">
              <span slot="label">
                <el-tooltip content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                显示状态
              </span>
              <el-radio-group v-model="form.visible">
                <el-radio v-for="dict in dict.type.sys_show_hide" :key="dict.value" :label="dict.value">{{ dict.label
                  }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="status">
              <span slot="label">
                <el-tooltip content="选择停用则路由将不会出现在侧边栏，也不能被访问" placement="top">
                  <i class="el-icon-question"></i>
                </el-tooltip>
                菜单状态
              </span>
              <el-radio-group v-model="form.status">
                <el-radio v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.value">{{
                  dict.label
                  }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 驰骋新建菜单对话框 -->
    <el-drawer :title="title" :visible.sync="ccopen" size="80%" @close="handleClose(closeEvent, isAddCCMenu)">
      <template #header>
        <h4 :id="title" style="height: 2vh">{{ title }}</h4>
      </template>
      <iframe :key="iframeUrl" :src="iframeUrl" style="width: 100%; height: 93vh;"></iframe>
    </el-drawer>
  </div>
</template>

<script>
import { listMenu, getMenu, delMenu, addMenu, updateMenu, getCCFastMenu } from "@/api/system/menu";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import IconSelect from "@/components/IconSelect";
import {
  generQuery,
  Open_GPN_Menus,
  Menu_Designer_Url,
  Menu_Runing_Url,
  GenerLastMenuInfo,
  GenerMenuInfo,
  Menu_Delete
} from "@/Toolkit/Dev2CCFastInterface";
import { Admin_Flow_One } from "@/Toolkit/Dev2UrlInterface";

export default {
  name: "Menu",
  dicts: ['sys_show_hide', 'sys_normal_disable'],
  components: { Treeselect, IconSelect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 菜单表格树数据
      menuList: [],
      // 菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: "",
      iframeUrl: "",
      ccopen: false,

      closeEvent: {},
      isAddCCMenu: false,
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部折叠
      isExpandAll: false,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        menuName: undefined,
        visible: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        menuName: [
          { required: true, message: "菜单名称不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "菜单顺序不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "路由地址不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 选择图标
    selected(name) {
      this.form.icon = name;
    },
    /** 查询菜单列表 */
    getList() {
      this.loading = true;
      listMenu(this.queryParams).then(response => {
        const data = this.handleTree(response.data, "menuId");
        this.menuList = this.addModelTipToMenu(data);
        this.loading = false;
      });
    },
    addModelTipToMenu(menuData) {
      // 定义映射关系
      const modelTipMap = {
        Bill: '单据',
        RefFlow: '流程',
        SelfUrl: '自定义菜单',
        DictTable: '字典表',
        Func: '独立功能',
        Windows: '统计分析',
        StandAloneFlow: '独立流程',
        Tabs: '标签容器',
        EntityNoName: '实体',
        RptWhite: '白色大屏',
        FixedUrl: '高代码',
        LinkFlowFunc:'流程菜单'
      };
      // 定义映射关系
      const modelTipColor = {
        Bill: 'faile',
        RefFlow: 'success',
        SelfUrl: 'faile',
        DictTable: 'primary',
        Func: 'faile',
        Windows: 'faile',
        StandAloneFlow: 'success',
        Tabs: 'warning',
        EntityNoName: 'info',
        RptWhite: 'danger'
      };

      // 递归处理函数
      function processNode(node) {
        // 检查当前节点是否需要添加ModelTip
        if (node.ccfastMenuModel && modelTipMap[node.ccfastMenuModel]) {
          node.ModelTip = modelTipMap[node.ccfastMenuModel];
        }

        // 递归处理子节点
        if (node.children && node.children.length > 0) {
          node.children.forEach(child => processNode(child));
        }
      }

      // 遍历顶层菜单
      menuData.forEach(topMenu => processNode(topMenu));
      // menuData.forEach(topMenu => processNode(topMenu));
      return menuData;
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.menuId,
        label: node.menuName,
        children: node.children
      };
    },
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      listMenu().then(response => {
        this.menuOptions = [];
        const menu = { menuId: 0, menuName: '主类目', children: [] };
        menu.children = this.handleTree(response.data, "menuId");
        this.menuOptions.push(menu);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleAddCCMenu(row) {
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
    handleAIAdd() {
      const row = {
        menuId: 1071,
        menuName: "车辆管理Demo",
      };
      const sortNo = row.menuId;
      const sortName = row.menuName;
      this.closeEvent = row;
      const url = Open_GPN_Menus(sortNo, sortName);
      this.iframeUrl = url;
      this.ccopen = true;
      this.isAddCCMenu = true;
      this.title = "添加ccfast菜单";
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
      this.getList();
    },
    async handleRuning(row) {
      if (row == null) {
        return;
      }
      const ccfastMenuId = row.ccfastMenuId;
      //获取ccfast菜单信息
      const response = await GenerMenuInfo(ccfastMenuId);
      if (response.code != 200) {
        this.$modal.msgError(response.msg);
        return;
      }
      const data = response.data;
      const myurl = await Menu_Runing_Url(data);
      this.iframeUrl = myurl;
      this.ccopen = true;
      this.isAddCCMenu = false;
      this.title = "运行ccfast菜单";
    },
    async handleDesign(row) {
      if (row == null) {
        return;
      }
      const ccfastMenuId = row.ccfastMenuId;
      //获取ccfast菜单信息
      const response = await GenerMenuInfo(ccfastMenuId);
      if (response.code != 200) {
        this.$modal.msgError(response.msg);
        return;
      }
      const data = response.data;
      const myurl = await Menu_Designer_Url(data);
      console.info(myurl)
      //流程单独打开
      if (data.MenuModel == 'RefFlow') {
        window.open(myurl)
        return;
      }
      if(myurl==''){
        return
      }
      this.iframeUrl = myurl;
      this.ccopen = true;
      this.isAddCCMenu = false;
      this.title = "设计";
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.menuId) {
        this.form.parentId = row.menuId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加菜单";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      getMenu(row.menuId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改菜单";
      });
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.menuId != undefined) {
            updateMenu(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMenu(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      debugger
      // if (row.path in ['/wf/ddm', '/jflow', '/wf/start', '/wf/todo', '/wf/runing', '/wf/cc','/wf/recent','/wf/complete','/wf/flowList','/wf/formList','/wf/dbList','46012-410610-112111','4102-4327-1012123','80154-410415-1110124','971310-41202-8101114','3993-4141110-109210','menu','monitor']) {
      //   alert('演示环境不允许删除');
      //   return;
      // }
      this.$modal.confirm('是否确认删除名称为"' + row.menuName + '"的数据项？').then(function () {
        if (row.ccfastMenuId) {
          Menu_Delete(row.ccfastMenuId);
        }
        return delMenu(row.menuId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    }
  }
};
</script>
<style lang="scss" scoped>
.el-drawer__body {
  overflow-y: hidden;
}
</style>
