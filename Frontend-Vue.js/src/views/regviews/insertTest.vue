<template>
    <div>
        <h1>test now</h1>
        <form @submit.prevent="postTest" id="test">
            <label>학번</label><input type="text" name="stuNum" v-model="test.stuNum">
            <br>
            <button>submit</button>
        </form>
    </div>
    </template>
    
    <script>
    import axios from 'axios'
    export default {
        data() {
            return {
                test: {
                    name: '',
                    age: '',
                    major:'',
                    teacher:'',
                    stuNum:''
                }
            }
        },
        methods: {
            postTest(){
                console.log("실행");
                //var form = document.getElementById('test');           
                //var json = JSON.stringify(form)
                console.log(this.test)
                
            axios.post('http://localhost:3000/testPost', this.test).then((res) => {
                console.log(res)
                alert("신청이 완료되었습니다");
                })
            }
        }
    }
    </script>
    
    <style>
    .reason{
        width: 70%;
        height: 300px;
    }
    </style>