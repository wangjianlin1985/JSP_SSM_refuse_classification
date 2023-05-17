// 
// 
// 

package com.edu.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.UserDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.UserService;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;
    
    @Override
    public User findByUsername(final String username) {
        return this.userDao.findByUsername(username);
    }
    
    @Override
    public int add(final User user) {
        return this.userDao.add(user);
    }
    
    @Override
    public int edit(final User user) {
        return this.userDao.edit(user);
    }
    
    @Override
    public int delete(final String ids) {
        return this.userDao.delete(ids);
    }
    
    @Override
    public List<User> findList(final Map<String, Object> queryMap) {
        return this.userDao.findList(queryMap);
    }
    
    @Override
    public int getTotal(final Map<String, Object> queryMap) {
        return this.userDao.getTotal(queryMap);
    }
    
    @Override
    public int editPassword(final User user) {
        return this.userDao.editPassword(user);
    }
}
