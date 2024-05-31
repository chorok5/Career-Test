<template>
  <div>
        <MySidebar></MySidebar>
    <div class="boundary">
        <main class="main-content">
          <h2 class="mb-4 title">나의 정보</h2>
          <hr class="line">
          <div style="display: flex; justify-content: space-between;">
          <div class="userInfo mb-4">
            <div class="mb-4"><h4 style="text-align: left; font-family: 'sjl';">개인 정보</h4></div>
            <div class="profileBox">
            <div>
            <div class="img"><img src="@/assets/img/myProfile.png" alt=""></div>
            <div class="profileName">{{ profile.name }}</div>
            </div>
            <div class="infoBox">
              <div>소속 : {{ profile.major_name }}</div>
              <div>학번 : {{ profile.id }}</div>
              <div>구분 : 
                <span v-if="profile.grade == 3">관리자</span>
                <span v-if="profile.grade == 2">교직원</span>
                <span v-if="profile.grade < 2">학생</span>
              </div>
              <div>생년월일 : {{ profile.birth_date }}</div>
              <div>연락처 : {{ profile.contact }}</div>              
            </div>            
            </div>
          </div>
          <div class="notice mb-4">
            <div class="mb-4">
              <h4 v-if="grade<2" style="text-align: left; font-family: 'sjl';">최근 글</h4>
              <h4 v-if="grade == 2" style="text-align: left; font-family: 'sjl';">일지 미작성 상담 ({{ needList.length }}건)</h4>
            </div>
            <div class="scrollDiv">            
            <table v-if="grade<2" class="myBoard">
              <thead>
                <tr>
                  <td>글 번호</td>
                  <td>제목</td>
                  <td>작성일</td>
                </tr>
              </thead>
              <tbody>
                <tr v-for="row in recentBoardList" :key="row.bno">
                  <td class="w2">{{row.bno}}</td>
                  <td class="w5">{{row.btitle}}</td>
                  <td class="w3">{{row.date}}</td>
                </tr>
              </tbody>
            </table>
            <!-- 교직원용 -->
            <table v-if="grade==2" class="myBoard">
              <thead>
                <tr>
                  <td>상담일</td>
                  <td>상담 시간</td>
                  <td>내담자명</td>
                  <td>일지 작성</td>
                </tr>
              </thead>
              <tbody>
                <tr v-for="row in needList" :key="row.bno">
                  <td class="w2">{{row.date}}</td>
                  <td class="w3">{{row.time}}</td>
                  <td class="w3">{{row.name}}</td>
                  <td class="w2">
                    <button @click="write(row.no,row.type)">일지 작성</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          </div>
          </div>
          <div class="schedule">
          <div class="calendarContainer">
            <h4 class="boxTitle">시간표</h4>
            <FullCalendar :options="calendarOptions" id="calendar"></FullCalendar>
          </div>
          <div class="timeListContainer">
          <h4 class="boxTitle mt-3 mb-3">일정</h4>
          <div class="date">
            <a @click="postday()">⬅️</a>
            <span>{{today}}</span>
            <a @click="nextday()">➡️</a>
          </div>
          <hr class="halfLine mb-3" >
          <div class="timeList">
            <ul>
              <li v-for="row in schedule" :key="row.title">                
                <span v-if="this.grade<2">{{row.time}}&ensp;|&ensp; {{ row.title }}&ensp;  ( {{ row.proName }} )</span> 
                <span v-if="this.grade>=2">{{row.time}}&ensp;|&ensp; {{ row.title }}&ensp; ( {{ row.stuName }} )</span>
              </li>
              <li v-if="schedule.length < 1" style="text-align: center; border: none;">일정이 존재하지 않습니다.</li>
            </ul>
          </div>
          </div>
        </div>
        </main>
    </div>
  </div>
</template>

<script>
import MySidebar from '@/components/MySidebar.vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import axios from 'axios';
import '../../css/calendar2.css';
import dayjs from 'dayjs';

