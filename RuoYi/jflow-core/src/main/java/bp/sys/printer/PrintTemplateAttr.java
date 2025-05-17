package bp.sys.printer;

import bp.en.EntityMyPKAttr;

/// <summary>
/// 打印模板属性
/// </summary>
public class PrintTemplateAttr extends EntityMyPKAttr {
    /**
     路径
     */
    public static final String Name = "Name";
    /**
     路径
     */
    public static final String TempFilePath = "TempFilePath";
    /**
     NodeID
     */
    public static final String NodeID = "NodeID";
    /**
     流程编号
     */
    public static final String FlowNo = "FlowNo";
    /**
     字段名称
     */
    public static final String KeyOfEn = "KeyOfEn";
    /**
     为生成单据使用
     */
    public static final String Idx = "Idx";
    /**
     单据类型
     */
    public static final String TemplateFileModel = "TemplateFileModel";
    /**
     是否生成PDF
     */
    public static final String PrintFileType = "PrintFileType";
    /**
     二维码生成方式
     */
    public static final String QRModel = "QRModel";
    /**
     文件打开方式
     */
    public static final String PrintOpenModel = "PrintOpenModel";
    /**
     表单的ID
     */
    public static final String FrmID = "FrmID";

    /// <summary>
    /// VSTOword打印模板文件
    /// </summary>
    public static final String VSTOWordPrintFile = "VSTOWordPrintFile";

    /// <summary>
    /// VSTOexcel打印模板文件
    /// </summary>
    public static final String VSTOExcelPrintFile = "VSTOExcelPrintFile";

    public static final String DBFile = "DBFile";


}
