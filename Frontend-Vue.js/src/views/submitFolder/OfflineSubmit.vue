<template>
    <div class="container mt-5">
        <h2>오프라인 검사 신청</h2>
        <form @submit.prevent="submitForm">
            <!-- 이름 -->
            <div class="mb-3 row">
                <label for="name" class="form-label col-sm-2">이름</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" v-model="formData.name" required>
                </div>
            </div>
            <!-- 연락처 (전화번호) -->
            <div class="mb-3 row">
                <label for="phone" class="form-label col-sm-2">전화번호</label>
                <div class="col-sm-10">
                    <input type="tel" class="form-control" id="phone" v-model="formData.phone" required>
                </div>
            </div>
            <!-- 연락처 (이메일) -->
            <div class="mb-3 row">
                <label for="email" class="form-label col-sm-2">이메일</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" v-model="formData.email" required>
                </div>
            </div>
            <!-- 연령 -->
            <div class="mb-3 row">
                <label for="age" class="form-label col-sm-2">연령</label>
                <div class="col-sm-10">
                    <select class="form-select" id="age" v-model="formData.age" required>
                        <option value="" disabled>연령 선택</option>
                        <option v-for="age in ageOptions" :key="age" :value="age">{{ age }}세</option>
                    </select>
                </div>
            </div>
            <!-- 성별 -->
            <div class="mb-3 row">
                <label class="form-label col-sm-2">성별</label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" id="male" value="male" v-model="formData.gender">
                        <label class="form-check-label" for="male">남성</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" id="female" value="female"
                            v-model="formData.gender">
                        <label class="form-check-label" for="female">여성</label>
                    </div>
                </div>
            </div>
            <!-- 예약 일정 및 시간 -->
            <div class="mb-3 row">
                <label class="form-label col-sm-2">예약 일정 및 시간</label>
                <div class="col-sm-10 d-flex align-items-center">
                    <vue3-datepicker v-model="selectedDate" :disabledDates="{ predicate: isTodayOver }"
                        format="yyyy-MM-dd" placeholder="날짜 선택" class="me-2"></vue3-datepicker>
                    <div class="time-selection d-flex">
                        <button v-for="time in availableTimes" :key="time" :value="time" @click="selectedTime = time"
                            :class="['time-button', { 'selected': time === selectedTime }]">
                            {{ time }}
                        </button>
                    </div>
                </div>
            </div>
            <!-- 상담 목적 및 내용 -->
            <div class="mb-3 row">
                <label class="form-label col-sm-2">상담 목적 및 내용</label>
                <div class="col-sm-10 d-flex flex-row flex-wrap">
                    <div class="form-check" style="margin-right: 10px;">
                        <input class="form-check-input" type="radio" id="personality_test" value="인성검사"
                            v-model="formData.purpose">
                        <label class="form-check-label" for="personality_test">인성검사</label>
                    </div>
                    <div class="form-check" style="margin-right: 10px;">
                        <input class="form-check-input" type="radio" id="personality_type_test" value="성격검사"
                            v-model="formData.purpose">
                        <label class="form-check-label" for="personality_type_test">성격검사</label>
                    </div>
                    <div class="form-check" style="margin-right: 10px;">
                        <input class="form-check-input" type="radio" id="mbti_test" value="MBTI 검사"
                            v-model="formData.purpose">
                        <label class="form-check-label" for="mbti_test">MBTI 검사</label>
                    </div>
                    <div class="form-check" style="margin-right: 10px;">
                        <input class="form-check-input" type="radio" id="mmpi_test" value="다면적 인성검사(MMPI)"
                            v-model="formData.purpose">
                        <label class="form-check-label" for="mmpi_test">다면적 인성검사(MMPI)</label>
                    </div>
                    <div class="form-check" style="margin-right: 10px;">
                        <input class="form-check-input" type="radio" id="strong_test" value="직업흥미검사(STRONG)"
                            v-model="formData.purpose">
                        <label class="form-check-label" for="strong_test">직업흥미검사(STRONG)</label>
                    </div>
                    <div class="form-check" style="margin-right: 10px;">
                        <input class="form-check-input" type="radio" id="mlst_test" value="학습전략검사(MLST-Ⅱ)"
                            v-model="formData.purpose">
                        <label class="form-check-label" for="mlst_test">학습전략검사(MLST-Ⅱ)</label>
                    </div>
                </div>
            </div>

            <!-- 추가 정보 -->
            <div class="mb-3 row">
                <label for="additional" class="form-label col-sm-2">기타 요구 사항 또는 특별한 요청</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="additional" v-model="formData.additional"></textarea>
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-10 offset-sm-2">
                    <button type="submit" class="btn btn-primary">신청하기</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import { ref } from 'vue';
import DatePicker from 'vue3-datepicker';

export default {
    components: {
        Vue3Datepicker: DatePicker
    },
    setup() {
        const selectedDate = ref(null);
        const availableTimes = ['10:00', '12:00', '14:00', '16:00'];
        const selectedTime = ref(null);
        const ageOptions = [];
        for (let i = 19; i <= 60; i++) {
            ageOptions.push(i);
        }
        const isTodayOver = (date) => {
            return date < new Date();
        };
        return {
            selectedDate,
            availableTimes,
            selectedTime,
            ageOptions,
            isTodayOver
        };
    },
    data() {
        return {
            formData: {
                name: '',
                phone: '',
                email: '',
                age: '',
                gender: '',
                purpose: '',
                additional: ''
            }
        };
    },
    methods: {
        selectTime(time) {
            this.selectedTime = time;
        },
        submitForm() {
            // 폼 데이터를 처리하는 메소드 추가해야함.
            console.log(this.formData);
        }
    }
};
</script>

<style>
.time-button {
    padding: 5px 10px;
    margin-right: 5px;
    border: 1px solid #ccc;
    border-radius: 15px;
    /* 둥근 모서리를 위한 CSS */
    background-color: #f8f9fa;
    cursor: pointer;
    transition: all 0.3s ease;
}

.time-button.selected,
.time-button:hover {
    background-color: #007bff;
    color: white;
    border-color: #007bff;
}
</style>