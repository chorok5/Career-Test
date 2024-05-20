<template>
  <div>
  <h1>Login</h1>
    <input type="text" class="form-control" id="floatingInput" placeholder="학번을 입력해주세요"
           @keyup.enter="submit()" v-model="state.form.id">
    <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호를 입력해주세요"
     @keyup.enter="submit()" v-model="state.form.password">
  <button @click="submit()">Sign in</button>
</div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/views/store";
import router from '@/router'

export default {
setup() {
  const state = reactive({
    form: {
      id: "",
      password: ""
    }
  })

const submit = () => {

    //state.form 을 보낸다
    console.log(state.form);

    axios.post('http://localhost:3000/api/account/login', state.form, { withCredentials: true }).then((res) => {
      window.alert("로그인하였습니다.");
      console.log("로그인하였습니다", JSON.stringify(res.data));

      // store 에 사용자 정보 저장
      store.commit('setAccount', res.data);
      console.log("스토어에 저장된 계정 정보:", store.state.account);

      // 세션스토리지에 아이디값 보관
      sessionStorage.setItem("id", res.data); 
      
      router.push({path: "/"});
    }).catch(() => {
      window.alert("로그인 정보가 존재하지 않습니다..");
    });
  }

  return {state, submit}
}
}
</script>