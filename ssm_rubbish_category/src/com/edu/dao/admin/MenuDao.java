// 
// 
// 

package com.edu.dao.admin;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao
{
    int add(Menu p0);
    
    List<Menu> findList(Map<String, Object> p0);
    
    List<Menu> findTopList();
    
    int getTotal(Map<String, Object> p0);
    
    int edit(Menu p0);
    
    int delete(Long p0);
    
    List<Menu> findChildernList(Long p0);
    
    List<Menu> findListByIds(String p0);
}
