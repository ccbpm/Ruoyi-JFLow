package bp.wf.template;


/**
 流程发起导航方式
*/
public enum StartGuideWay
{
	/**
	 无
	*/
	None(0),
	/**
	 SQL单条模式
	*/
	BySQLOne(1),
	/**
	 SQL多条模式
	 */
	BySQLMulti(2),
	/**
	 历史数据
	 */
	ByHistoryUrl(3),
	/**
	 父子流程模式
	 */
	ByParentFlowModel(4),
	/**
	 按系统的URL-(子父流程)多条模式.
	*/
	SubFlowGuide(5),
	/**
	 按照用户选择的表单.
	 */
	ByFrms(6),
	/**
	 按自定义的Url
	 */
	BySelfUrl(7),
	/**
	 * 按二维码
	 */
	ByQRCode(8),
	/**
	 * 按条码
	 */
	ByBarcode(9),
	/**
	 按自定义的Url
	 */
	ByHTMlText(10),
	/**
	 按系统的URL-(实体记录)单条模式
	*/
	BySystemUrlOneEntity(11),
	/**
	 按系统的URL-(实体记录)多条模式
	*/
	SubFlowGuideEntity(12);


	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, StartGuideWay> mappings;
	private static java.util.HashMap<Integer, StartGuideWay> getMappings()
	{
		if (mappings == null)
		{
			synchronized (StartGuideWay.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, StartGuideWay>();
				}
			}
		}
		return mappings;
	}

	private StartGuideWay(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static StartGuideWay forValue(int value)
	{
		return getMappings().get(value);
	}
}
