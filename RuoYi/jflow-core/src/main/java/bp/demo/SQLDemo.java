package bp.demo;

import bp.sys.CCBPMRunModel;
import bp.web.WebUser;

import java.util.Hashtable;

public class SQLDemo {
    public SQLDemo() { }

    public static String DemoSQL(String mark, Hashtable ht ) throws Exception {
        //学生类的SQL
        if (mark.startsWith("DemoStudent_Student") == true)
            return Demo_Student(mark, ht);

        //大屏类的sql.
        if (mark.startsWith("DemoStudent_DataV_StudentHome") == true)
            return DataV_StudentHome(mark, ht);

        throw new Exception("err@没有判断的SQL标记:" + mark);
    }
    public static String Demo_Student(String mark, Hashtable ht ) throws Exception {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key = ht.get("Key").toString();

        switch (mark)
        {
            case "DemoStudent_Student_BanJi": //班级.
                return "SELECT  No,Name FROM Demo_BanJi ";
            case "DemoStudent_Student_KeMu": //科目.
                return "SELECT  No,Name FROM Demo_KeMu ";
            case "DemoStudent_Student_BanJiList": //班级列表.
                return "SELECT No,Name, BZR as '班主任', Tel as '电话' FROM Demo_BanJi ";
            case "DemoStudent_Student_SearchCity": //城市.
                return "SELECT No,Name FROM Demo_City WHERE ShengFen='" + key + "' ";
            case "DemoStudent_Student_BanJiFind": //查询班级根据关键字.
                return "SELECT No,Name FROM Demo_BanJi WHERE No LIKE '%" + key + "%' OR Name LIKE '%" + key + "%'  ";
            case "DemoStudent_Student_FullTeacher": //班级填充DDL.
                return "SELECT BZR as TeacherName, Tel as TeacherTel FROM Demo_BanJi WHERE No='" + key + "' ";
            case "DemoStudent_Student_FullTBBanJi": //班级填充TB.
                return "SELECT BZR as TBBZR, Tel as TBTel FROM Demo_BanJi WHERE No='" + key + "' ";
            case "DemoStudent_Student_PopBanJiFull": //班级填充Pop.
                return "SELECT BZR as PopBZR, Tel as PopTel FROM Demo_BanJi WHERE No='" + key + "' ";
            default:
                break;
        }

        throw new Exception("err@没有判断的SQL标记:" + mark);
    }
    public static String DataV_StudentHome(String mark, Hashtable ht) throws Exception {
        String key = null;
        if (ht != null && ht.containsKey("Key") == true)
            key =  ht.get("Key").toString();

        switch (mark)
        {
            case "DemoStudent_DataV_StudentHome_AllStus": //学生总数.
                return "SELECT count(*) as Num FROM Demo_Student";
            case "DemoStudent_DataV_StudentHome_ZZMM1": //团员数.
                return "SELECT count(*) as Num FROM Demo_Student WHERE ZZMM=1";
            case "DemoStudent_DataV_StudentHome_XBNum": //求性别数据.
                return "SELECT Count(*) as Num FROM Demo_Student WHERE XB='" + key + "' GROUP BY XB ";
            case "DemoStudent_DataV_StudentHome_GroupXB": //性别分布.
                return "SELECT XB, Count(*) as Num FROM Demo_Student GROUP BY XB ";
            case "DemoStudent_DataV_StudentHome_GroupZZMM": //政治面貌.
                return "SELECT ZZMM, Count(*) as Num FROM Demo_Student GROUP BY ZZMM ";
            default:
                break;
        }
        throw new Exception("err@没有判断的SQL标记:" + mark);
    }
}
