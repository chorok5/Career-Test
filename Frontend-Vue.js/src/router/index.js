import ContactMap from '@/views/counselingFolder/ContactMap.vue';
import CounselingIntro from '@/views/counselingFolder/CounselingIntro.vue';
import CounselorShow from '@/views/counselingFolder/CounselorShow.vue';
import CounselorList from '@/views/alpha/CounselorList.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';
import FaqQuestion from '@/views/faqFolder/FaqQuestion.vue';
import FaqWrite from '@/views/faqFolder/FaqWrite.vue'; 
import FaqDetail from '@/views/faqFolder/FaqDetail.vue'; 
import TestMain from '@/views/testFolder/TestMain.vue';
import CareerTest from '@/views/testFolder/CareerTest.vue';
import PersonTest from '@/views/testFolder/PersonTest.vue';
import ResultPage from '@/views/testFolder/ResultPage.vue';
import mainPage from '@/views/mainPage.vue';
import testVue from "@/views/TestVue.vue";
import listTest from "@/views/ListTest.vue";
import Boardwrite from "@/views/boardviews/BoardWrite.vue";
import insertTest from "@/views/regviews/insertTest.vue";
import boardDetail from "@/views/boardviews/BoardDetail.vue";
import boardUpdate from "@/views/boardviews/BoardUpdate.vue";
import boardReply from "@/views/boardviews/BoardReply.vue";
import login from "@/views/LoginPage.vue";
import jobConsulting from "@/views/JobConsulting.vue"; // 취업상담
import mypage from "@/views/mypage/MyPage.vue";
import MyConsultations from '@/views/mypage/MyConsultations.vue';
import StudentConsultations from '@/views/mypage/StudentConsultations.vue';
import MyGroupConsulte from '@/views/mypage/MyGroupConsulte.vue';
import MyInfo from '@/views/mypage/MyInfo.vue';
import MyQna from '@/views/mypage/MyQna.vue';
import MyReviews from '@/views/mypage/MyReviews.vue';
import MySimriResult from '@/views/mypage/MySimriResult.vue';
import MySimriReserve from '@/views/mypage/MySimriReserve.vue';
import BoardList from '@/views/boardviews/BoardList.vue';
//import menu from '@/components/MenuPage.vue';
import regCounselor from '@/views/regviews/regCounselor.vue'
import pop from '@/layout/RegTimeLayout.vue';
import MainLayout from '@/layout/MainLayout.vue';
//import header from '@/components/Header.vue'
import secMenuTest from '@/views/regviews/secMenuTest.vue';
import applyForm3 from '@/views/RsvAndApply/applyForm3.vue';
import resultWrite from '@/views/regviews/resultWrite.vue';
import ProResult from '@/views/regviews/ProResult.vue';
import ProResultUpdate from '@/views/regviews/ProResultUpdate.vue';
import ProResultWrite from '@/views/regviews/ProResultWrite.vue';
import regResult from '@/views/regviews/regResult.vue';
import jobapply from '@/views/jobs/apply.vue';
import jobintro from '@/views/jobs/intro.vue';
import joblist from '@/views/jobs/list.vue';
import jobtest from '@/views/jobs/test.vue';

import cardNews from '@/views/alpha/cardNews.vue';
import cardDetail1 from '@/views/alpha/cardDetail1.vue';
import cardDetail2 from '@/views/alpha/cardDetail2.vue';
import cardDetail3 from '@/views/alpha/cardDetail3.vue';

import adminMemberControl from '@/layout/AdminSidebar.vue';
import MemberControl from '@/components/MemberControl.vue';
import BoardControl from '@/components/BoardControl.vue';
import resultUpdate from '@/views/regviews/resultUpdate.vue';
import regIntroduce from '@/views/regviews/regIntroduce.vue';

import { createRouter,createWebHashHistory } from 'vue-router';
import axios from 'axios';
import store from '@/store';

