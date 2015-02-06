package hungry.common;

import hungry.utility.StringUtility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class SecureLoginPasswordEncoder implements PasswordEncoder {

    private static final String SHA_512 = "SHA-512";

    public String encode(CharSequence rawPassword) {
        return getMessageDigestForPassword(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean result = false;
        if (StringUtility.isNullOrEmpty(encodedPassword)
                || rawPassword == null
                || StringUtility.isNullOrEmpty(rawPassword.toString())) {
            return result;
        }

        String inputPassword = getMessageDigestForPassword(rawPassword.toString());

        if (encodedPassword.equals(inputPassword)) {
            result = true;
        }
        return result;
    }

    /**
     * 暗号化したバイト配列を16進数表記で返す。<br>
     * <p>
     * 変換処理失敗の場合、NULL を返す。
     * <p>
     *
     * @param b 変換したい値（バイト配列）
     * @return ダイジェスト計算が完了した値（バイト配列）
     */
    private String getMessageDigestForPassword(String s) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(SHA_512);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return new String(Hex.encodeHex(md.digest(s.getBytes())));
    }
}

