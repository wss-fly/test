<template>
  <div class="contact-page">
    <NavBar />
    
    <!-- Page Hero -->
    <section class="page-hero">
      <div class="hero-content">
        <h1>{{ t('contact.pageHeroTitle') }}</h1>
        <div class="breadcrumb">
          <router-link to="/">{{ t('contact.breadcrumbHome') }}</router-link>
          <span class="separator">›</span>
          <span class="current">{{ t('contact.breadcrumbCurrent') }}</span>
        </div>
      </div>
    </section>

    <!-- Contact Main -->
    <section class="contact-main">
      <div class="section-container">
        <div class="contact-grid">
          <!-- Contact Info -->
          <div class="contact-info">
            <div class="info-header">
              <h2>{{ t('contact.infoTitle') }}</h2>
              <p>{{ t('contact.infoDesc') }}</p>
            </div>

            <div class="info-cards">
              <div class="info-card">
                <div class="card-icon whatsapp">
                  <svg viewBox="0 0 24 24" fill="currentColor">
                    <path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347m-5.421 7.403h-.004a9.87 9.87 0 01-5.031-1.378l-.361-.214-3.741.982.998-3.648-.235-.374a9.86 9.86 0 01-1.51-5.26c.001-5.45 4.436-9.884 9.888-9.884 2.64 0 5.122 1.03 6.988 2.898a9.825 9.825 0 012.893 6.994c-.003 5.45-4.437 9.884-9.885 9.884"/>
                  </svg>
                </div>
                <div class="card-content">
                  <h4>{{ t('contact.whatsappName') }}</h4>
                  <p class="card-value">19115289951</p>
                  <p class="card-note">{{ t('contact.whatsappNote') }}</p>
                </div>
                <a href="https://web.whatsapp.com/" target="_blank" rel="noopener" class="card-action" :title="t('contact.whatsappNote')">
                  <svg viewBox="0 0 24 24" fill="currentColor" width="24" height="24">
                    <path d="M17.472 14.382c-.297-.149-1.758-.867-2.03-.967-.273-.099-.471-.148-.67.15-.197.297-.767.966-.94 1.164-.173.199-.347.223-.644.075-.297-.15-1.255-.463-2.39-1.475-.883-.788-1.48-1.761-1.653-2.059-.173-.297-.018-.458.13-.606.134-.133.298-.347.446-.52.149-.174.198-.298.298-.497.099-.198.05-.371-.025-.52-.075-.149-.669-1.612-.916-2.207-.242-.579-.487-.5-.669-.51-.173-.008-.371-.01-.57-.01-.198 0-.52.074-.792.372-.272.297-1.04 1.016-1.04 2.479 0 1.462 1.065 2.875 1.213 3.074.149.198 2.096 3.2 5.077 4.487.709.306 1.262.489 1.694.625.712.227 1.36.195 1.871.118.571-.085 1.758-.719 2.006-1.413.248-.694.248-1.289.173-1.413-.074-.124-.272-.198-.57-.347"/>
                  </svg>
                </a>
              </div>

              <div class="info-card">
                <div class="card-icon telegram">
                  <svg viewBox="0 0 24 24" fill="currentColor">
                    <path d="M11.944 0A12 12 0 0 0 0 12a12 12 0 0 0 12 12 12 12 0 0 0 12-12A12 12 0 0 0 12 0a12 12 0 0 0-.056 0zm4.962 7.224c.1-.002.321.023.465.14a.506.506 0 0 1 .171.325c.016.093.036.306.02.472-.18 1.898-.962 6.502-1.36 8.627-.168.9-.499 1.201-.82 1.23-.696.065-1.225-.46-1.9-.902-1.056-.693-1.653-1.124-2.678-1.8-1.185-.78-.417-1.21.258-1.91.177-.184 3.247-2.977 3.307-3.23.007-.032.014-.15-.056-.212s-.174-.041-.249-.024c-.106.024-1.793 1.14-5.061 3.345-.48.33-.913.49-1.302.48-.428-.008-1.252-.241-1.865-.44-.752-.245-1.349-.374-1.297-.789.027-.216.325-.437.893-.663 3.498-1.524 5.83-2.529 6.998-3.014 3.332-1.386 4.025-1.627 4.476-1.635"/>
                  </svg>
                </div>
                <div class="card-content">
                  <h4>{{ t('contact.telegramName') }}</h4>
                  <p class="card-value">@Allence01</p>
                  <p class="card-note">{{ t('contact.telegramNote') }}</p>
                </div>
                <a href="https://t.me/Allence01" target="_blank" rel="noopener" class="card-action" :title="t('contact.telegramNote')">
                  <svg viewBox="0 0 24 24" fill="currentColor" width="24" height="24">
                    <path d="M9.04 19.5l.54-4.9 9.4-8.5-12.2 8.3-4.1-1.5c-.95-.34-.98-.95-.22-1.18l18.6-7.1c.92-.26 1.5.18 1.2 1.32L14.7 20.6c-.25.96-1.05 1.11-1.7.68L9.04 19.5z"/>
                  </svg>
                </a>
              </div>

              <div class="info-card">
                <div class="card-icon wework" @click="openWecomQr">
                  <svg viewBox="0 0 24 24" fill="currentColor">
                    <circle cx="12" cy="7.5" r="3"/>
                    <path d="M12 12.5c-3.9 0-6.5 2.4-6.5 5.3V19h13v-1.2c0-2.9-2.6-5.3-6.5-5.3z"/>
                    <path d="M17.8 6.2a1.9 1.9 0 1 0 0-3.8 1.9 1.9 0 0 0 0 3.8zm0 1.6c-1.1 0-2 .7-2 1.4-.6-1.7 1-3.3 3-3.3 1 0 1.9.4 2.5 1-.7-.2 1-1.4 1.5-.7.4.5.1 1.9.1 2.6-.1.3-.3.5-.5.6-.3.1-1.5.1-1.5 1a.5.5 0 0 1-.5.5c-1.5 0-2.6-.5-2.6-.5z" transform="matrix(-1,0,0,1,30,0)"/>
                  </svg>
                </div>
                <div class="card-content">
                  <h4>{{ t('contact.weworkName') }}</h4>
                  <p class="card-value">{{ t('contact.weworkValue') }}</p>
                  <p class="card-note">{{ t('contact.weworkNote') }}</p>
                </div>
                <a class="card-action cursor-pointer" @click.prevent="openWecomQr" :title="t('contact.weworkValue')">
                  <svg viewBox="0 0 24 24" fill="currentColor" width="24" height="24">
                    <path d="M3 3h8v8H3V3zm2 2v4h4V5H5zm8-2h8v8h-8V3zm2 2v4h4V5h-4zM3 13h8v8H3v-8zm2 2v4h4v-4H5zm11-3h3v3h-3v-3zm-4 0h2v3h-2v-3zm4 5h3v3h-3v-3zm-4 0h2v3h-2v-3z"/>
                  </svg>
                </a>
              </div>

              <div class="info-card">
                <div class="card-icon email">
                  <svg viewBox="0 0 24 24" fill="currentColor">
                    <path d="M12 0C5.373 0 0 5.373 0 12s5.373 12 12 12 12-5.373 12-12S18.627 0 12 0zm6.5 9l-6 3.75L6.5 9V7.5L12 11l5.5-3.5V9z"/>
                  </svg>
                </div>
                <div class="card-content">
                  <h4>{{ t('contact.emailName') }}</h4>
                  <p class="card-value">support@feitui.com</p>
                  <p class="card-note">{{ t('contact.emailNote') }}</p>
                </div>
              </div>

              <div class="info-card">
                <div class="card-icon location">
                  <svg viewBox="0 0 24 24" fill="currentColor">
                    <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/>
                  </svg>
                </div>
                <div class="card-content">
                  <h4>{{ t('contact.hoursName') }}</h4>
                  <p class="card-value">{{ t('contact.hoursValue') }}</p>
                  <p class="card-note">{{ t('contact.hoursNote') }}</p>
                </div>
              </div>
            </div>

            <div class="faq-preview">
              <h4>{{ t('contact.faqPreviewTitle') }}</h4>
              <ul>
                <li>
                  <span class="faq-q">Q: 产品如何收费？</span>
                  <span class="faq-a">A: 我们提供多种套餐方案，具体价格请联系客服获取详细报价。</span>
                </li>
                <li>
                  <span class="faq-q">Q: 是否提供免费试用？</span>
                  <span class="faq-a">A: 是的，我们提供7天免费试用，您可以在试用期内体验全部功能。</span>
                </li>
                <li>
                  <span class="faq-q">Q: 如何保证账号安全？</span>
                  <span class="faq-a">A: 我们采用银行级加密技术和智能风控系统，确保您的账号安全。</span>
                </li>
              </ul>
            </div>
          </div>

          <!-- Contact Form -->
          <div class="contact-form">
            <div class="form-header">
              <h3>{{ t('contact.formTitle') }}</h3>
              <p>{{ t('contact.formDesc') }}</p>
            </div>

            <el-form 
              ref="formRef" 
              :model="formData" 
              :rules="formRules" 
              class="cyber-form"
              @submit.prevent="submitForm">
              
              <div class="form-row">
                <el-form-item prop="name">
                  <el-input 
                    v-model="formData.name" 
                    :placeholder="t('contact.formName')" 
                    size="large"
                    clearable>
                    <template #prefix>
                      <el-icon><User /></el-icon>
                    </template>
                  </el-input>
                </el-form-item>

                <el-form-item prop="phone">
                  <el-input 
                    v-model="formData.phone" 
                    :placeholder="t('contact.formPhone')" 
                    size="large"
                    clearable>
                    <template #prefix>
                      <el-icon><Phone /></el-icon>
                    </template>
                  </el-input>
                </el-form-item>
              </div>

              <div class="form-row">
                <el-form-item prop="email">
                  <el-input 
                    v-model="formData.email" 
                    :placeholder="t('contact.formEmail')" 
                    size="large"
                    clearable>
                    <template #prefix>
                      <el-icon><Message /></el-icon>
                    </template>
                  </el-input>
                </el-form-item>

                <el-form-item prop="company">
                  <el-input 
                    v-model="formData.company" 
                    :placeholder="t('contact.formCompany')" 
                    size="large"
                    clearable>
                    <template #prefix>
                      <el-icon><OfficeBuilding /></el-icon>
                    </template>
                  </el-input>
                </el-form-item>
              </div>

              <el-form-item prop="platform">
                <el-select v-model="formData.platform" :placeholder="t('contact.formPlatform')" size="large" class="full-width">
                  <el-option label="Facebook" value="Facebook" />
                  <el-option label="Twitter/X" value="Twitter" />
                  <el-option label="Instagram" value="Instagram" />
                  <el-option label="多平台组合" value="Multi" />
                </el-select>
              </el-form-item>

              <el-form-item prop="requirement">
                <el-input 
                  v-model="formData.requirement" 
                  type="textarea" 
                  :rows="4"
                  :placeholder="t('contact.formRequirement')" 
                  resize="none"
                  size="large">
                </el-input>
              </el-form-item>

              <div class="form-actions">
                <button 
                  type="button" 
                  class="submit-btn"
                  :disabled="submitting"
                  @click="submitForm">
                  <span v-if="!submitting">{{ t('contact.formSubmit') }}</span>
                  <span v-else>
                    <el-icon class="is-loading"><Loading /></el-icon>
                    {{ t('contact.formSubmitting') }}
                  </span>
                </button>
                <button type="button" class="reset-btn" @click="resetForm">{{ t('contact.formReset') }}</button>
              </div>
            </el-form>

            <div class="form-success" v-if="showSuccess">
              <el-icon class="success-icon"><CircleCheck /></el-icon>
              <h4>{{ t('contact.formSuccessTitle') }}</h4>
              <p>{{ t('contact.formSuccessDesc') }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Map Section -->
    <section class="map-section">
      <div class="section-container">
        <div class="map-header">
          <h2>{{ t('contact.mapTitle') }}</h2>
          <p>{{ t('contact.mapDesc') }}</p>
        </div>
        <div class="map-card">
          <div class="map-placeholder">
            <div class="map-grid"></div>
            <div class="map-marker">
              <div class="marker-pin"></div>
              <div class="marker-pulse"></div>
            </div>
            <div class="map-info">
              <h4>{{ t('contact.mapCompany') }}</h4>
              <p>{{ t('contact.mapAddress') }}</p>
              <p><el-icon><Clock /></el-icon> {{ t('contact.mapHours') }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- FAQ Section -->
    <section class="faq-section">
      <div class="section-container">
        <div class="section-header">
          <h2>{{ t('contact.faqSectionTitle') }}</h2>
        </div>
        <div class="faq-grid">
          <div v-for="(faq, index) in faqList" :key="index" class="faq-item">
            <div class="faq-q">
              <el-icon><QuestionFilled /></el-icon>
              <span>{{ faq.question }}</span>
            </div>
            <div class="faq-a">
              <el-icon><ChatDotRound /></el-icon>
              <span>{{ faq.answer }}</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <Footer />
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import NavBar from '@/components/NavBar.vue'
import Footer from '@/components/Footer.vue'
import { User, Phone, Message, OfficeBuilding, Loading, CircleCheck, Clock, QuestionFilled, ChatDotRound } from '@element-plus/icons-vue'
import { submitContact } from '@/api'
import { ElMessage } from 'element-plus'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n

const formRef = ref(null)
const submitting = ref(false)
const showSuccess = ref(false)

// 企业微信联系：新开标签页展示二维码
const openWecomQr = () => {
  window.open('/wechat-qr', '_blank')
}

const formData = reactive({
  name: '',
  phone: '',
  email: '',
  company: '',
  platform: 'Facebook',
  requirement: ''
})

const formRules = {
  name: [
    { required: true, message: '请输入您的姓名', trigger: 'blur' },
    { min: 2, max: 50, message: '姓名长度在2-50个字符', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入手机号或WhatsApp', trigger: 'blur' },
    { pattern: /^[\d\-+\s()]+$/, message: '请输入正确的联系方式', trigger: 'blur' }
  ],
  requirement: [
    { min: 10, message: '请至少描述10个字的需求', trigger: 'blur' }
  ]
}

const faqList = computed(() => [
  { question: t('contact.faq1Question'), answer: t('contact.faq1Answer') },
  { question: t('contact.faq2Question'), answer: t('contact.faq2Answer') },
  { question: t('contact.faq3Question'), answer: t('contact.faq3Answer') },
  { question: t('contact.faq4Question'), answer: t('contact.faq4Answer') }
])

const submitForm = async () => {
  if (!formRef.value) return
  
  try {
    await formRef.value.validate()
    submitting.value = true
    
    try {
      await submitContact({
        name: formData.name,
        phone: formData.phone,
        email: formData.email,
        company: formData.company,
        platform: formData.platform,
        requirement: formData.requirement
      })
      showSuccess.value = true
      ElMessage.success('提交成功！我们将尽快联系您')
      resetForm()
    } catch (err) {
      ElMessage.error('提交失败，请稍后重试或直接联系客服')
    }
    
  } catch (err) {
    // Validation failed
  } finally {
    submitting.value = false
  }
}

const resetForm = () => {
  if (formRef.value) {
    formRef.value.resetFields()
  }
  showSuccess.value = false
}
</script>

<style lang="scss" scoped>
.contact-page {
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

.contact-main {
  padding: 40px 0;
  position: relative;
  z-index: 1;
}

.contact-grid {
  display: grid;
  grid-template-columns: 1fr 1.2fr;
  gap: 40px;
}

.contact-info {
  .info-header {
    margin-bottom: 28px;

    h2 {
      font-size: 28px;
      color: #0f172a;
      margin-bottom: 8px;
    }

    p {
      color: rgba(15, 23, 42, 0.7);
      font-size: 15px;
    }
  }
}

.info-cards {
  display: flex;
  flex-direction: column;
  gap: 16px;
  margin-bottom: 32px;
}

.info-card {
  display: flex;
  align-items: center;
  gap: 16px;
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 20px;
  transition: all 0.3s ease;

  &:hover {
    border-color: rgba(14, 109, 240, 0.4);
    transform: translateX(4px);
  }

  .card-icon {
    width: 50px;
    height: 50px;
    border-radius: 12px;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;

    svg {
      width: 24px;
      height: 24px;
      color: white;
    }

    &.whatsapp { background: linear-gradient(135deg, #25D366, #128C7E); }
    &.telegram { background: linear-gradient(135deg, #0088cc, #0077b5); }
    &.wework { background: linear-gradient(135deg, #07C160, #05A65B); cursor: pointer; }
    &.email { background: linear-gradient(135deg, #0575e6, #0088cc); }
    &.location { background: linear-gradient(135deg, #f97316, #ea580c); }
  }

  .card-content {
    flex: 1;

    h4 {
      color: #0f172a;
      font-size: 16px;
      margin-bottom: 4px;
    }

    .card-value {
      color: #0e6df0;
      font-weight: 600;
      margin-bottom: 4px;
    }

    .card-note {
      color: rgba(15, 23, 42, 0.5);
      font-size: 12px;
    }
  }

  .card-action {
    width: 40px;
    height: 40px;
    border-radius: 10px;
    background: rgba(14, 109, 240, 0.1);
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.3s ease;

    svg {
      width: 20px;
      height: 20px;
      color: #0e6df0;
    }

    &:hover {
      background: #0e6df0;
      svg { color: white; }
    }
  }
}

.faq-preview {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 16px;
  padding: 24px;

  h4 {
    color: #0e6df0;
    font-size: 16px;
    margin-bottom: 16px;
    padding-bottom: 12px;
    border-bottom: 1px solid #e2e8f0;
  }

  ul {
    list-style: none;
    padding: 0;

    li {
      padding: 12px 0;
      border-bottom: 1px solid #f1f5f9;

      &:last-child {
        border-bottom: none;
      }
    }
  }

  .faq-q {
    display: block;
    color: rgba(15, 23, 42, 0.85);
    font-size: 14px;
    margin-bottom: 4px;
  }

  .faq-a {
    display: block;
    color: rgba(15, 23, 42, 0.5);
    font-size: 13px;
    line-height: 1.5;
  }
}

.contact-form {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 20px;
  padding: 36px;
  position: relative;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 3px;
    background: linear-gradient(90deg, #0575e6, #021b79);
  }

  .form-header {
    margin-bottom: 24px;

    h3 {
      font-size: 24px;
      color: #0f172a;
      margin-bottom: 8px;
    }

    p {
      color: rgba(15, 23, 42, 0.6);
      font-size: 14px;
    }
  }
}

.cyber-form {
  .form-row {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 16px;
  }

  .el-form-item {
    margin-bottom: 20px;
  }

  .full-width {
    width: 100%;
  }

  .form-actions {
    display: flex;
    gap: 12px;
    margin-top: 8px;
  }

  .submit-btn {
    flex: 1;
    background: linear-gradient(135deg, #0575e6, #021b79);
    color: white;
    padding: 14px 32px;
    border-radius: 12px;
    font-weight: 600;
    font-size: 16px;
    cursor: pointer;
    transition: all 0.3s ease;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;

    &:hover:not(:disabled) {
      transform: translateY(-2px);
      box-shadow: 0 10px 30px rgba(14, 109, 240, 0.3);
    }

    &:disabled {
      opacity: 0.6;
      cursor: not-allowed;
    }
  }

  .reset-btn {
    background: #f1f5f9;
    border: 1px solid #e2e8f0;
    color: #0f172a;
    padding: 14px 28px;
    border-radius: 12px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.3s ease;

    &:hover {
      background: #e2e8f0;
    }
  }
}

.form-success {
  position: absolute;
  inset: 0;
  background: #ffffff;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 40px;
  border-radius: 20px;

  .success-icon {
    font-size: 64px;
    color: #22c55e;
    margin-bottom: 16px;
  }

  h4 {
    font-size: 24px;
    color: #0f172a;
    margin-bottom: 8px;
  }

  p {
    color: rgba(15, 23, 42, 0.7);
    font-size: 15px;
    line-height: 1.6;
  }
}

.map-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
}

.map-header {
  text-align: center;
  margin-bottom: 32px;

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

.map-card {
  max-width: 800px;
  margin: 0 auto;
  border-radius: 20px;
  overflow: hidden;
  border: 1px solid #e2e8f0;
}

.map-placeholder {
  height: 300px;
  background: linear-gradient(135deg, #eef4ff, #dbe9ff);
  position: relative;
  overflow: hidden;
}

.map-grid {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(14, 109, 240, 0.15) 1px, transparent 1px),
    linear-gradient(90deg, rgba(14, 109, 240, 0.15) 1px, transparent 1px);
  background-size: 40px 40px;
}

.map-marker {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -100%);
}

.marker-pin {
  width: 24px;
  height: 24px;
  background: #f43f5e;
  border: 3px solid white;
  border-radius: 50% 50% 50% 0;
  transform: rotate(-45deg);
  box-shadow: 0 4px 20px rgba(244, 63, 94, 0.5);
}

.marker-pulse {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60px;
  height: 60px;
  border: 2px solid #f43f5e;
  border-radius: 50%;
  animation: pulse-map 2s ease-out infinite;
}

@keyframes pulse-map {
  0% { transform: translate(-50%, -50%) scale(1); opacity: 1; }
  100% { transform: translate(-50%, -50%) scale(2); opacity: 0; }
}

.map-info {
  position: absolute;
  bottom: 20px;
  left: 20px;
  background: #ffffff;
  padding: 16px 20px;
  border-radius: 12px;
  border: 1px solid #e2e8f0;

  h4 {
    color: #0e6df0;
    margin-bottom: 8px;
  }

  p {
    color: rgba(15, 23, 42, 0.7);
    font-size: 13px;
    margin-bottom: 4px;
    display: flex;
    align-items: center;
    gap: 6px;

    .el-icon {
      color: #0e6df0;
    }
  }
}

.faq-section {
  padding: 60px 0;
  position: relative;
  z-index: 1;
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
  }
}

.faq-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  max-width: 1000px;
  margin: 0 auto;
}

.faq-item {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  padding: 20px 24px;
  transition: all 0.3s ease;

  &:hover {
    border-color: rgba(14, 109, 240, 0.4);
  }

  .faq-q {
    display: flex;
    align-items: flex-start;
    gap: 10px;
    color: #0f172a;
    font-weight: 600;
    margin-bottom: 12px;

    .el-icon {
      color: #0e6df0;
      flex-shrink: 0;
      margin-top: 2px;
    }
  }

  .faq-a {
    display: flex;
    align-items: flex-start;
    gap: 10px;
    color: rgba(15, 23, 42, 0.7);
    font-size: 14px;
    line-height: 1.6;

    .el-icon {
      color: #22c55e;
      flex-shrink: 0;
      margin-top: 2px;
    }
  }
}

.section-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

@media (max-width: 968px) {
  .contact-grid {
    grid-template-columns: 1fr;
  }

  .cyber-form .form-row {
    grid-template-columns: 1fr;
  }

  .faq-grid {
    grid-template-columns: 1fr;
  }

  .contact-form {
    padding: 24px;
  }
}
</style>
