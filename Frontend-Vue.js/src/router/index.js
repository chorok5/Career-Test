import { createRouter, createWebHistory } from 'vue-router';
import SimriMain from '@/views/SimriMain.vue';
import ContactMap from '@/views/contactFolder/ContactMap.vue';
import CounselingIntro from '@/views/counselingFolder/CounselingIntro.vue';
import CounselorShow from '@/views/counselorShow/CounselorShow.vue';
import FAQQuestion from '@/views/faqFolder/FAQQuestion.vue';
import CareerTest from '@/views/testFolder/CareerTest.vue';
import PersonTest from '@/views/testFolder/PersonTest.vue';
import ResultPage from '@/views/testFolder/ResultPage.vue';
import OfflineSubmit from '@/views/submitFolder/OfflineSubmit.vue';

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'SimriMain',
      component: SimriMain,
    },
    {
      path: '/contact',
      name: 'Contact',
      component: ContactMap,
    },
    {
      path: '/counseling',
      name: 'Counseling',
      component: CounselingIntro,
    },
    {
        path: '/counselorshow',
        name: 'CounselorShow',
        component: CounselorShow,
    },
    {
      path: '/faq',
      name: 'FAQ',
      component: FAQQuestion,
    },
    {
        path: '/offlineSubmit',
        name: 'OfflineSubmit',
        component: OfflineSubmit,
      },
    {
      path: '/test',
      name: 'Test',
      component: CareerTest,
      children: [
        {
          path: 'career',
          name: 'CareerTest',
          component: CareerTest,
        },
        {
          path: 'person',
          name: 'PersonTest',
          component: PersonTest,
        },
        {
          path: 'result',
          name: 'ResultPage',
          component: ResultPage,
        },
      ],
    },
  ],
});

export default router;
