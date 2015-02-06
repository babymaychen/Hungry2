package hungry.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

/**
 * 文字列を操作するユーティリティクラス
 *
 */
public class StringUtility {

    private static Logger logger = Logger.getLogger(StringUtility.class);

    public static final String EMPTY = "";

    /**
     * 文字列に含まれるHTML文字列をエスケープする
     * @param target エスケープ対象の文字列
     * @return HTML文字列をエスケープした文字列
     */
    public static String escapeHtml(final String target) {
        return StringEscapeUtils.escapeHtml4(target);
    }

    /**
     * エスケープしたHTML文字列を元のHTML文字列に戻す
     * @param escapedString エスケープされた文字列
     * @return エスケープを元のHTML文字列に戻した文字列
     */
    public static String unescapeHtml(final String escapedString) {
        return StringEscapeUtils.unescapeHtml4(escapedString);
    }

    /**
     * 文字列に含まれるJSON文字列をエスケープする
     * @param target エスケープ対象の文字列
     * @return JSON文字列をエスケープした文字列
     */
    public static String escapeJson(final String jsonString) {
        return StringEscapeUtils.escapeJson(jsonString);
    }

    /**
     * エスケープしたJSON文字列を元のJSON文字列に戻す
     * @param escapedString エスケープされた文字列
     * @return エスケープを元のJSON文字列に戻した文字列
     */
    public static String unescapeJson(final String escapedJsonString) {
        return StringEscapeUtils.unescapeJson(escapedJsonString);
    }

