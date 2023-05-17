// 
// 
// 

package com.edu.dao.admin;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao
{
    int add(Log p0);
    
    List<Log> findList(Map<String, Object> p0);
    
    int getTotal(Map<String, Object> p0);
    
    int delete(String p0);
}
