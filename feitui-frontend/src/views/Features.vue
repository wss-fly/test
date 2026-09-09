<template>
  <div class="features-page">
    <NavBar />
    
    <!-- Page Hero -->
    <section class="page-hero">
      <div class="hero-content">
        <h1>{{ t('features.pageTitle') }}</h1>
        <div class="breadcrumb">
          <router-link to="/">{{ t('features.breadcrumbHome') }}</router-link>
          <span class="separator">›</span>
          <span class="current">{{ t('features.breadcrumbCurrent') }}</span>
        </div>
      </div>
    </section>

    <!-- Core Features -->
    <section class="core-features">
      <div class="section-container">
        <div class="section-header">
          <div class="section-tag">
            <span class="tag-icon">⚙️</span>
            <span>{{ t('features.tagCore') }}</span>
          </div>
          <h2>{{ t('features.mainTitle') }}</h2>
        </div>
        
        <div class="features-grid">
          <div v-for="(feature, index) in coreFeatures" :key="index" class="feature-card" :class="'card-' + feature.color">
            <div class="card-header">
              <span class="feature-icon">{{ feature.icon }}</span>
              <h3>{{ feature.title }}</h3>
              <span class="expand-icon">›</span>
            </div>
            <div class="card-body">
              <p>{{ feature.description }}</p>
              <div class="feature-tags">
                <span v-for="(tag, tIdx) in feature.tags" :key="tIdx" class="f-tag">
                  <el-icon><Check /></el-icon>
                  {{ tag }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Highlights Section -->
    <section class="highlights-section">
      <div class="section-container">
        <div class="highlights-grid">
          <div class="highlights-panel">
            <h3 class="panel-title">{{ t('features.highlightsTitle') }}</h3>
            <div class="highlight-tags">
              <span v-for="(item, index) in highlights" :key="index" 
                    class="highlight-tag" 
                    :style="{ '--tag-color': item.color }">
                {{ item.name }}
              </span>
            </div>
          </div>
          
          <div class="platforms-panel">
            <h3 class="panel-title">{{ t('features.platformsTitle') }}</h3>
            <div class="platform-list">
              <div class="platform-item facebook-item">
                <div class="platform-logo" style="background: linear-gradient(135deg, #1877F2, #0c44a1)">
                  <svg viewBox="0 0 24 24" fill="#fff" width="20" height="20">
                    <path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/>
                  </svg>
                </div>
                <div class="platform-info">
                  <strong>{{ t('home.platformFacebook') }}</strong>
                  <span>{{ t('home.platformFacebookDetail') }}</span>
                </div>
                <el-icon class="check-icon"><CircleCheck /></el-icon>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Detailed Features -->
    <section class="detailed-features">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('features.detailedTitle') }}</h2>
          <p>{{ t('features.detailedDesc') }}</p>
        </div>
        
        <div class="feature-accordion">
          <div v-for="(detail, index) in detailedFeatures" :key="index" 
               class="accordion-item"
               :class="{ active: activeIndex === index }">
            <div class="accordion-header" @click="toggleAccordion(index)">
              <span class="accordion-icon">{{ detail.icon }}</span>
              <span class="accordion-title">{{ detail.title }}</span>
              <span class="accordion-arrow" :class="{ rotated: activeIndex === index }">›</span>
            </div>
            <div class="accordion-body" v-show="activeIndex === index">
              <p>{{ detail.content }}</p>
              <ul class="feature-list">
                <li v-for="(item, i) in detail.items" :key="i">
                  <el-icon><Check /></el-icon>
                  {{ item }}
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA -->
    <section class="cta-section">
      <div class="section-container">
        <div class="cta-card">
          <h2>{{ t('features.ctaTitle') }}</h2>
          <p>{{ t('features.ctaDesc') }}</p>
          <router-link to="/contact" class="cta-btn">{{ t('features.ctaBtn') }}</router-link>
        </div>
      </div>
    </section>

    <Footer />
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import { Check, CircleCheck } from '@element-plus/icons-vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n

