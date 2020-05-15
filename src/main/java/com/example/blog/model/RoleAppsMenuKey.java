package com.example.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleAppsMenuKey implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -3479269813800674472L;

	@Column(name = "menu_id")
	private int menuId;
	
	@Column(name="user_role_id")
	private int roleId;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (menuId ^ (menuId >>> 32));
		result = prime * result + (int) (roleId ^ (roleId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleAppsMenuKey other = (RoleAppsMenuKey) obj;
		if (menuId != other.menuId)
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}
	
	public RoleAppsMenuKey()
	{
		
	}

	public RoleAppsMenuKey(int menuId, int roleId) {
		super();
		this.menuId = menuId;
		this.roleId = roleId;
	}

	
	
}
