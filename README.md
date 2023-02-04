# Shadow博客主题快速食用指南

## 介绍

[Shadow](https://www.shuangxing.top)是一款完全开源的、易于使用的、具备前后端分离特性的博客主题。

:triangular_flag_on_post:网站演示：[雾雨霜星](https://www.shuangxing.top)

:books:前端采用VueCLI构建，后端采用SpringBoot+MyBatisPlus构建，前后端采用axios进行交互。

:ship:项目具备如下特性：

* 简易性：使用MySQL数据库进行各部分设置，无需重新调整源码。
* 开源性：提供前端和后端的全部源代码，可进行更加深入的自定义。
* 简约性：前端构建以CSS为主，不使用外部图片，网站响应速度高。
* 解耦性：前后端分离架构，组件模块分离，容易修改和维护。

:boat:网站具备如下特色：

* 简洁界面，清晰明了。
* 优良阅读体验的文章阅读页设计，完全按照markdown文件格式。
* 采用MathJax实现文章阅读页内LaTeX公式的显示。
* 采用highlight.js实现文章阅读页内代码高亮的显示。
* 归档页面内置搜索框模块，提供文章查询功能。
* 在页脚部分配置了备案号的设置，无需在源码中重新加入备案号。
* 自带留言板模块，无需引入其他留言板依赖包，易于维护留言内容。



:construction:shadow不具备hexo和WordPress那样的方便，需要自备后端服务器，需要自己手动网站部署。



## 初始准备

1. 后端服务器（各类型轻量服务器均可）
2. 域名及其备案



## 使用流程

1. 下载前后端源码、数据库表文件和配置文件
2. 构建数据库
3. 后端配置数据库账户信息
4. 后端打包生成jar包
5. 网站部署
6. 数据库内容修改以及修改设置文件



## 构建数据库

:rocket:下载文件"数据库表结构"内的所有excel文件，导入到数据库内即可。表内内容均为参考，根据实际修改即可。


以下对各个表的用途进行说明：

* basic：基础设置的内容，包括网站的标题(title)、简介(motto)、开始运行时间(begin)、头像(avatar)以及备案号(code)。
* config：需要使用文件进行设置的部分，用于记录设置文件位置给后端读取。
  * origin：about页面中网站起源部分的内容
  * theme：about页面中网站主题部分的内容
  * timeline：about页面中网站历程部分的内容
  * contract：about页面中博主联系方式部分的内容。
  * link：友链页面中对自己博客的介绍
  * mySelf：about页面中博主自我介绍
* link：用于记录友链的数据表
* message：用于记录留言的数据表。
  * name：留言者ID
  * url：留言者的网站
  * m：留言内容
  * time：提交留言的时间
* post：用于记录文章的数据表。
  * title：文章标题
  * time：文章上传时间或写作时间
  * url：文章所在运行后端的服务器上的绝对值路径位置
  * type：文章分类
  * intro：文章简介
  * hits：文章点击量
* talk：用于记录说说的数据表，所有talk的内容都将在说说页面进行展示。
* type：用于记录文章分类的数据表。
  * name：文章分类类别名
  * title：文章分类类别名的外文（随便写）
  * intro：文章分类的介绍



## 后端配置数据库访问的账户信息

完成MySQL数据库的构建后，打开后端backend文件夹内的src/main/resources/application.properties，修改内容如下：

```xml
spring.datasource.url=“这里写数据库的访问连接URL”
spring.datasource.username=“这里写数据库的登录用户名”
spring.datasource.password=“这里写数据库的登录密码”
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

关于数据库的访问链接URL，参考如下：

```txt
jdbc:mysql://102.51.7.134:3306/kn?useSSL=false&characterEncoding=utf-8
```

其中，102.51.7.134是数据库所在服务器的地址(此处应该为后端服务器的IP地址)，3306是数据库对外开放的端口，kn是数据库的登录用户名。

其余正常无需修改。



## 网站部署

推荐后端服务器采用宝塔面板进行管理控制，方便使用nginx和端口设置。

检查无误后将后端程序build生成jar，前端打包build得到dist。

:star:关于网站部署具体可以参考我的文章：[前端与后端的部署_雾雨霜星的博客-CSDN博客](https://blog.csdn.net/weixin_44786238/article/details/118686225)



## 数据库内容修改以及修改设置文件

设置文件在"设置文件"文件夹内，均为markdown文件，建议打开Typora进行修改。

关于各个设置文件的内容均已在前文关于数据库表config表中说明。

设置文件的修改按照上述我给出的文件进行参考，对照着修改即可。

数据库中需要修改的部分包括：

*  basic表各个字段内容
* config表中各个配置文件的位置（url字段）
* link表自己填入需要挂的友链
* message表内容可以清空，网站留言板部分接收的内容会上传至该表。
* post表自行填入需要挂载的文章
* talk表是说说部分
* type表按照自己的文章分类来设置即可



## QA汇总(其他注意细节)

1. 博客文章文件采用什么格式？

   统一使用markdown文件，建议使用Typora进行编写。

2. 需要用什么类型的数据库？

   建议采用MySQL数据库，且使用navicat软件进行数据库的管理，因为此博客的更新和设置完全通过数据库进行操控，navicat具备良好的表内容可视性，在管理上较为方便。

3. 数据库应该在哪？

   数据库要建立在后端运行的服务器上，建议在宝塔面板内对数据库的访问权限进行设置：自己电脑访问数据库所用的IP(方便在自己电脑上用navicat进行数据库管理)以及后端服务器的IP。

4. 文章发布的具体流程是什么？

   首先将写好的markdown文件上传到后端服务器，并确定其具体位置。然后在数据库post表内添加记录，需要给出title、简介以及该文件在后端服务器位置的绝对路径。

5. 如何更换网站标签的icon？

   更换前端文件中public/favicon.ico文件即可，更换后重新build生成即可，具体参考前后端部署的文章。

6. 端口号码使用多少？

   可以在宝塔面板安全部分设置开放端口，具体参考前后端部署的文章。默认数据库采用3360，后端使用8080（使用nginx代理）。

7. 后端服务器使用什么操作系统？

   只要可以支持java程序运行都可以，推荐使用centos。

8. 前端所得dist包和后端所得jar包放在后端服务器哪里？

   dist包放在nginx的html文件夹内，jar包位置无所谓，只需要确定位置后使用java将其运行起来就行。具体参考：[前端与后端的部署_雾雨霜星的博客-CSDN博客](https://blog.csdn.net/weixin_44786238/article/details/118686225)

9. 设置文件要放在后端服务器吗？

   设置文件要放在后端服务器上且确定其位置的绝对路径，在数据库config表内对应给出。

10. 如何更换代码高亮的样式？

    在shadow/src/main.js内，注释"引入代码高亮css"下，修改引入的css文件即可。

11. header处想要显示其他内容怎么修改？

    目前header是显示古诗词。只需修改shadow/src/assets/poetry.js内poetys数组内容即可。



## 鸣谢

shadow并非完全由我个人创意所得，前端借鉴了如下项目及资源：

* [Button hover effects with box-shadow (codepen.io)](https://codepen.io/giana/pen/BZaGyP)
* [Terminal Text Effect (codepen.io)](https://codepen.io/Tbgse/pen/dYaJyJ)
* [Negative Triangle SASS Mixin (codepen.io)](https://codepen.io/gionkunz/pen/VwYqva)
* [Cheap AI Chess! (codepen.io)](https://codepen.io/jakealbaugh/pen/JjRGQPY)
* [CSS背景动画-星空 (codepen.io)](https://codepen.io/shuangcs/pen/gKbQEj)
* [Timeline scribble (codepen.io)](https://codepen.io/Fischaela/pen/NWNQKW)
* [GitHub - chanshiyucx/aurora: An awesome blog theme](https://github.com/chanshiyucx/aurora) About页面的设计风格
* [iconfont-阿里巴巴矢量图标库](https://www.iconfont.cn/) 各SVG来源
* [MathJax | Beautiful math in all browsers.](https://www.mathjax.org/) LaTeX公式显示
* [GitHub - highlightjs/highlight.js: JavaScript syntax highlighter with language auto-detection and zero dependencies.](https://github.com/highlightjs/highlight.js)



