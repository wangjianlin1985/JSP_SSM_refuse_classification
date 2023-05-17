// 
// 
// 

package com.edu.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.RoleDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    private RoleDao roleDao;
    
    @Override
    public int add(final Role role) {
        return this.roleDao.add(role);
    }
    
    @Override
    public int edit(final Role role) {
        return this.roleDao.edit(role);
    }
    
    @Override
    public int delete(final Long id) {
        return this.roleDao.delete(id);
    }
    
    @Override
    public List<Role> findList(final Map<String, Object> queryMap) {
        return this.roleDao.findList(queryMap);
    }
    
    @Override
    public int getTotal(final Map<String, Object> queryMap) {
        return this.roleDao.getTotal(queryMap);
    }
    
    @Override
    public Role find(final Long id) {
        return this.roleDao.find(id);
    }
}
