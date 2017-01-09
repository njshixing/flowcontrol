package flowpipeline;

/**
 * 流程处理器
 * Created by laibao
 */
public interface FlowHandler<INPUT , OUTPUT> {

    /**
     * 处理流程
     * @param input
     * @param output
     * @param context
     */
    void handle(INPUT input , OUTPUT output , FlowContext context);

}
