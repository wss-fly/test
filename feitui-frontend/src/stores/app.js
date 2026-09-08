import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAppStore = defineStore('app', () => {
  const features = ref([])
  const stats = ref([])
  const videos = ref([])
  const highlights = ref([])
  const loading = ref(false)

  return {
    features,
    stats,
    videos,
    highlights,
    loading
  }
})
