package flowpipeline;

import java.util.LinkedList;

/**
 * 流程控制器标准实现
 * Created by laibao
 */
public class StandardFlowControl<INPUT , OUTPUT> implements FlowControl<INPUT , OUTPUT>  {

    /**
     * 处理器列表
     */
    private final LinkedList<FlowHandler<INPUT, OUTPUT>> handlers = new LinkedList<FlowHandler<INPUT, OUTPUT>>();

    /**
     * 为了避免对process方法进行同步造成效率损失，这里强制要求先将ready设置成true，后才能使用process方法。并且不能再继续添加处理器
     * 控制器是否初始化完成
     */
    private volatile boolean ready = false;

    public synchronized void registerHandlerAtLast(FlowHandler<INPUT, OUTPUT> handler) {
        ensureIsNotReady();
        handlers.addLast(handler);
    }

    public synchronized void registerHandlerAtFirst(FlowHandler<INPUT, OUTPUT> handler) {
        ensureIsNotReady();
        handlers.addFirst(handler);
    }

    public synchronized void registerHandlerAtBefore(FlowHandler<INPUT, OUTPUT> handler, FlowHandler<INPUT, OUTPUT> before) {
        ensureIsNotReady();
        int beforeIdx = handlers.indexOf(before);
        if(beforeIdx < 0){
            throw new RuntimeException("before handler [" + before + " ] not found");
        }
        handlers.add(beforeIdx , handler);
    }

    public synchronized void registerHandlerAtAfter(FlowHandler<INPUT, OUTPUT> handler, FlowHandler<INPUT, OUTPUT> after) {
        ensureIsNotReady();
        int afterIdx = handlers.indexOf(after);
        if(afterIdx < 0){
            throw new RuntimeException("before handler [" + after + " ] not found");
        }
        afterIdx++;
        handlers.add(afterIdx , handler);
    }

    public synchronized void ready() {
        ensureIsNotReady();
        ready = true ;
    }

    public void process(INPUT input, OUTPUT output) {
        ensureIsReady();
        new StandardFlowContext(input , output).doNext();
    }

    /**
     * 标准流程上下文实现
     */
    private class StandardFlowContext implements FlowContext{

        private int index = -1 ;
        private INPUT input ;
        private OUTPUT output ;

        public StandardFlowContext(INPUT input, OUTPUT output) {
            this.input = input;
            this.output = output;
        }

        public void doNext() {
            index++;
            if(index >= handlers.size()){
                return ;
            }
            FlowHandler<INPUT, OUTPUT> handler = handlers.get(index);
            try {
                handler.handle(input, output , this);
            } catch (FlowInterruptException e) {
                //中断流程
                return ;
            }
        }

        public void interrupt() {
            throw new FlowInterruptException();
        }

    }

    /**
     * 确保控制器是否已初始化完毕
     */
    private void ensureIsReady() throws RuntimeException{
        if(!ready){
            throw new RuntimeException("operate failed , the stat is not ready!");
        }
    }

    /**
     * 确保控制器是否还没初始化
     */
    private void ensureIsNotReady() throws RuntimeException{
        if(ready){
            throw new RuntimeException("operate failed , the stat is ready!");
        }
    }
}
