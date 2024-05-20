<template>
    <table>
      <thead>
          <tr>
              <th>아이디</th>
              <th>이름</th>
              <th>가격</th>
              <th>{{ itmes }}</th>
              <th>담기</th>
          </tr>
      </thead>
      <tbody>
          <tr v-for="item in state.items" v-bind:key="item.id">
              <td>{{ item.id }}</td>
              <td>{{ item.name }}</td>
              <td>{{ item.price }}</td>
              <td><button @click="addToCart(item.id)">담기</button></td>
          </tr>
      </tbody>
    </table>
</template>
  
<script>
import axios from 'axios'
import { reactive, onMounted } from 'vue';
  
export default {
    name: 'listTest',
    setup(){
    const state = reactive({
    items: []
    });

    onMounted(() => {
        axios.get('http://localhost:3000/api/items').then(response => {
            state.items = response.data;
        }).catch((err) => {
              alert("문제 발생"+err)
        });
      });

    const addToCart = (itemId) => {
        axios.post('http://localhost:3000/api/cart/items',{itemId}, { withCredentials: true })
        .then(() => {
          console.log('success');
        })
    };

    return{state, addToCart}
  }
}
  </script>