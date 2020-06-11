package com.example.blog.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_user_role", uniqueConstraints = { @UniqueConstraint(columnNames = {"role_title"}) })
@Where(clause = "is_deleted = 0")
@DynamicUpdate
public class Role implements GrantedAuthority {

	/**
	 *
	 */
	private static final long serialVersionUID = 6926766225621308700L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private int id;

	@Column(name="role_title")
	private String roletitle;
	
	@Column(name="role_desc")
	private String role_desc;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date create_time;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date update_time;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone = "GMT+7")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_time")
	private Date delete_time;
	
	@Column(name="is_deleted")
	private Integer is_deleted;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role",fetch = FetchType.LAZY)
	@JsonManagedReference(value = "role-rolemember")
	Set <RoleMember> roleMember;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "roleApps",fetch = FetchType.LAZY)
	@JsonManagedReference(value = "role-roleappsmenu")
	Set<RoleAppsMenu> roleAppsmenu;

	//Constructor
	public Role()
	{
		
	}
	
	
	public Role(int id, String roletitle, String role_desc, Date create_time, Date update_time, Date delete_time,
			Integer is_deleted, Set<RoleMember> roleMember, Set<RoleAppsMenu> roleAppsmenu) {
		super();
		this.id = id;
		this.roletitle = roletitle;
		this.role_desc = role_desc;
		this.create_time = create_time;
		this.update_time = update_time;
		this.delete_time = delete_time;
		this.is_deleted = is_deleted;
		this.roleMember = roleMember;
		this.roleAppsmenu = roleAppsmenu;
	}


	//getter setter
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoletitle() {
		return roletitle;
	}

	public void setRoletitle(String roletitle) {
		this.roletitle = roletitle;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc.trim();
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getDelete_time() {
		return delete_time;
	}

	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}

	public Integer getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Set<RoleMember> getRoleMember() {
		return roleMember;
	}


	public void setRoleMember(Set<RoleMember> roleMember) {
		this.roleMember = roleMember;
	}

	public Set<RoleAppsMenu> getRoleAppsmenu() {
		return roleAppsmenu;
	}


	public void setRoleAppsmenu(Set<RoleAppsMenu> roleAppsmenu) {
		this.roleAppsmenu = roleAppsmenu;
	}

	@Override
	public String getAuthority() {
		return this.getRoletitle();
	}


	//Define toString
	@Override
	public String toString() {
		return "Role [id=" + id + ", role_title=" + roletitle + ", role_desc=" + role_desc + ", create_time="
				+ create_time + ", update_time=" + update_time + ", delete_time=" + delete_time + ", is_deleted="
				+ is_deleted + "]";
	}
}
