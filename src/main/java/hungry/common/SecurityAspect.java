package hungry.common;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
public class SecurityAspect {
    // ロガー
    private final Logger logger = Logger.getLogger(this.getClass());


    /**
     * メソッドの実行前後にログを出力する
     *
     * @param point 対象となるポイント
     */
    @Around("execution(public * jp.microad.ssp.controller..*.*(..))")
    public Object checkArround(ProceedingJoinPoint point) throws Throwable {
        logger.info(this.getClass().getName() + ": " +  point.getTarget().getClass().getName() + "#"
                + point.getSignature().getName() + " : Start ");
        point.getTarget();
//        checkCode(point);
//        checkId(point);
//        escapeForSecurity(point);
        try {
            Object result = point.proceed();
            if (result instanceof ModelAndView) {
//                result = unescapeForSecurity((ModelAndView)result) ;
            }
            logger.info(this.getClass().getName() + ": " + point.getTarget().getClass().getName() + "#"
            + point.getSignature().getName() + " : End ");
            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument : "
                    + Arrays.toString(point.getArgs()) + " in "
                    + this.getClass().getName() + ": "
                    + point.getTarget().getClass().getName() + "#"
                    + point.getSignature().getName() + " : End ");
            throw e;
        }
    }

//    /**
//     * IDをチェックする
//     * @param point ジョインポイント
//     */
//    private void checkId(ProceedingJoinPoint point){
//        WebRequest webRequest = getWebRequest(point);
//        if (webRequest == null) return;
//
//        SspModel model = null;
//        Method method = null;
//        int objIndex = 0;
//        for (Object obj : point.getArgs()) {
//            if (isSspModel(obj)) {
//                model = (SspModel) obj;
//                method = getMethod(point.getTarget().getClass().getDeclaredMethods(),point.getSignature().getName());
//                if (method == null) continue;
//                Class<?>[] clazzes = method.getParameterTypes();
//                for (int i = 0; i < clazzes.length ; i++) {
//                    if (isSspModelName(clazzes[i])) {
//                        objIndex = i;
//                        break;
//                    }
//                }
//                Annotation[][] methods = method.getParameterAnnotations();
//                for (Annotation annotation : methods[objIndex]) {
//                    if (SecurityIdInspector.class.equals(annotation.annotationType())) {
////                        SessionModel sessionModel = (SessionModel) webRequest.getAttribute(model.getSessionModelName(), WebRequest.SCOPE_SESSION);
////                        if (sessionModel == null || !sessionModel.checkId(model)) {
////                            throw new SspUnhandledException("ID is incorrect: " + model.toString());
////                        }
////                        logger.debug("ID Security Check OK: " + point.getTarget().getClass().getName());
//                        return ;
//                    }
//                }
//            }
//        }
//    }

    /**
     * 指定した名前のメソッドを返す
     * @param methods メソッドの一覧
     * @param methodName メソッド名
     * @return 指定した名前のメソッド
     */
    private Method getMethod(Method[] methods, String methodName) {
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }

    /**
     * WebRequestを返す。
     * @param point
     * @return WebRequest
     */
    private WebRequest getWebRequest (ProceedingJoinPoint point) {
        WebRequest webRequest = null;
        for (Object obj : point.getArgs()) {
            if (isWebRequest(obj)) {
                webRequest = (WebRequest) obj;
                break;
            }
        }
        return webRequest;
    }

