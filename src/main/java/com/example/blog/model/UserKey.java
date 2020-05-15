package com.example.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserKey implements Serializable {

	
	/**
	 *
	 */
	private static final long serialVersionUID = 7173341417043861134L;

	@Column(name = "author_id")
	private long author_id;
	
	@Column(name="user_role_id")
	private int role_id;

	public long getauthor_id() {
		return author_id;
	}

	public void setauthor_id(long author_id) {
		this.author_id = author_id;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (role_id ^ (role_id >>> 32));
		result = prime * result + (int) (author_id ^ (author_id >>> 32));
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
		UserKey other = (UserKey) obj;
		if (role_id != other.role_id)
			return false;
		if (author_id != other.author_id)
			return false;
		return true;
	}

	
	
	
	
}