const activeIndex = ref(0)

const toggleAccordion = (index) => {
  activeIndex.value = activeIndex.value === index ? -1 : index
}

const coreFeatures = computed(() => [
  { 
    icon: '📝', 
    title: t('features.coreFeature1Title'), 
    description: t('features.coreFeature1Desc'), 
    tags: [t('features.coreFeature1Tag1'), t('features.coreFeature1Tag2'), t('features.coreFeature1Tag3')],
    color: 'cyan'
  },
  { 
    icon: '👆', 
    title: t('features.coreFeature2Title'), 
    description: t('features.coreFeature2Desc'), 
    tags: [t('features.coreFeature2Tag1'), t('features.coreFeature2Tag2'), t('features.coreFeature2Tag3')],
    color: 'purple'
  },
  { 
    icon: '💬', 
    title: t('features.coreFeature3Title'), 
    description: t('features.coreFeature3Desc'), 
    tags: [t('features.coreFeature3Tag1'), t('features.coreFeature3Tag2'), t('features.coreFeature3Tag3')],
    color: 'green'
  },
  { 
    icon: '👤', 
    title: t('features.coreFeature4Title'), 
    description: t('features.coreFeature4Desc'), 
    tags: [t('features.coreFeature4Tag1'), t('features.coreFeature4Tag2'), t('features.coreFeature4Tag3')],
    color: 'blue'
  },
  { 
    icon: '👥', 
    title: t('features.coreFeature5Title'), 
    description: t('features.coreFeature5Desc'), 
    tags: [t('features.coreFeature5Tag1'), t('features.coreFeature5Tag2'), t('features.coreFeature5Tag3')],
    color: 'orange'
  },
  { 
    icon: '🔍', 
    title: t('features.coreFeature6Title'), 
    description: t('features.coreFeature6Desc'), 
    tags: [t('features.coreFeature6Tag1'), t('features.coreFeature6Tag2'), t('features.coreFeature6Tag3')],
    color: 'pink'
  },
  { 
    icon: '🔎', 
    title: t('features.coreFeature7Title'), 
    description: t('features.coreFeature7Desc'), 
    tags: [t('features.coreFeature7Tag1'), t('features.coreFeature7Tag2'), t('features.coreFeature7Tag3')],
    color: 'red'
  }
])

const highlights = computed(() => [
  { name: t('features.highlight1'), color: '#0e6df0' },
  { name: t('features.highlight2'), color: '#4f7cff' },
  { name: t('features.highlight3'), color: '#22c55e' },
  { name: t('features.highlight4'), color: '#3b82f6' },
  { name: t('features.highlight5'), color: '#f97316' },
  { name: t('features.highlight6'), color: '#ec4899' },
  { name: t('features.highlight7'), color: '#f43f5e' },
  { name: t('features.highlight8'), color: '#06b6d4' },
  { name: t('features.highlight9'), color: '#10b981' },
  { name: t('features.highlight10'), color: '#6366f1' },
  { name: t('features.highlight11'), color: '#eab308' },
  { name: t('features.highlight12'), color: '#8b5cf6' }
])

