<template>
  <div class="page-container">
    <el-card shadow="never" class="panel">
      <template #header>
        <div class="panel-header">
          <span class="panel-title">视频管理</span>
          <div class="header-actions">
            <span class="tip-text">视频文件放在阿里云OSS，此处仅管理云端的 URL、标题与排序，保存后推广网站立即生效。</span>
            <el-button type="primary" :icon="Plus" @click="openCreate">新增视频</el-button>
          </div>
        </div>
      </template>

      <el-table :data="list" v-loading="loading" stripe>
        <el-table-column label="ID" prop="id" width="70" />
        <el-table-column label="排序" prop="sort" width="80" />
        <el-table-column label="标题" prop="title" min-width="140" />
        <el-table-column label="视频地址" prop="videoUrl" min-width="200" show-overflow-tooltip />
        <el-table-column label="封面图" prop="coverImage" min-width="150" show-overflow-tooltip>
          <template #default="{ row }">{{ row.coverImage || '（自动从视频抽帧）' }}</template>
        </el-table-column>
        <el-table-column label="时长" width="110">
          <template #default="{ row }">{{ row.duration || '—' }}</template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'info'" effect="light">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="更新时间" prop="updateTime" width="170">
          <template #default="{ row }">{{ row.updateTime || '—' }}</template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" size="small" @click="openEdit(row)">编辑</el-button>
            <el-button link type="danger" size="small" @click="handleDelete(row)">删除</el-button>
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
    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑视频' : '新增视频'" width="560px">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="视频标题（展示在推广网站卡片上）" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" :rows="2" placeholder="视频描述（可留空）" />
        </el-form-item>
        <el-form-item label="视频地址" prop="videoUrl">
          <div class="field-row">
            <el-input v-model="form.videoUrl" placeholder="https://你的bucket.oss-cn-xxx.aliyuncs.com/v1.mp4" />
            <el-button type="primary" :loading="uploading === 'video'" @click="triggerFile('video')">上传视频</el-button>
          </div>
          <div v-if="uploading === 'video'" class="upl-progress"><el-progress :percentage="uploadPercent" :stroke-width="8" /></div>
        </el-form-item>
        <el-form-item label="封面图" prop="coverImage">
          <div class="field-row">
            <el-input v-model="form.coverImage" placeholder="https://.../v1.jpg（可留空，自动从视频抽帧）" />
            <el-button type="primary" plain :loading="uploading === 'cover'" @click="triggerFile('cover')">上传封面</el-button>
          </div>
          <div v-if="uploading === 'cover'" class="upl-progress"><el-progress :percentage="uploadPercent" :stroke-width="8" /></div>
        </el-form-item>
        <el-form-item label="时长" prop="duration">
          <el-input v-model="form.duration" placeholder="可留空，展示时优先用真实时长" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" :min="0" controls-position="right" />
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
    <!-- 隐藏的本地上传文件选择 -->
    <input ref="fileInput" type="file" class="hidden-input" @change="onFileChange" />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getVideoAdminPage, createVideo, updateVideo, deleteVideo, getOssPresign } from '@/api'

const list = ref([])
const total = ref(0)
const loading = ref(false)
const query = reactive({ page: 1, size: 10 })

const dialogVisible = ref(false)
const isEdit = ref(false)
const formRef = ref()
const form = reactive({ id: null, title: '', description: '', videoUrl: '', coverImage: '', duration: '', sort: 0, status: 1 })

const rules = {
  title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
  videoUrl: [{ required: true, message: '请输入视频地址', trigger: 'blur' }]
}

// ---- 阿里云 OSS 直传：选择本地文件 -> 后端拿预签名地址 -> 前端 PUT 直传 -> 自动回填 ----
const fileInput = ref(null)
const currentWhich = ref('') // 'video' | 'cover'
const uploading = ref('') // 当前正在上传的字段，'' 表示空闲
const uploadPercent = ref(0)

