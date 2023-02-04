<template>
  <footer id="footer">
    <div class="footer-text" id="Footer-TimeBlock">小站运行起点:{{startTime}} | 博客总数:{{postCounts}}</div>
    <div class="footer-text" id="Footer-NameBlock">{{ title }} | {{motto}}</div>
    <div class="footer-text" id="Footer-HtmlSecurity">
      <img src="@/assets/HtmlSecurityIcon.png" alt="..."/><a id="footer-link" target="_blank" href="https://beian.miit.gov.cn/">{{code}}</a>
    </div>
  </footer>
</template>

<script>
import axios from "_axios@1.2.2@axios";
let postCounts;
export default {
  name: "myFooter",
  props:{
    postCounts,
  },
  data(){
    return{
      startTime: '',
      title: '',
      motto: '',
      code: '',
    }
  },
  methods: {
    query: function (){
      let _this = this;
      axios.get('http://localhost:8080/web/getBasic')
          .then(function (response) {
            _this.startTime = response.data[0].begin;
            _this.title = response.data[0].title;
            _this.motto = response.data[0].motto;
            _this.code = response.data[0].code;
          })
          .catch(function (error) {console.log(error);});
    },
  },
  created() {
    this.query();
  }
}
</script>

<style scoped>
#footer{
  width: 100%;
  height: 120px;
  padding: 15px 0 5px 0;
  background-color: rgba(255,255,255,0);
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
}
a{
  color: white;
}
img{
  color: #35cd4b;
}
#footer-link{
  text-decoration:none;
}
.footer-text{
  text-align: center;
  padding: 2px 0 2px 0;
}
@media screen and (max-width: 800px) {
  #footer{
    font-size: 0.8rem;
  }
  .footer-text{
  }
}
@media screen and (max-width: 800px) {
  #footer{
    font-size: 0.6rem;
  }
}
</style>