// 
// 
// 

package com.edu.service.admin;

import java.util.List;
import com.edu.entity.admin.Authority;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService
{
    int add(Authority p0);
    
    int deleteByRoleId(Long p0);
    
    List<Authority> findListByRoleId(Long p0);
}
