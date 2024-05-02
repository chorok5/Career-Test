import { createApp } from 'vue'
import App from './App.vue'
import router from './router'; //설정 라우터 호출

createApp(App).mount('#app')

const app = createApp(App)
app.use(router)
app.mount('#app')
