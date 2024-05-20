<template>
  <div>
    카트 페이지
    <table>
      <thead>
        <tr>
          <th>아이템 id</th>
          <th>아이템 이름</th>
          <th>아이템 가격</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in state.items" v-bind:key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.price }}</td>
            <td><button @click="remove(item.id)">삭제</button></td>
        </tr>
    </tbody>
    </table>
  </div>

   <!-- <div class="intro" >
    <div class="container">
      <h2>취업상담</h2>
    </div>
  </div>
 <div>
      <p>취업상담에 오신걸 환영합니다</p>
      <p>취업상담 하위 메뉴 : 1. 상담안내</p>
      <p>취업상담 하위 메뉴 : 2. 직업심리검사</p>
      <p>취업상담 하위 메뉴 : 3. 상담사 소개</p>
      <p>취업상담 하위 메뉴 : 4. 상담신청</p>
      <p>취업상담 하위 메뉴 : 5. 채용정보</p>
    </div> -->
</template>

<script>
import { reactive } from 'vue';
import axios from 'axios';

export default {
name: 'jobConsulting',
setup(){
  const state = reactive({
    items:[]
  })

  const load = () => {
  axios.get('http://localhost:3000/api/cart/items', { withCredentials: true })
  .then(response => {
    state.items = response.data;
    })
  };

  const remove = (itemId) => {
      axios.delete(`http://localhost:3000/api/cart/items/${itemId}`, { withCredentials: true })
      .then(() => {
        console.log('remove success');
        load(); // 데이터 삭제 후 다시 불러오기
      })
  };

  // 컴포넌트가 마운트될 때 초기 데이터를 불러옴
  load();

  return {state, remove}
}
}
</script>

<style scoped>
.intro {
  background-color: #f2f2f2;
}
</style>