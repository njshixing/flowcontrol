package flowpipline.example01.handler;

import flowpipeline.FlowContext;
import flowpipeline.FlowHandler;
import flowpipline.example01.FlowResponse;
import flowpipline.example01.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 校验用户名是否重复
 * Created by laibao
 */
public class UnameConflictCheckHandler implements FlowHandler<HttpServletRequest, FlowResponse<UserEntity>> {

    private static final List<String> existedUnames = new ArrayList<String>();

    static {
        existedUnames.add("Cherry");
        existedUnames.add("Jerry");
        existedUnames.add("Tiger");
    }

    public void handle(HttpServletRequest request, FlowResponse<UserEntity> response, FlowContext context) {
        String uname = response.getData().getUname();

        //校验用户是否已存在
        if(existedUnames.contains(uname)){
            response.setSuccess(false);
            response.setMsg("用户名已存在，换一个吧~");
            response.setCode(-11);
            context.interrupt(); //中断流程
        }

        //如果上面校验通过，则执行下一步
        context.doNext();
    }

}
