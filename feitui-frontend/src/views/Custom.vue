<template>
  <div class="custom-page">
    <NavBar />
    
    <!-- Page Hero -->
    <section class="page-hero">
      <div class="hero-content">
        <h1>{{ t('custom.pageTitle') }}</h1>
        <div class="breadcrumb">
          <router-link to="/">{{ t('custom.breadcrumbHome') }}</router-link>
          <span class="separator">›</span>
          <span class="current">{{ t('custom.breadcrumbCurrent') }}</span>
        </div>
      </div>
    </section>

    <!-- Intro Section -->
    <section class="intro-section">
      <div class="section-container">
        <div class="intro-content">
          <div class="intro-text">
            <div class="section-tag">
              <span>✨</span>
              <span>{{ t('custom.introTag') }}</span>
            </div>
            <h2>{{ t('custom.introTitle') }}</h2>
            <p>
              {{ t('custom.introDesc') }}
            </p>
            <router-link to="/contact" class="cta-btn">
              <el-icon><Message /></el-icon>
              {{ t('custom.ctaBtn') }}
            </router-link>
            <div class="intro-stats">
              <div class="avatar-group">
                <div class="avatar" v-for="i in 5" :key="i">U{{ i }}</div>
                <span class="plus">99+</span>
              </div>
              <div class="rating">
                <el-icon v-for="i in 5" :key="'star-' + i"><StarFilled /></el-icon>
                <strong>4.9/5</strong>
                <span>{{ t('custom.ratingDesc') }}</span>
              </div>
            </div>
          </div>
          <div class="intro-visual">
            <div class="solution-card">
              <div class="solution-header">
                <span class="tag-auto">{{ t('custom.solutionTagAuto') }}</span>
                <span class="tag-cus">{{ t('custom.solutionTagCus') }}</span>
                <span class="tag-int">{{ t('custom.solutionTagInt') }}</span>
              </div>
              <h3>{{ t('custom.solutionTitle') }}</h3>
              <div class="solution-grid">
                <div class="sol-item" v-for="(item, i) in solutionItems" :key="i">
                  <span class="sol-icon">{{ item.icon }}</span>
                  <span class="sol-label">{{ item.label }}</span>
                </div>
              </div>
              <div class="solution-footer">
                <span>{{ t('custom.solutionFooter1') }}</span>
                <span class="arrow">→</span>
                <span>{{ t('custom.solutionFooter2') }}</span>
                <span class="check">✓</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Service Types -->
    <section class="services-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('custom.servicesTitle') }}</h2>
          <p>{{ t('custom.servicesDesc') }}</p>
        </div>
        
        <div class="services-grid">
          <div v-for="(service, index) in serviceTypes" :key="index" 
               class="service-card"
               :style="{ '--accent-color': service.color }">
            <div class="service-icon">{{ service.icon }}</div>
            <h3>{{ service.title }}</h3>
            <p>{{ service.desc }}</p>
            <ul class="service-features">
              <li v-for="(feature, i) in service.features" :key="i">
                <el-icon><Check /></el-icon>
                {{ feature }}
              </li>
            </ul>
            <router-link to="/contact" class="service-more" :style="{ '--accent-color': service.color }">
              {{ t('custom.serviceMore') }}
              <el-icon><ArrowRight /></el-icon>
            </router-link>
          </div>
        </div>
      </div>
    </section>

    <!-- Standardized Development Flow -->
    <section class="tech-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('custom.standardTitle') }}</h2>
          <p>{{ t('custom.standardDesc') }}</p>
        </div>

        <div class="standard-grid">
          <div v-for="(step, index) in standardSteps" :key="index" class="standard-card">
            <span class="standard-num">{{ String(index + 1).padStart(2, '0') }}</span>
            <h4>{{ step.title }}</h4>
            <p>{{ step.desc }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- Success Stories -->
    <section class="stories-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('custom.storiesTitle') }}</h2>
          <p>{{ t('custom.storiesDesc') }}</p>
        </div>
        
        <div class="stories-grid">
          <div v-for="(story, index) in successStories" :key="index" class="story-card">
            <div class="story-header">
              <div class="story-logo">{{ story.logo }}</div>
              <div class="story-info">
                <strong>{{ story.company }}</strong>
                <span>{{ story.industry }}</span>
              </div>
            </div>
            <p class="story-quote">"{{ story.quote }}"</p>
            <div class="story-results">
              <div class="result-item">
                <span class="result-value">{{ story.growth }}</span>
                <span class="result-label">粉丝增长</span>
              </div>
              <div class="result-item">
                <span class="result-value">{{ story.efficiency }}</span>
                <span class="result-label">效率提升</span>
              </div>
              <div class="result-item">
                <span class="result-value">{{ story.cost }}</span>
                <span class="result-label">成本节省</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Final CTA -->
    <section class="final-cta">
      <div class="section-container">
        <div class="cta-banner">
          <h2>{{ t('custom.finalCtaTitle') }}</h2>
          <p>{{ t('custom.finalCtaDesc') }}</p>
          <div class="cta-buttons">
            <router-link to="/contact" class="btn-primary">{{ t('custom.finalCtaBtn') }}</router-link>
            <a href="#" class="btn-whatsapp">
              <svg viewBox="0 0 24 24" fill="currentColor">
                <path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884m8.413-18.297A11.815 11.815 0 0012.05 0C5.495 0 .16 5.335.157 11.892c0 2.096.547 4.142 1.588 5.945L.057 24l6.305-1.654a11.882 11.882 0 005.683 1.448h.005c6.554 0 11.89-5.335 11.893-11.893a11.821 11.821 0 00-3.48-8.413z"/>
              </svg>
              {{ t('custom.whatsappBtn') }}
            </a>
          </div>
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
import { Message, StarFilled, Check, ArrowRight } from '@element-plus/icons-vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n

const solutionItems = computed(() => [
  { icon: '⚙️', label: t('custom.solutionItem1') },
  { icon: '📊', label: t('custom.solutionItem2') },
  { icon: '🔧', label: t('custom.solutionItem3') },
  { icon: '💬', label: t('custom.solutionItem4') },
  { icon: '📈', label: t('custom.solutionItem5') },
  { icon: '🛡️', label: t('custom.solutionItem6') },
  { icon: '🎨', label: t('custom.solutionItem7') },
  { icon: '🚀', label: t('custom.solutionItem8') }
])

const serviceTypes = computed(() => [
  {
    icon: '🌐',
    title: t('custom.service1Title'),
    desc: t('custom.service1Desc'),
    features: [t('custom.service1Feature1'), t('custom.service1Feature2'), t('custom.service1Feature3'), t('custom.service1Feature4')],
    color: '#0e6df0'
  },
  {
    icon: '🛒',
    title: t('custom.service2Title'),
    desc: t('custom.service2Desc'),
    features: [t('custom.service2Feature1'), t('custom.service2Feature2'), t('custom.service2Feature3'), t('custom.service2Feature4')],
    color: '#4f7cff'
  },
  {
    icon: '⚙️',
    title: t('custom.service3Title'),
    desc: t('custom.service3Desc'),
    features: [t('custom.service3Feature1'), t('custom.service3Feature2'), t('custom.service3Feature3'), t('custom.service3Feature4')],
    color: '#22c55e'
  },
  {
    icon: '☁️',
    title: t('custom.service4Title'),
    desc: t('custom.service4Desc'),
    features: [t('custom.service4Feature1'), t('custom.service4Feature2'), t('custom.service4Feature3'), t('custom.service4Feature4')],
    color: '#f97316'
  },
  {
    icon: '🔗',
    title: t('custom.service5Title'),
    desc: t('custom.service5Desc'),
    features: [t('custom.service5Feature1'), t('custom.service5Feature2'), t('custom.service5Feature3'), t('custom.service5Feature4')],
    color: '#ec4899'
  },
  {
    icon: '🤖',
    title: t('custom.service6Title'),
    desc: t('custom.service6Desc'),
    features: [t('custom.service6Feature1'), t('custom.service6Feature2'), t('custom.service6Feature3'), t('custom.service6Feature4')],
    color: '#8b5cf6'
  }
])

const standardSteps = computed(() => [1, 2, 3, 4, 5, 6, 7, 8].map(n => ({
  title: t(`custom.standardStep${n}Title`),
  desc: t(`custom.standardStep${n}Desc`)
})))

const successStories = computed(() => [
  {
    logo: 'X',
    company: t('custom.story1Company'),
    industry: t('custom.story1Industry'),
    quote: t('custom.story1Quote'),
    growth: t('custom.story1Growth'),
    efficiency: t('custom.story1Efficiency'),
    cost: t('custom.story1Cost')
  },
  {
    logo: 'M',
    company: t('custom.story2Company'),
    industry: t('custom.story2Industry'),
    quote: t('custom.story2Quote'),
    growth: t('custom.story2Growth'),
    efficiency: t('custom.story2Efficiency'),
    cost: t('custom.story2Cost')
  },
  {
    logo: 'S',
    company: t('custom.story3Company'),
    industry: t('custom.story3Industry'),
    quote: t('custom.story3Quote'),
    growth: t('custom.story3Growth'),
    efficiency: t('custom.story3Efficiency'),
    cost: t('custom.story3Cost')
  }
])
</script>

<style lang="scss" scoped>
.custom-page {
  padding-top: 70px;
}

.page-hero {
  height: 240px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(180deg, rgba(14, 109, 240, 0.06) 0%, transparent 100%);
  position: relative;

  .hero-content {
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
      color: rgba(15, 23, 42, 0.7);

      a {
        color: rgba(15, 23, 42, 0.7);
        text-decoration: none;
        &:hover { color: #0e6df0; }
      }

      .separator {
        margin: 0 12px;
        color: rgba(15, 23, 42, 0.4);
      }

      .current {
        color: #0e6df0;
      }
    }
  }
}

.intro-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
}

