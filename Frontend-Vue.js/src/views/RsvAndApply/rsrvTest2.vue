<template>
  <div class="rsrvpage">
    <h2>예약 페이지</h2>
    <div class="datepicker-container open">
      <vue3-datepicker v-model="selectedDate" />
    </div>
    <div class="available-times-container">
      <!-- 예약 가능 시간 목록 -->
      <h3>예약 가능 시간</h3>
      <ul>
        <li v-for="time in availableTimes" :key="time">{{ time }}</li>
      </ul>
    </div>
    <div v-if="selectedTime">
      <button @click="goToReservationForm">예약하기</button>
    </div>
  </div>
</template>

<script>
import { ref, watch } from 'vue';
import Datepicker from 'vue3-datepicker';
// vue-awesome-swiper에서 필요한 컴포넌트 import
//import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
//import 'swiper/swiper-bundle.css'; // swiper 스타일 import

export default {
  components: {
    Vue3Datepicker: Datepicker
  },
  setup() {
    const selectedDate = ref(null);
    const selectedTime = ref(null);

    const goToReservationForm = () => {
      // 예약 신청 form으로 이동하는 로직을 작성합니다.
    };

    // 가상의 예약 가능 시간 데이터
    const availableTimes = ['09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00'];

    // 달력이 열렸는지 확인하기 위한 상태 변수
    const isCalendarOpen = ref(false);

    // selectedDate가 변경될 때마다 isCalendarOpen을 true로 설정
    watch(selectedDate, (newValue) => {
      isCalendarOpen.value = !!newValue;
    });

    return {
      selectedDate,
      selectedTime,
      goToReservationForm,
      availableTimes,
      isCalendarOpen
    };
  }
}
</script>

<style scoped>

.rsrvpage {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.datepicker-container {
  flex: 1;
  margin-right: 20px; /* 예약 가능 시간과의 간격 조절 */
}

.datepicker-container.open .mx-datepicker {
  display: block;
}

.available-times-container {
  flex: 1;
}
</style>