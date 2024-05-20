<template>
  <div class="calendar-container">

    <!-- 달력 형식 만들기 -->
    <div class="calendar">
      <div class="calendar-header">
        <button @click="prevMonth">이전달</button>
        <span class="current-month">{{ currentMonth }}</span>
        <button @click="nextMonth">다음달</button>
      </div>

      <!-- 주간 -->
      <div class="calendar-weekdays">
        <span v-for="day in weekdays" :key="day">{{ day }}</span>
      </div>

      <!-- 일간 -->
      <div class="calendar-days">
        <div v-for="week in weeks" :key="week.index" class="calendar-week">
          <span v-for="day in week" :key="day.date" class="calendar-day" :class="{ 'selected': day.isSelected }"
            @click="selectDay(day)">
            <span>{{ day.date }}</span>
          </span>
        </div>
      </div>
    </div>

    <!-- 예약 가능 시간 -->
    <div v-if="selectedDate" class="available-times">
      <div v-for="time in availableTimes[selectedDate]" :key="time" class="available-time" @click="selectTime(time)">{{
        time }}</div>
    </div>

    <!-- Reservation Info -->
    <div class="reservation-info">
      <div class="selected-date">선택 날짜: {{ selectedDate }}</div>
      <div class="selected-time">선택 시간: {{ selectedTime }}</div>
      <form @submit.prevent="submitReservation">
        <button type="submit">예약하기</button>
      </form>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
import axios from 'axios';

export default {
  data() {
    return {
      currentMonth: moment().format('YYYY-MM'),
      weekdays: ['일', '월', '화', '수', '목', '금', '토'],
      weeks: [],
      selectedDate: null,
      selectedTime: null,
      availableTimes: {
        // 예약 가능 시간 데이터
        // 기본적으로 비어있음. 사용자가 날짜를 선택할 때마다 업데이트됨.
      },
    };
  },
  mounted() {
    this.generateCalendar();
  },
  methods: {
    // 이전 달로 이동
    prevMonth() {
      const prevMonth = moment(this.currentMonth).subtract(1, 'month');
      this.currentMonth = prevMonth.format('YYYY-MM');
      this.generateCalendar();
    },
    // 다음 달로 이동
    nextMonth() {
      const nextMonth = moment(this.currentMonth).add(1, 'month');
      this.currentMonth = nextMonth.format('YYYY-MM');
      this.generateCalendar();
    },
    // 캘린더 생성
    generateCalendar() {
      const currentMonthMoment = moment(this.currentMonth);
      const daysInMonth = currentMonthMoment.daysInMonth();
      const firstDayOfMonth = currentMonthMoment.date(1).day(); // 월의 첫째 날 요일 
      //const lastDayOfMonth = currentMonthMoment.daysInMonth() + firstDayOfMonth - 1; // 월의 마지막 날 요일


      this.weeks = [];
      let week = [];

      // 이전 달의 마지막 날짜 추가
      for (let i = 0; i < firstDayOfMonth; i++) {
        week.push(null);
      }

      // 현재 달의 날짜 추가
      for (let day = 1; day <= daysInMonth; day++) {
        const dayMoment = currentMonthMoment.date(day);
        const dayOfWeek = dayMoment.day();
        const isSelected = dayMoment.format('YYYY-MM-DD') === this.selectedDate;

        week.push({
          date: day,
          dayOfWeek,
          isSelected,
        });
      // 주가 끝나면 새로운 주 생성
    if (dayOfWeek === 6 || day === daysInMonth) {
      this.weeks.push(week);
      week = [];
    }
  }


      // 현재 달의 날짜가 채워진 이후에 남은 요일은 null로 채움
      if (week.length > 0) {
      while (week.length < 7) {
        week.push(null);
      }
      this.weeks.push(week);
  }
      if (week.length > 0) {
        for (let i = week.length; i < 7; i++) {
          week.push(null);
        }
        this.weeks.push(week);
      }
    },
    // 날짜 선택
    selectDay(day) {
      this.selectedDate = `${this.currentMonth}-${day.date}`;
      this.selectedTime = null;

      // 선택된 경우에만 예약 가능 시간 가져오기
      if (this.selectedDate) {
        // 백엔드에서 예약 가능 시간 데이터 가져오기
        const selectedDate = this.selectedDate;
        axios.get(`/api/available-times?date=${selectedDate}`)
          .then(response => {
            this.availableTimes[selectedDate] = response.data;
          })
          .catch(error => {
            console.error('예약 가능 시간 데이터를 가져오는 중 오류가 발생했습니다:', error);
          });
      }

      // 선택된 경우에만 isSelected 설정
      for (const week of this.weeks) {
        for (const calendarDay of week) {
          if (calendarDay) {
            const dayMoment = moment(this.currentMonth).date(calendarDay.date);
            calendarDay.isSelected = dayMoment.format('YYYY-MM-DD') === this.selectedDate;
          }
        }
      }
    },
    // 시간 선택
    selectTime(time) {
      this.selectedTime = time;
    },
    // 예약 제출
    submitReservation() {
      const reservationData = {
        date: this.selectedDate,
        time: this.selectedTime,
        // 사용자 정보 추가
      };

      axios.post('/api/reservations', reservationData)
        .then(response => {
          console.log('예약이 성공적으로 완료되었습니다:', response.data);
          // 예약 성공 후 추가 로직 구현
        })
        .catch(error => {
          console.error('예약 중 오류가 발생했습니다:', error);
        });
    },
  }
}
</script>

<style>
/* 기본 스타일 */
.calendar {
  width: 500px;
  /* 달력 너비 */
  margin: 0 auto;
  /* 가운데 정렬 */
  font-family: 'Noto Sans KR', sans-serif;
  /* 한국어 폰트 사용 */
}

.calendar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.calendar-header button {
  background-color: #fff;
  border: none;
  cursor: pointer;
}

.calendar-body {
  padding: 10px;
}

.calendar-week {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.calendar-day {
  text-align: center;
  width: calc(100% / 7);
  /* 7등분 */
  padding: 5px;
  border: 1px solid #ccc;
}

.calendar-day.today {
  background-color: #f0f0f0;
  /* 오늘 날짜 배경색 */
}

/* 예약 가능한 시간대 스타일 */
.available-times {
  margin-top: 20px;
}

.available-times h2 {
  margin-bottom: 10px;
}

.available-times ul {
  list-style: none;
  padding: 0;
}

.available-times li {
  display: inline-block;
  margin-right: 10px;
}

.available-times button {
  background-color: #fff;
  border: 1px solid #ccc;
  padding: 5px 10px;
  cursor: pointer;
}

.available-times button:hover {
  background-color: #e0e0e0;
}

.available-times .selected {
  /* 선택된 시간대 스타일 */
  background-color: #007bff;
  color: #fff;
}

.available-times .disabled {
  /* 예약 완료된 시간대 스타일 */
  background-color: #ccc;
  cursor: not-allowed;
}
</style>