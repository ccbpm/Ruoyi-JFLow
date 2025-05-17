var s=Object.defineProperty;var i=(a,t,n)=>t in a?s(a,t,{enumerable:!0,configurable:!0,writable:!0,value:n}):a[t]=n;var e=(a,t,n)=>i(a,typeof t!="symbol"?t+"":t,n);class o{}e(o,"FieldNo",`
    ##### 背景说明
    - ccfast能够识别的格式:No,Name,ParentNo,  说明:No=编码列,Name=标签名称列, ParentNo=父节点的编码.
    - 对方返回的格式不符合,就需要设置转换属性让ccfast识别.
    - 比如: 调用API返回的是 BianHao,MingCheng,FuBianHao列.
    - 就需要在[编号属性No] 属性设置 BianHao,在[名称属性Name] 属性设置 MingCheng
    ##### 概述
    - 把对方的返回的字典格式，通过属性配置转化ccfast能够识别到的格式.
    `),e(o,"Help_TestParas",`
    ##### 参数格式
    - 对于需要参数的数据测试的对象.
    - 单个参数格式: @ParaKey=123
    - 多个参数格式: @ParaKey1=123@ParaKey2=456
    #### 关于别名
    - 参数名词，可以使用别名作为参数.
    - 使用别名前，需要设置别名, 请参考别名设置.
    ##### 说明
     - 数据源的参数属于内置的参数,不需要传入,比如: Token 等参数.
    `),e(o,"Help_PostDoc",`
  ##### POST 内容.
   -  对于Post访问格式有效.
   - 参数格式例如：{"Send":"@WebUser.No","WorkID":@WorkID}
   - 参数格式2： {"Token":"@Token"}   token是数据源的常量，可以在数据源属性里配置.
   - 参数的别名:定义过程时参数的名称定义为@JE,@WorkID,当在使用的时候对应参数的名字可能发生变化这时需要使用到参数别名，例如：
   - @JE=JinE,JiaGe@WorkID=OID等
   #### 什么是调用主体？
  - 使用字典的对象就是调用主体, 调用主体大概是:主表填充、从表填充、数据源实体.
  `),e(o,"Help_APISelectStatement",`
  ##### For: Get格式 
   -  输入完整的名称，地址里允许有参数: /xxx.do?userID=@WebUser.No&tike=@Token&workID=@WorkID&ndID=@NodeID&je=@JinE
   -  参数分为系统参与与自定义参数.
   -  系统参数: 登陆人员的信息,比如:@WebUser.No 登陆人员账号,@WebUser.Name 名称,@WebUser.DeptNo 部门编号,@WebUser.OrgNo 组织编号, @Token token.
   -  自定义参数: &je=@JE   金额是自定义参数.
  ##### For: Post 格式
   - 填写格式：/xxx.do
   - 参数是通过Post内容提供的.
  `),e(o,"Help_APISelectStatementUrl",`
    ##### For: Get格式 
     -  输入完整的名称，地址里允许有参数: http://127.0.0.1:9000/xxx.do?userID=@WebUser.No&tike=@Token&workID=@WorkID&ndID=@NodeID&je=@JinE
     -  参数分为系统参与与自定义参数.
     -  系统参数: 登陆人员的信息,比如:@WebUser.No 登陆人员账号,@WebUser.Name 名称,@WebUser.DeptNo 部门编号,@WebUser.OrgNo 组织编号, @Token token.
     -  自定义参数: &je=@JE   金额是自定义参数.
    ##### For: Post 格式
     - 填写格式：http://127.0.0.1:9000/xxx.do
     - 参数是通过Post内容提供的.
     #### 返回的数据格式要求:
     - 正确执行
      { code:200, message:'执行成功',data:'json or string data' }
     - 错误执行
     { code:500, message:'执行错误',data:'json or string data' }

    `),e(o,"Help_HeaderDoc",`
    ##### 说明
    - 对 post ,get 都有效.
    #####  格式
     - 填写格式：{key1:'xxxx',key2:'x33xxx'}
     #### 实例
     {
        "Token":"@Token",
         "md5": "@md5"
    }
    ##### 特别注意
    - 用半角的双引号.
    `),e(o,"Help_ParamAlia",`
  #### 定义
  - 参数的Key是通用的. Key1与Key2 是相等的，
  - 表达式1: SELECT * FROM XXX WHERE XinShui > @JE AND RecNo='@WebUser.No'
  - 表达式2: /WF/API/Demo_XXX?JE=@JinE&NianLing=@NianLing
  - 在A场景下传入的参数是JE, 在B场景下是JineE, 为了保持两个参数key的通用性。
  - 设置格式: JE=JineE,MyJE,NianLing=Age,NL
  - 使用逗号分开,各个参数. Key=Key1,Key2
    `),e(o,"Help_JsonNode",`
  #### 定义
  - 执行webapi的期间，如果返回的根节点下就是需要获得的数据，那么该属性保持为空.
  - 如果返回的数据在指定的节点下，就需要填写该节点的名字。
  #### 实例
  - 在webApi的字典中返回的结果是  {}  就不需要设置该属性，
  - 若返回的是 contents:{ } 那么数据就在contents节点之下，就需要把 contents 设置到该选项.
    `);export{o as GloDBsrcHelper};
