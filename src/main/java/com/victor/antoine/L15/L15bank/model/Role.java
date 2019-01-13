package com.victor.antoine.L15.L15bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
 
@Entity
public class Role {

	@Id
    @Column(name = "User_Id", nullable = false)
    private int userId;
 
    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
 
    public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
	public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
     
}