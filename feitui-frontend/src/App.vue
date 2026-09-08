<template>
  <div id="app-container">
    <CyberBackground />
    <router-view :key="$route.fullPath + '-' + currentLang" />
  </div>
</template>

<script setup>
import { computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import CyberBackground from '@/components/CyberBackground.vue'
import { useI18nStore } from '@/i18n'
import { initTracker, reportPageView } from '@/utils/tracker'

const i18n = useI18nStore()
const currentLang = computed(() => i18n.currentLang)

const router = useRouter()

onMounted(() => {
  // 初始化访问数据上报(页面切换、心跳实时在线)
  initTracker()
  router.afterEach(() => reportPageView())
})
</script>

<style lang="scss">
#app-container {
  min-height: 100vh;
  position: relative;
}
</style>
