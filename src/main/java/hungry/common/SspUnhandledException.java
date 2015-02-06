package hungry.common;

/**
 * SSPにてチェックしない例外
 */
public class SspUnhandledException extends RuntimeException {
    public SspUnhandledException(String message) {
        super(message);
    }
}
