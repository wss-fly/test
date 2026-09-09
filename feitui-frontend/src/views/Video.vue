<template>
  <div class="video-page">
    <NavBar />
    
    <!-- Page Hero -->
    <section class="page-hero">
      <div class="hero-bg"></div>
      <div class="hero-content">
        <h1>{{ t('video.mainTitle') }}</h1>
        <div class="breadcrumb">
          <router-link to="/">{{ t('video.breadcrumbHome') }}</router-link>
          <span class="separator">›</span>
          <span class="current">{{ t('video.breadcrumbCurrent') }}</span>
        </div>
      </div>
    </section>

    <!-- Main Video Player -->
    <section class="main-video-section">
      <div class="section-container">
        <div class="video-player-main">
          <div class="player-wrapper"
               @mouseenter="stopAutoPlay" @mouseleave="startAutoPlay"
               @touchstart.passive="onTouchStart" @touchend.passive="onTouchEnd">
            <!-- 轮播滑动视口：所有视频横向排列，整体用 translateX 平滑滑动 -->
            <div class="hero-slide-viewport">
              <div class="hero-slide-track" :style="slideTrackStyle">
                <div v-for="(v, i) in videoList" :key="i" class="hero-slide" @click="openVideo(i)">
                  <div class="player-placeholder" :style="slideCoverStyle(i)">
                    <div class="hero-shade"></div>
                    <div class="play-btn">
                      <el-icon :size="48"><VideoPlay /></el-icon>
                    </div>
                    <div class="player-overlay">
                      <h3>{{ v.title }}</h3>
                      <p>{{ v.description }}</p>
                    </div>
                    <span class="main-video-duration">{{ displayDuration(v, i) }}</span>
                  </div>
                </div>
              </div>
            </div>

            <!-- 轮播左右箭头 -->
            <button class="carousel-arrow prev" @click.stop="prevVideo">
              <el-icon><ArrowLeft /></el-icon>
            </button>
            <button class="carousel-arrow next" @click.stop="nextVideo">
              <el-icon><ArrowRight /></el-icon>
            </button>

            <!-- 轮播指示点 -->
            <div class="carousel-dots">
              <span
                v-for="(v, i) in videoList"
                :key="i"
                class="carousel-dot"
                :class="{ active: i === currentIndex }"
                @click="goToVideo(i)"
              ></span>
            </div>
          </div>
          <div class="player-controls">
            <div class="control-info">
              <h3>{{ currentVideo.title }}</h3>
              <span class="control-desc">{{ currentVideo.description }}</span>
            </div>
            <div class="control-actions">
              <button class="action-btn" @click="playMainVideo">
                <el-icon><VideoPlay /></el-icon>
                <span>{{ t('video.playBtn') }}</span>
              </button>
              <router-link to="/contact" class="action-btn primary">
                <el-icon><Calendar /></el-icon>
                <span>{{ t('video.bookBtn') }}</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Video List -->
    <section class="video-list-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('video.videoListTitle') }}</h2>
          <p>{{ t('video.videoListDesc') }}</p>
        </div>
        
        <div class="video-grid">
          <div v-for="(video, index) in videoList" :key="index"
               class="video-card"
               :class="{ active: currentIndex === index }"
               @click="openVideo(index)">
            <div class="video-thumb">
              <div
                class="thumb-cover"
                :style="coverStyle(index)">
                <div class="cover-shade"></div>
                <el-icon class="play-icon"><VideoPlay /></el-icon>
              </div>
              <!-- 隐藏的封面图片：检测本地 jpg 是否存在，失败时自动回退 -->
              <img
                class="cover-detector"
                :src="video.poster"
                @load="onCoverLoaded(index, video.poster)"
                @error="onCoverDetectorError(index, video)"
                loading="lazy"
                alt=""
              />
              <!-- 隐藏的抽帧视频元素：仅在需要抽帧时才写入 src（用于封面） -->
              <video
                v-if="pendingExtract[index]"
                :ref="(el) => setExtractVideoRef(index, el)"
                class="extract-video"
                :src="video.videoSrc"
                preload="metadata"
                muted
                playsinline
                @loadeddata="onVideoLoadedForFrame(index)"
                @error="onExtractVideoError(index, video)"
              ></video>
              <span class="video-duration">{{ displayDuration(video, index) }}</span>
              <span class="video-badge">{{ video.badge }}</span>
            </div>
            <div class="video-info">
              <h4>{{ video.title }}</h4>
              <p>{{ video.description }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Feature Walkthrough -->
    <section class="walkthrough-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('video.walkthroughTitle') }}</h2>
          <p>{{ t('video.walkthroughDesc') }}</p>
        </div>
        
        <div class="walkthrough-steps">
          <div v-for="(step, index) in walkthroughSteps" :key="index" 
               class="step-card"
               :class="{ completed: index < 2 }">
            <div class="step-number">{{ index + 1 }}</div>
            <div class="step-content">
              <h4>{{ step.title }}</h4>
              <p>{{ step.desc }}</p>
            </div>
            <el-icon v-if="index < 2" class="check-mark"><CircleCheck /></el-icon>
            <el-icon v-else class="play-mark"><VideoPlay /></el-icon>
          </div>
        </div>
      </div>
    </section>

    <!-- FAQ Section -->
    <section class="faq-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('video.faqTitle') }}</h2>
        </div>
        
        <div class="faq-list">
          <el-collapse v-model="activeFaq" class="cyber-collapse">
            <el-collapse-item v-for="(faq, index) in faqs" :key="index" :name="index">
              <template #title>
                <span class="faq-question">{{ faq.question }}</span>
              </template>
              <div class="faq-answer">{{ faq.answer }}</div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </div>
    </section>

    <!-- CTA -->
    <section class="cta-section">
      <div class="section-container">
        <div class="cta-card">
          <h2>{{ t('video.ctaFinalTitle') }}</h2>
          <p>{{ t('video.ctaFinalDesc') }}</p>
          <div class="cta-actions">
            <router-link to="/contact" class="cta-btn primary">{{ t('video.ctaBook') }}</router-link>
            <router-link to="/custom" class="cta-btn outline">{{ t('video.ctaCustom') }}</router-link>
          </div>
        </div>
      </div>
    </section>

    <Footer />

    <!-- 共享的隐藏视频元素：串行读取各卡片视频的真实播放时长（preload=metadata 读完即止，不产生在途请求，避免 ERR_ABORTED 噪音） -->
    <video ref="durationProbeRef" class="duration-probe" preload="metadata" muted playsinline></video>

    <!-- Video Player Dialog -->
    <el-dialog
      v-model="showVideoDialog"
      :title="playingVideo?.title || ''"
      width="860px"
      class="video-player-dialog"
      :close-on-click-modal="true"
      destroy-on-close
      align-center
      @close="handleDialogClose">
      <div class="dialog-video-wrapper">
        <video
          v-if="activeVideoSrc"
          ref="videoRef"
          class="dialog-video"
          controls
          playsinline
          preload="metadata"
          :poster="activePoster"
          @loadedmetadata="onDialogVideoReady"
          @error="onVideoError">
          <source :src="activeVideoSrc" type="video/mp4" />
          您的浏览器不支持 HTML5 视频标签。
        </video>
        <div class="dialog-video-buffering" v-show="activeVideoSrc && !videoReady">
          <span class="spinner"></span>
          <span>视频加载中，请稍候...</span>
        </div>
      </div>
      <div class="dialog-video-loading-tip" v-if="!activeVideoSrc">
        正在加载视频...
      </div>
      <div v-if="playingVideo" class="dialog-video-meta">
        <h4>{{ playingVideo.title }}</h4>
        <p>{{ playingVideo.description }}</p>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <span class="dialog-tip">提示：若无声音，请点击视频控件中的音量按钮开启</span>
          <el-button type="primary" @click="showVideoDialog = false">关闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted, onBeforeUnmount } from 'vue'
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import { VideoPlay, Calendar, CircleCheck, ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n

const currentIndex = ref(0)
const activeFaq = ref([0])
const showVideoDialog = ref(false)
const playingVideo = ref(null)
const videoRef = ref(null)
// 弹窗视频是否就绪（打开时先展示封面，元数据加载完成才自动播放，避免点开即解码大视频导致卡顿）
const videoReady = ref(false)

const currentVideo = computed(() => videoList.value[currentIndex.value])

// 视频源（支持两种方式：1.放入 public/videos/ 下的本地文件，用 /videos/xxx.mp4 引用；2.填远程 HTTP 链接）
// 本地视频使用说明：把 mp4 文件复制到 feitui-frontend/public/videos/ 目录，例如命名为 1.mp4、2.mp4 ...
// 如果没有本地视频，将默认使用远程示例视频（可随时替换）
const LOCAL_VIDEO_PREFIX = '/videos/'
const DEMO_FALLBACK = {
  1: { src: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4',
       poster: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg' },
  2: { src: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4',
       poster: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg' },
  3: { src: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4',
       poster: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg' },
  4: { src: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4',
       poster: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg' },
  5: { src: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4',
       poster: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg' },
  6: { src: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4',
       poster: 'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerJoyrides.jpg' }
}

const videoList = computed(() => [
  {
    title: t('video.video1Title'),
    description: t('video.video1Desc'),
    duration: t('video.video1Duration'),
    badge: t('video.video1Badge'),
    videoSrc: `${LOCAL_VIDEO_PREFIX}1.mp4`,
    fallbackSrc: DEMO_FALLBACK['1'].src,
    poster: `${LOCAL_VIDEO_PREFIX}1.jpg`,
    fallbackPoster: DEMO_FALLBACK['1'].poster
  },
  {
    title: t('video.video2Title'),
    description: t('video.video2Desc'),
    duration: t('video.video2Duration'),
    badge: t('video.video2Badge'),
    videoSrc: `${LOCAL_VIDEO_PREFIX}2.mp4`,
    fallbackSrc: DEMO_FALLBACK['2'].src,
    poster: `${LOCAL_VIDEO_PREFIX}2.jpg`,
    fallbackPoster: DEMO_FALLBACK['2'].poster
  },
  {
    title: t('video.video3Title'),
    description: t('video.video3Desc'),
    duration: t('video.video3Duration'),
    badge: t('video.video3Badge'),
    videoSrc: `${LOCAL_VIDEO_PREFIX}3.mp4`,
    fallbackSrc: DEMO_FALLBACK['3'].src,
    poster: `${LOCAL_VIDEO_PREFIX}3.jpg`,
    fallbackPoster: DEMO_FALLBACK['3'].poster
  },
  {
    title: t('video.video4Title'),
    description: t('video.video4Desc'),
    duration: t('video.video4Duration'),
    badge: t('video.video4Badge'),
    videoSrc: `${LOCAL_VIDEO_PREFIX}4.mp4`,
    fallbackSrc: DEMO_FALLBACK['4'].src,
    poster: `${LOCAL_VIDEO_PREFIX}4.jpg`,
    fallbackPoster: DEMO_FALLBACK['4'].poster
  },
  {
    title: t('video.video5Title'),
    description: t('video.video5Desc'),
    duration: t('video.video5Duration'),
    badge: t('video.video5Badge'),
    videoSrc: `${LOCAL_VIDEO_PREFIX}5.mp4`,
    fallbackSrc: DEMO_FALLBACK['5'].src,
    poster: `${LOCAL_VIDEO_PREFIX}5.jpg`,
    fallbackPoster: DEMO_FALLBACK['5'].poster
  },
  {
    title: t('video.video6Title'),
    description: t('video.video6Desc'),
    duration: t('video.video6Duration'),
    badge: t('video.video6Badge'),
    videoSrc: `${LOCAL_VIDEO_PREFIX}6.mp4`,
    fallbackSrc: DEMO_FALLBACK['6'].src,
    poster: `${LOCAL_VIDEO_PREFIX}6.jpg`,
    fallbackPoster: DEMO_FALLBACK['6'].poster
  }
])

const walkthroughSteps = computed(() => [
  { title: t('video.walkthroughStep1Title'), desc: t('video.walkthroughStep1Desc') },
  { title: t('video.walkthroughStep2Title'), desc: t('video.walkthroughStep2Desc') },
  { title: t('video.walkthroughStep3Title'), desc: t('video.walkthroughStep3Desc') },
  { title: t('video.walkthroughStep4Title'), desc: t('video.walkthroughStep4Desc') }
])

const faqs = computed(() => [
  { 
    question: t('video.faq1Question'), 
    answer: t('video.faq1Answer')
  },
  { 
    question: t('video.faq2Question'), 
    answer: t('video.faq2Answer')
  },
  { 
    question: t('video.faq3Question'), 
    answer: t('video.faq3Answer')
  },
  { 
    question: t('video.faq4Question'), 
    answer: t('video.faq4Answer')
  }
])

// 使用的实际视频源 + 海报（初始化用本地路径，出错后自动切换为远程示例）
const activeVideoSrc = ref('')
const activePoster = ref('')

// —— 视频卡片封面管理 ——
// 每个卡片当前使用的封面 URL 或 dataURL
const coverData = reactive(new Map())  // key: index, value: url|dataURL
// 封面加载状态：'loading' | 'jpg' | 'fallback' | 'extracted' | 'failed'
const coverStatus = reactive(new Map())
// 标记某个卡片是否需要进行"从mp4抽帧"（本地 jpg 且 fallback 也失败时启用）
const pendingExtract = reactive({})
// 抽帧用 video 元素引用
const extractVideoRefs = reactive(new Map())

// —— 封面抽帧串行队列 ——
// 本地视频是超大体量（约100MB/个）。若 6 个卡片同时 seek 抽帧，会同时解码多路超大视频，
// 导致主线程、磁盘瞬间打满，页面刷新/首次进入严重卡顿。
// 因此把"封面抽帧"改成串行：同一时间只对一个视频 seek+drawImage，完成后错开一点再处理下一个。
const extractQueue = []
let extracting = false
let extractingIndex = null

const pumpExtract = () => {
  if (extracting) return
  // 跳过已经拿到封面的索引（避免重复或已完成项残留）
  while (extractQueue.length && coverData.has(extractQueue[0])) extractQueue.shift()
  const next = extractQueue.shift()
  if (next == null) return
  extracting = true
  extractingIndex = next
  pendingExtract[next] = true  // 渲染该卡片的抽帧 video 元素
}

const enqueueExtract = (index) => {
  extractQueue.push(index)
  pumpExtract()
}

// 一次抽帧结束（成功或失败）后推进队列；错开 150ms 给浏览器喘息，避免高峰
const finishExtract = (index) => {
  try { extractVideoRefs.get(index)?.pause() } catch (e) {}
  pendingExtract[index] = false
  if (extractingIndex === index) {
    extractingIndex = null
    extracting = false
    setTimeout(pumpExtract, 150)
  }
}

const setExtractVideoRef = (index, el) => {
  if (el) extractVideoRefs.set(index, el)
  else extractVideoRefs.delete(index)
}

// —— 真实视频时长 ——
// 每张卡片对应视频的真实播放时长（秒），由"抽帧用的 video"在加载时顺带读取，避免额外的隐藏 video 元素
const videoDurations = reactive(new Map())  // key: index, value: number(秒)

// 秒 → mm:ss
const formatDuration = (seconds) => {
  if (seconds == null || !isFinite(seconds) || seconds <= 0) return null
  const m = Math.floor(seconds / 60)
  const s = Math.floor(seconds % 60)
  return `${m}:${String(s).padStart(2, '0')}`
}

// 卡片时长显示：优先真实时长，未加载到则回退到翻译的时长文案
const displayDuration = (video, index) => {
  const real = videoDurations.get(index)
  if (real) return formatDuration(real)
  return video.duration
}

// —— 真实时长探测 ——
// 用一个共享的隐藏 <video> 串行读取各卡片视频的真实时长，使右下角时长与真实播放时长一致。
// preload="metadata" 只读元数据，读完即无在途请求，切换下一个 src 时不会产生 ERR_ABORTED 噪音。
const durationProbeRef = ref(null)
let probeIndex = 0

const probeDurationMeta = () => {
  const v = durationProbeRef.value
  if (!v) return
  // 跳过已经有真实时长的卡片
  while (probeIndex < videoList.value.length && videoDurations.has(probeIndex)) probeIndex++
  if (probeIndex >= videoList.value.length) {
    v.onloadedmetadata = null
    v.onerror = null
    try { v.removeAttribute('src') } catch (e) {}
    return
  }
  const i = probeIndex
  v.onloadedmetadata = () => {
    if (isFinite(v.duration) && v.duration > 0) videoDurations.set(i, Math.floor(v.duration))
    probeIndex++
    probeDurationMeta() // 读下一张
  }
  v.onerror = () => {
    // 本地文件缺失等异常 → 跳过，用翻译兜底时长
    probeIndex++
    probeDurationMeta()
  }
  v.src = videoList.value[i].videoSrc
}

// 给卡片返回封面 style（背景图）
const coverStyle = (index) => {
  const url = coverData.get(index)
  if (url) {
    return {
      backgroundImage: `url("${url}")`,
      backgroundSize: 'cover',
      backgroundPosition: 'center',
      backgroundRepeat: 'no-repeat'
    }
  }
  // 无封面时保留默认占位样式
  return {}
}

// —— 封面抽帧结果缓存（localStorage）：首次抽帧后缓存，二次访问秒开 ——
const COVER_CACHE_KEY = (i) => `feitui_video_cover_${i}`
const loadCoverCache = (index) => {
  try { return localStorage.getItem(COVER_CACHE_KEY(index)) || null } catch (e) { return null }
}
const cacheCover = (index, dataUrl) => {
  try { localStorage.setItem(COVER_CACHE_KEY(index), dataUrl) } catch (e) { /* 超限等异常忽略 */ }
}

// 本地 jpg 封面加载成功 → 使用它
const onCoverLoaded = (index, url) => {
  coverData.set(index, url)
  coverStatus.set(index, 'jpg')
}

// 本地 jpg 不存在 → 优先读取缓存抽帧图，没有再从本地 mp4 自动抽帧
// （本地 mp4 比远程封面更快更稳定，避免外网慢导致封面长时间空白）
// 本地 jpg 不存在 → 探测本地视频是否真实存在：
// 注意 dev 环境 Vite 对缺失静态资源会返回 index.html(200, text/html)，而非 404，
// 因此不能用 HTTP 状态、也不能靠 <video> 的 error 判断，必须检查响应 Content-Type。
const onCoverDetectorError = async (index, video) => {
  const current = coverStatus.get(index)
  // 如果已经试过 fallback 或已经抽帧，不要再重试
  if (current && current !== 'loading') return
  // 探测本地视频的真实存在性：Content-Type 以 video/ 开头才算存在
  let exists = false
  try {
    const res = await fetch(video.videoSrc, { method: 'HEAD' })
    const type = (res.headers.get('content-type') || '').toLowerCase()
    exists = type.startsWith('video/')
  } catch (e) { exists = false }
  if (!exists) {
    // 本地视频已删除 → 撤销缓存封面，仅保留占位（不再显示旧封面/远程兜底）
    try { localStorage.removeItem(COVER_CACHE_KEY(index)) } catch (e) {}
    coverData.delete(index)
    coverStatus.set(index, 'failed')
    return
  }
  // 视频存在：1) 命中缓存 → 秒开；2) 无缓存 → 进入抽帧队列（串行处理大视频）
  const cached = loadCoverCache(index)
  if (cached) {
    coverData.set(index, cached)
    coverStatus.set(index, 'extracted')
    return
  }
  coverStatus.set(index, 'extracting')
  enqueueExtract(index)
}

// 抽帧用的 video 元素 loadeddata 触发 → 抓第 0.1s 帧生成图片
const onVideoLoadedForFrame = (index) => {
  const v = extractVideoRefs.get(index)
  if (!v) {
    finishExtract(index)
    return
  }
  // 顺带记录该卡片的真实播放时长（抽帧 video 已加载元数据），无需额外隐藏 video
  if (isFinite(v.duration) && v.duration > 0) {
    videoDurations.set(index, Math.floor(v.duration))
  }
  try {
    // 跳到视频 0.1s 位置以便拿到画面
    v.currentTime = Math.min(0.1, v.duration || 0.5)
    v.onseeked = () => {
      try {
        const canvas = document.createElement('canvas')
        // 按视频原始宽高比抽帧，上限 1280 宽，兼顾主视频大图清晰与缓存体积
        const rawW = v.videoWidth || 1280
        const rawH = v.videoHeight || 720
        let w = rawW
        let h = rawH
        if (w > 1280) {
          h = Math.round((h * 1280) / w)
          w = 1280
        }
        // 画布需为偶数，避免某些浏览器绘制模糊
        canvas.width = w % 2 === 0 ? w : w - 1
        canvas.height = h % 2 === 0 ? h : h - 1
        const ctx = canvas.getContext('2d')
        if (!ctx) {
          finishExtract(index)
          return
        }
        ctx.drawImage(v, 0, 0, canvas.width, canvas.height)
        const dataUrl = canvas.toDataURL('image/jpeg', 0.85)
        coverData.set(index, dataUrl)
        coverStatus.set(index, 'extracted')
        cacheCover(index, dataUrl)  // 缓存抽帧结果，二次访问无需重新抽帧
      } catch (e) {
        // canvas 跨域或抽帧失败 → 标记 failed，维持占位背景
        coverStatus.set(index, 'failed')
      } finally {
        finishExtract(index)  // 成功或失败都推进串行队列
      }
    }
  } catch (e) {
    coverStatus.set(index, 'failed')
    finishExtract(index)
  }
}

// mp4 也不存在（本地 mp4 没放 + 没加载远程） → 直接失败保持占位
const onExtractVideoError = (index, video) => {
  finishExtract(index)
  // 抽帧成功后在清理 src 时触发的"空源"错误：封面已生成，直接忽略，不覆盖
  if (coverStatus.get(index) === 'extracted') return
  // 本地 mp4 不存在 → 封面保持为空（占位），不加载任何远程兜底封面
  coverStatus.set(index, 'failed')
}

const openVideo = async (index) => {
  currentIndex.value = index
  const v = videoList.value[index]
  if (!v || !v.videoSrc) return
  playingVideo.value = { ...v }
  // 默认尝试本地视频
  activeVideoSrc.value = v.videoSrc
  activePoster.value = coverData.get(index) || v.poster
  // 先展示封面，元数据就绪后由 onDialogVideoReady 自动播放
  videoReady.value = false
  showVideoDialog.value = true
}

// 弹窗视频元数据就绪 → 标记完成并自动播放（此时已缓冲到首帧，播放较流畅）
const onDialogVideoReady = () => {
  videoReady.value = true
  setTimeout(() => {
    try { videoRef.value?.play().catch(() => {}) } catch (e) {}
  }, 0)
}

// —— 主视频滑动轮播 ——
// 主播放器封面：与下方卡片使用完全一致的 cover 铺图方式，每张 slide 独立绑定它自己的封面
const slideCoverStyle = (index) => {
  const url = coverData.get(index)
  const fallback = 'linear-gradient(135deg, rgba(15,23,42,0.6) 0%, rgba(30,41,59,0.6) 100%), radial-gradient(circle at center, rgba(0,212,255,0.18) 0%, transparent 70%)'
  return {
    backgroundImage: url ? `url("${url}")` : fallback,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat'
  }
}

// 轮播轨道偏移：把当前项滑动到视口中央
const slideTrackStyle = computed(() => ({
  transform: `translateX(-${currentIndex.value * 100}%)`,
  transition: 'transform 0.6s cubic-bezier(0.25, 0.8, 0.25, 1)'
}))

const prevVideo = () => {
  const total = videoList.value.length
  currentIndex.value = (currentIndex.value - 1 + total) % total
}

const nextVideo = () => {
  const total = videoList.value.length
  currentIndex.value = (currentIndex.value + 1) % total
}

const goToVideo = (index) => {
  currentIndex.value = index
}

// —— 自动播放：每个一段时间自动滑到下一张，鼠标悬停暂停 ——
let autoTimer = null
const startAutoPlay = () => {
  stopAutoPlay()
  autoTimer = setInterval(nextVideo, 4000)
}
const stopAutoPlay = () => {
  if (autoTimer) {
    clearInterval(autoTimer)
    autoTimer = null
  }
}

// —— 触摸滑动：移动端左右滑动切换 ——
let touchStartX = 0
const onTouchStart = (e) => {
  stopAutoPlay()
  touchStartX = e.changedTouches[0]?.clientX ?? 0
}
const onTouchEnd = (e) => {
  const endX = e.changedTouches[0]?.clientX ?? touchStartX
  const dx = endX - touchStartX
  if (Math.abs(dx) > 50) {
    dx < 0 ? nextVideo() : prevVideo()
  }
  startAutoPlay()
}

onMounted(() => {
  startAutoPlay()
  probeDurationMeta()
})
onBeforeUnmount(() => {
  stopAutoPlay()
  if (durationProbeRef.value) {
    durationProbeRef.value.onloadedmetadata = null
    durationProbeRef.value.onerror = null
    try { durationProbeRef.value.pause() } catch (e) {}
  }
})

// video 标签 onerror：本地 mp4 不存在时自动切换到远程示例
const onVideoError = () => {
  const v = playingVideo.value
  if (!v) return
  // 已切换过 fallback 就不再重复切换（避免死循环）
  if (activeVideoSrc.value === v.fallbackSrc) return
  if (v.fallbackSrc) {
    activeVideoSrc.value = v.fallbackSrc
    activePoster.value = v.fallbackPoster || ''
    // 替换 source 后需要强制重新加载
    setTimeout(() => {
      try {
        if (videoRef.value) {
          videoRef.value.load()
          videoRef.value.play().catch(() => {})
        }
      } catch (e) {}
    }, 0)
  }
}

// poster 加载失败时也切换到 fallback
const onPosterError = () => {
  const v = playingVideo.value
  if (!v || !v.fallbackPoster) return
  if (activePoster.value !== v.fallbackPoster) {
    activePoster.value = v.fallbackPoster
  }
}

const playMainVideo = () => {
  // 顶部大图播放区 → 打开当前所选视频（默认第一个）
  openVideo(currentIndex.value)
}

const handleDialogClose = () => {
  // 关闭弹窗时尝试 pause，避免后台继续播放
  try {
    if (videoRef.value) {
      videoRef.value.pause()
    }
  } catch (e) {}
  playingVideo.value = null
  activeVideoSrc.value = ''
  activePoster.value = ''
  videoReady.value = false
}
</script>

<style lang="scss" scoped>
.video-page {
  padding-top: 70px;
}

.page-hero {
  position: relative;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.hero-bg {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(180deg, rgba(14, 109, 240, 0.08) 0%, transparent 100%),
              url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 400"><rect fill="%23ffffff" width="1200" height="400"/><path d="M0,200 Q300,100 600,200 T1200,200" stroke="%230e6df0" fill="none" stroke-width="1" opacity="0.3"/><path d="M0,250 Q300,150 600,250 T1200,250" stroke="%234f7cff" fill="none" stroke-width="1" opacity="0.3"/></svg>');
  background-size: cover;
}

.hero-content {
  position: relative;
  z-index: 1;
  text-align: center;

  h1 {
    font-size: 42px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    margin-bottom: 12px;
  }

  .breadcrumb {
    color: #475569;

    a {
      color: #475569;
      text-decoration: none;
      &:hover { color: #0e6df0; }
    }

    .separator {
      margin: 0 12px;
      color: #94a3b8;
    }

    .current {
      color: #0e6df0;
    }
  }
}

.main-video-section {
  padding: 40px 0;
  position: relative;
  z-index: 1;
  background: #ffffff;
}

.video-player-main {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 20px 60px rgba(14, 109, 240, 0.08);
}

.player-wrapper {
  position: relative;
  aspect-ratio: 16/9;
  background: #000;
}

/* 轮播滑动视口与轨道 */
.hero-slide-viewport {
  position: absolute;
  inset: 0;
  overflow: hidden;
}

.hero-slide-track {
  display: flex;
  height: 100%;
  will-change: transform;
}

.hero-slide {
  position: relative;
  flex: 0 0 100%;
  min-width: 100%;
  height: 100%;
  cursor: pointer;
}

.player-placeholder {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  background:
    radial-gradient(circle at center, rgba(14, 109, 240, 0.1) 0%, transparent 70%),
    linear-gradient(135deg, #1e293b 0%, #0f172a 100%);
  transition: all 0.3s ease;

  &:hover {
    .play-btn {
      transform: scale(1.1);
      box-shadow: 0 0 40px rgba(14, 109, 240, 0.6);
    }
  }
}

/* 封面遮罩：让文字仍可读 */
.hero-shade {
  position: absolute;
  inset: 0;
  background: linear-gradient(180deg, rgba(0, 0, 0, 0.08) 0%, rgba(0, 0, 0, 0.28) 100%);
  pointer-events: none;
}

/* 轮播左右箭头 */
.carousel-arrow {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 44px;
  height: 44px;
  border-radius: 50%;
  border: 1px solid rgba(255, 255, 255, 0.4);
  background: rgba(0, 0, 0, 0.45);
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 20px;
  transition: all 0.3s ease;
  z-index: 5;

  &:hover {
    background: linear-gradient(135deg, #0575e6, #021b79);
    border-color: transparent;
    box-shadow: 0 0 20px rgba(14, 109, 240, 0.5);
  }

  &.prev { left: 16px; }
  &.next { right: 16px; }
}

/* 轮播指示点 */
.carousel-dots {
  position: absolute;
  bottom: 16px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 8px;
  z-index: 5;

  .carousel-dot {
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background: rgba(255, 255, 255, 0.4);
    cursor: pointer;
    transition: all 0.3s ease;

    &:hover {
      background: rgba(255, 255, 255, 0.7);
    }

    &.active {
      width: 24px;
      border-radius: 6px;
      background: linear-gradient(135deg, #0575e6, #021b79);
    }
  }
}

.play-btn {
  width: 100px;
  height: 100px;
  background: linear-gradient(135deg, #0575e6, #021b79);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
  transition: all 0.3s ease;
  box-shadow: 0 0 30px rgba(14, 109, 240, 0.4);
  animation: pulse-glow 2s ease-in-out infinite;
}

@keyframes pulse-glow {
  0%, 100% { box-shadow: 0 0 30px rgba(14, 109, 240, 0.4); }
  50% { box-shadow: 0 0 50px rgba(14, 109, 240, 0.7); }
}

.player-overlay {
  position: absolute;
  bottom: 24px;
  left: 24px;
  right: 24px;
  text-align: left;

  h3 {
    color: white;
    font-size: 20px;
    margin-bottom: 8px;
    text-shadow: 0 2px 10px rgba(0, 0, 0, 0.5);
  }

  p {
    color: rgba(255, 255, 255, 0.8);
    font-size: 14px;
    margin-bottom: 0;
  }
}

/* 主视频右下角时长：优先展示真实播放时长 */
.main-video-duration {
  position: absolute;
  bottom: 24px;
  right: 24px;
  z-index: 4;
  background: rgba(0, 0, 0, 0.6);
  color: white;
  padding: 4px 12px;
  border-radius: 4px;
  font-size: 13px;
}

.player-controls {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 24px;
  gap: 24px;

  .control-info {
    flex: 1;

    h3 {
      color: #0f172a;
      margin-bottom: 4px;
    }

    .control-desc {
      color: #64748b;
      font-size: 14px;
    }
  }

  .control-actions {
    display: flex;
    gap: 12px;
  }

  .action-btn {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 10px 20px;
    border-radius: 8px;
    background: #f1f5f9;
    border: 1px solid #e2e8f0;
    color: #0f172a;
    font-size: 14px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.3s ease;

    &:hover {
      background: #e2e8f0;
    }

    &.primary {
      background: linear-gradient(135deg, #0575e6, #021b79);
      color: white;
      border: none;

      &:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 25px rgba(14, 109, 240, 0.3);
      }
    }
  }
}

.video-list-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
  background: linear-gradient(135deg, #eff6ff, #f8fbff);
}

.section-header {
  text-align: center;
  margin-bottom: 40px;

  h2 {
    font-size: 32px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    margin-bottom: 12px;
  }

  p {
    color: #475569;
    font-size: 16px;
  }
}

.video-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.video-card {
  background: #ffffff;
  border: 1.5px solid rgba(14, 109, 240, 0.4);
  border-radius: 16px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover,
  &.active {
    transform: translateY(-5px);
    border-color: #0e6df0;
    box-shadow: 0 15px 40px rgba(14, 109, 240, 0.15);
  }
}

.video-thumb {
  position: relative;
  aspect-ratio: 16/9;
  background:
    linear-gradient(135deg, #1e293b, #0f172a),
    radial-gradient(circle at center, rgba(14, 109, 240, 0.15) 0%, transparent 65%);
  overflow: hidden;
  border-radius: 8px 8px 0 0;
}

.thumb-cover {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  background:
    radial-gradient(circle at center, rgba(14, 109, 240, 0.1) 0%, transparent 60%);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  transition: transform 0.3s ease;

  .cover-shade {
    position: absolute;
    inset: 0;
    background: linear-gradient(180deg, rgba(0, 0, 0, 0.1) 0%, rgba(0, 0, 0, 0.25) 100%);
  }

  .play-icon {
    position: relative;
    z-index: 2;
    font-size: 48px;
    color: rgba(14, 109, 240, 0.85);
    transition: all 0.3s ease;
    filter: drop-shadow(0 2px 8px rgba(0, 0, 0, 0.6));
  }
}

.video-card:hover .thumb-cover {
  transform: scale(1.04);

  .play-icon {
    color: #0e6df0;
    transform: scale(1.2);
  }
}

/* 封面探测器和抽帧用 video 都隐藏，只用于逻辑 */
.cover-detector,
.extract-video {
  position: absolute;
  width: 0;
  height: 0;
  opacity: 0;
  pointer-events: none;
  visibility: hidden;
}

.duration-probe {
  position: absolute;
  width: 0;
  height: 0;
  opacity: 0;
  pointer-events: none;
  visibility: hidden;
}

.metadata-video {
  position: absolute;
  width: 0;
  height: 0;
  opacity: 0;
  pointer-events: none;
  visibility: hidden;
}

.video-duration {
  position: absolute;
  bottom: 10px;
  right: 10px;
  background: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 4px 10px;
  border-radius: 4px;
  font-size: 12px;
}

.video-badge {
  position: absolute;
  top: 10px;
  left: 10px;
  background: #0e6df0;
  color: white;
  padding: 4px 10px;
  border-radius: 4px;
  font-size: 11px;
  font-weight: 500;
}

.video-info {
  padding: 16px;

  h4 {
    color: #0f172a;
    font-size: 15px;
    margin-bottom: 8px;
  }

  p {
    color: #64748b;
    font-size: 13px;
    line-height: 1.5;
  }
}

.walkthrough-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
  background: #ffffff;
}

.walkthrough-steps {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

.step-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 24px;
  text-align: center;
  position: relative;
  transition: all 0.3s ease;

  &.completed {
    border-color: rgba(34, 197, 94, 0.35);
    background: #f0fdf4;
  }

  .step-number {
    width: 50px;
    height: 50px;
    margin: 0 auto 16px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 20px;
    font-weight: 700;
  }

  .step-content {
    h4 {
      color: #0f172a;
      font-size: 16px;
      margin-bottom: 8px;
    }

    p {
      color: #64748b;
      font-size: 13px;
      line-height: 1.5;
    }
  }

  .check-mark,
  .play-mark {
    position: absolute;
    top: 16px;
    right: 16px;
    font-size: 24px;
  }

  .check-mark {
    color: #22c55e;
  }

  .play-mark {
    color: #0e6df0;
  }
}

.faq-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
  background: linear-gradient(135deg, #eff6ff, #f8fbff);
}

.faq-list {
  max-width: 800px;
  margin: 0 auto;
}

.cyber-collapse {
  border: none;
  background: transparent;

  :deep(.el-collapse-item) {
    border: none;
    margin-bottom: 12px;
    background: #ffffff;
    border: 1px solid #e2e8f0;
    border-radius: 12px;
    overflow: hidden;

    .el-collapse-item__header {
      background: transparent;
      border: none;
      padding: 16px 20px;
      color: #0f172a;
      font-weight: 500;
    }

    .el-collapse-item__wrap {
      border: none;
      background: transparent;
    }

    .el-collapse-item__content {
      padding: 0 20px 16px;
      color: #475569;
      line-height: 1.7;
    }
  }

  .faq-question {
    color: #0f172a;
  }
}

.cta-section {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #f8fafc;
}

.cta-card {
  background: linear-gradient(135deg, #021b79 0%, #0575e6 100%);
  border-radius: 24px;
  padding: 60px 40px;
  text-align: center;
  position: relative;
  overflow: hidden;
  box-shadow: 0 20px 50px rgba(2, 27, 121, 0.3);

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: -100%;
    width: 200%;
    height: 2px;
    background: linear-gradient(90deg, transparent, #ffffff, transparent);
    animation: scan 3s linear infinite;
  }

  h2 {
    font-size: 32px;
    margin-bottom: 16px;
    color: #ffffff;
  }

  p {
    color: rgba(255, 255, 255, 0.85);
    font-size: 16px;
    margin-bottom: 32px;
  }
}

@keyframes scan {
  from { left: -100%; }
  to { left: 100%; }
}

.cta-actions {
  display: flex;
  justify-content: center;
  gap: 16px;
}

.cta-btn {
  display: inline-block;
  padding: 16px 40px;
  border-radius: 12px;
  font-weight: 700;
  text-decoration: none;
  font-size: 16px;
  transition: all 0.3s ease;

  &.primary {
    background: #ffffff;
    color: #0575e6;
    &:hover { transform: translateY(-2px); box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2); }
  }

  &.outline {
    background: rgba(255, 255, 255, 0.12);
    border: 2px solid rgba(255, 255, 255, 0.4);
    color: #ffffff;
    &:hover { border-color: #ffffff; background: rgba(255, 255, 255, 0.2); }
  }
}

.section-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

@media (max-width: 968px) {
  .video-grid {
    grid-template-columns: 1fr;
  }

  .walkthrough-steps {
    grid-template-columns: repeat(2, 1fr);
  }

  .player-controls {
    flex-direction: column;
    align-items: stretch;
  }
}

/* 视频播放器弹窗样式 */
:deep(.video-player-dialog) {
  .el-dialog__body {
    padding-top: 16px;
  }
}

.dialog-video-wrapper {
  position: relative;
  width: 100%;
  background: #000;
  border-radius: 10px;
  overflow: hidden;
  line-height: 0;
}

.dialog-video {
  width: 100%;
  height: auto;
  max-height: 70vh;
  display: block;
  background: #000;
}

/* 弹窗缓冲提示：打开时先显示封面，加载完成前提示用户 */
.dialog-video-buffering {
  position: absolute;
  inset: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 12px;
  color: rgba(255, 255, 255, 0.8);
  font-size: 14px;
  pointer-events: none;

  .spinner {
    width: 36px;
    height: 36px;
    border: 3px solid rgba(255, 255, 255, 0.25);
    border-top-color: #fff;
    border-radius: 50%;
    animation: dialog-spin 0.8s linear infinite;
  }
}

@keyframes dialog-spin {
  to { transform: rotate(360deg); }
}

.dialog-video-meta {
  margin-top: 16px;

  h4 {
    font-size: 18px;
    margin: 0 0 6px;
    color: #0f172a;
    font-weight: 600;
  }

  p {
    margin: 0;
    color: #475569;
    font-size: 14px;
    line-height: 1.6;
  }
}

.dialog-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;

  .dialog-tip {
    font-size: 12px;
    color: #64748b;
  }
}
</style>
