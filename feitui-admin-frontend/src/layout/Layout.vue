<template>
  <el-container class="layout">
    <el-aside width="220px" class="aside">
      <div class="logo">
        <div class="logo-icon">
          <el-icon :size="20"><Promotion /></el-icon>
        </div>
        <div class="logo-text">
          <span class="logo-title">飞推引流</span>
          <span class="logo-sub">后台管理系统</span>
        </div>
      </div>
      <el-menu
        :default-active="activeMenu"
        router
        class="menu"
        background-color="transparent"
        text-color="#b6c8e0"
        active-text-color="#ffffff"
      >
        <el-menu-item index="/dashboard">
          <el-icon><DataBoard /></el-icon>
          <span>数据看板</span>
        </el-menu-item>
        <el-menu-item index="/contacts">
          <el-icon><ChatDotRound /></el-icon>
          <span>在线咨询管理</span>
        </el-menu-item>
        <el-menu-item index="/users" v-if="isSuper">
          <el-icon><UserFilled /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header class="header">
        <div class="header-left">
          <span class="page-title">{{ pageTitle }}</span>
        </div>
        <div class="header-right">
          <div class="online-tag">
            <span class="dot"></span>
            实时监控中
          </div>
          <el-dropdown @command="handleCommand">
            <span class="user-box">
              <el-avatar :size="32" class="avatar">{{ avatarChar }}</el-avatar>
              <span class="user-name">{{ auth.nickname }}</span>
              <el-icon><ArrowDown /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="password" v-if="isSuper">修改密码</el-dropdown-item>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <el-main class="main">
        <router-view />
      </el-main>
    </el-container>

    <!-- 修改密码对话框(仅超级管理员可用) -->
    <el-dialog v-model="pwdVisible" title="修改密码" width="440px" :close-on-click-modal="false">
      <el-form ref="pwdFormRef" :model="pwdForm" :rules="pwdRules" label-width="90px">
        <el-form-item label="原密码" prop="oldPassword">
          <el-input v-model="pwdForm.oldPassword" type="password" show-password placeholder="请输入原密码" />
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="pwdForm.newPassword" type="password" show-password placeholder="至少6位" />
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="pwdForm.confirmPassword" type="password" show-password placeholder="再次输入新密码" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="pwdVisible = false">取消</el-button>
        <el-button type="primary" :loading="pwdLoading" @click="handleChangePwd">确认修改</el-button>
      </template>
    </el-dialog>
  </el-container>
</template>

<script setup>
import { computed, reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/store/auth'
import { ElMessage, ElMessageBox } from 'element-plus'
import { logout, changePassword } from '@/api'

const route = useRoute()
const router = useRouter()
const auth = useAuthStore()

const activeMenu = computed(() => route.path)
const pageTitle = computed(() => (route.meta && route.meta.title) || '')
const avatarChar = computed(() => (auth.nickname || 'A').charAt(0).toUpperCase())
const isSuper = computed(() => auth.role === 'SUPER')

// 修改密码
const pwdVisible = ref(false)
const pwdLoading = ref(false)
const pwdFormRef = ref()
const pwdForm = reactive({ oldPassword: '', newPassword: '', confirmPassword: '' })
const pwdRules = {
  oldPassword: [{ required: true, message: '请输入原密码', trigger: 'blur' }],
  newPassword: [{ required: true, message: '请输入新密码', trigger: 'blur' }],
  confirmPassword: [{ required: true, message: '请再次输入新密码', trigger: 'blur' }]
}

function openChangePwd() {
  Object.assign(pwdForm, { oldPassword: '', newPassword: '', confirmPassword: '' })
  pwdVisible.value = true
}

async function handleChangePwd() {
  pwdFormRef.value.validate(async (valid) => {
    if (!valid) return
    if (pwdForm.newPassword.length < 6) {
      ElMessage.warning('新密码至少6位')
      return
    }
    if (pwdForm.newPassword !== pwdForm.confirmPassword) {
      ElMessage.warning('两次输入的新密码不一致')
      return
    }
    pwdLoading.value = true
    try {
      await changePassword({ oldPassword: pwdForm.oldPassword, newPassword: pwdForm.newPassword })
      ElMessage.success('密码修改成功，请重新登录')
      pwdVisible.value = false
      setTimeout(() => {
        auth.logout()
        router.push('/login')
      }, 800)
    } catch (e) {
      // 拦截器已提示
    } finally {
      pwdLoading.value = false
    }
  })
}

function handleCommand(cmd) {
  if (cmd === 'password') {
    openChangePwd()
  }
  if (cmd === 'logout') {
    ElMessageBox.confirm('确定要退出登录吗？', '提示', {
      confirmButtonText: '退出',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(async () => {
      try { await logout() } catch (e) {}
      auth.logout()
      router.push('/login')
    }).catch(() => {})
  }
}
</script>

<style scoped>
.layout { height: 100vh; width: 100%; }

.aside {
  background: linear-gradient(180deg, #1e4fb3 0%, #2b7cf7 100%);
  display: flex;
  flex-direction: column;
}

.logo {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 20px 20px 16px;
}
.logo-icon {
  width: 38px; height: 38px;
  border-radius: 9px;
  background: rgba(255,255,255,0.18);
  color: #fff;
  display: flex; align-items: center; justify-content: center;
}
.logo-text { display: flex; flex-direction: column; line-height: 1.2; }
.logo-title { color: #fff; font-size: 17px; font-weight: 600; letter-spacing: 1px; }
.logo-sub { color: #cfe0ff; font-size: 11px; }

.menu { border-right: none; padding: 6px 10px; }
.menu :deep(.el-menu-item) { border-radius: 8px; margin-bottom: 4px; height: 46px; }
.menu :deep(.el-menu-item.is-active) { background: rgba(255,255,255,0.22); }
.menu :deep(.el-menu-item:hover) { background: rgba(255,255,255,0.12); }

.header {
  background: #fff;
  border-bottom: 1px solid #e6edf7;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  height: 60px;
}
.page-title { font-size: 18px; font-weight: 600; color: var(--ft-text); }
.header-right { display: flex; align-items: center; gap: 22px; }
.online-tag {
  display: flex; align-items: center; gap: 7px;
  font-size: 13px; color: #3ba55d;
  padding: 5px 12px; border-radius: 20px;
  background: #eafaf1;
}
.online-tag .dot { width: 8px; height: 8px; border-radius: 50%; background: #3ba55d; animation: pulse 1.6s infinite; }
@keyframes pulse { 0%{box-shadow:0 0 0 0 rgba(59,165,93,.5);} 70%{box-shadow:0 0 0 7px rgba(59,165,93,0);} 100%{box-shadow:0 0 0 0 rgba(59,165,93,0);} }

.user-box { display: flex; align-items: center; gap: 8px; cursor: pointer; color: var(--ft-text); }
.avatar { background: var(--ft-primary); color:#fff; font-weight:600; }
.user-name { font-size: 14px; }

.main { padding: 16px; background: var(--ft-bg); overflow: auto; }
</style>