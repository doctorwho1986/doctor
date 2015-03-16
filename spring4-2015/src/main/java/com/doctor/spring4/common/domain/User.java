package com.doctor.spring4.common.domain;

/**
 * 
 * @author doctor
 *
 * @time 2015年3月16日
 */
public class User {

	private Long id;
	private String loginName;
	private String name;
	private String password;
	private String salt;
	private String email;
	private String status;
	private Long teamId;

	public User() {

	}

	public User(Long id, String loginName, String name, String password, String salt, String email, String status, Long teamId) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.status = status;
		this.teamId = teamId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}
