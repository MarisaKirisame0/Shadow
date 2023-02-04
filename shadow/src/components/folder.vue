<template>
  <div class="container">
    <div class="folder">
      <div class="paper">
        <h1>{{typeInfo.title}}</h1>
        <p>{{typeInfo.intro}}</p>
      </div>
      <div class="cover">
        <div class="title">{{typeInfo.name}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "folder",
  props:{
    typeInfo:JSON,
  },
}
</script>

<style scoped>
*, *:before, *:after {
  box-sizing: border-box;
}
.container {
  position: relative;
  width: 100px;
  height: 100px;
  margin: 50px 50px 50px 50px;
}
.container > .folder {
  width: 120px;
  height: 100px;
  position: absolute;
}
.container > .folder > .cover {
  cursor: pointer;
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #fab62f;
  -moz-border-radius: 0 0 10px 10px;
  -webkit-border-radius: 0;
  border-radius: 0 0 10px 10px;
  -moz-box-shadow: 5px 5px rgba(0, 0, 0, 0.2);
  -webkit-box-shadow: 5px 5px rgba(0, 0, 0, 0.2);
  box-shadow: 5px 5px rgba(0, 0, 0, 0.2);
}
.container > .folder > .cover:before, .container > .folder > .cover:after {
  box-sizing: border-box;
  content: "";
  display: block;
  position: absolute;
  top: -50px;
  border: 30px solid transparent;
}
.container > .folder > .cover:before {
  left: 0;
  width: 50%;
  border-left: none;
  border-bottom-color: #fab62f;
}
.container > .folder > .cover:after {
  right: 0;
  width: 50%;
  border-right: none;
  border-bottom-color: #fab62f;
}
.container > .folder > .cover > .title {
  position: absolute;
  padding: 10px;
  text-transform: uppercase;
  font-weight: bold;
  text-align: center;
  font-size: 1.5em;
  color: rgba(0, 0, 0, 0.3);
  -ms-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
.container > .folder > .paper {
  opacity: 1;
  position: absolute;
  overflow: hidden;
  width: 100px;
  height: 120px;
  top: calc(50% - 80px);
  left: calc(50% - 50px);
  transition: top 0.5s, opacity 0.4s;
  font-size: 0.1em;
  padding: 1em;
  color: #644913;
  background-color: #fde2ac;
  -moz-box-shadow: 10px 10px rgba(0, 0, 0, 0.2);
  -webkit-box-shadow: 10px 10px rgba(0, 0, 0, 0.2);
  box-shadow: 10px 10px rgba(0, 0, 0, 0.2);
  -moz-border-radius: 5px;
  -webkit-border-radius: 5px;
  border-radius: 5px;
}
.container > .folder:hover > .paper {
  top: -110px;
}
@media screen and (max-width: 850px) {
  .container {
    position: relative;
    width: 80px;
    height: 60px;
    margin: 30px 30px 30px 30px;
  }
  .container > .folder {
    width: 100px;
    height: 70px;
  }
  .container > .folder > .cover:before {
    width: 45%;
  }
  .container > .folder > .cover:after {
    width: 45%;
  }
  .container > .folder > .cover > .title {
    padding: 10px;
    font-size: 1.2rem;
  }
  .container > .folder > .paper {
    width: 90px;
    height: 100px;
    top: calc(50% - 70px);
    left: calc(50% - 45px);
    font-size: 1px;
  }
  .container > .folder:hover > .paper {
    top: -90px;
  }
}
</style>