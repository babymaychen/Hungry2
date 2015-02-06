package hungry.entity;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ユーザマスタテーブルに対応するクラス
 */
public class UserMaster {
	public static final String TEMPORARY_PASSWORD = "temp";

    // ユーザID
    private Integer userId;
    // 大厦ID
    private Integer buildingId;
    // メールアドレス
    private String mailAddress;
    // パスワード
    private String password;
    // 氏名
    private String userName;
    // ユーザ区分
    private String userRole;
    // コメント
    private String remarks;
    // 削除フラグ
    private String softDeleteFlag;
    // 更新日時
    private Timestamp updateTime;
    // 作成日時
    private Timestamp createTime;

    /*
     * ユーザ区分とロールの対応
     * ROLE_MA_STAFF      --- userRole = ma_staff
     * ROLE_REP           --- userRole = agency
     * ROLE_MEDIA_MANAGER --- userRole = client
     * ROLE_MEDIA_STAFF   --- userRole = othre
     */
    public enum UserRole {ROLE_CLIENT_NORMAL, ROLE_CLIENT_DIAMOND, ROLE_ADMIN };

    public static final String USER_ROLE_NORMAL = "client_normal";
    public static final String USER_ROLE_DIAMOND = "client_diamond";
    public static final String USER_ROLE_ADMIN = "admin";

    /**
     * ユーザロールをEnumで返す
     * @return 対応する権限
     */
    public UserRole getUserRoleEnum() {
        UserRole role = null;
        switch(userRole) {
            case USER_ROLE_NORMAL:
                role = UserRole.ROLE_CLIENT_NORMAL;
                break;
            case USER_ROLE_DIAMOND:
                role = UserRole.ROLE_CLIENT_DIAMOND;
                break;
            case USER_ROLE_ADMIN:
                role = UserRole.ROLE_ADMIN;
                break;
            default :
                role = null;
        }
        return role;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSoftDeleteFlag() {
        return softDeleteFlag;
    }

    public void setSoftDeleteFlag(String softDeleteFlag) {
        this.softDeleteFlag = softDeleteFlag;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