const routes = [
    {path: '/', component: mainPage, meta: {layout : MainLayout}},    
    {path: '/menu', name:'testVue', component: testVue, meta: {layout : MainLayout}},
    {path: '/testVue', name:'testVue', component: testVue, meta: {layout : MainLayout}},
    {path: '/listTest', name:'listTest', component: listTest, meta: {layout : MainLayout}},
    {path:'/BoardList', name:'BoardList', component: BoardList, meta: {layout : MainLayout}},
    {path:'/BoardWrite', name:'BoardWrite', component: Boardwrite, meta: {layout : MainLayout},
    beforeEnter: (to,from,next) =>{
      if(store.state.account.id!=null){
        next()
      }else{
        next('/')
      }
    }},
    {path: '/insertTest', name:'insertTest', component: insertTest, meta: {layout : MainLayout}},
    {path:"/boardDetail", name:"boardDetail", component: boardDetail, meta: {layout : MainLayout}},
    {path:"/boardUpdate", name:"boardUpdate",component:boardUpdate, meta: {layout : MainLayout}},


    {path:"/groupCreate", name:"/groupCreate", component:()=> import("@/views/groupviews/groupCreate.vue"), meta: {layout : MainLayout},
    beforeEnter: (to,from,next) =>{
      if(store.state.account.id!=null){
        next()
      }else{
        next('/')
      }
    }},


    {path: '/regRev', name:'regRev', component: () => import('@/views/regviews/RegRev.vue'), meta: {layout : MainLayout}},
    {path: '/regTime', component: () => import('@/views/regviews/RegTime.vue'), meta: { layout:pop}},
    {path: '/GroupList', name:'groupList', component: () => import('@/views/groupviews/groupList.vue'), meta: {layout : MainLayout}},
    {path: "/groupDetail", name:"groupDetail", component:()=> import("@/views/groupviews/groupDetail.vue"), meta: {layout : MainLayout}},
    {path: "/BoardReply", name:"BoardReply",component:boardReply, meta: {layout : MainLayout}},
    {path: "/rsrv", component:()=> import("@/views/RsvAndApply/rsrvTest3.vue"), meta: {layout : MainLayout}},
    {path: '/rsrvTest', name:'rsrvTest', component: () => import('@/views/RsvAndApply/rsrvTest.vue')},
    {path: '/rsrvTest2', name:'rsrvTest2', component: () => import('@/views/RsvAndApply/rsrvTest2.vue')},
    { path: '/rsrvTest4', name: 'rsrvTest4', component: () => import('@/views/RsvAndApply/rsrvTest4.vue'), meta: { layout: MainLayout },

    beforeEnter: (to, from, next) => {
      if (store.state.account.id != null) {
        next()
      } else {
        alert('상담 예약은 로그인 후 가능합니다.')
        next('/login')
      }
  }},
    {path: '/rsrvTest3', name:'rsrvTest3', component: () => import('@/views/RsvAndApply/rsrvTest3.vue')},
    {path: '/applyForm1', name:'applyForm1', component: () => import('@/views/RsvAndApply/applyForm1.vue'), props: route => ({ ...route.query }), meta: {layout : MainLayout}},
    {path: '/applyForm2', name:'applyForm2', component: () => import('@/views/RsvAndApply/applyForm2.vue'), props: route => ({ ...route.query }), meta: {layout : MainLayout}},
    {path: '/applyForm3', name:'applyForm3', component: () => import('@/views/RsvAndApply/applyForm3.vue'), props: route => ({ ...route.query }), meta: {layout : MainLayout}},
    {path: '/applyForm4', name:'applyForm4', component: () => import('@/views/RsvAndApply/applyForm4.vue'), props: route => ({ ...route.query }), meta: {layout : MainLayout}},
    {path: '/jobConsulting', name: 'jobConsulting', component: jobConsulting, meta: {layout : MainLayout}}, // 취업상담 경로 추가
    {path: '/login', name: 'login', component: login, meta: {layout : MainLayout} }, // 로그인 경로 추가
    {path: '/mypage', name: 'mypage',component: mypage, meta: {layout : MainLayout}}, // 마이페이지
    {path: '/simrimain', name: 'SimriMain', component: import('@/views/SimriMain.vue'), meta: {layout : MainLayout}},
    {path: '/contact', name: 'Contact', component: ContactMap, meta: {layout : MainLayout}},
    {path: '/counseling', name: 'Counseling', component: CounselingIntro, meta: {layout : MainLayout}},
    {path: '/counselorshow', name: 'CounselorShow', component: CounselorShow, meta: {layout : MainLayout}},
    {path: '/counselorList', name: 'CounselorList', component: CounselorList, meta: {layout : MainLayout}},
    {path: '/simrisec', name: 'simrisec', component: SimriSecMenu, meta: {layout : MainLayout}},
    {path: '/faq', name: 'FAQ', component: FaqQuestion, meta: {layout : MainLayout}},

    {path:"/faqwrite", name:"/faqwrite", component:()=> import("@/views/faqFolder/FaqWrite.vue"), meta: {layout : MainLayout},
    beforeEnter: (to,from,next) =>{
      if(store.state.account.id!=null){
        next()
      }else{
        next('/')
      }
    }},


    {path: '/faqwrite', name: 'FaqWrite', component: FaqWrite, meta: {layout : MainLayout}},
    {path: '/faq/:id', name: 'FaqDetail', component: FaqDetail, meta: {layout : MainLayout}},// 동적 세그먼트를 포함하는 라우트 추가
    {path: '/test',name: 'Test',component: TestMain, meta: {layout : MainLayout}},
    {path: '/test/career',name: 'CareerTest',component: CareerTest, meta: {layout : MainLayout}},
    {path: '/test/person', name: 'PersonTest',component: PersonTest, meta: {layout : MainLayout}},
    {path: '/regIntroduce', name: 'regIntroduce',component: regIntroduce, meta: {layout : MainLayout}},
    {path: '/regCounselor', name: 'regCounselor',component: regCounselor, meta: {layout : MainLayout}},



    {path: '/admin', name: 'admin',component: MemberControl,meta:{layout:adminMemberControl},
      beforeEnter:async (to,from,next) =>{
        const check = await userPermission();
        if(check){
          next()
        } else{
          next('/')
        }
      }
  },
    {path: '/adminMemberControl', name: 'adminMemberControl',component: MemberControl,meta:{layout:adminMemberControl},
    beforeEnter:async (to,from,next) =>{
      const check = await userPermission();
      if(check){
        next()
      } else{
        next('/')
      }
    }
  },
    {path: '/adminBoardControl', name: 'adminBoardControl',component: BoardControl,meta:{layout:adminMemberControl},
    beforeEnter:async (to,from,next) =>{
      const check = await userPermission();
      if(check){
        next()
      } else{
        next('/')
      }
    }
  },
  



    
    {path: '/test/result', name: 'ResultPage',component: ResultPage, meta: {layout : MainLayout}},
    {path: '/mypage', name: 'mypage', component: mypage, meta: {layout : MainLayout}},
    {path: '/myconsultations', name: 'myconsultations', component: MyConsultations, meta: { layout: MainLayout } },
    {path: '/StudentConsultations', name: 'StudentConsultations', component: StudentConsultations, meta: { layout: MainLayout } },
    {path: '/mygroup', name: 'mygroup', component: MyGroupConsulte, meta: { layout: MainLayout } },
    {path: '/myqna', name: 'myqna', component: MyQna, meta: { layout: MainLayout } },
    {path: '/myinfo', name: 'myinfo', component: MyInfo, meta: { layout: MainLayout } },
    {path: '/myreviews', name: 'myreviews', component: MyReviews, meta: { layout: MainLayout } },
    {path: '/mysimri', name: 'mysimri', component: MySimriResult, meta: { layout: MainLayout } },
    {path: '/mysimrireserve', name: 'mysimrireserve', component: MySimriReserve, meta: { layout: MainLayout } },
    {path: '/secMenuTest', name: 'secMenuTest', component: secMenuTest, meta: { layout: MainLayout } },
    {path: '/applyForm3', name: 'applyForm3', component: applyForm3, meta: { layout: MainLayout } },
    {path: '/resultWrite', name: 'resultWrite', component: resultWrite, meta: { layout: MainLayout } },
    {path: '/ProResult/:rsvno', name: 'ProResult', props: true, component: ProResult, meta: { layout: MainLayout } },
    {path: '/ProResultUpdate/:rsvno', name: 'ProResultUpdate', props: true, component: ProResultUpdate, meta: { layout: MainLayout } },
    {path: '/ProResultWrite/:rsvno', name: 'ProResultWrite', props: true, component: ProResultWrite, meta: { layout: MainLayout } },
    {path: '/regResult', name: 'regResult', component: regResult, meta: { layout: MainLayout } },
    {path: '/jobs/apply', name: 'jobapply', component: jobapply, meta: { layout: MainLayout } },
    {path: '/jobs/intro', name: 'jobintro', component: jobintro, meta: { layout: MainLayout } },
    {path: '/jobs/list', name: 'joblist', component: joblist, meta: { layout: MainLayout } },
    {path: '/jobs/test', name: 'jobtest', component: jobtest, meta: { layout: MainLayout } },
    {path: '/cardNews', name: 'cardNews', component: cardNews, meta: { layout: MainLayout } },
    {path: '/cardDetail1', name: 'cardDetail1', component: cardDetail1, meta: { layout: MainLayout } },
    {path: '/cardDetail2', name: 'cardDetail2', component: cardDetail2, meta: { layout: MainLayout } },
    {path: '/cardDetail3', name: 'cardDetail3', component: cardDetail3, meta: { layout: MainLayout } },
    {path: '/resultUpdate', name: 'resultUpdate', component: resultUpdate, meta: { layout: MainLayout } },


    
    
];

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
});


async function userPermission(){
  try {
    const res = await axios.post("/api/admin/admincheck", {}, { withCredentials: true });
    if (res.data == 1) {
      return true;
    } else {
      return false;
    }
  } catch (error) {
    console.error("Error checking user permission:", error);
    return false;
  }
}


export default router;
