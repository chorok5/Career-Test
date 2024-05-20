<template>
  <div>
    제목:<input v-model="upDate.btitle"><br>
    글쓴이:{{ upDate.writer }}<br>
    내용:<textarea v-model="upDate.bcontent"></textarea><br>
    <button @click="update()">수정하기</button>
    <button @click="recomment()">덧글쓰기</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name:"BoardUpdate",
  data(){
    return{
      requestBody:this.$route.query,
      upDate:{
        bno:this.$route.query.bno,
        title:null,
        content:null,
        writer:null,
      },
    }
  },mounted(){
    this.boardDetail()
  },
  methods:{
    boardDetail(){
      axios.get("/api/boardDetail?bno="+this.requestBody.bno).then((res)=>{
        this.upDate = res.data.list
      }).catch((err)=>{
        alert("에러 발생 : "+err)
      })
    },
    update(){
      if(confirm("수정하시겠습니까?")){
        axios.post("/api/boardUpdate",this.upDate).then((res)=>{
        if(res.data.result	==	1){
          this.$router.push({path:"./boardDetail",query:this.requestBody})
        }else{
          alert("에러")
        }
      }).catch((err)=>{
        alert(err)
      })
      }
    }
  }
}
</script>

<style>

</style>