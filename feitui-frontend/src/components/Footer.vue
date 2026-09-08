<template>
  <footer class="cyber-footer">
    <div class="footer-grid">
      <div class="footer-col brand-col">
        <div class="footer-logo">
          <svg viewBox="0 0 48 48" class="logo-svg">
            <circle cx="24" cy="24" r="20" fill="none" stroke="url(#gradFooter)" stroke-width="2"/>
            <path d="M16 24 L22 30 L32 18" fill="none" stroke="#0e6df0" stroke-width="3" stroke-linecap="round" stroke-linejoin="round"/>
            <circle cx="24" cy="24" r="4" fill="#4f7cff"/>
            <defs>
              <linearGradient id="gradFooter" x1="0%" y1="0%" x2="100%" y2="100%">
                <stop offset="0%" style="stop-color:#0e6df0"/>
                <stop offset="100%" style="stop-color:#4f7cff"/>
              </linearGradient>
            </defs>
          </svg>
          <span>{{ t('components.brand') }}</span>
        </div>
        <p class="brand-desc">
          {{ t('footer.brandDesc') }}
        </p>
      </div>

      <div class="footer-col">
        <h4 class="col-title">{{ t('footer.colSections') }}</h4>
        <ul class="footer-links">
          <li><router-link to="/features">{{ t('nav.features') }}</router-link></li>
          <li><router-link to="/video">{{ t('nav.video') }}</router-link></li>
          <li><router-link to="/custom">{{ t('nav.custom') }}</router-link></li>
          <li><router-link to="/contact">{{ t('nav.contact') }}</router-link></li>
        </ul>
      </div>

      <div class="footer-col">
        <h4 class="col-title">{{ t('footer.colTags') }}</h4>
        <div class="tag-list">
          <span class="tag">{{ t('footer.tag1') }}</span>
          <span class="tag">{{ t('footer.tag2') }}</span>
          <span class="tag">{{ t('footer.tag3') }}</span>
          <span class="tag">{{ t('footer.tag4') }}</span>
          <span class="tag">{{ t('footer.tag5') }}</span>
        </div>
      </div>

      <div class="footer-col contact-col">
        <h4 class="col-title">{{ t('footer.colContact') }}</h4>
        <p class="contact-info">
          <el-icon><Phone /></el-icon>
          <span>WhatsApp: 19115289951</span>
        </p>
        <p class="contact-info">
          <el-icon><Message /></el-icon>
          <span>Telegram: @Allence01</span>
        </p>
        <p class="contact-info">
          <el-icon><Message /></el-icon>
          <span>support@feitui.com</span>
        </p>
        <div class="social-links">
          <a href="#" class="social-link" title="WeChat">
            <svg viewBox="0 0 24 24"><path fill="currentColor" d="M8.691 2.188C3.891 2.188 0 5.476 0 9.53c0 2.212 1.17 4.203 3.002 5.55a.59.59 0 0 1 .213.665l-.39 1.48c-.019.07-.048.141-.048.213 0 .163.13.295.29.295a.326.326 0 0 0 .167-.054l1.903-1.114a.864.864 0 0 1 .717-.098 10.16 10.16 0 0 0 2.837.403c.276 0 .543-.027.811-.05-.857-2.578.157-6.168 3.228-8.588C11.077 4.78 9.11 2.188 8.691 2.188z"/></svg>
          </a>
          <a href="#" class="social-link" title="Telegram">
            <svg viewBox="0 0 24 24"><path fill="currentColor" d="M11.944 0A12 12 0 0 0 0 12a12 12 0 0 0 12 12 12 12 0 0 0 12-12A12 12 0 0 0 12 0a12 12 0 0 0-.056 0zm4.962 7.224c.1-.002.321.023.465.14a.506.506 0 0 1 .171.325c.016.093.036.306.02.472-.18 1.898-.962 6.502-1.36 8.627-.168.9-.499 1.201-.82 1.23-.696.065-1.225-.46-1.9-.902-1.056-.693-1.653-1.124-2.678-1.8-1.185-.78-.417-1.21.258-1.91.177-.184 3.247-2.977 3.307-3.23.007-.032.014-.15-.056-.212s-.174-.041-.249-.024c-.106.024-1.793 1.14-5.061 3.345-.48.33-.913.49-1.302.48-.428-.008-1.252-.241-1.865-.44-.752-.245-1.349-.374-1.297-.789.027-.216.325-.437.893-.663 3.498-1.524 5.83-2.529 6.998-3.014 3.332-1.386 4.025-1.627 4.476-1.635z"/></svg>
          </a>
          <a href="#" class="social-link" title="Email">
            <svg viewBox="0 0 24 24"><path fill="currentColor" d="M12 0C5.373 0 0 5.373 0 12s5.373 12 12 12 12-5.373 12-12S18.627 0 12 0zm6.5 9l-6 3.75L6.5 9V7.5L12 11l5.5-3.5V9z"/></svg>
          </a>
        </div>
        <div class="contact-btns">
          <a href="/wechat-qr" target="_blank" rel="noopener" class="contact-btn wechat">{{ t('footer.wechatContact') }}</a>
          <a href="https://t.me/Allence01" target="_blank" rel="noopener" class="contact-btn tg">{{ t('footer.tgContact') }}</a>
        </div>
      </div>
    </div>

    <div class="footer-bottom">
      <div class="links-row">
        <span class="link-label">{{ t('footer.friendlyLinks') }}</span>
        <a href="#">全球优质海外代理IP服务商</a>
        <a href="#">Google</a>
        <a href="#">GitHub</a>
        <a href="#">Cloudflare</a>
        <a href="#">StackExchange</a>
      </div>
      <div class="copyright">
        {{ t('footer.copyright') }}
      </div>
    </div>
  </footer>
