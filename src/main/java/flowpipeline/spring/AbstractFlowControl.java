package flowpipeline.spring;

import flowpipeline.FlowHandler;
import flowpipeline.StandardFlowControl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * 对spring的支持
 * Created by laibao
 */
public abstract class AbstractFlowControl<INPUT , OUTPUT> extends StandardFlowControl<INPUT , OUTPUT> implements
        ApplicationContextAware , InitializingBean{

    private ApplicationContext applicationContext ;

    private List<String> handlerBeanIds ;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
    }

    public void afterPropertiesSet() throws Exception {
        handlerBeanIds = getHandlerBeanIds();
        if(handlerBeanIds == null){
            throw new NullPointerException("handlerBeanIds is null");
        }
        for(String beanId : handlerBeanIds){
            FlowHandler handler = this.applicationContext.getBean(beanId , FlowHandler.class);
            super.registerHandlerAtLast(handler);
        }
        super.ready();
    }

    protected abstract List<String> getHandlerBeanIds();
}
