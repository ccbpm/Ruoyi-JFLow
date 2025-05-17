package bp.demo;

import bp.da.DataType;
import bp.tools.HttpClientUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DemoMain {
    public static void main(String[] args) throws IOException {
      String ntag="Bill_VSTO1CongBiao.1.BeiZhu";
      String[] tagArr=ntag.split("\\.");
        System.out.println(tagArr.length);
        System.out.println(Arrays.toString(tagArr));
    }


}
