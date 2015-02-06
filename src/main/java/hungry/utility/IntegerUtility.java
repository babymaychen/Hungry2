package hungry.utility;

public class IntegerUtility {

    /**
     * IntegerがNULLまたは0かどうかをチェックする
     * @param target チェック対象の文字列
     * @return チェック結果 <br/>
     *              true: Nullまたは0である<br/>
     *              false: Nullまたは0ではない<br/>
     */
    public static boolean isNullOrZero(final Integer target) {
        if (target == null || target==0 ) {
            return true;
        } else {
            return false;
        }
    }}
