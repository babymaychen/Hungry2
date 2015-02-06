package hungry.utility;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TimeStampUtility {

    // 1ヶ月あたりの日数(30日)
    public static final int DATE_PER_MONTH_30 = 30;

    // 1日あたりの時間
    private static final int HOUR_PER_DAY = 24;
    // 1時間あたりの分
    private static final int MINUTE_PER_HOUR = 60;
    // 1分あたりの秒
    private static final int SECONDS_PER_MINUTE = 60;

    /**
     * タイムスタンプを比較する
     * @param base 比較元
     * @param compared 比較先
     * @return true: タイムスタンプが異なる場合 ,false:タイムスタンプが一致する場合
     */
    public static boolean isNotSameTime(Timestamp base, Timestamp compared) {
        boolean isNotSameTime = true;

        if (base != null && compared != null && base.equals(compared)) {
            isNotSameTime = false;
        }
        return isNotSameTime;
    }

    /**
     * 時差を補正する
     * @param timeStamp 標準時間
     * @param timeDifference 時差
     * @return 補正後の時間
     */
    public static Timestamp adjustTimeDiffernce(Timestamp timeStamp , BigDecimal timeDifference) {

    	if (timeDifference == null || timeStamp == null) return timeStamp;

    	BigDecimal hour2Millis = new BigDecimal(60L * 60L * 1000L);
    	long time = timeStamp.getTime() + timeDifference.multiply(hour2Millis).longValue() ;
    	return new Timestamp(time);
    }

    /**
     * 指定した日数の秒数を返す
     * @param date 日数
     * @return 秒数
     */
    public static int getSecond(int date) {
        return date * HOUR_PER_DAY * MINUTE_PER_HOUR *SECONDS_PER_MINUTE ;
    }

}
