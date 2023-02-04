<template>
  <div id="contain">
    <div id="text-area">
      <p v-html=content></p>
      <a href="http://marisa-kirisame.gitee.io/bin/chess.html" target="_blank">或许会想要来一盘棋？</a>
    </div>
    <div id="friend-area">
      <friend-link v-for="lk in links" :link-info="lk"/>
    </div>
    <message-table/>
    <div id="message-area">
      <message v-for="m in messages" :message-data="m"/>
      <div id="page-button">
        <svg-icon class="svg-icon" data_iconName="Left" className="Left_icon" @click="lastPage"></svg-icon>
        <svg-icon class="svg-icon" data_iconName="Right" className="Right_icon" @click="nextPage"></svg-icon>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "_axios@1.2.2@axios";
import FriendLink from "@/components/friendLink";
import MessageTable from "@/components/messageTable";
import Message from "@/components/message";
export default {
  name: "LinkView",
  components: {Message, MessageTable, FriendLink},
  data(){
    return{
      content: String,
      links:[],
      messages: [],
      PageIndex: 1,
      maxPageIndex: 2,
    }
  },
  methods: {
    configQuery: function (){
      let _this = this;
      /* 获取link设置文件 */
      axios.get('apis/web/getSetup?Setup=link')
          .then(function (response) {_this.content = response.data;}).catch(function (error) {console.log(error);});
    },
    queryMessage: function (){
      let _this = this;
      axios.get('/apis/web/messages?pageIndex='+(_this.PageIndex).toString())
          .then(function (response) {
            _this.messages = response.data.messages;
            _this.maxPageIndex = response.data.maxPageIndex;
          })
          .catch(function (error) {console.log(error);});
    },
    lastPage:function() {
      this.PageIndex = this.PageIndex - 1;
      if(this.PageIndex<1){this.PageIndex = 1;}
      this.queryMessage();
    },
    nextPage:function() {
      this.PageIndex = this.PageIndex + 1;
      if(this.PageIndex>this.maxPageIndex){this.PageIndex = this.maxPageIndex;}
      this.queryMessage();
    },
  },
  created() {
    let _this = this;
    axios.get('/apis/web/links')
        .then(function (response) {_this.links = response.data;})
        .catch(function (error) {console.log(error);});
    _this.queryMessage();
  },
  beforeMount() {
    this.configQuery();
  }
}
</script>

<style scoped>
#contain{
  width: 1000px;
  margin: auto;
}
#text-area{
  text-align: left;
  margin: auto;
  padding: 10px 0 50px 0;
}
#friend-area{
  width: 100%;
  margin: auto;
  padding: 0 0 30px 0;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}
#message-area{
  width: 100%;
  margin-top: 30px;
}
#page-button{
  position: relative;
  overflow:hidden;
  bottom: 0;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-items: center;
  justify-content: space-around;
  margin: 0 auto 20px auto;
}
.svg-icon:hover{
  cursor: pointer;
}
a{
  color: #35cd4b;
}
@media screen and (max-width: 1000px) {
  #contain{
    width: 98%;
  }
  #text-area{
    padding-top: 0;
  }
}
@media screen and (max-width: 500px) {
  #text-area{
    font-size: 0.3rem;
  }
}
</style>