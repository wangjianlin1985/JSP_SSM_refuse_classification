// 
// 
// 

package com.edu.dao.admin;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao
{
    int add(Role p0);
    
    int edit(Role p0);
    
    int delete(Long p0);
    
    List<Role> findList(Map<String, Object> p0);
    
    int getTotal(Map<String, Object> p0);
    
    Role find(Long p0);
}
