// 
// 
// 

package com.edu.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class RubbishCategory
{
    private Long id;
    private String name;
    private String explain;
    private String require;
    private String common;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getExplain() {
        return this.explain;
    }
    
    public void setExplain(final String explain) {
        this.explain = explain;
    }
    
    public String getRequire() {
        return this.require;
    }
    
    public void setRequire(final String require) {
        this.require = require;
    }
    
    public String getCommon() {
        return this.common;
    }
    
    public void setCommon(final String common) {
        this.common = common;
    }
}
