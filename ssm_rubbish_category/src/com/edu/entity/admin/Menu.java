// 
// 
// 

package com.edu.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Menu
{
    private Long id;
    private Long parentId;
    private Long _parentId;
    private String name;
    private String url;
    private String icon;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getParentId() {
        return this.parentId;
    }
    
    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(final String icon) {
        this.icon = icon;
    }
    
    public Long get_parentId() {
        return this._parentId = this.parentId;
    }
    
    public void set_parentId(final Long _parentId) {
        this._parentId = _parentId;
    }
}
