package bp.demo;

import bp.da.DBAccess;
import bp.sys.GEEntity;
import bp.sys.MapData;
import bp.wf.FlowEventBase;
import bp.wf.GenerWorkFlow;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FEE001 extends FlowEventBase
{
	public FEE001() throws Exception {
		super();
	}

	@Override
	public String getFlowMark()
	{
		return "";
	} //这个位置可以写多个流程编号 045，046，如果需要修改状态的都可以在这个位置增加流程编号

	// 重写节点运动事件.
	@Override
	public String SendWhen() {
		return "";
	}

	@Override
	public String SendSuccess() throws Exception {
		GenerWorkFlow gwf  = new GenerWorkFlow(this.getWorkID());
		if (this.getHisNode().getNodeID() == 4501) //如果通用就改成 this.getHisNode().getNodeID() == Integer.parseInt(Integer.parseInt(gwf.getFlowNo())+'01')
		{
			String frmID = this.getHisNode().getNodeFrmID();
			MapData md = new MapData(frmID);
			DBAccess.RunSQL("UPDATE "+md.getPTable()+" SET BillState = 3 WHERE OID="+gwf.getPWorkID());
			DBAccess.RunSQL("UPDATE Frm_GenerBill SET BillState = 3  WHERE WorkID="+gwf.getPWorkID());

		}
		return super.SendSuccess();
	}
	/**
	 * 删除后
	 *
	 * @return
	 */
	@Override
	public String AfterFlowDel()
	{
		return null;
	}

	/**
	 * 删除前
	 *
	 * @return
	 */
	@Override
	public String BeforeFlowDel()
	{
		return null;
	}

	/**
	 * 结束后
	 *
	 * @return
	 */
	@Override
	public String FlowOverAfter() throws Exception {

		GenerWorkFlow gwf  = new GenerWorkFlow(this.getWorkID());
		if (this.getHisNode().getFlowNo().equals("045")) //如果通用if判断可以去掉
		{
			String frmID = this.getHisNode().getNodeFrmID();
			MapData md = new MapData(frmID);
			DBAccess.RunSQL("UPDATE "+md.getPTable()+" SET BillState = 100 WHERE OID="+gwf.getPWorkID());
			DBAccess.RunSQL("UPDATE Frm_GenerBill SET BillState = 100  WHERE WorkID="+gwf.getPWorkID());
		}
		return "";
	}

	public String ReturnAfter() throws Exception {
		GenerWorkFlow gwf  = new GenerWorkFlow(this.getWorkID());
		if (gwf.getNodeID() == 4501) //如果通用就改成 gwf.getNodeID() == Integer.parseInt(Integer.parseInt(gwf.getFlowNo())+'01')
		{
			String frmID = this.getHisNode().getNodeFrmID();
			MapData md = new MapData(frmID);
			DBAccess.RunSQL("UPDATE "+md.getPTable()+" SET BillState = 2 WHERE OID="+gwf.getPWorkID());
			DBAccess.RunSQL("UPDATE Frm_GenerBill SET BillState = 2  WHERE WorkID="+gwf.getPWorkID());
		}
		return "";
	}

	/**
	 * 结束前
	 *
	 * @return
	 */
	@Override
	public String FlowOverBefore()
	{
		return null;
	}

	// 重写事件，完成业务逻辑.
}
