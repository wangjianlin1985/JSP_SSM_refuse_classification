// 
// 
// 

package com.edu.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class User
{
    private Long id;
    private String username;
    private String password;
    private Long roleId;
    private String photo;
    private int sex;
    private Integer age;
    private String address;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(final String photo) {
        this.photo = photo;
    }
    
    public int getSex() {
        return this.sex;
    }
    
    public void setSex(final int sex) {
        this.sex = sex;
    }
    
    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(final Integer age) {
        this.age = age;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public Long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }
}
