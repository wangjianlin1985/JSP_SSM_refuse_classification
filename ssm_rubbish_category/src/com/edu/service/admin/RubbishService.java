// 
// 
// 

package com.edu.service.admin;

import java.util.Map;
import com.edu.entity.admin.Rubbish;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RubbishService
{
    List<Rubbish> findByName(String p0);
    
    int add(Rubbish p0);
    
    int edit(Rubbish p0);
    
    int delete(Long p0);
    
    List<Rubbish> findList(Map<String, Object> p0);
    
    int getTotal(Map<String, Object> p0);
}
