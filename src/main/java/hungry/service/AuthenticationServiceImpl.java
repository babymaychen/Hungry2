package hungry.service;

import hungry.dao.UserMasterDao;
import hungry.entity.UserMaster;
import hungry.utility.StringUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 認証処理を実施するサービスクラス
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserMasterDao userMasterDao;

	@Override
	public UserMaster findAuthentication(String userMailAddress) {

		if (StringUtility.isNullOrEmpty(userMailAddress))
			return new UserMaster();
		UserMaster master = new UserMaster();
		master.setMailAddress(userMailAddress);
		return userMasterDao.findByMailAddress(master);
	}

}
