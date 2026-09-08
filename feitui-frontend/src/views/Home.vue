<template>
  <div class="home-page">
    <NavBar />
    
    <!-- Hero Section -->
    <section class="hero-section">
      <div class="cyber-bg-grid"></div>
      <div class="hero-content">
        <div class="hero-text">
          <div class="hero-tag">
            <span class="tag-dot"></span>
            {{ t('home.heroTag') }}
          </div>
          <h1 class="hero-title">
            <span class="title-gradient">{{ t('home.brand') }}</span>
            <span class="title-sub">{{ t('home.subtitle') }}</span>
          </h1>
          <div class="hero-intro">
            <h2>{{ t('home.heroIntroTitle1') }}</h2>
            <h2>{{ t('home.heroIntroTitle2') }}</h2>
          </div>
          <p class="hero-desc">
            {{ t('home.heroDesc') }}
          </p>
          <div class="hero-actions">
            <router-link to="/contact" class="btn-primary">{{ t('home.heroBtnContact') }}</router-link>
            <router-link to="/video" class="btn-secondary">{{ t('home.heroBtnVideo') }}</router-link>
          </div>
        </div>
        
        <div class="hero-visual">
          <div class="hero-illustration">
            <div class="illustration-card">
              <div class="card-glow"></div>
              <div class="card-swirleffect"></div>
              <div class="check-list">
                <div class="check-item">
                  <span class="check-icon">✓</span>
                  <span class="check-text">{{ t('home.heroItem1') }}</span>
                </div>
                <div class="check-item">
                  <span class="check-icon">✓</span>
                  <span class="check-text">{{ t('home.heroItem2') }}</span>
                </div>
                <div class="check-item">
                  <span class="check-icon">✓</span>
                  <span class="check-text">{{ t('home.heroItem3') }}</span>
                </div>
                <div class="check-item">
                  <span class="check-icon">✓</span>
                  <span class="check-text">{{ t('home.heroItem4') }}</span>
                </div>
              </div>
            </div>

            <div class="floating-screens">
              <div class="screen-panel screen-panel-1 float-delay-1">
                <svg viewBox="0 0 120 90" width="100%" height="100%" preserveAspectRatio="none">
                  <defs>
                    <linearGradient id="gradScreen1" x1="0%" y1="0%" x2="0%" y2="100%">
                      <stop offset="0%" style="stop-color:rgba(14,109,240,0.25)"/>
                      <stop offset="100%" style="stop-color:rgba(14,109,240,0.05)"/>
                    </linearGradient>
                  </defs>
                  <rect x="2" y="2" width="116" height="86" rx="8" fill="url(#gradScreen1)" stroke="#0e6df0" stroke-width="1.5"/>
                  <rect x="14" y="14" width="92" height="10" rx="2" fill="#0e6df0" opacity="0.4"/>
                  <rect x="14" y="30" width="92" height="42" rx="3" fill="rgba(14,109,240,0.1)" stroke="#0e6df0" stroke-width="0.8" opacity="0.6"/>
                  <circle cx="28" cy="52" r="14" fill="none" stroke="#0e6df0" stroke-width="2.5" opacity="0.8"/>
                  <circle cx="28" cy="52" r="10" fill="none" stroke="#4f7cff" stroke-width="2" opacity="0.7"/>
                  <circle cx="28" cy="52" r="5" fill="rgba(79,124,255,0.8)"/>
                </svg>
              </div>
              <div class="screen-panel screen-panel-2 float-delay-2">
                <svg viewBox="0 0 120 90" width="100%" height="100%" preserveAspectRatio="none">
                  <defs>
                    <linearGradient id="gradScreen2" x1="0%" y1="0%" x2="100%" y2="100%">
                      <stop offset="0%" style="stop-color:rgba(79,124,255,0.2)"/>
                      <stop offset="100%" style="stop-color:rgba(14,109,240,0.1)"/>
                    </linearGradient>
                  </defs>
                  <rect x="2" y="2" width="116" height="86" rx="8" fill="url(#gradScreen2)" stroke="#4f7cff" stroke-width="1.5"/>
                  <rect x="14" y="12" width="92" height="6" rx="1.5" fill="#4f7cff" opacity="0.5"/>
                  <rect x="14" y="22" width="92" height="56" rx="3" fill="rgba(79,124,255,0.08)" stroke="#4f7cff" stroke-width="0.6" opacity="0.5"/>
                  <rect x="20" y="58" width="10" height="16" rx="1.5" fill="#0e6df0" opacity="0.75"/>
                  <rect x="34" y="48" width="10" height="26" rx="1.5" fill="#4f7cff" opacity="0.7"/>
                  <rect x="48" y="40" width="10" height="34" rx="1.5" fill="#22c55e" opacity="0.75"/>
                  <rect x="62" y="54" width="10" height="20" rx="1.5" fill="#eab308" opacity="0.7"/>
                  <rect x="76" y="32" width="10" height="42" rx="1.5" fill="#f97316" opacity="0.7"/>
                </svg>
              </div>
              <div class="screen-panel screen-panel-3 float-delay-3">
                <svg viewBox="0 0 140 90" width="100%" height="100%" preserveAspectRatio="none">
                  <defs>
                    <linearGradient id="gradScreen3" x1="0%" y1="0%" x2="100%" y2="100%">
                      <stop offset="0%" style="stop-color:rgba(79,124,255,0.25)"/>
                      <stop offset="100%" style="stop-color:rgba(14,109,240,0.15)"/>
                    </linearGradient>
                  </defs>
                  <rect x="2" y="2" width="136" height="86" rx="8" fill="url(#gradScreen3)" stroke="#4f7cff" stroke-width="1.5"/>
                  <rect x="12" y="12" width="116" height="8" rx="2" fill="#4f7cff" opacity="0.4"/>
                  <rect x="12" y="26" width="116" height="52" rx="3" fill="rgba(79,124,255,0.08)" stroke="#4f7cff" stroke-width="0.7" opacity="0.5"/>
                  <polyline points="20,70 35,50 50,58 65,36 80,44 95,22 110,30 124,18" fill="none" stroke="#0e6df0" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" opacity="0.9"/>
                  <rect x="20" y="52" width="10" height="24" rx="1" fill="#0e6df0" opacity="0.35"/>
                  <rect x="36" y="42" width="10" height="34" rx="1" fill="#4f7cff" opacity="0.4"/>
                  <rect x="52" y="50" width="10" height="26" rx="1" fill="#22c55e" opacity="0.4"/>
                  <rect x="68" y="28" width="10" height="48" rx="1" fill="#eab308" opacity="0.45"/>
                  <rect x="84" y="36" width="10" height="40" rx="1" fill="#f97316" opacity="0.45"/>
                  <rect x="100" y="14" width="10" height="62" rx="1" fill="#ec4899" opacity="0.5"/>
                </svg>
              </div>
            </div>

            <div class="illustration-scene">
              <div class="glow-platform">
                <div class="ring ring-1"></div>
                <div class="ring ring-2"></div>
                <div class="ring ring-3"></div>
                <div class="ring-glow"></div>
              </div>
              <div class="phone-device">
                <svg viewBox="0 0 72 140" width="100%" height="100%">
                  <defs>
                    <linearGradient id="phoneBody" x1="0%" y1="0%" x2="100%" y2="100%">
                      <stop offset="0%" style="stop-color:#e5e7eb"/>
                      <stop offset="100%" style="stop-color:#9ca3af"/>
                    </linearGradient>
                    <linearGradient id="phoneScreen" x1="0%" y1="0%" x2="0%" y2="100%">
                      <stop offset="0%" style="stop-color:#1e3a5f"/>
                      <stop offset="100%" style="stop-color:#0f172a"/>
                    </linearGradient>
                  </defs>
                  <rect x="2" y="2" width="68" height="136" rx="10" fill="url(#phoneBody)" stroke="#6b7280" stroke-width="0.8"/>
                  <rect x="6" y="14" width="60" height="112" rx="4" fill="url(#phoneScreen)"/>
                  <circle cx="36" cy="8" r="2" fill="#374151"/>
                  <rect x="12" y="24" width="14" height="8" rx="1.5" fill="#0e6df0" opacity="0.8"/>
                  <rect x="30" y="24" width="20" height="8" rx="1.5" fill="#4f7cff" opacity="0.7"/>
                  <rect x="12" y="36" width="48" height="24" rx="3" fill="rgba(14,109,240,0.12)" stroke="#0e6df0" stroke-width="0.5"/>
                  <rect x="16" y="66" width="6" height="16" rx="1" fill="#0e6df0" opacity="0.6"/>
                  <rect x="26" y="56" width="6" height="26" rx="1" fill="#4f7cff" opacity="0.7"/>
                  <rect x="36" y="46" width="6" height="36" rx="1" fill="#22c55e" opacity="0.7"/>
                  <rect x="46" y="62" width="6" height="20" rx="1" fill="#eab308" opacity="0.6"/>
                  <rect x="12" y="88" width="48" height="4" rx="1" fill="#0e6df0" opacity="0.5"/>
                  <rect x="12" y="96" width="38" height="4" rx="1" fill="#4f7cff" opacity="0.5"/>
                  <rect x="12" y="104" width="28" height="4" rx="1" fill="#22c55e" opacity="0.45"/>
                  <rect x="12" y="112" width="44" height="4" rx="1" fill="#0e6df0" opacity="0.4"/>
                  <circle cx="36" cy="130" r="2.5" fill="none" stroke="#6b7280" stroke-width="0.7"/>
                </svg>
              </div>
              <div class="robot-figure">
                <svg viewBox="0 0 160 180" width="100%" height="100%">
                  <defs>
                    <linearGradient id="robotWhite" x1="0%" y1="0%" x2="100%" y2="100%">
                      <stop offset="0%" style="stop-color:#f8fafc"/>
                      <stop offset="60%" style="stop-color:#e2e8f0"/>
                      <stop offset="100%" style="stop-color:#cbd5e1"/>
                    </linearGradient>
                    <linearGradient id="robotBlue" x1="0%" y1="0%" x2="100%" y2="100%">
                      <stop offset="0%" style="stop-color:#60a5fa"/>
                      <stop offset="100%" style="stop-color:#1d4ed8"/>
                    </linearGradient>
                    <radialGradient id="headGlow" cx="50%" cy="50%" r="50%">
                      <stop offset="0%" style="stop-color:rgba(14,109,240,0.3)"/>
                      <stop offset="100%" style="stop-color:rgba(14,109,240,0)"/>
                    </radialGradient>
                    <filter id="softShadow" x="-50%" y="-50%" width="200%" height="200%">
                      <feGaussianBlur in="SourceAlpha" stdDeviation="2"/>
                      <feOffset dx="0" dy="3" result="offsetblur"/>
                      <feComponentTransfer>
                        <feFuncA type="linear" slope="0.4"/>
                      </feComponentTransfer>
                      <feMerge>
                        <feMergeNode/>
                        <feMergeNode in="SourceGraphic"/>
                      </feMerge>
                    </filter>
                  </defs>
                  <circle cx="80" cy="50" r="42" fill="url(#headGlow)"/>
                  <ellipse cx="80" cy="50" rx="36" ry="33" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1.2" filter="url(#softShadow)"/>
                  <ellipse cx="80" cy="48" rx="22" ry="14" fill="#0c1a2e" stroke="#3b82f6" stroke-width="1"/>
                  <circle cx="70" cy="48" r="5" fill="#06b6d4"/>
                  <circle cx="90" cy="48" r="5" fill="#06b6d4"/>
                  <circle cx="70" cy="48" r="2" fill="#ffffff"/>
                  <circle cx="90" cy="48" r="2" fill="#ffffff"/>
                  <ellipse cx="70" cy="48" rx="7" ry="7" fill="none" stroke="#06b6d4" stroke-width="0.5" opacity="0.6"/>
                  <ellipse cx="90" cy="48" rx="7" ry="7" fill="none" stroke="#06b6d4" stroke-width="0.5" opacity="0.6"/>
                  <line x1="80" y1="14" x2="80" y2="4" stroke="#cbd5e1" stroke-width="1.5"/>
                  <circle cx="80" cy="2.5" r="2.5" fill="#4f7cff"/>
                  <circle cx="80" cy="2.5" r="1.2" fill="#ffffff"/>
                  <rect x="60" y="80" width="40" height="50" rx="12" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1.2" filter="url(#softShadow)"/>
                  <rect x="66" y="88" width="12" height="9" rx="2" fill="url(#robotBlue)"/>
                  <rect x="82" y="88" width="12" height="9" rx="2" fill="#3b82f6" opacity="0.8"/>
                  <circle cx="80" cy="108" r="7" fill="url(#robotBlue)"/>
                  <circle cx="80" cy="108" r="2.5" fill="#ffffff"/>
                  <ellipse cx="80" cy="130" rx="14" ry="5" fill="#e2e8f0"/>
                  <rect x="40" y="88" width="20" height="10" rx="5" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1"/>
                  <rect x="100" y="86" width="22" height="12" rx="6" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1"/>
                  <circle cx="30" cy="93" r="7" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1"/>
                  <circle cx="130" cy="92" r="7" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1"/>
                  <rect x="64" y="132" width="8" height="20" rx="3" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1"/>
                  <rect x="88" y="132" width="8" height="20" rx="3" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1"/>
                  <rect x="58" y="152" width="22" height="10" rx="4" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1" filter="url(#softShadow)"/>
                  <rect x="80" y="152" width="22" height="10" rx="4" fill="url(#robotWhite)" stroke="#cbd5e1" stroke-width="1" filter="url(#softShadow)"/>
                  <rect x="58" y="160" width="22" height="4" rx="2" fill="#1d4ed8" opacity="0.7"/>
                  <rect x="80" y="160" width="22" height="4" rx="2" fill="#1d4ed8" opacity="0.7"/>
                </svg>
              </div>
            </div>

            <router-link to="/contact" class="illustration-cta">
              <span>{{ t('home.heroIllustrationBtn') }}</span>
            </router-link>
          </div>
        </div>
      </div>
      
      <!-- Wave Decoration -->
      <div class="wave-decoration">
        <svg viewBox="0 0 1440 120" preserveAspectRatio="none">
          <path fill="#f8fafc" d="M0,64L80,69.3C160,75,320,85,480,80C640,75,800,53,960,53.3C1120,53,1280,75,1360,85.3L1440,96L1440,120L1360,120C1280,120,1120,120,960,120C800,120,640,120,480,120C320,120,160,120,80,120L0,120Z"></path>
        </svg>
      </div>
    </section>

    <!-- Stats Section -->
    <section class="stats-section">
      <div class="section-container">
        <div class="stats-grid">
          <div v-for="stat in statsList" :key="stat.id" class="stat-card">
            <div class="stat-value">{{ stat.statValue }}</div>
            <div class="stat-label">{{ stat.statLabel }}</div>
          </div>
        </div>
        <div class="stats-desc">
          <p>{{ t('home.statsDesc') }}</p>
          <ul class="check-list">
            <li>
              <el-icon><Check /></el-icon>
              <span>{{ t('home.benefit1') }}</span>
            </li>
            <li>
              <el-icon><Check /></el-icon>
              <span>{{ t('home.benefit2') }}</span>
            </li>
            <li>
              <el-icon><Check /></el-icon>
              <span>{{ t('home.benefit3') }}</span>
            </li>
            <li>
              <el-icon><Check /></el-icon>
              <span>{{ t('home.benefit4') }}</span>
            </li>
            <li>
              <el-icon><Check /></el-icon>
              <span>{{ t('home.benefit5') }}</span>
            </li>
          </ul>
          <p class="highlight-text">
            {{ t('home.highlightText') }}
          </p>
        </div>
      </div>
    </section>

    <!-- Features Preview Section -->
    <section class="features-preview">
      <div class="section-container">
        <div class="section-title">
          <h2>{{ t('home.coreFeaturesTitle') }}</h2>
          <p>{{ t('home.coreFeaturesDesc') }}</p>
        </div>
        <div class="feature-cards-grid">
          <div v-for="(feature, index) in previewFeatures" :key="index" class="feature-card">
            <div class="feature-icon">{{ feature.icon }}</div>
            <h3>{{ feature.title }}</h3>
            <p>{{ feature.description }}</p>
            <div class="feature-tags-row">
              <span v-for="(tag, tIdx) in feature.tags" :key="tIdx" class="mini-tag">{{ tag }}</span>
            </div>
          </div>
        </div>
        <div class="view-more">
          <router-link to="/features" class="view-more-btn">{{ t('home.viewAllBtn') }} <el-icon><ArrowRight /></el-icon></router-link>
        </div>
      </div>
    </section>

    <!-- Real Data Section -->
    <section class="real-data-section">
      <div class="section-container">
        <div class="section-title">
          <h2>{{ t('home.realDataSectionTitle') }}</h2>
        </div>
        <div class="real-data-content">
          <div class="data-visual">
            <div class="data-card">
              <h4>{{ t('home.realDataCardTitle') }}</h4>
              <div class="data-item" v-for="(item, i) in realData" :key="i">
                <span class="data-badge" :class="'badge-' + item.badge">{{ item.badge }}</span>
                <div class="data-info">
                  <strong>{{ item.title }}</strong>
                  <span>{{ item.desc }}</span>
                </div>
                <div class="data-bar">
                  <div class="bar-fill" :style="{ width: item.progress + '%' }"></div>
                </div>
              </div>
            </div>
          </div>
          <div class="data-desc">
            <h3>{{ t('home.realDataDescTitle') }}</h3>
            <ul class="check-list">
              <li v-for="(item, index) in realData" :key="index">
                <el-icon><Check /></el-icon>
                <span>{{ item.title }}</span>
              </li>
            </ul>
            <p class="desc-text">
              {{ t('home.realDataSummary') }}
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- Facebook Platform Section -->
    <section class="facebook-section">
      <div class="section-container">
        <div class="section-title">
          <h2>{{ t('home.platforms') }}</h2>
          <p>{{ t('home.platformsDesc') }}</p>
        </div>
        
        <div class="facebook-intro-card">
          <div class="facebook-main-info">
            <div class="facebook-logo-large">
              <svg viewBox="0 0 24 24" fill="#fff" width="44" height="44">
                <path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/>
              </svg>
            </div>
            <div class="facebook-title-area">
              <h3>{{ t('home.platformFacebook') }}</h3>
              <p class="facebook-desc">{{ t('home.platformFacebookDesc') }}</p>
              <div class="facebook-feature-tags">
                <span class="feature-tag">{{ t('home.platformFacebookDetail') }}</span>
              </div>
            </div>
          </div>

          <div class="facebook-content">
            <div class="content-left">
              <h4>{{ t('home.facebookTitle') }}</h4>
              <p>{{ t('home.facebookIntro') }}</p>
              
              <div class="facebook-stats">
                <div class="stat-item-facebook">
                  <div class="stat-num">{{ t('home.facebookStat1') }}</div>
                  <div class="stat-label">{{ t('home.facebookStat1Label') }}</div>
                </div>
                <div class="stat-item-facebook">
                  <div class="stat-num">{{ t('home.facebookStat2') }}</div>
                  <div class="stat-label">{{ t('home.facebookStat2Label') }}</div>
                </div>
                <div class="stat-item-facebook">
                  <div class="stat-num">{{ t('home.facebookStat3') }}</div>
                  <div class="stat-label">{{ t('home.facebookStat3Label') }}</div>
                </div>
                <div class="stat-item-facebook">
                  <div class="stat-num">{{ t('home.facebookStat4') }}</div>
                  <div class="stat-label">{{ t('home.facebookStat4Label') }}</div>
                </div>
              </div>
            </div>

            <div class="content-right">
              <h4>{{ t('home.facebookFeatures') }}</h4>
              <div class="feature-grid">
                <div class="feature-item">
                  <div class="feature-icon-box">🎯</div>
                  <div class="feature-info">
                    <strong>{{ t('home.facebookFeature1') }}</strong>
                    <span>{{ t('home.facebookFeature1Desc') }}</span>
                  </div>
                </div>
                <div class="feature-item">
                  <div class="feature-icon-box">📱</div>
                  <div class="feature-info">
                    <strong>{{ t('home.facebookFeature2') }}</strong>
                    <span>{{ t('home.facebookFeature2Desc') }}</span>
                  </div>
                </div>
                <div class="feature-item">
                  <div class="feature-icon-box">👥</div>
                  <div class="feature-info">
                    <strong>{{ t('home.facebookFeature3') }}</strong>
                    <span>{{ t('home.facebookFeature3Desc') }}</span>
                  </div>
                </div>
                <div class="feature-item">
                  <div class="feature-icon-box">🛒</div>
                  <div class="feature-info">
                    <strong>{{ t('home.facebookFeature4') }}</strong>
                    <span>{{ t('home.facebookFeature4Desc') }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="facebook-why">
            <h4>{{ t('home.facebookWhyUs') }}</h4>
            <p>{{ t('home.facebookWhyDesc') }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA Section -->
    <section class="cta-section">
      <div class="section-container">
        <div class="cta-card">
          <h2>{{ t('home.ctaMainTitle') }}</h2>
          <p>{{ t('home.ctaMainDesc') }}</p>
          <div class="cta-actions">
            <router-link to="/contact" class="btn-primary-large">{{ t('home.ctaBtn1') }}</router-link>
            <router-link to="/custom" class="btn-outline-large">{{ t('home.ctaBtn2') }}</router-link>
          </div>
        </div>
      </div>
    </section>

    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import { Check, ArrowRight } from '@element-plus/icons-vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n

const statsList = computed(() => [
  { id: 1, statValue: t('home.statValue1'), statLabel: t('home.statLabel1') },
  { id: 2, statValue: t('home.statValue2'), statLabel: t('home.statLabel2') },
  { id: 3, statValue: t('home.statValue3'), statLabel: t('home.statLabel3') },
  { id: 4, statValue: t('home.statValue4'), statLabel: t('home.statLabel4') }
])

const previewFeatures = computed(() => [
  { icon: t('home.feature1Icon'), title: t('home.feature1Title'), description: t('home.feature1Desc'), tags: [t('home.feature1Tag1'), t('home.feature1Tag2')] },
  { icon: t('home.feature2Icon'), title: t('home.feature2Title'), description: t('home.feature2Desc'), tags: [t('home.feature2Tag1'), t('home.feature2Tag2')] },
  { icon: t('home.feature3Icon'), title: t('home.feature3Title'), description: t('home.feature3Desc'), tags: [t('home.feature3Tag1'), t('home.feature3Tag2')] }
])

const realData = computed(() => [
  { badge: 'A', title: t('home.realData1Title'), desc: t('home.realData1Desc'), progress: 85 },
  { badge: 'B', title: t('home.realData2Title'), desc: t('home.realData2Desc'), progress: 72 },
  { badge: 'C', title: t('home.realData3Title'), desc: t('home.realData3Desc'), progress: 90 }
])

onMounted(() => {
})
</script>

<style lang="scss" scoped>
.home-page {
  padding-top: 70px;
}

.hero-section {
  min-height: 90vh;
  position: relative;
  padding: 60px 0 120px;
  overflow: hidden;
  background: linear-gradient(135deg, #021b79 0%, #0575e6 55%, #0e6df0 100%);
}

.cyber-bg-grid {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image:
    linear-gradient(rgba(255, 255, 255, 0.04) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255, 255, 255, 0.04) 1px, transparent 1px);
  background-size: 50px 50px;
  pointer-events: none;
}

.hero-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: grid;
  grid-template-columns: 1.2fr 1fr;
  gap: 60px;
  align-items: center;
  position: relative;
  z-index: 1;
}

.hero-tag {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  background: rgba(255, 255, 255, 0.12);
  border: 1px solid rgba(255, 255, 255, 0.3);
  padding: 8px 20px;
  border-radius: 30px;
  font-size: 14px;
  color: #ffffff;
  margin-bottom: 24px;

  .tag-dot {
    width: 8px;
    height: 8px;
    background: #ffffff;
    border-radius: 50%;
    animation: pulse 1.5s infinite;
  }
}

@keyframes pulse {
  0%, 100% { opacity: 1; transform: scale(1); }
  50% { opacity: 0.5; transform: scale(1.2); }
}

.hero-title {
  font-size: 34px;
  margin-bottom: 24px;
  line-height: 1.3;

  .title-gradient {
    background: linear-gradient(135deg, #ffffff, #bfe3ff);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    display: block;
  }

  .title-sub {
    display: block;
    font-size: 24px;
    color: rgba(255, 255, 255, 0.92);
    margin-top: 8px;
  }
}

.hero-intro {
  margin-bottom: 8px;

  h2 {
    font-size: 26px;
    margin-bottom: 12px;
    color: #ffffff;
    font-weight: 700;
    line-height: 1.3;
  }
}

.hero-actions {
  display: flex;
  gap: 16px;
  margin-top: 32px;
}

.btn-primary {
  background: #ffffff;
  color: #0575e6;
  padding: 14px 32px;
  border-radius: 12px;
  font-weight: 700;
  text-decoration: none;
  transition: all 0.3s ease;
  display: inline-flex;
  align-items: center;
  gap: 8px;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 12px 30px rgba(0, 0, 0, 0.25);
  }
}

.btn-secondary {
  background: rgba(255, 255, 255, 0.12);
  border: 1px solid rgba(255, 255, 255, 0.4);
  color: #ffffff;
  padding: 14px 32px;
  border-radius: 12px;
  font-weight: 700;
  text-decoration: none;
  transition: all 0.3s ease;

  &:hover {
    background: rgba(255, 255, 255, 0.2);
  }
}

.hero-visual {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.hero-text {
  max-width: 600px;
}

.hero-desc {
  color: rgba(255, 255, 255, 0.85);
  font-size: 15px;
  line-height: 1.85;
  margin: 12px 0 28px;
  max-width: 580px;
}

.hero-illustration {
  position: relative;
  width: 100%;
  max-width: 500px;
  min-height: 560px;
}

.illustration-card {
  position: relative;
  background: rgba(255, 255, 255, 0.96);
  border: 1px solid rgba(255, 255, 255, 0.4);
  border-radius: 28px;
  padding: 32px 220px 180px 32px;
  box-shadow:
    0 24px 60px rgba(2, 27, 121, 0.35),
    inset 0 1px 0 rgba(255, 255, 255, 0.9);
  overflow: visible;
  backdrop-filter: blur(10px);
}

.card-glow {
  position: absolute;
  top: -60%;
  right: -10%;
  width: 70%;
  height: 120%;
  background: radial-gradient(ellipse at 70% 30%, rgba(14, 109, 240, 0.12) 0%, transparent 65%);
  pointer-events: none;
  z-index: 0;
}

.card-swirleffect {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 60%;
  background:
    radial-gradient(ellipse 60% 40% at 10% 100%, rgba(14, 109, 240, 0.1) 0%, transparent 70%),
    radial-gradient(ellipse 50% 35% at 90% 90%, rgba(79, 124, 255, 0.1) 0%, transparent 70%);
  pointer-events: none;
  z-index: 0;
  border-radius: 28px;
}

.check-list {
  display: flex;
  flex-direction: column;
  gap: 14px;
  position: relative;
  z-index: 10;
}

.check-item {
  display: flex;
  align-items: center;
  gap: 14px;
  background: #f0f7ff;
  border: 1px solid #e2e8f0;
  border-radius: 14px;
  padding: 16px 18px;
  transition: all 0.3s ease;

  &:hover {
    background: #e5f1ff;
    border-color: #0e6df0;
    transform: translateX(4px);
  }
}

.check-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 32px;
  height: 32px;
  min-width: 32px;
  background: linear-gradient(135deg, #22c55e, #16a34a);
  border-radius: 50%;
  color: #fff;
  font-weight: 800;
  font-size: 15px;
  flex-shrink: 0;
  box-shadow: 0 4px 12px rgba(34, 197, 94, 0.35);
}

.check-text {
  color: #0f172a;
  font-size: 14px;
  line-height: 1.55;
}

.floating-screens {
  position: absolute;
  top: 10%;
  right: 8px;
  width: 200px;
  height: 200px;
  pointer-events: none;
  z-index: 5;
}

.screen-panel {
  position: absolute;
  border-radius: 10px;
  animation: floatScreens 3.5s ease-in-out infinite;
  filter: drop-shadow(0 6px 16px rgba(14, 109, 240, 0.3));
}

.screen-panel-1 {
  width: 110px;
  height: 72px;
  top: 8px;
  right: 100px;
  transform: rotate(-6deg);
}

.screen-panel-2 {
  width: 90px;
  height: 62px;
  top: 0;
  right: -8px;
  transform: rotate(4deg);
}

.screen-panel-3 {
  width: 130px;
  height: 72px;
  top: 92px;
  right: -18px;
  transform: rotate(2deg);
}

.float-delay-1 { animation-delay: 0s; }
.float-delay-2 { animation-delay: 0.8s; }
.float-delay-3 { animation-delay: 1.6s; }

@keyframes floatScreens {
  0%, 100% { transform: translateY(0) rotate(var(--rot, 0deg)); }
  50%      { transform: translateY(-10px) rotate(var(--rot, 0deg)); }
}
.screen-panel-1 { transform: rotate(-6deg); --rot: -6deg; }
.screen-panel-2 { transform: rotate(4deg);  --rot: 4deg;  }
.screen-panel-3 { transform: rotate(2deg);  --rot: 2deg;  }

.illustration-scene {
  position: absolute;
  right: -20px;
  bottom: -10px;
  width: 320px;
  height: 270px;
  z-index: 8;
  pointer-events: none;
}

.glow-platform {
  position: absolute;
  left: 50px;
  bottom: 10px;
  width: 250px;
  height: 90px;
}

.ring {
  position: absolute;
  left: 50%;
  bottom: 20px;
  transform: translateX(-50%) rotateX(70deg);
  border-radius: 50%;
  border-style: solid;
  border-color: transparent;
}

.ring-1 {
  width: 220px;
  height: 50px;
  bottom: 15px;
  border-top-color: rgba(14, 109, 240, 0.35);
  border-top-width: 2px;
  animation: ringPulse 3s ease-in-out infinite;
}

.ring-2 {
  width: 180px;
  height: 42px;
  bottom: 22px;
  border-top-color: rgba(79, 124, 255, 0.45);
  border-top-width: 2px;
  animation: ringPulse 3s ease-in-out infinite 0.5s;
}

.ring-3 {
  width: 140px;
  height: 34px;
  bottom: 28px;
  border-top-color: rgba(14, 109, 240, 0.5);
  border-top-width: 2px;
  animation: ringPulse 3s ease-in-out infinite 1s;
}

@keyframes ringPulse {
  0%, 100% { opacity: 0.5; transform: translateX(-50%) rotateX(70deg) scale(1); }
  50%      { opacity: 1;   transform: translateX(-50%) rotateX(70deg) scale(1.06); }
}

.ring-glow {
  position: absolute;
  left: 50%;
  bottom: 10px;
  transform: translateX(-50%);
  width: 280px;
  height: 80px;
  background: radial-gradient(ellipse 60% 40% at 50% 60%, rgba(14, 109, 240, 0.25) 0%, rgba(79, 124, 255, 0.18) 40%, transparent 75%);
  filter: blur(4px);
}

.phone-device {
  position: absolute;
  right: 120px;
  bottom: 70px;
  width: 64px;
  height: 124px;
  transform: rotate(-4deg);
  filter: drop-shadow(0 10px 24px rgba(0, 0, 0, 0.35));
  z-index: 10;
  animation: phoneTilt 4s ease-in-out infinite;
}

@keyframes phoneTilt {
  0%, 100% { transform: rotate(-4deg) translateY(0); }
  50%      { transform: rotate(-2deg) translateY(-6px); }
}

.robot-figure {
  position: absolute;
  right: 0px;
  bottom: 40px;
  width: 150px;
  height: 170px;
  z-index: 11;
  filter: drop-shadow(0 12px 28px rgba(0, 0, 0, 0.4));
  animation: robotBreath 3.2s ease-in-out infinite;
}

@keyframes robotBreath {
  0%, 100% { transform: translateY(0); }
  50%      { transform: translateY(-7px); }
}

.illustration-cta {
  position: absolute;
  left: 12px;
  bottom: 22px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #0575e6 0%, #021b79 100%);
  color: white;
  padding: 14px 34px;
  border-radius: 16px;
  font-weight: 700;
  font-size: 15px;
  text-decoration: none;
  transition: all 0.3s ease;
  box-shadow: 0 8px 24px rgba(2, 27, 121, 0.35);
  z-index: 20;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 14px 36px rgba(2, 27, 121, 0.5);
  }
}

.wave-decoration {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  line-height: 0;

  svg {
    width: 100%;
    height: 120px;
  }
}

.stats-section {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #f8fafc;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
  margin-bottom: 60px;
}

.stat-card {
  text-align: center;
  padding: 32px 20px;
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  box-shadow: 0 8px 24px rgba(14, 109, 240, 0.06);
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-5px);
    border-color: #0e6df0;
    box-shadow: 0 12px 30px rgba(14, 109, 240, 0.12);
  }

  .stat-value {
    font-size: 42px;
    font-weight: 800;
    background: linear-gradient(135deg, #0575e6, #021b79);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    margin-bottom: 8px;
  }

  .stat-label {
    color: #475569;
    font-size: 16px;
  }
}

.stats-desc {
  max-width: 800px;
  margin: 0 auto;
  text-align: center;

  p {
    color: #475569;
    font-size: 16px;
    margin-bottom: 20px;
  }

  .check-list {
    list-style: none;
    padding: 0;
    text-align: left;
    max-width: 600px;
    margin: 0 auto 24px;

    li {
      display: flex;
      align-items: center;
      gap: 12px;
      padding: 10px 0;
      color: #334155;

      .el-icon {
        color: #22c55e;
        font-size: 18px;
      }
    }
  }

  .highlight-text {
    color: #0e6df0;
    font-weight: 600;
  }
}

.features-preview {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #ffffff;
}

.feature-cards-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
  margin-bottom: 40px;
}

.feature-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 32px;
  box-shadow: 0 8px 24px rgba(14, 109, 240, 0.05);
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-5px);
    border-color: #0e6df0;
    box-shadow: 0 12px 30px rgba(14, 109, 240, 0.12);
  }

  .feature-icon {
    font-size: 48px;
    margin-bottom: 16px;
  }

  h3 {
    font-size: 20px;
    margin-bottom: 12px;
    color: #0f172a;
  }

  p {
    color: #475569;
    font-size: 14px;
    margin-bottom: 16px;
    line-height: 1.6;
  }

  .feature-tags-row {
    display: flex;
    gap: 8px;
    flex-wrap: wrap;

    .mini-tag {
      background: #e5f1ff;
      border: 1px solid #cbe4ff;
      color: #0e6df0;
      padding: 4px 12px;
      border-radius: 20px;
      font-size: 12px;
    }
  }
}