const detailedFeatures = computed(() => [
  {
    icon: '📝',
    title: t('features.detail1Title'),
    content: t('features.detail1Content'),
    items: [
      t('features.detail1Item1'),
      t('features.detail1Item2'),
      t('features.detail1Item3'),
      t('features.detail1Item4')
    ]
  },
  {
    icon: '👆',
    title: t('features.detail2Title'),
    content: t('features.detail2Content'),
    items: [
      t('features.detail2Item1'),
      t('features.detail2Item2'),
      t('features.detail2Item3'),
      t('features.detail2Item4')
    ]
  },
  {
    icon: '💬',
    title: t('features.detail3Title'),
    content: t('features.detail3Content'),
    items: [
      t('features.detail3Item1'),
      t('features.detail3Item2'),
      t('features.detail3Item3'),
      t('features.detail3Item4')
    ]
  },
  {
    icon: '👤',
    title: t('features.detail4Title'),
    content: t('features.detail4Content'),
    items: [
      t('features.detail4Item1'),
      t('features.detail4Item2'),
      t('features.detail4Item3'),
      t('features.detail4Item4')
    ]
  },
  {
    icon: '👥',
    title: t('features.detail5Title'),
    content: t('features.detail5Content'),
    items: [
      t('features.detail5Item1'),
      t('features.detail5Item2'),
      t('features.detail5Item3'),
      t('features.detail5Item4')
    ]
  },
  {
    icon: '🔍',
    title: t('features.detail6Title'),
    content: t('features.detail6Content'),
    items: [
      t('features.detail6Item1'),
      t('features.detail6Item2'),
      t('features.detail6Item3'),
      t('features.detail6Item4'),
      t('features.detail6Item5')
    ]
  },
  {
    icon: '🔎',
    title: t('features.detail7Title'),
    content: t('features.detail7Content'),
    items: [
      t('features.detail7Item1'),
      t('features.detail7Item2'),
      t('features.detail7Item3'),
      t('features.detail7Item4'),
      t('features.detail7Item5')
    ]
  }
])
</script>

<style lang="scss" scoped>
.features-page {
  padding-top: 70px;
}

.page-hero {
  height: 240px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  background: linear-gradient(180deg, rgba(14, 109, 240, 0.06) 0%, transparent 100%);
}

.hero-content {
  position: relative;
  z-index: 1;
  text-align: center;

  h1 {
    font-size: 48px;
    margin-bottom: 16px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
  }

  .breadcrumb {
    color: #475569;
    font-size: 15px;

    a {
      color: #475569;
      text-decoration: none;

      &:hover {
        color: #0e6df0;
      }
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

.core-features {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #ffffff;
}

.section-header {
  text-align: center;
  margin-bottom: 48px;

  .section-tag {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    background: rgba(14, 109, 240, 0.08);
    border: 1px solid rgba(14, 109, 240, 0.25);
    padding: 8px 20px;
    border-radius: 30px;
    color: #0e6df0;
    font-size: 14px;
    margin-bottom: 20px;

    .tag-icon {
      font-size: 16px;
    }
  }

  h2 {
    font-size: 36px;
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

.features-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.feature-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  overflow: hidden;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 20px 40px rgba(14, 109, 240, 0.12);
  }

  &.card-purple {
    border-color: rgba(79, 124, 255, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(79, 124, 255, 0.08), transparent); }
  }

  &.card-cyan {
    border-color: rgba(14, 109, 240, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(14, 109, 240, 0.08), transparent); }
  }

  &.card-green {
    border-color: rgba(34, 197, 94, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(34, 197, 94, 0.08), transparent); }
  }

  &.card-blue {
    border-color: rgba(59, 130, 246, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(59, 130, 246, 0.08), transparent); }
  }

  &.card-orange {
    border-color: rgba(249, 115, 22, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(249, 115, 22, 0.08), transparent); }
  }

  &.card-pink {
    border-color: rgba(236, 72, 153, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(236, 72, 153, 0.08), transparent); }
  }

  &.card-red {
    border-color: rgba(244, 63, 94, 0.35);
    .card-header { background: linear-gradient(90deg, rgba(244, 63, 94, 0.08), transparent); }
  }
}

.card-header {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 20px 24px;
  transition: all 0.3s ease;

  .feature-icon {
    font-size: 36px;
  }

  h3 {
    flex: 1;
    color: #0f172a;
    font-size: 18px;
    margin: 0;
  }

  .expand-icon {
    font-size: 24px;
    color: #94a3b8;
  }
}

