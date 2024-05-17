<template>
  <div>
    <h1>마이페이지입니다</h1>
  </div>
  <table>
    <thead>
        <tr>
          <th>내정보 id|--</th>
          <th>내정보 email|--</th>
          <th>내정보 비밀번호|--</th>
        </tr>
    </thead>
    <tbody>
      <tr v-for="myinfo in state.myinfos" v-bind:key="myinfo.id">
            <td>{{ myinfo.id }}</td>
            <td>{{ myinfo.email }}</td>
            <td>{{ myinfo.password }}</td>
        </tr>
    </tbody>
  </table>
</template>

<script>
import axios from 'axios'
import { reactive, onMounted } from 'vue';

export default {
  setup(){
  const state = reactive({
  myinfos:[]
  });

  onMounted(() => {
        axios.get('http://localhost:3000/api/myinfo', { withCredentials: true })
        .then(response => {
            state.myinfos = response.data;
        }).catch((err) => {
              alert("문제 발생"+err)
        });
    });

  return{state}
  }
}
</script>
