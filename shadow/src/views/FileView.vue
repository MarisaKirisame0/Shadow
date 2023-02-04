<template>
  <div id="search-area">
    <label>
      <input id="searchInput" type="text" placeholder="" ref="searchInput">
    </label>
    <svg-icon class="svg-icon" data_iconName="Search" className="Search_icon" @click="search"></svg-icon>
  </div>

  <div id="types-area">
    <folder v-for="typeItem in typeList" :typeInfo="typeItem" @click="getTypePost(typeItem.name)"/>
  </div>

  <div id="query-res-area" :style="{opacity: opacity_var}">
      <transition v-for="(info, i) in postList">
        <post :info="info" v-show="showControl[i]"/>
      </transition>
  </div>

</template>

<script>
import folder from "@/components/folder";
import post from "@/components/post";

import axios from "_axios@1.2.2@axios";

export default {
  name: "FileView",
  components: {post, folder},
  data(){
    return{
      typeList: [],
      postList: [],
      showControl: [],
      showCounts: 4,  // 用于控制每次显示增加项目数量
      showControlIndex: 0,  // 用于控制当前showControl从哪里开始为false
      PageIndex:1,      //当前页面页码
      MaxPageIndex:1000,   //从后端获取的数据库分页最大页码数
      opacity_var: 0,  // postArea的透明度控制变量
      type_name_now: '',  // 当前的查询类型
    }
  },
  methods:{
    typeQuery: function () {  // 查询类型列表
      let _this = this;
      axios.get('apis/web/types')
          .then(function (response) {
            _this.typeList = response.data;
          })
          .catch(function (error) {console.log(error);});
    },
    search: function () {  // 按照关键词查询文章
      let _this = this;
      let kw = _this.$refs.searchInput.value  // 获取输入框的内容
      // 字符筛选,去除引号,避免SQL注入
      kw = kw.replace(/‘/g, '').replace(/“/g, '').replace(/'/g, '').replace(/"/g, '');
      _this.opacity_var = 0;  // 关闭查询区域显示
      // axios数据交互(与getTypePost中操作一致)
      axios.get('apis/web/keyWord?KeyWord='+ kw)
          .then(function (response) {
            let arr = Array(response.data.length).fill(false);
            let transCounts = _this.showCounts;
            if(_this.showCounts > arr.length){transCounts = arr.length;}
            for(let i=0; i<transCounts; i++){arr[i] = true;}
            _this.showControlIndex = transCounts;
            _this.showControl = arr;
            clearTimeout(_this.timer);
            _this.timer = setTimeout(()=>{
              _this.postList = response.data;
              _this.opacity_var = 1;
            },600);
          })
          .catch(function (error) {console.log(error);});
    },
    getTypePost: function (type_name){  // 按照类型查询文章
      let _this = this;
      _this.opacity_var = 0;
      axios.get('apis/web/type?Type='+ type_name)
          .then(function (response) {
            // 数据交互后进行响应
            let arr = Array(response.data.length).fill(false);  // 生成post项显示控制数组
            // post项控制数组前几项显示设置
            let transCounts = _this.showCounts;
            if(_this.showCounts > arr.length){transCounts = arr.length;}
            for(let i=0; i<transCounts; i++){arr[i] = true;}
            _this.showControlIndex = transCounts; // 更新showControlIndex
            _this.showControl = arr;  // post项控制数组变量设置
            //设置延迟执行
            clearTimeout(_this.timer);  //清除延迟执行
            _this.timer = setTimeout(()=>{
              _this.postList = response.data;  // 更新post数据列表
              _this.opacity_var = 1;  // 更新后进行显示
            },600);
          })
          .catch(function (error) {console.log(error);});
    },
    scrolling: function (){  // 页面滚动条的监听事件
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      let clientHeight = document.documentElement.clientHeight;
      let scrollHeight = document.documentElement.scrollHeight;
      //滚动条到底部的条件
      if(scrollTop + clientHeight >= scrollHeight){
        if(this.opacity_var !== 0){  // 判断是否已经开启搜索
          if(this.showControlIndex !== this.postList.length){
            // 判断显示控制数组更新的终止位置
            let end_index = this.showControlIndex + this.showCounts;
            if(this.showControlIndex + this.showCounts > this.postList.length){
              end_index = this.postList.length;
            }
            // 修改showControl显示新内容
            for(let i=this.showControlIndex; i<end_index; i++){
              this.showControl[i] = true;
            }
            // 更新showControlIndex
            this.showControlIndex = end_index;
          }
        }
      }
    },
  },
  created() {
    this.typeQuery();
  },
  mounted() {
    window.addEventListener("scroll", this.scrolling);
  }
}
</script>

<style scoped>
#searchInput{
  width: 30%;
  height: 25px;
  border-radius: 8px;
  opacity: 0.2;
}
.svg-icon{
  position: relative;
  top: 5px;
}
.svg-icon:hover{
  cursor: pointer;
}
#types-area{
  width: 60%;
  height: 250px;
  margin: 30px auto;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
}
#query-res-area{
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: center;
  transition: opacity 0.5s ease-in-out;
  margin-bottom: 100px;
}
.v-enter-active {
  transition: all 0.5s ease-out;
}
.v-enter-from{
  opacity: 0;
}
@media screen and (max-width: 1300px) {
  #types-area{
    width: 90%;
  }
}
@media screen and (max-width: 1100px) {
  #searchInput{
    width: 80%;
    height: 30px;
  }
  #types-area{
    width: 100%;
    height: 250px;
    margin: 20px auto;
  }
}
</style>