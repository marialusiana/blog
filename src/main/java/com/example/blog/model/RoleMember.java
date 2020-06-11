package com.example.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="tbl_user_role_member")
@Where(clause = "is_deleted = 0")
@DynamicUpdate
public class RoleMember implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6762220426121894923L;

	@EmbeddedId
	UserKey id;
	
	@ManyToOne(targetEntity = Author.class,fetch=FetchType.LAZY)
	@MapsId("author_id")
	@JoinColumn(name="author_id", insertable=false,  updatable=false)
	@JsonBackReference(value = "user-rolemember")
	private  Author author;
	
	@ManyToOne(targetEntity = Role.class,fetch=FetchType.LAZY)
	@MapsId("user_role_id")
	@JoinColumn(name="user_role_id", insertable=false,  updatable=false)
	@JsonBackReference(value = "role-rolemember")
	private  Role role;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss",timezone="GMT+7")
	@JsonView(View.Internal.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time",updatable=false)
	private Date created_time;

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

	
	public UserKey getId() {
		return id;
	}

	public void setId(UserKey id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
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

	public RoleMember(UserKey id, Author author, Role role, Date created_time, Date update_time, Date delete_time,
			Integer is_deleted) {
		this.id = id;
		this.author = author;
		this.role = role;
		this.created_time = created_time;
		this.update_time = update_time;
		this.delete_time = delete_time;
		this.is_deleted = is_deleted;
	}

	public RoleMember() {
		
	}

	
}
