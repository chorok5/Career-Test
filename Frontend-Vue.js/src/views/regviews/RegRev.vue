<template>
    <div class="container">
        <h1>정기상담 예약 조회</h1>
        <table class="table regRev">
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">학번</th>
                    <th scope="col">상태</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="n in list" v-bind:key="n.reg_no" v-bind:class="[n.reg_no+'tr']" @click="modalOpen(n.reg_no,n.stuNum)">
                    <td scope="row">{{n.reg_no }}</td>
                    <td>{{ n.stuNum }}</td>
                    <td v-if=" n.stat === '0'" v-bind:class="[n.reg_no+'stat']" >
                        <button @click="al(n, 1)" >승인</button>
                        <button>거절</button>
                    </td>
                    <td v-if=" n.stat === '1' " v-bind:class="[n.no+'stat']">승인완료</td>
                    <td>
                        <button @click="modalOpen">자세히보기</button>
                    </td>
                </tr>
            </tbody>
        </table>
    
        <!-- 모달 -->
        <div class="modal-wrap" v-show="modalCheck">
            <div class="modal-container">
                <h5>{{regno}} / {{stuNum}} // {{ check }}</h5>
                <button @click="btnPopup(regno, stuNum)">신청하기</button>
                <table class="table stu" v-if="responseData && responseData.length > 0">
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">학번</th>
                            <th scope="col">날짜</th>
                            <th scope="col">시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="row in responseData" :key="row.regno">
                            <td scope="row">{{ row.rownum }}</td>
                            <td>{{ row.stuNum }}</td>
                            <td>{{ row.DATE }}</td>
                            <td>{{ row.TIME }}</td>
                        </tr>
                    </tbody>
                </table>
                <div class="modal-btn">
                    <button @click="modalOpen">닫기</button>
                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center">
          <ul class="pagination" style="">
    
            <li class="page-item" v-if="pageMap.currentpage != 1">
              <span class="page-link" aria-label="Previous" @click="changegroup(1)">&laquo;</span>
            </li>
            <li  class="page-item" v-else>
              <span>
                <span class="page-link disabled" aria-label="Previous">&nbsp;</span>
              </span>
            </li>
    
            <li class="page-item" v-if="pageMap.currentpage != 1">
              <span class="page-link" aria-label="Previous" @click="changegroup(pageMap.currentpage - 1)">&lt;</span>
            </li>
            <li v-else>
              <span>
                <span class="page-link disabled" aria-label="Previous">&nbsp;</span>
              </span>
            </li>
    
            <li class="page-item" v-for="n in pageMap.pagelist" :key="n">
              <span class="page-link active" @click="changegroup(n)" v-if="pageMap.currentpage==n">{{ n }}</span>
              <span class="page-link" @click="changegroup(n)" v-else>{{ n }}</span>
            </li>
    
            <li class="page-item" v-if="pageMap.currentpage != pageMap.totalpage">
              <span class="page-link" @click="changegroup(pageMap.currentpage + 1)" aria-label="Next">
                <span aria-hidden="true">&gt;</span>
              </span>
            </li>
            <li v-else>
              <span>
                <span class="page-link disabled" aria-label="Previous">&nbsp;</span>
              </span>
            </li>
    
    
            <li class="page-item" v-if="pageMap.currentpage != pageMap.totalpage">
              <span class="page-link" @click="changegroup(pageMap.totalpage)" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </span>
            </li>
            <li v-else>
              <span>
                <span class="page-link disabled" aria-label="Previous">&nbsp;</span>
              </span>
            </li>
    
          </ul>
        </div>
    </div>
    </template>
    
    <script>
    import axios from 'axios'
    export default {
        data(){
            return{
                list:[],
                modalCheck: false,
                regno:'',
                stuNum:'',
                totalCount:'',
                pageMap: [],
                check:'',
    
            }
        },
        mounted(){
            if(this.$store.state.account.id == 0){
                alert("로그인 됨");
            }
            axios.get('http://localhost:3000/regTotalCount').then((res) => {  
                this.totalCount = res.data;
                console.log(this.totalCount);
                
                axios.get('http://localhost:3000/test?total='+this.totalCount).then((res) => {
                this.list = res.data.list;
                this.pageMap = res.data.pageMap;
                })
            })
        },
        methods:{
            al(item,stat){
                var arrJson = {'reg_no':item.reg_no, 'stat':stat};            
                axios.post('http://localhost:3000/accept',arrJson).then((res)=>{
                    console.log(res);
                    item.stat='1'
                }).catch((err) =>{
                    console.log(err);
                })
            },
            changegroup(page){
            axios.get("http://localhost:3000/test?total="+this.totalCount+"&page="+page).then((res)=>{
                this.list=res.data.list
                this.pageMap=res.data.pageMap
            }).catch((err)=>{
                alert(err)
            })
            },
            async modalOpen(reg_no, stuNum){
                console.log(this.modalCheck==false)
                if(this.modalCheck == false){
                    await this.getData(reg_no)
                }
                this.modalCheck = !this.modalCheck;
                this.regno = reg_no;
                this.stuNum = stuNum;
                console.log(this.regno);
            },
            btnPopup(regno,stuNum){
                window.open("#/regTime?stuNum="+stuNum+"&regno="+regno,"_blank","width=950,height=500");
            },
            async getData(reg_no){
                try{
                    const response = await axios.get('http://localhost:3000/regDetail?rgno='+reg_no);
                    this.responseData = response.data;
                    console.log(this.responseData);
                } catch(error) {
                    console.log('에러 발생: '+error )
                }
            }
        },
    }
    </script>
    
    <style>
    .modal-wrap{
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background: rgba(0,0,0,0.4);
        z-index: 99;
    }
    
    .modal-container{
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 550px;
        background: #fff;
        border-radius: 10px;
        padding: 20px;
        box-sizing: border-box;
    }
    </style>