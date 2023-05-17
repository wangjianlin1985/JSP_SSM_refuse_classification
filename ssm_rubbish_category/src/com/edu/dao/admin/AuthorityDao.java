// 
// 
// 

package com.edu.dao.admin;

import java.util.List;
import com.edu.entity.admin.Authority;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao
{
    int add(Authority p0);
    
    int deleteByRoleId(Long p0);
    
    List<Authority> findListByRoleId(Long p0);
}
