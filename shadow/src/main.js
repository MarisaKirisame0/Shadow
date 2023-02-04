import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import hljs from 'highlight.js'
import '@/assets/fonts/fonts.css'

// 引入代码高亮css
import 'highlight.js/styles/night-owl.css'

// 引入SVG图像显示js
import './svgIcon'
import '@/svgIcon/index.js'
import svgIcon from "@/svgIcon/index.vue"

// createApp(App).use(router).mount('#app')

const app = createApp(App)
app.use(router)
app.component('svg-icon',svgIcon)
app.mount('#app')


// 定义代码高亮指令 v-highlight
app.directive('highlight',function (el) {
    let blocks = el.querySelectorAll('pre code');
    blocks.forEach((block)=>{
        hljs.highlightBlock(block);
    })
})

// 定义代码区域使用mac风格代码框的指令 v-code
app.directive('code',function (el) {
    let figures = el.querySelectorAll('figure');
    if (figures.length === 0){  // 为了避免在passage-area的opacity变化前后渲染两次, 每次触发时检测是否已经建立figure
        let blocks = el.querySelectorAll('pre code');
        blocks.forEach((block)=>{
            // 获取父节点pre,因为后面插入原本元素后父节点改变
            let block_parent = block.parentNode
            // 创建新元素figure,其类为codeBoxFigure
            let codBoxFigure = document.createElement("figure");
            codBoxFigure.setAttribute('class', 'codeBoxFigure')
            // 将代码区域code元素加入到figure,成为其子元素
            codBoxFigure.appendChild(block)
            // 创建新元素div,codeBoxDiv
            let codBoxDiv = document.createElement("div");
            codBoxDiv.setAttribute("class",'codeBoxDiv');
            // 留空内容为了留下区域显示代码框上三个圆圈
            codBoxDiv.innerHTML = '   ';
            // 插入该div到figure中
            codBoxFigure.insertBefore(codBoxDiv, codBoxFigure.childNodes[0]);
            // 节点替换,将原本code区域父节点pre的子节点代替为figure元素节点
            block_parent.appendChild(codBoxFigure);
        })
    }
})

app.use(hljs.vuePlugin)
