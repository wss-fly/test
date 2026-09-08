// 网站访问数据上报工具：将访问量、来源、设备信息实时上报至后台管理系统
// 用于后台看板统计(总访问/今日访问/实时在线/访问来源分析)
const TRACK_URL = import.meta.env.VITE_TRACK_URL || 'http://localhost:9090/api/visit/report'
const HEARTBEAT_MS = 60000 // 心跳间隔，用于实时在线判定

function genId(prefix) {
  const rand = () => Math.random().toString(36).slice(2, 10) + Date.now().toString(36)
  return prefix + '_' + rand() + rand()
}

function getVisitorId() {
  let id = localStorage.getItem('ft_visitor_id')
  if (!id) {
    id = 'visitor_' + (crypto.randomUUID ? crypto.randomUUID().replace(/-/g, '').slice(0, 20) : genId('v'))
    localStorage.setItem('ft_visitor_id', id)
  }
  return id
}

function getSessionId() {
  let id = sessionStorage.getItem('ft_session_id')
  if (!id) {
    id = 'session_' + (crypto.randomUUID ? crypto.randomUUID().replace(/-/g, '').slice(0, 20) : genId('s'))
    sessionStorage.setItem('ft_session_id', id)
  }
  return id
}

// 从 URL 参数或 referrer 中识别访问来源
function resolveSource() {
  const params = new URLSearchParams(window.location.search)
  const utm = params.get('utm_source') || params.get('source')
  if (utm) return utm
  try {
    const ref = new URL(document.referrer)
    const host = ref.hostname.toLowerCase()
    if (host.includes('facebook')) return 'facebook'
    if (host.includes('instagram')) return 'instagram'
    if (host.includes('telegram') || host.includes('t.me')) return 'telegram'
    if (host.includes('whatsapp') || host.includes('wa.me')) return 'whatsapp'
    if (/google|bing|baidu|sogou/.test(host)) return 'engine'
    if (host.includes('twitter') || host.includes('x.com')) return 'twitter'
    if (host.includes('tiktok')) return 'tiktok'
  } catch (e) {}
  return 'direct'
}

let started = false

function send(event) {
  const payload = {
    event,
    visitorId: getVisitorId(),
    sessionId: getSessionId(),
    page: window.location.pathname + window.location.search,
    referrer: document.referrer || '',
    source: resolveSource(),
    ua: navigator.userAgent
  }
  // 静默上报，失败不打扰用户
  try {
    if (navigator.sendBeacon) {
      navigator.sendBeacon(TRACK_URL, new Blob([JSON.stringify(payload)], { type: 'application/json' }))
    } else {
      fetch(TRACK_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
        keepalive: true
      }).catch(() => {})
    }
  } catch (e) {}
}

export function reportPageView() {
  send('pageview')
}

export function initTracker() {
  if (started) return
  started = true
  // 首次加载上报
  window.addEventListener('load', () => reportPageView())
  // 周期心跳，保持在线状态
  setInterval(() => send('heartbeat'), HEARTBEAT_MS)
  // 页面关闭前上报最后活跃
  window.addEventListener('beforeunload', () => send('heartbeat'))
}

export default { initTracker, reportPageView }