package live.xsg.authentication.exception;

/**
 * token无效异常类
 * Created by xsg on 2019/12/4.
 */
public class TokenInvalidException extends Exception {

    public TokenInvalidException() {
    }

    public TokenInvalidException(String message) {
        super(message);
    }
}
