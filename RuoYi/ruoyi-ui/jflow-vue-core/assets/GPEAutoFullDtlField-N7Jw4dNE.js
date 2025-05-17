var u=Object.defineProperty;var n=(a,t,e)=>t in a?u(a,t,{enumerable:!0,configurable:!0,writable:!0,value:e}):a[t]=e;var r=(a,t,e)=>n(a,typeof t!="symbol"?t+"":t,e);import{l as o,U as i,f as p,F as l}from"./entry/index-B5R3Coa4-1746862693206.js";import{a as s}from"./MapExt-DVovzpWn.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./Events-D9tOL1Ad.js";import"./SFTable-BpxUt1jb.js";import"./SFDBSrc-DbkqYXE6.js";class f extends o{constructor(e){super("TS.MapExt.GPEAutoFullDtlField");r(this,"JSDesc",` 
  #### 说明
  计算后要触发的脚本函数(比如:求和以后要激活的function)，该脚本要求写入到:DataUserJSLabMyFromID_Self.js
   `);r(this,"DescTag1",` 
   #### 说明
   - zhoupeng 补充
    `);r(this,"DescDoc",` 
  #### 说明
  - SQL格式为:
  - SELECT No,Name FROM Port_Emp WHERE PinYin LIKE '%@Key%' OR No LIKE '%@Key%' OR Name LIKE '%@Key%' 
  - SELECT No,Name FROM CN_City WHERE PinYin LIKE '%@Key%' OR Name LIKE '%@Key%'
  - URL格式为:
  - /DataUser/Handler.ashx?xxx=sss 
  - 方法的格式为:
  - MyFunName
   `);e&&(this.MyPK=e)}get HisUAC(){const e=new i;return e.IsDelete=!0,e.IsUpdate=!0,e.IsInsert=!0,e}get EnMap(){const e=new p("Sys_MapExt","对从表列求值");return e.AddGroupAttr("基本设置"),e.AddMyPK(),e.AddDDLSQL(s.Doc,null,"从表",l.SQLOfAutoFullDtlField,!0),e.AddTBString(s.FK_MapData,null,"对应的表单",!1,!1,0,50,200,!0,this.DescDoc),e.AddTBString(s.Tag1,null,"输入列",!0,!1,0,50,200,!0,this.DescTag1),e.SetPopList("Tag1",l.sqlMapAttrNumberFields,!1,"400px","300px","选择字段","icon-people"),e.AddBoolean("Tag2",!1,"计算后要触发的脚本函数",!0,!0,!0,this.JSDesc),e.AddDDLStringEnum(s.Tag,"Sum","计算方式","@Sum=求和@Avg=求平均@Max=求最大@Min=求最小",!0,null,!1),e.AddBoolean("Tag3",!1,"对其它只读string字段进行大写转换",!0,!0,!0,this.JSDesc),e.AddTBString(s.Tag4,null,"只读字段名",!0,!1,0,50,200,!0,this.DescTag1),e.AddTBString(s.Tag5,null,"计算过滤表达式",!0,!1,0,50,200,!0,`##### 帮助
    - 过滤表达式是: 从表的字段名=值, 比如:SRType=1
    - 比如从表里有一个枚举字段收入类型(SRType)：营业收入=0，非营业收入=1. 一个数值字段，收入ShouRu:
    - 主表有两个字段数值类型字段：营业收入, 非营业收入, 要对从表列进行求和，这个场景就需要借助表达式.
    - 支持:  SRType = 001,002,003 格式,多个值使用逗号拼接.

    `),this._enMap=e,this._enMap}}export{f as GPEAutoFullDtlField};
