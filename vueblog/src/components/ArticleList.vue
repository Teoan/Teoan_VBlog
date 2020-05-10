<template>
    <el-container>
        <el-main>
            <el-tabs v-model="activeName" type="border-card">
            <el-tab-pane label="全部文章" name="all">
                <blog_table state="-1" :showEdit="false" :showDelete="false" :showRestore="false" :activeName="activeName"/>
                </el-tab-pane>
            <el-tab-pane label="已发表" name="post">
                <blog_table state="1" :showEdit="true" :showDelete="true" :showRestore="true" :activeName="activeName"/>
                </el-tab-pane>
            <el-tab-pane label="草稿箱" name="draft">
                <blog_table state="0" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"/>
            </el-tab-pane>
            <el-tab-pane label="回收站" name="dustbin">
                <blog_table state="2" :showEdit="false" :showDelete="true" :showRestore="true" :activeName="activeName"/>
            </el-tab-pane>
            <el-tab-pane label="博客管理" name="blogmana" v-if="isAdmin" >
                <blog_table state="-2" :showEdit="false" :showDelete="true" :showRestore="false" :activeName="activeName"/>
            </el-tab-pane>
            <el-tab-pane label="博客配置" name="blogcfg">
                <blog_cfg/>
            </el-tab-pane>
            </el-tabs>
        </el-main>
    </el-container>
</template>

<script>
import BlogTable from "../components/BlogTable";
import BlogCfg from "../components/BlogCfg";

export default {
    mounted(){
        this.getIsAdmin();
    },
    data(){
        return{
            activeName:'all',
            isAdmin:false
        };
    },
    components:{
        'blog_table': BlogTable,
        'blog_cfg': BlogCfg
    },
    methods:{
        getIsAdmin(){
            var _this= this;
            this.getRequest("/isAdmin").then(resp=>{
                if(resp.status==200){
                    _this.isAdmin=resp.data;
                }
            })
        }
    }
}
</script>

<style>

</style>