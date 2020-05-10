<template>
    <el-card class="card">
        <div style="text-align:left">
            <el-form :model="emailValidateForm" label-position="top" ref="emailValidateForm">
                <el-form-item
                prop="email"
                label="开启博客评论通知"
                :rules="rules.email"
                >
                <el-input type="email" placeholder="请输入邮箱地址..." size="mini" v-model="emailValidateForm.email" style="width:300px"/>
                <el-button type="primary" size="mini" @click="submitForm('emailValidateForm')" style="margin-left: 10px">确定</el-button>
                </el-form-item>
            </el-form>
        </div>
    </el-card>

</template>

<script>
export default {
    data(){
        return{
            emailValidateForm: {
            email: ''
            },
            loading: false ,
            rules:{
                email:[{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]
            }           
        }
    },
    mounted:function(){
        this.getRequest("/currentUserEmail").then(resp=>{
            if(resp.status == 200){
                this.emailValidateForm.email=resp.data;
            }
        });
    },
    methods:{
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.loading=true;
                this.putRequest("/updateUserEmail",{email:this.emailValidateForm.email}).then(resp=>{
                    this.loading=false;
                    if(resp.status == 200){
                        this.$message({type: resp.data.status, message: resp.data.msg});
                    }else{
                        this.$message({type: 'error', message: '开启失败！'});
                    }
                },resp=>{
                    this.loading=false;
                    this.$message({type: 'error', message: '开启失败！'});
                })
            } else {
                this.$message({type: 'error', message: '邮箱格式不正确！'});
                return false;
            }
            });
        },
    }
}
</script>

<style>
.card{
    width: 500px;
}
.from{
    color: #20a0ff;
    font-size: 14px;
}
</style>