package flowpipline.example01;

import flowpipeline.spring.AbstractFlowControl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by laibao
 */
@Component
public class SpringUserRegistProcessControl extends AbstractFlowControl<HttpServletRequest, FlowResponse<UserEntity>> {

    @Override
    protected List<String> getHandlerBeanIds() {
        return null;
    }

}
