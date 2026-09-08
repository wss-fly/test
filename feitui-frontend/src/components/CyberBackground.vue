<template>
  <div class="cyber-bg" ref="bgRef">
    <div class="grid-layer"></div>
    <canvas ref="canvasRef" class="particles-canvas"></canvas>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'

const canvasRef = ref(null)
const bgRef = ref(null)
let animationId = null

onMounted(() => {
  const canvas = canvasRef.value
  if (!canvas) return
  
  const ctx = canvas.getContext('2d')
  let particles = []
  let width = 0
  let height = 0

  const resize = () => {
    width = window.innerWidth
    height = window.innerHeight
    canvas.width = width
    canvas.height = height
  }

  const createParticles = () => {
    particles = []
    const particleCount = Math.min(80, Math.floor(width * height / 15000))
    for (let i = 0; i < particleCount; i++) {
      particles.push({
        x: Math.random() * width,
        y: Math.random() * height,
        vx: (Math.random() - 0.5) * 0.5,
        vy: (Math.random() - 0.5) * 0.5,
        size: Math.random() * 2 + 1,
        opacity: Math.random() * 0.5 + 0.1
      })
    }
  }

  const animate = () => {
    ctx.clearRect(0, 0, width, height)
    
    particles.forEach((p, i) => {
      p.x += p.vx
      p.y += p.vy
      
      if (p.x < 0 || p.x > width) p.vx *= -1
      if (p.y < 0 || p.y > height) p.vy *= -1
      
      ctx.beginPath()
      ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2)
      ctx.fillStyle = `rgba(0, 212, 255, ${p.opacity})`
      ctx.fill()
      
      particles.forEach((p2, j) => {
        if (i < j) {
          const dx = p.x - p2.x
          const dy = p.y - p2.y
          const dist = Math.sqrt(dx * dx + dy * dy)
          if (dist < 150) {
            ctx.beginPath()
            ctx.moveTo(p.x, p.y)
            ctx.lineTo(p2.x, p2.y)
            ctx.strokeStyle = `rgba(0, 212, 255, ${(150 - dist) / 150 * 0.15})`
            ctx.lineWidth = 0.5
            ctx.stroke()
          }
        }
      })
    })
    
    animationId = requestAnimationFrame(animate)
  }

  resize()
  createParticles()
  animate()

  window.addEventListener('resize', () => {
    resize()
    createParticles()
  })
})

onUnmounted(() => {
  if (animationId) {
    cancelAnimationFrame(animationId)
  }
})
</script>

<style lang="scss" scoped>
.cyber-bg {
  position: fixed;
  inset: 0;
  z-index: 0;
  pointer-events: none;
  overflow: hidden;
}

.grid-layer {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(0, 212, 255, 0.02) 1px, transparent 1px),
    linear-gradient(90deg, rgba(0, 212, 255, 0.02) 1px, transparent 1px);
  background-size: 60px 60px;
}

.particles-canvas {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
}
</style>