.card-body {
  padding: 0 24px 24px;

  p {
    color: #475569;
    font-size: 14px;
    line-height: 1.6;
    margin-bottom: 16px;
  }

  .feature-tags {
    display: flex;
    gap: 8px;
    flex-wrap: wrap;

    .f-tag {
      display: inline-flex;
      align-items: center;
      gap: 4px;
      background: #f1f5f9;
      padding: 6px 12px;
      border-radius: 20px;
      font-size: 12px;
      color: #475569;

      .el-icon {
        color: #22c55e;
        font-size: 14px;
      }
    }
  }
}

.highlights-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
  background: linear-gradient(135deg, #eff6ff, #f8fbff);
}

.highlights-grid {
  display: grid;
  grid-template-columns: 1.2fr 1fr;
  gap: 24px;
}

.highlights-panel,
.platforms-panel {
  background: rgba(15, 23, 42, 0.8);
  border: 1px solid rgba(0, 212, 255, 0.2);
  border-radius: 16px;
  padding: 32px;
}

.panel-title {
  text-align: center;
  font-size: 22px;
  color: #0e6df0;
  margin-bottom: 24px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(14, 109, 240, 0.2);
}

.highlight-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  justify-content: center;

  .highlight-tag {
    background: var(--tag-color, #0e6df0);
    color: #fff;
    padding: 10px 20px;
    border-radius: 24px;
    font-size: 14px;
    font-weight: 500;
    transition: all 0.3s ease;
    cursor: pointer;

    &:hover {
      transform: scale(1.05);
      box-shadow: 0 5px 20px var(--tag-color);
    }
  }
}

.platform-list {
  .platform-item {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 16px 0;
    border-bottom: 1px solid #e2e8f0;

    &:last-child {
      border-bottom: none;
    }

    .platform-logo {
      width: 44px;
      height: 44px;
      border-radius: 10px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: white;
      font-weight: 700;
      font-size: 18px;
      flex-shrink: 0;
    }

    .platform-info {
      flex: 1;

      strong {
        display: block;
        color: #0f172a;
        font-size: 15px;
        margin-bottom: 2px;
      }

      span {
        color: #64748b;
        font-size: 13px;
      }
    }

    .check-icon {
      color: #22c55e;
      font-size: 20px;
    }
  }
}

.detailed-features {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #ffffff;
}

.feature-accordion {
  max-width: 800px;
  margin: 0 auto;
}

.accordion-item {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  margin-bottom: 16px;
  overflow: hidden;
  transition: all 0.3s ease;

  &.active {
    border-color: rgba(14, 109, 240, 0.4);
    background: #ffffff;
  }
}

.accordion-header {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 20px 24px;
  cursor: pointer;
  transition: background 0.3s ease;

  &:hover {
    background: rgba(14, 109, 240, 0.04);
  }

  .accordion-icon {
    font-size: 28px;
  }

  .accordion-title {
    flex: 1;
    color: #0f172a;
    font-size: 17px;
    font-weight: 600;
  }

  .accordion-arrow {
    font-size: 24px;
    color: #94a3b8;
    transition: transform 0.3s ease;

    &.rotated {
      transform: rotate(90deg);
      color: #0e6df0;
    }
  }
}

.accordion-body {
  padding: 0 24px 24px;

  p {
    color: #475569;
    line-height: 1.7;
    margin-bottom: 16px;
  }

  .feature-list {
    list-style: none;
    padding: 0;

    li {
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 8px 0;
      color: #475569;
      font-size: 14px;

      .el-icon {
        color: #22c55e;
      }
    }
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

.cta-btn {
  display: inline-block;
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

.section-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

@media (max-width: 968px) {
  .features-grid {
    grid-template-columns: 1fr;
  }

  .highlights-grid {
    grid-template-columns: 1fr;
  }

  .hero-content h1 {
    font-size: 32px;
  }
}
</style>
