import { createApp } from 'vue'
import store from "@/views/store"
import App from './App.vue'
import router from './router'

//부트스트랩 적용
import BootstrapVue3	from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

//데이터피크 전역 사용
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

const app = createApp(App)

app.component('DatePicker', VueDatePicker)

app.use(store)
app.use(BootstrapVue3)
app.use(router).mount('#app')

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'


