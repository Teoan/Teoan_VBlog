<template>
  <el-container class="home_container">
    <el-header class="header">
      <el-row :gutter="10" type="flex">
        <el-col :span="12"><div class="title">V部落博客管理平台</div></el-col>
        <el-col :span="12">
          <div class="userinfoContainer">
            <el-dropdown @command="handleCommand">
              <span class="el-dropdown-link userinfo">
                <i class="el-icon-setting" style="margin-right: 15px"></i>
                {{currentUserName}}<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="sysMsg" icon="el-icon-s-tools" >系统消息</el-dropdown-item>
                <el-dropdown-item command="MyArticle" icon="el-icon-document">我的文章</el-dropdown-item>
                <el-dropdown-item command="MyHome" icon="el-icon-user">个人主页</el-dropdown-item>
                <el-dropdown-item command="logout" icon="el-icon-error" divided>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="0"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          :router="true">
          <template v-for="(item,index) in this.$router.options.routes">
            <template v-if="!item.hidden">
              <el-submenu :index="index+''" v-if="item.children.length>1" :key="index">
                <template slot="title">
                  <i :class="item.iconCls"></i>
                  <span>{{item.name}}</span>
                </template>
                <el-menu-item-group>
                  <template v-for="child in item.children">
                    <el-menu-item v-if="!child.hidden" :index="child.path" :key="child.path">
                      {{child.name}}
                    </el-menu-item>
                  </template>
                </el-menu-item-group>
              </el-submenu>
              <template v-else>
                <el-menu-item :index="item.children[0].path" :key="item.children[0].path">
                  <template slot="title">
                    <i :class="item.children[0].iconCls"></i>
                    <span>{{item.children[0].name}}</span>
                  </template>
                </el-menu-item>
              </template>
            </template>  
          </template>
        </el-menu>        
      </el-aside>
      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
        </el-breadcrumb>
          <keep-alive>
            <router-view v-if="this.$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!this.$route.meta.keepAlive"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  methods:{
    handleCommand(command){
      var _this= this;
      if(command=='logout'){
        _this.$confirm('注销登录吗？','提示',{
          confirmBottonText:'确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function(){
          _this.getRequest("/logout");
          _this.currentUserName='游客';
          _this.$router.replace({path:'/'});
        },function(){
          //取消
        })
      }
    }

  },
  mounted: function () {
        var _this= this;
        this.getRequest("/currentUserName").then(function(msg){
          _this.currentUserName=msg.data;
        },function(msg){
          _this.currentUserName = '游客';
        });
  },
  data(){
    return{
      currentUserName: 'Teoan'
    }
  }

};
</script>

<style>
  .title {
    color: #fff;
    font-size: 22px;
    display: flex;
    align-items: center;
    height: 60px;
  }
  .header {
    background-color: #20a0ff;
    height: 60px;
  }

  .userinfoContainer {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    height: 60px;
  }

  .el-aside{
    background-color:#545c64;
  }
  .userinfo {
    color: #fff;
    cursor: pointer;
  }
  .home_container{
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;  
  }
</style>
