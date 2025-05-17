package bp.sys.printer.Sdt;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import javax.xml.namespace.QName;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

//word中 sdt标签处理
public class WordFillContentControls {

    public static List<SDTContentControl> extractSDTsFromBody(XWPFDocument document) {
        SDTContentControl sdt;
        XmlCursor xmlcursor = document.getDocument().getBody().newCursor();
        QName qnameSdt = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdt", "w");
        List<SDTContentControl> allsdts = new ArrayList<SDTContentControl>();
        while (xmlcursor.hasNextToken()) {
            XmlCursor.TokenType tokentype = xmlcursor.toNextToken();
            if (tokentype.isStart()) {
                if (qnameSdt.equals(xmlcursor.getName())) {
                    if (xmlcursor.getObject() instanceof XmlObject) {
                        sdt = new SDTContentControl((XmlObject)xmlcursor.getObject());
                        allsdts.add(sdt);
                    }
                }
            }
        }
        return allsdts;
    }

    public static void main(String[] args) throws Exception {

        String[] contentControlTags = new String[]{
                "NameTag", "GenderTag", "DateTag", "AmountTag",
                "DescriptionTag", "Col1Tag", "Col2Tag",
                "Col1DateTag", "Col2ChooseTag"
        };
        Object[] contents = new Object[]{
                "Axel Richter", "male", new GregorianCalendar(2022, 0, 1), BigDecimal.valueOf(1234.56),
                "Lorem ipsum semit dolor ... dolor semit ...", "Blah blah", "Blubb blubb",
                new GregorianCalendar(1964, 11, 21), "My choice"
        };

        XWPFDocument document = new XWPFDocument(new FileInputStream("D:\\company\\杂\\Bill_VSTO1.docx"));

        List<SDTContentControl> allsdts = extractSDTsFromBody(document);

        for (SDTContentControl sdt : allsdts) {
//System.out.println(sdt);
            String title = sdt.getTitle();
            String tag = sdt.getTag();
            String content = sdt.getContentText();
            System.out.println(title + ": " + tag + ": " + content);
            sdt.setContent(tag + " content");
//            for (int i = 0; i < contentControlTags.length; i++) {
//                String tagToReplace = contentControlTags[i];
//                if (tagToReplace.equals(tag)) {
//                    Object contentO = contents[i];
//                    sdt.setContent(contentO);
//                }
//            }

        }

        allsdts = extractSDTsFromBody(document);

        for (SDTContentControl sdt : allsdts) {
            String title = sdt.getTitle();
            String tag = sdt.getTag();
            String content = sdt.getContentText();
            System.out.println(title + ": " + tag + ": " + content);
        }

        FileOutputStream out = new FileOutputStream("D:\\company\\杂\\Bill_VSTO1.docx");
        document.write(out);
        out.close();
        document.close();
    }
}