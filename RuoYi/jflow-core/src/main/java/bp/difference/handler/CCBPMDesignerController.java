package bp.difference.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bp.wf.httphandler.WF_Admin_CCBPMDesigner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/WF/Admin/CCBPMDesigner")
@ResponseBody
public class CCBPMDesignerController extends HttpHandlerBase {
    /**
     * 默认执行的方法
     *
     * @return
     */
    @RequestMapping(value = "/ProcessRequest")
    public final void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        WF_Admin_CCBPMDesigner CCBPMDHandler = new WF_Admin_CCBPMDesigner();
        super.ProcessRequestPost(CCBPMDHandler, request, response);
    }

    @Override
    public Class<WF_Admin_CCBPMDesigner> getCtrlType() {
        return WF_Admin_CCBPMDesigner.class;
    }

}
