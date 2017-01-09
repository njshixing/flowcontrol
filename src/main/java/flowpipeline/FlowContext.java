package flowpipeline;

/**
 * 流程上下文
 * Created by laibao
 */
public interface FlowContext {

    /**
     * 执行下一步
     */
    void doNext();

    /**
     * 中断流程
     */
    void interrupt();

}
