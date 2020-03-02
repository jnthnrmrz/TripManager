package com.tripmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int id;
	@Column(name="role_id")
	private int roleId = 2; //1 is admin 2 is user
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	
	public User() {
	}
	
	public User(User user) {
		this.id = user.getId();
		this.roleId = user.getRoleId();
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