//    /**
//     * リクエストされた値が不正アクセスデータかどうかチェックする
//     * @param point ジョインポイント
//     */
//    private void checkCode(ProceedingJoinPoint point) {
//        Field[] fields;
//        Annotation[] annotations;
//        SspDto dto = null;
//        String code = null;
//        WebRequest webRequest = getWebRequest(point);
//
//        if (webRequest == null) return;
//        for (Object obj : point.getArgs()) {
//            if (isSspDto(obj)) {
//                fields = obj.getClass().getDeclaredFields();
//                dto = (SspDto) obj;
//                for (Field field : fields) {
//                    annotations = field.getDeclaredAnnotations();
//                    for (Annotation annotation : annotations) {
//                        if (SecurityInspector.class.equals(annotation.annotationType())) {
//                            code = getCode(obj, field, annotation);
//                            if (!StringUtility.isNullOrEmpty(code)) {
//
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * CodeListManagerで管理されているコード一覧に対応する
//     * @param obj
//     * @param field
//     * @param annotation
//     * @return
//     */
//    private String getCode(Object obj, Field field, Annotation annotation) {
//        String code = null;
//        SecurityInspector sec = (SecurityInspector) annotation;
//        try {
//            if (!StringUtility.isNullOrEmpty(sec.code())) {
//                logger.debug("Escape[" + sec.code() + "] " + field.getName());
//                code = getField(field, obj);
//
//                String prop = field.getName();
//                if (StringUtility.isNullOrEmpty(prop)) {
//                    throw new SspException("Field Name is null or empty.");
//                }
//
//                //リストを取得する
//                String mname = "get" + prop.substring(0,1).toUpperCase() + prop.substring(1) +"Map";
//                Method method;
//                Collection<?> result = null;
//                try {
//                    method = codeListManager.getClass().getMethod(mname, new Class[] {});
//                    result = (Collection<?>) method.invoke(obj, new Object[0]);
//                    if(!result.contains(code)) {
//                        throw new SspUnhandledException("");
//                    }
//                } catch (NoSuchMethodException e) {
//                    throw new SspException(e.getMessage());
//                } catch (SecurityException e) {
//                    throw new SspException(e.getMessage());
//                } catch (IllegalAccessException e) {
//                    throw new SspException(e.getMessage());
//                } catch (IllegalArgumentException e) {
//                    throw new SspException(e.getMessage());
//                } catch (InvocationTargetException e) {
//                    throw new SspException(e.getMessage());
//                }
//            }
//        } catch(Exception e) {
//            logger.error(e.getMessage());
//        }
//        return code;
//    }

//    /**
//     * エスケープ処理を施す
//     * @param point ジョインポイント
//     * @throws Exception
//     */
//    private void escapeForSecurity(ProceedingJoinPoint point) throws Exception {
//        for (Object obj : point.getArgs()) {
//            escapeSspSecurity(obj);
//        }
//    }

    /**
     * WebRequestかどうかを確認する
     * @param obj
     * @return true:WebRequestである 、false:WebRequestでない
     */
    private boolean isWebRequest(Object obj) {
        return obj instanceof org.springframework.web.context.request.WebRequest;
    }

//    /**
//     * SspDtoかどうかを確認する
//     * @param obj
//     * @return true: SspDtoである、false: SspDtoでない
//     */
//    private boolean isSspDto(Object obj) {
//        return obj instanceof jp.microad.ssp.dto.SspDto;
//    }
//
//    /**
//     * SspModelかどうかを確認する
//     * @param obj
//     * @return true: SspModelである、false: SspModelでない
//     */
//    private boolean isSspModel(Object obj) {
//        return obj instanceof jp.microad.ssp.model.SspModel;
//    }
//
//    /**
//     * SspModelかどうかを確認する
//     * @param obj
//     * @return true: SspModelである、false: SspModelでない
//     */
//    private boolean isSspModelName(Class<?> clazz) {
//        try {
//            return clazz.newInstance() instanceof jp.microad.ssp.model.SspModel;
//        } catch (InstantiationException | IllegalAccessException e) {
//            return false;
//        }
//
//    }
//
//    /**
//     * 文字列をエスケープする
//     * @param obj エスケープ対象のオブジェクト
//     */
//    private void escapeSspSecurity(Object obj) {
//        Field[] fields;
//        Annotation[] annotations;
//        if (isSspDto(obj)) {
//            fields = obj.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                if (java.lang.String.class.equals( field.getType())) {
//                    annotations = field.getDeclaredAnnotations();
//                    for (Annotation annotation : annotations) {
//                        if (SecurityEscape.class.equals(annotation.annotationType())) {
//                            escape(obj, field, annotation);
//                        }
//                    }
//                }
//            }
//        }
//    }

    /**
     * エスケープ処理を元にもどす
     * @param point ジョインポイント
     * @throws Exception
     */
