package flowpipline.example01.handler;

import flowpipeline.FlowContext;
import flowpipeline.FlowHandler;
import flowpipline.example01.FlowResponse;
import flowpipline.example01.UserEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * 密码强弱登录校验
 * Created by laibao
 */
public class PasswordLevelCheckHandler implements FlowHandler<HttpServletRequest, FlowResponse<UserEntity>> {

    public void handle(HttpServletRequest request, FlowResponse<UserEntity> response, FlowContext context) {
        String password = response.getData().getPassword();

        //校验密码长度是否大于8位
        if(password.length() < 8){
            response.setSuccess(false);
            response.setCode(-41);
            response.setMsg("密码长度不能小于8");
            context.interrupt();
        }

        //校验密码是否包含数字
        if(!password.matches("\\d+")){
            response.setSuccess(false);
            response.setCode(-41);
            response.setMsg("密码必需包含数字");
            context.interrupt();
        }

        //校验密码是否包含字母
        if(!password.matches("\\w+")){
            response.setSuccess(false);
            response.setCode(-41);
            response.setMsg("密码必需包含字母");
            context.interrupt();
        }

        //密码不能与用户名相同
        if(password.equals(response.getData().getUname())){
            response.setSuccess(false);
            response.setCode(-41);
            response.setMsg("密码不能与用户名相同");
            context.interrupt();
        }

        //上面校验都通过，继续下一步
        context.doNext();
    }

}