.intro-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 60px;
  align-items: center;
}

.intro-text {
  .section-tag {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    background: rgba(79, 124, 255, 0.1);
    border: 1px solid rgba(79, 124, 255, 0.3);
    padding: 8px 20px;
    border-radius: 30px;
    color: #4f7cff;
    font-size: 14px;
    margin-bottom: 24px;
  }

  h2 {
    font-size: 36px;
    color: #fff;
    margin-bottom: 20px;
    line-height: 1.3;
  }

  p {
    color: rgba(15, 23, 42, 0.75);
    font-size: 16px;
    line-height: 1.7;
    margin-bottom: 28px;
  }

  .cta-btn {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    color: white;
    padding: 14px 32px;
    border-radius: 12px;
    font-weight: 600;
    text-decoration: none;
    transition: all 0.3s ease;
    margin-bottom: 32px;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 10px 30px rgba(14, 109, 240, 0.3);
    }
  }

  .intro-stats {
    display: flex;
    align-items: center;
    gap: 20px;

    .avatar-group {
      display: flex;

      .avatar {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background: linear-gradient(135deg, #0575e6, #021b79);
        display: flex;
        align-items: center;
        justify-content: center;
        color: white;
        font-size: 14px;
        font-weight: 600;
        border: 2px solid #ffffff;
        margin-left: -10px;

        &:first-child {
          margin-left: 0;
        }
      }

      .plus {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background: rgba(14, 109, 240, 0.06);
        display: flex;
        align-items: center;
        justify-content: center;
        color: #0e6df0;
        font-size: 14px;
        font-weight: 600;
        border: 2px solid #ffffff;
        margin-left: -10px;
      }
    }

    .rating {
      display: flex;
      align-items: center;
      gap: 6px;

      .el-icon {
        color: #f97316;
        font-size: 14px;
      }

      strong {
        color: #fff;
        margin-left: 4px;
      }

      span {
        color: rgba(15, 23, 42, 0.6);
        font-size: 13px;
      }
    }
  }
}

