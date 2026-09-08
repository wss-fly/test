<template>
  <header class="navbar" :class="{ scrolled: isScrolled }">
    <div class="nav-container">
      <router-link to="/" class="logo">
        <div class="logo-icon">
          <svg viewBox="0 0 48 48" class="icon-cyber">
            <circle cx="24" cy="24" r="20" fill="none" stroke="url(#grad1)" stroke-width="2"/>
            <path d="M16 24 L22 30 L32 18" fill="none" stroke="#0e6df0" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
            <circle cx="24" cy="24" r="4" fill="#4f7cff" class="pulse-ring"/>
            <defs>
              <linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="100%">
                <stop offset="0%" style="stop-color:#0e6df0"/>
                <stop offset="100%" style="stop-color:#4f7cff"/>
              </linearGradient>
            </defs>
          </svg>
        </div>
        <span class="logo-text">{{ t('components.brand') }}</span>
      </router-link>

      <nav class="nav-menu" :class="{ active: mobileMenuOpen }">
        <router-link to="/" class="nav-link">{{ t('nav.home') }}</router-link>
        <router-link to="/features" class="nav-link">{{ t('nav.features') }}</router-link>
        <router-link to="/video" class="nav-link">{{ t('nav.video') }}</router-link>
        <router-link to="/custom" class="nav-link">{{ t('nav.custom') }}</router-link>
        <router-link to="/contact" class="nav-link">{{ t('nav.contact') }}</router-link>
      </nav>

      <div class="nav-actions">
        <router-link to="/contact" class="btn-cyber-small">{{ t('nav.getSupport') }}</router-link>
        <button class="lang-switch" @click="toggleLang">{{ currentLang === 'zh' ? 'EN' : '中' }}</button>
      </div>

      <button class="mobile-toggle" @click="mobileMenuOpen = !mobileMenuOpen">
        <span :class="{ open: mobileMenuOpen }"></span>
        <span :class="{ open: mobileMenuOpen }"></span>
        <span :class="{ open: mobileMenuOpen }"></span>
      </button>
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { currentLang, toggleLang, t } = i18n

const isScrolled = ref(false)
const mobileMenuOpen = ref(false)

const handleScroll = () => {
  isScrolled.value = window.scrollY > 50
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
</script>

<style lang="scss" scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  background: #ffffff;
  padding: 16px 0;
  transition: all 0.3s ease;

  &.scrolled {
    background: rgba(255, 255, 255, 0.95);
    backdrop-filter: blur(10px);
    box-shadow: 0 2px 30px rgba(14, 109, 240, 0.12);
    padding: 12px 0;
  }
}

.nav-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo {
  display: flex;
  align-items: center;
  gap: 10px;
  text-decoration: none;
}

.logo-icon {
  width: 40px;
  height: 40px;

  .icon-cyber {
    width: 100%;
    height: 100%;
  }

  .pulse-ring {
    animation: pulse 2s infinite;
  }
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.5; }
}

.logo-text {
  font-size: 20px;
  font-weight: 700;
  background: linear-gradient(135deg, #0e6df0, #4f7cff);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.nav-menu {
  display: flex;
  gap: 32px;

  .nav-link {
    color: rgba(15, 23, 42, 0.7);
    text-decoration: none;
    font-size: 15px;
    font-weight: 500;
    padding: 8px 0;
    position: relative;
    transition: color 0.3s ease;

    &::after {
      content: '';
      position: absolute;
      bottom: 0;
      left: 0;
      width: 0;
      height: 2px;
      background: linear-gradient(90deg, #0e6df0, #4f7cff);
      transition: width 0.3s ease;
    }

    &:hover,
    &.router-link-active {
      color: #0e6df0;

      &::after {
        width: 100%;
      }
    }
  }
}

.nav-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.btn-cyber-small {
  background: linear-gradient(135deg, #0575e6, #021b79);
  color: white;
  padding: 10px 24px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.3s ease;
  white-space: nowrap;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 25px rgba(14, 109, 240, 0.4);
  }
}

.lang-switch {
  background: transparent;
  border: 1px solid rgba(14, 109, 240, 0.3);
  color: #0e6df0;
  padding: 8px 16px;
  border-radius: 8px;
  font-size: 14px;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    border-color: #0e6df0;
    background: rgba(14, 109, 240, 0.08);
  }
}

.mobile-toggle {
  display: none;
  flex-direction: column;
  gap: 5px;
  background: transparent;
  border: none;
  padding: 8px;
  cursor: pointer;

  span {
    display: block;
    width: 24px;
    height: 2px;
    background: #0e6df0;
    transition: all 0.3s ease;

    &.open:nth-child(1) {
      transform: rotate(45deg) translate(5px, 5px);
    }
    &.open:nth-child(2) {
      opacity: 0;
    }
    &.open:nth-child(3) {
      transform: rotate(-45deg) translate(7px, -7px);
    }
  }
}

@media (max-width: 968px) {
  .nav-menu {
    position: fixed;
    top: 70px;
    left: 0;
    right: 0;
    background: rgba(255, 255, 255, 0.98);
    flex-direction: column;
    padding: 24px;
    gap: 16px;
    transform: translateY(-100%);
    opacity: 0;
    pointer-events: none;
    transition: all 0.3s ease;
    border-top: 1px solid rgba(14, 109, 240, 0.2);
    box-shadow: 0 10px 30px rgba(15, 23, 42, 0.1);

    &.active {
      transform: translateY(0);
      opacity: 1;
      pointer-events: auto;
    }
  }

  .nav-actions {
    .btn-cyber-small {
      display: none;
    }
  }

  .mobile-toggle {
    display: flex;
  }
}
</style>
