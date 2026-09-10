import request from '@/utils/request'

// 用户管理
export const getAdminUserPage = (params) => request({ url: '/admin-user/page', method: 'get', params })
export const createAdminUser = (data) => request({ url: '/admin-user', method: 'post', data })
export const updateAdminUser = (id, data) => request({ url: `/admin-user/${id}`, method: 'put', data })
export const resetAdminUserPassword = (id, password) => request({ url: `/admin-user/${id}/password`, method: 'put', data: { password } })
export const deleteAdminUser = (id) => request({ url: `/admin-user/${id}`, method: 'delete' })

// 认证
export const login = (data) => request({ url: '/auth/login', method: 'post', data })
export const logout = () => request({ url: '/auth/logout', method: 'post' })
export const getInfo = () => request({ url: '/auth/info', method: 'get' })
// 仅超级管理员本人修改自己的密码
export const changePassword = (data) => request({ url: '/auth/change-password', method: 'post', data })

// 数据看板
export const getOverview = () => request({ url: '/dashboard/overview', method: 'get' })
export const getTrend = (days = 7) => request({ url: '/dashboard/trend', method: 'get', params: { days } })
export const getSource = () => request({ url: '/dashboard/source', method: 'get' })
export const getOnline = () => request({ url: '/dashboard/online', method: 'get' })

// 在线咨询
export const getContactPage = (params) => request({ url: '/admin/contact/page', method: 'get', params })
export const getContactDetail = (id) => request({ url: `/admin/contact/${id}`, method: 'get' })
export const updateContactStatus = (id, status) => request({ url: `/admin/contact/${id}/status`, method: 'put', params: { status } })
export const updateContactRemark = (id, remark) => request({ url: `/admin/contact/${id}/remark`, method: 'put', data: { remark } })
export const deleteContact = (id) => request({ url: `/admin/contact/${id}`, method: 'delete' })

// 视频管理
export const getVideoAdminPage = (params) => request({ url: '/admin/video/page', method: 'get', params })
export const createVideo = (data) => request({ url: '/admin/video', method: 'post', data })
export const updateVideo = (id, data) => request({ url: `/admin/video/${id}`, method: 'put', data })
export const deleteVideo = (id) => request({ url: `/admin/video/${id}`, method: 'delete' })
export const swapVideo = (idA, idB) => request({ url: '/admin/video/swap', method: 'post', params: { idA, idB } })

// 阿里云 OSS 直传：获取预签名上传地址
export const getOssPresign = (data) => request({ url: '/admin/oss/presign', method: 'post', data })