<template>
  <div id="about">
    <div id="mySelf"><mySelf/></div>
    <infoBlock/>
  </div>
</template>
<style>
#about{
  width:65%;
  margin:20px auto;
  padding: 50px 10px 10px 10px;
}
#mySelf{
  padding-left: 20px;
  padding-right: 20px;
}
@media screen and (max-width: 1300px) {
  #about{
    width: 80%;
  }
}
@media screen and (max-width: 1000px) {
  #about{
    width: 90%;
    margin:10px auto;
    padding: 20px 0;
  }
}
@media screen and (max-width: 600px) {
  #about{
    width: 100%;
  }
}
</style>
<script>
import mySelf from "@/components/mySelf";
import infoBlock from "@/components/infoBlock";
export default {
  components: {mySelf, infoBlock}
}

</script>