package com.example.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.example.blog.view.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="tbl_user_role_apps_menu")
@Where(clause = "is_deleted = 0")
@DynamicUpdate
public class RoleAppsMenu {

	// @EmbeddedId
	// RoleAppsMenuKey id;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(targetEntity = Role.class,fetch=FetchType.LAZY)
	@MapsId("roleId")
	@JoinColumn(name="user_role_id")
	@JsonManagedReference
    // private Author author;
	// @JsonBackReference(value = "role-roleappsmenu")
	private Role roleApps;
	
	@ManyToOne(targetEntity = AppsMenu.class,fetch=FetchType.LAZY)
	@MapsId("menuId")
	@JoinColumn(name="menu_id")
	@JsonManagedReference
    // private Author author;
	// @JsonBackReference(value = "appsmenu-roleappsmenu")
	private AppsMenu appsMenu;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
	@JsonView(View.Internal.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date create_time;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
	@JsonView(View.Public.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date update_time;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
	@JsonView(View.Public.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_time")
	private Date delete_time;
	
	@Column(name="is_deleted")
	private Integer is_deleted;

	@Column(name="request_type")
	private String requestType;
	
	//Getter Setter
	// public RoleAppsMenuKey getId() {
	// 	return id;
	// }

	// public void setId(RoleAppsMenuKey id) {
	// 	this.id = id;
	// }

	public Role getRoleApps() {
		return roleApps;
	}

	public void setRoleApps(Role roleApps) {
		this.roleApps = roleApps;
	}

	public AppsMenu getAppsMenu() {
		return appsMenu;
	}

	public void setAppsMenu(AppsMenu appsMenu) {
		this.appsMenu = appsMenu;
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

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	//Constructor
	public RoleAppsMenu()
	{
		
	}



		
}
