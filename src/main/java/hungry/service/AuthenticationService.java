package hungry.service;

import hungry.entity.UserMaster;

/**
 * ログイン等の認証処理を実施するサービス
 */
public interface AuthenticationService {
    /**
     * メールアドレスに対応するユーザ情報を取得する
     * @param userMailAddress ログイン画面にて入力したメールアドレス
     * @return メールアドレスに対応したユーザ情報
     */
    public UserMaster findAuthentication(String userMailAddress);


}
