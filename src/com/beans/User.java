package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_info")
public class User implements java.io.Serializable {

	// Fields
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="user_name")
	private String name;
	
	@Column(name="user_passwd")
	private String passwd;
	
	@Column(name="user_privilege")
	private Integer privilege;

	// Constructors

	public User() {
	}

	public User(String userName, String userPasswd) {
		// TODO Auto-generated constructor stub
		this.name = userName;
		this.passwd = userPasswd;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", passwd=" + passwd + ", privilege=" + privilege + "]";
	}

	

}