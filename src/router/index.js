import { createRouter, createWebHistory } from "vue-router";
import BoardList from "@/views/BoardList.vue";
import TestPage from "@/views/TestPage.vue";
import WritePage from "@/views/WritePage.vue";
import HelloWorld from "@/components/HelloWorld.vue";

const routes = [
  { path: "/helloWorld", component: HelloWorld},
  { path: "/boardList", component: BoardList },
  { path: "/testPage", component: TestPage },
  { path: "/writePage", component: WritePage },
  {path: '/error', name: 'errorPage', component: ()=>
import('@/views/ErrorPage.vue')}, //추가
{path: "/:pathMatch(.*)*", redirect: "/error"}, //추가
{ path: "/detail", component: () => import("../views/DetailPage.vue") }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});
export default router;