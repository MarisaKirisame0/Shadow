<template>
  <div id="Myself-main-area">
    <div id="AvatarImageBlock">
    <span class="author-image-span"><img id="Author-Image" :src="avatar" alt="想看本人什么的不可能的"></span>
    </div>
    <div id="DetailInfoBlock">
      <p v-html=content></p>
    </div>
  </div>
</template>

<script>
import axios from "_axios@1.2.2@axios";

export default {
  name: "mySelf",
  data(){
    return{
      content: String,
      avatar:"",
    }
  },
  methods: {
    setupQuery: function (){
      let _this = this;
      /* 获取mySelf设置文件 */
      axios.get('apis/web/getSetup?Setup=mySelf')
          .then(function (response) {_this.content = response.data;}).catch(function (error) {console.log(error);});
    },
    query: function (){
      let _this = this;
      axios.get('http://localhost:8080/web/getBasic')
          .then(function (response) {
            _this.avatar = response.data[0].avatar;
          })
          .catch(function (error) {console.log(error);});
    },
  },
  created() {
    this.setupQuery();
    this.query();
  }
}
</script>

<style scoped>
#Myself-main-area{
  font-family: Gabriola,cursive,"KaiTi",Serif;
  font-size: larger;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: left;
}
#Author-Image{
  width:100px;
  height: 100px;
  border:1px solid darkblue;
  border-radius: 50%;
  overflow: hidden;
  transition: all 2s linear;
}
#Author-Image:hover{
  transform:rotate(-360deg);
}
.author-image-span{
  margin: 0;
}
#DetailInfoBlock{
  position: relative;
  left: 20px;
  margin: 0;
  text-align: left;
}
#DetailInfoBlock >>> p{
  margin: 0;
}
.svg-icon{
  margin:0 5px 0 5px;
}
#AuthorName{
  margin: 5px 0 5px 0;
}
#Signature{
  margin: 5px 0 5px 0;
}
#Location{
  margin: 5px 0 5px 0;
}
#University{
  margin: 5px 0 5px 0;
}
#profession{
  margin: 5px 0 5px 0;
}
@media screen and (max-width: 650px) {
  #Myself-main-area{
    width:100%;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
    backdrop-filter:blur(50px);
    font-size: medium;
  }
  #AvatarImageBlock{
    margin: auto;
    padding-bottom: 5px;
  }
  #DetailInfoBlock{
    left: 5px;
  }
  #Location{
  }
  #University{
    font-size: small;
  }
  #profession{
    font-size: small;
  }
}
</style>