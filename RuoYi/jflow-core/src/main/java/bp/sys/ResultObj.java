package bp.sys;

/**
 表数据来源类型
*/
public class ResultObj
{
	/// <summary>
/// 代码
/// </summary>
	public int code = 200;
	/// <summary>
/// 消息
/// </summary>
	public String message = "";
	/// <summary>
/// 数据
/// </summary>
	public String data = "";
	public ResultObj() { }

	public String ToSpecString()
	{
		return "{\"code\":"+this.code+ ",\"message\":\""+this.message+"\",\"data\":\""+this.data+"\"}";
	}
}
