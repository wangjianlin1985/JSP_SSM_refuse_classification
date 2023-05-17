// 
// 
// 

package com.edu.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.edu.entity.admin.RubbishCategory;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.RubbishCategoryDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.RubbishCategoryService;

@Service
public class RubbishCategoryServiceImpl implements RubbishCategoryService
{
    @Autowired
    private RubbishCategoryDao rubbishCategoryDao;
    
    @Override
    public RubbishCategory findByName(final String name) {
        return this.rubbishCategoryDao.findByName(name);
    }
    
    @Override
    public int add(final RubbishCategory rubbishCategory) {
        return this.rubbishCategoryDao.add(rubbishCategory);
    }
    
    @Override
    public int edit(final RubbishCategory rubbishCategory) {
        return this.rubbishCategoryDao.edit(rubbishCategory);
    }
    
    @Override
    public int delete(final Long id) {
        return this.rubbishCategoryDao.delete(id);
    }
    
    @Override
    public List<RubbishCategory> findList(final Map<String, Object> queryMap) {
        return this.rubbishCategoryDao.findList(queryMap);
    }
    
    @Override
    public int getTotal(final Map<String, Object> queryMap) {
        return this.rubbishCategoryDao.getTotal(queryMap);
    }
    
    @Override
    public List<RubbishCategory> findByCommon(final String name) {
        return this.rubbishCategoryDao.findByCommon(name);
    }
}
