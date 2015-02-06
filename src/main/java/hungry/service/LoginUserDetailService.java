package hungry.service;

import hungry.entity.UserMaster;
import hungry.entity.UserMaster.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * SpringSecurityを利用したログイン処理用のサービスクラス
 */
@Service
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDetails userDetails = null;

        try {
            UserMaster userMaster = authenticationService.findAuthentication(userName);
            userDetails = new User(
                    userMaster.getMailAddress(),
                    userMaster.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    getAtuhtorities(userMaster.getUserRoleEnum()));

        } catch(Exception e) {
            throw new UsernameNotFoundException("Error in retrieving user");
        }
        return userDetails;
    }


    /**
     * システム上の権限を取得する<br/>
     * 権限のレベルは以下の順に大きくなり、上位権限は下位権限は包含する。
     *  ROLE_MEDIA_STAFF < ROLE_MEDIA_MANAGER < ROLE_REP < ROLE_MA_STAFF
     * @param userRole ログインユーザのユーザ区分に対応する権限
     * @return システムの権限
     */
    private Collection<GrantedAuthority> getAtuhtorities(UserRole userRole) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        if (userRole == null) return authList;

        // 普通用户权限分配
        authList.add(new SimpleGrantedAuthority(UserRole.ROLE_CLIENT_NORMAL.toString()));

        // 钻石用户权限分配
        if (userRole.compareTo(UserRole.ROLE_CLIENT_DIAMOND) > 0) {
            authList.add(new SimpleGrantedAuthority(UserRole.ROLE_CLIENT_DIAMOND.toString()));
        }

        // 管理用户权限分配
        if (userRole.compareTo(UserRole.ROLE_ADMIN) > 0) {
            authList.add(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.toString()));
        }
        return authList;
    }



}