</template>

<script setup>
import { Phone, Message } from '@element-plus/icons-vue'
import { useI18nStore } from '@/i18n'

const i18n = useI18nStore()
const { t } = i18n
</script>

<style lang="scss" scoped>
.cyber-footer {
  background: linear-gradient(180deg, #202438 0%, #1e2134 100%);
  border-top: 1px solid rgba(14, 109, 240, 0.15);
  padding: 60px 0 0;
  position: relative;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 2px;
    background: linear-gradient(90deg, transparent, #0e6df0, #4f7cff, transparent);
  }
}

.footer-grid {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: grid;
  grid-template-columns: 1.5fr 1fr 1.2fr 1.5fr;
  gap: 48px;
}

.footer-col {
  .col-title {
    font-size: 18px;
    font-weight: 600;
    color: #fff;
    margin-bottom: 20px;
    position: relative;
    padding-bottom: 12px;

    &::after {
      content: '';
      position: absolute;
      bottom: 0;
      left: 0;
      width: 40px;
      height: 2px;
      background: linear-gradient(90deg, #0e6df0, #4f7cff);
    }
  }
}

.footer-logo {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;

  .logo-svg {
    width: 36px;
    height: 36px;
  }

  span {
    font-size: 20px;
    font-weight: 700;
    background: linear-gradient(135deg, #0e6df0, #4f7cff);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  }
}

.brand-desc {
  color: rgba(255, 255, 255, 0.6);
  font-size: 14px;
  line-height: 1.8;
}

.footer-links {
  list-style: none;

  li {
    margin-bottom: 12px;

    a {
      color: rgba(255, 255, 255, 0.7);
      text-decoration: none;
      font-size: 14px;
      transition: color 0.3s ease;

      &:hover {
        color: #4f7cff;
      }
    }
  }
}

.tag-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;

  .tag {
    background: rgba(14, 109, 240, 0.12);
    border: 1px solid rgba(14, 109, 240, 0.25);
    color: #4f7cff;
    padding: 6px 14px;
    border-radius: 20px;
    font-size: 13px;
    transition: all 0.3s ease;
    cursor: pointer;

    &:hover {
      background: rgba(14, 109, 240, 0.22);
      border-color: #0e6df0;
    }
  }
}

.contact-info {
  display: flex;
  align-items: center;
  gap: 10px;
  color: rgba(255, 255, 255, 0.7);
  font-size: 14px;
  margin-bottom: 12px;

  .el-icon {
    color: #0e6df0;
    font-size: 18px;
  }
}

.social-links {
  display: flex;
  gap: 12px;
  margin: 20px 0;

  .social-link {
    width: 40px;
    height: 40px;
    background: rgba(14, 109, 240, 0.12);
    border: 1px solid rgba(14, 109, 240, 0.25);
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #4f7cff;
    transition: all 0.3s ease;

    svg {
      width: 20px;
      height: 20px;
    }

    &:hover {
      background: #0e6df0;
      color: #1e2134;
    }
  }
}

.contact-btns {
  display: flex;
  gap: 12px;

  .contact-btn {
    flex: 1;
    padding: 10px 16px;
    border-radius: 8px;
    text-align: center;
    font-size: 14px;
    font-weight: 600;
    text-decoration: none;
    transition: all 0.3s ease;

    &.wechat {
      background: linear-gradient(135deg, #07c160, #06ad56);
      color: white;
      &:hover { transform: translateY(-2px); }
    }

    &.tg {
      background: linear-gradient(135deg, #0088cc, #0077b5);
      color: white;
      &:hover { transform: translateY(-2px); }
    }
  }
}

.footer-bottom {
  border-top: 1px solid rgba(14, 109, 240, 0.1);
  margin-top: 60px;
  padding: 24px 0;
}

.links-row {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 16px;
  font-size: 14px;
  color: rgba(255, 255, 255, 0.6);

  .link-label {
    font-weight: 600;
    color: rgba(255, 255, 255, 0.8);
  }

  a {
    color: rgba(255, 255, 255, 0.6);
    text-decoration: none;
    transition: color 0.3s ease;

    &:hover {
      color: #4f7cff;
    }
  }
}

.copyright {
  max-width: 1200px;
  margin: 16px auto 0;
  padding: 0 24px;
  text-align: center;
  color: rgba(255, 255, 255, 0.4);
  font-size: 13px;
}

@media (max-width: 968px) {
  .footer-grid {
    grid-template-columns: 1fr 1fr;
    gap: 32px;
  }
}

@media (max-width: 600px) {
  .footer-grid {
    grid-template-columns: 1fr;
  }
}
</style>
