<template>
  <router-link class="postBox" :to="{path: '/post',query: {id}}" target="_blank" tag="a">
    <div id="title" class="postBoxBlock">{{title}}</div>
    <div id="intro" class="postBoxBlock">{{intro}}</div>
    <div id="info" class="postBoxBlock">
      <div id="time">
        <svg-icon class="svg-icon" data_iconName="Time" className="Time_icon"></svg-icon>
        <span>{{time}}</span>
      </div>
      <div id="type">
        <svg-icon class="svg-icon" data_iconName="Bookmark" className="Bookmark_icon"></svg-icon>
        <span>{{type}}</span>
      </div>
      <div id="tags">
        <svg-icon class="svg-icon" data_iconName="Tag" className="Tag_icon"></svg-icon>
        <span>{{hits}}</span>
      </div>
    </div>
  </router-link>
</template>
<script>

export default {
  name: "post",
  props:{
    info:JSON,
  },
  components: {},
  data () {
    return{
      id:"",
      title:"",
      time:"",
      type:"",
      intro:"",
      hits:"",
      cover:"",
    }
  },
  watch:{
    info:function (){
      this.id=this.info.id;
      this.title=this.info.title;
      this.time=this.info.time;
      this.type=this.info.type;
      this.intro=this.info.intro;
      this.hits=this.info.hits;
      this.cover=this.info.cover;
    }
  },
  created () {
    this.id=this.info.id;
    this.title=this.info.title;
    this.time=this.info.time;
    this.type=this.info.type;
    this.intro=this.info.intro;
    this.hits=this.info.hits;
    this.cover=this.info.cover;
  },
}
</script>

<style scoped>
.postBox{
  position: relative;
  display: block;
  overflow: hidden;
  width: 60%;
  margin: 20px auto;
  color: #b2c2ef;
  transition: 0.5s ease-in-out;
}
.postBox:before {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: #302cce;
  opacity: 0.2;
  z-index: -1;
  transform: translateX(-100%);
  transition: 0.4s ease-in-out;
}
.postBox:hover {
  color: #FFFFFF;
  transition: 0.5s ease-in-out;
}
.postBox:hover:before {
  transform: translateX(0);
  transition: 0.4s ease-in-out;
}
.postBoxBlock{
  text-align: left;
  margin: 10px auto;
}
#title{
  color: #eeffff;
  text-align: left;
  font-size: 1.2em;
  letter-spacing: 1px;
  text-indent: 5px;
  border-style: none none dashed none;
  border-width: 2px;
  border-color: rgba(255, 255, 255, 0.3);
  padding-bottom: 5px;
}
#info{
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-items: center;
  justify-content: left;
  padding: 3px 0 0 0;
}
#time{
}
#type{
  padding-left: 20px;
}
#tags{
  padding-left: 20px;
}
@media screen and (max-width: 1100px) {
  .postBox{
    width: 80%;
  }
}
@media screen and (max-width: 800px) {
  .postBox{
    width: 95%;
    margin: 10px auto;
  }
  #title{
    font-size: 0.9em;
  }
  #intro{
    font-size: 0.8em;
  }
  #info{
    font-size: 0.7em;
  }
  .postBoxBlock{
    margin: 5px auto;
  }
}
@media screen and (max-width: 300px) {
  #info{
    font-size: 0.4em;
  }
}
</style>