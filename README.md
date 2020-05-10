# Teoan_VBlog

该项目是本人边学习边实现VBlog项目的内容，目的在于锻炼自己阅读别人代码、学习框架使用的能力，巩固SpringBoot后端的知识、了解前端框架Vue和ElementUI的基本使用流程。了解了SpringSecurity如何进行权限设置和登录请求，以及前后端如何利用axois进行Json对象的数据传输。

## 使用到的技术栈
### 后端
+ 后端服务：SpringBoot
+ 权限管理认证：SpringSecurity
+ 持久层框架：MyBatis
+ 接口：Restful风格
+ 数据库：Mysql

### 前端
+ 前端框架：Vue
+ 数据交互：axios
+ 文章编辑器：mavon-editor
+ 路由：vue-router

## 目录介绍
**blogserver**：后端的SpringBoot项目，可直接用idea打开。运行项目后打开浏览器输入*lcalhost:8081/index.html*访问浏览项目内容。
**vueblog**：前端Vue项目，安装依赖后可用VsCode打开。
**vueblog2.sql**:数据库文件。
## 与原项目的不同
+ Vue CLI的版本从原来的2.x改为新的3.x，主要的的区别在于包名，配置文件,启动脚本名称不同等。具体可以看[Vue CLI官网](https://cli.vuejs.org/zh/guide/installation.html)
+ 删除了数据统计页面，保留与后端数据紧密联系的数据显示页面。
+ 简化的vue项目中axios的封装。
+ 更改的小部分后端的代码。
+ 用户的头像图床链接貌似挂掉了，随便用了一张图替换。

## 项目效果
### 登录界面
![](https://i.imgur.com/dhYcmBt.jpg)

### 文章列表
![](https://i.imgur.com/9UnXMrU.jpg)

### 发表文章
![](https://i.imgur.com/YNdp3Lq.jpg)

### 用户管理
![](https://i.imgur.com/XkdQd3a.jpg)

### 栏目管理
![](https://i.imgur.com/Cuvbyen.jpg)

## 完成过程中遇到的问题以及笔记
[记录一下SpringSecurity的使用过程](http://teoan.ml/2020/05/10/%E8%AE%B0%E5%BD%95%E4%B8%80%E4%B8%8BSpringSecurity%E7%9A%84%E4%BD%BF%E7%94%A8%E8%BF%87%E7%A8%8B/)

[关于spring-security 表单登录参数为null问题](http://teoan.ml/2020/05/04/%E5%85%B3%E4%BA%8Espring-Security-%E8%A1%A8%E5%8D%95%E7%99%BB%E5%BD%95%E5%8F%82%E6%95%B0%E4%B8%BAnul%E9%97%AE%E9%A2%98/)

[关于vue-cli npm run build空白页问题](http://teoan.ml/2020/04/29/%E5%85%B3%E4%BA%8Evue-cli%20npm%20run%20build%E7%A9%BA%E7%99%BD%E9%A1%B5%E9%97%AE%E9%A2%98/)








