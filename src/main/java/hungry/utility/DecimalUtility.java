package hungry.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecimalUtility {

	/** DSP設定画面変換形式 **/
	public static final String ZERO_SUPPARESS_SEVEN = "0.#######";
	/** mpressioin設定画面、impression割合保障設定 **/
	public static final String ZERO_SUPPARESS_EIGHT = "0.########";

    public static String getZeroSuppress(String key,BigDecimal target) {
        DecimalFormat format = new DecimalFormat(key);
        return format.format(target);
    }

}
