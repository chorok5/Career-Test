<template>
  <div class="rsv-container">
    <div class="calendar-container">
      <div class="calendar-header">
        <div class="prev-month" @click="prevMonth">
          <span>이전 달</span>
        </div>
            <!-- 현재 년/월 -->
        <div class="current-month">{{ currentYear }}년 {{ currentMonth }}월</div>
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
          <tr v-for="week in calendar" :key="week">
            <td v-for="day in week" :key="day.date"
              :class="{ 'current-day': isCurrentDay(day.date), 'disabled': !day.isCurrentMonth }"
              @click="selectDay(day.date)">
              {{ day.day }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="available-times-container">
      <h3>예약 가능 시간</h3>
      <ul v-if="selectedDate">
        <li v-for="time in availableTimes" :key="time">{{ time }}</li>
        <li v-if="availableTimes.length === 0">예약 가능한 시간이 없습니다.</li>
      </ul>
      <p v-else>날짜를 선택해주세요.</p>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';

export default {
  setup() {
    const currentDate = new Date();
    const currentYear = ref(currentDate.getFullYear());
    const currentMonth = ref(currentDate.getMonth() + 1);
    const selectedDate = ref(currentDate);
    const availableTimes = ref('');

    const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];

    const calendar = computed(() => {
      const firstDayOfMonth = new Date(currentYear.value, currentMonth.value - 1, 1).getDay();
      const daysInMonth = new Date(currentYear.value, currentMonth.value, 0).getDate();

      const calendarArray = [];
      let week = [];

      // 이전 달의 마지막 날짜 추가
      for (let i = 0; i < firstDayOfMonth; i++) {
        week.push({ day: '', date: null, isCurrentMonth: false });
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
          week.push({ day: '', date: null, isCurrentMonth: false });
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

    const selectDay = (date) => {
      selectedDate.value = date;
      // 실제로는 백엔드 API를 호출하여 해당 날짜의 예약 가능 시간을 가져와야 합니다.
      // 예시: fetchAvailableTimes(date);
      availableTimes.value = ['09:00', '10:00', '11:00', '14:00', '15:00'];
    };

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
      selectDay,
      isCurrentDay,
    };
  },
};
</script>

<style scoped>
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
}

.calendar td.current-day {
  background-color: #e0e0e0;
}

.available-times-container {
  flex: 1;
}
</style>