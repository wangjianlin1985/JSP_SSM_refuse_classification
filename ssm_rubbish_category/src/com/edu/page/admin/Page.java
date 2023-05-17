// 
// 
// 

package com.edu.page.admin;

import org.springframework.stereotype.Component;

@Component
public class Page
{
    private int page;
    private int rows;
    private int offset;
    
    public Page() {
        this.page = 1;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(final int page) {
        this.page = page;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public void setRows(final int rows) {
        this.rows = rows;
    }
    
    public int getOffset() {
        return this.offset = (this.page - 1) * this.rows;
    }
    
    public void setOffset(final int offset) {
        this.offset = offset;
    }
}
