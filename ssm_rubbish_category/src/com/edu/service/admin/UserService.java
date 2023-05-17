// 
// 
// 

package com.edu.service.admin;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService
{
    User findByUsername(String p0);
    
    int add(User p0);
    
    int edit(User p0);
    
    int editPassword(User p0);
    
    int delete(String p0);
    
    List<User> findList(Map<String, Object> p0);
    
    int getTotal(Map<String, Object> p0);
}
