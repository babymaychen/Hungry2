package hungry.utility;

import java.util.Collection;

/**
 * コレクションを操作するユーティリティクラス
 *
 */
public class CollectionUtility {

    /**
     * コレクションがNullまたは空であるかどうか確認する
     * @param collection 確認対象のコレクション
     * @return true: Nullまたは空の場合、false:それ以外
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }
}
