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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.example.blog.view.View;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="tbl_menu")
@DynamicUpdate
public class AppsMenu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_id")
	private int menu_id;
	
	@Column(name="parent_menu_id")
	private int parent_menu_id;
	
	@Column(name="menu_label_en")
	private String menu_label_en;
	
	@Column(name="menu_label_id")
	private String menu_label_id;
	
	@Column(name="menu_hint_en")
	private String menu_hint_en;
	
	@Column(name="menu_hint_id")
	private String menu_hint_id;
	
	@Column(name="menu_link")
	private String menu_link;
	
	@Column(name="menu_icon")
	private String menu_icon;
	
	@Column(name="is_menu_active")
	private Integer menu_status;
	
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

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "appsMenu",fetch = FetchType.LAZY)
	@JsonManagedReference(value = "appsmenu-roleappsmenu")
	private Set<RoleAppsMenu> roleappsmenu;
		
	//Constructor
	public AppsMenu()
	{
		
	}

	

	public AppsMenu(int menu_id, int parent_menu_id, String menulabelen, String menulabelid, String menu_hint_en,
			String menu_hint_id, String menu_link, String menu_icon, Integer menu_status, Date create_time,
			Date update_time, Date delete_time, Integer is_deleted, Set<RoleAppsMenu> roleappsmenu) {
		super();
		this.menu_id = menu_id;
		this.parent_menu_id = parent_menu_id;
		this.menu_label_en = menulabelen;
		this.menu_label_id = menulabelid;
		this.menu_hint_en = menu_hint_en;
		this.menu_hint_id = menu_hint_id;
		this.menu_link = menu_link;
		this.menu_icon = menu_icon;
		this.menu_status = menu_status;
		this.create_time = create_time;
		this.update_time = update_time;
		this.delete_time = delete_time;
		this.is_deleted = is_deleted;
		this.roleappsmenu = roleappsmenu;
	}



	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getParent_menu_id() {
		return parent_menu_id;
	}

	public void setParent_menu_id(int parent_menu_id) {
		this.parent_menu_id = parent_menu_id;
	}

	

	public String getMenuLabeEn() {
		return menu_label_en;
	}



	public void setMenuLabelEn(String menulabelen) {
		this.menu_label_en = menulabelen;
	}



	public String getMenuLabelId() {
		return menu_label_id;
	}



	public void setMenuLabelId(String menulabelid) {
		this.menu_label_id = menulabelid;
	}



	public String getMenu_hint_en() {
		return menu_hint_en;
	}

	public void setMenu_hint_en(String menu_hint_en) {
		this.menu_hint_en = menu_hint_en;
	}

	public String getMenu_hint_id() {
		return menu_hint_id;
	}

	public void setMenu_hint_id(String menu_hint_id) {
		this.menu_hint_id = menu_hint_id;
	}

	public String getMenu_link() {
		return menu_link;
	}

	public void setMenu_link(String menu_link) {
		this.menu_link = menu_link;
	}

	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	public Integer getMenu_status() {
		return menu_status;
	}

	public void setMenu_status(Integer menu_status) {
		this.menu_status = menu_status;
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

	public Set<RoleAppsMenu> getRoleappsmenu() {
		return roleappsmenu;
	}

	public void setRoleappsmenu(Set<RoleAppsMenu> roleappsmenu) {
		this.roleappsmenu = roleappsmenu;
	}
	

	
}
