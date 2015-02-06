package hungry.common;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * AOPにてログを出力する
 */
@Component
@Aspect
public class LogAspect {

    //ロガー
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * メソッドの実行前後にログを出力する
     * @param point 対象となるポイント
     */
    @Around("execution(* jp.microad.ssp..*.*(..))")
    public Object logArround(ProceedingJoinPoint point) throws Throwable{
        logger.info(point.getTarget().getClass().getName() + "#" + point.getSignature().getName() + " : Start ");
        try {
            Object result = point.proceed();
            logger.info(point.getTarget().getClass().getName() + "#" + point.getSignature().getName() + " : End ");
            return result;
        } catch(IllegalArgumentException e) {
            logger.error("Illegal argument : " + Arrays.toString(point.getArgs())
                    + " in " + point.getTarget().getClass().getName() + "#" + point.getSignature().getName() + " : End ");
            throw e;
        }
    }
}
