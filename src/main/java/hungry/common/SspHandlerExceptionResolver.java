package hungry.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.UndeclaredThrowableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * SSP用のカスタム例外ハンドラ
 */
public class SspHandlerExceptionResolver implements HandlerExceptionResolver {
    // ロガー
    private static Logger logger = Logger.getLogger(SspHandlerExceptionResolver.class);

    // ログイン画面に遷移する
    // システムエラーの際のメッセージ等を設定する
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) {
        ModelAndView mav = new ModelAndView();

        //スタックとレースの出力
        StringWriter stack = new StringWriter();
        exception.printStackTrace(new PrintWriter(stack));
        logger.error(stack);

        // Exceptionの中身を取り出す処理
        Throwable throwable = exception;
        if ((throwable instanceof UndeclaredThrowableException) && (exception.getCause() != null)) {
            throwable = exception.getCause();
            mav.addObject("error", exception);
            logger.error(exception.getMessage());
        }

        // エラーの遷移先はログイン画面
        mav.setViewName("redirect:" + "/error");
        return mav;
    }
}