.view-more {
  text-align: center;

  .view-more-btn {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    color: #0e6df0;
    font-weight: 700;
    text-decoration: none;
    transition: gap 0.3s ease;

    &:hover {
      gap: 12px;
    }
  }
}

.real-data-section {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #f8fafc;
}

.real-data-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 48px;
  align-items: center;
}

.data-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 24px;
  box-shadow: 0 8px 24px rgba(14, 109, 240, 0.05);

  h4 {
    color: #0e6df0;
    font-size: 18px;
    margin-bottom: 20px;
    padding-bottom: 12px;
    border-bottom: 1px solid #e2e8f0;
  }
}

.data-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px 0;
  border-bottom: 1px solid #f1f5f9;

  &:last-child {
    border-bottom: none;
  }

  .data-badge {
    width: 36px;
    height: 36px;
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: 700;
    color: #fff;
    flex-shrink: 0;

    &.badge-A { background: linear-gradient(135deg, #0575e6, #021b79); }
    &.badge-B { background: linear-gradient(135deg, #4f7cff, #7c3aed); }
    &.badge-C { background: linear-gradient(135deg, #22c55e, #16a34a); }
  }

  .data-info {
    flex: 1;

    strong {
      display: block;
      color: #0f172a;
      font-size: 14px;
      margin-bottom: 4px;
    }

    span {
      color: #64748b;
      font-size: 12px;
    }
  }

  .data-bar {
    width: 100px;
    height: 6px;
    background: #e2e8f0;
    border-radius: 3px;
    overflow: hidden;

    .bar-fill {
      height: 100%;
      background: linear-gradient(90deg, #0575e6, #021b79);
      border-radius: 3px;
      transition: width 1s ease;
    }
  }
}

.data-desc {
  h3 {
    color: #0f172a;
    font-size: 22px;
    margin-bottom: 20px;
  }

  .check-list {
    list-style: none;
    padding: 0;
    margin-bottom: 20px;

    li {
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 10px 0;
      color: #334155;

      .el-icon {
        color: #22c55e;
        flex-shrink: 0;
      }
    }
  }

  .desc-text {
    color: #64748b;
    font-size: 14px;
    line-height: 1.6;
  }
}

.facebook-section {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #ffffff;
}

.facebook-intro-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 24px;
  padding: 40px;
  box-shadow: 0 8px 30px rgba(24, 119, 242, 0.08);
}

.facebook-main-info {
  display: flex;
  align-items: center;
  gap: 24px;
  padding-bottom: 32px;
  border-bottom: 1px solid #eef2f7;
  margin-bottom: 32px;
}

.facebook-logo-large {
  width: 80px;
  height: 80px;
  background: #1877F2;
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  box-shadow: 0 10px 30px rgba(24, 119, 242, 0.35);

  svg {
    width: 48px;
    height: 48px;
  }
}

.facebook-title-area {
  flex: 1;

  h3 {
    font-size: 28px;
    color: #0f172a;
    margin-bottom: 8px;
  }

  .facebook-desc {
    color: #475569;
    font-size: 16px;
    margin-bottom: 12px;
  }

  .facebook-feature-tags {
    display: flex;
    gap: 8px;
    flex-wrap: wrap;

    .feature-tag {
      background: #e5f1ff;
      border: 1px solid #cbe4ff;
      color: #1877F2;
      padding: 6px 16px;
      border-radius: 20px;
      font-size: 13px;
    }
  }
}

.facebook-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 40px;
  margin-bottom: 32px;
}

.content-left,
.content-right {
  h4 {
    color: #0e6df0;
    font-size: 20px;
    margin-bottom: 16px;
  }

  p {
    color: #475569;
    line-height: 1.8;
    margin-bottom: 20px;
  }
}

.facebook-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.stat-item-facebook {
  background: #f0f7ff;
  border: 1px solid #dbeafe;
  border-radius: 12px;
  padding: 20px;
  text-align: center;

  .stat-num {
    font-size: 32px;
    font-weight: 700;
    color: #1877F2;
    margin-bottom: 4px;
  }

  .stat-label {
    font-size: 13px;
    color: #475569;
  }
}

.feature-grid {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.feature-item {
  display: flex;
  gap: 16px;
  padding: 16px;
  background: #f8fafc;
  border-radius: 12px;
  transition: all 0.3s ease;

  &:hover {
    background: #f0f7ff;
    transform: translateX(5px);
  }

  .feature-icon-box {
    width: 44px;
    height: 44px;
    background: #e5f1ff;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 22px;
    flex-shrink: 0;
  }

  .feature-info {
    strong {
      display: block;
      color: #0f172a;
      font-size: 15px;
      margin-bottom: 4px;
    }

    span {
      font-size: 13px;
      color: #64748b;
      line-height: 1.5;
    }
  }
}

.facebook-why {
  text-align: center;
  padding-top: 24px;
  border-top: 1px solid #eef2f7;

  h4 {
    color: #0e6df0;
    font-size: 20px;
    margin-bottom: 12px;
  }

  p {
    color: #475569;
    font-size: 16px;
    max-width: 600px;
    margin: 0 auto;
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
}

@keyframes scan {
  from { left: -100%; }
  to { left: 100%; }
}

.cta-card h2 {
  font-size: 32px;
  margin-bottom: 16px;
  color: #ffffff;
}

.cta-card p {
  color: rgba(255, 255, 255, 0.85);
  font-size: 16px;
  margin-bottom: 32px;
}

.cta-actions {
  display: flex;
  justify-content: center;
  gap: 16px;
}

.btn-primary-large {
  background: #ffffff;
  color: #0575e6;
  padding: 16px 40px;
  border-radius: 12px;
  font-weight: 700;
  text-decoration: none;
  font-size: 16px;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
  }
}

.btn-outline-large {
  background: rgba(255, 255, 255, 0.12);
  border: 2px solid rgba(255, 255, 255, 0.4);
  color: #ffffff;
  padding: 16px 40px;
  border-radius: 12px;
  font-weight: 700;
  text-decoration: none;
  font-size: 16px;
  transition: all 0.3s ease;

  &:hover {
    border-color: #ffffff;
    color: #ffffff;
    background: rgba(255, 255, 255, 0.2);
  }
}

.section-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

.section-title {
  text-align: center;
  margin-bottom: 48px;

  h2 {
    font-size: 34px;
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

@media (max-width: 968px) {
  .hero-content {
    grid-template-columns: 1fr;
    gap: 40px;
  }

  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .feature-cards-grid {
    grid-template-columns: 1fr;
  }

  .real-data-content {
    grid-template-columns: 1fr;
  }

  .platforms-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .cta-card h2 {
    font-size: 24px;
  }
}

@media (max-width: 600px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }

  .platforms-grid {
    grid-template-columns: 1fr;
  }

  .hero-actions,
  .cta-actions {
    flex-direction: column;
  }

  .hero-title {
    font-size: 24px;
  }
}

@media (max-width: 768px) {
  .facebook-main-info {
    flex-direction: column;
    text-align: center;
  }

  .facebook-content {
    grid-template-columns: 1fr;
  }

  .facebook-stats {
    grid-template-columns: repeat(2, 1fr);
  }
}
</style>