<template>
  <div class="wecom-page">
    <div class="qr-card">
      <div class="brand">
        <svg viewBox="0 0 48 48" class="logo-svg">
          <circle cx="24" cy="24" r="20" fill="none" stroke="#0e6df0" stroke-width="2"/>
          <path d="M16 24 L22 30 L32 18" fill="none" stroke="#0e6df0" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
          <circle cx="24" cy="24" r="4" fill="#4f7cff"/>
        </svg>
        <span>赛博飞推</span>
      </div>

      <h2 class="title">{{ t('contact.weworkName') }}</h2>
      <p class="sub">{{ t('contact.weworkValue') }}</p>

      <div class="qr-box">
        <el-image
          v-if="qrOk"
          :src="qrSrc"
          class="qr-el-img"
          fit="contain"
          :preview-src-list="[qrSrc]"
          preview-teleported
          :initial-index="0"
          @error="qrOk = false"
        />
        <div v-else class="qr-fallback">
          <svg viewBox="0 0 24 24" fill="currentColor" width="40" height="40">
            <path d="M3 3h8v8H3V3zm2 2v4h4V5H5zm8-2h8v8h-8V3zm2 2v4h4V5h-4zM3 13h8v8H3v-8zm2 2v4h4v-4H5zm11-3h3v3h-3v-3zm-4 0h2v3h-2v-3zm4 5h3v3h-3v-3zm-4 0h2v3h-2v-3z"/>
          </svg>
          <span>二维码图片加载中或未放置，请检查 public/images/ 目录</span>
        </div>
      </div>
      <p class="zoom-tip">点击二维码可放大查看</p>

      <p class="tip">{{ t('contact.weworkQrTip') }}</p>

      <router-link to="/" class="back-btn">
        <svg viewBox="0 0 24 24" fill="currentColor" width="16" height="16">
          <path d="M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z"/>
        </svg>
        返回首页
      </router-link>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n

const qrSrc = '/images/wechat-work-qr.jpg'
const qrOk = ref(true)
</script>

<style lang="scss" scoped>
.wecom-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 20px;
  background:
    radial-gradient(circle at 20% 20%, rgba(0, 212, 255, 0.15) 0%, transparent 40%),
    radial-gradient(circle at 80% 80%, rgba(168, 85, 247, 0.15) 0%, transparent 40%),
    linear-gradient(135deg, #0b1120 0%, #111827 100%);
  font-family: inherit;
}

.qr-card {
  width: 100%;
  max-width: 420px;
  background: linear-gradient(160deg, rgba(17, 24, 39, 0.95) 0%, rgba(11, 17, 32, 0.95) 100%);
  border: 1px solid rgba(0, 212, 255, 0.25);
  border-radius: 20px;
  padding: 36px 32px;
  text-align: center;
  box-shadow: 0 0 50px rgba(0, 212, 255, 0.12);
}

.brand {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  margin-bottom: 8px;

  .logo-svg {
    width: 34px;
    height: 34px;
  }

  span {
    font-size: 18px;
    font-weight: 600;
    background: linear-gradient(90deg, #0575e6, #021b79);
    -webkit-background-clip: text;
    background-clip: text;
    -webkit-text-fill-color: transparent;
    color: transparent;
  }
}

.title {
  color: #fff;
  font-size: 26px;
  font-weight: 700;
  margin: 16px 0 6px;
}

.sub {
  color: #475569;
  font-size: 15px;
  margin: 0 0 24px;
}

.qr-box {
  width: 240px;
  height: 240px;
  margin: 0 auto;
  background: #fff;
  border-radius: 16px;
  padding: 10px;
  box-shadow: 0 12px 32px rgba(14, 109, 240, 0.1);

  img,
  .qr-el-img {
    width: 100%;
    height: 100%;
    object-fit: contain;
    border-radius: 10px;
    cursor: zoom-in;
  }

  .qr-fallback {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 12px;
    color: #64748b;
    font-size: 13px;
    line-height: 1.6;
    padding: 12px;
    text-align: center;
    background: rgba(226, 232, 240, 0.6);
    border-radius: 10px;
  }
}

.zoom-tip {
  color: #38bdf8;
  font-size: 12px;
  margin: 12px 0 0;
  opacity: 0.85;
}

.tip {
  color: #64748b;
  font-size: 14px;
  line-height: 1.6;
  margin: 24px 0 20px;
}

.back-btn {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 10px 24px;
  border-radius: 10px;
  color: #fff;
  text-decoration: none;
  font-size: 14px;
  background: linear-gradient(135deg, #0575e6, #021b79);
  transition: opacity 0.3s ease;

  &:hover {
    opacity: 0.85;
  }
}
</style>