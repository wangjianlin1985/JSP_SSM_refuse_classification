// 
// 
// 

package com.edu.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Authority
{
    private Long id;
    private Long roleId;
    private Long menuId;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }
    
    public Long getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(final Long menuId) {
        this.menuId = menuId;
    }
}
