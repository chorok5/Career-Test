<template>
  <div class="container" style="padding:15px">

    <div>
      <div class="input-group mb-3">
        <div class="col-2 firstGroup">
          상담 제목 : 
        </div>
        <div class="col">
          <input type="text" aria-label="First name" class="form-control" v-model="group.request.g_title">  
        </div>
      </div>

      <div class="input-group">
        <div class="col-2 firstGroup">
          상담사 이름 :  
        </div>
        <div class="col">
          <input type="text" aria-label="First name" class="form-control" disabled v-model="coun.coun_name">
        </div>
      </div>
    </div>

    <hr>

    <div>
      <div class="input-group mb-3">
        <div class="col-2 firstGroup">
          내용
        </div>
        <div class="col">
          <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" v-model="group.request.g_content"></textarea>
        </div>
      </div>

      <div class="input-group mb-3">
        <div class="col-2 firstGroup">파일 선택</div>
        <div class="col">
          <input class="form-control" type="file" id="formFile" @change="imageUpload()" ref="image" accept="image/*">
        </div>
      </div>
    </div>

    <hr>

    <div>  
      <div class="input-group mb-3">
            <div class="col-2">
            신청 기간
            </div>
            <div class="col">
              <DatePicker
                range
                locale="ko"
                v-model="date1"
                :format="dateRangeFormat1"
                :enable-time-picker="false"
                @click="click1()"
              />
            </div>
        
      </div>
      <div class="input-group mb-3">
            <div class="col-2">
            운영 기간
            </div>
            <div class="col">
              <DatePicker
                range
                locale="ko"
                v-bind:disabled="date1 == null"
                v-model="date2"
                :format="dateRangeFormat2"
                :enable-time-picker="false"
                :min-date="mindate"
              />
            </div>
        
      </div>
      <div class="input-group mb-3">
            <div class="col-2">
            시간
            </div>
            <div class="col">
              <button class="btn me-3" :class="{ 'btn-outline-primary': !clicked[index], 'btn-primary': clicked[index] }" v-for="(i,index) in btn" 
              :key="index" @click="clickbtn(i,index)">
                {{ i }}
              </button>
            </div>
        
      </div>
      
      <div class="input-group mb-3">
            <div class="col-2">
            요일
            </div>
            <div class="col">
              <select class="form-select" v-model="group.request.g_day">
                <option value="0">월</option>
                <option value="1">화</option>
                <option value="2">수</option>
                <option value="3">목</option>
                <option value="4">금</option>
              </select>
            </div>
        
      </div>

      <div class="input-group">
        <div class="col-2 firstGroup">
          총 인원 수: 
        </div>
        <div class="col">
          <select class="form-select" v-model="group.request.g_total">
            <option>선택</option>
            <option v-for="n in 10" v-bind:key="n">{{ n }}</option>
          </select>
        </div>
      </div>
    </div>

    <hr>
    <div class="mb-3">
      <div  class="input-group mb-3">
        <div class="col-2 firstGroup">성별 : </div>
        <div class="col">
          <select class="form-select" v-model="group.request.g_sex">
            <option value="전체">전체</option>
            <option value="남자">남자</option>
            <option value="여자">여자</option>
          </select>
        </div>
      </div>
      <div class="input-group">
        <div class="col-2 firstGroup">전공 :</div>
        <div class="col">
          <select class="form-select" v-model="group.request.g_major">
            <option value="전체">전체</option>
            <option value="컴퓨터학과">컴퓨터학과</option>
            <option value="심리학과">심리학과</option>
            <option value="국어국문과">국어국문과</option>
          </select>
        </div> 
      </div>
    </div>
    <div>
      <button class="btn btn-primary" @click="createGroup()">등록</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { onMounted, ref } from "vue";
import { useRoute } from 'vue-router';

