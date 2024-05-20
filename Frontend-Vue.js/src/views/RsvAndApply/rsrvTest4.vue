<template>
  <div class="container calendar-wrapper my-5">
    <h2 class="text-center mb-4">상담 신청하기</h2>
    <div class="row">
      <div class="col-md-6">
        <div class="calendar-container mb-4">
          <FullCalendar :options="calendarOptions" id="calendar"></FullCalendar>
        </div>
      </div>
      <div class="col-md-6">
        <div class="available-times p-3 border rounded">

          <!-- 240517 상담 유형 선택 추가 -->
          <div class="counseling-type-selection mb-3">
            <h3 class="h5 mb-3">상담 유형을 선택하세요:</h3>
            <div role="group" aria-label="Counseling Types">
              <button type="button" v-for="type in counselingTypes" :key="type" class="counseling-type"
                :class="{ 'selected': selectedCounselingType === type }" @click="selectCounselingType(type)">
                {{ type }}
              </button>
            </div>
          </div>

          <!-- 날짜가 선택되지 않았을 때의 메시지 -->
          <div v-if="!selectedDate" class="alert alert-info">
            희망하는 날짜를 선택하시면 <br> 예약 가능 시간이 나타납니다.
          </div>
          <!-- 날짜가 선택되었을 때의 메시지 -->
          <div v-else>
            <h3 class="h5">{{ selectedDate }}의 예약 가능 시간</h3>
            <p v-if="isPast">당일 및 이전 날짜는 예약이 불가능합니다.</p>
            <ul class="list-group">
              <li v-for="time in availableTimes" :key="time.time"
                class="list-group-item d-flex justify-content-between align-items-center">
                <label class="form-check-label">
                  <input type="radio" v-model="selectedTime" :value="time" :disabled="!time.available"
                    class="form-check-input me-2">
                  {{ time.time }}
                </label>
                <span class="badge" :class="{ 'bg-success': time.available, 'bg-secondary': !time.available }">
                  {{ time.available ? '예약 가능' : '예약 불가' }}
                </span>
              </li>
            </ul>
            <button v-if="!isPast && availableTimes.some(t => t.available)" @click="submitReservation">신청하기</button>
          </div>
        </div>
      </div>
    </div>
    <event-modal v-model="showModal" :event="currentEvent"></event-modal>
  </div>
</template>

<script>
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import EventModal from './EventModal.vue';
import axios from 'axios';
import '../../css/calendar.css';
import 'bootstrap/dist/css/bootstrap.min.css';

