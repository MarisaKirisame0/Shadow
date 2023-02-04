<template>
<div id="headerBlock">
  <div class='console-container'>
    <span id='text'></span>
    <div class='console-underscore' id='console'>&#95;</div>
  </div>

</div>
</template>

<script>
import axios from "_axios@1.2.2@axios";
import poetrys from '../assets/poetry.js';

export default {
  name: "myHeader",
  data(){
    return {
      content:String,
    }
  },
  methods: {
    cursorTypeEffect: function (){
      let visible = true;
      let con = document.getElementById('console');
      window.setInterval(function() {
        if (visible === true) {
          con.className = 'console-underscore hidden';
          visible = false;
        } else {
          con.className = 'console-underscore';
          visible = true;
        }}, 400)
    },
    textTypeEffect: function (id){
      let word = poetrys[Math.round(Math.random() * poetrys.length - 1)];  // 随机选取
      let letterCount = 1;
      let x = 1;
      let waiting = false;
      let target = document.getElementById(id);
      target.setAttribute('style', 'color:' + ['#fff']);
      window.setInterval(function() {
        if (letterCount === 0 && waiting === false) {
          word = poetrys[Math.round(Math.random() * poetrys.length - 1)];  // 随机更新
          waiting = true;
          target.innerHTML = word.substring(0, letterCount)
          window.setTimeout(function() {
            x = 1;
            letterCount += x;
            waiting = false;
          }, 1000)
        } else if (letterCount === word.length + 1 && waiting === false) {
          waiting = true;
          window.setTimeout(function() {
            x = -1;
            letterCount += x;
            waiting = false;
          }, 11000)
        } else if (waiting === false) {
          target.innerHTML = word.substring(0, letterCount)
          if(word[letterCount] === '<' || word[letterCount] === '>'){letterCount += 2 * x;}
          if(word[letterCount] === '&' || word[letterCount] === ';'){letterCount += 6 * x;}
          else {letterCount += x;}
        }
      }, 150)
    },

    consoleText: function(words, id, colors){
      // function([string1, string2],target id,[color1,color2])
      if (colors === undefined) colors = ['#fff'];
      let visible = true;
      let con = document.getElementById('console');
      let letterCount = 1;
      let x = 1;
      let waiting = false;
      let target = document.getElementById(id)
      target.setAttribute('style', 'color:' + colors[0])
      window.setInterval(function() {
        if (letterCount === 0 && waiting === false) {
          waiting = true;
          target.innerHTML = words[0].substring(0, letterCount)
          window.setTimeout(function() {
            let usedColor = colors.shift();
            colors.push(usedColor);
            let usedWord = words.shift();
            words.push(usedWord);
            x = 1;
            target.setAttribute('style', 'color:' + colors[0])
            letterCount += x;
            waiting = false;
          }, 1000)
        } else if (letterCount === words[0].length + 1 && waiting === false) {
          waiting = true;
          window.setTimeout(function() {
            x = -1;
            letterCount += x;
            waiting = false;
          }, 3000)
        } else if (waiting === false) {
          target.innerHTML = words[0].substring(0, letterCount)
          if(words[0][letterCount] === '<' || words[0][letterCount] === '>'){
            letterCount += 3 * x;
          }
        else {
            letterCount += x;
          }
          // letterCount += x;
        }
      }, 120)
      window.setInterval(function() {
        if (visible === true) {
          con.className = 'console-underscore hidden'
          visible = false;
        } else {
          con.className = 'console-underscore'
          visible = true;
        }
      }, 400)
    },

  },
  beforeMount() {
    let _this = this;
    /* 获取header设置文件 */
    axios.get('apis/web/getSetup?Setup=header')
        .then(function (response) {_this.content = response.data;}).catch(function (error) {console.log(error);});
  },
  mounted() {
    this.cursorTypeEffect();
    this.textTypeEffect('text');
    // this.consoleText(poetrys, 'text',['tomato','rebeccapurple','lightblue']);
  }
}
</script>

<style scoped>
#headerBlock {
  width: 100%;
  margin: 5px auto;
}
.hidden {
  opacity:0;
}
.console-container {
  font-size:1em;
  height: 60px;
  text-align:center;
  display:block;
  color:white;
  margin:auto;
}
.console-underscore {
  display:inline-block;
  position:relative;
  top:-0.14em;
}
@media screen and (max-width: 800px) {
  .console-container {
    font-size:0.8em;
  }
}
/*/deep/ h2{*/
/*  margin: 1px auto;*/
/*  text-indent: 0.4em;*/
/*  letter-spacing: 0.4em;*/
/*  color: #42b983;*/
/*  text-align: center;*/
/*}*/
/*/deep/ h4{*/
/*  margin: 0;*/
/*  text-align: center;*/
/*}*/
</style>