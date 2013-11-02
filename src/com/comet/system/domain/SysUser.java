package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysUser;
import com.comet.core.security.privilege.Privilege;
import org.springframework.security.core.GrantedAuthority;

import java.util.Map;


public class SysUser extends BaseSysUser {
	private static final long serialVersionUID = 1L;

    private Long deptId;
    private String deptName;
    private String roleNames;
    private String password2;
    private String password1;

    public SysUser() {

    }

    public SysUser (Long id,String realName, String username, String password, boolean enabled,
                        boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, GrantedAuthority[] authorities,
                        Map<String, Privilege> privilegeList) {
        super(id, realName, username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, privilegeList);
    }

/*[CONSTRUCTOR MARKER BEGIN]*/
//	public SysUser () {
//		super();
//	}

	/**
	 * Constructor for primary key
	 */
//	public SysUser (java.lang.Long id) {
//		super(id);
//	}

	/**
	 * Constructor for required fields
	 */
//	public SysUser (
//		java.lang.Long id,
//		java.lang.String loginName) {
//
//		super (
//			id,
//			loginName);
//	}

/*[CONSTRUCTOR MARKER END]*/

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
}