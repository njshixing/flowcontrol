package flowpipline.example01.handler;

import flowpipeline.FlowContext;
import flowpipeline.FlowHandler;
import flowpipline.example01.FlowResponse;
import flowpipline.example01.UserEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验验证码处理器
 * Created by laibao
 */
public class CodeVerificationHandler implements FlowHandler<HttpServletRequest, FlowResponse<UserEntity>>{

    public void handle(HttpServletRequest request, FlowResponse<UserEntity> response, FlowContext context) {

        String code = response.getData().getCode() ;

        //判断验证码是否与session中的一致
        if(!code.equals(request.getSession().getAttribute("code"))){
            response.setSuccess(false);
            response.setMsg("验证码错误");
            response.setCode(-1);
            context.interrupt();
        }

        //如果上面校验都通过，则执行下一步
        context.doNext();
    }
}