export default {

  name:"groupCreate",
  setup(){
    const route = useRoute()

    const date1 = ref();
    const date2 = ref();
    const image = ref();
    const mindate = ref();
    const clicked = ref([]);
    const coun = ref({coun_name:null});
    const btn = ref(["09:00","10:00","11:00","13:00","14:00","15:00","16:00","17:00"])
    const group = ref({
        input:{
          g_img:null,
        },
        request:{
          g_title:null,
          g_coun_name:null,
          g_coun_id:route.query.id,
          g_content:null,
          g_apply:null,
          g_oper:null,
          g_time:null,
          g_day:"0",
          g_total:"선택",
          g_sex:"전체",
          g_major:"전체"
        }
      })


    onMounted(()=>{
      axios.get("/api/getcoun?id="+route.query.id).then((res)=>{
        coun.value.coun_name = res.data.map.coun_name
      }).catch((err)=>{
        alert(err);
      })
    })

    const click1= () =>{
      
      const startDate1 = new Date();
      const endDate1 = new Date(new Date().setDate(startDate1.getDate() + 7));
      date1.value = [startDate1, endDate1];

      
      const startDate2 = new Date();
      const endDate2 = new Date(new Date().setDate(startDate2.getDate() + 7));
      date2.value = [startDate2, endDate2];
    }
      const createGroup = async () => {
        group.value.request.g_coun_name =  coun.value.coun_name

        const formData = new FormData();
        formData.append("image", group.value.input.g_img);
        formData.append("request", JSON.stringify(group.value.request));

        console.log(formData)
        try {
          await axios.post("/api/createGroup", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });
          alert("Group created successfully!");
        } catch (error) {
          alert("Error creating group.");
          alert(error);
        }
      };

    const dateRangeFormat1 = (value) => {
      const startDate1 = value[0];
      const endDate1 = value[1];

      if (startDate1 && endDate1) {
        const startYear1 = startDate1.getFullYear();
        const startMonth1 = (startDate1.getMonth() + 1).toString().padStart(2, "0");
        const startDay1 = startDate1.getDate().toString().padStart(2, "0");

        const endYear1 = endDate1.getFullYear();
        const endMonth1 = (endDate1.getMonth() + 1).toString().padStart(2, "0");
        const endDay1 = endDate1.getDate().toString().padStart(2, "0");
        
        mindate.value = `${endYear1}-${endMonth1}-${endDay1}`

        group.value.request.g_apply =`${startYear1}-${startMonth1}-${startDay1} ~ ${endYear1}-${endMonth1}-${endDay1}`
        
        return `${startYear1}-${startMonth1}-${startDay1} ~ ${endYear1}-${endMonth1}-${endDay1}`;
      }
    };

    const dateRangeFormat2 = (value) =>{
      const startDate2 = value[0];
      const endDate2 = value[1];

      if (startDate2 && endDate2) {
        const startYear2 = startDate2.getFullYear();
        const startMonth2 = (startDate2.getMonth() + 1).toString().padStart(2, "0");
        const startDay2 = startDate2.getDate().toString().padStart(2, "0");

        const endYear2 = endDate2.getFullYear();
        const endMonth2 = (endDate2.getMonth() + 1).toString().padStart(2, "0");
        const endDay2 = endDate2.getDate().toString().padStart(2, "0");

        
        group.value.request.g_oper =`${startYear2}-${startMonth2}-${startDay2} ~ ${endYear2}-${endMonth2}-${endDay2}`

        return `${startYear2}-${startMonth2}-${startDay2} ~ ${endYear2}-${endMonth2}-${endDay2}`;
      }
    }
    const imageUpload = () => {
      group.value.input.g_img = image.value.files[0]
    }

    const clickbtn= (time,index) =>{
      for(let i=0; i<clicked.value.length;i++){
        clicked.value[i]=false
      }
      clicked.value[index] = !clicked.value[index]
      group.value.request.g_time = time
      console.log(group.value.request.g_time)
    }

    return{
      dateRangeFormat1,date1,dateRangeFormat2,date2,click1,mindate,group,coun,createGroup,imageUpload,image,btn,clickbtn,clicked
    }
  }
}
</script>

<style scope>
  .firstGroup {
    padding-left: 20px;
    text-align: left;
  }
</style>