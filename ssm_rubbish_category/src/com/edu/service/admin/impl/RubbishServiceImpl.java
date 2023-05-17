// 
// 
// 

package com.edu.service.admin.impl;

import java.util.Map;
import com.edu.entity.admin.Rubbish;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.RubbishDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.RubbishService;

@Service
public class RubbishServiceImpl implements RubbishService
{
    @Autowired
    private RubbishDao rubbishDao;
    
    @Override
    public List<Rubbish> findByName(final String name) {
        return this.rubbishDao.findByName(name);
    }
    
    @Override
    public int add(final Rubbish rubbish) {
        return this.rubbishDao.add(rubbish);
    }
    
    @Override
    public int edit(final Rubbish rubbish) {
        return this.rubbishDao.edit(rubbish);
    }
    
    @Override
    public int delete(final Long id) {
        return this.rubbishDao.delete(id);
    }
    
    @Override
    public List<Rubbish> findList(final Map<String, Object> queryMap) {
        return this.rubbishDao.findList(queryMap);
    }
    
    @Override
    public int getTotal(final Map<String, Object> queryMap) {
        return this.rubbishDao.getTotal(queryMap);
    }
}
