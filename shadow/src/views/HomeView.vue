<template>
  <div id="home-view">
<!--    <loader v-show="loader_show"/>-->
    <div id="post-area" :style="{opacity: opacity_var}">
      <post class="post" v-for="info in PostList" :info="info"/>
    </div>
    <div id="page-button">
      <page-button v-if="lastBtn" @click="lastPage" text="BACK"/>
      <page-button v-if="nextBtn" @click="nextPage" text="NEXT"/>
    </div>
  </div>
</template>

<script>
import axios from "_axios@1.2.2@axios";
import post from "@/components/post";
import PageButton from "@/components/pageButton";
import Loader from "@/components/loader";

export default {
  name: 'HomeView',
  components: {
    Loader,
    PageButton,
    post
  },
  data(){
    return{
      PostList:[],      //保存从后端获取的文章推送信息(JSON数组)
      TotalPostCount:0, //从后端获取的目前已有文章总数
      PageIndex:1,      //当前页面页码
      MaxPageIndex:2,   //从后端获取的数据库分页最大页码数
      opacity_var: 1,
      nextBtn: true,
      lastBtn: false,
    }
  },
  computed:{
    loader_show: function (){
      return this.opacity_var===0;
    }
  },
  methods:{
    InitQuery:function (){
      let _this = this;
      axios.get('apis/web/getPage?pageIndex='+(_this.PageIndex).toString())
          .then(function (response) {
            _this.PostList = response.data.PostList;
            _this.MaxPageIndex = response.data.MaxPageIndex;
            _this.TotalPostCount = response.data.TotalPostCount;
          })
          .catch(function (error) {console.log(error);});
    },
    nextPage:function (){
      this.opacity_var = 0; // 控制推送部分不显示
      clearTimeout(this.timer);  //清除延迟执行
      this.timer = setTimeout(()=>{   //设置延迟执行
        this.PageIndex = this.PageIndex + 1;  // 页码更新
        let _this = this;
        axios.get('apis/web/getPage?pageIndex='+(_this.PageIndex).toString())
            .then(function (response) {_this.PostList = response.data.PostList;})
            .catch(function (error) {console.log(error);});
        this.timer = setTimeout(()=>{this.opacity_var = 1;},500);
      },550);
      // 按钮状态更新
      this.lastBtn = true;
      if(this.PageIndex >= this.MaxPageIndex){
        this.nextBtn = false;
      }
    },
    lastPage:function (){
      this.opacity_var = 0; // 控制推送部分不显示
      clearTimeout(this.timer);  //清除延迟执行
      this.timer = setTimeout(()=>{   //设置延迟执行
        this.PageIndex = this.PageIndex - 1;  // 页码更新
        let _this = this;
        axios.get('apis/web/getPage?pageIndex='+(_this.PageIndex).toString())
            .then(function (response) {_this.PostList = response.data.PostList;})
            .catch(function (error) {console.log(error);});
        this.timer = setTimeout(()=>{this.opacity_var = 1;},500);
      },550);
      this.nextBtn = true;
      if(this.PageIndex <= 1){
        this.lastBtn = false;
      }
    },
  },
  created() {
    this.InitQuery();
  },
}
</script>
<style>
#home-view{
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: center;
}
#post-area{
  transition: opacity 0.5s ease-in-out;
}
#page-button{
  position: relative;
  overflow:hidden;
  bottom: 0;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-items: center;
  justify-content: left;
  margin: auto;
}
</style>
