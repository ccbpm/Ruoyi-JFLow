<template>
  <div class="navbar">
    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" v-if="!topNav"/>
    <top-nav id="topmenu-container" class="topmenu-container" v-if="topNav"/>

    <div class="right-menu">
      <template v-if="device!=='mobile'">
        <!-- <span class="right-menu-item " style="cursor: pointer" @click="onlineBan()"><span class="flows">原始版</span></span> -->
        <span class="right-menu-item " style="cursor: pointer" @click="onlineKe()"><span class="flows"> <svg-icon icon-class='phone'></svg-icon>视频教程</span></span>
        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/start?action=start')"><span class="flows"> <svg-icon icon-class='guide'></svg-icon> 发起</span></span>
<!--        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/todo?action=todo')"><span class="flows"> <svg-icon icon-class='time'></svg-icon> 待办</span></span>-->
          <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/todo?action=todo')"><span class="flows"> <svg-icon icon-class='time'></svg-icon> <el-badge :value="todoCount"  >待办 </el-badge></span></span>

        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/runing?action=runing')"><span class="flows"> <svg-icon icon-class='job'></svg-icon> 在途</span></span>
        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/cc?action=cc')"><span class="flows"><svg-icon icon-class='select'></svg-icon><el-badge :value="ccCount"  >抄送 </el-badge> </span></span>

        <!-- <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/recent?action=recent')"><span class="flows"> <svg-icon icon-class='checkbox'></svg-icon> 近期</span></span>
        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/complete?action=complete')"><span class="flows"> <svg-icon icon-class='code'></svg-icon> 完结</span></span> -->
        <template v-if="isAdmin==true">
         <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/system/menu')"><span class="ccfast"><svg-icon icon-class='tree-table'></svg-icon>低代码</span></span>
        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/flowList?action=flowList')"><span class="flows"><svg-icon icon-class='tree'></svg-icon>流程</span></span>
        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/formList?action=formList')"><span class="flows"> <svg-icon icon-class='excel'></svg-icon> 表单</span></span>
        <span class="right-menu-item " style="cursor: pointer" @click="$router.push('/wf/dbList?action=dbList')"><span class="flows"><i class="el-icon-setting"></i>数据源</span></span>
      </template>

<!--        <search id="header-search" class="right-menu-item" />-->
        <!--        <el-tooltip content="源码地址" effect="dark" placement="bottom">-->
        <!--          <ruo-yi-git id="ruoyi-git" class="right-menu-item hover-effect" />-->
        <!--        </el-tooltip>-->

        <!--        <el-tooltip content="文档地址" effect="dark" placement="bottom">-->
        <!--          <ruo-yi-doc id="ruoyi-doc" class="right-menu-item hover-effect" />-->
        <!--        </el-tooltip>-->

        <!--        <screenfull id="screenfull" class="right-menu-item hover-effect" />-->

        <!--        <el-tooltip content="布局大小" effect="dark" placement="bottom">-->
        <!--          <size-select id="size-select" class="right-menu-item hover-effect" />-->
        <!--        </el-tooltip>-->

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar"  @error="handleImageError">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>
          <el-dropdown-item @click.native="setting = true">
            <span>布局设置</span>
          </el-dropdown-item>

          <el-dropdown-item divided @click.native="logout">
            <span>退出登录</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import TopNav from '@/components/TopNav'
import Hamburger from '@/components/Hamburger'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import Search from '@/components/HeaderSearch'
import RuoYiGit from '@/components/RuoYi/Git'
import RuoYiDoc from '@/components/RuoYi/Doc'
import store from '@/store'
import { Flow_TodoNums } from "@/Toolkit/Dev2ApiInterface";

export default {
  components: {
    Breadcrumb,
    TopNav,
    Hamburger,
    Screenfull,
    SizeSelect,
    Search,
    RuoYiGit,
    RuoYiDoc
  },
  data() {
    return {
      //是否管理员
      isAdmin: true,
      todoCount:0,
      ccCount:0
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

    this.cclist();

  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device'
    ]),
    setting: {
      get() {
        return this.$store.state.settings.showSettings
      },
      set(val) {
        this.$store.dispatch('settings/changeSetting', {
          key: 'showSettings',
          value: val
        })
      }
    },
    topNav: {
      get() {
        return this.$store.state.settings.topNav
      }
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      this.$confirm('确定注销并退出系统吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$store.dispatch('LogOut').then(() => {
          location.href = '/index';
        })
      }).catch(() => {});
    }
    ,
    yuanma() {
      window.open('https://gitee.com/opencc/RuoYi-JFlow','_blank')
    }
    ,
    cclist() {
      Flow_TodoNums().then(res => {
        console.info(res)
        const rjson=JSON.parse(res.data);
        this.todoCount= rjson.Todolist_EmpWorks;
        this.ccCount= rjson.CCList_UnRead+ rjson.CCList_Read;
      })
    },
    wendang(){
      window.open('https://gitee.com/opencc/RuoYi-JFlow/wikis/pages/preview?sort_id=9324287&doc_id=1983176','_blank')

    },
    handleImageError(e) {
      // 处理图片不存在的情况，比如替换为默认图片或其他操作
      let src=process.env.VUE_APP_BASE_API;
      if(!src.endsWith("/")){
        src+="/";
      }
      e.target.src =src+"default.png"; // 使用默认图片
    },
    onlineKe(){
      window.open('https://ccflow.org/Ke.html','_blank')
    },
    onlineBan(){
      window.open(' https://vue3.ccbpm.cn/','_blank')
    }


  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-badge__content.is-fixed  {
  top: 9px ;
}
.flows{
  font-size: 14px;
  color: #303133;
}
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .topmenu-container {
    position: absolute;
    left: 50px;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }
    .ccfast{
      font-size: 14px;
      color: #f27123;
      font-weight: bold;
    }
    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }

      }
    }
  }
}
</style>
