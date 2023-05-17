// 
// 
// 

package com.edu.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.edu.entity.admin.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.edu.dao.admin.LogDao;
import org.springframework.stereotype.Service;
import com.edu.service.admin.LogService;

@Service
public class LogServiceImpl implements LogService
{
    @Autowired
    private LogDao logDao;
    
    @Override
    public int add(final Log log) {
        return this.logDao.add(log);
    }
    
    @Override
    public List<Log> findList(final Map<String, Object> queryMap) {
        return this.logDao.findList(queryMap);
    }
    
    @Override
    public int getTotal(final Map<String, Object> queryMap) {
        return this.logDao.getTotal(queryMap);
    }
    
    @Override
    public int delete(final String ids) {
        return this.logDao.delete(ids);
    }
    
    @Override
    public int add(final String content) {
        final Log log = new Log();
        log.setContent(content);
        log.setCreateTime(new Date());
        return this.logDao.add(log);
    }
}
