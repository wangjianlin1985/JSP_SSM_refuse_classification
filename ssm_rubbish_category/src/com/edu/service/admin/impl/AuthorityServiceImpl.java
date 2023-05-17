// 
// 
// 

package com.edu.service.admin.impl;

import java.util.List;
import com.edu.entity.admin.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.AuthorityDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService
{
    @Autowired
    private AuthorityDao authorityDao;
    
    @Override
    public int add(final Authority authority) {
        return this.authorityDao.add(authority);
    }
    
    @Override
    public int deleteByRoleId(final Long roleId) {
        return this.authorityDao.deleteByRoleId(roleId);
    }
    
    @Override
    public List<Authority> findListByRoleId(final Long roleId) {
        return this.authorityDao.findListByRoleId(roleId);
    }
}
