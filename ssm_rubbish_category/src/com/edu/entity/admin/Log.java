// 
// 
// 

package com.edu.entity.admin;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class Log
{
    private Long id;
    private String content;
    private Date createTime;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = content;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
}
