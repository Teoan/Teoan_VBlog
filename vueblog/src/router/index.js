import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home";
import Login from "../views/Login";
import ArticleList from "../components/ArticleList";
import BlogDetail from "../components/BlogDetail";
import CateMana from "../components/CateMana";
import DataCharts from "../components/DataCharts";
import PostArticle from "../components/PostArticle";
import UserMana from "../components/UserMana";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "登录",
    component: Login,
    hidden:true
  },{
    path: "/home",
    name:"",
    component:Home,
    hidden:true
  },{
    path: "/home",
    component:Home,
    name: "文章管理",
    iconCls: "fa fa-file-text-o",
    children:[{
      path: "/articleList",
      name: "文章列表",
      component:ArticleList,
      meta:{
        keepAlive:true
      }
    },{
      path: "/postArticle",
      name: "发表文章",
      component:PostArticle,
      meta:{
        keepAlive:false
      }
    },{
      path: "/blogDetail",
      name: "博客详情",
      component:BlogDetail,
      hidden:true,
      meta:{
        keepAlive:false
      }
    },{
      path: "/editBlog",
      name: "编辑博客",
      component:PostArticle,
      hidden:true,
      meta:{
        keepAlive:false
      }
    }]
  },{
    path: "/home",
    name: "用户管理",
    component:Home,
    children:[{
      path: "/user",
      name: "用户管理",
      component:UserMana,
      iconCls:"fa fa-user-o"
    }]
  },{
    path: "/home",
    name: "栏目管理",
    component:Home,
    children:[{
      path: "/cateMana",
      name:"栏目管理",
      component:CateMana,
      iconCls:"fa fa-reorder"
    }]
  }
];


const router = new VueRouter({
  // mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
