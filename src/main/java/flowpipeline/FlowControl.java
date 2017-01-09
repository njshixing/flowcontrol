package flowpipeline;

/**
 * 流程控制器
 * Created by laibao
 */
public interface FlowControl<INPUT , OUTPUT> {

    /**
     * 注册处理器到最后
     * @param handler
     */
    void registerHandlerAtLast(FlowHandler<INPUT , OUTPUT> handler);

    /**
     * 注册处理器到最后最前
     * @param handler
     */
    void registerHandlerAtFirst(FlowHandler<INPUT , OUTPUT> handler);

    /**
     * 在 [before] 处理器前面注册一个处理器 [handler]
     * @param handler
     * @param before
     */
    void registerHandlerAtBefore(FlowHandler<INPUT , OUTPUT> handler , FlowHandler<INPUT , OUTPUT> before);

    /**
     * 在 [after] 处理器后面注册一个处理器 [handler]
     * @param handler
     * @param after
     */
    void registerHandlerAtAfter(FlowHandler<INPUT , OUTPUT> handler , FlowHandler<INPUT , OUTPUT> after);

    /**
     * 就绪
     */
    void ready();

    /**
     * 执行处理
     * @param input 输入
     * @param initOutput 初始输出
     * @return
     */
    void process(INPUT input, OUTPUT initOutput);

}
