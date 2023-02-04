<template>
  <ul class="timeline">
    <transition v-for="(talk, i) in talkList">
      <li class="timeline-event" v-show="showControl[i]">
        <label class="timeline-event-icon"></label>
        <div class="timeline-event-copy">
          <p class="timeline-event-thumbnail">{{talk.time}}</p>
          <p class="timeline-event-mess">{{talk.mess}}</p>
          <p></p>
        </div>
      </li>
    </transition>
  </ul>
</template>

<script>
import axios from "_axios@1.2.2@axios";
export default {
  name: "timeView",
  data (){
    return{
      talkList:[],      //保存从后端获取的说说信息(JSON数组)
      showControl: [],  //控制显示数组
      showCounts: 6,  // 用于控制每次显示增加项目数量
      showControlIndex: 0,  // 用于控制当前showControl从哪里开始为false
      TotalTimeCount:0, //从后端获取的目前已有说说总数
      PageIndex:1,      //当前页面页码
      MaxPageIndex:2,   //从后端获取的数据库分页最大页码数
    }
  },
  methods: {
    Query: function () {
      let _this = this;
      axios.get('apis/web/talks')
          .then(function (response) {
            // 设置显示内容
            let arr = Array(response.data.length).fill(false);
            let transCounts = _this.showCounts;
            if(_this.showCounts > arr.length){transCounts = arr.length;}
            for(let i=0; i<transCounts; i++){arr[i] = true;}
            _this.showControlIndex = transCounts;
            _this.showControl = arr;
            // 获取数据
            _this.talkList = response.data;
          })
          .catch(function (error) {console.log(error);});
    },
    scrolling: function (){  // 页面滚动条的监听事件
      let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
      let clientHeight = document.documentElement.clientHeight;
      let scrollHeight = document.documentElement.scrollHeight;
      //滚动条到底部的条件
      if(scrollTop + clientHeight >= scrollHeight){
        if(this.showControlIndex !== this.talkList.length){
          // 判断显示控制数组更新的终止位置
          let end_index = this.showControlIndex + this.showCounts;
          if(this.showControlIndex + this.showCounts > this.talkList.length){
            end_index = this.talkList.length;
          }
          // 修改showControl显示新内容
          for(let i=this.showControlIndex; i<end_index; i++){
            this.showControl[i] = true;
          }
          // 更新showControlIndex
          this.showControlIndex = end_index;
        }
      }
    },
  },
  created() {
    this.Query();
  },
  mounted() {
    window.addEventListener("scroll", this.scrolling);
  }
}
</script>

<style scoped>
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
ul{list-style: none;}
.timeline {
  margin: 10px auto;
  position: relative;
  max-width: 65%;
  text-align: left;
}
.timeline::before {
  background-color: black;
  content: "";
  margin-left: -1px;
  position: absolute;
  top: 0;
  left: 100px;
  width: 2px;
  height: 100%;
}
.timeline-event {
  position: relative;
}
.timeline-event:hover .timeline-event-icon {
  -moz-transform: rotate(-45deg);
  -ms-transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  background-color: #a83279;
}
.timeline-event:hover .timeline-event-thumbnail {
  -moz-box-shadow: inset 200px 0 0 0 #a83279;
  -webkit-box-shadow: inset 200px 0 0 0 #a83279;
  box-shadow: inset 200px 0 0 0 #a83279;
}
.timeline-event-copy {
  padding: 25px 30px 15px 25px;
  position: relative;
  top: -30px;
  left: 60px;
  width: 80%;
}
.timeline-event-copy strong {
}
.timeline-event-icon {
  -moz-transition: -moz-transform 0.2s ease-in;
  -o-transition: -o-transform 0.2s ease-in;
  -webkit-transition: -webkit-transform 0.2s ease-in;
  transition: transform 0.2s ease-in;
  -moz-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
  background-color: black;
  outline: 4px solid white;
  display: block;
  margin: 8px 8px 8px -8px;
  position: absolute;
  top: 0;
  left: 60px;
  width: 16px;
  height: 16px;
}
.timeline-event-thumbnail {
  -moz-transition: box-shadow 0.5s ease-in 0.1s;
  -o-transition: box-shadow 0.5s ease-in 0.1s;
  -webkit-transition: box-shadow 0.5s ease-in;
  -webkit-transition-delay: 0.1s;
  transition: box-shadow 0.5s ease-in 0.1s;
  color: white;
  font-size: 15px;
  background-color: black;
  -moz-box-shadow: inset 0 0 0 0 #ef795a;
  -webkit-box-shadow: inset 0 0 0 0 #ef795a;
  box-shadow: inset 0 0 0 0 #ef795a;
  display: inline-block;
  margin-bottom: 1px;
  padding: 3px 10px 3px 10px;
  text-align: left;
}
.timeline-event-mess{
}
.v-enter-active {
  transition: all 0.5s ease-out;
}
.v-enter-from{
  opacity: 0;
}
@media screen and (max-width: 1300px) {
  .timeline {
    max-width: 80%;
  }
@media screen and (max-width: 800px) {
  .timeline {
    font-size: 0.8rem;
    max-width: 95%;
    padding-left: 0;
    left: -30px
  }
  .timeline-event-thumbnail {
    font-size: 0.8rem;
  }
  .timeline::before {
    left: 60px;
  }
  .timeline-event-copy{
    top: -15px;
    padding: 10px 30px 0 25px;
    width: 100%;
  }
}
}
</style>