    /**
     * 文字列がNULLまたは空文字列(半角スペースのみの場合も空文字列を扱う)
     * @param target チェック対象の文字列
     * @return チェック結果 <br/>
     *              true: Nullまたはから文字列である<br/>
     *              false: Nullまたはから文字ではな<br/>
     */
    public static boolean isNullOrEmpty(final String target) {
        if (target == null || target.trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文字列が数字のみであるかを調べる
     * @param target
     * @return true:数字である 、false:数字以外が含まれている
     */
    public static boolean isDigit(String target) {
        if (isNullOrEmpty(target)) return false;
        return Pattern.compile("^[0-9]*$").matcher(target).find();
    }

    /**
     * 文字列が数字のみであるかを調べる
     * @param target
     * @return true:数字である 、false:数字以外が含まれている
     */
    public static boolean isDecimal(String target) {
        if (isNullOrEmpty(target)) return false;
        return Pattern.compile("^[0-9].[0-9]$").matcher(target).find();
    }


    /**
     * 文字列が日付フォーマット("yyyy/MM/dd")であるかを調べる
     * @param target
     * @return true:日付フォーマットである 、false:日付フォーマットでない
     */
    public static boolean isDateFormat(String target) {
        if (isNullOrEmpty(target)) return false;
        return Pattern.compile("^[0-9]{4}/[0-9]{2}/[0-9]{2}").matcher(target).find();
    }

    /**
     * 文字列("yyyy/MM/dd")を日付(java.util.Date)に変換する。
     * @param dateStr
     * @return 変換したDate
     */
    public static Date parseToDate(String dateStr) {
        Date date = null;
        if (StringUtility.isDateFormat(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                date = sdf.parse(dateStr);
            }
            catch (ParseException e) {
                //事前チェック済みのため到達しない
            }
        }
        return date;
    }

    /**
     * 文字列が16進数表記であるかを調べる
     * @param target
     * @return true:数字である 、false:数字以外が含まれている
     */
    public static boolean isHex(String target) {
        if (isNullOrEmpty(target)) return false;
        return Pattern.compile("^[0-9,a-f,A-F]*$").matcher(target).find();
    }

    /**
     * ２つの文字列を比較する
     * @param target 比較する文字列
     * @param compared 比較する文字列
     * @return true:一致、false:一致しない
     */
    public static boolean equals(String target, String compared) {
        if (target == null || compared == null) {
            return false;
        }
        if (target.equals(compared)) {
            return true;
        }
        return false;
    }


    /**
     * 文字列のIPアドレスを数値(Long)に変換する
     * @param ipAddressStr 文字列のIPアドレス
     * @return 数値のIPアドレス
     */
    public static Long convertIpAddressToInteger(String ipAddressStr) {
    	if (isNullOrEmpty(ipAddressStr) ) return 0L;
    	String[] ipAddressArray = ipAddressStr.split("\\.");
    	if (ipAddressArray.length != 4) return 0L;
    	long[] ipArrayInt = new long[ipAddressArray.length];
    	for (int i = 0 ; i < ipAddressArray.length; i++) {
    		if (isDigit(ipAddressArray[i])) {
    			ipArrayInt[i] = Integer.parseInt(ipAddressArray[i]);
    			if (ipArrayInt[i] < 0 || ipArrayInt[i] > 255) return 0L;
    		} else  {
    			return 0L;
    		}
    	}

    	long result = (ipArrayInt[0] << 24)
    			+ (ipArrayInt[1] << 16)
    			+ (ipArrayInt[2] << 8)
    			+ ipArrayInt[3];

        return Long.valueOf(result);
    }

    /**
     * 数値(Long)のIPアドレスを文字列に変換する
     * @param ipAddressInt 数値のIPアドレス
     * @return 文字列のIPアドレス
     */
    public static String convertIntegerToIpAddress(Long ipAddressInt) {

    	long[] ipInt = new long[4];

    	ipInt[0] = (ipAddressInt >> 24) & 0xff;
    	ipInt[1] = (ipAddressInt >> 16) & 0xff;
    	ipInt[2] = (ipAddressInt >> 8) & 0xff;
    	ipInt[3] = ipAddressInt & 0xff;

    	StringBuilder ipAddress = new StringBuilder();

        for (int i=0; i<ipInt.length; i++) {
        	if (ipInt[i] > 255) return "0.0.0.0";
        	ipAddress.append(ipInt[i]);
        	if (i < ipInt.length -1) {
        		ipAddress.append(".");
        	}
        }
        return ipAddress.toString();
    }

    public static String toAscii(String target) {
        String result = "";
        byte[] asciiBytes;
        try {
            asciiBytes = target.getBytes("US-ASCII");
            for (int i = 0; i < asciiBytes.length; i++) {
                result = result + "&#" +  asciiBytes[i] + ";";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result = target;
        }
        return result;
    }
    /**
     * クロスサイトスクリプティング対応のエスケープ処理
     * @param target 対象文字列
     * @return エスケープ後文字列
     */
    public static String escapeXss(String target) {
        return target.replaceAll("&(?!lt;)(?!gt;)(?!amp;)(?!quot;)(?!#\\d\\d;)[.]*", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#39;");
//        return escapeHtml(target);
    }

    /**
     * SQLインジェクション対応のエスケープ処理
     * @param target 対象文字列
     * @return エスケープ後文字列
     */
    public static String escapeSqlInjection(String target) {
        return target.replaceAll("<", "&lt;")
                .replaceAll("\\\\", "&#92;")
                .replaceAll("'", "&#39;");
    }

    /**
     * コマンドインジェクション対応のエスケープ処理(削除処理)
     * @param target 対象文字列
     * @return エスケープ後文字列
     */
    public static String escapeCommand(String target) {
        String regex = "[;\\|&`<>$\\*?{}\\[\\]!]";
        String repl = "";
        return Pattern.compile(regex).matcher(target).replaceAll(repl);
    }

    /**
     * ディレクトリとラバーサル対応のエスケープ処理(削除処理)
     * @param target 対象文字列
     * @return エスケープ後文字列
     */
    public static String escapeDirectoryTraversal(String target) {
        String regex = "[(\\.\\./)(\\./)/(\\.\\.\\\\)(\\.\\\\)\\\\]";
        return target.replaceAll(regex, "");
    }

    /**
     * 文字列をURLエンコーディングする
     * @param target URLエンコーディング対象文字列
     * @return URLエンコーディング後文字列
     */
    public static String getUrlEndcoded(String target) {
        String encodedString = null;
        try {
            encodedString = URLEncoder.encode(target, "utf-8" );
        }
        catch (UnsupportedEncodingException e) {
            //ありえない
            logger.error(e.getMessage());
        }
        return encodedString;
    }

    /**
     * 文字列をURLデコーディングする
     * @param target URLデコーディング対象文字列
     * @return URLデコーディング後文字列
     */
    public static String getUrlDecoded(String target) {
        String decodedString = null;
        try {
            decodedString = URLDecoder.decode(target, "utf-8" );
        }
        catch (UnsupportedEncodingException e) {
            //ありえない
            logger.error(e.getMessage());
        }
        return decodedString;
    }
}
