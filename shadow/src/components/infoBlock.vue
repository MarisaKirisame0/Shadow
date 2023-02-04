<template>

  <div class="Info-block" style="border-top:1px solid mediumorchid">
    <!--方块标签图案-->
    <div class="label" style="color: mediumorchid; background-color: mediumorchid;">
      <span class="label-text">Origin</span>
    </div>
    <!--内容-->
    <p class="Info-block-text" v-html=origin></p>
  </div>

  <div class="Info-block" style="border-top:1px solid navajowhite">
    <!--方块标签图案-->
    <div class="label" style="color: navajowhite; background-color: navajowhite;">
      <span class="label-text">Theme</span>
    </div>
    <!--内容-->
    <p class="Info-block-text" v-html=theme></p>
  </div>

  <div class="Info-block" style="border-top:1px solid pink">
    <!--方块标签图案-->
    <div class="label" style="color: pink; background-color: pink;">
      <span class="label-text">TimeLine</span>
    </div>
    <!--内容-->
    <p class="Info-block-text" v-html=timeline></p>
  </div>

  <div class="Info-block" style="border-top:1px solid turquoise">
    <!--方块标签图案-->
    <div class="label" style="color: turquoise; background-color: turquoise;">
      <span class="label-text">MySelf</span>
    </div>
    <!--内容-->
    <p id="mySelfBlock" class="Info-block-text" v-html=myself></p>
  </div>

</template>

<script>
import axios from "_axios@1.2.2@axios";

export default {
  name: "infoBlock",
  data (){
  return{
    origin:String,
    theme: String,
    timeline: String,
    myself: String,
  }
  },
  methods: {
    Query: function () {
      let _this = this;
      /* 获取origin设置文件 */
      axios.get('apis/web/getSetup?Setup=origin')
          .then(function (response) {_this.origin = response.data;}).catch(function (error) {console.log(error);});
      /* 获取theme设置文件 */
      axios.get('apis/web/getSetup?Setup=theme')
          .then(function (response) {_this.theme = response.data;}).catch(function (error) {console.log(error);});
      /* 获取timeline设置文件 */
      axios.get('apis/web/getSetup?Setup=timeline')
          .then(function (response) {_this.timeline = response.data;}).catch(function (error) {console.log(error);});
      /* 获取contract设置文件 */
      axios.get('apis/web/getSetup?Setup=contract')
          .then(function (response) {_this.myself = response.data;}).catch(function (error) {console.log(error);});
    }
  },
  beforeMount() {
    this.Query();
  },
}
</script>

<style>
#mySelfBlock{
  font-family: Gabriola,cursive,Serif;
  font-size: larger;
  letter-spacing: 1px;
}
.Info-block{
  position: relative;
  Margin-top:20px;
  Margin-left: 25px;
  Margin-right: 25px;
  padding-top: 10px;
  padding-bottom: 10px;
  box-shadow: 0 2px 10px lightgray;
  transition-property: all;
  transition-duration: 0.5s;
  line-height: 30px;
  font-size: 1em;
  text-align: left;
}
.Info-block:hover{
  bottom: 1px;
  box-shadow: 0 10px 20px lightgray;
}
.Info-block-text{
  word-break: break-all;
  padding-left: 7px;
  padding-right: 7px;
}
.label{
  width:10%;
  left:-16px;
  display:inline-block;
  margin: auto;
  padding-top: 5px;
  padding-bottom: 5px;
  position: relative;
  font-family: Gabriola, cursive;
  font-size: larger;
  text-align: center;
}
.label:after{
  content: "";
  position: absolute;
  left: 0;
  top: 100%;
  width: 0;
  height: 0;
  border-style: solid;
  border-width: 0 16px 16px 0;
  border-color: transparent;
  border-right-color: currentColor;
  filter: brightness(120%);
}
.label-text{
  margin: auto;
  color: 	white;
  font-size: larger;
  letter-spacing: 1px;
}
@media screen and (max-width: 1300px) {
  .Info-block{
    Margin-top:0;
    Margin-left: 0;
    Margin-right: 0;
  }
}
@media screen and (max-width: 600px) {
  .Info-block{
    font-size: 0.5em;
    line-height: 15px;
  }
  .label{
    width: 20%;
    font-size: 1em;
  }
}
</style>

<style scoped>

</style>