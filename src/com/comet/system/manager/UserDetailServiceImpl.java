package com.comet.system.manager;

import com.comet.core.security.privilege.Privilege;
import com.comet.core.security.user.BaseUser;
import com.comet.core.webservice.security.WSUserService;
import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import com.comet.system.utils.UserSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Project:shmpi-mis
 *
 * <p>
 * 用户详细信息获取实现类
 * </p>
 * Create On 2009-5-11 上午10:24:38
 *
 * @author <a href="mailto:hzxia2002@gmail.com">Jackie</a>
 * @version 1.0
 *
 */
public class UserDetailServiceImpl implements UserDetailsService, WSUserService {
	private UserManager userManager;

	/**
	 * 设置service
	 *
	 * @param userManager
	 *            UserManager
	 */
	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * 取得service
	 *
	 * @return UserManager
	 */
	public UserManager getUserManager() {
		return this.userManager;
	}

	/**
	 * 用户详细信息获取实现
	 *
	 * @param userName
	 *            用户名
	 * @return UserDetails接口
	 * @throws UsernameNotFoundException
	 *             UsernameNotFoundException
	 * @throws DataAccessException
	 *             DataAccessException
	 */
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
        boolean isKey = false;
		List<SysUser> userList = userManager.findByProperty("loginName",
				userName);

		if (userList == null || userList.size() <= 0) {
            // 如果用户名不存在，尝试通过身份证号进行登录
            userList = userManager.findByProperty("userId", userName);
            isKey = true;

            if(userList == null || userList.size() <= 0) {
			    throw new UsernameNotFoundException("用户[<font color='red'>"
					+ userName + "</font>]不存在");
            }
		}

		SysUser user = userList.get(0);

        // 如果是通过u-key登录，将密码设为身份证号
        if(isKey) {
            user.setPassword(user.getUserId());
        }

		if (UserSessionUtils.getInstance().isUserInvalid(user)) {
			throw new UsernameNotFoundException("用户[<font color='red'>"
					+ userName + "</font>]已经无效!");
		} else if(UserSessionUtils.getInstance().isUserLocked(user)) {
            throw new UsernameNotFoundException("用户[<font color='red'>"
                    + userName + "</font>]已经锁定!");
        }

		// 取得用户所拥有的权限
		Set<GrantedAuthority> authsList = new HashSet<GrantedAuthority>();

        // 取得用户角色
        List<SysRole> roles = userManager.getUserRoles(user);

        if(roles == null || roles.size() == 0) {
		    authsList.add(new GrantedAuthorityImpl("ROLE_USER"));
        } else {
            for(SysRole role : roles) {
                authsList.add(new GrantedAuthorityImpl(role.getCode()));
            }
        }

		Map<String, Privilege> set = new HashMap<String, Privilege>();

		// 取得用户的特权
		List<SysPrivilege> privileges = userManager
				.getUserPrivileges(user, "0");

		if (privileges != null && privileges.size() > 0) {
			for (SysPrivilege tmp : privileges) {
				set.put(tmp.getCode(), tmp);
			}
		}

		// 取得用户的角色权限
		List<SysPrivilege> rolePrivileges = userManager.getUserRolePrivileges(
				user, "1");

		if (rolePrivileges != null && rolePrivileges.size() > 0) {
			for (SysPrivilege tmp : rolePrivileges) {
				set.put(tmp.getCode(), tmp);
			}
		}
		BaseUser userdetail = new BaseUser(user.getId(), user.getDisplayName(),
				user.getLoginName(), user.getPassword(), true, true, true,
				        true,
                authsList.toArray(new GrantedAuthority[authsList.size()]), set);

        userdetail.setLoginName(user.getLoginName());

		return userdetail;
	}

    /**
     * 取得Web Service访问用户的详细信息
     *
     * @param username
     * @return
     */
    public BaseUser getUser(String username) {
        return (BaseUser)loadUserByUsername(username);
    }
}
