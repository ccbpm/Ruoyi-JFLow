import { login, logout, getInfo } from '@/api/login'
import {getToken, setToken, removeToken, setJFlowToken, setJFlowUserToken} from '@/utils/auth'
import { isHttp, isEmpty } from "@/utils/validate"
import defAva from '@/assets/images/profile.jpg'
import * as Dev2ApiInterface from "@/Toolkit/Dev2ApiInterface";

const user = {
  state: {
    token: getToken(),
    id: '',
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },
 LoginParams: {
    username: '',
    password: '',
    OrgNo: '', //SAAS版 登录需要组织编号
    verifyCode: '',
    goHomeType: '',
    loginType:''
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {
          setToken(res.token)
          setJFlowToken(res.jflow_token);
          commit('SET_TOKEN', res.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //驰骋工作流登录
    // JFlowLogin({ commit, userInfo }) {
    //   return new Promise((resolve, reject) => {
    //     const data = Dev2ApiInterface.Port_Login(userInfo.username, userInfo.password)
    //     if (!data.Token) {
    //       Modal.error({
    //         title: () => h('span', '错误'),
    //         content: () => h('span', '未获取到登录凭证')
    //       })
    //       return null
    //     }
    //     setJFlowToken(data.token)
    //     setJFlowUserToken(data)
    //   })
    // },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo().then(res => {
          const user = res.user
          let avatar = user.avatar || ""
          if (!isHttp(avatar)) {
            avatar = (isEmpty(avatar)) ? defAva : process.env.VUE_APP_BASE_API + avatar
          }
          if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', res.roles)
            commit('SET_PERMISSIONS', res.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_ID', user.userId)
          commit('SET_NAME', user.userName)
          commit('SET_AVATAR', avatar)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 退出系统
    JFlowLogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        const data = Dev2ApiInterface.Port_Logout(state.id)
      })
    },
    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
   ,

  }
}

export default user
