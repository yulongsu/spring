package cn.su.exam;

/**
 * 业务异常
 *
 * @author suyulong
 */
public class BizException extends RuntimeException{

    private final String resultCode;

    private final String resultMsg;

    public BizException(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
