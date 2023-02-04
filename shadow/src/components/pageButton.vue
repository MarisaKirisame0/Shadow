<template>
  <button class="pulse">
    {{text}}
  </button>
</template>

<script>
export default {
  name: "pageButton",
  props:{
    text:String,
  },
}
</script>

<style scoped>
button {
    width: 100px;
    height: 40px;
  background: none;
  border: 2px solid;
  font: inherit;
  line-height: 1;
  margin: 0.5em;
  padding-top: 10px;
  padding-bottom: 10px;
}
button {
  color: var(--color);
  transition: 0.25s;
}
button:hover, button:focus {
  border-color: var(--hover);
  color: #fff;
}
.pulse {
  --color: #ef6eae;
  --hover: #ef8f6e;
}
.pulse:hover,
.pulse:focus {
  -webkit-animation: pulse 1s;
  animation: pulse 1s;
  box-shadow: 0 0 0 2em transparent;
}
@-webkit-keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 var(--hover);
  }
}
@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 var(--hover);
  }
}



/*html *:before,*/
/*html *:after {*/
/*  box-sizing: border-box;*/
/*  transition: 0.5s ease-in-out;*/
/*}*/
/**:before,*/
/**:after {*/
/*  z-index: -1;*/
/*}*/
/*.btn{*/
/*  position: relative;*/
/*  overflow: hidden;*/
/*  width: 100px;*/
/*  height: 40px;*/
/*  margin: 10px 10px 10px 10px;*/
/*  text-transform: uppercase;*/
/*  text-align: center;*/
/*  border: 1px solid currentColor;*/
/*  color: #a16c60;*/

/*  display: flex;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*}*/
/*.btn:before, .btn:after,*/
/*.btn span:before,*/
/*.btn span:after {*/
/*  content: "";*/
/*  position: absolute;*/
/*  top: 0;*/
/*  left: 0;*/
/*  width: 100%;*/
/*  height: 0;*/
/*  background-color: rgba(86, 49, 41, 0.25);*/
/*  transition: 0.4s ease-in-out;*/
/*}*/
/*.btn:after,*/
/*.btn span:before {*/
/*  top: auto;*/
/*  bottom: 0;*/
/*}*/
/*.btn span:before,*/
/*.btn span:after {*/
/*  transition-delay: 0.4s;*/
/*}*/
/*.btn:hover {*/
/*  color: #e3d4d0;*/
/*}*/
/*.btn:hover:before, .btn:hover:after,*/
/*.btn:hover span:before,*/
/*.btn:hover span:after {*/
/*  height: 80px;*/
/*}*/
/*.btn:active {*/
/*  background-color: #905244;*/
/*}*/
</style>