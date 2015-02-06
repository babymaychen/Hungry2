package hungry.dao;

import hungry.entity.UserMaster;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserMasterDaoImp implements UserMasterDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * データソースの設定
     * @param coreMasterDS データベース
     */
    @Autowired
    public void setCoreMasterDS(DataSource coreMasterDS) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(coreMasterDS);
    }

    @Override
    public UserMaster findByMailAddress(UserMaster master) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
