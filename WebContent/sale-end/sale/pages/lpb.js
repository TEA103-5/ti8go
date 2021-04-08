
        const hello ={
            props:['named'],
            template: '<p>歡迎 {{ named }} 來到 Vue.js 前端設計實戰營！</p>'
        }
        new Vue({
            el: '#app',
            components:{
                hello
            }
        })
