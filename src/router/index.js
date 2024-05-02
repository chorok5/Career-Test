import { createRouter, createWebHistory } from "vue-router";
import BoardList from "@/views/BoardList.vue";
import TestPage from "@/views/TestPage.vue";
import HelloWorld from "@/components/HelloWorld.vue";

const routes = [
  { path: "/helloWorld", component: HelloWorld},
  { path: "/boardList", component: BoardList },
  { path: "/testPage", component: TestPage }
];
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});
export default router;