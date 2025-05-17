package bp.difference.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bp.da.DataType;
import bp.difference.SystemConfig;
import bp.web.WebUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import bp.wf.httphandler.WF_Comm;

import java.io.PrintWriter;

@Controller
@RequestMapping("/WF/Comm")
@ResponseBody
public class WF_Comm_Controller extends HttpHandlerBase {

    /**
     * 默认执行的方法
     *
     * @return
     */
//    @RequestMapping(value = "/ProcessRequest")
//    public void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        SystemConfig.setIsBSsystem(true);
//        //处理Token信息
//        try {
//            DealToken();
//        }catch (Exception e) {
//            PrintWriter out = null;
//            try{
//                response.setHeader("content-type", "text/html;charset=UTF-8");
//                response.setCharacterEncoding("UTF-8");
//                out = this.getResponse().getWriter();
//                out.write(e.getMessage());
//            }catch(Exception ex){
//            }finally{
//                if(out!=null)
//                    out.close();
//            }
//        }
//        response.reset();
//        response.setCharacterEncoding("UTF-8");
//        WF_Comm CommHandler = new WF_Comm();
//        if (request instanceof DefaultMultipartHttpServletRequest) {
//            //如果是附件上传Request，则将该Request放入全局Request。为了解决springmvc中全局Request无法转化为附件Request
//            HttpServletRequest request1 = CommonUtils.getRequest();
//            request1.setAttribute("multipartRequest", request);
//        }
//        super.ProcessRequestPost(CommHandler);
//    }

    @RequestMapping(value = "/ProcessRequest")
    public void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SystemConfig.setIsBSsystem(true);

        // 处理Token信息
        try {
            DealToken();
        } catch (Exception e) {
            // 使用参数中的response对象，而不是通过getResponse()获取
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 适当的状态码

            try (PrintWriter out = response.getWriter()) {
                out.write(e.getMessage());
            }
            // 切记，如果已经写入了错误响应，就提前返回，不要继续处理
            return;
        }

        // 只有在token处理成功后才设置响应编码
        response.setCharacterEncoding("UTF-8");

        // 处理文件上传请求
        if (request instanceof DefaultMultipartHttpServletRequest) {
            HttpServletRequest requestContext = CommonUtils.getRequest();
            requestContext.setAttribute("multipartRequest", request);
        }

        // 使用相同的response对象
        super.ProcessRequestPost(new WF_Comm(), request, response);
    }

    @Override
    public Class<WF_Comm> getCtrlType() {
        return WF_Comm.class;
    }

    private void DealToken() throws Exception {
        String doMethod = this.GetRequestVal("DoMethod");
        String doType = this.getDoType();
        if (doType.equals("HttpHandler") == true)
            doType = doMethod;
        if (DataType.IsNullOrEmpty(doType) == true)
            throw new RuntimeException("err@没有获取到执行的方法");

        doType = doType.toLowerCase();
        if (doType.contains("login") == true
                || doType.contains("index")
                || doType.contains("selectoneorg_init")
                || doType.contains("getorgbyno")
                || doType.contains("getorgs")
                || doType.contains("admiin")
                || doType.contains("dbinstall")
                || doType.contains("getdingtalkuserinfo")
                || doType.contains("autologinwithry")
                || doType.contains("default_initry")
                || doType.contains("auto_login")
                || doType.contains("getdingcorpid")
                || doType.contains("getwxappletuserinfo")
                || doType.contains("default_logout")
                || doType.contains("checkencryptenable")
                || doType.contains("getpasswordencryptiontype")
                || doType.contains("getisencryptionkey")
                || doType.contains("ccbpmservices")
                || doType.contains("pcandmobileurl")
                || doType.contains("scanguide_init_vue3")
                || doType.contains("do_init")
                || doType.contains("port_init")
                || doType.contains("portsaas_init")
                || doType.contains("getuserinfo")
                || doType.contains("user_orgnos")
                || doType.contains("sso_init")
                || doType.contains("sso_callback")
                || doType.contains("sso_selectuctoken")
                || doType.contains("sso_jumpLogin")
                || doType.contains("sso_logout")
                || doType.contains("sso_loginbyst"))
            return;
        String token = this.GetRequestVal("Token");
        if (DataType.IsNullOrEmpty(token) == false && (token.equals("undefined") == true || token.equals("null") == true))
            token = "";
        if (DataType.IsNullOrEmpty(token) == false) {
            bp.wf.Dev2Interface.Port_LoginByToken(token);
            return;
        }
        if (DataType.IsNullOrEmpty(WebUser.getNo()) == true) {
            throw new Exception("err@登录信息丢失，请重新登录");
        }


    }


}
