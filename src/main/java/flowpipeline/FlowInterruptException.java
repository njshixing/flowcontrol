package flowpipeline;

/**
 * 流程中断异常
 * Created by laibao
 */
public class FlowInterruptException extends RuntimeException{

    public FlowInterruptException() {
    }

    public FlowInterruptException(String message) {
        super(message);
    }

    public FlowInterruptException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlowInterruptException(Throwable cause) {
        super(cause);
    }

    public FlowInterruptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
