<template>
  <div>
    <br>
    <div class="sbmContainer boundary">
        <table class="subMenu">
            <tr>
                <th :style="select[0]" @click="displayAct(0)">버튼1</th>
                <th :style="select[1]" @click="displayAct(1)">버튼2</th>
                <th :style="select[2]" @click="displayAct(2)">버튼3</th>
                <th :style="select[3]" @click="displayAct(3)">버튼4</th>
            </tr>
        </table>
    </div>
    <section class="section s1" :style="cssAct[0]">
        <h1>테스트1</h1>
    </section>
    <section class="section s2" :style="cssAct[1]">
        테스트2
    </section>
    <section class="section s3" :style="cssAct[2]">
        테스트3
    </section>
    <section class="section s4" :style="cssAct[3]">
        테스트4
    </section>
  </div>
</template>

<script>
export default {
    data(){
        return{
            act : "none",
            cssAct: [
                {display : "none"},
                {display : "none"},
                {display : "none"},
                {display : "none"}
            ],
            select: [
                {},
                {},
                {},
                {}
            ],
        }
    },
    mounted(){
        this.cssAct[0] = {display:"block"};
        this.select[0] = {border:"2px solid rgb(43, 43, 165", 'border-bottom':"none"};
    },
    methods:{
        displayAct(num){
            this.cssAct = this.cssAct.map((style, i) => {
            if(i == num){
                return {display:"block"};                
            }
            return {display:"none"};
        })
            this.select = this.select.map((style, i)=> {
                if(i==num){
                    return {border:"2px solid rgb(43, 43, 165", 'border-bottom':"none"};
                }
            })
        }
    },

}
</script>

<style>
.sbmContainer{
    width: 100%;
    margin-bottom: 50px;
}
.subMenu{    
    width: 100%;
    margin: auto;
}
.subMenu tr th{
    width: 25%;
    height: 55px;
    border-bottom: 2px solid rgb(43, 43, 165);
}
.boundary{
    width: 100%;
    max-width: 1200px;
    padding: 0 auto;
}

</style>