function triggerFile(which) {
  currentWhich.value = which
  fileInput.value && fileInput.value.click()
}

function onFileChange(e) {
  const file = e.target.files && e.target.files[0]
  e.target.value = ''
  if (file) uploadToOss(currentWhich.value, file)
}

async function uploadToOss(which, file) {
  uploading.value = which
  uploadPercent.value = 0
  try {
    const res = await getOssPresign({ filename: file.name })
    if (res.code !== 200) {
      ElMessage.error(res.message || '获取上传地址失败')
      return
    }
    const { uploadUrl, objectUrl, contentType } = res.data
    const ok = await putToOss(uploadUrl, file, contentType, (p) => { uploadPercent.value = p })
    if (!ok) {
      ElMessage.error('上传失败：请确认 OSS 跨域(CORS)已允许本后台域名')
      return
    }
    if (which === 'video') form.videoUrl = objectUrl
    else form.coverImage = objectUrl
    ElMessage.success(which === 'video' ? '视频已上传到 OSS' : '封面已上传到 OSS')
  } catch (err) {
    ElMessage.error('上传失败：' + (err.message || err))
  } finally {
    uploading.value = ''
  }
}

function putToOss(uploadUrl, file, contentType, onProgress) {
  return new Promise((resolve) => {
    const xhr = new XMLHttpRequest()
    xhr.open('PUT', uploadUrl)
    xhr.setRequestHeader('Content-Type', contentType)
    xhr.upload.onprogress = (ev) => {
      if (ev.lengthComputable) onProgress(Math.round((ev.loaded / ev.total) * 100))
    }
    xhr.onload = () => resolve(xhr.status >= 200 && xhr.status < 300)
    xhr.onerror = () => resolve(false)
    xhr.send(file)
  })
}

async function loadList() {
  loading.value = true
  try {
    const res = await getVideoAdminPage({ page: query.page, size: query.size })
    list.value = res.data.list || []
    total.value = res.data.total || 0
  } catch (e) {} finally {
    loading.value = false
  }
}

function openCreate() {
  isEdit.value = false
  Object.assign(form, { id: null, title: '', description: '', videoUrl: '', coverImage: '', duration: '', sort: 0, status: 1 })
  dialogVisible.value = true
}

function openEdit(row) {
  isEdit.value = true
  Object.assign(form, {
    id: row.id, title: row.title, description: row.description || '',
    videoUrl: row.videoUrl, coverImage: row.coverImage || '',
    duration: row.duration || '', sort: row.sort ?? 0, status: row.status ?? 1
  })
  dialogVisible.value = true
}

function handleSave() {
  formRef.value.validate(async (valid) => {
    if (!valid) return
    const payload = { ...form }
    try {
      if (isEdit.value) {
        await updateVideo(form.id, payload)
      } else {
        await createVideo(payload)
      }
      ElMessage.success('保存成功')
      dialogVisible.value = false
      loadList()
    } catch (e) {}
  })
}

function handleDelete(row) {
  ElMessageBox.confirm(`确定要删除视频「${row.title || row.id}」吗？`, '提示', {
    confirmButtonText: '删除',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteVideo(row.id)
      ElMessage.success('删除成功')
      loadList()
    } catch (e) {}
  }).catch(() => {})
}

onMounted(loadList)
</script>

<style scoped>
.page-container { padding: 4px; }
.panel-title { font-size: 16px; font-weight: 600; color: var(--ft-text); }
.header-actions { display: flex; align-items: center; gap: 12px; }
.tip-text { font-size: 12px; color: #8a94a6; }
.pager { display: flex; justify-content: flex-end; margin-top: 16px; }
.field-row { display: flex; align-items: center; gap: 8px; width: 100%; }
.field-row .el-input { flex: 1; }
.field-row .el-button { flex-shrink: 0; }
.upl-progress { margin-top: 8px; }
.hidden-input { display: none; }
</style>