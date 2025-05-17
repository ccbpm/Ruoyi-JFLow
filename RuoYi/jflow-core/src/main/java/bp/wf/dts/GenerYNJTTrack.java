package bp.wf.dts;

import bp.da.DBAccess;
import bp.da.DataRow;
import bp.da.DataTable;
import bp.difference.SystemConfig;
import bp.en.Method;
import bp.port.Emp;
import bp.port.Emps;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/// <summary>
/// 升级ccflow6 要执行的调度
/// </summary>
public class GenerYNJTTrack extends Method
{
	/**
	 不带有参数的方法
	 */
	public GenerYNJTTrack()
	{
		Title = "生成历史审核数据.";
		Help = "xxxx";
	}
	/**
	 设置执行变量

	 @return
	 */
	@Override
	public void Init()
	{
	}

	/**
	 当前的操纵员是否可以执行这个方法
	 */
	@Override
	public boolean getIsCanDo()  {
		if (bp.web.WebUser.getNo().equals("admin") == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 执行

	 @return 返回执行结果
	 */
	@Override
	public Object Do() throws Exception
	{
		DataTable dt = new DataTable();
		dt.Columns.Add("TableName");
		dt.Columns.Add("TableDesc");
		dt.Columns.Add("FrmID");
		dt.Columns.Add("FlowNos");

		DataRow dr = dt.NewRow();
		dr.setValue("TableName", "pur_contract");
		dr.setValue("TableDesc", "协作合同");
		dr.setValue("FrmID", "249");
		dr.setValue("FlowNos", ",737,735,715,707,676,");
		dt.Rows.add(dr);

		dr = dt.NewRow();
		dr.setValue("TableName", "C_jgcdjmx");
		dr.setValue("TableDesc", "甲供材单价明细");
		dr.setValue("FrmID", "1046");
		dr.setValue("FlowNos", ",743,739,738,714,713,683,578,");
		dt.Rows.add(dr);

		dr = dt.NewRow();
		dr.setValue("TableName", "Def_CLNDJH");
		dr.setValue("TableDesc", "协作单位台账建立表");
		dr.setValue("FrmID", "1129");
		dr.setValue("FlowNos", ",736,675,632,");
		dt.Rows.add(dr);

		dr = dt.NewRow();
		dr.setValue("TableName", "Def_DXLWHZGCJS");
		dr.setValue("TableDesc", "对下劳务合作收方明细表");
		dr.setValue("FrmID", "1136");
		dr.setValue("FlowNos", ",677,638,");
		dt.Rows.add(dr);

		dr = dt.NewRow();
		dr.setValue("TableName", "F_dxgcjstjb");
		dr.setValue("TableDesc", "对下劳务合作收方统计表");
		dr.setValue("FrmID", "1135");
		dr.setValue("FlowNos", ",682,635,");
		dt.Rows.add(dr);

		dr = dt.NewRow();
		dr.setValue("TableName", "F_fenbaojiesuan");
		dr.setValue("TableDesc", "对下计量支付");
		dr.setValue("FrmID", "250");
		dr.setValue("FlowNos", ",684,");
		dt.Rows.add(dr);

		String msg = "";
		for (DataRow mydr : dt.Rows)
		{
			String tableName = mydr.getValue(0).toString();
			String tableDesc = mydr.getValue(1).toString();
			String frmID = mydr.getValue(2).toString();
			String flowNos = mydr.getValue(3).toString();

			msg += "开始处理:" + tableDesc + "," + tableName + "数据.";

			String sql = "SELECT WorkID FROM " + tableName + " WHERE WorkID > 0 ";
			DataTable billDT = DBAccess.RunSQLReturnTable(sql);
			for (DataRow billDR : billDT.Rows)
			{
				String workID = billDR.getValue(0).toString();

				//根据workID找它的流程.
				String flowNo = DBAccess.RunSQLReturnStringIsNull("SELECT FlowNo FROM FlowCurrent WHERE WorkID=" + workID, null);
				if (flowNo == null)
				{
					msg += "err@没有找到流程编号:" + workID;
					continue;
				}

				String ndTrack = "ND" + Integer.parseInt(flowNo) + "Track";

				sql = "SELECT * FROM " + ndTrack + " WHERE WorkID=" + workID + " ORDER BY RDT";
				DataTable track = DBAccess.RunSQLReturnTable(sql);

				String info = "";
				for (DataRow drTrack : track.Rows)
				{
					info += "\t\n 节点:" + drTrack.getValue("NDFromT").toString() + "   处理人:" + drTrack.getValue("EmpFromT").toString() + "    时间:" + drTrack.getValue("RDT").toString();
					info += "\t\n 信息:" + drTrack.getValue("Msg").toString();
					info += "<hr/>";
				}

				sql = "UPDATE " + tableName + " SET History='" + info + "' WHERE  WorkID=" + workID;
				DBAccess.RunSQL(sql);
				msg += " 记录:" + workID + " 增加成功.";
			}
		}
		return "执行信息:" + msg;

	}
}