<template>
  <div>
    <h1>{{stuNum}}</h1>
    <div class="rsv-container">
    <div class="calendar-container">
        <div class="calendar-header">
        <div class="prev-month" @click="prevMonth">
            <span>이전 달</span>
        </div>
        <!-- 현재 년/월 -->
        <div class="current-month">
            {{ currentYear }}년 {{ currentMonth }}월
        </div>
        <div class="next-month" @click="nextMonth">
            <span>다음 달</span>
        </div>
        </div>

        <table class="calendar">
        <thead>
            <tr>
                <!-- 요일 = daysOfWeek -->
                <th v-for="day in daysOfWeek" :key="day">{{ day }}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(week, rowIndex) in calendar" :key="rowIndex">
              <td
                v-for="(day, cellIndex) in week"
                :key="cellIndex"
                :class="[{
                  'current-day': isCurrentDay(day.date),
                  disabled: pastDate(day.date),
                },
                {'selected' : isSelected === rowIndex+''+cellIndex}]"
                @click="selectDay(rowIndex, cellIndex, day.date)"
              >
                {{ day.day }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="available-times-container">
        <table>
          <thead>
            <tr>
              <th>예약 가능 시간</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(time, index) in availableTime" :key="time">
              <td v-if="selectedDate" :class="{selected : active == time.time}" @click="selectTime(time,index)">{{ time.time }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <form @submit.prevent="regSubmit" id="application">
      <input type="text" name="stuNum" v-model="application.stuNum">
      <input type="text" name="date" v-model="application.date">
      <input type="text" name="time" v-model="application.time">
      <input type="text" name="regno" v-model="application.regno">
      <textarea name="memo" v-model="application.memo"></textarea>
      <button type="submit">신청하기</button>
    </form>
  </div>
</template>

<script>
import { ref, computed } from "vue";
import axios from "axios";

export default {
  mounted(){
    this.application.stuNum = this.$route.query.stuNum;
    this.application.regno = this.$route.query.regno;
  },
  data(){
    return{
      isSelected : null,
      stuNum : null,
      active: false,
      availableTime: [],
      application: {
        stuNum: '',
        date: '',
        time: '',
        regno: '',
        memo:'',
      },
    };
  },
  setup() {
    const currentDate = new Date();
    const currentYear = ref(currentDate.getFullYear());
    const currentMonth = ref(currentDate.getMonth() + 1);
    const selectedDate = ref(currentDate);
    const availableTimes = ref("");

    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

    const calendar = computed(() => {
      const firstDayOfMonth = new Date(
        currentYear.value,
        currentMonth.value - 1,
        1
      ).getDay();
      const daysInMonth = new Date(
        currentYear.value,
        currentMonth.value,
        0
      ).getDate();

      const calendarArray = [];
      let week = [];

      // 이전 달의 마지막 날짜 추가
      for (let i = 0; i < firstDayOfMonth; i++) {
        week.push({ day: "", date: null, isCurrentMonth: false });
      }

      // 현재 달의 날짜 추가
      for (let i = 1; i <= daysInMonth; i++) {
        const date = new Date(currentYear.value, currentMonth.value - 1, i);
        week.push({ day: i, date, isCurrentMonth: true });

        if (week.length === 7) {
          calendarArray.push(week);
          week = [];
        }
      }

      // 다음 달의 첫 날짜 추가
      if (week.length > 0) {
        for (let i = week.length; i < 7; i++) {
          week.push({ day: "", date: null, isCurrentMonth: false });
        }
        calendarArray.push(week);
      }

      return calendarArray;
    });

    const prevMonth = () => {
      if (currentMonth.value === 1) {
        currentMonth.value = 12;
        currentYear.value--;
      } else {
        currentMonth.value--;
      }
    };

    const nextMonth = () => {
      if (currentMonth.value === 12) {
        currentMonth.value = 1;
        currentYear.value++;
      } else {
        currentMonth.value++;
      }
    };
    const pastDate = (date) => {
      const today = new Date();
      return date < today;
    }

    //없엘까
    const available = (date) => {
      const today = new Date();
      return date > today;
    }
    const isCurrentDay = (date) => {
      if (!date) {
        return false; // 날짜가 null인 경우 false를 반환
      }

      const today = new Date();
      return (
        date.getFullYear() === today.getFullYear() &&
        date.getMonth() === today.getMonth() &&
        date.getDate() === today.getDate()
      );
    };

    return {
      currentYear,
      currentMonth,
      selectedDate,
      availableTimes,
      daysOfWeek,
      calendar,
      prevMonth,
      nextMonth,
      isCurrentDay,
      pastDate,
      available,
    };
  },
  methods: {
    async selectDay(rowIndex,cellIndex,date){
      try {
        const today = new Date(); 
        if(date<today){
          alert("오늘보다 이전 날짜로는 신청 할 수 없습니다.");
          return false;
        }
        this.active = '';
        this.application.time = '';

        const response = await axios.get('http://localhost:3000/timetable');
        this.availableTime = response.data;
        console.log(response.data);
      } catch(error) {
        console.log(error);
      }
      this.isSelected = rowIndex+''+cellIndex;
      this.selectedDate = date;
      this.application.date = this.selectedDate;
      console.log(date);
    },
    async regSubmit(){
      console.log(this.application);
      const today = new Date();
      if(this.application.date < today){
        alert("오늘보다 이전 날짜로 지정 할 수 없습니다.");
        return false;
      }
      if(this.application.date.length<1 || this.application.time.length<1
        || this.application.stuNum.length<1 || this.application.regno.length<1) {
        alert("다시 선택해주세요");
        return false;
      }
      if(!confirm('신청하시겠습니까?')){
        return false;
      }
      await axios.post('http://localhost:3000/regSubmit',this.application)
      .then((res)=>{
        console.log(res);
        if(res.data>0){
          alert('제출됨');
          window.close();
        } else {
          alert('제출 실패');
        }
      }).catch((err)=>{
        alert(err);
      })
      //window.close();
    },
    selectTime(time){
      this.application.time = time.time;
      this.active = time.time;
    }
  }
};
</script>

<style>
.rsv-container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.calendar-container {
  flex: 1;
  margin-right: 20px;
}

.calendar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.calendar {
  width: 100%;
  border-collapse: collapse;
}

.calendar th,
.calendar td {
  padding: 8px;
  text-align: center;
  border: 1px solid #ccc;
}

.calendar th {
  background-color: #f2f2f2;
}

.calendar td.disabled {
  color: #ccc;
  background-color: #c0c0c0;
}

.calendar td.current-day {
  color: black;
  background-color: skyblue;
}

.available-times-container {
  flex: 1;
}
.selected{
  background-color: yellow;
}
.available{
  background-color: white;
}
.selectedTime{
  background-color: yellow;
}
</style>
