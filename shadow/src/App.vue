<template>
  <starrySky id="Background" />
  <navigation/>
  <my-header/>
  <router-view/>
  <my-footer :post-counts="TotalPostCount"/>
</template>


<script>
import axios from "_axios@1.2.2@axios";
import navigation from "@/components/navigation";
import StarrySky from "@/components/starrySky";
import MyHeader from "@/components/myHeader";
import MyFooter from "@/components/myFooter";

export default {
  components: {MyFooter, MyHeader, StarrySky, navigation},
  data(){
    return{
      TotalPostCount: Number,
    }
  },
  created() {
    let _this = this;
    axios.get('apis/web/init')
        .then(function (response) {_this.TotalPostCount = response.data;})
        .catch(function (error) {console.log(error);});
  },
}
</script>

<style>
#app{
  font-family: FiroMono, SfCN, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #FFF;
}
#Background{
  position: fixed;
  width:100%;
  height:100%;
  top:0;
  left:0;
  z-index: -99;
}
a{
  color: greenyellow;
  text-decoration: none;
}
</style>