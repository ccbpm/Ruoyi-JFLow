package bp.wf.dts;

import bp.da.*;
import bp.en.*; import bp.en.Map;
import bp.*;
import bp.tools.DateUtils;
import bp.wf.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/** 
 同步待办时间戳 的摘要说明
*/
public class DTS_GenerWorkFlowTimeSpan extends Method
{
	/** 
	 同步待办时间戳
	*/
	public DTS_GenerWorkFlowTimeSpan()
	{
		this.Title = "同步待办时间戳,状态,流程注册表的时间段(本周，上周，2周以前，3其他。).";
		this.Help = "该方法每周一自动执行，如果不能自动执行就手动执行";
		this.GroupName = "流程自动执行定时任务";

	}
	/** 
	 设置执行变量
	 
	 @return 
	*/
	@Override
	public void Init()
	{
		//this.Warning = "您确定要执行吗？";
		//HisAttrs.AddTBString("P1", null, "原密码", true, false, 0, 10, 10);
		//HisAttrs.AddTBString("P2", null, "新密码", true, false, 0, 10, 10);
		//HisAttrs.AddTBString("P3", null, "确认", true, false, 0, 10, 10);
	}
	/** 
	 当前的操纵员是否可以执行这个方法
	*/
	@Override
	public boolean getIsCanDo()
	{
		return true;
	}

	/**
	 执行
	 
	 @return 返回执行结果
	*/
	@Override
	public Object Do() throws Exception {

		LocalDate today = LocalDate.now();
		LocalDate startOfWeek = today.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)); //本周一
		LocalDate endOfWeek = startOfWeek.plusDays(6); //本周日

		LocalDate lastWeekStart = startOfWeek.minusWeeks(1); //上周一
		LocalDate lastWeekEnd = endOfWeek.minusWeeks(1); //上周日

		LocalDate twoWeeksAgoStart = lastWeekStart.minusWeeks(1); //上上周一
		LocalDate twoWeeksAgoEnd = lastWeekEnd.minusWeeks(1); //上上周日

		//默认都设置为本周
		String sql = "UPDATE WF_GenerWorkFlow SET TSpan=" + TSpan.ThisWeek.getValue();
		DBAccess.RunSQL(sql);

		//设置为上周.
		sql = "UPDATE WF_GenerWorkFlow SET TSpan=" + TSpan.NextWeek.getValue() + " WHERE RDT >= '" + lastWeekStart + " 00:00' AND RDT <= '" + lastWeekEnd + " 00:00'";
		DBAccess.RunSQL(sql);

		//把上周的，设置为两个周以前.
		sql = "UPDATE WF_GenerWorkFlow SET TSpan=" + TSpan.TowWeekAgo.getValue() + " WHERE RDT >= '" + twoWeeksAgoStart + " 00:00' AND RDT <= '" + twoWeeksAgoEnd + " 00:00' ";
		DBAccess.RunSQL(sql);

		//把上周的，设置为更早.
		sql = "UPDATE WF_GenerWorkFlow SET TSpan=" + TSpan.More.getValue() + " WHERE RDT <= '" + twoWeeksAgoStart + " 00:00' ";
		DBAccess.RunSQL(sql);

		return "执行成功...";
	}
}
