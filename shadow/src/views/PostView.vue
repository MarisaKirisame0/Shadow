<template>
<div id="passage" :style="{opacity: opacity_var}">
  <div id="passage-main-area" v-html="content" v-highlight v-code></div>
</div>
</template>

<script>
import axios from "_axios@1.2.2@axios";
import globalVariable from '@/components/mathJax/globalVariable'

export default {
  name: "PostView",
  data(){
    return{
      content:"",
      opacity_var: 0,
    }
  },
  methods:{
    getContent: function(){
      let _this = this;
      // 文章内容获取
      axios.get('http://localhost:8080/web/getPost?ID='+ _this.$route.query.id)
          .then(function (response) {_this.content = response.data.Content;})
          .catch(function (error) {console.log(error);});
      // LaTex公式渲染
      if(globalVariable.isMathjaxConfig){
        globalVariable.initMathjaxConfig();
      }
      globalVariable.MathQueue("passage-main-area");//渲染文章里面的LaTeX公式
    },
    showControl(){
      clearTimeout(this.timer);  //清除延迟执行
      this.timer = setTimeout(()=>{this.opacity_var = 1;},500);  //设置延迟执行
    }
  },
  created() {
    this.getContent();  // 使用Axios查询获取文章内容
    globalVariable.MathQueue("passage-main-area");
  },
  mounted() {
    // MathJax调用
    this.$nextTick(function () { //这里要注意，使用$nextTick等组件数据渲染完之后再调用MathJax渲染方法，要不然会获取不到数据
      if(globalVariable.isMathjaxConfig){globalVariable.initMathjaxConfig();}
      globalVariable.MathQueue("passage-main-area");//传入组件id，让组件被MathJax渲染
    })
    // 页面文章逐渐淡化出现
    this.showControl();
  },
  updated() {
    // 页面更新后要重新渲染
    globalVariable.MathQueue("passage-main-area");
  }
}
</script>

<style scoped>
#passage{
  width:75%;
  margin:auto;
  text-align:left;
  transition: opacity 0.5s ease-in-out;
}
#passage-main-area >>> .MJXc-display{
  color: lightcyan;
}
#passage-main-area >>> .mjx-noError{
  color: lightcyan;
}
#passage-main-area >>> h1{
  font-size: 1.8rem;
  text-align: center;
}
#passage-main-area >>> h2{
  color: skyblue;
  font-size: 1.6rem;
  display: inline-block;
}
#passage-main-area >>> h3{
  color: skyblue;
  font-size: 1.5rem;
}
#passage-main-area >>> h4{
  color: skyblue;
  font-size: 1.4rem;
}
#passage-main-area >>> h5{
  color: skyblue;
  font-size: 1.4rem;
}
#passage-main-area >>> h6{
  color: khaki;
  font-size: 1.2rem;
}
#passage-main-area >>> a{
  /*color: greenyellow;*/
}
#passage-main-area >>> p{
  font-size: 1rem;
}
#passage-main-area >>> li{
  font-size: 1rem;
}
#passage-main-area >>> pre{
  font-size: 1rem;
}
#passage-main-area >>> .codeBoxFigure{
  overflow-y: hidden;
  padding: 0;
  background-color: rgba(0, 0, 0, 0.4);
  color: #eff;
  line-height: 1.6;
  position: relative;
  -webkit-box-shadow: 0 0 5px 0 rgb(0 0 0);
  box-shadow: 0 0 5px 0 rgb(0 0 0);
  -webkit-transform: translateZ(0);
  border-radius: 10px;
  margin-bottom: 20px;
  margin-top: 10px;
}
#passage-main-area >>> .codeBoxDiv{
  color: white;
}
#passage-main-area >>> .codeBoxDiv::before{
  content: ' ';
  position: absolute;
  -webkit-border-radius: 50%;
  border-radius: 50%;
  background: #fc625d;
  width: 12px;
  height: 12px;
  top: 0;
  left: 20px;
  margin-top: 8px;
  margin-bottom: 5px;
  -webkit-box-shadow: 20px 0 #fdbc40, 40px 0 #35cd4b;
  box-shadow: 20px 0 #fdbc40, 40px 0 #35cd4b;
  z-index: 3;
}
@media screen and (max-width: 1200px) {
  #passage{
    width:90%;
  }
}
@media screen and (max-width: 700px) {
  #passage{
    width:100%;
  }
  #passage-main-area >>> h1{
    font-size: 1.2rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> h2{
    font-size: 1.2rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> h3{
    font-size: 1.1rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> h4{
    font-size: 1rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> h5{
    font-size: 1rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> h6{
    font-size: 0.9rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> li{
    font-size: 0.8rem;
  }
  #passage-main-area >>> p{
    font-size: 0.8rem;
    padding-left: 5px;
    padding-right: 5px;
  }
  #passage-main-area >>> pre{
    width: 100%;
    font-size: 0.8rem;
    padding: 0;
  }
  #passage-main-area >>> .codeBoxDiv::before{
    width: 8px;
    height: 8px;
  }
  #passage-main-area >>> figure{
    margin: 0;
  }
  #passage-main-area >>> ul{
    padding-left: 10px;
  }
  #passage-main-area >>> ol{
    padding-left: 10px;
  }
}
</style>