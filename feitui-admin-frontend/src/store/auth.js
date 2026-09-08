import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('ft_token') || '',
    username: localStorage.getItem('ft_username') || '',
    nickname: localStorage.getItem('ft_nickname') || '',
    role: localStorage.getItem('ft_role') || ''
  }),
  actions: {
    setLogin(token, username, nickname, role) {
      this.token = token
      this.username = username
      this.nickname = nickname
      this.role = role
      localStorage.setItem('ft_token', token)
      localStorage.setItem('ft_username', username)
      localStorage.setItem('ft_nickname', nickname || username)
      localStorage.setItem('ft_role', role || '')
    },
    logout() {
      this.token = ''
      this.username = ''
      this.nickname = ''
      this.role = ''
      localStorage.removeItem('ft_token')
      localStorage.removeItem('ft_username')
      localStorage.removeItem('ft_nickname')
      localStorage.removeItem('ft_role')
    }
  }
})