package bp.sys.printer.Sdt;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * word中 sdt标签
 */
public class SDTContentControl {
    private XmlObject object = null;

    public SDTContentControl(XmlObject object) {
        this.object = object;
    }

    public String getTitle() {
        if (this.object instanceof CTSdtBlock) {
            CTSdtBlock ctSdtBlock = (CTSdtBlock)this.object;
            if (ctSdtBlock.isSetSdtPr()) {
                if (ctSdtBlock.getSdtPr().isSetAlias()) {
                    return ctSdtBlock.getSdtPr().getAlias().getVal();
                }
            }
        } else if (this.object instanceof CTSdtRun) {
            CTSdtRun ctSdtRun = (CTSdtRun)this.object;
            if (ctSdtRun.isSetSdtPr()) {
                if (ctSdtRun.getSdtPr().isSetAlias()) {
                    return ctSdtRun.getSdtPr().getAlias().getVal();
                }
            }
        } else if (this.object instanceof CTSdtCell) {
            CTSdtCell ctSdtCell = (CTSdtCell)this.object;
            if (ctSdtCell.isSetSdtPr()) {
                if (ctSdtCell.getSdtPr().isSetAlias()) {
                    return ctSdtCell.getSdtPr().getAlias().getVal();
                }
            }
        }
        return null;
    }

    public String getTag() {
        if (this.object instanceof CTSdtBlock) {
            CTSdtBlock ctSdtBlock = (CTSdtBlock)this.object;
            if (ctSdtBlock.isSetSdtPr()) {
                if (ctSdtBlock.getSdtPr().isSetTag()) {
                    return ctSdtBlock.getSdtPr().getTag().getVal();
                }
            }
        } else if (this.object instanceof CTSdtRun) {
            CTSdtRun ctSdtRun = (CTSdtRun)this.object;
            if (ctSdtRun.isSetSdtPr()) {
                if (ctSdtRun.getSdtPr().isSetTag()) {
                    return ctSdtRun.getSdtPr().getTag().getVal();
                }
            }
        } else if (this.object instanceof CTSdtCell) {
            CTSdtCell ctSdtCell = (CTSdtCell)this.object;
            if (ctSdtCell.isSetSdtPr()) {
                if (ctSdtCell.getSdtPr().isSetTag()) {
                    return ctSdtCell.getSdtPr().getTag().getVal();
                }
            }
        }
        return null;
    }

    public String getContentText() {
        XmlObject[] sdtContents = this.object.selectPath(
                "declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' "
                        +".//w:sdtContent");
        for (XmlObject sdtContent : sdtContents) {
            if (sdtContent instanceof XmlObjectBase) {
                return ((XmlObjectBase)sdtContent).getStringValue();
            }
        }
        return null;
    }