export default {
  name: 'MyPage',
  components: {
    MySidebar,
    FullCalendar
  },
  data(){
    return {
      id:'',
      grade:'',
      today: null,
      schedule: [],
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        locale: 'ko',
        selectable: false,
        weekends: false,
        editable: false,
        validRange: { // 오늘부터 한 달까지만 달력에 나오도록
          start: Date.now(),
          //start: new Date(new Date().setDate(new Date().getDate() + 1)),
          //end: Date.now() + 2592000000,
          end: new Date(new Date().setMonth(new Date().getMonth() + 1))
        },
        events: [],
        eventClick: this.handleEventClick,
        dateClick: this.fetchDateInfo,
        dayCellDidMount: this.handleDayMount,
      },
      profile:[],
      recentBoardList:[],
      needList:[],
    }
  },
  mounted(){
    this.today = dayjs().format('YYYY-MM-DD');    
    this.id = this.$store.state.account.id;
    this.getScehdule().then(()=>{
      this.getOnedayScehdule();
      if(this.grade < 2){
        this.recentBoard();
      } else if(this.grade==2) {
        this.need2Write();
      }
    })    
  },
  methods: {
    nextday(){
      this.today = dayjs(this.today).add(1,'day').format('YYYY-MM-DD');
      this.getOnedayScehdule();
    },
    postday(){
      this.today = dayjs(this.today).subtract(1,'day').format('YYYY-MM-DD');
      this.getOnedayScehdule();
    },
    getOnedayScehdule(){
      const params = new URLSearchParams();
      params.append('date', this.today );
      params.append('id', this.id );
      params.append('grade', this.grade );
      axios.get('http://localhost:3000/rsvs',{params:params}).then((res) => {
      this.schedule = res.data;
      console.log(res.data);
    }).catch((err) => {
      console.log(err);
    })   
    },
    async getScehdule(){
      await this.getGrade();
      const params = new URLSearchParams();      
      params.append('id', this.id );
      params.append('grade', this.grade );
      axios.get('http://localhost:3000/getScehdule',{params:params}).then((res) => {
      this.calendarOptions.events = res.data;
    }).catch((err) => {
      console.log(err);
    })   
    },
    async getGrade(){
      await axios.get('http://localhost:3000/memberDetail?id='+this.id).then((res) => {      
        console.log(res.data[0]);
      this.grade = res.data[0].grade;      
      this.profile = res.data[0];
      return this.grade;
    }).catch((err) => {
      console.log(err);
    })
    },
    async recentBoard(){
      await axios.get('http://localhost:3000/recentBoard?id='+this.id).then((res)=>{
        console.log(res.data);
        this.recentBoardList = res.data;
      }).catch((err) => {
        console.log(err);
      })
    },
    async need2Write(){
      await axios.get('http://localhost:3000/need2Write?id='+this.id).then((res) => {
        this.needList = res.data;
        console.log(res.data);
      }).catch((err) => {
        console.log(err.data);
      })
    },
    write(no,type){
      if(type === "reg"){
        this.$router.push("/resultWrite?regno="+no);
      } else {
        this.$router.push("/ProResultWrite/"+no);
      }
    }
  }

}
</script>

<style scoped>
.userInfo{
  width: 49%;
  height: 290px;
  padding: 20px 30px 30px 30px;
  border: 1px solid #c0c0c0;
  background-color: white;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.175) !important;
  border-radius: 15px;
}
.myBoard thead{
  border-bottom: 1px solid #c0c0c0;
}
.myBoard tr td{
  height: 30px;
  font-family: 'pr';
  border-bottom: 1px solid #c0c0c0;
  font-size: smaller;  
}
.scrollDiv{
  height: 195px;
  overflow: auto;
}
.w2{
  width: 20%;
}
.w2 button{
  background-color: white;
}
.w3{
  width: 30%;
}
.w5{
  width: 50%;
}
.myBoard{
  width: 100%;
}
.line{
  width: 40%;  
}
.title{
  text-align: left;
  font-family: 'sj';
}
.notice{
  width: 49%;
  height: 290px;
  padding: 20px 30px 30px 30px;
  border: 1px solid #c0c0c0;
  background-color: white;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.175) !important;
  border-radius: 15px;
}
.profileBox{
  display: flex;
}
.boxTitle{
  font-family: 'sj';
}
.date span{
  margin: 0 20px 0 20px;
  font-family: 'sj';
  font-size: large;
}
.date a{
  cursor: pointer;
}
.halfLine{
  width: 90%;
  margin: 0 auto;
}
.img{
  width: 120px;
  height: 120px;
  background-color: grey;
  border-radius: 50%;
}
.img img{
  width: 120px;
  height: 120px;
  border-radius: 50%;
}
.profileName{
  margin-top: 20px;
}
.schedule{
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.container-fluid {
  padding-top: 56px; 
}
.calendarContainer{
  width: 49%;
  margin: 0;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.175) !important;
  padding: 15px;
  border: 1px solid #c0c0c0;
  border-radius: 15px;
}
.timeListContainer{
  width: 49%;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.175) !important;
  border: 1px solid #c0c0c0;
  border-radius: 15px;
  padding: 10px
}
.timeList{
  width: 90%;
  border : 1px soild black;
  margin: 0 auto;
}
.timeList ul{
  list-style: none;
  padding: 0 0;
}
.infoBox{
  width: calc(100% - 190px);
  text-align: left;
  margin-left: 30px;
  line-height: 2;
}
.timeList ul li {
  height: 45px;
  position: relative;
  box-sizing: border-box;
  border-bottom: 1px solid #dfdfdf;
  line-height: 45px;
  text-align: left;
  font-family: 'sjl';
}
.boundary{
  width: 100%;
  max-width: 1200px;
}
.sidebar {
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  height: calc(100vh - 56px);
  padding-top: 1rem;
  overflow-x: hidden;
  overflow-y: auto; 
}
.sidebar .nav-link {
  font-weight: 500;
  color: #333;
}
.sidebar .nav-link:hover {
  color: #007bff;
}
.main-content {
  flex: 1; /* 나머지 공간을 모두 채우도록 설정 */
  padding-left: 20px; /* 사이드바 영역과 겹치지 않도록 왼쪽 여백 설정 */
}
a{
  color:black;
}
</style>
