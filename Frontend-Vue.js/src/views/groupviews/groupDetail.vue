<template>
  <div>
    <div class="container">
      <div style="display: flex;">
        <div class="groupimg" style="width:40%;">
          <div style="width:100%;height: 50%;">
            <img :src="'/images/'+list.img" style="width:100%;height: 100%;">
          </div>
          <div style="height: 50%;">
            <span>{{ list.coun_name }}</span><br>
            <span>{{ list.email }}</span><br>
            <span>{{ list.mobile }}</span><br>
            <span>{{ list.address }}</span><br>
          </div>
        </div>

        <div class="board" style="width:60%">
          <div>
            <span>{{ list.gr_title }}</span>
          </div>
            <hr>
          <div>
            <span>모집대상 {{ list.target }}</span><br>
            <span>학년/성별 {{ list.target }}/{{ list.sex }}</span><br>
            <span>학과 {{ list.major }}</span>
          </div>
          <div  style="height: 200px; overflow-y:auto">
            <div v-for="row in groupList" v-bind:key="row.gr_title" style="display: flex;">
              <div>
                <input type="checkbox" value="" id="flexCheckDefault" class="form-check-input" >
              </div>
              <div>
                <span>{{ row.gr_title }}</span><br>
                <span>{{ row.gr_oper }}</span><br>
                <span>0/{{ row.total_num }}</span><br>
                <span v-if="row.con_del==1">진행중</span>
                <span v-else>종료</span>
              </div>
              <hr>
            </div>
          </div>
        </div>
      </div>
      <div>
        {{ list.gr_content }}
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name:"groupDetail",
  data(){
    return{
      no:this.$route.query.no,
      list:[],
      groupList:[]
    }
  },
  mounted(){
    axios.get("/api/groupDetail?gr_no="+this.no).then((res)=>{
      this.list = res.data.list
      this.groupList = res.data.glist
    }).catch((err)=>{
      alert(err)
    })
  }
}
</script>

<style>

</style>