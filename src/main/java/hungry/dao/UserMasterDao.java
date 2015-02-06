package hungry.dao;

import hungry.entity.UserMaster;

public interface UserMasterDao {

    public UserMaster findByMailAddress(UserMaster master);
}
