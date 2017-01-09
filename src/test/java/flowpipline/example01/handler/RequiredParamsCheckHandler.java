package flowpipline.example01.handler;

import flowpipeline.FlowContext;
import flowpipeline.FlowHandler;
import flowpipline.example01.FlowResponse;
import flowpipline.example01.UserEntity;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 基本参数校验器
 * Created by laibao
 */
public class RequiredParamsCheckHandler implements FlowHandler<HttpServletRequest, FlowResponse<UserEntity>> {

    public void handle(HttpServletRequest request, FlowResponse<UserEntity> response, FlowContext context) {
        String code = request.getParameter("code");
        String uname =  request.getParameter("uname");
        String password = request.getParameter("password");
        if(StringUtils.isBlank(code)){
            response.setSuccess(false);
            response.setMsg("验证码不能为空");
            response.setCode(-1);
            context.interrupt();
        }
        if(StringUtils.isBlank(uname)){
            response.setSuccess(false);
            response.setMsg("用户名不能为空");
            response.setCode(-2);
            context.interrupt();
        }
        if(StringUtils.isBlank(password)){
            response.setSuccess(false);
            response.setMsg("密码不能为空");
            response.setCode(-3);
            context.interrupt();
        }

        //设置用户信息
        UserEntity userEntity = response.getData();
        userEntity.setCode(code);
        userEntity.setUname(uname);
        userEntity.setPassword(password);
        userEntity.setIp(request.getRemoteAddr());

        //校验通过，下一步
        context.doNext();
    }
}
