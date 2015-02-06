package hungry.utility;

import java.io.ByteArrayOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class CryptUtility {
    private static Logger logger = Logger.getLogger(CryptUtility.class);

    private static final byte[] SALT
        = { (byte) 0xcc, (byte) 0x73, (byte) 0x62, (byte) 0xc0, (byte) 0x0e, (byte) 0x28, (byte) 0x44, (byte) 0x96 };

    private static final int ITER_COUNT = 128;

    private static final PBEParameterSpec PBE_PARAM_SPEC = new PBEParameterSpec(SALT, ITER_COUNT);

    /**
     * 初期化ベクトルを利用して暗号化する
     * 対象機能：
     * 　リマインダーURLのパラメータ
     * 　クリエイティブのHTML文字列
     * @param target 暗号化対象文字列
     * @return 暗号化文字列
     */
    public static String encrypt(String target) {

        return encrypt(target ,"Cgt%gdSDERy5LnIf", "J75gdu#h6&Gsd5Oe");
    }

    /**
     * 初期化ベクトルを利用して暗号化する
     * 対象機能：
     * 　リマインダーURLのパラメータ
     * 　クリエイティブのHTML文字列
     * @param target 暗号化対象文字列
     * @return 暗号化文字列
     */
    public static String encryptForImage(String target) {

        return encrypt(target , "ZZ?Ag0N}TI5zQMP1","3G1SG@^b($2lQgvP");
    }

    /**
     * 初期化ベクトルを利用して暗号化する
     * 対象機能：
     * 　リマインダーURLのパラメータ
     * 　クリエイティブのHTML文字列
     * @param target 暗号化対象文字列
     * @return 暗号化文字列
     */
    public static String decryptForImage(String target) {

        return decrypt(target , "ZZ?Ag0N}TI5zQMP1","3G1SG@^b($2lQgvP");
    }

    /**
     * 初期化ベクトルを利用して暗号化する
     * 対象機能：
     * 　リマインダーURLのパラメータ
     * 　クリエイティブのHTML文字列
     * @param target 暗号化対象文字列
     * @return 暗号化文字列
     */
    public static String encryptForTag(String target) {

        return encrypt(target , "H5ryw8SDW7dpaY1t","R28eir%f5MW27yUi");

    }

    /**
     * 初期化ベクトルを利用して暗号化する
     * 対象機能：
     * 　リマインダーURLのパラメータ
     * 　クリエイティブのHTML文字列
     * @param target 暗号化対象文字列
     * @return 暗号化文字列
     */
    public static String encrypt(String target, String ivStr, String keyStr) {
        String encryptStr = "";
        if (StringUtility.isNullOrEmpty(target)
                || StringUtility.isNullOrEmpty(ivStr)
                || StringUtility.isNullOrEmpty(keyStr)) {
            return encryptStr;
        }
        try {
            byte[] key = keyStr.getBytes();
            byte[] iv =ivStr.getBytes();
            SecretKey cipherKey = new SecretKeySpec(key, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, cipherKey, ivSpec);
            byte[] cipherText = cipher.doFinal(target.getBytes());
            encryptStr = byteToString(cipherText);
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * 初期化ベクトルを利用して復号化する
     * 対象機能：
     * 　リマインダーURLのパラメータ
     * 　クリエイティブのHTML文字列
     * @param target 復号化対象文字列
     * @return 復号化文字列
     */
    public static String decrypt(String target, String ivStr, String keyStr) {
        String dcecryptStr = "";
        if (StringUtility.isNullOrEmpty(target)
                || StringUtility.isNullOrEmpty(ivStr)
                || StringUtility.isNullOrEmpty(keyStr)) {
            return dcecryptStr;
        }

        try {
            byte[] key = keyStr.getBytes();
            byte[] iv =ivStr.getBytes();
            SecretKey cipherKey = new SecretKeySpec(key, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, cipherKey, ivSpec);
            byte[] cipherBytes = cipher.doFinal(stringToByte(target));
            if (cipherBytes == null || cipherBytes.length == 0) {
                dcecryptStr = StringUtility.EMPTY;
            } else {
                dcecryptStr = new String(cipherBytes);
            }
            return dcecryptStr;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * パスワードベースで暗号化する
     * 対象機能：
     * 　クリエイティブプレビュー機能用
     * @param text 暗号化するプレーンテキスト
     * @param key 暗号化キー
     * @return 暗号化文字列
     */
    public static String encryptForPreview(String text, String key) {
        if (StringUtility.isNullOrEmpty(text) || StringUtility.isNullOrEmpty(key)) {
            return StringUtility.EMPTY;
        }
        try {
            byte[] bytePassword = text.getBytes();
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            Cipher descipher = Cipher.getInstance("PBEWithMD5AndDES");
            descipher.init(Cipher.ENCRYPT_MODE, pbeKey, PBE_PARAM_SPEC);
            byte[] cipherPassword = descipher.doFinal(bytePassword);
            return byteToString(cipherPassword);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException
                | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * パスワードベースで復号化する
     * 対象機能：
     * 　クリエイティブプレビュー機能用
     * @param dec 暗号化文字列
     * @param key
     * @return 復号化したプレーンテキスト
     */
    public static String decryptForPreview(String dec, String key) {
        if (StringUtility.isNullOrEmpty(dec) || StringUtility.isNullOrEmpty(key)) {
            return StringUtility.EMPTY;
        }
        try {
            byte[] bytePassword = stringToByte(dec);
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
            Cipher descipher = Cipher.getInstance("PBEWithMD5AndDES");
            descipher.init(Cipher.DECRYPT_MODE, pbeKey, PBE_PARAM_SPEC);
            byte[] cipherPassword = descipher.doFinal(bytePassword);
            return new String(cipherPassword);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException
                | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * バイト配列を16進数の文字列に変換します
     * @param bytes バイト配列
     * @return 16進数文字列
     */
    private static String byteToString(byte[] bytes) {
        StringBuffer strbuf = new StringBuffer(bytes.length * 2);
        for (int index = 0; index < bytes.length; index++) {
            int bt = bytes[index] & 0xff;
            if (bt < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Integer.toHexString(bt));
        }
        return strbuf.toString();
    }

    /**
     * 16進数の文字列をバイト配列に変換します
     * @param string 対象文字列
     * @return バイト配列
     */
    private static byte[] stringToByte(String string) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        for (int i = 0; i < string.length(); i++) {
            String hex = string.substring(i, i + 2);
            os.write(Integer.parseInt(hex, 16));
            i += 1;
        }
        return os.toByteArray();
    }

    /**
     * 文字列ハッシュを取得する
     * @param input ハッシュ対象の文字列
     * @return SHA512でのハッシュ値
     */
    public static String getMessageDigest(String input) {

        if (StringUtility.isNullOrEmpty(input)) return input;

        MessageDigest md = null;
        StringBuilder builder = new StringBuilder();

        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md.update(input.getBytes());
        byte[] byteHashArray = md.digest();
        for (int d : byteHashArray) {
            if (d < 0) {
                d += 256;
            }

            if (d < 16) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(d));
        }
        return builder.toString();
    }
}
