package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Author extends AuditModel implements UserDetails   {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(length = 45)
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column(length = 45)
    private String first_name;
    @Column(length = 45)
	private String last_name;
	
	@Setter
	@Transient
	private String role = "NONE";

	public Integer getRoleId() {
		Integer roleId = 0;

		try {
			for (RoleMember roleMember: this.getRoleMember()) {
				if (roleId == 0) {
					roleId = roleMember.getRole().getId();
				}
			}
		} catch (Exception e) {
			roleId = 0;
		}
		
		return roleId;
	}


    
    public String getRole() {
		String role = "NONE";
		try {
		
			
			for(RoleMember roleMember: this.getRoleMember()) {
				if ( role.equals("NONE")) {
					role = roleMember.getRole().getRoletitle();
				}
			}
		} catch (Exception e) {
			role = "NONE";
		}
		
		return role;
    }
	
	

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
	@JsonManagedReference(value = "user-rolemember")
	Set<RoleMember> roleMember;
	
	public void setAuthorities(Collection<Role> authorities) {
		this.authorities = authorities;
	}
    
	public Set<RoleMember> getRoleMember() {
		return roleMember;
	}

	public void setRoleMember(Set<RoleMember> roleMember) {
		this.roleMember = roleMember;
	}

 
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_user_role_member", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id"))
    @OrderBy
    @JsonIgnore
    private Collection<Role> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


}