export default {
  components: {
    FullCalendar,
    EventModal
  },
  data() {
    return {
      selectedDate: '',
      selectedTime: null,
      availableTimes: [],
      isPast: false,
      showModal: false, // 모달창 표시 여부
      currentEvent: {}, // 현재 이벤트 객체
      counselingTypes: ['지도교수 상담', '취업상담', '전문 상담', '심리 상담'], // 상담 유형 추가
      selectedCounselingType: '', // 선택된 상담 유형 초기화
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
        dateClick: this.fetchDateInfo, // 수정: dateClick 핸들러를 Ajax 호출 함수로 변경
        dayCellDidMount: this.handleDayMount
      }
    };
  },
  created() {
    this.loadReservations();
  },
  methods: {
    loadReservations() {
      axios.get('/api/reservations')
        .then(response => {
          this.calendarOptions.events = response.data.map(reservation => {
            return {
              title: 'Reservation',
              start: `${reservation.cdate}T${reservation.ctime.split(' - ')[0]}:00`,
              end: `${reservation.cdate}T${reservation.ctime.split(' - ')[1]}:00`
            };
          });
        })
        .catch(error => {
          console.error("Error fetching reservations:", error);
        });
    },
    fetchDateInfo(info) {
      this.selectedDate = info.dateStr;
      this.selectedTime = null;  // 시간 선택 초기화

      const today = new Date();
      today.setHours(0, 0, 0, 0);
      const clickedDate = new Date(info.dateStr);

      //  this.isPast = clickedDate < today;
      //  if (this.isPast) {
      //    this.availableTimes = [];
      //    return;
      //  }
      this.isPast = clickedDate < today;
      if (this.isPast) {
        this.availableTimes = [];
        return;
      } else if (clickedDate.toDateString() === today.toDateString()) {
        this.isPast = true; // 당일 클릭 시 isPast를 true로 설정
        this.availableTimes = [];
        return;
      }

      //// 더미 데이터 
      //this.availableTimes = [
      //  { id: 1, time: '10:00 - 11:00', availableSeats: 3 },
      //  { id: 2, time: '11:00 - 12:00', availableSeats: 5 },
      //  { id: 3, time: '13:00 - 14:00', availableSeats: 2 },
      //  { id: 4, time: '14:00 - 15:00', availableSeats: 1 },
      //  { id: 5, time: '15:00 - 16:00', availableSeats: 3 },
      //  { id: 6, time: '16:00 - 17:00', availableSeats: 1 },
      //  { id: 7, time: '17:00 - 18:00', availableSeats: 1 }
      //];

      // 예약 가능한 시간대 설정
      const timeSlots = [
        '10:00 - 11:00',
        '11:00 - 12:00',
        '13:00 - 14:00',
        '14:00 - 15:00',
        '15:00 - 16:00',
        '16:00 - 17:00',
        '17:00 - 18:00'
      ];
      axios.get('/api/reservations', { params: { date: info.dateStr } })
        .then(response => {
          const reservedTimes = response.data.map(reservation => reservation.ctime);
          this.availableTimes = timeSlots.map(time => ({
            time,
            available: !reservedTimes.includes(time)
          }));
        })
        .catch(error => {
          console.error("Error fetching available times:", error);
          this.availableTimes = timeSlots.map(time => ({ time, available: true }));
        });

      //axios.get('/api/available-times', { params: { date: info.dateStr } })
      //  .then(response => {
      //    this.availableTimes = response.data.times;
      //  })
      //  .catch(error => {
      //    console.error("Error fetching available times:", error);
      //    this.availableTimes = [];  // 오류 시 배열 초기화
      //  });


      // 선택한 시간을 FullCalendar 이벤트에 추가하기
      // const events = this.availableTimes.map(time => {
      //   return {
      //     title: `Available Seats: ${time.availableSeats}`,
      //    start: `${info.dateStr}T${time.time.split(' - ')[0]}:00`,
      //     end: `${info.dateStr}T${time.time.split(' - ')[1]}:00`
      //   };
      // });

      // FullCalendar의 이벤트 업데이트
      // this.calendarOptions.events = events;

      // 선택한 날짜 강조 스타일 추가
      //et days = document.querySelectorAll(".selectedDate");
      //days.forEach(function (day) {
      //  day.classList.remove("selectedDate");
      //});
      //info.dayEl.classList.add("selectedDate");
      //},

      let days = document.querySelectorAll(".selectedDate");
      days.forEach(day => day.classList.remove("selectedDate"));
      info.dayEl.classList.add("selectedDate");
    },
    selectCounselingType(type) {
      this.selectedCounselingType = type;
    },

    submitReservation() {
      if (!this.selectedTime || !this.selectedCounselingType) {
        alert("상담 유형과 시간을 선택해 주세요.");
        return;
      }

      //240517 선택한 상담 유형에 따라 신청form.vue 다른 곳으로 보내기
      let formRoute;
      switch (this.selectedCounselingType) {
        case '지도교수 상담':
          formRoute = 'applyForm1';
          break;
        case '취업상담':
          formRoute = 'applyForm2';
          break;
        case '전문 상담':
          formRoute = 'applyForm3';
          break;
        case '심리 상담':
          formRoute = 'applyForm4';
          break;
        default:
          formRoute = 'applyForm5';
      }
      // 폼 제출 로직: 서버에 예약 데이터 전송
      console.log(`Reservation submitted for ${this.selectedDate} at ${this.selectedTime.time}`);

      // 서버로 보내기
      axios.post('/api/reservations', {
        selectedDate: this.selectedDate,
        selectedTime: this.selectedTime.time,
        selectedCounselingType: this.selectedCounselingType //240517 추가
        // 기타 예약 정보
      }).then(() => {
        // 예약 성공 시 FullCalendar에 이벤트 추가
        const event = {
          title: 'Reservation',
          start: `${this.selectedDate}T${this.selectedTime.time.split(' - ')[0]}:00`,
          end: `${this.selectedDate}T${this.selectedTime.time.split(' - ')[1]}:00`
        };
        this.calendarOptions.events.push(event);

        // 예약 완료 후 페이지 이동
        this.$router.push({
          name: formRoute,
          //params: {
          //props: {

          query: { // params, prop 대신 query 사용 - url로 값 띄우고 전달
            selectedDate: this.selectedDate,
            //selectedTime: this.selectedTime ? this.selectedTime.time : ''
            selectedTime: this.selectedTime.time // 여기에서 'time' 프로퍼티에 접근하여 문자열 형태로 전달

          }
        });
      }).catch(error => {
        console.error("Error submitting reservation:", error);
        alert("예약 중 오류가 발생했습니다.");
      });
    },
    handleDayRender({ date, el }) {
      console.log(el);  // 개발자 도구 콘솔에서 확인 가능
      const today = new Date();
      today.setHours(0, 0, 0, 0); // 오늘 날짜의 자정으로 설정
      if (date < today) {
        //el.classList.add('past-date'); // 과거 날짜에 클래스 추가
        el.style.backgroundColor = '#ccc';
        el.style.color = '#666';
        el.style.pointerEvents = 'none';
        el.style.opacity = '0.6';
      }
    },
    handleEventClick(info) {
      // 이벤트 클릭 시 실행될 로직
      this.currentEvent = {
        title: info.event.title,
        start: info.event.start.toISOString()
      };
      this.showModal = true;  // 모달창 표시
    },

    close() {
      this.$emit('update:modelValue', false);
    }

  }
}
</script>