.intro-visual {
  display: flex;
  justify-content: center;
}

.solution-card {
  width: 100%;
  max-width: 420px;
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 20px;
  padding: 32px;
  box-shadow: 0 20px 60px rgba(14, 109, 240, 0.08);
}

.solution-header {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;

  span {
    padding: 6px 14px;
    border-radius: 20px;
    font-size: 12px;
    font-weight: 500;

    &.tag-auto {
      background: rgba(14, 109, 240, 0.1);
      color: #0e6df0;
    }

    &.tag-cus {
      background: rgba(79, 124, 255, 0.12);
      color: #4f7cff;
    }

    &.tag-int {
      background: rgba(34, 197, 94, 0.15);
      color: #22c55e;
    }
  }
}

.solution-card h3 {
  font-size: 28px;
  color: #fff;
  margin-bottom: 24px;
  text-align: center;
}

.solution-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
  margin-bottom: 24px;
}

.sol-item {
  aspect-ratio: 1;
  background: rgba(14, 109, 240, 0.05);
  border: 1px solid #e2e8f0;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 6px;
  padding: 8px;

  .sol-icon {
    font-size: 24px;
  }

  .sol-label {
    color: rgba(15, 23, 42, 0.7);
    font-size: 11px;
    text-align: center;
  }
}

.solution-footer {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  padding-top: 20px;
  border-top: 1px solid #e2e8f0;
  color: rgba(15, 23, 42, 0.7);
  font-size: 13px;

  .arrow {
    color: #0e6df0;
  }

  .check {
    color: #22c55e;
  }
}

.process-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
}

.section-header {
  text-align: center;
  margin-bottom: 48px;

  h2 {
    font-size: 32px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    margin-bottom: 12px;
  }

  p {
    color: rgba(15, 23, 42, 0.7);
    font-size: 16px;
  }
}

.services-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
}

