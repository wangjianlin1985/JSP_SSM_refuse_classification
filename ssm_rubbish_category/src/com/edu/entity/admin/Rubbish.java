// 
// 
// 

package com.edu.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Rubbish
{
    private Long id;
    private String name;
    private Long categoryId;
    private RubbishCategory rubbishCategory;
    
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
    
    public Long getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public RubbishCategory getRubbishCategory() {
        return this.rubbishCategory;
    }
    
    public void setRubbishCategory(final RubbishCategory rubbishCategory) {
        this.rubbishCategory = rubbishCategory;
    }
}
