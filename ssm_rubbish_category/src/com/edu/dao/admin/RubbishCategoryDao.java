// 
// 
// 

package com.edu.dao.admin;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.RubbishCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface RubbishCategoryDao
{
    RubbishCategory findByName(String p0);
    
    int add(RubbishCategory p0);
    
    int edit(RubbishCategory p0);
    
    int delete(Long p0);
    
    List<RubbishCategory> findList(Map<String, Object> p0);
    
    int getTotal(Map<String, Object> p0);
    
    List<RubbishCategory> findByCommon(String p0);
}
