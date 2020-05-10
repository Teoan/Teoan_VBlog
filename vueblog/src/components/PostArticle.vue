<template>
    <el-container v-loading="loading" class="container">
        <el-header class="header">
          <el-select v-model="article.cid" placeholder="请选择文章栏目" style="width:150px" size="mini">
            <el-option
              v-for="item in categories"
              :key="item.id"
              :label="item.catename"
              :value="item.id">
            </el-option>
          </el-select>
          <el-input v-model="article.title" placeholder="请输入标题..." style="width:400px;margin-left: 10px" size="mini"></el-input>
          <el-tag
          v-for="tag in article.dynamicTags"
          :key="tag"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)" style="margin-left: 10px">
          {{tag}}
          </el-tag>
          <el-input
            class="input-new-tag"
            v-if="tagInputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          >
          </el-input>
          <el-button v-else class="button-new-tag" size="small" @click="showInput" style="margin-left: 10px" type="primary">+ New Tag</el-button>
        </el-header>
        <el-main>
        <div class="editor">
        <mavon-editor style="height: 100%;width: 100%;" ref=md @imgAdd="imgAdd"
                      @imgDel="imgDel" v-model="article.mdcontent"></mavon-editor>
        </div>
        <div class="div">
          <el-button @click="cancelEdit" v-if="from!=undefined">放弃修改</el-button>
          <template v-if="from==undefined || from=='draft'">
            <el-button @click="saveBlog(0)">保存到草稿箱</el-button>
            <el-button type="primary" @click="saveBlog(1)">发表文章</el-button>
          </template>
          <template v-else-if="from=='post'">
            <el-button type="primary" @click="saveBlog(1)">保存修改</el-button>
          </template>
        </div>
        </el-main>
    </el-container>
</template>

<script>
import {uploadFileRequest} from '../utils/api'
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import {isNotNullORBlank} from '../utils/utils'
export default {
  props: ['from','id'],
  data(){
    return{
        categories: [],
        tagInputVisible: false,
        inputValue: '',
        loading: false,
        from:'',
        article: {
          id: '-1',
          dynamicTags: [],
          title: '',
          mdcontent: '',
          cid: ''
        }
    }
  },
  components:{
    mavonEditor
  },

  methods:{
      handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
          this.article.dynamicTags.push(inputValue);
        }
        this.tagInputVisible = false;
        this.inputValue = '';
      },
      showInput() {
        this.tagInputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },
      handleClose(tag) {
        this.article.dynamicTags.splice(this.article.dynamicTags.indexOf(tag), 1);
      },
      cancelEdit(){
        this.$router.go(-1)
      },
      saveBlog(state){
        var _this = this;
        if (!(isNotNullORBlank(this.article.title, this.article.mdcontent, this.article.cid))) {
          this.$message({type: 'error', message: '数据不能为空!'});
          return;
        }
        this.loading=true;
        this.postRequest("/article/",{
          id: _this.article.id,
          title: _this.article.title,
          mdcontent: _this.article.mdcontent,
          htmlcontent: _this.$refs.md.d_render,
          cid: _this.article.cid,
          state: state,
          dynamicTags: _this.article.dynamicTags
        }).then(resp=>{
          _this.loading=false;
          _this.article.id=resp.data.msg;
          _this.$message({type: 'success', message: state == 0 ? '保存成功!' : '发布成功!'});
          window.bus.$emit('blogTableReload');
          if(state==1){
            _this.$router.replace("/articleList");
          }
        },resp=>{
          _this.loading=false;
          _this.$message({type: 'error', message: state == 0 ? '保存草稿失败!' : '博客发布失败!'})
        })
      },
      imgAdd(pos,$file){
        var _this = this;
        // 第一步.将图片上传到服务器.
        var formdata = new FormData();
        formdata.append('image', $file);
        uploadFileRequest("/article/uploadimg", formdata).then(resp=> {
          var json = resp.data;
          if (json.status == 'success') {
//            _this.$refs.md.$imgUpdateByUrl(pos, json.msg)
            _this.$refs.md.$imglst2Url([[pos, json.msg]])
          } else {
            _this.$message({type: json.status, message: json.msg});
          }
        });
      },
      imgDel(pos){

      },
      getCategories(){
        this.getRequest("/admin/category/all").then(resp=> {
          this.categories=resp.data;
        })
      }
  },
  mounted:function(){
    this.getCategories();
    var from = this.$route.query.from;
    this.from=from;
    var _this = this;
    if(from!==null&&from!=undefined&&from!=''){
      var id =this.$route.query.id;
      this.id=id;
      this.loading = true;
      this.getRequest("/article/"+ id).then(resp=> {
        _this.loading=false;
        if(resp.status == 200){
            _this.article = resp.data;
            var tags = resp.data.tags;
            _this.article.dynamicTags = []
            for (var i = 0; i < tags.length; i++) {
              _this.article.dynamicTags.push(tags[i].tagname)
            }
          } else {
            _this.$message({type: 'error', message: '页面加载失败!'})
          }
        }, resp=> {
          _this.loading = false;
          _this.$message({type: 'error', message: '页面加载失败!'})
      })      
    }
  }

}
</script>

<style>
  .container{
    background-color: #ececec;

  }

  .container > .header{
    background-color: #ececec;
    display: flex;
    align-items: center; 
    justify-content: flex-start;
  }

  .div{
    display: flex;
    align-items: center;
    margin-left: 15px;
    justify-content: flex-end;
    margin-top:20px;
  }
  .editor{
    width: 100%;
    height: 450px;
    text-align: left;
  }
  .container >.header>.el-tag + .el-tag {
    margin-left: 10px;
  }
  .container >.header>.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .container >.header>.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>