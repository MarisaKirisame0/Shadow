萌新初学Web开发，第一次个人建站 (练手项目)。在制作该网站时，把主题为Auroa的[蝉时雨](https://chanshiyu.com/#/)的个人网站作为了实现的目标站，大量地方都复现了Auroa主题的样式，也加入了一些自己想到的创意。

  前端采用VueCLI4.5进行搭建，没有引入jQUery依赖，动画依赖Vue的过渡与CSS进行完成，代码高亮使用Highlight.js。由于工程没有使用ui框架，很多样式我写的不尽其美，最终也包含了大量的CSS。前端采用Axios与后端进行交互，Markdown文章的解析是交由后端完成的。
  后端采用SpringBoot框架，结合MyBatisPlus插件进行搭建，数据库使用MySQL。目前的方法是文章托管于本地服务器，也可以修改为托管到GitHub或者Gitee，那样需要增加Java后端中网络数据访问，配合是可以自由搭建后端仓库的。

  主题名为“RainbowLight”(虹光)。
  博客的前端后端工程已经开源到了我的[ Gitee ](https://gitee.com/marisa-kirisame)和[ Github ](https://github.com/MarisaKirisame0)上，可以免费食用。