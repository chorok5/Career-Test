<template>
  <div>
    <MySidebar></MySidebar>
    <MenuPage/>
    <div class="container-fluid mt-5 pt-4">
  <div class="row">
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div class="main-content">
        <div class="section">
          <h1 class="mb-4">마이페이지입니다</h1>
          <div>
            <div class="mt-4">
              <p>현재 로그인한 사용자 id: <strong>{{ $store.state.account.id }}</strong></p>
              <p>현재 로그인한 사용자 email: <strong>{{ $store.state.account.email }}</strong></p>
              <p>현재 로그인한 사용자 name: <strong>{{ $store.state.account.name }}</strong></p>
            </div>
          </div>
        </div>
        <hr>
        <div class="section">
          <div>
            <h2>비밀번호 수정</h2>
            <p class="note">변경된 개인 정보를 정확히 입력해주시기 바랍니다</p>
            <p class="note">이름 및 휴대폰 번호가 변경된 경우는 상담센터에 문의해주세요</p>
            <form class="info-form">
              <div class="form-group">
                <label for="name">이름*</label>
                <span class="text-value">{{ $store.state.account.name }}</span>
              </div>
              <hr>
              <div class="form-group">
                <label for="id">아이디(학번)*</label>
                <span class="text-value">{{ $store.state.account.id }}</span>
              </div>
              <hr>
              <div class="form-group">
                <label for="phone">휴대폰 번호*</label>
                <span class="text-value">{{ $store.state.account.email }}</span>
              </div>
              <hr>
              <div class="form-group">
                <label for="password">새 비밀번호*</label>
                <input type="password" @keyup.enter="changePassword" v-model="form.newPassword" placeholder="새 비밀번호" />
              </div>
              <hr>
              <div class="form-group">
                <label for="confirm-password">비밀번호 확인*</label>
                <input type="password" @keyup.enter="changePassword" v-model="form.newPasswordCheck" placeholder="새 비밀번호 확인" />
              </div>
              <hr>
              <button type="button" class="btn btn-change" @click="changePassword">변경</button>
            </form>
          </div>
        </div>
      </div>
    </main>
  </div>
</div>
</div>

</template>


<script>
import axios from "axios";
import router from '@/router';
import MySidebar from '@/components/MySidebar.vue';
import MenuPage from '@/components/MenuPage.vue';

export default {
  name: 'MyInfo',
  components: {
    MySidebar,
    MenuPage,
  },
  data() {
    return {
      form: {
        newPassword: "",
        newPasswordCheck: ""
      }
    };
  },
  methods: {
    changePassword() {
      if (this.form.newPassword !== this.form.newPasswordCheck) {
        window.alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
      }
      axios.post('http://localhost:3000/api/account/changepassword', this.form, { withCredentials: true })
        .then(() => {
          window.alert("비밀번호가 변경되었습니다.");
          router.push({ path: "/login" });
        })
        .catch(() => {
          window.alert("비밀번호 변경에 실패했습니다. 현재 비밀번호를 확인해주세요.");
        });
    }
  }
};
</script>

<style scoped>
/* 스타일 추가 */
.main-content {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 8px;
  max-width: 1000px; /* 페이지 양쪽 맞춤을 위해 너비 제한 */
  margin: 0 auto; /* 가운데 정렬 */
}
.note {
  margin-bottom: 10px;
  font-size: 14px;
  color: #666;
}
.info-form {
  margin-top: 20px;
}
.form-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}
.form-group label {
  flex: 1;
  font-weight: bold;
  text-align: left; /* 글씨 왼쪽 정렬 */
}
.form-group .text-value {
  flex: 2;
  padding: 8px;
  text-align: left; /* 글씨 왼쪽 정렬 */
}
.form-group input {
  flex: 2; /* 레이블과 동일한 너비 비율 적용 */
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.btn {
  display: inline-block;
  padding: 10px 20px;
  color: #fff;
  background-color: #333;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}
.btn.btn-change {
  background-color: #555;
}
</style>
