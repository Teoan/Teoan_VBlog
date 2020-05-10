<template>
  <div>
      <div class="search_input">
        <el-input
            placeholder="通过标题搜索该分类下的博客..."
            prefix-icon="el-icon-search"
            v-model="keywords" size="mini" style="width:400px">
        </el-input>
        <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left:3px" @click="searchClick" >搜索</el-button>
      </div>
    <el-table
        ref="multipleTable"
        :data="articles"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
        type="selection"
        width="35" align="left" v-if="showEdit||showDelete">
        </el-table-column>
        <el-table-column
        label="标题"
        width="400" align="left">
        <template slot-scope="scope">
            <span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">
                {{ scope.row.title}}
            </span>
        </template>
        </el-table-column>
        <el-table-column
        label="最近编辑时间"
        width="140" align="left">
        <template slot-scope="scope">
           {{ scope.row.edittime | formatDateTime}} 
        </template>
        </el-table-column>
        <el-table-column
        prop="nickname"
        label="作者"
        width="120" align="left"> 
        </el-table-column>
        <el-table-column
        prop="cateName"
        label="所属分类"
        width="120" align="left"> 
        </el-table-column>
        <el-table-column
        label="操作"
        align="left" v-if="showEdit||showDelete"> 
            <template slot-scope="scope">
                <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)" v-if="showEdit">编辑
                </el-button>
                <el-button
                size="mini"
                @click="handleRestore(scope.$index, scope.row)" v-if="showRestore">还原
                </el-button>
                <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)" v-if="showDelete">删除
                </el-button>
            </template>
        </el-table-column>
    </el-table>
    <div class="blog_table_footrt">
        <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.articles.length>0 && showDelete"
            :disabled="this.selItems.length==0" @click="deleteMany">批量删除
        </el-button>
        <el-pagination
            background
            :page-size="pageSize"
            layout="prev, pager, next"
            :total="totalCount" @current-change="currentChange" v-show="this.articles.length>0">
        </el-pagination>
    </div>
  </div>
</template>

<script>

export default {
    props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore'],
    data(){
        return{
        articles: [],
        selItems: [],
        loading: false,
        currentPage: 1,
        totalCount: -1,
        pageSize: 6,
        keywords: '',
        dustbinData: []  
        }
    },
    methods:{
        searchClick(){
            this.loadBlogs(1,this.pageSize);
        },
        itemClick(row){
            this.$router.push({path: '/blogDetail', query: {aid: row.id}});
        },
        deleteMany(){
        var selItems = this.selItems;
        for (var i = 0; i < selItems.length; i++) {
          this.dustbinData.push(selItems[i].id)
        }
        this.deleteToDustBin(selItems[0].state)
        },    
        loadBlogs(page,count){
            var url='';
            if(this.state==-2){
                url = "/admin/article/all" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
            }else{
                url = "/article/all?state=" + this.state + "&page=" + page + "&count=" + count + "&keywords=" + this.keywords;
            }
            this.getRequest(url).then(resp=>{
                this.loading=false;
                if(resp.status==200){
                    this.articles = resp.data.articles;
                    this.totalCount = resp.data.totalCount;  
                }else{
                    this.$message({type: 'error', message: '数据加载失败!'});
                }
            },resp=>{
                if (resp.response.status == 403) {
                    this.$message({type: 'error', message: resp.response.data});
                } else {
                    this.$message({type: 'error', message: '数据加载失败!'});
                }                
            }).catch(resp=>{
                this.loading=false;
                this.$message({type: 'error', message: '数据加载失败!'});   
            })
        },
        handleSelectionChange(val) {
            this.selItems = val;
        },
        handleEdit(index, row) {
            this.$router.push({path: '/editBlog', query: {from: this.activeName,id:row.id}});
        },
        handleDelete(index, row) {
            this.dustbinData.push(row.id);
            this.deleteToDustBin(row.state);
        },
        handleRestore(index, row) {
            let _this = this;
            this.$confirm('将该文件还原到原处，是否继续？','提示',{
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            } ).then(() => {
            _this.loading = true;
            this.putRequest('/article/restore', {articleId: row.id}).then(resp=> {
                if (resp.status == 200) {
                var data = resp.data;
                _this.$message({type: data.status, message: data.msg});
                if (data.status == 'success') {
                    window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
                }
                } else {
                _this.$message({type: 'error', message: '还原失败!'});
                }
                _this.loading = false;
            });
            }).catch(() => {
                _this.$message({
                    type: 'info',
                    message: '已取消还原'
                });
            });
        },
        currentChange(currentPage){
            this.currentPage = currentPage;
            this.loading = true;
            this.loadBlogs(currentPage, this.pageSize);
        },
        deleteToDustBin(state){
            var _this = this;
            this.$confirm(state != 2 ? '将该文件放入回收站，是否继续?' : '永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
            _this.loading = true;
            var url = '';
            if (_this.state == -2) {
                url = "/admin/article/dustbin";
            } else {
                url = "/article/dustbin";
            }
            this.putRequest(url, "aids="+_this.dustbinData+"&state="+state).then(resp=> {
                if (resp.status == 200) {
                var data = resp.data;
                _this.$message({type: data.status, message: data.msg});
                if (data.status == 'success') {
                    window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
                }
                } else {
                _this.$message({type: 'error', message: '删除失败!'});
                }
                _this.loading = false;
                _this.dustbinData = []
            }, resp=> {
                _this.loading = false;
                _this.$message({type: 'error', message: '删除失败!'});
                _this.dustbinData = []
            });
            }).catch(() => {
            _this.$message({
                type: 'info',
                message: '已取消删除'
            });
            _this.dustbinData = []
            });
        },
        initView(){
            var _this = this;
            this.loading=true;
            this.loadBlogs(1,this.pageSize);
            window.bus.$on('blogTableReload',function(){
                _this.loading=true;
                _this.loadBlogs(_this.currentPage,_this.pageSize);
            });
        }
    },
    mounted(){
        this.initView();
    }
}
</script>

<style>

.search_input{
    display: flex;
    justify-self:flex-start;
}

.blog_table_footrt{
    display: flex;
    box-sizing: content-box;
    justify-content: space-between;
    margin-top: 10px;
}
</style>