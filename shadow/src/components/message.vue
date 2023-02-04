<template>
  <div class="message-block">
    <a :href="messageData.url" target="_blank" >
      <div id="name" class="text-contain">{{messageData.name}}</div>
    </a>
    <div id="time" class="text-contain">time: {{messageData.time}}</div>
    <div id="content" class="text-contain">{{messageData.m}}</div>
  </div>
</template>

<script>
export default {
  name: "message",
  props:{
    messageData:JSON,
  }
}
</script>

<style scoped>
.message-block{
  margin: 0 auto 20px auto;
  text-align: left;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: center;
  border: 1px dashed blanchedalmond;
}
.text-contain{
  padding: 10px;
}
a{
  color: #FFFFFF;
}
a:hover{
  color: #35cd4b;
}
#name{
  color: rgba(255, 255, 255, 0.9);
}
#time{
  color: rgba(255,255,255,0.61);
  padding-top: 0;
  font-style: italic
}
@media screen and (max-width: 800px) {
  .message-block{
    font-size: 0.8rem;
  }
  .text-contain{
    padding: 5px;
  }
}
</style>