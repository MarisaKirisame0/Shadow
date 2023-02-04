<template>
<div id="contain">
  <p>留言板</p>
  <div class="contain-item">
    <label class="input-area">
      <input class="input-block" id="id-area" type="text" placeholder="名字" ref="idArea" >
    </label>
    <label class="input-area">
      <input class="input-block" id="mail-area" type="text" placeholder="邮箱" ref="mailArea">
    </label>
  </div>
  <label class="input-area">
    <input class="input-block" id="url-area" type="text" placeholder="网址(请加上https或http)" ref="urlArea">
  </label>
  <textarea class="input-block" rows="10" cols="10" placeholder="撰写留言(未设置邮箱发送，提交后刷新可见)" ref="textArea"></textarea>
  <page-button @click="sendMessage" text="summit"/>
</div>
</template>

<script>
import axios from "axios";
import PageButton from "@/components/pageButton";

export default {
  name: "messageTable",
  components: {PageButton},
  methods: {
    sendMessage: function (){
      // 获取留言板中输入内容
      let id = this.$refs.idArea.value;
      let mail = this.$refs.mailArea.value;
      let url = this.$refs.urlArea.value;
      let text = this.$refs.textArea.value;
      // 清空留言板
      this.$refs.idArea.value = '';
      this.$refs.mailArea.value = '';
      this.$refs.urlArea.value = '';
      this.$refs.textArea.value = '';
      // 字符筛选,去除引号,避免SQL注入
      id = id.replace(/‘/g, '').replace(/“/g, '').replace(/'/g, '').replace(/"/g, '');
      mail = mail.replace(/‘/g, '').replace(/“/g, '').replace(/'/g, '').replace(/"/g, '');
      url = url.replace(/‘/g, '').replace(/“/g, '').replace(/'/g, '').replace(/"/g, '');
      text = text.replace(/‘/g, '').replace(/“/g, '').replace(/'/g, '').replace(/"/g, '');
      // 构造数据体
      let newMes = {
        name: id,
        url: url,
        m: text,
        time: new Date().toLocaleDateString(),
        mail: mail,
      };
      // axios数据交互
      if(id !== '' && text !== ''){
        axios.post('/apis/web/addM', newMes)
            .then(function (response) {console.log("留言成功");})
            .catch(function (error) {console.log(error);});
      }
    }
  },
}
</script>

<style scoped>
#contain{
  margin: auto;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  justify-content: space-between;
}
p{
  font-size: 1.2em;
  letter-spacing: 1px;
}
.contain-item{
  width: 100%;
  margin: auto;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-between;
}
.input-area{
  margin-top: 3px;
  margin-bottom: 3px;
}
input{
  height: 25px;
}
.input-block{
  background-color: rgba(0, 0, 0, 0.3);
  color: #FFFFFF;
  border-radius: 8px;
  text-align: left;
}
#id-area{
  width: 490px;
}
#mail-area{
  width: 490px;
}
#url-area{
  width: 994px;
}
textarea{
  width: 996px;
  height: 100px;
  margin: 5px 0;
}
button{
  width:100px;
  margin:auto;
}
@media screen and (max-width: 1000px) {
  .contain-item{
    flex-direction: column;
    justify-content: center;
  }
  textarea{
    width: 100%;
    height: 120px;
  }
  #url-area{
    width: 100%;
  }
  #id-area{
    width: 100%;
  }
  #mail-area{
    width: 100%;
  }
}
@media screen and (max-width: 500px) {
  textarea{
    height: 150px;
  }
}
</style>