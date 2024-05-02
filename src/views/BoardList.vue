<template>
  <div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>글쓴이</th>
          <th>날짜</th>
          <th>ip</th>
        </tr>
      </thead>

    </table>
    <button @click="$router.push('/write')">글쓰기</button>
  </div>
</template>


<script>
export default {
  name: 'BoardList',

}
</script>
<style></style>