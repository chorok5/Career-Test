<template>
  <div>
    <HeaderMenu></HeaderMenu>
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <MySidebar></MySidebar>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <h1> 아래는 예시입니다. 수정 필수! </h1><br><hr>
          <h1 class="mb-4">내 정보 관리</h1>
          <div class="row">
            <div class="col-12 mb-4">
              <h2>개인 정보</h2>
              <form>
                <div class="mb-3">
                  <label for="name" class="form-label">이름</label>
                  <input type="text" class="form-control" id="name" v-model="user.name">
                </div>
                <div class="mb-3">
                  <label for="email" class="form-label">이메일 주소</label>
                  <input type="email" class="form-control" id="email" v-model="user.email">
                </div>
                <div class="mb-3">
                  <label for="phone" class="form-label">연락처</label>
                  <input type="text" class="form-control" id="phone" v-model="user.phone">
                </div>
                <div class="mb-3">
                  <label for="address" class="form-label">주소</label>
                  <input type="text" class="form-control" id="address" v-model="user.address">
                </div>
                <button type="submit" class="btn btn-primary">수정</button>
              </form>
            </div>
            <div class="col-12"><br>
              <h2>비밀번호 변경</h2>
              <form>
                <div class="mb-3">
                  <label for="current-password" class="form-label">현재 비밀번호</label>
                  <input type="password" class="form-control" id="current-password" v-model="password.current">
                </div>
                <div class="mb-3">
                  <label for="new-password" class="form-label">새 비밀번호</label>
                  <input type="password" class="form-control" id="new-password" v-model="password.new">
                </div>
                <div class="mb-3">
                  <label for="confirm-password" class="form-label">비밀번호 확인</label>
                  <input type="password" class="form-control" id="confirm-password" v-model="password.confirm">
                </div>
                <button type="submit" class="btn btn-primary">비밀번호 변경</button>
              </form>
            </div>
          </div>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderMenu from '@/components/HeaderMenu.vue';
import MySidebar from '@/components/MySidebar.vue';

export default {
  name: 'MyInfo',
  components: {
    HeaderMenu,
    MySidebar,
  },
  data() {
    return {
      user: {
        name: '',
        email: '',
        phone: '',
        address: ''
      },
      password: {
        current: '',
        new: '',
        confirm: ''
      }
    };
  }
};
</script>

<style scoped>
.container-fluid {
  padding-top: 56px; /* Adjust based on the height of HeaderMenu */
}
.sidebar {
  position: -webkit-sticky;
  position: sticky;
  top: 56px; /* Adjust based on the height of HeaderMenu */
  height: calc(100vh - 56px); /* Adjust based on the height of HeaderMenu */
  padding-top: 1rem;
  overflow-x: hidden;
  overflow-y: auto; /* Enable scrolling if the content exceeds the viewport height */
}
.sidebar .nav-link {
  font-weight: 500;
  color: #333;
}
.sidebar .nav-link:hover {
  color: #007bff;
}
</style>
