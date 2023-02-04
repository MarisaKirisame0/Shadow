<template>
  <div id="nav">
    <router-link to="/">
      <svg-icon class="svg-icon" data_iconName="Home" className="Home_icon"></svg-icon>
      <span class="linkItem">首页</span>
    </router-link>
    <router-link to="/stacks">
      <svg-icon class="svg-icon" data_iconName="File" className="File_icon"></svg-icon>
      <span class="linkItem">归档</span>
    </router-link>
    <router-link to="/talk">
      <svg-icon class="svg-icon" data_iconName="Talk" className="Talk_icon"></svg-icon>
      <span class="linkItem">说说</span>
    </router-link>
    <router-link to="/links">
      <svg-icon class="svg-icon" data_iconName="Link" className="Link_icon"></svg-icon>
      <span class="linkItem">友链</span>
    </router-link>
    <router-link to="/about">
      <svg-icon class="svg-icon" data_iconName="About" className="About_icon"></svg-icon>
      <span class="linkItem">关于</span>
    </router-link>
  </div>
</template>

<script>

export default {
  name: "navigation",
  data(){
    return{
    }
  },
  methods:{
  },
  created() {
  },
}
</script>

<style scoped>
#nav{
  margin:0 auto;
  display: flex;
  flex-direction: row;
  justify-content:center;
  text-align:center;
  z-index: 99;
}
.linkItem{
  display:block;
  padding:0 10px 0 10px;
  text-align:center;
  text-decoration:none;
}
a{
  transition: all 0.8s linear;
  color: rgba(255, 255, 255, 0);
}
a:hover{
  transition: all 0.8s linear;
  color: rgba(255, 255, 255, 1);
}
@media screen and (max-width: 1100px) {
  #nav{
    margin:15px auto;
  }
  .linkItem{
    font-size: 0.8rem;
  }
}
</style>