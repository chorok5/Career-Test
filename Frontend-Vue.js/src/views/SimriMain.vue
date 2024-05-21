<template>
  <div>
    <MenuPage />
    <SimriSecMenu />


  </div>
</template>

<script>
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
    components: {
    MenuPage,
    SimriSecMenu
  },
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