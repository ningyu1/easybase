package io.ningyu.common.exception;

/**
 * @ClassName: UnauthorizedException
 * @Description: 身份认证异常
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -2804627713269695216L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