//    private ModelAndView unescapeForSecurity(ModelAndView mav) {
//        ModelAndView result = new ModelAndView();
//        result.addAllObjects(mav.getModelMap());
//        result.setView(mav.getView());
//
//        ModelMap modelMap = result.getModelMap();
//        Collection<Object> collection = modelMap.values();
//        for (Object value : collection) {
//            if (isSspDto(value)) {
//                escapeSspSecurity(value);
//            } else if (value instanceof java.util.List) {
//                @SuppressWarnings("unchecked")
//                List<Object> list = (List<Object>) value;
//                for (Object obj : list) {
//                    if (isSspDto(value)) {
//                        escapeSspSecurity(obj);
//                    } else if(obj instanceof java.lang.String) {
//                        unescapeForSecurity(mav);
//                    }
//                }
//            }
//        }
//        return mav;
//    }
//
//    /**
//     * 文字列をエスケープする
//     * @param obj 対象のオブジェクト
//     * @param field objのフィールド
//     * @param annotation フィールドに設定されているアノテーション
//     * @throws Exception
//     */
//    private void escape(Object obj, Field field, Annotation annotation) {
//        SecurityEscape sec;
//        sec = (SecurityEscape) annotation;
//        try {
//            switch (sec.escape()) {
//            case SecurityEscape.XXS :
//                logger.debug("Escape[" + sec.escape() + "] " + field.getName());
//                setField(field, obj,StringUtility.escapeXss( getField(field, obj)));
//                break;
//            case SecurityEscape.SQL :
//                logger.debug("Escape[" + sec.escape() + "] " + field.getName());
//                setField(field, obj, StringUtility.escapeSqlInjection( getField(field, obj)));
//                break;
//            case SecurityEscape.CMD :
//                logger.debug("Escape[" + sec.escape() + "] " + field.getName());
//                setField(field, obj, StringUtility.escapeCommand(getField(field, obj)));
//                break;
//            case SecurityEscape.DIR :
//                logger.debug("Escape[" + sec.escape() + "] " + field.getName());
//                setField(field, obj, StringUtility.escapeDirectoryTraversal(getField(field, obj)));
//                break;
//            default:
//                logger.debug("Escape[" + sec.escape() + "] " + field.getName());
//                setField(field, obj,StringUtility.escapeXss( getField(field, obj)));
//        }
//        } catch(Exception e) {
//            logger.error(e.getMessage());
//        }
//    }
//
//    /**
//     * フィールドの値を取得する
//     * @param field objのフィールド
//     * @param obj 対象のオブジェクト
//     * @return objのfieldの値
//     * @throws Exception 例外
//     */
//    private String getField(Field field, Object obj) throws SspException {
//        String prop = field.getName();
//        if (StringUtility.isNullOrEmpty(prop)) {
//            throw new SspException("Field Name is null or empty.");
//        }
//        String mname = "get" + prop.substring(0,1).toUpperCase() + prop.substring(1);
//        Method method;
//        String result = null;
//
//        try {
//            method = obj.getClass().getMethod(mname, new Class[] {});
//            result = (String) method.invoke(obj, new Object[0]);
//        } catch (NoSuchMethodException e) {
//            throw new SspException(e.getMessage());
//        } catch (SecurityException e) {
//            throw new SspException(e.getMessage());
//        } catch (IllegalAccessException e) {
//            throw new SspException(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            throw new SspException(e.getMessage());
//        } catch (InvocationTargetException e) {
//            throw new SspException(e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * フィールドの値を設定する
//     * @param field objのフィールド
//     * @param obj 対象のオブジェクト
//     * @param value 設定する値
//     * @throws Exception 例外
//     */
//    private void setField(Field field, Object obj, String value) throws SspException {
//        String prop = field.getName();
//        if (StringUtility.isNullOrEmpty(prop)) {
//            throw new SspException("Field Name is null or empty.");
//        }
//        String mname = "set" + prop.substring(0,1).toUpperCase() + prop.substring(1);
//        Method method;
//        try {
//            method = obj.getClass().getMethod(mname, new Class[] { String.class });
//            method.invoke(obj, new Object[] { value });
//        } catch (NoSuchMethodException e) {
//            throw new SspException(e.getMessage());
//        } catch (SecurityException e) {
//            throw new SspException(e.getMessage());
//        } catch (IllegalAccessException e) {
//            throw new SspException(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            throw new SspException(e.getMessage());
//        } catch (InvocationTargetException e) {
//            throw new SspException(e.getMessage());
//        }
//    }
}
