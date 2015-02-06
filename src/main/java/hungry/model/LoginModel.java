package hungry.model;

public class LoginModel {
    // パスワード
    private String password;
    // 氏名
    private String userName;
    // 记住
    private Boolean isRemember;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getIsRemember() {
        return isRemember;
    }
    public void setIsRemember(Boolean isRemember) {
        this.isRemember = isRemember;
    }

}
