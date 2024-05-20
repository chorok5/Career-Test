<template>
  <div>
    제목 : <input v-model="reply.btitle"><br>
    글쓴이 : {{ reply.writer }}<br>
    내용 : <textarea v-model="reply.bcontent"></textarea><br>
    <button @click="boardReply()">답글 달기</button>
  </div>
</template>

<script>
import axios from 'axios';


export default {
  name:"BoardReply",
  data(){
    return{
      requestBody:this.$route.query,
      reply:{
        bno:this.$route.query.bno,
        btitle:null,
        bcontent:null,
        writer:"상담자",
        up_bno:null,
        depth:null,
        ordernum:null
      }
    }
  },mounted(){
    axios.get("/api/getPath?bno="+this.requestBody.bno).then((res)=>{
      if(res!=null){
        this.reply.up_bno = res.data.list.up_bno
        this.reply.depth = res.data.list.depth
        this.reply.ordernum = res.data.list.ordernum
      }else{
        alert("에러입니다.")
      }
    }).catch((err)=>{
      alert(err);
    })
  },
  methods:{
    boardReply(){
      axios.post("/api/boardReply",this.reply).then((res)=>{
        if(res.data.result==1){
          this.$router.push({path:"/boardList"})
        }
      }).catch((err)=>{
        alert(err);
      })
    }
  }
}
</script>

<style>

</style>