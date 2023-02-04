<template>
  <a class="FriendLink" :href="linkInfo.url" target="_blank">
    <div class="FriendBlock">
      <img class="Friend-Avatar" :src="linkInfo.avatar"/>
      <div id="friend-link-intro">
        <div class="Friend-Name">{{linkInfo.name}}</div>
        <div class="Friend-Info">{{linkInfo.info}}</div>
      </div>
    </div>
  </a>
</template>

<script>
export default {
  name: "friendLink",
  props:{
    linkInfo:JSON,
  },
}
</script>

<style scoped>
.FriendLink{
  width: 220px;
  margin-bottom: 10px;
  text-align: center;
  text-decoration:none;
  border-radius: 7px;
}
.FriendBlock{
  background-color: rgba(0,0,0,0);
  color: #FFFFFF;
  height: 150px;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: center;
  transition: background-color 0.5s linear;
}
#friend-link-intro{
  width: 100%;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: center;
}
.FriendBlock:hover{
  background-color: rgba(255,255,255,0.1);
}
.Friend-Avatar{
  width: 50px;
  height: 50px;
  margin: auto;
  border: 1px solid darkslateblue;
  border-radius: 50%;
  overflow: hidden;
  transition: transform 0.7s linear;
}
.Friend-Avatar:hover{
  transform:rotate(360deg);
}
.Friend-Name{
  margin:5px 0 5px 0;
  font-size: large;
}
.Friend-Info{
  margin:5px 0 5px 0;
  font-size: 15px;
}
a:link{
  color:black;
}
a:visited{
  color:black;
}
a:hover{
  color: rebeccapurple;
}
@media screen and (max-width: 650px) {
  .FriendLink{
    width: 100%;
  }
  .FriendBlock{
    flex-direction: row;
    background-color: rgba(255,255,255,0.6);
    height: 60px;
    transition: none;
  }
  .Friend-Name{
    font-size: medium;
  }
  .Friend-Info{
    font-size: 10px;
  }
  #friend-link-intro{
    width: 90%;
  }
  .FriendBlock:hover{
    background-color: rgba(255,255,255,0.6);
  }
  .Friend-Avatar{
    position: relative;
    left: 10px;
    margin: 15px 0;
    width: 40px;
    height: 40px;
  }
}
@media screen and (max-width: 300px) {
  .Friend-Name{
    font-size: 10px;
  }
  .Friend-Info{
    font-size: 9px;
  }
}
</style>