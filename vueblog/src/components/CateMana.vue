<template>
  <el-container>
    <el-header class="cate_mana_header">
      <el-input placeholder="请输入栏目名称" v-model="catename" style="width:200px" size="mini"></el-input>
      <el-button type="primary" size="mini" style="margin-left: 10px" @click="addNewCate">新增栏目</el-button>
    </el-header>
    <el-main class="cate_mana_main">
      <el-table
        ref="multipleTable"
        :data="categories"
        style="width: 100%"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange" v-loading="loading">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          label="编号"
          prop="id"
          width="120">
        </el-table-column>
        <el-table-column
          label="栏目名称"
          prop="catename"
          width="120">
        </el-table-column>
        <el-table-column
        label="启用时间"
        prop="data">
          <i class="el-icon-time"></i>
          <template slot-scope="scope">{{ scope.row.date | formatDate}}</template>
        </el-table-column>
        <el-table-column 
        label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>        
      </el-table>
      <el-button type="danger" :disabled="this.selItems.length==0" style="margin-top: 10px;width: 100px; float: left; "
                 @click="deleteAll" v-if="this.categories.length>0">批量删除
      </el-button>
    </el-main>
  </el-container>
</template>

<script>

export default {
    data(){
        return{
            catename: '',
            selItems: [],
            categories: [],
            loading: false 
        }
    },
    methods:{
      addNewCate(){
        var _this = this;
        this.loading=true;
       this.postRequest("/admin/category/",{catename:this.catename}).then(resp=>{
          if(resp.status == 200){
            var data = resp.data;
            _this.$message({type: data.status, message: data.msg});
            _this.catename='';
            _this.refresh();
          }
          _this.loading=false;
        },resp=>{
          if(resp.response.status == 403){
            var data = resp.response.data;
            _this.$message({type: 'error', message: data});
          }
          _this.loading=false;
        })
      },
      refresh(){
        var _this = this;
        this.getRequest("/admin/category/all").then(resp=>{
          if(resp.status == 200){
            _this.categories=resp.data;
            _this.loading=false;
          }
        },resp=>{
            if(resp.response.status == 403){
            var data = resp.response.data;
            _this.$message({type: 'error', message: data});
          }
          _this.loading=false;
        })
      },
      deleteAll(){
        var _this = this;
        _this.$confirm('确认删除这 ' + _this.selItems.length + ' 条数据?','提示',{
          type:'warning',
          confirmButtonText:'确定',
          cancelButtonText: '取消'
        }).then(()=>{
          var ids='';
          var selItems=_this.selItems;
          for (var i = 0; i < selItems.length; i++) {
            ids += selItems[i].id + ",";
          }
          this.deleteCate(ids.substring(0, ids.length - 1));
        }).catch(()=>{
          _this.loading=false;
        })
      },
      deleteCate(ids){
        var _this = this;
        this.deleteRequest("/admin/category/"+ ids).then(resp=>{
          var data = resp.data;
          _this.$message({type:data.status, message: data.msg});
          _this.refresh();
        },resp=>{
          _this.loading = false;
          if(resp.response.status==403){
            _this.$message({type: 'error', message: data});
          }else if(resp.response.status==500){
            _this.$message({type: 'error', message: '该栏目下尚有文章，删除失败!'});
          }
        })
      },
      handleEdit(index, row) {
        var _this=this;
        this.$prompt('请输入新名称','编辑',{
          confirmButtonText: '更新',
          inputValue: row.catename,
          cancelButtonText: '取消'}).then(({value})=>{
            if(value==null||value.length==0){
              _this.$message({type: 'info', message: '数据不能为空!'});
            }else{
              _this.loading=true;
              this.putRequest("/admin/category/","id="+row.id+"&catename="+value).then(resp=>{
                var data = resp.data
                if(resp.status == 200){
                  _this.$message({type: data.status, message: data.msg})
                  _this.refresh();
                }
                _this.loading=false;
              },resp=>{
                if(resp.response.status == 403){
                  _this.$message({type:'error', message: resp.response.data})
                }
                _this.loading = false;
              })
            }
          })
      },
      handleDelete(index, row) {
        var _this = this;
        this.$confirm('确认删除 ' + row.catename + ' ?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.deleteCate(row.id);
        }).catch(() => {
          //取消
          _this.loading = false;
        });
      },
      handleCurrentChange(val) {
        this.selItems = val;
      },
      handleSelectionChange(val) {
        this.selItems = val;
      },
    },
    mounted: function () {
      this.loading = true;
      this.refresh();
    },
}
</script>

<style>
    .cate_mana_header{
      background-color: #ececec;
      margin-top: 20px;
      padding-left: 5px;
      display: flex;
      align-items: center;
      justify-content: flex-start;
    }
    .cate_mana_main{
      display: flex;
      flex-direction: column;
      padding-left: 5px;
      background-color: #ececec;
      margin-top: 20px;
      padding-top: 10px;
    }
</style>