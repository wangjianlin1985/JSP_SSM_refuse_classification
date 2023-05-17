// 
// 
// 

package com.edu.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.MenuDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.MenuService;

@Service
public class MenuServiceImpl implements MenuService
{
    @Autowired
    private MenuDao menuDao;
    
    @Override
    public int add(final Menu menu) {
        return this.menuDao.add(menu);
    }
    
    @Override
    public List<Menu> findList(final Map<String, Object> queryMap) {
        return this.menuDao.findList(queryMap);
    }
    
    @Override
    public List<Menu> findTopList() {
        return this.menuDao.findTopList();
    }
    
    @Override
    public int getTotal(final Map<String, Object> queryMap) {
        return this.menuDao.getTotal(queryMap);
    }
    
    @Override
    public int edit(final Menu menu) {
        return this.menuDao.edit(menu);
    }
    
    @Override
    public int delete(final Long id) {
        return this.menuDao.delete(id);
    }
    
    @Override
    public List<Menu> findChildernList(final Long parentId) {
        return this.menuDao.findChildernList(parentId);
    }
    
    @Override
    public List<Menu> findListByIds(final String ids) {
        return this.menuDao.findListByIds(ids);
    }
}
