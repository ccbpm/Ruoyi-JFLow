import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const JFlowTokenKey = 'JFLOW-TOKEN'
const JFlowUserKey = 'JFLOW-USER'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  Cookies.remove(JFlowTokenKey)
  Cookies.remove(JFlowUserKey)
  return Cookies.remove(TokenKey)
}

export function getJFlowToken() {
  return Cookies.get(JFlowTokenKey)
}

export function setJFlowToken(token) {
  return Cookies.set(JFlowTokenKey, token)
}

export function removeJFlowToken() {
  return Cookies.remove(JFlowTokenKey)
}
export function getJFlowUser() {
  return Cookies.get(JFlowUserKey)
}

export function setJFlowUser(userinfo) {
  return Cookies.set(JFlowUserKey, userinfo)
}

export function removeJFlowUser() {
  return Cookies.remove(JFlowUserKey)
}