.services-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.service-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 32px 24px;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 3px;
    background: var(--accent-color, #0e6df0);
  }

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 40px rgba(14, 109, 240, 0.1);
  }

  .service-icon {
    font-size: 48px;
    margin-bottom: 16px;
  }

  h3 {
    color: #fff;
    font-size: 20px;
    margin-bottom: 12px;
  }

  p {
    color: rgba(15, 23, 42, 0.7);
    font-size: 14px;
    margin-bottom: 16px;
    line-height: 1.6;
  }

  .service-features {
    list-style: none;
    padding: 0;
    margin-bottom: 20px;

    li {
      display: flex;
      align-items: center;
      gap: 8px;
      padding: 6px 0;
      color: rgba(15, 23, 42, 0.75);
      font-size: 13px;

      .el-icon {
        color: #22c55e;
      }
    }
  }

  .service-more {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    padding-top: 16px;
    border-top: 1px solid #e2e8f0;
    color: var(--accent-color, #0e6df0);
    font-weight: 600;
    font-size: 15px;
    text-decoration: none;
    transition: all 0.3s ease;

    .el-icon {
      font-size: 16px;
      transition: transform 0.3s ease;
    }

    &:hover {
      gap: 10px;

      .el-icon {
        transform: translateX(3px);
      }
    }
  }
}

.tech-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
}

.standard-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

.standard-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 28px 24px;
  position: relative;
  overflow: hidden;
  transition: all 0.3s ease;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 3px;
    background: #0e6df0;
  }

  .standard-num {
    display: inline-block;
    font-size: 15px;
    font-weight: 700;
    color: #0e6df0;
    line-height: 1;
    margin-bottom: 14px;
    padding: 5px 12px;
    background: rgba(14, 109, 240, 0.1);
    border-radius: 20px;
    letter-spacing: 1px;
  }

  h4 {
    color: #0f172a;
    font-size: 17px;
    margin-bottom: 10px;
  }

  p {
    color: rgba(15, 23, 42, 0.6);
    font-size: 14px;
    line-height: 1.6;
    margin: 0;
  }

  &:hover {
    border-color: rgba(14, 109, 240, 0.5);
    transform: translateY(-5px);
    box-shadow: 0 15px 40px rgba(14, 109, 240, 0.1);
  }
}

.stories-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
}

.stories-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}

.story-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 28px;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 40px rgba(14, 109, 240, 0.1);
  }
}

.story-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 16px;

  .story-logo {
    width: 44px;
    height: 44px;
    background: linear-gradient(135deg, #0575e6, #021b79);
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-weight: 700;
  }

  .story-info {
    strong {
      display: block;
      color: #fff;
    }

    span {
      color: rgba(15, 23, 42, 0.6);
      font-size: 13px;
    }
  }
}

.story-quote {
  color: rgba(15, 23, 42, 0.75);
  font-size: 14px;
  line-height: 1.7;
  margin-bottom: 20px;
  font-style: italic;
}

.story-results {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
  padding-top: 16px;
  border-top: 1px solid #e2e8f0;

  .result-item {
    text-align: center;

    .result-value {
      display: block;
      color: #22c55e;
      font-size: 18px;
      font-weight: 700;
      margin-bottom: 4px;
    }

    .result-label {
      color: rgba(15, 23, 42, 0.5);
      font-size: 12px;
    }
  }
}

.final-cta {
  padding: 80px 0;
  position: relative;
  z-index: 1;
  background: #f8fafc;
}

.cta-banner {
  position: relative;
  overflow: hidden;
  background: linear-gradient(135deg, #021b79 0%, #0575e6 100%);
  border-radius: 24px;
  padding: 60px 40px;
  text-align: center;
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
    color: #fff;
    margin-bottom: 16px;
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

.cta-buttons {
  display: flex;
  justify-content: center;
  gap: 16px;
}

.btn-primary {
  display: inline-flex;
  align-items: center;
  gap: 8px;
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

.btn-whatsapp {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  color: #fff;
  padding: 16px 40px;
  border-radius: 12px;
  font-weight: 700;
  text-decoration: none;
  font-size: 16px;
  background: rgba(255, 255, 255, 0.12);
  border: 2px solid rgba(255, 255, 255, 0.4);
  transition: all 0.3s ease;

  svg {
    width: 20px;
    height: 20px;
  }

  &:hover {
    border-color: #ffffff;
    background: rgba(255, 255, 255, 0.2);
  }
}

.section-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

@media (max-width: 968px) {
  .intro-content {
    grid-template-columns: 1fr;
  }

  .services-grid,
  .standard-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .stories-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 600px) {
  .services-grid,
  .tech-categories {
    grid-template-columns: 1fr;
  }
}
</style>
