import { createRouter, createWebHistory } from "vue-router";
import testVue from "@/views/TestVue.vue";
import listTest from "@/views/ListTest.vue";
import Boardwrite from "@/views/BoardWrite.vue";
import insertTest from "@/views/insertTest.vue";
import PersonTest from "@/views/PersonTest.vue";
import CareerTest from "@/views/CareerTest.vue";
import ResultPage from "@/views/ResultPage.vue";

const routes = [
    { path: '/testVue', name: 'testVue', component: testVue },
    { path: '/listTest', name: 'listTest', component: listTest },
    { path: '/BoardList', name: 'BoardList', component: () => import('../views/BoardList.vue') },
    { path: '/BoardWrite', name: 'BoardWrite', component: Boardwrite },
    { path: '/insertTest', name: 'insertTest', component: insertTest },
    { path: '/PersonTest', name: 'PersonTest', component: PersonTest },
    { path: '/CareerTest', name: 'CareerTest', component: CareerTest },
    { path: '/ResultPage', name: 'ResultPage', component: ResultPage }
];


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router