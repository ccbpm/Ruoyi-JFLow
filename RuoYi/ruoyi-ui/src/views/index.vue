<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>若依&驰骋:RuoYi-ccfast</h2>
        <!--        <p>-->
        <!--          <ul>-->
        <!--            <li><h3>国外有Flowable,国内有JFlow.</h3> </li>-->
        <!--          </ul>-->
        <!--        </p>-->
        <p>
          <b>当前版本Ruoyi:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger">&yen;开源GPL协议</el-tag>&nbsp;&nbsp;
          <el-tag type="danger">&yen;商务WX:18660153393</el-tag>&nbsp;&nbsp;
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://gitee.com/opencc/RuoYi-JFlow')"
          >Gitee下载</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://ccflow.org/RuoYi.html')"
          >访问主页</el-button>
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('https://docs.qq.com/doc/DRHhsdXBRa2VibVJM')"
          >提Bug兑商业版源</el-button>
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('https://vue3.ccbpm.cn')"
          >纯净版</el-button>
        </p>
        <p>
          <h3>功能介绍:</h3>
          <ol class="content">
            <li>  驰骋低代码研发与2003年，具有.net与java两个版本，这两个版本代码结构，数据库结构，设计思想，功能组成， 操作手册，完全相同。 导入导出的流程模版，表单模版两个版本完全通用。</li>

            <li>我们把驰骋低代码简称ccfast, CCFlow是.net版本的简称，JFlow是java版本的简称，我们未来将要发布python版本的PFlow,敬请关注.</li>
            <li>十多年来，我们一直践行自己的诺言，真心服务中国IT产业，努力提高产品质量，成为了国内知名的老牌工作流引擎。</li>
            <li>ccfast 作简单、概念通俗易懂、操作手册完善（计:40多万操作手册说明书）、代码注释完整、案例丰富翔实、单元测试完整。</li>
            <li>ccfast包含表单引擎与流程引擎两大部分，并且两块完美结合，流程引擎对表单引擎的操纵，协同高效工作, 完成了很多国内生产审批模式下的流程设计,</li>
            <li>ccfast的流程与表单界面可视化的设计，可配置程度高，采用结构化的表单模版设计,集中解析模式的设计. 适应于中国国情的多种场景的需要、配置所见即所得、低代码、高配置.</li>
            <li>ccfast 在国内拥有最广泛的研究群体与应用客户群，是大型集团企业IT部门、软件公司、研究院、高校研究与应用的产品。</li>
            <li>ccfast不仅仅能够满足中小企业的需要，也能满足通信级用户的应用，先后在服役。</li>
            <li>ccfast可以独立运行，也可以作为中间件嵌入您的开发架构,还可以作为服务的模式支持对外发布.</li>
            <li>ccfast 既有配置类型的开发适用于业务人员，IT维护人员， 也有面向程序员的高级引擎API开发,满足不同层次的流程设计人员需要.</li>
            <li>支持信创环境,国产10多个数据库，5000多个配置项，1200多个配置页面。</li>
            <li>流程引擎设计支持所见即所得的设计：节点设计、表单设计、单据设计、报表定义设计、以及用户菜单设计。</li>
            <li>流程模式简洁，只有4种容易理解：线性流程、同表单分合流、异表单分合流、父子流程，没有复杂的概念。</li>
            <li>配置参数丰富，支持流程的基础功能：前进、后退、转向、转发、撤销、抄送、挂起、草稿、任务池共享，也支持高级功能取回审批、项目组、外部用户等等。</li>
          </ol>
          </ul>
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>我们的客户</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <img src="@/assets/images/case.png" alt="light" style="width:100%">
          </el-col>

        </el-row>
      </el-col>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.9"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.content{
  li{
    margin:12px 0;
    font-size: 14px;
  };

}
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
  .el-col-12{
    width: 100%;
  }
}
</style>

