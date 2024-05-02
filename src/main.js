import { createApp } from 'vue'
import App from './App.vue'
import router from './router'; //설정 라우터 호출
import store from './store/store'

createApp(App).mount('#app')


const app = createApp(App)
app.config.globalProperties.$store = store

app.use(router)
app.mount('#app')