    public void setContent(String text) {
        if (this.object instanceof CTSdtBlock) {
            CTSdtBlock ctSdtBlock = (CTSdtBlock)this.object;
            if (ctSdtBlock.isSetSdtContent()) {
                CTSdtContentBlock sdtContentBlock = ctSdtBlock.getSdtContent();
                CTP ctP = sdtContentBlock.getPArray(0); if (ctP == null) ctP = CTP.Factory.newInstance();
                for (int r = ctP.getRList().size()-1; r >= 0 ; r--) ctP.removeR(r);
                CTR ctR = ctP.addNewR();
                if (ctSdtBlock.isSetSdtPr()) {
                    if (ctSdtBlock.getSdtPr().isSetRPr()) {
                        ctR.setRPr(ctSdtBlock.getSdtPr().getRPr());
                    }
                }
                CTText ctText = ctR.addNewT();
                ctText.setStringValue(text);
                sdtContentBlock.setPArray(new CTP[]{ctP});
            }
        } else if (this.object instanceof CTSdtRun) {
            CTSdtRun ctSdtRun = (CTSdtRun)this.object;
            if (ctSdtRun.isSetSdtContent()) {
                CTSdtContentRun sdtContentRun = ctSdtRun.getSdtContent();
                CTR ctR = CTR.Factory.newInstance();
                if (ctSdtRun.isSetSdtPr()) {
                    if (ctSdtRun.getSdtPr().isSetRPr()) {
                        ctR.setRPr(ctSdtRun.getSdtPr().getRPr());
                    }
                }
                CTText ctText = ctR.addNewT();
                ctText.setStringValue(text);
                sdtContentRun.setRArray(new CTR[]{ctR});
            }
        } else if (this.object instanceof CTSdtCell) {
            CTSdtCell ctSdtCell = (CTSdtCell)this.object;
            if (ctSdtCell.isSetSdtContent()) {
                CTSdtContentCell sdtContentCell = ctSdtCell.getSdtContent();
                for (int c = 0; c < sdtContentCell.getTcList().size(); c++) {
                    CTTc ctTc = sdtContentCell.getTcList().get(c);
                    CTP ctP = ctTc.getPArray(0); if (ctP == null) ctP = CTP.Factory.newInstance();
                    for (int r = ctP.getRList().size()-1; r >= 0 ; r--) ctP.removeR(r);
                    CTR ctR = ctP.addNewR();
                    if (ctSdtCell.isSetSdtPr()) {
                        if (ctSdtCell.getSdtPr().isSetRPr()) {
                            ctR.setRPr(ctSdtCell.getSdtPr().getRPr());
                        }
                    }
                    CTText ctText = ctR.addNewT();
                    ctText.setStringValue(text);
                    ctTc.setPArray(new CTP[]{ctP});
                }
            }
        }
    }

    public void setContent(Calendar calendar) {
        String dateFormat = "yyyy-MM-dd";
        if (this.object instanceof CTSdtBlock) {
            CTSdtBlock ctSdtBlock = (CTSdtBlock)this.object;
            if (ctSdtBlock.isSetSdtPr()) {
                if (ctSdtBlock.getSdtPr().isSetDate()) {
                    if (ctSdtBlock.getSdtPr().getDate().isSetDateFormat()) {
                        dateFormat = ctSdtBlock.getSdtPr().getDate().getDateFormat().getVal();
                    }
                    ctSdtBlock.getSdtPr().getDate().setFullDate(calendar);
                }
            }
        } else if (this.object instanceof CTSdtRun) {
            CTSdtRun ctSdtRun = (CTSdtRun)this.object;
            if (ctSdtRun.isSetSdtPr()) {
                if (ctSdtRun.getSdtPr().isSetDate()) {
                    if (ctSdtRun.getSdtPr().getDate().isSetDateFormat()) {
                        dateFormat = ctSdtRun.getSdtPr().getDate().getDateFormat().getVal();
                    }
                    ctSdtRun.getSdtPr().getDate().setFullDate(calendar);
                }
            }
        } else if (this.object instanceof CTSdtCell) {
            CTSdtCell ctSdtCell = (CTSdtCell)this.object;
            if (ctSdtCell.isSetSdtPr()) {
                if (ctSdtCell.getSdtPr().isSetDate()) {
                    if (ctSdtCell.getSdtPr().getDate().isSetDateFormat()) {
                        dateFormat = ctSdtCell.getSdtPr().getDate().getDateFormat().getVal();
                    }
                    ctSdtCell.getSdtPr().getDate().setFullDate(calendar);
                }
            }
        }
        SimpleDateFormat simpledDateFormat = new SimpleDateFormat(dateFormat);
        String text = simpledDateFormat.format(calendar.getTime());
        this.setContent(text);
    }

    public void setContent(BigDecimal value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String text = decimalFormat.format(value.doubleValue());
        this.setContent(text);
    }

    public void setContent(Object content) {
        if (content instanceof String) {
            this.setContent((String)content);
        } else if (content instanceof Calendar) {
            this.setContent((Calendar)content);
        } else if (content instanceof BigDecimal) {
            this.setContent((BigDecimal)content);
//} else if (content instanceof ...) {
            //ToDo
        } else {
            this.setContent(String.valueOf(content));
        }
    }

}