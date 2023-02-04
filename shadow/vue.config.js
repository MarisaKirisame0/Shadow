const { defineConfig } = require('@vue/cli-service')

const path = require('path') //必须引入
const resolve = dir => path.join(__dirname, dir)//必须引入

module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  publicPath: './',
  outputDir: 'dist',  //build输出目录
  assetsDir: 'assets', //静态资源目录（js, css, img）
  lintOnSave: false, //是否开启eslint
  devServer: {
    open: true, //是否自动弹出浏览器页面
    host: "localhost", //表示启动的时候使用的域名，默认可以不写，则是使用localhost和本机IP
    port: '8081', // 设置端口号
    https: false,  //是否使用https协议
    // hotOnly: true, //是否开启热更新
    proxy: {
      '/apis': {
        target: 'http://localhost:8080/', //API服务器的地址
        ws: true, //代理websockets
        changeOrigin: true, // 是否跨域，虚拟的站点需要更管origin
        pathRewrite: {
          '^/api5': '/apis',
          '^/apis': '',
        }
      },
    },
  },
  chainWebpack: config => {
    config.module
        .rule('svg')
        .exclude.add(resolve('src/svgIcon')) //对应下面配置svg的路径
        .end();
    config.module
        .rule('svgIcon')
        .test(/\.svg$/)
        .include.add(resolve('src/svgIcon')) //对应下面配置svg的路径
        .end()
        .use('svg-sprite-loader')
        .loader('svg-sprite-loader')
        .options({
          symbolId: 'icon-[name]'
        });
  },
}