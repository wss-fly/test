<template>
  <div class="page-container">
    <el-card shadow="never" class="panel">
      <template #header>
        <div class="panel-header">
          <span class="panel-title">后台用户管理</span>
          <div class="header-actions">
            <el-input v-model="query.keyword" placeholder="搜索用户名/昵称" clearable size="default" style="width: 240px" @keyup.enter="handleSearch" @clear="handleSearch" />
            <el-button type="primary" :icon="Search" @click="handleSearch">查询</el-button>
            <el-button type="primary" :icon="Plus" @click="openCreate">新增用户</el-button>
          </div>
        </div>
      </template>

      <el-table :data="list" v-loading="loading" stripe>
        <el-table-column label="ID" prop="id" width="70" />
        <el-table-column label="用户名" prop="username" min-width="120" />
        <el-table-column label="昵称" prop="nickname" min-width="130">
          <template #default="{ row }">{{ row.nickname || '—' }}</template>
        </el-table-column>
        <el-table-column label="角色" width="110">
          <template #default="{ row }">
            <el-tag :type="row.role === 'SUPER' ? 'danger' : 'primary'" effect="light">
              {{ row.role === 'SUPER' ? '超级管理员' : (row.role === 'admin' || row.role === 'ADMIN' ? '管理员' : row.role) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'info'" effect="light">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="最近登录" prop="lastLoginTime" width="170">
          <template #default="{ row }">{{ row.lastLoginTime || '—' }}</template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createTime" width="170" />
        <el-table-column label="操作" width="230" fixed="right">
          <template #default="{ row }">
            <!-- 超级管理员唯一，不可修改、不可删除 -->
            <template v-if="row.role !== 'SUPER'">
              <el-button link type="primary" size="small" @click="openEdit(row)">编辑</el-button>
              <el-button link type="warning" size="small" @click="openResetPwd(row)">重置密码</el-button>
              <el-button link type="danger" size="small" @click="handleDelete(row)">删除</el-button>
            </template>
            <span v-else class="protected">系统保护账号</span>
          </template>
        </el-table-column>
      </el-table>

      <div class="pager">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :total="total"
          :current-page="query.page"
          :page-size="query.size"
          @current-change="(p) => { query.page = p; loadList() }"
        />
      </div>
    </el-card>

    <!-- 新增/编辑对话框 -->
    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑用户' : '新增用户'" width="480px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" :disabled="isEdit" placeholder="登录用户名" />
        </el-form-item>
        <el-form-item v-if="!isEdit" label="密码" prop="password">
          <el-input v-model="form.password" type="password" show-password placeholder="至少6位，用于登录" />
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="显示昵称" />
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="form.role" style="width: 100%">
            <el-option label="管理员" value="ADMIN" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :value="1">启用</el-radio>
            <el-radio :value="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>

    <!-- 重置密码对话框 -->
    <el-dialog v-model="pwdVisible" title="重置密码" width="420px">
      <el-form label-width="80px">
        <el-form-item label="用户名">
          <span>{{ pwdTarget ? pwdTarget.username : '' }}</span>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="newPassword" type="password" show-password placeholder="至少6位，用于登录" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="pwdVisible = false">取消</el-button>
        <el-button type="primary" @click="handleResetPwd">确认重置</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { Search, Plus } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getAdminUserPage, createAdminUser, updateAdminUser, resetAdminUserPassword, deleteAdminUser } from '@/api'

const list = ref([])
const total = ref(0)
const loading = ref(false)
const query = reactive({ page: 1, size: 10, keyword: '' })

const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref()
const form = reactive({ id: null, username: '', password: '', nickname: '', role: 'ADMIN', status: 1 })

const pwdVisible = ref(false)
const pwdTarget = ref(null)
const newPassword = ref('')

const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

async function loadList() {
  loading.value = true
  try {
    const res = await getAdminUserPage({ page: query.page, size: query.size, keyword: query.keyword || undefined })
    list.value = res.data.list || []
    total.value = res.data.total || 0
  } catch (e) {} finally {
    loading.value = false
  }
}

function handleSearch() { query.page = 1; loadList() }

function openCreate() {
  isEdit.value = false
  Object.assign(form, { id: null, username: '', password: '', nickname: '', role: 'ADMIN', status: 1 })
  dialogVisible.value = true
}

function openEdit(row) {
  isEdit.value = true
  Object.assign(form, { id: row.id, username: row.username, password: '', nickname: row.nickname, role: row.role, status: row.status })
  dialogVisible.value = true
}

function handleSave() {
  formRef.value.validate(async (valid) => {
    if (!valid) return
    try {
      if (isEdit.value) {
        await updateAdminUser(form.id, { nickname: form.nickname, role: form.role, status: form.status })
      } else {
        await createAdminUser({ username: form.username, password: form.password, nickname: form.nickname, role: form.role, status: form.status })
      }
      ElMessage.success('保存成功')
      dialogVisible.value = false
      loadList()
    } catch (e) {}
  })
}

function openResetPwd(row) {
  pwdTarget.value = row
  newPassword.value = ''
  pwdVisible.value = true
}

async function handleResetPwd() {
  if (!newPassword.value || newPassword.value.length < 6) {
    ElMessage.warning('新密码至少6位')
    return
  }
  try {
    await resetAdminUserPassword(pwdTarget.value.id, newPassword.value)
    ElMessage.success('密码已重置')
    pwdVisible.value = false
  } catch (e) {}
}

function handleDelete(row) {
  ElMessageBox.confirm(`确定删除用户「${row.username}」吗？此操作不可恢复。`, '提示', {
    type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消'
  }).then(async () => {
    try {
      await deleteAdminUser(row.id)
      ElMessage.success('删除成功')
      loadList()
    } catch (e) {}
  }).catch(() => {})
}

onMounted(loadList)
</script>

<style scoped>
.panel-header { display: flex; align-items: center; justify-content: space-between; }
.panel-title { font-size: 15px; font-weight: 600; color: var(--ft-text); }
.header-actions { display: flex; gap: 10px; }
.pager { display: flex; justify-content: flex-end; margin-top: 16px; }
.protected { font-size: 12px; color: #b0bccf; }
</style>