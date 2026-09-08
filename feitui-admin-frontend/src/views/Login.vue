<template>
  <div class="login-page">
    <div class="bg-decoration">
      <div class="circle c1"></div>
      <div class="circle c2"></div>
    </div>

    <div class="login-card">
      <div class="brand">
        <div class="brand-icon">
          <el-icon :size="30"><Promotion /></el-icon>
        </div>
        <h2 class="brand-title">飞推引流</h2>
        <p class="brand-sub">后台管理系统</p>
      </div>

      <div class="role-tabs">
        <div class="role-tab" :class="{ active: loginType === 'super' }" @click="loginType = 'super'">
          超级管理员登录
        </div>
        <div class="role-tab" :class="{ active: loginType === 'admin' }" @click="loginType = 'admin'">
          管理员登录
        </div>
      </div>
      <p class="role-desc">{{ roleDesc }}</p>

      <el-form ref="formRef" :model="form" :rules="rules" class="login-form" size="large">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" :prefix-icon="User" clearable autocomplete="off" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="请输入密码"
            :prefix-icon="Lock"
            show-password
            autocomplete="new-password"
            @keyup.enter="handleLogin"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-btn" :loading="loading" @click="handleLogin">
            登 录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { login } from '@/api'
import { useAuthStore } from '@/store/auth'

const router = useRouter()
const auth = useAuthStore()

const formRef = ref()
const loading = ref(false)
const form = reactive({ username: '', password: '' })
const loginType = ref('super')

const roleDesc = computed(() =>
  loginType.value === 'super'
    ? '超级管理员：可查看数据看板、在线咨询，并管理后台账号（新增/修改/删除）'
    : '管理员：可查看数据看板与在线咨询'
)

const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

async function handleLogin() {
  await formRef.value.validate(async (valid) => {
    if (!valid) return
    loading.value = true
    try {
      const res = await login({ username: form.username, password: form.password, loginType: loginType.value })
      auth.setLogin(res.data.token, res.data.username, res.data.nickname, res.data.role)
      const roleName = auth.role === 'SUPER' ? '超级管理员' : '管理员'
      ElMessage.success(`${roleName}登录成功`)
      router.push('/dashboard')
    } catch (e) {
      // 错误提示已由拦截器处理
    } finally {
      loading.value = false
    }
  })
}
</script>

<style scoped>
.login-page {
  height: 100vh;
  width: 100%;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  background: linear-gradient(135deg, #1e4fb3 0%, #4a93ff 60%, #7db3ff 100%);
}
.bg-decoration { position: absolute; inset: 0; }
.circle { position: absolute; border-radius: 50%; background: rgba(255,255,255,0.08); }
.c1 { width: 420px; height: 420px; top: -120px; right: -80px; }
.c2 { width: 340px; height: 340px; bottom: -100px; left: -60px; }

.login-card {
  width: 400px;
  background: #fff;
  border-radius: 16px;
  padding: 40px 36px 28px;
  box-shadow: 0 20px 50px rgba(0, 40, 120, 0.28);
  text-align: center;
  position: relative;
  /* 白色半透明光晕 */
}
.brand { margin-bottom: 26px; }
.brand-icon {
  width: 62px; height: 62px;
  margin: 0 auto 12px;
  border-radius: 14px;
  background: linear-gradient(135deg, #2b7cf7, #5aa2ff);
  color: #fff;
  display: flex; align-items: center; justify-content: center;
  box-shadow: 0 8px 18px rgba(43,124,247,0.35);
}
.brand-title { font-size: 24px; font-weight: 700; color: var(--ft-text); letter-spacing: 2px; }
.brand-sub { color: #8a99b2; font-size: 13px; margin-top: 4px; letter-spacing: 3px; }

.role-tabs {
  display: flex;
  background: #eef3fb;
  border-radius: 8px;
  padding: 4px;
  margin-bottom: 6px;
}
.role-tab {
  flex: 1;
  text-align: center;
  padding: 8px 0;
  font-size: 14px;
  color: var(--ft-text-2);
  cursor: pointer;
  border-radius: 6px;
  transition: all .2s;
}
.role-tab.active {
  background: #fff;
  color: var(--ft-primary);
  font-weight: 600;
  box-shadow: 0 2px 8px rgba(43,124,247,0.15);
}
.role-desc { font-size: 12px; color: #8a99b2; margin-bottom: 16px; text-align: center; }

.login-form { text-align: left; }
.login-btn { width: 100%; height: 46px; font-size: 16px; letter-spacing: 6px; margin-top: 4px; }
</style>