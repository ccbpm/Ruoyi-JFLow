var M=Object.defineProperty;var N=(y,I,e)=>I in y?M(y,I,{enumerable:!0,configurable:!0,writable:!0,value:e}):y[I]=e;var D=(y,I,e)=>N(y,typeof I!="symbol"?I+"":I,e);var x=(y,I,e)=>new Promise((s,a)=>{var c=t=>{try{i(e.next(t))}catch(r){a(r)}},b=t=>{try{i(e.throw(t))}catch(r){a(r)}},i=t=>t.done?s(t.value):Promise.resolve(t.value).then(c,b);i((e=e.apply(y,I)).next())});import _ from"./Entity-Chp-BVny.js";import{P as O,F as u,H as A,G as d,m as p,D as E}from"./entry/index-B5R3Coa4-1746862693206.js";import{GroupField as L}from"./GroupField-lMJeJtcW.js";import{SFDBSrc as B}from"./SFDBSrc-DbkqYXE6.js";import{SFColumns as W,SFColumn as G}from"./SFColumn-1gJai08U.js";import T from"./Events-D9tOL1Ad.js";import{downloadByData as q}from"./download-Cb1ocZ2f.js";import V from"./BSEntities-D1vdB9S4.js";import{Node as H}from"./Node-BsvTqXX9.js";import"./Request-Cs1ZNhZ7.js";import"./form-D-kP1HSJ.js";import"./vue-B6GVRDGm.js";import"./antd-C8r6Ue4p.js";import"./MapAttr-DcWjEeWW.js";import"./EntityOID-C1xznxai.js";import"./MapData-D5zymw8O.js";import"./EnumLab-CsLi93T0.js";import"./base64Conver-t-3tszFb.js";import"./EntityNodeID-3BfNz0DC.js";class le extends O{constructor(){super("GPN_FrmExpImp");D(this,"Imp",`
  #### 帮助
   - 选择的模版文件必须是驰骋表单引擎导出的格式为 .xml 的文件.
   - **导入后会清空当前设计的元素.**
  `);D(this,"ImpNodeFrm",`
  #### 帮助
   - 选择下列节点所绑定的表单进行导入.
   
  `);D(this,"ImpIsReadOnly",`
  #### 帮助
   - 选择从表单库导入的表单是否可编辑.
   
  `);D(this,"TableSrc",`
  #### 帮助
  - 从数据表结构导入字段然后生成表单.
  - 选择表结构.
  `);D(this,"TableSrc_Tables",`
  #### 帮助
  - 选择表.
  `);D(this,"TableSrc_Tables_Fields",`
  #### 帮助
  - 选择字段.
  `);D(this,"ImpEnsFrm",`
  #### 帮助
  - 根据实体类的属性进行导入.
  `);D(this,"Exp",`
  #### 帮助
   - 表单导出.
   - 请点击下一步进行下载.  
  `);D(this,"WordImpHelpUn",`
   #### 帮助
   - 视频教程：https://drive.weixin.qq.com/s?k=AOsAZQczAAY4qqF3E1
   - 此功能使用到了OFFICE API,后台只能发布在安装office或wps的windows系统中
   - 此功能只支持docx操作
   - 通过识别定义的标志对word进行解析操作
       1.自动生成表单
       2.自动生成rtf模版，在运行=》列表 ，双击已生成的数据时 可以在左侧看到“rtf生成文档”打印按钮
   #### 标志介绍
   - .XY （表格标志）
       第一行第一列单元格内容以.XY结尾 (行标题和列标题共同组成主表字段，数据以表单的形式逐个录入)
      ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/xy1.png "屏幕截图.png") 
      ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/xy2.png "屏幕截图.png") 
      ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/xy3.png "屏幕截图.png") 
   - .X  （表格标志）
      第一行第一列单元格内容以.X结尾(行标题组成主表字段，数据以表单的形式逐个录入)
     ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/x1.png "屏幕截图.png") 
   - .T  （表格标志）
      第一行第一列单元格内容以.T结尾 (行标题组成主表字段，数据以表单的形式逐个录入) 
     ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/t1.png "屏幕截图.png") 
   - .DTL （表格标志）  
      第一行第一列单元格内容以.DTL结尾(行标题组成主表字段，数据以表格形式插入多行数据)
      ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/dtl.png "屏幕截图.png") 
   - .* (表格标志)
      以此开头的单元格内容会保留原数据不会处理  
   - #PIC# （图片标志）
      在word文档需要插入图片的地方录入此标志(支持多图片上传)
      ![输入图片说明](./resource/WF/Admin/FrmLogic/ImpExp/pic.png "屏幕截图.png") 
  `);this.PageTitle="模板导入导出"}Init(){return x(this,null,function*(){this.AddGroup("A","模板导入");const e=[{No:"0",Name:"只读"},{No:"1",Name:"可编辑"}],s=this.RequestVal("FlowNo");s&&(this.SelectItemsByList("ImpNodeFrm","从节点上导入",this.ImpNodeFrm,!1,u.SQLOfNodes(s)),this.SelectItemsByList("ImpNodeFrm.IsReadOnly","导入是否只读",this.ImpIsReadOnly,!1,JSON.stringify(e))),this.SelectItemsByGroupList("ImpFlowFrom","从其它流程导入",this.ImpNodeFrm,!1,u.srcFlowSorts,u.srcFlows),this.SelectItemsByGroupList("ImpFrmID","从表单库导入",this.ImpNodeFrm,!1,u.srcFrmTree,u.srcFrmList),this.SelectItemsByList("ImpFrmID.IsReadOnly","导入是否只读",this.ImpIsReadOnly,!1,JSON.stringify(e)),this.TextBox1_Name("ImpFrmIDInput","按表单ID导入","请输入表单ID","表单ID","","如：Frm_XXX"),this.SelectItemsByList("ImpFrmIDInput.IsReadOnly","导入是否只读",this.ImpIsReadOnly,!1,JSON.stringify(e)),this.FileUpload("Imp","导入表单模板","请上传文件",this.Imp),this.AddIcon("icon-magic-wand","Imp"),this.TextBox1_Name("ImpEnsFrm","从实体类导入",this.ImpEnsFrm,"className","","如：BP.Port.Emps"),this.SelectItemsByList("TableSrc","导入表结构",this.TableSrc,!1,u.SQLOfSelectItemsByList),this.AddIcon("icon-calendar","TableSrc"),this.Table("TableSrc.Tables","选择表",this.TableSrc_Tables,!1,this.GenerTables),this.SelectItemsByList("TableSrc.Tables.Fields","选择字段",this.TableSrc_Tables,!0,this.GenerTableFields),this.SelectItemsByGroupList("SFSearch","导入查询",this.TableSrc,!0,u.srcDBSrc,u.srcDBSFSearch,!0),this.AddIcon("icon-magnifier-add","SFSearch"),this.SelectItemsByList("SFSearch.Fields","选择字段",this.TableSrc_Tables,!0,this.GenerSFSearchFields,!0,!1),this.AddGroup("B","Office导入"),this.FileUpload("Excel","Excel表单模板","请上传Excel格式的表单模板","对excel内容作为表单的字段生成"),this.SelectItemsByList("Excel.FieldModel","选择模式",this.ImpNodeFrm,!1,u.SQLOfFieldModel),this.SelectItemsByList("Excel.FieldModel.SelectField","选择字段",this.ImpNodeFrm,!0,this.GenerExcelFields),this.FileUpload("Word","Word表单模板","请上传Word格式的表单模板",this.WordImpHelpUn),this.SelectItemsByList("Word.FrmModel","表单模式",this.ImpNodeFrm,!1,u.SQLOfFrmModel),this.AddGroup("C","表单模板导出","icon-layers"),this.AddBlank("ExpXml","导出xml模板",this.Exp),this.AddBlank("ExpExcel","导出Excel模板",this.Exp),this.AddGroup("D","表单数据导出","icon-directions"),this.AddBlank("ExpDataXml","导出xml格式",this.Exp),this.AddBlank("ExpDataExcel","导出Excel格式数据",this.Exp)})}GenerExcelFields(){return x(this,null,function*(){const e=this.RequestVal("FrmID")||this.RequestVal("PKVal"),s=new A("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_ImpExp");s.AddFile(this.UploadFile),s.AddPara("FrmID",e),s.AddPara("Model",this.RequestVal("tb1","Excel.FieldModel"));const a=yield s.DoMethodReturnString("Imp_ExcelFileds");return JSON.stringify(a)})}GenerTables(){return x(this,null,function*(){const e=this.RequestVal("tb1","TableSrc"),s=new B(e);yield s.RetrieveFromDBSources();const a=yield s.GenerTables();return JSON.stringify(a)})}GenerTableFields(){return x(this,null,function*(){const e=this.RequestVal("tb1","TableSrc"),s=this.RequestVal("tb1","TableSrc.Tables"),a=new B(e);yield a.RetrieveFromDBSources();const c=yield a.GenerTableFields(s);return JSON.stringify(c)})}GenerSFSearchFields(){return x(this,null,function*(){const e=this.RequestVal("tb1","SFSearch"),s=new W;yield s.Retrieve("RefPKVal",e);for(let a=0;a<s.length;a++){const c=s[a];c.No=c.AttrKey,c.Name=c.AttrName}return JSON.stringify(s)})}GenerSorts(){return x(this,null,function*(){return Promise.resolve([])})}Save_TextBox_X(e,s,a,c,b){return x(this,null,function*(){const i=this.RequestVal("FrmID")||this.RequestVal("PKVal");if(e=="Word.FrmModel"){const t=new A("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_ImpExp");t.AddFile(this.UploadFile),t.AddPara("FrmID",i),t.AddPara("FrmModel",a);const r=yield t.DoMethodReturnString("Imp_WordFileSaveIt");return T.emit("reloadForm"),r.includes("err@")?new d(p.Error,r):new d(p.Message,r)}if(e=="Excel.FieldModel.SelectField"){const t=new A("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_ImpExp");t.AddFile(this.UploadFile),t.AddPara("FrmID",i),t.AddPara("Fields",a);const r=yield t.DoMethodReturnString("Imp_ExcelFileSaveIt");return T.emit("reloadForm"),r.includes("err@")?new d(p.Error,r):new d(p.Message,r)}if(e==="Imp"){const t=new A("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_ImpExp");t.AddFile(this.UploadFile),t.AddPara("FrmID",i);const r=yield t.DoMethodReturnString("Imp_LoadFrmTempleteFromLocalFile");return T.emit("reloadForm"),r.includes("err@")?new d(p.Error,r):new d(p.Message,r)}if(e==="ImpNodeFrm.IsReadOnly"||e==="ImpFrmIDInput.IsReadOnly"||e==="ImpFrmID.IsReadOnly"||e==="ImpFlowFrom"){let t,r;if(e==="ImpFrmID.IsReadOnly"&&(t=this.RequestVal("tb1","ImpFrmID"),r=this.RequestVal("tb1","ImpFrmID.IsReadOnly")),e==="ImpFrmIDInput.IsReadOnly"&&(t=this.RequestVal("tb1","ImpFrmIDInput"),r=this.RequestVal("tb1","ImpFrmIDInput.IsReadOnly")),e==="ImpNodeFrm.IsReadOnly"&&(t="ND"+this.RequestVal("tb1","ImpNodeFrm"),r=this.RequestVal("tb1","ImpNodeFrm.IsReadOnly")),e==="ImpFlowFrom"){const l=parseInt(parseInt(a)+"01"),w=new H(l);yield w.Retrieve(),t=w.NodeFrmID?w.NodeFrmID:"ND"+l}const n=new A("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_ImpExp");n.AddPara("FK_MapData",i),n.AddPara("FromFrmID",t),n.AddPara("IsClear",0),n.AddPara("IsSetReadonly",r==="1"?0:1);const f=yield n.DoMethodReturnString("Imp_FromsCopyFrm");return T.emit("reloadForm"),new d(p.Reload,f)}if(e=="ImpEnsFrm"){const t=a,r=new A("BP.WF.HttpHandler.WF_Admin_FoolFormDesigner_ImpExp");r.AddPara("FrmID",i),r.AddPara("EnsName",t);const n=yield r.DoMethodReturnString("Imp_FrmEnsName");return T.emit("reloadForm"),new d(p.Message,n)}if(e=="TableSrc.Tables.Fields"){const t=a,r=c,n=new V("BP.Sys.GroupFields");yield n.Retrieve("FrmID",i);const f=n.getData();let l=0;if(f.length==0){const o=new L;o.Lab="基本信息",o.FrmID=i,o.Idx=1,yield o.DirectInsert(),l=o.OID}else l=f[0].OID;const w=t.split(","),R=r.split(",");for(let o=0;o<w.length;o++){const F=w[o].split("="),P=R[o],S=new _("BP.Sys.MapAttr"),g=`${i}_${F[0]}`;if(S.setPK(g),!(yield S.RetrieveFromDBSources())){const h=S.getData();h.KeyOfEn=F[0],F[1]==="varchar"&&(h.MyDataType=1),F[1]==="int"&&(h.MyDataType=2),h.GroupID=l,h.Idx=o,h.MyPK=g,h.FrmID=i,h.FK_MapData=i,h.Name=P,yield S.Insert()}T.emit("reloadForm")}return new d(p.Message,"导入成功.")}if(e=="SFSearch.Fields"){const t=a,r=c,n=t.split(","),f=r.split(","),l=new G,w=this.RequestVal("tb1","SFSearch");let R="";for(let o=0;o<n.length;o++){const F=n[o],P=f[o];l.MyPK=w+"_"+F,yield l.RetrieveFromDBSources();const S=new _("BP.Sys.MapAttr"),g=`${i}_${F}`;if(S.setPK(g),(yield S.RetrieveFromDBSources())==1){R+="字段:"+F+"已经存在.";continue}l.MyPK=F,yield l.RetrieveFromDBSources();const m=S.getData();m.KeyOfEn=F,l.DataType=="String"&&(m.MyDataType=E.AppString),l.DataType=="Int"&&(m.MyDataType=E.AppInt),l.DataType=="Float"&&(m.MyDataType=E.AppFloat),l.DataType=="DateTime"&&(m.MyDataType=E.AppDateTime),l.DataType=="Date"&&(m.MyDataType=E.AppDate),l.DataType=="Double"&&(m.MyDataType=E.AppDouble),m.Idx=o,m.MyPK=g,m.FrmID=i,m.FK_MapData=i,m.Name=P,yield S.Insert(),R+="字段:"+F+"导入成功."}return T.emit("reloadForm"),new d(p.Message,"导入信息如下:"+R)}if(e==="ExpExcel"){alert("尚未实现.");return}if(e==="ExpXml"){const t=new _("BP.Sys.MapData",i);yield t.Init();const r=new A("BP.WF.HttpHandler.WF_Admin_CCBPMDesigner");r.AddPara("FK_MapData",i);const n=yield r.DoMethodReturnString("DownFormTemplete");if(n.includes("url@")){const f=n.replace("url@","");return new d(p.GoToUrl,f)}return q(n,t.getData().Name+".xml","xml"),T.emit("reloadForm"),new d(p.DoNothing,"")}})}}export{le as GPN_FrmExpImp};
