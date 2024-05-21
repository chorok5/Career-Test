<template>
  <div>
    <MenuPage />
    <SimriSecMenu />
    <div class="container py-5">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <h1 class="text-center mb-5">마음의 짐, 함께 내려놓아요</h1>

          <div class="row">
            <div class="col-md-6 mb-4">
              <div class="card h-100 shadow-sm">
                <div class="card-body d-flex flex-column align-items-center">
                  <i class="fas fa-brain fa-3x text-primary mb-3"></i>
                  <h5 class="card-title mb-3">심리 상담이란?</h5>
                  <p class="card-text text-muted">
                    전문 상담사와의 대화를 통해 심리적인 어려움을 극복하고, 건강한 삶을 향해 나아가도록 돕는 과정입니다.
                  </p>
                </div>
              </div>
            </div>
          </div>

          <div class="row mt-4">
            <div class="col-md-6 mb-4">
              <div class="card h-100 shadow-sm">
                <div class="card-body d-flex flex-column align-items-center">
                  <i class="fas fa-comments fa-3x text-primary mb-3"></i>
                  <h5 class="card-title mb-3">누구에게 필요할까요?</h5>
                  <ul class="list-unstyled text-muted">
                    <li>• 학업 스트레스, 진로 고민</li>
                    <li>• 대인관계 어려움</li>
                    <li>• 불안, 우울, 무기력</li>
                    <li>• 가족 문제, 연애 문제</li>
                    <li>• 자존감 저하, 슬픔, 분노 조절 어려움</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>

          <div class="row mt-5">
            <div class="col-md-12">
              <div class="card shadow-sm">
                <div class="card-body">
                  <h3 class="mb-4">상담 과정</h3>
                  <div class="row">
                    <div class="col-md-4 mb-4 text-center">
                      <i class="fas fa-calendar-alt fa-3x text-primary mb-3"></i>
                      <h4>1. 예약</h4>
                      <p class="text-muted">편한 시간에 온라인 또는 전화로 예약하세요.</p>
                    </div>
                    <div class="col-md-4 mb-4 text-center">
                      <i class="fas fa-user-friends fa-3x text-primary mb-3"></i>
                      <h4>2. 상담 진행</h4>
                      <p class="text-muted">편안한 분위기에서 상담사와 이야기를 나눕니다.</p>
                    </div>
                    <div class="col-md-4 mb-4 text-center">
                      <i class="fas fa-heart fa-3x text-primary mb-3"></i>
                      <h4>3. 마음 돌보기</h4>
                      <p class="text-muted">상담을 통해 얻은 깨달음을 바탕으로 스스로를 돌봅니다.</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="text-center mt-5">
            <router-link to="/reservation" class="btn btn-primary btn-lg px-5">
              <i class="fas fa-hand-holding-heart mr-2"></i> 지금 상담 신청하기
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
  name: 'CounselingInfo',
    components: {
    MenuPage,
    SimriSecMenu
  }
  
};
</script>