<template>
<el-form :rules='rules' label-position="left" class="login-container" v-loading="loading" label-width="0px" >
    <h3 class="login_title">登录</h3>
  <el-form-item prop ="account">
    <el-input type="text" v-model="loginFrom.username" auto-complete="off" placeholder="账号"></el-input>
  </el-form-item>
  <el-form-item prop = "checkPass">
    <el-input type="password" v-model="loginFrom.password" auto-complete="off" placeholder="密码"></el-input>
  </el-form-item>
    <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
  <el-form-item style="width:100%" >
     <el-button type="primary" @click="submitClick" style="width:100%">登录</el-button>
  </el-form-item>
</el-form>    
</template>

<script>
export default {
    data(){
        return {
            rules:{
                account:{required:true,message:'请输入用户名',tirgger:'blur'},
                checkPass:{required:true,message:'请输入密码',tirgger:'blur'}
            },
            checked:true,
            loginFrom:{
                username:'',
                password:''
            },
            loading:false
        }
    },
    methods:{
        submitClick: function(){
            console.log();
            var _this=this;
            _this.loading = true;
            var url = 'username='+_this.loginFrom.username+'&password='+_this.loginFrom.password+'&remember-me='+_this.checked;
            this.postRequest('/login',url).then(resp=>{
                if(resp.status==200){
                    //成功
                    var json = resp.data;
                    if(json.status=='success'){
                        _this.$router.replace({path:'/home'});
                        // _this.$router.push({path:'/home',query:{currentUserName:_this.loginFrom.username}});
                    }else{
                        _this.loading=false;
                        _this.$alert('登录失败！','失败！');
                    }
                }else{
                    _this.loading=false;
                    _this.$alert('登录失败！','失败！');
                }
            },resp=>{
                _this.loading=false;
                _this.$alert('找不到服务器⊙﹏⊙∥!', '失败!');
            })
        }
    }
}
</script>

<style>
 .login-container{
     border-radius: 15px;
     background-clip: padding-box;
     margin:180px auto;
     width:350px;
     padding:35px 35px 15px 35px;
     background:#fff;
     border: 1px solid #eaeaea;
     box-shadow: 0 0 25px #cac6c6;
 }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>