<template>
  <div class="page-container dashboard">
    <!-- 顶部指标卡片 -->
    <el-row :gutter="16">
      <el-col :span="6" v-for="card in statCards" :key="card.key">
        <el-card class="stat-card" shadow="never">
          <div class="stat-inner">
            <div class="stat-icon" :style="{ background: card.bg, color: card.color }">
              <el-icon :size="26"><component :is="card.icon" /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">
                <div class="num">{{ card.value }}</div>
                <div class="delta" v-if="card.deltaText">
                  <span class="delta-label">{{ card.deltaLabel }}</span>
                  <span class="delta-val">{{ card.deltaText }}</span>
                </div>
              </div>
              <div class="stat-label">{{ card.label }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表行 -->
    <el-row :gutter="16" class="chart-row">
      <el-col :span="16">
        <el-card shadow="never" class="panel">
          <template #header>
            <div class="panel-header">
              <span class="panel-title">访问与咨询趋势（近7天）</span>
              <el-radio-group v-model="trendType" size="small">
                <el-radio-button label="pv">浏览量</el-radio-button>
                <el-radio-button label="uv">访客数</el-radio-button>
                <el-radio-button label="contacts">咨询量</el-radio-button>
              </el-radio-group>
            </div>
          </template>
          <div ref="trendRef" class="chart"></div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never" class="panel">
          <template #header>
            <div class="panel-header"><span class="panel-title">访问来源分析</span></div>
          </template>
          <div ref="sourceRef" class="chart"></div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 实时在线访客 -->
    <el-card shadow="never" class="panel online-panel">
      <template #header>
        <div class="panel-header">
          <span class="panel-title">实时在线访客</span>
          <span class="online-count">当前在线 <b class="blue">{{ overview.onlineUsers ?? 0 }}</b> 人</span>
        </div>
      </template>
      <el-table :data="onlineList" v-loading="onlineLoading" height="300" size="default">
        <el-table-column label="访客标识" prop="visitor_id" min-width="150" show-overflow-tooltip />
        <el-table-column label="渠道" prop="channel" width="110">
          <template #default="{ row }">
            <el-tag :type="channelTag(row.channel)" effect="light">{{ row.channel || '未知' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="设备" prop="device" width="90" />
        <el-table-column label="浏览器" prop="browser" width="90" />
        <el-table-column label="落地页" prop="landing_page" min-width="160" show-overflow-tooltip />
        <el-table-column label="浏览量" prop="pv" width="80" align="center" />
        <el-table-column label="最近活跃" prop="last_active" width="170" />
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onBeforeUnmount, watch } from 'vue'
import * as echarts from 'echarts'
import { getOverview, getTrend, getSource, getOnline } from '@/api'

const trendRef = ref()
const sourceRef = ref()
const trendType = ref('pv')

const overview = reactive({
  totalVisits: '-', todayVisits: '-', onlineUsers: 0, todayContacts: '-',
  todayUV: '-', yesterdayVisits: '-', totalUV: '-', totalContacts: '-'
})
const onlineList = ref([])
const onlineLoading = ref(false)

let trendChart = null
let sourceChart = null
let overviewTimer = null
let onlineTimer = null
let slowTimer = null

const statCards = reactive([
  { key: 'totalVisits', label: '总访问量', value: '-', icon: 'View', color: '#2b7cf7', bg: '#e8f1ff', deltaLabel: '独立访客', deltaText: '-' },
  { key: 'todayVisits', label: '今日访问量', value: '-', icon: 'TrendCharts', color: '#16a34a', bg: '#e9f9f0', deltaLabel: '今日访客', deltaText: '-' },
  { key: 'onlineUsers', label: '实时在线人数', value: 0, icon: 'User', color: '#ea580c', bg: '#fff3e8', deltaLabel: '在线监控', deltaText: '' },
  { key: 'todayContacts', label: '今日咨询量', value: '-', icon: 'ChatDotSquare', color: '#9333ea', bg: '#f5ecff', deltaLabel: '累计咨询', deltaText: '-' }
])

function updateCards() {
  statCards[0].value = overview.totalVisits ?? 0
  statCards[0].deltaText = overview.totalUV ?? 0
  statCards[1].value = overview.todayVisits ?? 0
  statCards[1].deltaText = overview.todayUV ?? 0
  statCards[2].value = overview.onlineUsers ?? 0
  statCards[2].deltaText = ''
  statCards[3].value = overview.todayContacts ?? 0
  statCards[3].deltaText = overview.totalContacts ?? 0
}

async function loadOverview() {
  try {
    const res = await getOverview()
    Object.assign(overview, res.data)
    updateCards()
  } catch (e) {}
}

async function loadSlow() {
  try {
    const [trendRes, sourceRes] = await Promise.all([getTrend(7), getSource()])
    renderTrend(trendRes.data)
    renderSource(sourceRes.data)
  } catch (e) {}
}

async function loadOnline() {
  onlineLoading.value = true
  try {
    const res = await getOnline()
    onlineList.value = res.data || []
  } catch (e) {} finally {
    onlineLoading.value = false
  }
}

function renderTrend(data) {
  if (!trendChart) trendChart = echarts.init(trendRef.value)
  const dates = data.map((d) => d.date.slice(5))
  const field = trendType.value
  const series = {
    pv: { name: '访问量', data: data.map((d) => d.pv), color: '#2b7cf7' },
    uv: { name: '访客数', data: data.map((d) => d.uv), color: '#16a34a' },
    contacts: { name: '咨询量', data: data.map((d) => d.contacts), color: '#9333ea' }
  }
  const s = series[field]
  trendChart.setOption({
    tooltip: { trigger: 'axis' },
    grid: { left: 45, right: 20, top: 30, bottom: 30 },
    xAxis: { type: 'category', data: dates, axisLine: { lineStyle: { color: '#d9e5f5' } }, axisLabel: { color: '#6b7a90' } },
    yAxis: { type: 'value', splitLine: { lineStyle: { color: '#eef3fb' } }, axisLabel: { color: '#6b7a90' } },
    series: [{
      name: s.name,
      type: 'line',
      smooth: true,
      data: s.data,
      itemStyle: { color: s.color },
      areaStyle: { opacity: 0.12, color: s.color },
      lineStyle: { width: 3, color: s.color },
      symbolSize: 6
    }]
  }, true)
}

function renderSource(data) {
  if (!sourceChart) sourceChart = echarts.init(sourceRef.value)
  sourceChart.setOption({
    tooltip: { trigger: 'item', formatter: '{b}: {c} ({d}%)' },
    legend: { bottom: 0, textStyle: { color: '#6b7a90', fontSize: 12 } },
    series: [{
      type: 'pie',
      radius: ['45%', '68%'],
      center: ['50%', '46%'],
      label: { color: '#6b7a90' },
      itemStyle: { borderColor: '#fff', borderWidth: 2 },
      data: data.map((it) => ({ name: it.name, value: it.value }))
    }]
  }, true)
}

function channelTag(channel) {
  const map = { Facebook: 'danger', Telegram: 'primary', WhatsApp: 'success', 搜索引擎: 'warning', Instagram: 'info', 直接访问: '' }
  return map[channel] ?? ''
}

watch(trendType, () => {
  loadSlow()
})

function resizeAll() {
  trendChart && trendChart.resize()
  sourceChart && sourceChart.resize()
}

onMounted(() => {
  loadOverview()
  loadSlow()
  loadOnline()
  overviewTimer = setInterval(loadOverview, 5000)
  onlineTimer = setInterval(loadOnline, 8000)
  slowTimer = setInterval(loadSlow, 30000)
  window.addEventListener('resize', resizeAll)
})

onBeforeUnmount(() => {
  clearInterval(overviewTimer)
  clearInterval(onlineTimer)
  clearInterval(slowTimer)
  window.removeEventListener('resize', resizeAll)
  trendChart && trendChart.dispose()
  sourceChart && sourceChart.dispose()
})
</script>

<style scoped>
.dashboard { padding: 16px; }
.stat-card { margin-bottom: 16px; }
.stat-inner { display: flex; align-items: center; gap: 16px; }
.stat-icon {
  width: 56px; height: 56px; border-radius: 12px;
  display: flex; align-items: center; justify-content: center; flex-shrink: 0;
}
.stat-info { flex: 1; min-width: 0; }
.stat-value { display: flex; align-items: baseline; gap: 12px; }
.num { font-size: 28px; font-weight: 700; color: var(--ft-text); line-height: 1.1; }
.delta { font-size: 12px; color: var(--ft-text-2); }
.delta-label { margin-right: 4px; }
.delta-val { color: var(--ft-primary); font-weight: 600; }
.stat-label { color: var(--ft-text-2); font-size: 13px; margin-top: 4px; }

.chart-row { margin-bottom: 16px; }
.panel { margin-bottom: 16px; }
.panel-header { display: flex; align-items: center; justify-content: space-between; }
.panel-title { font-size: 15px; font-weight: 600; color: var(--ft-text); }
.chart { height: 320px; width: 100%; }
.online-count { font-size: 13px; color: var(--ft-text-2); }
.online-count .blue { color: var(--ft-primary); font-size: 18px; margin: 0 2px; }
</style>