package flowpipline.example01;

import flowpipeline.StandardFlowControl;
import flowpipline.example01.handler.CodeVerificationHandler;
import flowpipline.example01.handler.PasswordLevelCheckHandler;
import flowpipline.example01.handler.RequiredParamsCheckHandler;
import flowpipline.example01.handler.UnameConflictCheckHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户注册服务
 * Created by laibao
 */
public class UserRegisterServlet extends HttpServlet {

    /**
     * 用户注册校验器
     */
    private StandardFlowControl<HttpServletRequest , FlowResponse<UserEntity>> registerInfoProcessControl = new StandardFlowControl();

    public void init(ServletConfig config) throws ServletException {
        registerInfoProcessControl.registerHandlerAtLast(new RequiredParamsCheckHandler());
        registerInfoProcessControl.registerHandlerAtLast(new CodeVerificationHandler());
        registerInfoProcessControl.registerHandlerAtLast(new UnameConflictCheckHandler());
        registerInfoProcessControl.registerHandlerAtLast(new PasswordLevelCheckHandler());
        registerInfoProcessControl.ready();
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //初始化信息
        FlowResponse<UserEntity> userInfoResponse = new FlowResponse<UserEntity>();
        userInfoResponse.setData(new UserEntity());

        //用户信息处理
        registerInfoProcessControl.process((HttpServletRequest)req , userInfoResponse);

        if(!userInfoResponse.isSuccess()){
            res.getWriter().write(userInfoResponse.getMsg());
            return ;
        }

        //注册用户
        doRegistUser(userInfoResponse.getData());

        res.getWriter().write("恭喜你，注册成功!");

        //其实用户注册成功后也有一系列操作要处理，比如初始化xxx信息，通知xxx系统。都可以通过一个新的FlowControl组织起来
    }

    /**
     * 执行用户注册(写入用户信息到db)
     * @param userEntity
     */
    private void doRegistUser(UserEntity userEntity){
        // insert userInfo to db
    }


}
