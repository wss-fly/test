<template>
  <div class="page-container">
    <el-card shadow="never" class="panel">
      <template #header>
        <div class="panel-header">
          <span class="panel-title">在线咨询列表（实时同步）</span>
          <div class="header-actions">
            <el-select v-model="query.status" placeholder="全部状态" clearable size="default" style="width: 140px" @change="handleSearch" @clear="handleSearch">
              <el-option label="待处理" :value="0" />
              <el-option label="已处理" :value="1" />
              <el-option label="已关闭" :value="2" />
            </el-select>
            <el-input v-model="query.keyword" placeholder="搜索姓名/电话/邮箱/需求" clearable size="default" style="width: 260px" @keyup.enter="handleSearch" @clear="handleSearch" />
            <el-button type="primary" :icon="Search" @click="handleSearch">查询</el-button>
            <el-button :icon="Refresh" @click="loadList">刷新</el-button>
            <el-divider direction="vertical" />
            <el-button type="danger" :icon="Delete" :disabled="selectedIds.length === 0" @click="handleBatchDelete">
              批量删除<template v-if="selectedIds.length">（{{ selectedIds.length }}）</template>
            </el-button>
          </div>
        </div>
      </template>

      <el-table :data="list" v-loading="loading" stripe @selection-change="onSelectionChange">
        <el-table-column type="selection" width="50" />
        <el-table-column label="咨询人" min-width="110">
          <template #default="{ row }">
            <div class="person">
              <span class="name">{{ row.name }}</span>
              <el-tag v-if="row.status === 0" type="danger" size="small" effect="dark" class="new-tag">新</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="联系方式" prop="phone" min-width="130" />
        <el-table-column label="邮箱/公司" min-width="140">
          <template #default="{ row }">
            <div class="sub">{{ row.email || '—' }}</div>
            <div class="sub">{{ row.company || '—' }}</div>
          </template>
        </el-table-column>
        <el-table-column label="咨询内容" prop="requirement" min-width="200" show-overflow-tooltip />
        <el-table-column label="平台" prop="platform" width="90" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="statusType(row.status)" effect="light">{{ statusText(row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="咨询时间" prop="createTime" width="170" />
        <el-table-column label="操作" width="220" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" size="small" @click="openDetail(row)">详情</el-button>
            <el-button v-if="row.status === 0" link type="success" size="small" @click="changeStatus(row, 1)">标记已处理</el-button>
            <el-button v-if="row.status !== 2" link type="warning" size="small" @click="changeStatus(row, 2)">关闭</el-button>
            <el-button link type="danger" size="small" @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pager">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next"
          :total="total"
          :current-page="query.page"
          :page-size="query.size"
          :page-sizes="[10, 20, 50]"
          @current-change="(p) => { query.page = p; loadList() }"
          @size-change="(s) => { query.size = s; query.page = 1; loadList() }"
        />
      </div>
    </el-card>

    <!-- 详情对话框 -->
    <el-dialog v-model="detailVisible" title="咨询详情" width="560px">
      <el-descriptions :column="1" border>
        <el-descriptions-item label="咨询人">{{ detail.name }}</el-descriptions-item>
        <el-descriptions-item label="联系方式">{{ detail.phone }}</el-descriptions-item>
        <el-descriptions-item label="邮箱">{{ detail.email || '—' }}</el-descriptions-item>
        <el-descriptions-item label="公司">{{ detail.company || '—' }}</el-descriptions-item>
        <el-descriptions-item label="目标平台">{{ detail.platform || '—' }}</el-descriptions-item>
        <el-descriptions-item label="咨询时间">{{ detail.createTime }}</el-descriptions-item>
        <el-descriptions-item label="咨询内容">
          <div class="requirement">{{ detail.requirement || '—' }}</div>
        </el-descriptions-item>
      </el-descriptions>
      <div class="remark-box">
        <div class="remark-label">处理备注</div>
        <el-input v-model="remark" type="textarea" :rows="2" placeholder="填写处理备注" />
      </div>
      <template #footer>
        <el-button @click="detailVisible = false">取消</el-button>
        <el-button type="primary" @click="saveRemark">保存备注</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'
import { Search, Refresh, Delete } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getContactPage, updateContactStatus, updateContactRemark, deleteContact, deleteContactBatch } from '@/api'

const list = ref([])
const total = ref(0)
const loading = ref(false)
const query = reactive({ page: 1, size: 10, keyword: '', status: null })
const selectedIds = ref([])

function onSelectionChange(rows) {
  selectedIds.value = rows.map((r) => r.id)
}

const detailVisible = ref(false)
const detail = ref({})
const remark = ref('')
const detailId = ref(null)

let timer = null

async function loadList() {
  loading.value = true
  try {
    const res = await getContactPage({
      page: query.page,
      size: query.size,
      keyword: query.keyword || undefined,
      status: query.status === null || query.status === '' ? undefined : query.status
    })
    list.value = res.data.list || []
    total.value = res.data.total || 0
  } catch (e) {} finally {
    loading.value = false
  }
}

function handleSearch() {
  query.page = 1
  loadList()
}

function openDetail(row) {
  detail.value = { ...row }
  remark.value = row.remark || ''
  detailId.value = row.id
  detailVisible.value = true
}

async function saveRemark() {
  await updateContactRemark(detailId.value, remark.value)
  ElMessage.success('备注已保存')
  detailVisible.value = false
  loadList()
}

async function changeStatus(row, status) {
  await updateContactStatus(row.id, status)
  ElMessage.success('状态已更新')
  loadList()
}

function handleDelete(row) {
  ElMessageBox.confirm(`确定删除「${row.name}」的咨询记录吗？`, '提示', {
    type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消'
  }).then(async () => {
    await deleteContact(row.id)
    ElMessage.success('删除成功')
    loadList()
  }).catch(() => {})
}

function handleBatchDelete() {
  const n = selectedIds.value.length
  if (!n) return
  ElMessageBox.confirm(`确定删除选中的 ${n} 条咨询记录吗？删除后不可恢复。`, '批量删除', {
    type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消'
  }).then(async () => {
    await deleteContactBatch(selectedIds.value)
    ElMessage.success('批量删除成功')
    selectedIds.value = []
    loadList()
  }).catch(() => {})
}

function statusText(s) {
  return { 0: '待处理', 1: '已处理', 2: '已关闭' }[s] ?? '未知'
}
function statusType(s) {
  return { 0: 'danger', 1: 'success', 2: 'info' }[s] ?? 'info'
}

onMounted(() => {
  loadList()
  timer = setInterval(loadList, 8000)
})
onBeforeUnmount(() => clearInterval(timer))
</script>

<style scoped>
.panel-header { display: flex; align-items: center; justify-content: space-between; }
.panel-title { font-size: 15px; font-weight: 600; color: var(--ft-text); }
.header-actions { display: flex; gap: 10px; }
.person { display: flex; align-items: center; gap: 6px; }
.person .name { font-weight: 600; }
.new-tag { animation: blink 1.6s infinite; }
@keyframes blink { 50% { opacity: 0.55; } }
.sub { color: var(--ft-text-2); font-size: 12px; }
.pager { display: flex; justify-content: flex-end; margin-top: 16px; }
.requirement { white-space: pre-wrap; color: var(--ft-text); }
.remark-box { margin-top: 16px; }
.remark-label { font-size: 13px; color: var(--ft-text); margin-bottom: 8px; font-weight: 600; }
</style>